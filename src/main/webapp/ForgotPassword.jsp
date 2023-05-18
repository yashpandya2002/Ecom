<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
	crossorigin="anonymous">
<title>ECom Forgot Password</title>
</head>
<body>
		<div class="container-fluid">
			ECom ForgotPassword
			<div class="row">
			<div class="col-md-4"></div>
				<div class="col-md-4">
					<form class="" action="ForgotPasswordServlet" method="post">
					
					<div class="form-group">
						<label>Email:</label>
						<input type="text" name="email" class="form-control"/>
						<div></div>
					</div>
					${error}
					<div class="form-group">
					
						<input type="submit" title="Submit" class="btn btn-success">
					</div> 
					
					</form>
					<a href="Login.jsp">Login?</a>
		
				</div>
			</div>
		</div>

</body>
</html>