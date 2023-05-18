<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<%int isAdmin=(Integer)(session.getAttribute("isAdmin"));
if(isAdmin==1){%>
		<jsp:include page="Header.jsp"></jsp:include>
		<%}else {%>
		<jsp:include page="UserHeader.jsp"></jsp:include>
		<% }%>
<div class="row">
<div class="col-md-6">
	<img src="https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/115a5246415003.58537fa9604a6.gif">
	</div>
	<div class="col-md-6">
	<h1>Delivery Status</h1>
	<%
			
		ResultSet result=(ResultSet) request.getAttribute("rs1");
	String stringDate="2023-05-03";  
	Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);  
	Calendar cal = Calendar.getInstance();
	cal.setTime(date1);
	    
	// manipulate date
	cal.add(Calendar.DATE, 3); 
	Date dateWith5Days = cal.getTime();
			while(result.next()){
				
		%>
		<h3>Delivery Boy Name:<%=result.getString("delivery_name") %></h3>
		<h3>Delivery Boy Number:<%=result.getString("delivery_no") %></h3>
		<h3>Expected date of delivery:<%=dateWith5Days %></h3>
		<%} %>
		
		<form action="CreatePdf">
			<input type="submit" value="Create PDF">
		</form>
	</div>
	</div>
</body>
</html>