package com.bikefunclub.photo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;/**支援圖片上傳*/
import com.bikefunclub.album.model.AlbumService;
import com.bikefunclub.album.model.AlbumVO;
import com.bikefunclub.gpalbum.model.GpalbumVO;
import com.bikefunclub.photo.model.*;

@WebServlet("/PhotoServlet")
public class PhotoServlet extends HttpServlet {


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

		String action = null;
		MultipartRequest multi = null;
		// instance AdService
		PhotoService photoSvc = new PhotoService();
		String contentType = req.getContentType();
		// 判斷request的contentType
		if (contentType != null && contentType.startsWith("multipart/form-data")) {
			/** 建立MultipartRequest實體 */
			multi = new MultipartRequest(req, getServletContext().getRealPath(
					"img"), 5 * 1024 * 1024, "UTF-8");
			action = multi.getParameter("action");
		} else {
			action = req.getParameter("action");
		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("photono");  //form 送過來的資料格式是String
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入相片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/photo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				Integer photono = null;
				try {
					photono = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("相片編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/photo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PhotoVO photoVO = photoSvc.getOnePhoto(photono);
				if (photoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				// if (errorMsgs不是空的)
				if (!errorMsgs.isEmpty()) {  
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/photo/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("photoVO", photoVO); // 資料庫取出的empVO物件,存入req
				String url = "/back/photo/listOnePhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/photo/select_page.jsp");
				failureView.forward(req, res);
			}
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
				Integer photono = new Integer(req.getParameter("photono"));
				/** 2.開始查詢資料 */
				PhotoVO photoVO = photoSvc.getOnePhoto(photono);

				/** 3.查詢完成,準備轉交(Send the Success view) */
				req.setAttribute("photoVO", photoVO); // 資料庫取出的adVO物件,存入req
				String url = "/back/photo/update_photo_input.jsp";
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
				Integer photono = new Integer(multi.getParameter("photono"));
				Integer memno = new Integer(multi.getParameter("memno"));
			  //String phcoo = multi.getParameter("phcoo").trim();
				Integer phass = new Integer(multi.getParameter("phass"));
				Timestamp phup = Timestamp.valueOf(multi.getParameter("phup"));

				InputStream in = null;
				byte[] phfile = null;
				String phfilename = "";
				String phextname = "";

				PhotoVO photoVO = upload(multi, photoSvc, in, photono, memno, phass, phfilename,
						          phextname, phfile);

				phfile = photoVO.getPhfile();
				phfilename = photoVO.getPhfilename();
				phextname = photoVO.getPhextname();

				if (phfilename.isEmpty()) {
					errorMsgs.add("請輸入相片檔名");
				}
				if (phfile == null) {
					errorMsgs.add("請選擇照片檔案!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("photoVO", photoVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/photo/update_photo_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/

				photoVO = photoSvc.updatePhoto(photono , memno , phass , phfilename , phextname
			            , phup , phfile);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = requestURL + "?whichPage=" + whichPage + "&photono="
						+ photono;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交送出修改的來源網頁路徑
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back/photo/update_photo_input.jsp");
				failureView.forward(req, res);
			}
		}
	if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			    Integer photono = 0;// insert時，adno給0,因編號是由sequence產生
				Integer memno = new Integer(multi.getParameter("memno").trim());
				Integer phass = new Integer(multi.getParameter("phass").trim());

				InputStream in = null;
				byte[] phfile = null;
				String phfilename = "";
				String phextname = "";

				PhotoVO photoVO = upload(multi, photoSvc, in, photono, memno, phass, phfilename,
				          phextname, phfile);
				phfile = photoVO.getPhfile();
				phfilename = photoVO.getPhfilename();
				phextname = photoVO.getPhextname();

				if (phfilename.isEmpty()) {
					errorMsgs.add("請輸入照片名稱!");
				}
				if (phextname.isEmpty()) {
					errorMsgs.add("請輸入照片副檔名!");
				}
				if (phfile == null) {
					errorMsgs.add("請選擇照片!");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("photoVO", photoVO); // 含有輸入格式錯誤的adVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/album/page_addPhoto.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 *****************************************/
				Integer albno = new Integer(multi.getParameter("albno").trim());
				photoSvc.insertWithGpalbum(photoVO , albno);
				AlbumService albumSvc = new AlbumService();
				AlbumVO albumVO = albumSvc.getOneAlbum(albno);
				List<PhotoVO> listPohto = albumSvc.getAlbno(albno);
				
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("albumVO", albumVO);
				req.setAttribute("listPohto", listPohto); 
				String url = "/front/album/page_OneAlbum_TO_PHOTO.jsp?albno="+multi.getParameter("albno");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交page_listAllAd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/album/page_addPhoto.jsp");
				failureView.forward(req, res);
			}
		}
	if ("myinsert".equals(action)) { 

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		    Integer photono = 0;// insert時，adno給0,因編號是由sequence產生
			Integer memno = new Integer(multi.getParameter("memno").trim());
			Integer phass = new Integer(multi.getParameter("phass").trim());

			InputStream in = null;
			byte[] phfile = null;
			String phfilename = "";
			String phextname = "";

			PhotoVO photoVO = upload(multi, photoSvc, in, photono, memno, phass, phfilename,
			          phextname, phfile);
			phfile = photoVO.getPhfile();
			phfilename = photoVO.getPhfilename();
			phextname = photoVO.getPhextname();

			if (phfilename.isEmpty()) {
				errorMsgs.add("請輸入照片名稱!");
			}
			if (phextname.isEmpty()) {
				errorMsgs.add("請輸入照片副檔名!");
			}
			if (phfile == null) {
				errorMsgs.add("請選擇照片!");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("photoVO", photoVO); // 含有輸入格式錯誤的adVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/album/page_addmyPhoto.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 *****************************************/
			Integer albno = new Integer(multi.getParameter("albno").trim());
			photoSvc.insertWithGpalbum(photoVO , albno);
			AlbumService albumSvc = new AlbumService();
			AlbumVO albumVO = albumSvc.getOneAlbum(albno);
			List<PhotoVO> listPohto = albumSvc.getAlbno(albno);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("albumVO", albumVO);
			req.setAttribute("listPohto", listPohto); 
			String url = "/front/album/page_OneAlbum_TO_MYPHOTO.jsp?albno="+multi.getParameter("albno");
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交page_listAllAd.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front/album/page_addmyPhoto.jsp");
			failureView.forward(req, res);
		}
	}	
		if ("delete".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑:
																// 可能為【/add/listAllAd.jsp】
			String whichPage = req.getParameter("whichPage"); // 送出刪除的來源網頁的第幾頁(只用於:listAllAd.jsp)
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer photono = new Integer(req.getParameter("photono"));

				/*************************** 2.開始刪除資料 ***************************************/
				photoSvc.deletePhoto(photono);

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
	public PhotoVO upload(MultipartRequest multi, PhotoService photoSvc, InputStream in,
			Integer photono,Integer memno,Integer phass,String  phfilename,
			String  phextname,byte[]  phfile) throws IOException {
		PhotoVO photoVO = new PhotoVO();
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
					phfilename = filenameall.substring(0, dotPos);
					phextname = filenameall.substring(dotPos + 1);

					// 將file轉成InputStream
					in = new FileInputStream(f);
					// InputStream轉型byte array
					int picsize = in.available();
					phfile = new byte[picsize];
					in.read(phfile);

					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {// f == null
				if (photono != 0) {// update時使用
					photoVO = photoSvc.getOnePhoto(photono);

					photono = photoVO.getPhotono();
					memno  = photoVO.getMemno();
				  //phcoo = photoVO.getPhcoo();
					phass = photoVO.getPhass();
					phfilename = photoVO.getPhfilename();
					phextname = photoVO.getPhextname();
//					phup = photoVO.getPhup();
					phfile = photoVO.getPhfile();
				}

			}
			// 存到AdVO
			photoVO.setPhotono(photono);
			photoVO.setMemno(memno);
	      //photoVO.setPhcoo(phcoo);
			photoVO.setPhass(phass);
			photoVO.setPhfilename(phfilename);
			photoVO.setPhextname(phextname);
//			photoVO.setPhup(phup);
			photoVO.setPhfile(phfile);

		}
		return photoVO;
	}
}
