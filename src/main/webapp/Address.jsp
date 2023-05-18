<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<title>Address</title>
</head>
<body>
	<%
	int isAdmin = (Integer) (session.getAttribute("isAdmin"));
	System.out.println(isAdmin + "value");
	if (isAdmin == 1) {
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<%
	} else {
	%>
	<jsp:include page="UserHeader.jsp"></jsp:include>
	<%
	}
	%>
	<div class="container-fluid">
		Update Address
		<div class="row">
			<div class="col-md-4"></div>
			
			<div class="col-md-4">
				<form class="" action="UpdateAddressServlet" method="post">
					<%ResultSet result=(ResultSet)request.getAttribute("rs");
					ResultSet result1=(ResultSet)request.getAttribute("rs1");
						while(result.next()){
					%>
					<div class="form-group">
						<label>House:</label> <input type="text" name="house"
							class="form-control" value=" <%=result.getInt("house")%>"/>
						<div></div>
					</div>
					<div class="form-group">
						<label>Street:</label> <input type="text" name="street"
							class="form-control" value="<%=result.getString("street")%>"/>
						<div></div>
					</div>
					<div class="form-group">
						<label>City:</label> <input type="text" name="city"
							class="form-control" value="<%=result.getString("city")%>"/>
						<div></div>
					</div>
					<div class="form-group">
						<label>Landmark:</label> <input type="text" name="landmark"
							class="form-control" value="<%=result.getString("landmark")%>"/>
						<div></div>
					</div>
					<div class="form-group">
					<label>Pincode:</label>
						<select name="pincode" class="form-select" aria-label="Default select example">
					
					<%
						while(result1.next()){
					%>
						<option value="<%=result1.getInt("pincode")%>"><%=result1.getInt("pincode")%></option>
					<%} %>
					</select> 
					</div>
					
					${error}
					<div class="form-group">

						<input type="submit" title="Update Address" class="btn btn-success" value="Update Address">
					</div>
<%} %>
				</form>
				

			</div>
		</div>

	</div>

</body>
</html>