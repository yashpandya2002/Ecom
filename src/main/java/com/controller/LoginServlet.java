package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;

public class LoginServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement preparedStatement=con.prepareStatement("select * from users where email=? and password=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				HttpSession session=request.getSession();
				if(result.getString("email").equals("admin@gmail.com"))
				{
					session.setAttribute("isAdmin",1);
				}
				else
				{
					session.setAttribute("isAdmin",0);
				}
				
				session.setAttribute("userId",(Integer)result.getInt("userId"));
				RequestDispatcher rd=request.getRequestDispatcher("ProductController");
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("error", "Please try agian!");
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}
	
		

}
