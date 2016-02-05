<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- index.jsp stellt die Startansicht dar, der "Einstieg" zur BlueCouch
Bietet User die Moeglichkeit zur Entscheidung zwischen Login und Registrierung -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<style>
img {
	max-width: 50%;
	height: auto;
}
</style>
</head>
<body>
<br><br>

<div class="row">
	<div class="col-md-4">
     </div>
     <div class="col-md-4" align="center">
     	<h1>Herzlich Willkommen zur BlueCouch!</h1>
     </div>
     <div class="col-md-4">
     </div>
</div>
<div class="row">
	<div class="col-md-4">
     </div>
     <div class="col-md-4" align="center">
     	<h2>Setzen Sie sich! </h2>
     </div>
     <div class="col-md-4">
     </div>
</div>

<center>
<img src="pic/BlueCouch.png" alt="BlueCouch.png">
</center>

<div class="row">
	<div class="col-md-4">
     </div>
     <div class="col-md-4">
     	  	<div class="row">
				<div class="col-md-4" align="right">
					<a class="btn btn-primary btn-lg" href="/BlueCouch/Login">Login</a>
     			</div>
     			<div class="col-md-4">
   				</div>
   			  	<div class="col-md-4" align="left">
   			  		<a class="btn btn-primary btn-lg" href="/BlueCouch/Register">Register</a>
     			</div>
			</div>
     </div>
     <div class="col-md-4">
     </div>
</div>

</body>
</html>