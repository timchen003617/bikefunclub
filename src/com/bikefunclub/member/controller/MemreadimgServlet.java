package com.bikefunclub.member.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bikefunclub.member.model.MemService;
import com.bikefunclub.member.model.MemVO;

/**
 * Servlet implementation class MemreadimgServlet
 */
@WebServlet("/MemreadimgServlet")
public class MemreadimgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");

		String memno = req.getParameter("memno");
		MemService memsvc = new MemService();
		MemVO memVO = memsvc.getOnemem(Integer.parseInt(memno));
		// 開啟輸出資料串流,1次傳4k資料在網頁上,直到上傳完畢
		ServletOutputStream out = res.getOutputStream();

		try {
			if (memVO != null) {
				BufferedInputStream in = new BufferedInputStream(
						new ByteArrayInputStream(memVO.getMemfile()));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				out.write(memVO.getMemfile());

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
