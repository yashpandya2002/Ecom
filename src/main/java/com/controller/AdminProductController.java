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

import com.mysql.cj.xdevapi.Result;
import com.util.DbConnection;


public class AdminProductController extends HttpServlet {
		
		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				try {
					Connection con =DbConnection.getConnection();
					PreparedStatement pstmt=con.prepareStatement("select * from products");
					ResultSet result=pstmt.executeQuery();
					request.setAttribute("rs", result);
					RequestDispatcher rd=request.getRequestDispatcher("AdminProduct.jsp");
					rd.forward(request, response);
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
}
