package com.bikefunclub.emp.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.bikefunclub.emp.model.*;

@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		EmpService empSvc = new EmpService();

		if ("getOne_For_Update".equals(action)) {

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
				/*************************** 1.接收請求參數 ****************************************/
				Integer empno = new Integer(req.getParameter("empno"));

				/*************************** 2.開始查詢資料 ****************************************/

				EmpVO empVO = empSvc.getOneEmp(empno);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("empVO", empVO); // 資料庫取出empVO物件,存入req
				String url = "/back/Emp/page_update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);

				failureView.forward(req, res);

			}

		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			System.out.println("int00");
			try {
				/*************************** 1.接收請求參數 -輸入格式錯誤處理 **********************/

				Integer empno = new Integer(req.getParameter("empno").trim());
				System.out.println("int01");
				String empacc = req.getParameter("empacc").trim();

				// String emppw = req.getParameter("emppw").trim();

				String empname = req.getParameter("empname").trim();
				System.out.println("int02");
				String empemail = req.getParameter("empemail").trim();

				String empaddr = req.getParameter("empaddr").trim();

				String empid = req.getParameter("empid").trim();

				// Timestamp 寫法
				Date date = new Date(System.currentTimeMillis());
				Timestamp emprgdate = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date));

				String emptel = req.getParameter("emptel").trim();
				System.out.println("int03");
				// 放值
				EmpVO empVO = new EmpVO();
				empVO.setEmpacc(empacc);
				// empVO.setEmppw(emppw);
				empVO.setEmpname(empname);
				empVO.setEmpemail(empemail);
				empVO.setEmpaddr(empaddr);
				empVO.setEmpid(empid);
				empVO.setEmprgdate(emprgdate);
				empVO.setEmptel(emptel);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/Emp/update_emp_input.jsp");
					failureView.forward(req, res);

					return; // 程式結束
				}

				/*************************** 2.開始修改資料 *****************************************/

				empVO = empSvc.updateEmp(empno, empacc,/* emppw, */empname,
						empemail, empaddr, empid, emprgdate, emptel);

				/*************************** 3.修改資料,準備轉交(Send the Success view) *************/
				String url = requestURL + "?whichPage=" + whichPage + "&empno="
						+ empno;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交送出修改的來源網頁路徑

				successView.forward(req, res);

				/*************************** 其他可能錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("資料修改失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/Emp/page_update_emp_input.jsp");

				failureView.forward(req, res);

			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 -輸入格式錯誤處理 *************************/

				String empacc = req.getParameter("empacc").trim();
				String emppw = getRegSN();

				String empname = req.getParameter("empname").trim();
				// 檢查姓名格式
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empname == null || empname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!empname.trim().matches(enameReg)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字，請重填");
				}

				String empemail = req.getParameter("empemail").trim();
				// 信箱檢查
				String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (empemail == null || empemail.trim().length() == 0) {
					errorMsgs.add("email': 請勿空白");
				} else if (!empemail.trim().matches(emailReg)) {
					errorMsgs.add("e-mail格式不正確，請重填!!");
				}

				String empaddr = req.getParameter("empaddr").trim();
				String empid = req.getParameter("empid").trim();

				// Timestamp 寫法
				Date date = new Date(System.currentTimeMillis());
				Timestamp emprgdate = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date));

				String emptel = req.getParameter("emptel").trim();

				Boolean flag = isusedaccount(empSvc, empacc);
				Boolean flagid = isusedIdentity(empSvc, empid);

				// 驗證
				// 帳號檢查
				if (empacc == null || empacc.trim().length() == 0) {
					errorMsgs.add("員工帳號': 請勿空白");
				} else if (flag == true) {
					errorMsgs.add("此帳號已經被使用，請重填!!");
				}

				// 身分證號
				if (empid == null || empid.trim().length() == 0) {
					errorMsgs.add("員工身分證號': 請勿空白");
				} else if (flagid == true) {
					errorMsgs.add("此身分證號已經被使用，請重填!!");
				}

				// 電話
				if (emptel.isEmpty()) {
					errorMsgs.add("請輸入電話!");
				}

				// 放值
				EmpVO empVO = new EmpVO();
				empVO.setEmpacc(empacc);
				empVO.setEmppw(emppw);
				empVO.setEmpname(empname);
				empVO.setEmpemail(empemail);
				empVO.setEmpaddr(empaddr);
				empVO.setEmpid(empid);
				empVO.setEmprgdate(emprgdate);
				empVO.setEmptel(emptel);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO2", empVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/Emp/page_add_emp_input.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/

				empVO = empSvc.addEmp(empacc, emppw, empname, empemail,
						empaddr, empid, emprgdate, emptel);

				req.setAttribute("emppw", emppw);

				/*************************** 3.新增資料,準備轉交(Send the Success view) ***********/
				req.setAttribute("option", "include");
				RequestDispatcher dispathcher = req
						.getRequestDispatcher("/back/Emp/JavaMailProccess.jsp");
				dispathcher.include(req, res);

				String url = "/back/Emp/page_listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/Emp/page_add_emp_input.jsp");
				failureView.forward(req, res);

			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer empno = new Integer(req.getParameter("empno"));

				/*************************** 2.開始刪除資料 ***************************************/

				empSvc.deleteEmp(empno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能錯誤 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料錯誤:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

	}/* 判斷新員工的帳號是否已經被舊會員使用;被使用回傳true,未被使用回傳false */

	public Boolean isusedaccount(EmpService empSvc, String empacc) {
		Boolean flag = false;
		List<EmpVO> list = empSvc.getAll();
		for (EmpVO empVO : list) {
			String empoldacc = empVO.getEmpacc();
			if (empoldacc.equals(empacc)) {
				flag = true;
				break;
			}
		}
		return flag;

	}

	/* 判斷新員工的身分證號是否已經被使用;被使用回傳true,未被使用回傳false */
	public Boolean isusedIdentity(EmpService empSvc, String empid) {
		Boolean flagid = false;
		List<EmpVO> list = empSvc.getAll();
		for (EmpVO empVO : list) {
			String empoldid = empVO.getEmpid();
			if (empoldid.equals(empid)) {
				flagid = true;
				break;
			}
		}
		return flagid;

	}

	// 密碼亂數
	private String regsn;
	private int pwlength = 5; // 密碼長度設定

	private String getRegSN() {
		regsn = "";
		String[] RegSNContent = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z" };
		for (int i = 0; i < pwlength; i++)
			regsn += RegSNContent[(int) (Math.random() * RegSNContent.length)];
		return regsn;
	}

}
