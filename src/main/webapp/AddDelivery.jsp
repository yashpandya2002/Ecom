<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
	crossorigin="anonymous">
<title>E-COM Login</title>
</head>
<body>
<%int isAdmin=(Integer)(session.getAttribute("isAdmin"));
if(isAdmin==1){%>
		<jsp:include page="Header.jsp"></jsp:include>
		<%}else {%>
		<jsp:include page="UserHeader.jsp"></jsp:include>
		<% }%>
		<div class="container-fluid">
			Add Product
			<div class="row">
			<div class="col-md-4"></div>
				<div class="col-md-4">
					<form class="" action="AddDeliveryServlet" method="post">
					
					<div class="form-group">
						<label>Delivery Person Name:</label>
						<input type="text" name="name" class="form-control"/>
						<div></div>
					</div>
					<div class="form-group">
						<label>Delivery Person Phone Number:</label>
						<input type="text" name="no" class="form-control"/>
						<div></div>
					</div>
					<div class="form-group">
						<label>Pincode:</label>
						<input type="text" name="pincode" class="form-control"/>
						<div></div>
					</div>
					
					<div class="form-group">
					
						<input type="submit" title="AddDelivery" class="btn btn-success" value="Add Delivery">
					</div> 
					
					</form>
					
		
				</div>
			</div>
			
		</div>

</body>
</html>