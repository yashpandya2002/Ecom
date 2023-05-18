package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;

public class OTPServlet extends HttpServlet {

	
		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			String otp=request.getParameter("otp");
			String email=(String) request.getParameter("email");
			try {
				Connection con=DbConnection.getConnection();
				HttpSession session = request.getSession();
//				String email = (String) session.getAttribute("email");
				
				System.out.println("email12   "+email);
				PreparedStatement pstmt=con.prepareStatement("select otp from users where email=?");
				pstmt.setString(1, email);
				ResultSet result=(ResultSet) pstmt.executeQuery();
//				System.out.println((String)result.getString("otp"));
				if(result.next())
				{
					String otp_f=(String)result.getString("otp");
					System.out.println("generated otp:"+otp_f);
					System.out.println("if starts");
					System.out.println("otp entered"+otp);
					if(otp.equals(otp_f))
					{
						System.out.println("nested if starts");
						request.setAttribute("email", email);
						RequestDispatcher requestDispatcher=request.getRequestDispatcher("ChangePassword.jsp");
						requestDispatcher.forward(request, response);
					}
					else
					{
						System.out.println("nested else starts");
						request.setAttribute("error","Wrong OTP entered!");
						RequestDispatcher rd=request.getRequestDispatcher("OTP.jsp");
						rd.forward(request, response);
					}
				
				}
			} catch (Exception e) {
//				System.out.println(e);
				e.printStackTrace();
				
				// TODO: handle exception
			}
			
			
			
		}
}
