package com.bikefunclub.gpcomm.controller;

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
import com.bikefunclub.gpcomm.model.GpcommService;
import com.bikefunclub.gpcomm.model.GpcommVO;

@WebServlet("/Gpcomm.do")
public class GpcommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GpcommServlet() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String action = req.getParameter("action");

		if ("gpcomm".equals(action)) {// 新增揪團留言
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數 **/
			Integer gpno = new Integer(req.getParameter("thisgpno"));
			Integer memno = new Integer(req.getParameter("commemno"));
			String gpcomm = req.getParameter("gpcomm");
			Date date = new Date(System.currentTimeMillis());

			Timestamp adddate = Timestamp.valueOf(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(date));

			/** 2.開始刪除memgp資料 **/
			GpcommService gpcommSvc = new GpcommService();
			GpcommVO gpcommVO = new GpcommVO();

			gpcommVO.setGpno(gpno);
			gpcommVO.setMemno(memno);
			gpcommVO.setGpcomm(gpcomm);
			gpcommVO.setGpcommdate(adddate);

			gpcommVO = gpcommSvc.insert(gpcommVO);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("gpcommVO", gpcommVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/gp/page_joingp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			GpVO gpVO = new GpVO();
			GpService gpSvc = new GpService();
			gpVO = gpSvc.findByPrimaryKey(gpno);

			req.setAttribute("gpVO", gpVO);
			String url = "/front/gp/page_joingp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}
	}

}
