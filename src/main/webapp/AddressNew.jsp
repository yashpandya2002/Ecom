<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		Add Address
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form class="" action="AddAddressServlet" method="post">

					<div class="form-group">
						<label>House:</label> <input type="text" name="house"
							class="form-control" />
						<div></div>
					</div>
					<div class="form-group">
						<label>Street:</label> <input type="text" name="street"
							class="form-control" />
						<div></div>
					</div>
					<div class="form-group">
						<label>City:</label> <input type="text" name="city"
							class="form-control" />
						<div></div>
					</div>
					<div class="form-group">
						<label>Landmark:</label> <input type="text" name="landmark"
							class="form-control" />
						<div></div>
					</div>
					<div class="form-group">
						<label>Pincode:</label> <select name="pincode" class="form-select"
							aria-label="Default select example">
							<%
							ResultSet result = (ResultSet) request.getAttribute("rs");
							while (result.next()) {
							%>

							<option value="<%=result.getInt("pincode")%>"><%=result.getInt("pincode")%></option>
							<%
							}
							%>
						</select>
					</div>

					${error}
					<div class="form-group">

						<input type="submit" title="Add Address" class="btn btn-success"
							value="Add Address" />
					</div>

				</form>


			</div>
		</div>

	</div>

</body>
</html>