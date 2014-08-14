package com.bikefunclub.blogcls.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bikefunclub.blogcls.model.*;

import javax.servlet.annotation.WebServlet;
@WebServlet("/BlogclsServlet")

public class BlogclsServlet extends HttpServlet {

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
				String str = req.getParameter("blogclsno");  //form 送過來的資料格式是String
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入網誌分類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				Integer blogclsno = null;
				try {
					blogclsno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("網誌分類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BlogclsService blogclsSvc = new BlogclsService();
				BlogclsVO blogclsVO = blogclsSvc.getOneBlogcls(blogclsno);
				if (blogclsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				// if (errorMsgs不是空的)
				if (!errorMsgs.isEmpty()) {  
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogcls/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogclsVO", blogclsVO); // 資料庫取出的empVO物件,存入req
				String url = "/blogcls/listOneBlogcls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogcls/select_page.jsp");
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
				Integer blogclsno = new Integer(req.getParameter("blogclsno"));
				
				/***************************2.開始查詢資料****************************************/
				BlogclsService blogclsSvc = new BlogclsService();
				BlogclsVO blogclsVO = blogclsSvc.getOneBlogcls(blogclsno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("blogclsVO", blogclsVO);         // 資料庫取出的empVO物件,存入req
				String url = "/blogcls/update_blogcls_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogcls/listAllBlogcls.jsp");
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
				String blogclsname = req.getParameter("blogclsname").trim();
				Integer blogclsno = new Integer(req.getParameter("blogclsno").trim());

				BlogclsVO blogclsVO = new BlogclsVO();
				blogclsVO.setBlogclsname(blogclsname);
				blogclsVO.setBlogclsno(blogclsno);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogclsVO", blogclsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogcls/update_blogcls_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BlogclsService blogclsSvc = new BlogclsService();
				blogclsVO = blogclsSvc.updateBlogcls(blogclsname , blogclsno);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogclsVO", blogclsVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/blogcls/listOneBlogcls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogcls/update_blogcls_input.jsp");
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
				String blogclsname = req.getParameter("blogclsname").trim();


				BlogclsVO blogclsVO = new BlogclsVO();
				blogclsVO.setBlogclsname(blogclsname);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogclsVO", blogclsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/blogcls/addBlogcls.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				BlogclsService blogclsSvc = new BlogclsService();
				blogclsVO = blogclsSvc.addBlogcls(blogclsname);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/blogcls/listAllBlogcls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogcls/addBlogcls.jsp");
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
				Integer blogclsno = new Integer(req.getParameter("blogclsno"));
				
				/***************************2.開始刪除資料***************************************/
				BlogclsService blogclsSvc = new BlogclsService();
				blogclsSvc.deleteBlogcls(blogclsno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/blogcls/listAllBlogcls.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
