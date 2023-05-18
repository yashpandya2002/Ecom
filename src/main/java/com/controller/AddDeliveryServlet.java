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


public class AddDeliveryServlet extends HttpServlet {
			@Override
			protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
					String name=request.getParameter("name");
					String no=request.getParameter("no");
					int pincode=Integer.parseInt(request.getParameter("pincode"));
					try {
						Connection con=DbConnection.getConnection();
						PreparedStatement pstmt=con.prepareStatement("insert into delivery (delivery_name,delivery_no,pincode) values (?,?,?)");
						pstmt.setString(1, name);
						pstmt.setString(2, no);
						pstmt.setInt(3, pincode);
						pstmt.executeUpdate();
						RequestDispatcher rd=request.getRequestDispatcher("AddDelivery.jsp");
						rd.forward(request, response);
					} catch (Exception e) {
						// TODO: handle exception
					}
			
			}

}
