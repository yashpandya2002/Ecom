<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.DbConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mysql.cj.xdevapi.Result"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
	crossorigin="anonymous">
<title>MyCart</title>
</head>
<body>
		<%int isAdmin=(Integer)(session.getAttribute("isAdmin"));
if(isAdmin==1){%>
		<jsp:include page="Header.jsp"></jsp:include>
		<%}else {%>
		<jsp:include page="UserHeader.jsp"></jsp:include>
		<% }
		int myCart=0; %>
		<%
			
		ResultSet result=(ResultSet) request.getAttribute("result");
		
			while(result.next()){
				myCart=1;
		%>
			<div class="row mt-5">
			<div class="col-md-5"></div>
			<div class="col-md-2">
						<div class="card" style="width: 20rem;">
  						<img class="card-img-top" src="<%=result.getString("path") %>" alt="Card image cap">
			  			<div class="card-body">
			  			
			    		<h5 class="card-title "><%=result.getString("name") %></h5>
			    		<h7 class="card-title"><%=result.getString("price") %></h7><br>
			    		<h7 class="card-title">Quantity:<%=result.getInt("quantity") %></h7>
			    		<br><br>
			    		<a href="DeleteProduct?productId=<%=result.getInt("productId") %>" class="btn btn-primary">Delete From Cart</a>
			  </div>
			  </div>
			  </div>
			  
			  <div class="col-md-5"></div>
</div>
<%} %>
</div>
<%if(myCart==1){ %>
<div class="row m-5">
	<div class="col-md-4"></div>
	<div class="col-md-4 align-items-center justify-content-center"><a href="CheckOutServlet" class="btn btn-primary">Checkout</a></div>
	<div class="col-md-4"></div>
</div>
<%}
else{ %>
<div class="row m-5">
	<div class="col-md-4"></div>
	<div class="col-md-4 align-items-center justify-content-center"><h2>Your cart is empty!</h2></div>
	<div class="col-md-4"></div>
</div>
<%} %>
<%@include file="Footer.jsp" %>
</body>
</html>