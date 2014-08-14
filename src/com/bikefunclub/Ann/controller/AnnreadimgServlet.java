package com.bikefunclub.Ann.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikefunclub.ad.model.AdService;
import com.bikefunclub.ad.model.AdVO;
import com.bikefunclub.Ann.model.*;
import com.bikefunclub.Ann.controller.*;


@WebServlet("/AnnreadimgServlet")
public class AnnreadimgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");

		String annno = req.getParameter("annno");
		AnnService annsvc = new AnnService();
		AnnVO annVO = annsvc.getOneAnn(Integer.parseInt(annno));
		// 開啟輸出資料串流,1次傳4k資料在網頁上,直到上傳完畢
		ServletOutputStream out = res.getOutputStream();

		try {
			if (annVO != null) {
				BufferedInputStream in = new BufferedInputStream(
						new ByteArrayInputStream(annVO.getAnnfile()));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				out.write(annVO.getAnnfile());

			} else {
				try {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
