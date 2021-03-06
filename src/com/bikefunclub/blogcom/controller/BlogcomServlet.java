package com.bikefunclub.blogcom.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bikefunclub.blog.model.BlogService;
import com.bikefunclub.blog.model.BlogVO;
import com.bikefunclub.blogcom.model.*;

import javax.servlet.annotation.WebServlet;
@WebServlet("/BlogcomServlet")

public class BlogcomServlet extends HttpServlet {

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
				String str = req.getParameter("bgcomno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/blogcom/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer bgcomno = null;
				try {
					bgcomno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/blogcom/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				BlogcomService blogcomSvc = new BlogcomService();
				BlogcomVO blogcomVO = blogcomSvc.getOneBlogcom(bgcomno);
				if (blogcomVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/blogcom/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogcomVO", blogcomVO); // 資料庫取出的empVO物件,存入req
				String url = "/back/blogcom/listOneBlogcom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/blogcom/select_page.jsp");
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
				Integer bgcomno = new Integer(req.getParameter("bgcomno"));
				
				/***************************2.開始查詢資料****************************************/
				BlogcomService blogcomSvc = new BlogcomService();
				BlogcomVO blogcomVO = blogcomSvc.getOneBlogcom(bgcomno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("blogcomVO", blogcomVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back/blogcom/update_blogcom_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/blogcom/listAllBlogcomVO.jsp");
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
				Integer blogno = new Integer(req.getParameter("blogno").trim());	
				Integer memno = new Integer(req.getParameter("memno").trim());
				String bgcomtext = req.getParameter("bgcomtext").trim();
				Timestamp bgcomtime = Timestamp.valueOf(req.getParameter("bgcomtime"));
				Integer bgcomno = new Integer(req.getParameter("bgcomno").trim());
				

				BlogcomVO blogcomVO = new BlogcomVO();
				blogcomVO.setBlogno(blogno);
				blogcomVO.setMemno(memno);
				blogcomVO.setBgcomtext(bgcomtext);
				blogcomVO.setBgcomtime(bgcomtime);
				blogcomVO.setBgcomno(bgcomno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogcomVO", blogcomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/blogcom/update_blogcom_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				BlogcomService blogcomSvc = new BlogcomService();
				blogcomVO = blogcomSvc.updateBlogcom(blogno , memno , bgcomtext , bgcomtime , bgcomno);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blogcomVO", blogcomVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back/blogcom/listOneBlogcom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/blogcom/update_blogcom_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			BlogService blogSvc = new  BlogService();
			
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer blogno = new Integer(req.getParameter("blogno").trim());
				
				Integer memno = new Integer(req.getParameter("memno").trim());
				
				String bgcomtext = req.getParameter("bgcomtext").trim();
				
				//留言時間
				Date date = new Date(System.currentTimeMillis());
				
				Timestamp bgcomtime = Timestamp
						.valueOf(new java.text.SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss").format(date));
				if(bgcomtext == null){
					errorMsgs.add("請輸入網誌內容");
				}
				
				
				
				BlogcomVO blogcomVO = new BlogcomVO();
				blogcomVO.setBlogno(blogno);
				blogcomVO.setMemno(memno);
				blogcomVO.setBgcomtext(bgcomtext);
				blogcomVO.setBgcomtime(bgcomtime);
				
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blogcomVO", blogcomVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/blog/page_listAllblog.jsp");
					
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				BlogcomService blogcomSvc = new BlogcomService();
				
				blogcomVO = blogcomSvc.addBlogcom(blogno , memno , bgcomtext);
				BlogVO blogVO = blogSvc.findByPrimaryKey(blogno);
				req.setAttribute("blogVO", blogVO);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/blog/page_blog_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_blog_info.jsp");
				
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer bgcomno = new Integer(req.getParameter("bgcomno"));
				
				/***************************2.開始刪除資料***************************************/
				BlogcomService blogcomSvc = new BlogcomService();
				blogcomSvc.deleteBlogcom(bgcomno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/blogcom/listAllBlogcom.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete_fromFront".equals(action)) { // 來自listAllEmp.jsp
            
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);
			
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			
			
			BlogService blogSvc = new  BlogService();
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer bgcomno = new Integer(req.getParameter("bgcomno"));
				Integer blogno = new Integer(req.getParameter("blogno"));			
				/***************************2.開始刪除資料***************************************/
				BlogcomService blogcomSvc = new BlogcomService();
				blogcomSvc.deleteBlogcom_fromFront(bgcomno);
				
				
				BlogVO blogVO = blogSvc.findByPrimaryKey(blogno);
				req.setAttribute("blogVO", blogVO);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				
				
				String url = req.getParameter("requestURL");
				
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				
				
				
				
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/blog/page_blog_info.jsp");
				
				failureView.forward(req, res);
			}
		}
		
		
	}
}
