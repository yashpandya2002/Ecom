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


public class UpdateAddressServlet extends HttpServlet {
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
						Connection con=DbConnection.getConnection();
						PreparedStatement pstmt=con.prepareStatement("update address set house=?,street=?,city=?,landmark=?,pincode=? where userId=?");
						
						pstmt.setInt(1, house);
						pstmt.setString(2, street);
						pstmt.setString(3, city);
						pstmt.setString(4, landmark);
						pstmt.setInt(5, Integer.parseInt(pincode));
						pstmt.setInt(6, userId);
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
