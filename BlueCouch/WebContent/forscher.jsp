<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- forscher.jsp ist die Hauptansicht des Forschers. Hier erhaelt dieser die Möglichkeit die entsprechenden
Auswertungen vorzunehmen und sich die Ergebnisse anzeigen zu lassen -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<title>Forscher</title>
</head>
<body>

<!-- Navigationsleiste -->
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header" align="center">
      <a class="navbar-brand" style="width:400px">Forscher <%= request.getAttribute("email") %></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" align="center">
      <ul class="nav navbar-nav">
        <li class="active">
        	<a style="width:500px" >Statistiken</a>
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

<!--  Statistik funktionen aufrufen und auswerten  -->
<div class="row">
	<div class="col-md-4">
     </div>
     <div class="col-md-4" align="center">
     	<h3>Bitte wählen Sie die Auswertungen:</h3>
     </div>
     <div class="col-md-4">
     </div>
</div>
<br>
<br>


<form class="form-horizontal" action="/BlueCouch/Forscher" method="post">
  <fieldset>

 <div class="row">
 	 	<div class="col-md-2">
     	</div>
  		 <div class="col-md-4 control-label" >
  		 
	     	 <label > Anzahl der Freunde/User: </label>
	 	
	 	 </div>
     	<div class="col-md-2">
    		 <input type="text" class="form-control" id="outputFreundeUser" name="FreundeUser" placeholder="Ergebnis" readonly
    					
			<% if(request.getAttribute("FreundeUser") != null) { %>
    			value = <% out.println(request.getAttribute("FreundeUser").toString());%>  
    		<%  }   %> 
    		
    		> <br>
   	 	</div>
   	 	<div class="col-md-1" align="center">
   	 		<div class="checkbox">
                  <input type="checkbox" id="checkboxFreundeUser" name="cbxFreundeUser">
        	 </div>
   	 	</div>
   	 	<div class="col-md-3">
  		</div>
 	 </div>
  
    <div class="row">
 	 	<div class="col-md-2">
     	</div>
  		 <div class="col-md-4 control-label" >
	     	 <label > Anzahl neuer Freunde/User (letztes Monat): </label>
	     	 	 
	 	 </div>
     	<div class="col-md-2">
    		 <input type="text" class="form-control" id="outputFreundeMonat" name="FreundeMonat" placeholder="Ergebnis" readonly
    		    		 			
			<% if(request.getAttribute("FreundeMonat") != null) { %>
    			value = <% out.println(request.getAttribute("FreundeMonat").toString());%>  
    		<%  }   %> 
    	    		 
    		 ><br>
   	 	</div>
   	 	<div class="col-md-1" align="center">
   	 		<div class="checkbox">
                  <input type="checkbox" id="checkboxFreundeMonat" name="cbxFreundeMonat" >
        	 </div>
   	 	</div>
   	 	<div class="col-md-3">
  		</div>
 	 </div>

  <div class="row">
 	 	<div class="col-md-2">
     	</div>
  		 <div class="col-md-4 control-label" align="left">
	     	 <label >Gesamte Anzahl von Freundschaftsbeziehungen: </label>
	 	 </div>
     	<div class="col-md-2">
    		 <input type="text" class="form-control" id="outputFreundeGesamt" name="FreundeGesamt" placeholder="Ergebnis" readonly
    		 
    		 <% if(request.getAttribute("FreundeGesamt") != null) { %>
    			value = <% out.println(request.getAttribute("FreundeGesamt").toString());%>  
    		<%  }   %> 
    		
    		 ><br>
   	 	</div>
   	 	<div class="col-md-1" align="center">
   	 		<div class="checkbox">
                  <input type="checkbox" id="checkboxFreundeGesamt" name="cbxFreundeGesamt" >
        	 </div>
   	 	</div>
   	 	<div class="col-md-3">
  		</div>
 	 </div>

  <div class="row">
 	 	<div class="col-md-2">
     	</div>
  		 <div class="col-md-4 control-label" align="left">
	     	 <label >Anzahl Beitraege/User: </label>
	 	 </div>
     	<div class="col-md-2">
    		 <input type="text" class="form-control" id="outputBeitragUser" name="BeitragUser" placeholder="Ergebnis" readonly
    		 
    		 <% if(request.getAttribute("BeitragUser") != null) { %>
    			value = <% out.println(request.getAttribute("BeitragUser").toString());%>  
    		<%  }   %> 
    		 
    		 ><br>
   	 	</div>
   	 	<div class="col-md-1" align="center">
   	 		<div class="checkbox">
                  <input type="checkbox" id="checkboxBeitragUser" name="cbxBeitragUser" >
        	 </div>
   	 	</div>
   	 	<div class="col-md-3">
  		</div>
 	 </div>


  <div class="row">
 	 	<div class="col-md-2">
     	</div>
  		 <div class="col-md-4 control-label" align="left">
	     	 <label>Anzahl neuer Beitraege/User (letztes Monat): </label>
	 	 </div>
     	<div class="col-md-2">
    		 <input type="text" class="form-control" id="outputBeitragMonat" name="BeitragMonat" placeholder="Ergebnis" readonly
    		 
    		  <% if(request.getAttribute("BeitragMonat") != null) { %>
    			value = <% out.println(request.getAttribute("BeitragMonat").toString());%>  
    		<%  }   %> 
    		     		 
    		 ><br>
   	 	</div>
   	 	<div class="col-md-1" align="center">
   	 		<div class="checkbox">
                  <input type="checkbox" id="checkboxBeitragMonat" name="cbxBeitragMonat" >
        	 </div>
   	 	</div>
   	 	<div class="col-md-3">
  		</div>
 	 </div>

  <div class="row">
 	 	<div class="col-md-2">
     	</div>
  		 <div class="col-md-4 control-label" align="left">
	     	 <label>Anzahl aktuell geperrter User: </label>
	 	 </div>
     	<div class="col-md-2">
    		 <input type="text" class="form-control" id="outputUserSperren" name="UserSperren" placeholder="Ergebnis" readonly
    		 
    		 	  <% if(request.getAttribute("UserSperren") != null) { %>
    			value = <% out.println(request.getAttribute("UserSperren").toString());%>  
    		<%  }   %> 
    		 
    		 ><br>
   	 	</div>
   	 	<div class="col-md-1" align="center">
   	 		<div class="checkbox">
                  <input type="checkbox" id="checkboxUserSperren" name="cbxUserSperren" >
        	 </div>
   	 	</div>
   	 	<div class="col-md-3">
  		</div>
 	 </div>

<br>
 
    <div class="row">
	<div class="col-md-3">
     </div>
     <div class="col-md-6">
     		<div class="row">
				<div class="col-md-4" align="center">
					<button type="submit" class="btn btn-primary">Auswahl berechnen</button>
     			</div>
     			<div class="col-md-4" align="center">
   				 <button type="submit" class="btn btn-primary" name="alleBerechnen" >Alle berechnen</button>
   				</div>
   			  	<div class="col-md-4" align="center">
   			  		 <button type="reset" class="btn btn-default">Auswahl loeschen</button>
     			</div>
			</div>
     	      
     </div>
     <div class="col-md-3">
     </div>
	</div>
	
  </fieldset>
</form>
<br>
<br>
<br>



</body>
</html>