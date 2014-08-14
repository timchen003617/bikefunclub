package com.bikefunclub.emp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bikefunclub.emp.model.*;

/**
 * Servlet implementation class LoginOut
 */
@WebServlet("/EmpLoginOut")
public class EmpLoginOut extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession();		
		session.removeAttribute("empVO");
		session.invalidate();
		
		res.sendRedirect(req.getContextPath()+"/back/home/signin.jsp");
	}

}
