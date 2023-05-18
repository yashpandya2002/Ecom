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

/**
 * Servlet implementation class DisplayMessageServlet
 */
public class DisplayMessageServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int userId=(Integer)(session.getAttribute("userId"));
		try {
		Connection con=DbConnection.getConnection();
		PreparedStatement preparedStatement=con.prepareStatement("select * from delivery join address where address.userId=? and address.pincode=delivery.pincode");
		preparedStatement.setInt(1, userId);
		ResultSet result1=preparedStatement.executeQuery();
		
		
		RequestDispatcher rd=request.getRequestDispatcher("DisplayMessage.jsp");
		request.setAttribute("rs1",result1);
		
		rd.forward(request, response);
		}
		catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}

}
