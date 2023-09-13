package co.yedam.prj.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private String encode;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encode = filterConfig.getInitParameter("encode"); // web.xml 에 설정된 encode를 가져온다
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException { // 프로젝트가 동작할때
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encode);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() { // 프로젝트가 끝날때

	}

}
