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

public class CheckOutServlet extends HttpServlet {

		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session=request.getSession();
			int userId=(Integer) session.getAttribute("userId");
			try {
				Connection con=DbConnection.getConnection();
				PreparedStatement preparedStatement=con.prepareStatement
						("select * from carts join products where carts.productId=products.productId and carts.userId=?");
				preparedStatement.setInt(1, userId);
				ResultSet result=preparedStatement.executeQuery();
				
				request.setAttribute("result",result);
				preparedStatement=con.prepareStatement
						("select sum(quantity*price) as total from carts join products where carts.productId=products.productId and carts.userId=?");
				preparedStatement.setInt(1, userId);
				
				ResultSet result1=preparedStatement.executeQuery();
//				int total=Integer.parseInt(result1.getString("total"));
//				session.setAttribute("total",total);
				request.setAttribute("result1", result1);
//				int total = 0;
//				while(result1.next()) {
//					total=Integer.parseInt(result1.getString("total"));
//				}
//				session.setAttribute("total", total);
				RequestDispatcher rd=request.getRequestDispatcher("Checkout.jsp");
				rd.forward(request, response);
				
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}

		}
}
