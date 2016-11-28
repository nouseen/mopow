package cn.mopow.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFileter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request1=(HttpServletRequest) request;   
        HttpServletResponse response1  =(HttpServletResponse) response;    
        HttpSession session = request1.getSession(true); 
        String uri=request1.getRequestURI(); 
		if(session==null||session.getAttribute("uName") == null){
			System.out.println("用户未登陆，跳转到登陆页面");
			 Pattern pattern = Pattern.compile("mengke");
		     Matcher matcher = pattern.matcher(uri);
			 while(matcher.find()){
				 response1.sendRedirect("/mengke/log_in.html");
				 return;
			 }
		        
			response1.sendRedirect("/log_in.html");
			return ;
		}else{
			System.out.println("登陆通过！");
		};
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
