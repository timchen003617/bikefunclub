package com.bikefunclub.report.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bikefunclub.report.model.ReportService;
import com.bikefunclub.report.model.ReportVO;

public class ReportServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("repdate");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉日期");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/report/report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer repno = null;
				try {
					repno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/report/report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ReportService repSvc = new ReportService();
				ReportVO repVO = repSvc.getOneReport(repno);
				if (repVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/report/report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("repVO", repVO); // 資料庫取出的reportVO物件,存入req
				String url = "/back/report/report_listOneReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneReport.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/report/report.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) {// 來自listAllReport.jsp的請求

		}
		if ("update".equals(action)) {// 來自update_report_input.jsp的請求

		}
		if ("insert".equals(action)) { // 來自addReport.jsp的請求

		}
		if ("delete".equals(action)) { // 來自listAllReport.jsp

		}
	}
}
