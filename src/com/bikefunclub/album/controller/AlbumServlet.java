package com.bikefunclub.album.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bikefunclub.album.model.*;
import com.bikefunclub.photo.model.PhotoVO;

import javax.servlet.annotation.WebServlet;
@WebServlet("/AlbumServlet")

public class AlbumServlet extends HttpServlet {

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
				String str = req.getParameter("albno");  //form 送過來的資料格式是String
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入相簿編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/album/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				Integer albno = null;
				try {
					albno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("相簿編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/album/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AlbumService albumSvc = new AlbumService();
				AlbumVO albumVO = albumSvc.getOneAlbum(albno);
				if (albumVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				// if (errorMsgs不是空的)
				if (!errorMsgs.isEmpty()) {  
					RequestDispatcher failureView = req
							.getRequestDispatcher("/album/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("albumVO", albumVO); // 資料庫取出的empVO物件,存入req
				String url = "/back/album/listOneAlbum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/album/select_page.jsp");
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
				Integer albno = new Integer(req.getParameter("albno"));
				
				/***************************2.開始查詢資料****************************************/
				AlbumService albumSvc = new AlbumService();
				AlbumVO albumVO = albumSvc.getOneAlbum(albno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("albumVO", albumVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front/album/page_update_album_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/album/listAllAlbum.jsp");
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
				Integer memno = new Integer(req.getParameter("memno").trim());
				Integer albclsno = new Integer(req.getParameter("albclsno").trim());
				String authname = req.getParameter("authname").trim();
				String albtitle = req.getParameter("albtitle").trim();
				String albdesc = req.getParameter("albdesc").trim();
				Integer albno = new Integer(req.getParameter("albno").trim());
				Timestamp albtime = Timestamp.valueOf(req.getParameter("albtime"));

				AlbumVO albumVO = new AlbumVO();
				albumVO.setMemno(memno);
				albumVO.setAlbclsno(albclsno);
				albumVO.setAuthname(authname);
				albumVO.setAlbtitle(albtitle);
				albumVO.setAlbdesc(albdesc);
				albumVO.setAlbtime(albtime);
				albumVO.setAlbno(albno);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("albumVO", albumVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/album/update_album_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AlbumService albumSvc = new AlbumService();
				albumVO = albumSvc.updateAlbum(albno , memno , albclsno , authname , albtitle , albdesc , albtime);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("albumVO", albumVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front/album/page_listOneAlbum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/album/update_album_input.jsp");
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
				Integer memno = new Integer(req.getParameter("memno").trim());
				Integer albclsno = new Integer(req.getParameter("albclsno").trim());
				String authname = req.getParameter("authname");
				String albtitle = req.getParameter("albtitle");
				String albdesc = req.getParameter("albdesc");


				AlbumVO albumVO = new AlbumVO();
				albumVO.setMemno(memno);
				albumVO.setAlbclsno(albclsno);
				albumVO.setAuthname(authname);
				albumVO.setAlbtitle(albtitle);
				albumVO.setAlbdesc(albdesc);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("albumVO", albumVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/album/addAlbum.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AlbumService albumSvc = new AlbumService();
				albumVO = albumSvc.addAlbum(memno , albclsno , authname , albtitle , albdesc);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/album/page_AllAlbum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/album/addAlbum.jsp");
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
				
				/***************************2.開始刪除資料***************************************/
				AlbumService albumSvc = new AlbumService();
				albumSvc.deleteAlbum(albno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/albcls/page_getOneAlbcls.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("GET_ALBNO_TO_PHOTO".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("albno");  //form 送過來的資料格式是String
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/album/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				Integer albno = null;
				
				albno = new Integer(str);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/album/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AlbumService albumSvc = new AlbumService();
				List<PhotoVO> listPohto = albumSvc.getAlbno(albno);
				AlbumVO albumVO = new AlbumVO();
				albumVO.setAlbno(albno);
				
				// Send the use back to the form, if there were errors
				// if (errorMsgs不是空的)
				if (!errorMsgs.isEmpty()) {  
					RequestDispatcher failureView = req
							.getRequestDispatcher("/album/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("listPohto", listPohto); // 資料庫取出的empVO物件,存入req
				req.setAttribute("albumVO", albumVO);
				String url = "/front/album/page_OneAlbum_TO_PHOTO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/album/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("GET_ALBNO_TO_Delete".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer albno = new Integer(req.getParameter("albno"));
				Integer photono = new Integer(req.getParameter("photono"));

				
				/***************************2.開始刪除資料***************************************/
				AlbumService albumSvc = new AlbumService();
				AlbumVO albumVO = albumSvc.getOneAlbum(albno);
				albumSvc.deleteGpalbum(photono);
				List<PhotoVO> listPohto = albumSvc.getAlbno(albno);

				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = req.getParameter("requestURL");
				req.setAttribute("albumVO", albumVO);
				req.setAttribute("listPohto", listPohto); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/album/OneAlbum_TO_PHOTO.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
