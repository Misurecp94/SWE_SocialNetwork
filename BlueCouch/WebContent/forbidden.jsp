<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- forbidden.jsp wird beim Versuch angezeigt, eine Seite zu betrachten, für die dem Benutzer die notwendigen
Berechtigungen fehlen (Beispielsweise nicht eingeloggt, oder User versucht Forscher-Seite zu betrachten  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zugriff verboten</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>

<div class="row">
	<div class="col-md-4">
	</div>
	<div class="col-md-4" align="center">
	<br>
		<h1> Zugriff verboten</h1>
	<br>
		<p> Sie haben versucht auf eine Seite zuzugreifen, auf die Sie keine Berechtigung haben.</p>
		<br>
		<!-- Weist zurück auf IndexPage -->
		<a href="/BlueCouch/" class="btn btn-primary">Zurück</a>
	</div>
	<div class="col-md-4">
	</div>
</div>

</body>
</html>