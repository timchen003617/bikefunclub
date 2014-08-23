package com.bikefunclub.friendlist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikefunclub.friendlist.model.*;
import com.bikefunclub.member.model.*;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/FriendListServlet")
public class FriendListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String action = null;
		MultipartRequest multi = null;
		// instance FriendListService,MemService
		FriendListService friSvc = new FriendListService();
		MemService memSvc = new MemService();
		String contentType = req.getContentType();
		// 判斷request的contentType
		if (contentType != null&&contentType.startsWith("multipart/form-data")) {
			/** 建立MultipartRequest實體 */
			multi = new MultipartRequest(req, getServletContext().getRealPath(
					"img"), 5 * 1024 * 1024, "UTF-8");
			action = multi.getParameter("action");
		} else {
			action = req.getParameter("action");
		}
		// 取得登入會員編號
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO) session.getAttribute("memVO");
		Integer memno = memVO.getMemno();

		/** 新增好友 **/
		if ("insertfri".equals(action)) {/* 來自notfrimemlist.jsp */
			/** 1.接收請求參數 */
			// 想被加入好友的其他會員
			Integer youno = new Integer(req.getParameter("insertyouno"));
			String isblack = "N";
			String isfriend = "Y";

			/** 2.登入會員開始新增好友 */
			friSvc.addFriendList(memno, youno, isblack, isfriend);
//			friSvc.addFriendList(youno, memno, isblack, isfriend);

			/**3.查詢完成,準備轉交(Send the Success view) ***********/
			String url = "/front/friendlist/friendlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		/** 刪除好友 **/
		if ("deletefri".equals(action)) {/* 來自allfrilist.jsp */
			/** 1.接收請求參數 */
			// 想被刪除好友的其他會員
			Integer youno = new Integer(req.getParameter("deleteyouno"));

			/** 2.登入會員開始刪除好友 */
			friSvc.deleteFriendList(memno, youno);
//			friSvc.deleteFriendList(youno, memno);
			/** 3.回傳刪除後的所有好友 */
			List<FriendListVO> frilist = friSvc.getAllFriendList(memno);
			req.setAttribute("FriendListVOlist", frilist);
			
			/**3.查詢完成,準備轉交(Send the Success view) ***********/
			String url = "/front/friendlist/friendlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		/** 查詢好友 **/
		if ("getfriname_For_Display".equals(action)) {
			/** 1.接收請求參數 */
			String friname = req.getParameter("friname");
			session.setAttribute("friname", friname);
			// 模糊查詢
			friname = "%" + friname + "%";
			/** 2.開始查詢登入會員好友 */
			List<FriendListVO> frilist = friSvc.getFriendListByname(memno,
					friname);			
			req.setAttribute("FriendListVOlist", frilist);
			
			/**3.查詢完成,準備轉交(Send the Success view) ***********/
			String url = "/front/friendlist/allfrilist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		/** 查詢會員 **/
		if ("getmemname_For_Display".equals(action)) {
			/** 1.接收請求參數 */
			String memname = req.getParameter("memname");
			req.setAttribute("memname", memname);
			// 模糊查詢
			memname = "%" + memname + "%";
			/** 2.開始查詢登入會員非好友會員，帶入登入會員編號、使用者輸入的會員姓名 */
			List<MemVO> memlist = memSvc.getAllfornewfribyname(memno, memname);

			req.setAttribute("MemVOlist", memlist);
			
			/**3.查詢完成,準備轉交(Send the Success view) ***********/
			String url = "/front/friendlist/notfrimemlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
