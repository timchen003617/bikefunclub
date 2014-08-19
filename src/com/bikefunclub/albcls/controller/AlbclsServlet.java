package com.bikefunclub.albcls.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bikefunclub.albcls.model.*;
import com.bikefunclub.album.model.AlbumVO;

import javax.servlet.annotation.WebServlet;
@WebServlet("/AlbclsServlet")

public class AlbclsServlet extends HttpServlet {

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
				String str = req.getParameter("albclsno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入相簿編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer albclsno = null;
				try {
					albclsno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("相簿分類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AlbclsService albclsSvc = new AlbclsService();
				AlbclsVO albclsVO = albclsSvc.getOneAlbcls(albclsno);
				if (albclsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("albclsVO", albclsVO); // 資料庫取出的empVO物件,存入req
				String url = "/front/albcls/listOneAlbcls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/albcls/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer albclsno = new Integer(req.getParameter("albclsno"));
				
				/***************************2.開始查詢資料****************************************/
				AlbclsService albclsSvc = new AlbclsService();
				AlbclsVO albclsVO = albclsSvc.getOneAlbcls(albclsno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("albclsVO", albclsVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front/albcls/page_update_albcls_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/albcls/page_listAllAlbcls.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer albclsno = new Integer(req.getParameter("albclsno").trim());
				String albclsname = req.getParameter("albclsname").trim();			
				

				AlbclsVO albclsVO = new AlbclsVO();
				albclsVO.setAlbclsno(albclsno);
				albclsVO.setAlbclsname(albclsname);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("albclsVO", albclsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/page_update_albcls_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AlbclsService albclsSvc = new AlbclsService();
				albclsVO = albclsSvc.updateAlbcls(albclsno , albclsname);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("albclsVO", albclsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back/albcls/page_listAllAlbcls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/albcls/page_update_albcls_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String albclsname = req.getParameter("albclsname").trim();

				AlbclsVO albclsVO = new AlbclsVO();
				albclsVO.setAlbclsname(albclsname);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("albclsVO", albclsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/page_addAlbcls.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AlbclsService albclsSvc = new AlbclsService();
				albclsVO = albclsSvc.addAlbcls(albclsname);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/albcls/page_listAllAlbcls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/albcls/page_addAlbcls.jsp");
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
				Integer albclsno = new Integer(req.getParameter("albclsno"));
				
				/***************************2.開始刪除資料***************************************/
				AlbclsService albclsSvc = new AlbclsService();
				albclsSvc.deleteAlbcls(albclsno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/albcls/page_listAllAlbcls.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOneAlbcls_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("albclsno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入相簿分類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer albclsno = null;
				try {
					albclsno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("相簿分類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/getOneAlbcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AlbclsService albclsSvc = new AlbclsService();
				List<AlbumVO> listAlbum = albclsSvc.findByAlbum(albclsno);
				AlbclsVO albclsVO = new AlbclsVO();
				albclsVO.setAlbclsno(albclsno);	
				if (listAlbum == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listAlbum", listAlbum); // 資料庫取出的empVO物件,存入req
				req.setAttribute("albclsVO", albclsVO);
				String url = "/front/albcls/page_getOneAlbcls.jsp"; //列出該相簿分類所有相簿
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交getOneAlbcls.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/albcls/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("back_getOneAlbcls_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("albclsno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入相簿分類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer albclsno = null;
				try {
					albclsno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("相簿分類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/getOneAlbcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AlbclsService albclsSvc = new AlbclsService();
				List<AlbumVO> listAlbum = albclsSvc.findByAlbum(albclsno);
				AlbclsVO albclsVO = new AlbclsVO();
				albclsVO.setAlbclsno(albclsno);	
				if (listAlbum == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/albcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listAlbum", listAlbum); // 資料庫取出的empVO物件,存入req
				req.setAttribute("albclsVO", albclsVO);
				String url = "/back/albcls/page_getOneAlbcls.jsp"; //列出該相簿分類所有相簿
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交getOneAlbcls.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/albcls/page_listAllAlbcls.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("back_getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer albclsno = new Integer(req.getParameter("albclsno"));
				
				/***************************2.開始查詢資料****************************************/
				AlbclsService albclsSvc = new AlbclsService();
				AlbclsVO albclsVO = albclsSvc.getOneAlbcls(albclsno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("albclsVO", albclsVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back/albcls/page_update_albcls_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/albcls/page_listAllAlbcls.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("back_delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer albclsno = new Integer(req.getParameter("albclsno"));
				
				/***************************2.開始刪除資料***************************************/
				AlbclsService albclsSvc = new AlbclsService();
				albclsSvc.deleteAlbcls(albclsno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/albcls/page_listAllAlbcls.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
