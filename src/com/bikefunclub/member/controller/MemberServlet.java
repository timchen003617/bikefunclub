package com.bikefunclub.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikefunclub.member.model.MemService;
import com.bikefunclub.member.model.MemVO;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 6706563385877341496L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String action = null;
		MultipartRequest multi = null;
		// instance AdService
		MemService memSvc = new MemService();
		String contentType = req.getContentType();
		// 判斷request的contentType
		if (contentType != null&& contentType.startsWith("multipart/form-data")
				) {
			/** 建立MultipartRequest實體 */
			multi = new MultipartRequest(req, getServletContext().getRealPath(
					"img"), 5 * 1024 * 1024, "UTF-8");
			action = multi.getParameter("action");
		} else {
			action = req.getParameter("action");
		}
		if ("insert".equals(action)) { // 來自memregister.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/** 1接收請求參數 ***/
				Integer memno = 0;// insert時，memno給0,因編號是由sequence產生
				String memname = multi.getParameter("name").trim();

				String memnickname = multi.getParameter("nickname").trim();
				String memsex = multi.getParameter("sex").trim();
				java.sql.Date membirth = null;
				// 生日 string cast cast sql date
				String birth = multi.getParameter("birthday").trim();
				if (!birth.isEmpty()) {
					membirth = java.sql.Date.valueOf(birth);
				}
				String memid = multi.getParameter("id").trim();
				String memtelm = multi.getParameter("telm").trim();
				String memtelh = "";
				String memtelo = "";
				String memgetmailyn = "N";//預設為未通過認證
				String mememail = multi.getParameter("email").trim();
				// 會員帳號=會員email的@前面字元
				int dotPos = mememail.indexOf('@');
				String memacc = "";
				if (dotPos >= 0) {
					memacc = mememail.substring(0, dotPos);
				}
				// 判斷會員帳號是否已被使用
				Boolean flag = isusedaccount(memSvc, memacc);

				String mempw = multi.getParameter("password").trim();
				String memzip = multi.getParameter("zip").trim();
				String memaddr = multi.getParameter("addr").trim();

				Date date = new Date(System.currentTimeMillis());

				Timestamp memrgdate = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date));
				InputStream in = null;
				byte[] memfile = null;
				String memfilename = "";
				String memextname = "";
				/** 2 輸入格式的錯誤處理 **/
				if (flag == true) {
					errorMsgs.add("此帳號已經被使用!");
				}
				if (memname.isEmpty()) {
					errorMsgs.add("請輸入姓名!");
				}
				if (mememail.isEmpty()) {
					errorMsgs.add("請輸入email!");
				}
				if (mempw.isEmpty()) {
					errorMsgs.add("請輸入密碼!");
				}
				// 上傳圖片
				MemVO memVO = upload(multi, memSvc, memno, memacc, mempw,
						memname, memnickname, memsex, membirth, memid, memtelh,
						memtelo, memtelm, mememail, memzip, memaddr, in,
						memfile, memfilename, memextname, memrgdate);
				memfile = memVO.getMemfile();
				memfilename = memVO.getMemfilename();
				memextname = memVO.getMemextname();
				
				memVO.setMemno(memno);
				memVO.setMemacc(memacc);
				memVO.setMempw(mempw);
				memVO.setMemname(memname);
				memVO.setMemid(memid);
				memVO.setMembirth(membirth);
				memVO.setMemnickname(memnickname);
				memVO.setMemfile(memfile);
				memVO.setMemfilename(memfilename);
				memVO.setMemextname(memextname);
				memVO.setMememail(mememail);
				memVO.setMemsex(memsex);
				memVO.setMemzip(memzip);
				memVO.setMemaddr(memaddr);
				memVO.setMemtelh(memtelh);
				memVO.setMemtelo(memtelo);
				memVO.setMemtelm(memtelm);
				memVO.setMemrgdate(memrgdate);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/mem/memregister.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 3.開始新增資料 *****************************************/
				memVO = memSvc.addmem(memacc, mempw, memname, memid, membirth,
						memnickname, memfile, memfilename, memextname,
						mememail, memsex, memzip, memaddr, memtelh, memtelo,
						memtelm,memgetmailyn,memrgdate);
				/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/
				//使用者認證的連結
				String getmailurl = "http://localhost:8081"+req.getContextPath()+req.getServletPath()+"?action='updategetmailyn'&memgetmailyn=Y";
				
				req.setAttribute("getmailurl", getmailurl);
				
				req.setAttribute("option", "include");
				RequestDispatcher dispathcher = req
						.getRequestDispatcher("/front/mem/JavaMailProccess.jsp");
				dispathcher.include(req, res);
				
				String url = "/front/home/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/mem/memregister.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updategetmailyn".equals(action)) {// 來自使用者信箱內文超連結的請求
			String memgetmailyn = req.getParameter("memgetmailyn");
			
		}
		
		if ("update".equals(action)) {// 來自update_mem_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/** 1接收請求參數 ***/
				Integer memno = new Integer(multi.getParameter("memno").trim());
				String memname = multi.getParameter("name").trim();
				String memnickname = multi.getParameter("nickname").trim();
				String memsex = multi.getParameter("sex").trim();
				// 生日 string cast cast sql date
				java.sql.Date membirth = java.sql.Date.valueOf(multi
						.getParameter("birthday").trim());

				String memid = multi.getParameter("id").trim();
				String memtelm = multi.getParameter("telm").trim();
				String memtelh = "";
				String memtelo = "";
				String mememail = multi.getParameter("email").trim();
				// 會員帳號=會員email的@前面字元
				int dotPos = mememail.indexOf('@');
				String memacc = mememail.substring(0, dotPos);

				String mempw = multi.getParameter("password").trim();
				String memzip = multi.getParameter("zip").trim();
				String memaddr = multi.getParameter("addr").trim();

				Date date = new Date(System.currentTimeMillis());

				Timestamp memrgdate = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date));
				InputStream in = null;
				byte[] memfile = null;
				String memfilename = "";
				String memextname = "";
				// 上傳圖片
				MemVO memVO = upload(multi, memSvc, memno, memacc, mempw,
						memname, memnickname, memsex, membirth, memid, memtelh,
						memtelo, memtelm, mememail, memzip, memaddr, in,
						memfile, memfilename, memextname, memrgdate);
				memfile = memVO.getMemfile();
				memfilename = memVO.getMemfilename();
				memextname = memVO.getMemextname();
				/** 2 輸入格式的錯誤處理 **/
				if (memname.isEmpty()) {
					errorMsgs.add("請輸入姓名!");
				}
				if (memsex.isEmpty()) {
					errorMsgs.add("請輸入性別!");
				}
				if (mememail.isEmpty()) {
					errorMsgs.add("請輸入email!");
				}
				if (mempw.isEmpty()) {
					errorMsgs.add("請輸入密碼!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/mem/page_update_mem_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 3.開始修改資料 *****************************************/
				memVO = memSvc.updateMem(memno, memacc, mempw, memname, memid,
						membirth, memnickname, memfile, memfilename,
						memextname, mememail, memsex, memzip, memaddr, memtelh,
						memtelo, memtelm);
				req.setAttribute("memVO", memVO);
				req.setAttribute("success", "success");
				/*************************** 4.修改完成,準備轉交(Send the Success view) ***********/
				String url = "/LoginOut";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/mem/page_update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
	}

	/** 上傳圖片至資料夾、資料庫、存memVO */
	public MemVO upload(MultipartRequest multi, MemService memSvc,
			Integer memno, String memacc, String mempw, String memname,
			String memnickname, String memsex, java.sql.Date membirth,
			String memid, String memtelh, String memtelo, String memtelm,
			String mememail, String memzip, String memaddr, InputStream in,
			byte[] memfile, String memfilename, String memextname,
			Timestamp memrgdate) throws IOException {
		MemVO memVO = new MemVO();
		/** 取得所有上傳檔案之輸入型態、名稱 */
		Enumeration<?> files = multi.getFileNames();// File物件 <input type="file"
													// name="key" value="取這裡">

		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();// 上一頁的upfile
			String filenameall = multi.getFilesystemName(name);// 真正檔案名稱
			File f = multi.getFile(name);

			if (f != null) {
				try {
					/** 檔案名稱拆成檔名副檔名 */
					int dotPos = filenameall.indexOf('.');
					memfilename = filenameall.substring(0, dotPos);
					memextname = filenameall.substring(dotPos + 1);

					// 將file轉成InputStream
					in = new FileInputStream(f);
					// InputStream轉型byte array
					int picsize = in.available();
					memfile = new byte[picsize];
					in.read(memfile);

					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {// f == null
				if (memno != 0) {// update時執行
					memVO = memSvc.getOnemem(memno);
					memfile = memVO.getMemfile();
					memfilename = memVO.getMemfilename();
					memextname = memVO.getMemextname();
				}
			}

			// 存到MemVO
			memVO.setMemno(memno);
			memVO.setMemacc(memacc);
			memVO.setMempw(mempw);
			memVO.setMemname(memname);
			memVO.setMemid(memid);
			memVO.setMembirth(membirth);
			memVO.setMemnickname(memnickname);
			memVO.setMemfile(memfile);
			memVO.setMemfilename(memfilename);
			memVO.setMemextname(memextname);
			memVO.setMememail(mememail);
			memVO.setMemsex(memsex);
			memVO.setMemzip(memzip);
			memVO.setMemaddr(memaddr);
			memVO.setMemtelh(memtelh);
			memVO.setMemtelo(memtelo);
			memVO.setMemtelm(memtelm);
			memVO.setMemrgdate(memrgdate);
		}
		return memVO;
	}

	/* 判斷新會員的帳號是否已經被舊會員使用;被使用回傳true,未被使用回傳false */
	public Boolean isusedaccount(MemService memSvc, String memacc) {
		Boolean flag = false;
		List<MemVO> list = memSvc.getAll();
		for (MemVO memVO : list) {
			String memoldacc = memVO.getMemacc();
			if (memoldacc.equals(memacc)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
