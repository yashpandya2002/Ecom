package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import com.util.DbConnection;
import com.util.SendMail;


public class ForgotPasswordServlet extends HttpServlet {
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email=request.getParameter("email");
		try {
			
		
		Connection db= DbConnection.getConnection();
		PreparedStatement pstmt=db.prepareStatement("select email from users where email=?");
		pstmt.setString(1, email);
		ResultSet result=(ResultSet) pstmt.executeQuery();
		if(result.next())
		{
			String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 5) {
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        PreparedStatement preparedStatement=db.prepareStatement("update users set otp=? where email=?");
	        preparedStatement.setString(1, saltStr);
	        preparedStatement.setString(2, email);
	        preparedStatement.executeUpdate();
	        SendMail.sendOTP(email, saltStr);
//	        HttpSession session = request.getSession();  
//			session.setAttribute("email", email);
	        request.setAttribute("email",email);
	        RequestDispatcher requestDispatcher=request.getRequestDispatcher("OTP.jsp");
	        requestDispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("error","Invalid Email!");
			RequestDispatcher rd=request.getRequestDispatcher("ForgotPassword.jsp");
			rd.forward(request, response);
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	
}
}
