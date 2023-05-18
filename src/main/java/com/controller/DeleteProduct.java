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
public class DeleteProduct extends HttpServlet {

		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				int productId=Integer.parseInt(request.getParameter("productId"));
				HttpSession session=request.getSession();
				int userId=(Integer)session.getAttribute("userId");
				try {
					System.out.println("try entered");
					System.out.println("userid:"+userId+" productId:"+productId);
					Connection con=DbConnection.getConnection();
					if(con==null)
					{
						System.out.println("null con");
					}
					else
					{
						System.out.println("con not null");
					}
					PreparedStatement preparedStatement=con.prepareStatement("select * from carts where productId=? and userId=?");
					preparedStatement.setInt(1, productId);
					preparedStatement.setInt(2, userId);
					ResultSet result=(ResultSet)preparedStatement.executeQuery();
					int qty=0;
					if(result.next())
					{
						System.out.println("not null result");
						qty=result.getInt("quantity");
					}
					else
					{
						System.out.println("resukt null");
						
					}
					
					System.out.println("From deleteproduct servlet the qty is"+qty);
					if(qty==1) {
					PreparedStatement preparedStatement1=con.prepareStatement("delete from carts where productId=? and userId=?");
					preparedStatement1.setInt(1, productId);
					preparedStatement1.setInt(2, userId);
					preparedStatement1.executeUpdate();
					}
					else
					{
						qty=qty-1;
						PreparedStatement preparedStatement2=con.prepareStatement("update carts set quantity=? where productId=? and userId=?");
						preparedStatement2.setInt(1, qty);
						preparedStatement2.setInt(2, productId);
						preparedStatement2.setInt(3, userId);
						preparedStatement2.executeUpdate();
					}
					response.sendRedirect("MyCartServlet");
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
}
