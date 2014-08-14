package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class MemberLoginFilter
 * 
 */
@WebFilter("/EmpLoginFilter")
public class EmpLoginFilter implements Filter {
	private FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object empVO = session.getAttribute("empVO");
		
		if (empVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back/home/signin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

}
