package com.bikefunclub.emp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bikefunclub.emp.model.*;



/**
 * Servlet implementation class LonginHandler
 */
@WebServlet("/EmpLoginHandler")
public class EmpLoginHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	EmpService empsvc = new EmpService();
	
	protected boolean allowUser(String account, String password) {
		
		EmpVO empVO = empsvc.findByAccount(account);
		
		if (empVO != null) {
			if (empVO.getEmpacc().equals(account)
					&& empVO.getEmppw().equals(password)) {
				
				return true;
				
			} else {
				
				return false;
			}
		} else {
		
			return false;
		}
	
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// req.setCharacterEncoding("UTF-8"); //已由filters.SetCharacterEncodingFilter處理
		// res.setContentType("text/html; charset=UTF-8");//已由filters.SetContentTypeFilter處理
		
		// 【取得使用者 帳號(account) 密碼(password)】
		String account = req.getParameter("account"); 
		String password = req.getParameter("password");
		
		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(account, password)) { // 【帳號 , 密碼無效時】
			req.setAttribute("errorLogin", "帳號或密碼失效");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back/home/signin.jsp");
			failureView.forward(req, res);
			
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			HttpSession session = req.getSession();
		    EmpVO empVO = empsvc.findByAccount(account);
			
			session.setAttribute("empVO", empVO); // 將員工存進session			
			
			
			try { // *工作2: 看看有無來源網頁 (-如有:則重導之)
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}

			res.sendRedirect(req.getContextPath() + "/back/ann/page_listAllAnn.jsp");    // 
																			// 無來源網頁,
																				// 則重導至index.jsp網頁)
		}
	}

}
