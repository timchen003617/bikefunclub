package com.bikefunclub.photo.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikefunclub.photo.model.*;

/**
 * Servlet implementation class CarouselServlet
 */
@WebServlet("/PhotoPreViewServlet")
public class PhotoPreViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");

		String photono = req.getParameter("photono");
		PhotoService photosvc = new PhotoService();
		PhotoVO photoVO = photosvc.getOnePhoto(Integer.parseInt(photono));
		// 開啟輸出資料串流,1次傳4k資料在網頁上,直到上傳完畢
		ServletOutputStream out = res.getOutputStream();

		try {
			if (photoVO != null) {
				BufferedInputStream in = new BufferedInputStream(
						new ByteArrayInputStream(photoVO.getPhfile()));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				out.write(photoVO.getPhfile());

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
