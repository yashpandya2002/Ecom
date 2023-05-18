package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.util.Validator;


public class SignUpFilter implements Filter {
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean isError=false;
		String firstName=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		if(Validator.onlyAlpha(firstName)==false)
		{
			isError=true;
			System.out.println("Filter error in name");
			request.setAttribute("firstNameError", "No digits allowed in the firstName");
		}
		if(Validator.countPassword(password)==false)
		{
			isError=true;
			System.out.println("Filter error in password");
			request.setAttribute("passwordError", "Password must contain a digit,lowercase letter, uppercase letter anf special symbol");
		}
		if(Validator.emailValidator(email)==false)
		{
			isError=true;
			System.out.println("Filter error in email");
			request.setAttribute("emailError", "xyz@gmail.com");
		}
		if(isError==true)
		{
			System.out.println("Sending from filter to signup jsp");
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("SignUp.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			System.out.println("Sending from filter to signup servlet");
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
