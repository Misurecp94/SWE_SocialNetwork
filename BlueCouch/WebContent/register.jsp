<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- login.jsp bietet Nutzer die Moeglichkeit eine Email, ein Passwort sowie den als zu registrierenden Typ
einzugeben, und an das entsprechende Servlet zu schicken, welches ihn dann entweder per Fehlermeldung zurueckleitet, 
oder zur Hauptansicht des jeweiligen Benuters weiterleitet. 
Desweiteren kann der User sich stattdessen entscheiden sich zu Registrieren (bietet weiterleitung zu "login.jsp" -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<br>
<div class="row">
	<div class="col-md-4">
     </div>
     <div class="col-md-4" align="center">
     	<h1>Register</h1>
     </div>
     <div class="col-md-4">
     </div>
</div>
<br>
<div class="row">
	<div class="col-md-4">
     </div>
     <div class="col-md-4" align="center">
     	<h3>Bitte Geben Sie Ihre Daten ein:</h3>
     </div>
     <div class="col-md-4">
     </div>
</div>
<br>
<br>
<form class="form-horizontal" action="/BlueCouch/Register" method="post">
  <fieldset>
  <div class="row">
 	 <div class="col-md-4">
     </div>
  	 <div class="col-md-4">
	      <input type="text" class="form-control" id="inputEmail" name="eMail" placeholder="E-Mail"><br>
    </div>
    <div class="col-md-4">
  	</div>
  </div>
  <div class="row">
  	<div class="col-md-4">
  	</div>
  	<div class="col-md-4">
        <input type="password" class="form-control" id="inputPassword" name="password" placeholder=Password><br>
	</div>
	<div class="col-md-4">
  	</div>
  </div>
  <div class="row">
  	<div class="col-md-4">
  	</div>
  	<div class="col-md-4">
  		<div class="row">
  		<div class="col-md-4" align="left">
	  		<div class="radio">
	          <label>
	            <input type="radio" name="typ" id="optionsRadios1" value="user" checked="">
	            User
	          </label>
	        </div>
  		</div>
  		<div class="col-md-4" align="center">
	  		<div class="radio">
	          <label>
	            <input type="radio" name="typ" id="optionsRadios2" value="forscher">
	            Forscher
	          </label>
	        </div>
  		</div>
  		<div class="col-md-4" align="right">
  			<div class="radio">
	          <label>
	            <input type="radio" name="typ" id="optionsRadios3" value="admin">
	            Admin
	          </label>
	        </div>
  		</div>
  		</div>
        <br>
  	</div>
  	<div class="col-md-4">
  	</div>
  </div>
  <div class="row">
   <div class="col-md-4">
   </div>
   <div class="col-md-4">
   		<div class="row">
			<div class="col-md-4" align="left">
				<button type="submit" class="btn btn-primary" style="width:100px">Submit</button>
     		</div>
     		<div class="col-md-4">
    		</div>
     		<div class="col-md-4" align="right">
     			 <a href="/BlueCouch/Login" class="btn btn-default">Login</a>
     		</div>
	</div> 
    </div>
   	<div class="col-md-4">
   	</div>
  </div>
  </fieldset>
</form>
<br>
<!--  Fehlermeldung bei fehlender Email -->
<% if(request.getAttribute("Error") != null) { %>
	<% if(request.getAttribute("Error") == "1"){ %>
		<div class="row">
			<div class="col-md-4">
		     </div>
		     <div class="col-md-4" align="center">
		     	<div class="alert alert-dismissible alert-danger">
		  			<strong>E-Mail nicht ausgefuellt!</strong>
				</div>
		     </div>
		     <div class="col-md-4">
		     </div>
		</div>
	<%  }  %>
<!-- Fehlermeldung bei fehlendem Passwort -->
	<% if(request.getAttribute("Error") == "2"){ %>
		<div class="row">
			<div class="col-md-4">
		     </div>
		     <div class="col-md-4" align="center">
		     	<div class="alert alert-dismissible alert-danger">
		  			<strong>Passwort nicht ausgefuellt!</strong>
				</div>
		     </div>
		     <div class="col-md-4">
		     </div>
		</div>
	<%  }  %>
<!-- Fehlermeldung bei fehlender Auswahl des Typs -->
	<% if(request.getAttribute("Error") == "3"){ %>
		<div class="row">
			<div class="col-md-4">
		     </div>
		     <div class="col-md-4" align="center">
		     	<div class="alert alert-dismissible alert-danger">
		  			<strong>Typ nicht ausgewählt!</strong>
				</div>
		     </div>
		     <div class="col-md-4">
		     </div>
		</div>
	<%  }  %>
<!-- Fehlermeldung, falls entsprechende eindeutige EmailID bereits vergeben ist -->
	<% if(request.getAttribute("Error") == "4"){ %>
		<div class="row">
			<div class="col-md-4">
		     </div>
		     <div class="col-md-4" align="center">
		     	<div class="alert alert-dismissible alert-danger">
		  			<strong>E-Mail bereits vergeben!</strong>
				</div>
		     </div>
		     <div class="col-md-4">
		     </div>
		</div>
	<%  }  %>
<%  }  %>
</html>