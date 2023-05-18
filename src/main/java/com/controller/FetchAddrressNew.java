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

/**
 * Servlet implementation class FetchAddrress
 */
public class FetchAddrressNew extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		Connection con=DbConnection.getConnection();
		PreparedStatement preparedStatement=con.prepareStatement("select * from delivery");
		ResultSet result=preparedStatement.executeQuery();
		
		RequestDispatcher rd=request.getRequestDispatcher("AddressNew.jsp");
		request.setAttribute("rs", result);
		rd.forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
