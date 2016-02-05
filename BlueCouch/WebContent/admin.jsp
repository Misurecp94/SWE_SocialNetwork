<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<title>Admin</title>
</head>
<body>
<!-- Fuer JSP benoetigte Java Imports -->
<%@ page import="benutzer.management.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.File" %>
<%@ page import="benutzer.user.*" %>
<!--  AnwenderManagement um entsprechende Methoden zur Verfuegung zu stellen -->
<% AnwenderManagement aw = new AnwenderManagement(); %>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header" align="center">
      <a class="navbar-brand" style="width:400px">Admin <%= request.getAttribute("email") %></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" align="center">
      <ul class="nav navbar-nav">
        <li class="active">
        	<a style="width:500px" >Gemeldete Beiträge</a>
        </li>
      </ul>
      	  <form action="/BlueCouch/Logout" method="get">
	      <ul class="nav navbar-nav navbar-right">
	        <li>
	        	<a href="/BlueCouch/Logout">Logout</a>
	        </li>
	      </ul>
	      </form>
    </div>
  </div>
</nav>
<br>
<br>
<br>
<!-- Liste aller gemeldeten Beitraege -->
<% ArrayList<Beitrag> gemBeitrag = aw.getGemeldeteBeitraege((String) request.getAttribute("email")); %>
	<% if(request.getAttribute("warning") != null) {%>
			<br>
			<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
			<div class="alert alert-dismissible alert-danger">
 				 <strong>Datum ungültig oder in der Vergangenheit. Format: YYYY-MM-DD</strong>
			</div>
			</div>
			<div class="col-md-2"></div>
			</div>
	<%} %>
  			<div class ="row">
  				<div class="col-md-2"></div>
  				<div class="col-md-8">
  				<br>
  				<%if(gemBeitrag != null) {%>
  					<legend>Gemeldete Beiträge  (<%=gemBeitrag.size() %>) </legend>
  					<%} else { %>
  					<legend>Gemeldete Beiträge (0)</legend>
  					<%} %>
  				</div>
  				<div class="col-md-2"></div>
  			</div>
  			<div class="row">
  				<div class="col-md-2"></div>
  				<div class="col-md-8" align = "right">
		 			
		 		</div>
		 		<div class="col-md-2"></div>
  			</div>
  			<br>
  			<%if(gemBeitrag != null){ %>
  			<%for(int i = 0; i<gemBeitrag.size(); i++) {  %>
  			<form class="form-horizontal" action="/BlueCouch/Admin" method="post">
  			<fieldset>
			<div class="row">
							<div class="col-md-2"></div>
		 					<div class="col-md-8">
		  						<div class="panel panel-primary">
		  							<div class="panel-heading">
		    							<h4 class="panel-title"> <%=gemBeitrag.get(i).getTitel() %> </h4>
		 							 </div>
		 							 <div class="panel-body">
		   								 <%=gemBeitrag.get(i).getInhalt() %>
		 							 </div>
		 							 <div align="right">
		 							 <input type="hidden" name="beitragsID" class="form-control" value="<%=gemBeitrag.get(i).getID()%>">
		 							 <%if(aw.istGesperrt(gemBeitrag.get(i).getUser())){ %>
		 							 <button class="btn btn-danger btn-xs">User bereits gesperrt </button>
		 							 <%} else { %>
		 							 <button type="submit" class="btn btn-danger btn-xs" name="userSperren" value="<%=gemBeitrag.get(i).getUser()%>">User sperren </button>
		 							 <%} %>
		 							 <button type="submit" class="btn btn-warning btn-xs" name="beitragLoeschen" value="<%=gemBeitrag.get(i).getUser()%>">Beitrag löschen</button>
		 							 <button type="submit" class="btn btn-success btn-xs" name="beitragFreigeben" value="<%=gemBeitrag.get(i).getUser()%>">Beitrag freigeben</button>
		 							 <div class="col-md-5">
		 							 <input type="text" class="form-control" placeholder="yyyy-MM-dd User sperren bis ..." name="sperrenBis"></input>
		 							</div>
									</div>
								</div>
		  					</div>
		  					<div class="col-md-2"></div>
		     </div>
		    </fieldset>
			</form>
			<br>
		     <%} } %>


</body>
</html>