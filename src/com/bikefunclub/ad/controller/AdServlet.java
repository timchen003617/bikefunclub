package com.bikefunclub.ad.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;/**支援圖片上傳*/
import com.bikefunclub.ad.model.*;

@WebServlet("/AdServlet")
public class AdServlet extends HttpServlet {

	private static final long serialVersionUID = 6706563385877341490L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String action = null;
		MultipartRequest multi = null;
		// instance AdService
		AdService adSvc = new AdService();
		
		String contentType = req.getContentType();
		System.out.println(contentType);
		// 判斷request的contentType
		if (contentType != null&&contentType.startsWith("multipart/form-data")) {
			/** 建立MultipartRequest實體 */
			multi = new MultipartRequest(req, getServletContext().getRealPath(
					"img"), 5 * 1024 * 1024, "UTF-8");
			action = multi.getParameter("action");
		} else {
			action = req.getParameter("action");
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllAd.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 為【/ad/listAllAd.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁,
														// 存入req(只用於:istAllEmp.jsp)
			try {
				/** 1.接收請求參數 */
				Integer adno = new Integer(req.getParameter("adno"));

				/** 2.開始查詢資料 */
				AdVO adVO = adSvc.getOneAd(adno);

				/** 3.查詢完成,準備轉交(Send the Success view) */
				req.setAttribute("adVO", adVO); // 資料庫取出的adVO物件,存入req
				String url = "/back/ad/page_update_ad_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交page_update_ad_input.jsp
				successView.forward(req, res);

				/** 錯誤處理,轉回來源網頁路徑 */
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {// 來自update_ad_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = multi.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL);

			String whichPage = multi.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);

			/** 1.接收請求參數 ,上傳檔案、 輸入格式的錯誤處理 */
			try {
				Integer adno = new Integer(multi.getParameter("adno"));
				String adname = multi.getParameter("adname").trim();
				String adurl = multi.getParameter("adurl").trim();
				String adesc = multi.getParameter("adesc").trim();

				InputStream in = null;
				byte[] adfile = null;
				String filename = "";
				String extname = "";

				AdVO adVO = upload(multi, adSvc, adno, adname, adurl, adesc,
						in, adfile, filename, extname);

				adfile = adVO.getAdfile();
				filename = adVO.getFilename();
				extname = adVO.getExtname();

				if (adname.isEmpty()) {
					errorMsgs.add("請輸入廣告標題");
				}
				if (adfile == null) {
					errorMsgs.add("請選擇檔案!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/ad/page_update_ad_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/

				adVO = adSvc.updateAd(adno, adname, adesc, adfile, filename,
						extname, adurl);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = requestURL + "?whichPage=" + whichPage + "&adno="
						+ adno;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交送出修改的來源網頁路徑
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ad/page_update_ad_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addAD.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer adno = 0;// insert時，adno給0,因編號是由sequence產生
				String adname = multi.getParameter("adname").trim();
				String adurl = multi.getParameter("adurl").trim();
				String adesc = multi.getParameter("adesc").trim();

				InputStream in = null;
				byte[] adfile = null;
				String filename = "";
				String extname = "";

				AdVO adVO = upload(multi, adSvc, adno, adname, adurl, adesc,
						in, adfile, filename, extname);
				adfile = adVO.getAdfile();
				filename = adVO.getFilename();
				extname = adVO.getExtname();

				if (adname.isEmpty()) {
					errorMsgs.add("請輸入廣告標題!");
				}
				if (adurl.isEmpty()) {
					errorMsgs.add("請輸入廣告連結!");
				}
				if (adfile == null) {
					errorMsgs.add("請選擇檔案!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/ad/page_add_ad_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 *****************************************/
				adVO = adSvc.addAd(adname, adesc, adfile, filename, extname,
						adurl);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back/ad/page_listAllAd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交page_listAllAd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/ad/page_add_ad_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllAd.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑:
																// 可能為【/add/listAllAd.jsp】
			String whichPage = req.getParameter("whichPage"); // 送出刪除的來源網頁的第幾頁(只用於:listAllAd.jsp)
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer adno = new Integer(req.getParameter("adno"));

				/*************************** 2.開始刪除資料 ***************************************/
				adSvc.deleteAd(adno);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = requestURL + "?whichPage=" + whichPage; // 送出刪除的來源網頁的第幾頁(只用於:listAllAd.jsp)
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

	}

	/** 上傳圖片至資料夾、資料庫、存AdVO */
	public AdVO upload(MultipartRequest multi, AdService adSvc, Integer adno,
			String adname, String adurl, String adesc, InputStream in,
			byte[] adfile, String filename, String extname) throws IOException {
		AdVO adVO = new AdVO();
		/** 取得所有上傳檔案之輸入型態、名稱 */
		Enumeration<?> files = multi.getFileNames();// File物件 <input type="file"
													// name="key" value="取這裡">

		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();// 上一頁的upfile
			String filenameall = multi.getFilesystemName(name);// 真正檔案名稱
			File f = multi.getFile(name);

			if (f != null) {
				try {
					/** 檔案名稱拆成檔名副檔名 */
					int dotPos = filenameall.indexOf('.');
					filename = filenameall.substring(0, dotPos);
					extname = filenameall.substring(dotPos + 1);

					// 將file轉成InputStream
					in = new FileInputStream(f);
					// InputStream轉型byte array
					int picsize = in.available();
					adfile = new byte[picsize];
					in.read(adfile);

					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {// f == null
				if (adno != 0) {// update時使用
					adVO = adSvc.getOneAd(adno);

					adno = adVO.getAdno();
					adname = adVO.getAdname();
					adurl = adVO.getAdurl();
					adesc = adVO.getAdesc();
					adfile = adVO.getAdfile();
					filename = adVO.getFilename();
					extname = adVO.getExtname();
				}

			}
			// 存到AdVO
			adVO.setAdno(adno);
			adVO.setAdname(adname);
			adVO.setAdurl(adurl);
			adVO.setAdfile(adfile);
			adVO.setAdesc(adesc);
			adVO.setFilename(filename);
			adVO.setExtname(extname);

		}
		return adVO;
	}
}
