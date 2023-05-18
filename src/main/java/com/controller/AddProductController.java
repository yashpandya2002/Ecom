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


public class AddProductController extends HttpServlet {
			@Override
			protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
					String name=request.getParameter("name");
					Integer price=Integer.parseInt(request.getParameter("price"));
					String path="resources/images/"+request.getParameter("path")+".jpg";
					try {
						Connection con=DbConnection.getConnection();
						PreparedStatement pstmt=con.prepareStatement("insert into products (name,price,path) values (?,?,?)");
						pstmt.setString(1, name);
						pstmt.setInt(2, price);
						pstmt.setString(3, path);
						pstmt.executeUpdate();
						RequestDispatcher rd=request.getRequestDispatcher("AddProduct.jsp");
						rd.forward(request, response);
					} catch (Exception e) {
						// TODO: handle exception
					}
			
			}

}
