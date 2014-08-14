package com.bikefunclub.gpcls.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikefunclub.gp.model.GpService;
import com.bikefunclub.gp.model.GpVO;
import com.bikefunclub.gpcls.model.GpclsService;
import com.bikefunclub.gpcls.model.GpclsVO;




@WebServlet("/Gpcls.do")
public class GpclsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GpclsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String gpclsname = req.getParameter("gpclsname").trim();

				GpclsVO gpclsVO = new GpclsVO();
				gpclsVO.setGpclsname(gpclsname);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gpclsVO", gpclsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gpcls/page_insert_gpcls.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				GpclsService gpclsSvc = new GpclsService();
				gpclsVO = gpclsSvc.insert(gpclsVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/gpcls/page_listAllGpcls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("輸入的值請勿空白!!");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/gpcls/page_insert_gpcls.jsp");
				failureView.forward(req, res);
			}
		}	
		if ("getOne_For_Updategpcls".equals(action)) { // 來自揪團for修改揪團分類

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:【/emp/listAllGpcls.jsp】
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer gpno = new Integer(req.getParameter("gpno"));
				
				/***************************2.開始查詢資料****************************************/
				GpService gpSvc = new GpService();
				GpVO gpVO= gpSvc.findByPrimaryKey(gpno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("gpVO", gpVO); // 資料庫取出的gpclsVO物件,存入req
				String url = "/back/gpcls/page_update_gpcls_fromgp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_gpcls_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}		
		if ("getOne_For_Update".equals(action)) { // 來自listAllGpcls.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:【/emp/listAllGpcls.jsp】
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer gpclsno = new Integer(req.getParameter("gpclsno"));
				
				/***************************2.開始查詢資料****************************************/
				GpclsService gpclsSvc = new GpclsService();
				GpclsVO gpclsVO = gpclsSvc.findByPrimaryKey(gpclsno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("gpclsVO", gpclsVO); // 資料庫取出的gpclsVO物件,存入req
				String url = "/back/gpcls/page_update_gpcls.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_gpcls_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}	
		if ("update_Gpcls_FromGp".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer gpclsno = new Integer(req.getParameter("gpclsno"));
				Integer gpno = new Integer(req.getParameter("gpno"));

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gpno", gpno); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gpcls/page_update_Gpcls_fromgp.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				GpService gpSvc = new GpService();
				//GpVO gpVO =new GpVO();
				gpSvc.updateGpByGpclsno(gpclsno, gpno);
				List<GpVO> list = gpSvc.getGpsBygpclsno(gpclsno);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				req.setAttribute("listGps_Bygpclsno", list);
				req.setAttribute("gpclsno", gpclsno);
				RequestDispatcher successView = req.getRequestDispatcher("/back/gpcls/page_listAllGpcls.jsp");   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/gpcls/page_update_Gpcls_fromgp.jsp");
				failureView.forward(req, res);
			}
		}		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
			//System.out.println(requestURL);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer gpclsno = new Integer(req.getParameter("gpclsno").trim());
				String gpclsname = req.getParameter("gpclsname").trim();

				GpclsVO gpclsVO = new GpclsVO();
				gpclsVO.setGpclsno(gpclsno);
				gpclsVO.setGpclsname(gpclsname);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gpclsVO", gpclsVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gpcls/page_update_gpcls.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				GpclsService gpclsSvc = new GpclsService();
				gpclsVO = gpclsSvc.update(gpclsVO);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+"輸入的值請勿空白!!");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/gpcls/page_update_gpcls.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getGps_FromGpclsno_ForGpcls".equals(action) || "getGps_FromGpclsno_ForGp".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			if(req.getParameter("whichPage")==null){
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer gpclsno = new Integer(req.getParameter("gpclsno"));

				/*************************** 2.開始查詢資料 ****************************************/
				GpService gpSvc = new GpService();
				List<GpVO> list = gpSvc.getGpsBygpclsno(gpclsno);
				if (list.isEmpty()) {
					errorMsgs.add("查無資料");
					req.setAttribute("gpclsno", gpclsno);
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					if ("getGps_FromGpclsno_ForGp".equals(action)){					
						RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gp/page_select_gp.jsp");
						failureView.forward(req, res);
					}
					else if("getGps_FromGpclsno_ForGpcls".equals(action)){					
						RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gpcls/page_listAllGpcls.jsp");
						failureView.forward(req, res);
					}
					return;//程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGps_Bygpclsno", list);    // 資料庫取出的list物件,存入request
				req.setAttribute("gpclsno", gpclsno);
				String url = null;
				if ("getGps_FromGpclsno_ForGpcls".equals(action))
					url = "/back/gpcls/page_listAllGpcls.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
				else if ("getGps_FromGpclsno_ForGp".equals(action))
					url = "/back/gp/page_listgps_fromgpclsno.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
			}else{
					/*************************** 1.接收請求參數 ****************************************/
					Integer gpclsno = new Integer(req.getParameter("gpclsno"));

					/*************************** 2.開始查詢資料 ****************************************/
					GpService gpSvc = new GpService();
					List<GpVO> list = gpSvc.getGpsBygpclsno(gpclsno);
					
					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("listGps_Bygpclsno", list);    // 資料庫取出的list物件,存入request
					req.setAttribute("gpclsno", gpclsno);
					String url = null;
					if ("getGps_FromGpclsno_ForGpcls".equals(action))
						url = "/back/gpcls/page_listAllGpcls.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
					else if ("getGps_FromGpclsno_ForGp".equals(action))
						url = "/back/gp/page_listgps_fromgpclsno.jsp";              // 成功轉交 dept/listAllDept.jsp

					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				
			}
		}


		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer gpclsno = new Integer(req.getParameter("gpclsno"));
				
				/***************************2.開始刪除資料***************************************/
				GpclsService gpclsSvc = new GpclsService();
				gpclsSvc.delete(gpclsno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				//req.setAttribute("listgpcls",gpclsSvc.getAll()); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+"此分類尚有關聯於揪團，請修改揪團分類後再刪除!!");
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}

}
