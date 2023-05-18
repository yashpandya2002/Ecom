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

import com.util.DbConnection;
import com.util.SendMail;

public class SignUpServlet extends HttpServlet {
@Override
			protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String firstName=request.getParameter("name");
				String email=request.getParameter("email");
				String password=request.getParameter("password");
				try {
					Connection con=DbConnection.getConnection();
					PreparedStatement preparedStatement=con.prepareStatement("select * from users where email=?");
					preparedStatement.setString(1, email);
					ResultSet resultSet=preparedStatement.executeQuery();
					if(!resultSet.next()) {
//					if(con==null)
//					{
//						System.out.println("The connection is null");
//					}
//					else
//					{
//						System.out.println("The connection is  not null");
//					}
					preparedStatement=con.prepareStatement("insert into users (firstName,email,password) values (?,?,?)");
					preparedStatement.setString(1, firstName);
					preparedStatement.setString(2, email);
					preparedStatement.setString(3, password);
					preparedStatement.executeUpdate();
					SendMail.SignupMail(email);
					request.setAttribute("success", "Signed Up Successfuly!");
					RequestDispatcher requestDispatcher=request.getRequestDispatcher("Login.jsp");
					requestDispatcher.forward(request, response);
					}
					else {
						request.setAttribute("error", "An account already exists with this email address");
						RequestDispatcher requestDispatcher=request.getRequestDispatcher("SignUp.jsp");
						requestDispatcher.forward(request, response);
					}
					//response.sendRedirect("Login.jsp");
				} catch (Exception e) {
					// TODO: handle exception
				}
			
			}
}
