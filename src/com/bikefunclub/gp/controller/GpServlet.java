package com.bikefunclub.gp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.bikefunclub.memgp.model.MemgpService;
import com.bikefunclub.memgp.model.MemgpVO;
import com.bikefunclub.rot.model.RotService;
import com.bikefunclub.rot.model.RotVO;

@WebServlet("/Gp.do")
public class GpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GpServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String action = req.getParameter("action");
		GpService gpSvc = new GpService();
		// 設定日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ("getGp_info".equals(action)) {//來自後台page_select_gp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				Integer gpno = new Integer(req.getParameter("gpno"));

				/*************************** 2.開始查詢資料 ****************************************/
				GpVO gpVO = gpSvc.findByPrimaryKey(gpno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("gpVO", gpVO); // 資料庫取出的list物件,存入request

				String url = "/back/gp/page_gp_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/gp/page_select_gp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_fromgpno".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String str = req.getParameter("gpno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gp/page_select_gp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer gpno = null;
				try {
					gpno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("揪團編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gp/page_select_gp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 ****************************************/
				GpVO gpVO = gpSvc.findByPrimaryKey(gpno);
				if (gpVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gp/page_select_gp.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("gpVO", gpVO); // 資料庫取出的list物件,存入request

				String url = "/back/gp/page_gp_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/gp/page_select_gp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listGps_frommemno".equals(action)) {

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
								.getRequestDispatcher("/back/gp/page_select_gp.jsp");
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
								.getRequestDispatcher("/back/gp/page_select_gp.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}

					/*************************** 2.開始查詢資料 ****************************************/
					List<GpVO> list = gpSvc.getGpsBymemno(memno);
					if (list.isEmpty()) {
						errorMsgs.add("查無資料");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back/gp/page_select_gp.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("listGps_frommemno", list); // 資料庫取出的list物件,存入request
					req.setAttribute("memno", memno);
					String url = "/back/gp/page_listgps_frommemno.jsp";
					RequestDispatcher successView = req
							.getRequestDispatcher(url);
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 ***********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/gp/page_select_gp.jsp");
					failureView.forward(req, res);
				}
			} else {
				/*************************** 1.接收請求參數 ****************************************/
				Integer memno = new Integer(req.getParameter("memno"));

				/*************************** 2.開始查詢資料 ****************************************/
				List<GpVO> list = gpSvc.getGpsBymemno(memno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listGps_frommemno", list); // 資料庫取出的list物件,存入request
				req.setAttribute("memno", memno);
				String url = "/back/gp/page_listgps_frommemno.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		if ("delete_fromgpclsno".equals(action)
				|| "delete_frommemno".equals(action)
				|| "delete_fromrep".equals(action)
				|| "delete_launchgp".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑:

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer gpno = new Integer(req.getParameter("gpno"));

				/*************************** 2.開始刪除資料 ***************************************/
				GpVO gpVO = gpSvc.findByPrimaryKey(gpno);
				gpSvc.delete(gpno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = null;
				if ("delete_fromgpclsno".equals(action)) {
					List<GpVO> list = gpSvc.getGpsBygpclsno(gpVO.getGpclsno());
					req.setAttribute("listGps_Bygpclsno", list); // 資料庫取出的list物件,存入request
					req.setAttribute("gpclsno", gpVO.getGpclsno());
					url = requestURL;
				} else if ("delete_frommemno".equals(action)) {
					List<GpVO> list = gpSvc.getGpsBymemno(gpVO.getMemno());
					req.setAttribute("listGps_frommemno", list); // 資料庫取出的list物件,存入request
					req.setAttribute("memno", gpVO.getMemno());
					url = requestURL;
				} else if ("delete_fromrep".equals(action)) {
					url = requestURL;
				} else if ("delete_launchgp".equals(action)) {
					url = requestURL;
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
		if ("launchgp".equals(action)) {// 發起揪團，來自page_insert_gp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數 **/
			try {
				Integer memno = new Integer(req.getParameter("memno"));
				Integer gpclsno = new Integer(req.getParameter("gpclsno"));
				String gptitle = req.getParameter("gptitle").trim();
				String gpdesc = req.getParameter("gpdesc").trim();
				String gpnote = req.getParameter("gpnote").trim();

				// 揪團建立時間
				Date date = new Date(System.currentTimeMillis());

				Timestamp gpbuilddate = Timestamp.valueOf(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(date));
				// 報名開始時間
				Timestamp joinstartdate = null;
				String joinstart_str = req.getParameter("joinstartdatetime");
				if (joinstart_str.length() == 11) {
					joinstart_str = joinstart_str + "00:00:00";
				}
				Date joinstart_date = sdf.parse(joinstart_str);

				if (!joinstart_str.isEmpty()) {
					joinstartdate = new Timestamp(joinstart_date.getTime());
				}
				// 報名結束時間
				Timestamp joinenddate = null;
				String joinend_str = req.getParameter("joinenddatetime");
				if (joinend_str.length() == 11) {
					joinend_str = joinend_str + "00:00:00";
				}
				Date joinend_date = sdf.parse(joinend_str);
				if (!joinend_str.isEmpty()) {
					joinenddate = new Timestamp(joinend_date.getTime());
				}
				// 揪團開始時間
				Timestamp gpstartdate = null;
				String gpstart_str = req.getParameter("gpstartdatetime");
				if (gpstart_str.length() == 11) {
					gpstart_str = gpstart_str + "00:00:00";
				}
				Date gpstart_date = sdf.parse(gpstart_str);
				if (!gpstart_str.isEmpty()) {
					gpstartdate = new Timestamp(gpstart_date.getTime());
				}
				// 揪團結束時間
				Timestamp gpenddate = null;
				String gpend_str = req.getParameter("gpenddatetime");
				if (gpend_str.length() == 11) {
					gpend_str = gpend_str + "00:00:00";
				}
				Date gpend_date = sdf.parse(gpend_str);
				if (!gpend_str.isEmpty()) {
					gpenddate = new Timestamp(gpend_date.getTime());
				}
				// 人數上限預設15人
				Integer gpmaxnum = 15;
				if (!req.getParameter("gpmaxnum").isEmpty()) {
					gpmaxnum = new Integer(req.getParameter("gpmaxnum"));
				}
				// 路線編號
				Integer rotno = new Integer(req.getParameter("rotno"));
				// 揪團權限狀態
				String gpauth = req.getParameter("gpauth");

				/** 2 輸入格式的錯誤處理 **/

				if (gptitle.isEmpty()) {
					errorMsgs.add("請輸入揪團標題!");
				}
				if (gpdesc.isEmpty()) {
					errorMsgs.add("請輸入揪團描述!");
				}
				if (joinstart_str.isEmpty()) {
					errorMsgs.add("請輸入報名開始時間!");
				}
				if (joinend_str.isEmpty()) {
					errorMsgs.add("請輸入報名結束時間!");
				}
				if (gpstart_str.isEmpty()) {
					errorMsgs.add("請輸入揪團開始時間!");
				}
				if (gpend_str.isEmpty()) {
					errorMsgs.add("請輸入揪團結束時間!");
				}
				// Str1.compareTo(Str2); Str1按字典顺序小于参数字符串Str2，则返回值小于0
				if (gpstart_str.compareTo(joinend_str) < 0) {
					errorMsgs.add("揪團開始時間需在報名結束時間之後!");
				}
				// 用途:輸入格式錯誤，仍保留之前輸入的值 and存進vo給service insert method使用
				GpVO gpVO = new GpVO();
				gpVO.setMemno(memno);
				gpVO.setGpclsno(gpclsno);								
				gpVO.setGptitle(gptitle);
				gpVO.setGpdesc(gpdesc);

				RotVO rotVO = new RotVO();
				rotVO.setRotno(rotno);
				RotService rotSvc = new RotService();
				rotVO = rotSvc.findByPrimaryKey(rotno);

				rotVO.setRotname(rotVO.getRotname());

				gpVO.setGpnote(gpnote);
				gpVO.setGpbuilddate(gpbuilddate);
				gpVO.setJoinstartdate(joinstartdate);
				gpVO.setJoinenddate(joinenddate);
				gpVO.setGpstartdate(gpstartdate);
				gpVO.setGpenddate(gpenddate);
				gpVO.setGpmaxnum(gpmaxnum);
				gpVO.setRotno(rotno);
				gpVO.setGpauth(gpauth);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rotVO", rotVO);// 含有輸入格式錯誤的rotVO物件,也存入req
					req.setAttribute("gpVO", gpVO); // 含有輸入格式錯誤的gpVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/gp/page_insert_gp.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 3.開始新增資料 *****************************************/
				gpVO = gpSvc.insert(gpVO);
				//揪團發起人也需寫入到揪團參加人資料表			
				MemgpVO memgpVO = new MemgpVO(); 
				memgpVO.setMemno(memno);
				memgpVO.setGpno(gpVO.getGpno());
				memgpVO.setAdddate(gpbuilddate);
				
				MemgpService memgpSvc = new MemgpService();
				memgpSvc.insert(memgpVO);
				/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front/gp/page_manage_gp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/** 其他可能的錯誤處理 **/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/gp/page_insert_gp.jsp");
				failureView.forward(req, res);
			}

		}
		if ("launchgp_info".equals(action)) {// 發起揪團詳細資料,來自launchgp_info.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				Integer gpno = new Integer(req.getParameter("gpno"));
				/*************************** 2.開始查詢資料 ****************************************/
				GpVO gpVO = gpSvc.findByPrimaryKey(gpno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("gpVO", gpVO); // 資料庫取出的list物件,存入request

				String url = "/front/gp/page_launchgp_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/gp/page_manage_gp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("gpdetail".equals(action)) {// 瀏覽揪團詳細資料,來自page_listAllgp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/

				Integer gpno = new Integer(req.getParameter("gpno"));
				/*************************** 2.開始查詢資料 ****************************************/
				GpVO gpVO = gpSvc.findByPrimaryKey(gpno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("gpVO", gpVO); // 資料庫取出的list物件,存入request

				String url = "/front/gp/page_joingp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/gp/page_listAllgp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_for_updatelaunchgp".equals(action)) {// 跳至發起揪團修改頁面,來自manage_gp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:

			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁,
			try {
				/** 1.接收請求參數 */
				Integer gpno = new Integer(req.getParameter("gpno"));

				/** 2.開始查詢資料 */
				GpVO gpVO = gpSvc.findByPrimaryKey(gpno);

				/** 3.查詢完成,準備轉交(Send the Success view) */
				req.setAttribute("gpVO", gpVO); // 資料庫取出的gpVO物件,存入req
				String url = "/front/gp/page_update_gp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交page_update_gp_input.jsp
				successView.forward(req, res);

				/** 錯誤處理,轉回來源網頁路徑 */
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("update_launchgp".equals(action)) {// 修改揪團詳細資料,來自update_gp_input.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			/** 1.接收請求參數 **/
			try {
				Integer gpno = new Integer(req.getParameter("gpno"));
				Integer memno = new Integer(req.getParameter("memno"));
				Integer gpclsno = new Integer(req.getParameter("gpclsno"));
				String gptitle = req.getParameter("gptitle").trim();
				String gpdesc = req.getParameter("gpdesc").trim();
				String gpnote = req.getParameter("gpnote").trim();

				// 報名開始時間
				Timestamp joinstartdate = null;
				String joinstart_str = req.getParameter("joinstartdatetime");
				if (joinstart_str.length() == 11) {
					joinstart_str = joinstart_str + "00:00:00";
				}
				Date joinstart_date = sdf.parse(joinstart_str);

				if (!joinstart_str.isEmpty()) {
					joinstartdate = new Timestamp(joinstart_date.getTime());
				}
				// 報名結束時間
				Timestamp joinenddate = null;
				String joinend_str = req.getParameter("joinenddatetime");
				if (joinend_str.length() == 11) {
					joinend_str = joinend_str + "00:00:00";
				}
				Date joinend_date = sdf.parse(joinend_str);
				if (!joinend_str.isEmpty()) {
					joinenddate = new Timestamp(joinend_date.getTime());
				}
				// 揪團開始時間
				Timestamp gpstartdate = null;
				String gpstart_str = req.getParameter("gpstartdatetime");
				if (gpstart_str.length() == 11) {
					gpstart_str = gpstart_str + "00:00:00";
				}
				Date gpstart_date = sdf.parse(gpstart_str);
				if (!gpstart_str.isEmpty()) {
					gpstartdate = new Timestamp(gpstart_date.getTime());
				}
				// 揪團結束時間
				Timestamp gpenddate = null;
				String gpend_str = req.getParameter("gpenddatetime");
				if (gpend_str.length() == 11) {
					gpend_str = gpend_str + "00:00:00";
				}
				Date gpend_date = sdf.parse(gpend_str);
				if (!gpend_str.isEmpty()) {
					gpenddate = new Timestamp(gpend_date.getTime());
				}
				// 人數上限預設15人
				Integer gpmaxnum = 15;
				if (!req.getParameter("gpmaxnum").isEmpty()) {
					gpmaxnum = new Integer(req.getParameter("gpmaxnum"));
				}
				// 路線編號
				Integer rotno = new Integer(req.getParameter("rotno"));
				// 揪團權限狀態
				String gpauth = req.getParameter("gpauth");

				/** 2 輸入格式的錯誤處理 **/

				if (gptitle.isEmpty()) {
					errorMsgs.add("請輸入揪團標題!");
				}
				if (gpdesc.isEmpty()) {
					errorMsgs.add("請輸入揪團描述!");
				}
				if (joinstart_str.isEmpty()) {
					errorMsgs.add("請輸入報名開始時間!");
				}
				if (joinend_str.isEmpty()) {
					errorMsgs.add("請輸入報名結束時間!");
				}
				if (gpstart_str.isEmpty()) {
					errorMsgs.add("請輸入揪團開始時間!");
				}
				if (gpend_str.isEmpty()) {
					errorMsgs.add("請輸入揪團結束時間!");
				}
				// Str1.compareTo(Str2); Str1按字典顺序小于参数字符串Str2，则返回值小于0
				if (gpstart_str.compareTo(joinend_str) < 0) {
					errorMsgs.add("揪團開始時間需在報名結束時間之後!");
				}
				// 用途:輸入格式錯誤，仍保留之前輸入的值 and存進vo給service update method使用
				GpVO gpVO = new GpVO();
				gpVO.setGpno(gpno);
				gpVO.setMemno(memno);
				gpVO.setGpclsno(gpclsno);
				gpVO.setGptitle(gptitle);
				gpVO.setGpdesc(gpdesc);
				gpVO.setGpnote(gpnote);
				gpVO.setJoinstartdate(joinstartdate);
				gpVO.setJoinenddate(joinenddate);
				gpVO.setGpstartdate(gpstartdate);
				gpVO.setGpenddate(gpenddate);
				gpVO.setGpmaxnum(gpmaxnum);
				gpVO.setRotno(rotno);
				gpVO.setGpauth(gpauth);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gpVO", gpVO); // 含有輸入格式錯誤的gpVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/gp/page_update_gp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 3.開始修改資料 *****************************************/
				gpVO = gpSvc.update(gpVO);
				/*************************** 4.修改完成,準備轉交(Send the Success view) ***********/
				String url = requestURL + "?whichPage=" + whichPage + "&gpno="
						+ gpno;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/** 其他可能的錯誤處理 **/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/gp/page_update_gp_input.jsp");
				failureView.forward(req, res);
			}

		}
		if ("joingp".equals(action)) {//參加揪團,來自joingp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/** 1.接收請求參數 **/
				Integer gpno = new Integer(req.getParameter("gpno"));
				Integer memno = new Integer(req.getParameter("loginmemno"));				
				Integer gpclsno = new Integer(req.getParameter("gpclsno"));
				String gptitle = req.getParameter("gptitle").trim();
				String gpdesc = req.getParameter("gpdesc").trim();
				String gpnote = req.getParameter("gpnote").trim();
				//揪團發起時間
				String gpbuilddate_str = req.getParameter("gpbuilddate").trim();
				
				Timestamp gpbuilddate = Timestamp.valueOf(gpbuilddate_str);	
				
				// 會員報名時間
				Date date = new Date(System.currentTimeMillis());

				Timestamp adddate = Timestamp.valueOf(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(date));
				// 報名開始時間
				Timestamp joinstartdate = null;
				String joinstart_str = req.getParameter("joinstartdatetime");
				if (joinstart_str.length() == 11) {
					joinstart_str = joinstart_str + "00:00:00";
				}
				Date joinstart_date = sdf.parse(joinstart_str);

				if (!joinstart_str.isEmpty()) {
					joinstartdate = new Timestamp(joinstart_date.getTime());
				}
				// 報名結束時間
				Timestamp joinenddate = null;
				String joinend_str = req.getParameter("joinenddatetime");
				if (joinend_str.length() == 11) {
					joinend_str = joinend_str + "00:00:00";
				}
				Date joinend_date = sdf.parse(joinend_str);
				if (!joinend_str.isEmpty()) {
					joinenddate = new Timestamp(joinend_date.getTime());
				}
				// 揪團開始時間
				Timestamp gpstartdate = null;
				String gpstart_str = req.getParameter("gpstartdatetime");
				Date gpstart_date = sdf.parse(gpstart_str);
				if (!gpstart_str.isEmpty()) {
					gpstartdate = new Timestamp(gpstart_date.getTime());
				}
				Integer gpmaxnum = 15;
				if (!req.getParameter("gpmaxnum").isEmpty()) {
					 gpmaxnum = new Integer(req.getParameter("gpmaxnum"));
				}
				// 路線編號
				Integer rotno = new Integer(req.getParameter("rotno"));

				//參加會員
				String[] attendmemnos = req.getParameterValues("attendmemno");
				
				/** 2 錯誤處理 **/
				/**已報名揪團的會員不能再報名**/
				for (int i = 0; i < attendmemnos.length; i++) {
					if (memno.toString().equals(attendmemnos[i])) {
						errorMsgs.add("你已經報名!!");
					}
				}

				/**參加要在報名期間**/
				if(adddate.after(joinenddate)){
					errorMsgs.add("報名已結束!!");
				}
				if(adddate.before(joinstartdate)){
					errorMsgs.add("報名未開始!!");
				}	
				
				// 用途:輸入格式錯誤，仍保留之前的值
				GpVO gpVO = new GpVO();
				gpVO.setGpno(gpno);
				gpVO.setMemno(memno);
				gpVO.setGpclsno(gpclsno);
				gpVO.setGptitle(gptitle);
				gpVO.setGpdesc(gpdesc);
				gpVO.setGpnote(gpnote);
				gpVO.setGpbuilddate(gpbuilddate);
				gpVO.setJoinstartdate(joinstartdate);
				gpVO.setJoinenddate(joinenddate);
				gpVO.setGpstartdate(gpstartdate);
				gpVO.setGpmaxnum(gpmaxnum);
				gpVO.setRotno(rotno);
				
				// Send the use back to the form, if there were errors								
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("gpVO", gpVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/gp/page_joingp.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 3.開始新增資料 *****************************************/			
				MemgpVO memgpVO = new MemgpVO();
				memgpVO.setGpno(gpno);
				memgpVO.setMemno(memno);
				memgpVO.setAdddate(adddate);
				
				MemgpService memSvc = new MemgpService();
				memSvc.insert(memgpVO);
				
				/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/				
				String url = "/front/gp/page_manage_gp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/gp/page_joingp.jsp");
				failureView.forward(req, res);
			}
		}
		if ("leavegp".equals(action)) {//退出揪團,來自manage_gp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出退出團的來源網頁路徑:
			
			try {
				/** 1.接收請求參數 **/
				Integer gpno = new Integer(req.getParameter("gpno"));
				Integer memno = new Integer(req.getParameter("loginmemno"));
				
				/** 2.開始刪除memgp資料 **/
				MemgpService memSvc = new MemgpService();
				memSvc.delete(memno, gpno);
				
				req.setAttribute("tabNum", req.getParameter("tabNum"));
				/*************************** 4.刪除完成,準備轉交(Send the Success view) ***********/				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
