package com.bikefunclub.rot.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.bikefunclub.memrot.model.*;
import com.bikefunclub.rot.model.RotService;
import com.bikefunclub.rot.model.RotVO;

/**
 * Servlet implementation class RotServlet
 */
@WebServlet("/Rot.do")
public class RotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RotServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		MemrotService memrotSvc = new MemrotService();
		RotService rotSvc = new RotService();


		if ("getRots_FromRotclsno_ForRot".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			if(req.getParameter("whichPage")==null){
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer rotclsno = new Integer(req.getParameter("rotclsno"));

				/*************************** 2.開始查詢資料 ****************************************/
				List<RotVO> list = rotSvc.getRotsByrotclsnofromback(rotclsno);
				if (list.isEmpty()) {
					errorMsgs.add("查無資料");
					req.setAttribute("rotclsno", rotclsno);
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {			
						RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
						failureView.forward(req, res);
				    	return;//程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listRots_Byrotclsno", list);    // 資料庫取出的list物件,存入request
				req.setAttribute("rotclsno", rotclsno);
				String url = null;
				url = "/back/rot/page_listmemrots_fromrotclsno.jsp";              // 成功轉交 dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
			}else{
					/*************************** 1.接收請求參數 ****************************************/
					Integer rotclsno = new Integer(req.getParameter("rotclsno"));

					/*************************** 2.開始查詢資料 ****************************************/
					List<RotVO> list = rotSvc.getRotsByrotclsnofromback(rotclsno);
					
					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("listRots_Byrotclsno", list);    // 資料庫取出的list物件,存入request
					req.setAttribute("rotclsno", rotclsno);
					String url = null;
					url = "/back/rot/page_listmemrots_fromrotclsno.jsp";        

					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				
			}		
		}
		if ("listRots_frommemno".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			if (req.getParameter("whichPage") == null) {
				try {
					/*************************** 1.接收請求參數 ****************************************/
					String str = req.getParameter("memno");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入會員編號");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					Integer memno = null;
					try {
						memno = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("會員編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 2.開始查詢資料 ****************************************/
					List<RotVO> list = rotSvc.getRotsBymemno(memno);
					if (list.isEmpty()) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("listRots_frommemno", list); // 資料庫取出的list物件,存入request
					req.setAttribute("memno", memno);
					String url = "/back/rot/page_listmemrots_frommemno.jsp";
					RequestDispatcher successView = req
							.getRequestDispatcher(url);
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 ***********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
					failureView.forward(req, res);
				}
			} else {
				/*************************** 1.接收請求參數 ****************************************/
				Integer memno = new Integer(req.getParameter("memno"));

				/*************************** 2.開始查詢資料 ****************************************/
				List<RotVO> list = rotSvc.getRotsBymemno(memno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listRots_frommemno", list); // 資料庫取出的list物件,存入request
				req.setAttribute("memno", memno);
				String url = "/back/rot/page_listmemrots_frommemno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}		
		

		if ("delete_frommemno".equals(action)
				|| "delete_fromrotclsno".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑:
																// 可能為【/emp/listAllEmp.jsp】
																// 或
																// 【/dept/listEmps_ByDeptno.jsp】
																// 或 【
																// /dept/listAllDept.jsp】

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer rotno = new Integer(req.getParameter("rotno"));

				/*************************** 2.開始刪除資料 ***************************************/
			    RotVO rotVO= rotSvc.findByPrimaryKey(rotno);
			    rotSvc.delete_ByMem(rotno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = null;
				if ("delete_fromrotclsno".equals(action)) {
					List<RotVO> list = rotSvc.getRotsByrotclsnofromback(rotVO.getRotclsno());
					if (list.isEmpty()) {
						errorMsgs.add("該分類已無資料");
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					}else{
					req.setAttribute("listRots_Byrotclsno", list); // 資料庫取出的list物件,存入request
					req.setAttribute("rotclsno", rotVO.getRotclsno());
					url = requestURL;
					}
				} else if ("delete_frommemno".equals(action)) {
					List<RotVO> list = rotSvc.getRotsBymemno(rotVO.getMemno());
					if (list.isEmpty()) {
						errorMsgs.add("該會員已無資料");
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					}else{
					req.setAttribute("listRots_frommemno", list); // 資料庫取出的list物件,存入request
					req.setAttribute("memno", rotVO.getMemno());
					url = requestURL;
					}
				}
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

			if ("delete_fromsg".equals(action)) {

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				
				String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer rotno = new Integer(req.getParameter("rotno"));
					
					/***************************2.開始刪除資料***************************************/
					rotSvc.delete(rotno);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/
					//req.setAttribute("list",rotSvc.getAllSg()); // 資料庫取出的list物件,存入request
					
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add("該路線尚有揪團參考!");
					System.out.println(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
		
		if ("delete_fromrotsmanage_bymemno".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); 

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer rotno = new Integer(req.getParameter("rotno"));

				/*************************** 2.開始刪除資料 ***************************************/

				rotSvc.delete_ByMem(rotno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = null;
                url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("delete_fromrotmanage_bymemrot".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer rotno = new Integer(req.getParameter("rotno"));
				Integer memno = new Integer(req.getParameter("memno"));

				/*************************** 2.開始刪除資料 ***************************************/
				memrotSvc.delete(memno, rotno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = null;
				url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
        if ("add_memrot".equals(action)) { 

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				Integer memno = new Integer(req.getParameter("memno"));				
				Integer rotno = new Integer(req.getParameter("rotno"));		
				
				MemrotVO memrotVO = new MemrotVO();	
				Date date = new Date(System.currentTimeMillis());
				memrotVO.setRotno(rotno);
				memrotVO.setMemno(memno);
				memrotVO.setClttime(Timestamp.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)));

			
				/***************************2.開始新增資料***************************************/

				memrotVO = memrotSvc.insert(memrotVO);

				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/rot/page_rots_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
		}
		
		
        if ("insert_sg".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String rotname = req.getParameter("rotname");
				
				if (rotname == null || (rotname.trim()).length() == 0) {
					errorMsgs.add("請輸入路線名稱");
				}				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rot/page_insert_sgrot_2.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
						
				
				Integer rotclsno = new Integer(req.getParameter("rotclsno"));
				String rotstart = req.getParameter("rotstart");
				String rotend = req.getParameter("rotend");
				String rotloc = req.getParameter("rotloc").trim();
				String rottag = req.getParameter("rottag").trim();
				Integer memno = new Integer("1");
				String rotauth = req.getParameter("rotauth").trim();
				String rotdesc = req.getParameter("rotdesc").trim();
				
				
				RotVO rotVO = new RotVO();	
				rotVO.setRotclsno(rotclsno);
				rotVO.setRotname(rotname);
				rotVO.setRotstart(rotstart);
				rotVO.setRotend(rotend);
				rotVO.setRotloc(rotloc);
				rotVO.setRottag(rottag);
				rotVO.setMemno(memno);
				rotVO.setRotstatus("1");
				rotVO.setRotauth(rotauth);
				rotVO.setRotdesc(rotdesc);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rotVO", rotVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rot/page_insert_sgrot_2.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/

				rotVO = rotSvc.insert(rotVO);
			    rotSvc.delete_Mem(rotVO.getRotno());
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back/rot/page_rot_info.jsp";
				req.setAttribute("rotVO", rotVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rot/page_insert_sgrot_2.jsp");
				failureView.forward(req, res);
			}
		}			
		
        if ("insert_mem".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String rotname = req.getParameter("rotname");
				
				if (rotname == null || (rotname.trim()).length() == 0) {
					errorMsgs.add("請輸入路線名稱");
				}				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/rot/page_insert_memrot_2.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
						
				
				Integer rotclsno = new Integer(req.getParameter("rotclsno"));
				String rotstart = req.getParameter("rotstart");
				String rotend = req.getParameter("rotend");
				String rotloc = req.getParameter("rotloc").trim();
				String rottag = req.getParameter("rottag").trim();
				Integer memno = new Integer(req.getParameter("memno"));
				String rotauth = req.getParameter("rotauth").trim();
				String rotdesc = req.getParameter("rotdesc").trim();
				
				
				RotVO rotVO = new RotVO();	
				rotVO.setRotclsno(rotclsno);
				rotVO.setRotname(rotname);
				rotVO.setRotstart(rotstart);
				rotVO.setRotend(rotend);
				rotVO.setRotloc(rotloc);
				rotVO.setRottag(rottag);
				rotVO.setMemno(memno);
				rotVO.setRotstatus("1");
				rotVO.setRotauth(rotauth);
				rotVO.setRotdesc(rotdesc);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rotVO", rotVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/rot/page_insert_memrot_2.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/

				rotVO = rotSvc.insert(rotVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/rot/page_rot_info.jsp";
				req.setAttribute("rotVO", rotVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/rot/page_insert_memrot_2.jsp");
				failureView.forward(req, res);
			}
		}
        
		if ("getOne_fromrotno".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String str = req.getParameter("rotno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入路線編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer rotno = null;
				try {
					rotno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("路線編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 ****************************************/
				RotVO rotVO = rotSvc.findByPrimaryKey(rotno);
				if (rotVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rotVO", rotVO); // 資料庫取出的list物件,存入request
				req.setAttribute("rotno", rotVO.getRotno());
				String url = "/back/rot/page_rot_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/rot/page_select_memrot.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if (("getRot_info".equals(action))||("back_getRot_info".equals(action))) {

				/*************************** 1.接收請求參數 ****************************************/	
					
					Integer	rotno = new Integer(req.getParameter("rotno"));
				
				/*************************** 2.開始查詢資料 ****************************************/
					RotVO rotVO = rotSvc.findByPrimaryKey(rotno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rotVO", rotVO);    // 資料庫取出的list物件,存入request
				req.setAttribute("rotno", rotVO.getRotno());
				String url="";
				if("getRot_info".equals(action)){
					 url = "/front/rot/page_rot_info.jsp";
				}else if("back_getRot_info".equals(action)){
					 url = "/back/rot/page_rot_info.jsp";	
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}

		if ("getSelected_rotinfo".equals(action)||"back_getSelected_rotinfo".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/

			Integer rotno = new Integer(req.getParameter("rotno"));

			/*************************** 2.開始查詢資料 ****************************************/
			RotVO rotVO = rotSvc.findByPrimaryKey(rotno);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			JSONObject obj = new JSONObject();
			obj.put("rotno", rotVO.getRotno());
			obj.put("rotclsno", rotVO.getRotclsno());
			obj.put("rotname", rotVO.getRotname());
			obj.put("rotstart", rotVO.getRotstart());
			obj.put("rotend", rotVO.getRotend());
			obj.put("rotloc", rotVO.getRotloc());
			obj.put("rottag", rotVO.getRottag());
			obj.put("memno", rotVO.getMemno());
			obj.put("rotstatus", rotVO.getRotstatus());
			obj.put("rotauth", rotVO.getRotauth());
			obj.put("rotdesc", rotVO.getRotdesc());

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			//System.out.println(obj.toString());
			out.write(obj.toString());
			out.flush();
			
			out.close();

		}
		if ("getSelected_rots".equals(action)) {
			JSONArray array = new JSONArray();
			/*************************** 1.接收請求參數 ****************************************/

			Integer rotclsno = new Integer(req.getParameter("rotclsno"));

			/*************************** 2.開始查詢資料 ****************************************/
			List<RotVO> list = new LinkedList<RotVO>();
			if (0 == rotclsno) {
				list = rotSvc.getAll();
			} else {
				list = rotSvc.getRotsByrotclsno(rotclsno);
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			for (RotVO Rot : list) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("rotno", Rot.getRotno());
					obj.put("rotclsno", Rot.getRotclsno());
					obj.put("rotname", Rot.getRotname());
					obj.put("rotstart", Rot.getRotstart());
					obj.put("rotend", Rot.getRotend());
					obj.put("rotloc", Rot.getRotloc());
					obj.put("rottag", Rot.getRottag());
					obj.put("memno", Rot.getMemno());
					obj.put("rotstatus", Rot.getRotstatus());
					obj.put("rotauth", Rot.getRotauth());
					obj.put("rotdesc", Rot.getRotdesc());
				} catch (Exception e) {
				}
				array.add(obj);
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
	}
}
