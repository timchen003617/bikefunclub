package com.bikefunclub.friendlist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bikefunclub.friendlist.model.*;
import com.bikefunclub.member.model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
/**
 * Json-lib requires (at least) the following dependencies in your classpath:

	jakarta commons-lang 2.5
	jakarta commons-beanutils 1.8.0
	jakarta commons-collections 3.2.1
	jakarta commons-logging 1.1.1
	ezmorph 1.0.6
 */

/**
 * Servlet implementation class Friautocomplete
 */
@WebServlet("/Friautocomplete")
public class Friautocomplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();

		JSONArray jsonArray = new JSONArray();  
		// instance FriendListService,MemService
		FriendListService friSvc = new FriendListService();
		MemService memSvc = new MemService();

		/** 1. 取得登入會員編號 **/
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO) session.getAttribute("memVO");
		Integer memno = memVO.getMemno();
		/** 2.接收請求參數 */
		//在沒有做更改的情況下，頁面輸入框傳入request對象的輸入參數，必須用term獲取
		String friname = req.getParameter("term");
		//模糊查詢
		String frinamelike = "%"+friname+"%";
		/** 3.開始查詢登入會員好友 */
		List<FriendListVO> frilist = friSvc.getFriendListByname(memno, frinamelike);

		int i = 0;
		for (FriendListVO friendListVO : frilist) {
			// 取好友會員編號
			Integer youno = friendListVO.getYouno();
			// 取好友姓名
			MemVO memvo = memSvc.getOnemem(youno);
			String memname = memvo.getMemname();
			if (memname.startsWith(friname)) {				
				try {
					jsonArray.add(i, memname);

					i++;
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		out.println(jsonArray.toString());
		out.close();
//		System.out.println(jsonArray.toString());
	}

}
