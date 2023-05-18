package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DbConnection;


public class AddAddressServlet extends HttpServlet {
			@Override
			protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session=request.getSession();
				int userId=(Integer)(session.getAttribute("userId"));
					int house=Integer.parseInt(request.getParameter("house"));
					String street=request.getParameter("street");
					String city=request.getParameter("city");
					String landmark=request.getParameter("landmark");
					String pincode=request.getParameter("pincode");
					
//					Integer price=Integer.parseInt(request.getParameter("price"));
//					String path="resources/images/"+request.getParameter("path")+".jpg";
					try {
						int total=Integer.parseInt((String) session.getAttribute("total"));
						System.out.println("total is"+total);
						
						Connection con=DbConnection.getConnection();
						PreparedStatement pstmt=con.prepareStatement("insert into address (userId,house,street,city,landmark,pincode) values (?,?,?,?,?,?)");
						pstmt.setInt(1, userId);
						pstmt.setInt(2, house);
						pstmt.setString(3, street);
						pstmt.setString(4, city);
						pstmt.setString(5, landmark);
						pstmt.setInt(6, Integer.parseInt(pincode));
						pstmt.executeUpdate();
						RequestDispatcher rd=request.getRequestDispatcher("DisplayMessageServlet");
						request.setAttribute("error", "Address Added Successfully");
						rd.forward(request, response);
					} catch (Exception e) {
						System.out.println(e);
						// TODO: handle exception
					}
			
			}

}
