package com.bikefunclub.ad.controller;

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

/**
 * Servlet implementation class CarouselServlet
 */
@WebServlet("/CarouselServlet")
public class CarouselServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");

		String adno = req.getParameter("adno");
		AdService adsvc = new AdService();
		AdVO adVO = adsvc.getOneAd(Integer.parseInt(adno));
		// 開啟輸出資料串流,從資料庫1次傳4k資料在網頁上,直到傳輸完畢
		ServletOutputStream out = res.getOutputStream();

		try {
			if (adVO != null) {
				BufferedInputStream in = new BufferedInputStream(
						new ByteArrayInputStream(adVO.getAdfile()));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				out.write(adVO.getAdfile());

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
