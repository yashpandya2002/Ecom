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
<title>Products</title>
</head>
<body style="background-color:aquamarine;">
		<%int isAdmin=(Integer)(session.getAttribute("isAdmin"));
		System.out.println(isAdmin+"value");
if(isAdmin==1){%>
		<jsp:include page="Header.jsp"></jsp:include>
		<%}else{%>
		<jsp:include page="UserHeader.jsp"></jsp:include>
		<% }
		ResultSet result=(ResultSet) request.getAttribute("rs");
		
			while(result.next()){
		%>
		
			<div class="row mt-5">
			<center>
			<div class="col-md-4">
						<div class="card" style="width: 20rem; height: 40rem;">
  						<img class="card-img-top" src="<%=result.getString("path") %>" alt="Card image cap">
			  			<div class="card-body">
			  			
			    		<h5 class="card-title "><%=result.getString("name") %></h5>
			    		<h7 class="card-title"><%=result.getString("price") %></h7>
			    		<br><br>
			    		<a href="AddToCart?productId=<%=result.getInt("productId") %>" class="btn btn-primary">Add To Cart</a>
			  </div>
			  </div>
			  </div>
			  </center><%result.next(); %>
			  <center>
			<div class="col-md-4">
						<div class="card" style="width: 20rem;height: 40rem;">
  						<img class="card-img-top" src="<%=result.getString("path") %>" alt="Card image cap">
			  			<div class="card-body">
			  			
			    		<h5 class="card-title "><%=result.getString("name") %></h5>
			    		<h7 class="card-title"><%=result.getString("price") %></h7>
			    		<br><br>
			    		<a href="AddToCart?productId=<%=result.getInt("productId") %>" class="btn btn-primary">Add To Cart</a>
			  </div>
			  </div>
			  </div></center><%result.next(); %>
			  <center>
			  <div class="col-md-4">
						<div class="card" style="width: 20rem;height: 40rem;">
  						<img class="card-img-top" src="<%=result.getString("path") %>" alt="Card image cap">
			  			<div class="card-body">
			  			
			    		<h5 class="card-title "><%=result.getString("name") %></h5>
			    		<h7 class="card-title"><%=result.getString("price") %></h7>
			    		<br><br>
			    		<a href="AddToCart?productId=<%=result.getInt("productId") %>" class="btn btn-primary">Add To Cart</a>
			  </div>
			  </div>
			  </div>
			  </center>
</div>
<%} %>
</div>
<%@include file="Footer.jsp" %>
</body>
</html>