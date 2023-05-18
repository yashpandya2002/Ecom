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
public class AddToCart extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int userId=(Integer)(session.getAttribute("userId"));
		int productId=Integer.parseInt(request.getParameter("productId"));
		System.out.println("The product id chosen is "+productId+" and the userId is"+userId );
		try {
			Connection con=DbConnection.getConnection();
			PreparedStatement preparedStatement=con.prepareStatement("select * from carts where userId=? and productId=?");
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, productId);
			ResultSet result=preparedStatement.executeQuery();
			if(result.next())
			{
				int qty=result.getInt("quantity");
				System.out.println("The quantity is"+qty);
				qty=qty+1;
				System.out.println("The new quantity is"+qty);
				preparedStatement=con.prepareStatement("update carts set quantity=? where userId=? and productId=?");
				preparedStatement.setInt(1, qty);
				preparedStatement.setInt(2, userId); 
				preparedStatement.setInt(3, productId);
				preparedStatement.executeUpdate();
			}
			else {
				preparedStatement=con.prepareStatement("insert into carts (userId,productId,quantity) values (?,?,1)");
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, productId);
				preparedStatement.executeUpdate();
			}
			response.sendRedirect("ProductController");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	 
}
