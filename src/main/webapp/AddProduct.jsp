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
					<form class="" action="AddProductController" method="post">
					
					<div class="form-group">
						<label>Product Name:</label>
						<input type="text" name="name" class="form-control"/>
						<div></div>
					</div>
					<div class="form-group">
						<label>Price:</label>
						<input type="text" name="price" class="form-control"/>
						<div></div>
					</div>
					<div class="form-group">
						<label>Path:</label>
						<input type="text" name="path" class="form-control"/>
						<div></div>
					</div>
					
					<div class="form-group">
					
						<input type="submit" title="AddProduct" class="btn btn-success">
					</div> 
					
					</form>
					
		
				</div>
			</div>
			
		</div>

</body>
</html>