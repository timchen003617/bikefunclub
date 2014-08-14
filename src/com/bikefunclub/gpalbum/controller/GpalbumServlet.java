package com.bikefunclub.gpalbum.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bikefunclub.gpalbum.model.*;

import javax.servlet.annotation.WebServlet;
@WebServlet("/GpalbumServlet")

public class GpalbumServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str1 = req.getParameter("albno");
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入相簿編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gpalbum/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
							
				/***************************2.開始查詢資料*****************************************/
				Integer albno = new Integer(str1);
				GpalbumService gpalbumSvc = new GpalbumService();
				GpalbumVO gpalbumVO = gpalbumSvc.getOneAlbno(albno);
				if (albno == null){
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gpalbum/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("gpalbumVO", gpalbumVO); // 資料庫取出的empVO物件,存入req
				String url = "/back/gpalbum/listOneGpalbum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/gpalbum/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer albno = new Integer(req.getParameter("albno"));
//				Integer photono = new Integer(req.getParameter("photono"));
//				
//				/***************************2.開始查詢資料****************************************/
//				GpalbumService gpalbumSvc = new GpalbumService();
//				GpalbumVO gpalbumVO = gpalbumSvc.getOneGpalbum(albno , photono);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("gpalbumVO", gpalbumVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/gpalbum/update_gpalbum_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/gpalbum/listAllGpalbum.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer albno = new Integer(req.getParameter("albno").trim());
//				Integer photono = new Integer(req.getParameter("photono").trim());			
//				
//
//				GpalbumVO gpalbumVO = new GpalbumVO();
//				gpalbumVO.setAlbno(albno);
//				gpalbumVO.setPhotono(photono);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("gpalbumVO", gpalbumVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/gpalbum/update_gpalbum_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				GpalbumService gpalbumSvc = new GpalbumService();
//				gpalbumVO = gpalbumSvc.updateGpalbum(albno , photono);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("albclsVO", albclsVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/albcls/listOneAlbcls.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/albcls/update_albcls_input.jsp");
//				failureView.forward(req, res);
//			}
//		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer albno = new Integer(req.getParameter("albno").trim());
				Integer photono = new Integer(req.getParameter("photono").trim());

				GpalbumVO gpalbumVO = new GpalbumVO();
				gpalbumVO.setAlbno(albno);
				gpalbumVO.setPhotono(photono);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gpalbumVO", gpalbumVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gpalbum/addGpalbum.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GpalbumService gpalbumSvc = new GpalbumService();
				gpalbumVO = gpalbumSvc.addGpalbum(albno , photono);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/gpalbum/listAllGpalbum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/gpalbum/addGpalbum.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer albno = new Integer(req.getParameter("albno"));
				Integer photono = new Integer(req.getParameter("photono"));
				
				/***************************2.開始刪除資料***************************************/
				GpalbumService gpalbumSvc = new GpalbumService();
				gpalbumSvc.deleteGpalbum(albno , photono);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back/gpalbum/listAllGpalbum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/gpalbum/listAllGpalbum.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
