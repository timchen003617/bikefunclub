package com.bikefunclub.blog.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bikefunclub.blog.model.*;
import com.bikefunclub.gp.model.GpService;
import com.bikefunclub.gp.model.GpVO;
import com.bikefunclub.memgp.model.MemgpService;
import com.bikefunclub.memgp.model.MemgpVO;
import com.bikefunclub.rot.model.RotService;
import com.bikefunclub.rot.model.RotVO;

import javax.servlet.annotation.WebServlet;
@WebServlet("/BlogServlet")

public class BlogServlet extends HttpServlet {

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
	
		
		//當點選詳細表列出該筆相關資訊
		if ("getBlog_info".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				Integer blogno = new Integer(req.getParameter("blogno"));

				/*************************** 2.開始查詢資料 ****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blogno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("blogVO", blogVO); // 資料庫取出的list物件,存入request

				String url = "/front/blog/page_blog_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_listAllBlog.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("blogno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入網誌編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/blog/page_update_blog_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer blogno = null;
				try {
					blogno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("網誌編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/blog/page_update_blog_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blogno);
				if (blogVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/blog/page_update_blog_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogVO", blogVO); // 資料庫取出的empVO物件,存入req
				String url = "/front/blog/page_listAllblog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_update_blog_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { //
        
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer blogno = new Integer(req.getParameter("blogno"));
			
				/***************************2.開始查詢資料****************************************/
				BlogService blogSvc = new BlogService();
				BlogVO blogVO = blogSvc.getOneBlog(blogno);
							
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("blogVO", blogVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front/blog/page_update_blog_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_update_blog_input.jsp");
				
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_blog_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer blogno = new Integer(req.getParameter("blogno").trim());
				
				Integer memno = new Integer(req.getParameter("memno").trim());
				
				Integer blogclsno = new Integer(req.getParameter("blogclsno").trim());
				
				String bgtitle = req.getParameter("bgtitle");
				
				String bgtext = req.getParameter("bgtext");
				
				String authname = req.getParameter("authname");
				
				Date date = new Date(System.currentTimeMillis());
				Timestamp bgtime = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date));
				
				
				
				BlogVO blogVO = new BlogVO();
				blogVO.setBlogno(blogno);
				blogVO.setMemno(memno);
				blogVO.setBlogclsno(blogclsno);
				blogVO.setBgtitle(bgtitle);
				blogVO.setBgtext(bgtext);
				blogVO.setAuthname(authname);
				blogVO.setBgtime(bgtime);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/blog/page_update_blog_input.jsp");
					
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				BlogService blogSvc = new BlogService();
				blogVO = blogSvc.updateBlog(blogno ,memno ,blogclsno ,bgtitle ,bgtext , 
		                                    authname ,bgtime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("blogVO", blogVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front/blog/page_manage_blog.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_update_blog_input.jsp");
				
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
        	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer memno = new Integer(req.getParameter("memno").trim());	
				
				Integer blogclsno = new Integer(req.getParameter("blogclsno").trim());
				
				String bgtitle = req.getParameter("bgtitle");
				
				String bgtext = req.getParameter("bgtext");
				
				String authname = req.getParameter("authname");
			
				Date date = new Date(System.currentTimeMillis());
				Timestamp bgtime = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date));
				
                
				BlogVO blogVO = new BlogVO();
				blogVO.setMemno(memno);
				blogVO.setBlogclsno(blogclsno);
				blogVO.setBgtitle(bgtitle);
				blogVO.setBgtext(bgtext);
				blogVO.setAuthname(authname);
				blogVO.setBgtime(bgtime);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/blog/page_insert_blog.jsp");
					
					failureView.forward(req, res);
					
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				BlogService blogSvc = new BlogService();
				
				blogVO = blogSvc.addBlog(memno ,blogclsno ,bgtitle ,bgtext ,authname);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
			
				String url = "/front/blog/page_listAllblog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				
				successView.forward(req, res);	
				
				
				/***************************其他可能的錯誤處理**********************************/
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_insert_blog.jsp");
				
				failureView.forward(req, res);
			}
		}
		
		
    	if ("delete_launchblog".equals(action)) { // 來自listAllEmp.jsp
           
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			  
			try {
				/***************************1.接收請求參數***************************************/
				
				Integer blogno = new Integer(req.getParameter("blogno"));
				  
				/***************************2.開始刪除資料***************************************/
				BlogService blogSvc = new BlogService();
				
				blogSvc.delete(blogno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				 
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_listAllblog.jsp");
				 
				failureView.forward(req, res);
			}
		}
		if ("launchblblog_info".equals(action)) {// 發起揪團詳細資料,來自launchgp_info.jsp
           
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			BlogService blogSvc = new BlogService();
			
		   try {
				/*************************** 1.接收請求參數 ****************************************/
                Integer blogno = new Integer(req.getParameter("blogno"));
               
				/*************************** 2.開始查詢資料 ****************************************/
				BlogVO blogVO = blogSvc.getOneBlog(blogno);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("blogVO", blogVO); // 資料庫取出的list物件,存入request

				String url = "/front/blog/page_launchblog_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_blog_info.jsp");
				
				failureView.forward(req, res);
			}
		}
		
		if ("launchblog".equals(action)) {// 發起揪團，來自page_insert_blog.jsp
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/** 1.接收請求參數 **/
			try {
				 
				Integer memno = new Integer(req.getParameter("memno").trim());		
                 
				Integer blogclsno = new Integer(req.getParameter("blogclsno").trim());
				 
				String bgtitle = req.getParameter("bgtitle");
				 
				String bgtext = req.getParameter("bgtext");
				 
				String authname = req.getParameter("authname");
				
				Date date = new Date(System.currentTimeMillis());
				Timestamp bgtime = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date));
				 
				/** 2 輸入格式的錯誤處理 **/

				if (bgtitle.isEmpty()) {
					errorMsgs.add("請輸入揪團標題!");
				}
				if (bgtext.isEmpty()) {
					errorMsgs.add("請輸入揪團描述!");
				}
				
				BlogVO blogVO = new BlogVO();
				blogVO.setMemno(memno);
				blogVO.setBlogclsno(blogclsno);
				blogVO.setBgtitle(bgtitle);
				blogVO.setBgtext(bgtext);
				blogVO.setAuthname(authname);
				blogVO.setBgtime(bgtime);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogVO", blogVO); // 含有輸入格式錯誤的gpVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/blog/page_insert_blog.jsp");
					failureView.forward(req, res);
					
					return; // 程式中斷
				}
				/*************************** 3.開始新增資料 *****************************************/
				BlogService blogSvc = new BlogService();
				blogVO = blogSvc.addBlog(memno ,blogclsno ,bgtitle ,bgtext ,authname);
				
				/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/foront/blog/page_listAllBlog.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				
				successView.forward(req, res);
				/** 其他可能的錯誤處理 **/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_insert_blog.jsp");
				
				failureView.forward(req, res);
			}

		}
		
		
	
	
	
	}
}
