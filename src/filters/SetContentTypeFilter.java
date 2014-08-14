package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class SetContentTypeFilter
 */
@WebFilter("/SetContentTypeFilter")
public class SetContentTypeFilter implements Filter {
	private FilterConfig config;
	private String charset = null;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.charset = config.getInitParameter("charset");// 取得web.xml初始化參數值
	}

	public void destroy() {
		this.config = null;
		this.charset = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// place your code here
		res.setContentType("text/html; charset="+charset);
		// pass the request along the filter chain
		chain.doFilter(req, res);
	}

}
