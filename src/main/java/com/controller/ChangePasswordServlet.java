package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DbConnection;

public class ChangePasswordServlet extends HttpServlet {
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password1=request.getParameter("password1");
		String password2=request.getParameter("password2");
		String email=request.getParameter("email");
		if(password1.equals(password2)) {
		try {
			
			Connection db=DbConnection.getConnection();
			PreparedStatement pstmt=db.prepareStatement("update users set password=? where email=?");
			pstmt.setString(1, password1);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
			response.sendRedirect("Login.jsp");
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		}
		else
		{
			request.setAttribute("error", "Both passwords should match");
			RequestDispatcher rd=request.getRequestDispatcher("ChangePassword.jsp");
			rd.forward(request, response);
		}
}
}
