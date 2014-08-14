package com.bikefunclub.Ann.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;/**支援圖片上傳*/
import com.bikefunclub.Ann.model.*;
import com.bikefunclub.emp.model.*;
@WebServlet("/AnnServlet")
public class AnnServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6706563385877341490L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		//取得員工編號
		HttpSession session = req.getSession();
		EmpVO empVO = (EmpVO)session.getAttribute("empVO");
		String action = null;
		MultipartRequest multi = null;
		// instance AdService
		AnnService annSvc = new AnnService();
		String contentType = req.getContentType();
		// 判斷request的contentType
		if (contentType != null&&contentType.startsWith("multipart/form-data")) {
			/** 建立MultipartRequest實體 */
			multi = new MultipartRequest(req, getServletContext().getRealPath(
					"img"), 5 * 1024 * 1024, "UTF-8");
			action = multi.getParameter("action");
		} else {
			action = req.getParameter("action");
		}

		if ("getAnn_info".equals(action)) { // 來自listAllAd.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/** 1.接收請求參數 */
				Integer annno = new Integer(req.getParameter("annno"));

				/** 2.開始查詢資料 */
				AnnVO annVO = annSvc.getOneAnn(annno);

				/** 3.查詢完成,準備轉交(Send the Success view) */
				req.setAttribute("annVO", annVO); // 資料庫取出的adVO物件,存入req
				System.out.println(annVO.getAnnno());
				String url = "/front/ann/page_ann_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交page_update_ad_input.jsp
				successView.forward(req, res);

				/** 錯誤處理,轉回來源網頁路徑 */
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/ann/page_listAllann.jsp");
				failureView.forward(req, res);
			}
		}		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAd.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 為【/ann/listAllann.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁,
														// 存入req(只用於:istAllEmp.jsp)
			try {
				/** 1.接收請求參數 */
				Integer annno = new Integer(req.getParameter("annno"));

				/** 2.開始查詢資料 */
				AnnVO annVO = annSvc.getOneAnn(annno);

				/** 3.查詢完成,準備轉交(Send the Success view) */
				req.setAttribute("annVO", annVO); // 資料庫取出的adVO物件,存入req
				String url = "/back/ann/page_update_ann_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交page_update_ad_input.jsp
				successView.forward(req, res);

				/** 錯誤處理,轉回來源網頁路徑 */
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {// 來自update_ad_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = multi.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = multi.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);

			/** 1.接收請求參數 ,上傳檔案、 輸入格式的錯誤處理 */
			try {
				Integer annno = new Integer(multi.getParameter("annno"));
				Integer empno = empVO.getEmpno();
				String anntitle = multi.getParameter("anntitle").trim();
				String anncontent = multi.getParameter("anncontent").trim();

				Date date = new Date(System.currentTimeMillis());

				Timestamp anndate = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date).toString());

				InputStream in = null;
				byte[] annfile = null;
				String filename = "";
				String extname = "";

				AnnVO annVO = upload(multi, annSvc, annno, anntitle,
						anncontent, anndate, in, annfile, filename, extname);

				annfile = annVO.getAnnfile();
				filename = annVO.getAnnfilename();
				extname = annVO.getAnnextname();

				if (anntitle.isEmpty()) {
					errorMsgs.add("請輸入廣告標題");
				}
//				if (annfile == null) {
//					errorMsgs.add("請選擇檔案!");
//				}
				if (anndate == null) {
					errorMsgs.add("請輸入日期!");
				}
				if (anncontent == null) {
					errorMsgs.add("請輸入公告內容!");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("annVO", annVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/ann/page_update_ann_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/

				annVO = annSvc.updateAnn(annno, empno, anntitle, anncontent,
						anndate, annfile, filename, extname);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = requestURL + "?whichPage=" + whichPage + "&annno="
						+ annno;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交送出修改的來源網頁路徑
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ann/page_update_ann_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addann.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer annno = 0;// annno欄位是由sequence產生
				Integer empno = empVO.getEmpno();
				String anntitle = multi.getParameter("anntitle").trim();
				String anncontent = multi.getParameter("anncontent").trim();

				Date date = new Date(System.currentTimeMillis());

				Timestamp anndate = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date).toString());

				InputStream in = null;
				byte[] annfile = null;
				String filename = "";
				String extname = "";

				AnnVO annVO = upload(multi, annSvc, annno, anntitle,
						anncontent, anndate, in, annfile, filename, extname);
				annfile = annVO.getAnnfile();
				filename = annVO.getAnnfilename();
				extname = annVO.getAnnfilename();

				if (anntitle.isEmpty()) {
					errorMsgs.add("請輸入公告標題");
				}
//				if (annfile == null) {
//					errorMsgs.add("請選擇檔案!");
//				}
				if (anndate == null) {
					errorMsgs.add("請輸入日期!");
				}
				if (anncontent == null) {
					errorMsgs.add("請輸入公告內容!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("annVO", annVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/ann/page_add_ann_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 *****************************************/
				annVO = annSvc.addAnn(empno, anntitle, anncontent, anndate,
						annfile, filename, extname);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back/ann/page_listAllAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交page_listAllAnn.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ann/page_add_ann_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllAd.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑:
																// 可能為【/add/listAllAd.jsp】
			String whichPage = req.getParameter("whichPage"); // 送出刪除的來源網頁的第幾頁(只用於:listAllAd.jsp)
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer annno = new Integer(req.getParameter("annno"));

				/*************************** 2.開始刪除資料 ***************************************/
				annSvc.deleteAnn(annno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL + "?whichPage=" + whichPage; // 送出刪除的來源網頁的第幾頁(只用於:listAllAd.jsp)
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

	}

	/** 上傳圖片至資料夾、資料庫、存AdVO */
	public AnnVO upload(MultipartRequest multi, AnnService annSvc,
			Integer annno, String anntitle, String anncontent,
			Timestamp anndate, InputStream in, byte[] annfile, String filename,
			String extname) throws IOException {
		AnnVO annVO = new AnnVO();
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
					filename = filenameall.substring(0, dotPos);
					extname = filenameall.substring(dotPos + 1);

					// 將file轉成InputStream
					in = new FileInputStream(f);
					// InputStream轉型byte array
					int picsize = in.available();
					annfile = new byte[picsize];
					in.read(annfile);

					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {// f == null
				if (annno == 0) {// insert時,break
					break;
				}
				annVO = annSvc.getOneAnn(annno);
				annno = annVO.getAnnno();
				anntitle = annVO.getAnntitle();
				anncontent = annVO.getAnncontent();
				anndate = annVO.getAnndate();
				annfile = annVO.getAnnfile();
				filename = annVO.getAnnfilename();
				extname = annVO.getAnnfilename();
			}
			// 存到AnnVO
			annVO.setAnntitle(anntitle);
			annVO.setAnncontent(anncontent);
			annVO.setAnndate(anndate);
			annVO.setAnnfile(annfile);
			annVO.setAnnfilename(filename);
			annVO.setAnnextname(extname);

		}
		return annVO;
	}
}
