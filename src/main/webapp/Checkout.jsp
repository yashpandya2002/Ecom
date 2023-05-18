<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.DbConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%int isAdmin=(Integer)(session.getAttribute("isAdmin"));
		System.out.println(isAdmin+"value");
if(isAdmin==1){%>
		<jsp:include page="Header.jsp"></jsp:include>
		<%}else{%>
		<jsp:include page="UserHeader.jsp"></jsp:include>
		<% }%>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
						<table class="table">
  
						  <tbody>
						
						<%
						
							ResultSet result=(ResultSet)request.getAttribute("result");
							ResultSet result1=(ResultSet)request.getAttribute("result1"); 
							while(result.next()){
						%>
						    <tr>
						      <td>		<img style="width=5rem;height=5rem;" src="<%= (String)result.getString("path")%>" alt="Picture"></td>
						      <td>		<%=(String)result.getString("name") %>
						  					</td>
						      <td><%=result.getInt("price") %></td>
						      <td>Qty:<%=result.getInt("quantity") %></td>
						    </tr>
						    <%} %>
						    <tr>
						    	<td><%while(result1.next()){ %><h3>Total:<%=result1.getInt("total") %></h3><%} %></td>
						    	<td> 
						    	<%
						    	
								int userId=(Integer)(session.getAttribute("userId"));
						    	Connection con=DbConnection.getConnection();
								PreparedStatement pstmt=con.prepareStatement("select * from address where userId=?");
								pstmt.setInt(1, userId);
								ResultSet rs=pstmt.executeQuery();
								if(rs.next()){
						    	%>
						    		<form action="FetchAddrress">
						    			<input type="submit" value="Update Address" class="btn btn-success">
						    		</form><%}else{ %>
						    		<form action="FetchAddrressNew">
						    			<input type="submit" value="Add Address" class="btn btn-success">
						    		</form><%} %>
						    	</td>	
						    </tr>
						    
							</tbody>
						
							</table>  
															
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>