package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;
public class DeleteProductAdmin extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int userId=(Integer)(session.getAttribute("userId"));
		int productId=Integer.parseInt(request.getParameter("productId"));
		System.out.println("The product id chosen is "+productId+" and the userId is"+userId );
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement preparedStatement=con.prepareStatement("delete from products where productId=?");
			
			preparedStatement.setInt(1, productId);
			preparedStatement.executeUpdate();
			response.sendRedirect("AdminProductController");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	 
}
