<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- User JSP. Stellt Schnittstelle zwischen Physischem Anwender und System zur Verfügung. Regelt die graphische
Ausgabe, sowie die grafische Eingabe von Daten, um ein ensprechendes AnwenderGUI zur Verfügung zu stellen.
Enge zusammenarbeit mit UserServlet sowie mit AnwenderManagement. Bietet dem User saemtliche Moeglichkeiten zur 
Ineraktion mit dem System betreffend seines Accounts (Daten, Bild, Gruppen, Freunde etc.) -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<title>User</title>
</head>
<body>
<!-- Fuer JSP benoetigte Java Imports -->
<%@ page import="benutzer.management.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.File" %>
<!--  AnwenderManagement um entsprechende Methoden zur Verfuegung zu stellen -->
<% AnwenderManagement aw = new AnwenderManagement(); %>
<!--  Navigationsleite -->
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header" align="center">
      <a class="navbar-brand" style="width:250px"><%=request.getAttribute("email")%></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" align="center" aria-expanded="false" style="height: 1px;">
      <ul class="nav navbar-nav">
        <li>
        	<!--  Funktion UserSuche (Eingabefeld, button, sowie aufruf zur Auswertung ueber UserServlet post-Methode -->
        	<form class="navbar-form navbar-left" action="/BlueCouch/User" method="post">
        		<div class="form-group" style="width: 150px">
         			 <input type="text" name="usersuche" class="form-control" placeholder="SearchUser" >
        		</div>
       			 <button type="submit" class="btn btn-default">Suchen</button>
    			</form>
        </li>
        <!-- Aktuelle position in Navigation mittels "Active" Schaltflaeche festhalten -->
        <li <% if(request.getAttribute("p") != null) {
	        	   if(request.getAttribute("p").equals("Pinnwand")){%>
	        			class="active"   
	        	<% }
       			} %>>
        	<a href="/BlueCouch/User?p=Pinnwand" style="width:90px"> Pinnwand </a>
        </li>
        <li <% if(request.getAttribute("p") != null) {
	        	   if(request.getAttribute("p").equals("Freunde") || request.getAttribute("p").equals("Gruppe")){%>
	        			class="active"
	        	<% }
       			} %>>
        	<a href="/BlueCouch/User?p=Freunde" style="width:90px" > Freunde</a>
        </li>
        <li <% if(request.getAttribute("p") != null) {
	        	   if(request.getAttribute("p").equals("Profilinfo")){%>
	        			class="active"   
	        	<% }
       			} %>>
        	<a href="/BlueCouch/User?p=Profilinfo" style="width:90px" > Profilinfo</a>
        </li>
        <li <% if(request.getAttribute("p") != null) {
	        	   if(request.getAttribute("p").equals("Kontaktinfo")){%>
	        			class="active"   
	        	<% }
       			} %>>
        	<a href="/BlueCouch/User?p=Kontaktinfo" style="width:100px" > Kontaktinfo</a>
        </li>
        <li>
        	<a href="/BlueCouch/User" style="width:90px" > MyCouch </a>
        </li>
      </ul>
      	  <form action="/BlueCouch/Logout" method="get">
	      <ul class="nav navbar-nav navbar-right">
	        <li>
	        	<!--  Logout mittels LogoutServlet - loescht entsprechende Cookies zur automatischen Anmeldung -->
	        	<a href="/BlueCouch/Logout">Logout</a>
	        </li>
	      </ul>
	      </form>
    </div>
  </div>
</nav>
<!--  Beginn der eigentlichen Seite zur Interaktion -->
<br>
<br>
<br>
<br>
<!--  Bild wird in jeder Ansicht angezeigt außer "Bild aendern" ansicht -->
<% if(request.getAttribute("p") == null || (!request.getAttribute("p").equals("Bild"))){ %>
<div class="row">
	<div class="col-md-2" align="center">
	<!--  Bild anzeigen, alt.: Unknown Bild anzeigen -->
   	  <% String benutzerbild = aw.getBenutzerbildFromUser((String)request.getAttribute("email"));
  	   if(benutzerbild != null){%>
	  		<a href="/BlueCouch/User?p=Bild"><img style="width:80%" class="img-rounded" src="<%=benutzerbild%>" alt="Picture"></a> 
    <% } else  { %>
	  		<a href="/BlueCouch/User?p=Bild"><img style="width:80%" class="img-rounded" src="pic/Unknown.png" alt="Unknown"></a>
  	<% } %>
  	<% if(request.getAttribute("p") != null){if(request.getAttribute("p").equals("Freunde")) {%>
  	<br>
  	<br>
  	<a href="/BlueCouch/User?p=Gruppe" class="btn btn-primary">Gruppen</a>
  	<%} if(request.getAttribute("p").equals("Gruppe")) {%>
  	<br>
  	<br>
  	<a href="/BlueCouch/User?p=Freunde" class="btn btn-primary">zurück</a>
  	<%} }%>
    </div>
    <div class="col-md-10">
    <!-- Gefundene User anzeigen und Freundschaftsanfrage versenden -->
    <% 
    if(request.getAttribute("p") == null){
      if(request.getAttribute("nutzersuche") != null) { 
       ArrayList<String> al = (ArrayList<String>) request.getAttribute("nutzersuche");
       ArrayList<String> id = (ArrayList<String>) request.getAttribute("emailID");
       for(int i = 0; i<al.size();i++){ %>
       		<div class="row">
       			<div class="col-md-2">
       			<br>
       			</div>
       			<div class="col-md-2">
       			<%String benutzerbild_freund = aw.getBenutzerbildFromUser(id.get(i)); %>
       			 	<%if(benutzerbild_freund != null) { %>
       			 		<!--  so vorhanden Benutzerbild des gefundenen Users anzeigen, sonst unknown -->
       			 		<img style="width:50%" class="img-rounded"  src="<%=benutzerbild_freund%>" alt="User.png">
       			 	<%} else { %>
       			 		<img style="width:50%"class="img-rounded" src="pic/Unknown.png" alt="Unknown">
       			    <%} %>
       			</div>
       			<div class="col-md-4">
       				<br>
       				<div class="well well-sm">
       					<%=al.get(i)%>
       				</div>
       			</div>
       			<%if(aw.istInEinerFreundesliste((String)request.getAttribute("email"), id.get(i))) {%>
       			<div class="col-md-4">
       			<br>
	    	    <form action="/BlueCouch/User" method="post"> 
	    	    <input type="hidden" name="freundadd" class="form-control" value="<%=id.get(i)%>">
	    	    <input type="submit" class="btn btn-primary" value="Freundschaftsanfrage versenden"> </form> <br>
	    	    </div>
	    	    <%} %>
	    	</div>
    <%} } }%>
    
    <!--  Profilinfo Anzeigen & aendern -->
    <%if(request.getAttribute("p") != null) {
    	if(request.getAttribute("p").equals("Profilinfo")){ 
      		%><%@ page import="java.text.*" %><% 
      	%>
      		<form class="form-horizontal" action="/BlueCouch/User" method="post">
			  <fieldset>
			  <%if(request.getAttribute("w") != null){
	  				  if(request.getAttribute("w").equals("error1")){%>
		  				  <div class="row">
		  				  	<div class="col-md-10" align="center">
				     			<div class="alert alert-dismissible alert-danger">
				  					<strong>Datumsformat ungültig! yyyy-MM-dd </strong>
								</div>
				     		</div>
		  				  </div>
	  				<% } } %>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Vorname
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputVorname" name="vorname" placeholder="Vorname" 
				      		<%String vorname = aw.getVorname((String) request.getAttribute("email"));%>
				      		<%if(vorname != null){%>value="<%=vorname%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Nachname
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputNachname" name="nachname" placeholder="Nachname" 
				      		<%String nachname = aw.getNachname((String) request.getAttribute("email")); %>
				      		<%if(nachname != null){%>value="<%=nachname%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Geburtsdatum
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputGeburtsdatum" name="geburtsdatum" placeholder="yyyy-mm-dd" 
				       		<%String bday = aw.getBday((String)request.getAttribute("email")); %>
				      		<%if(bday != null){%>value="<%=bday%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Hobbies
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputHobbies" name="hobbies" placeholder="Hobbies" 
				      		<%String hobbies = aw.getHobbies((String)request.getAttribute("email")); %>
				      		<%if(hobbies != null){%>value="<%=hobbies%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Geschlecht
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputGeschlecht" name="geschlecht" placeholder="Geschlecht" 
				      		<%String geschlecht = aw.getGeschlecht((String) request.getAttribute("email")); %>
				      		<%if(geschlecht != null){%>value="<%=geschlecht%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Religion
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputReligion" name="religion" placeholder="Religion" 
				      		<%String religion = aw.getReligion((String) request.getAttribute("email")); %>
				      		<%if(religion != null){%>value="<%=religion%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Interessen
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputInteressen" name="interessen" placeholder="Interessen" 
				      		<%String interessen = aw.getInteressen((String) request.getAttribute("email")); %>
				      		<%if(interessen != null){%>value="<%=interessen%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Familienstatus
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputFamilienstatus" name="familienstatus" placeholder="Familienstatus" 
				      		<%String familienstatus = aw.getFamilienstatus((String) request.getAttribute("email")); %>
				      		<%if(familienstatus != null){%>value="<%=familienstatus%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Sexuelle Orientierung
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputSexOrientierung" name="sexOrientierung" placeholder="Sexuelle Orientierung" 
				      		<%String sexOrientierung = aw.getSexOrientierung((String) request.getAttribute("email")); %>
				      		<%if(sexOrientierung != null){%>value="<%=sexOrientierung%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Politische Einstellung
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputPolEinstellung" name="polEinstellung" placeholder="Politische Einstellung" 
				      <%String polEinstellung = aw.getPolEinstellung((String) request.getAttribute("email")); %>
				      <%if(polEinstellung != null){%>value="<%=polEinstellung%>"<%}%>>
				      <br>
			    </div>
			  </div>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Zusatzangaben
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputZusatzangaben" name="zusatzangaben" placeholder="Zusatzangaben" 
				      		<%String zusatzangaben = aw.getZusatzangaben((String) request.getAttribute("email")); %>
				      		<%if(zusatzangaben != null){%>value="<%=zusatzangaben%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			   <div class="col-md-10">
			   		<div class="row">
						<div class="col-md-4" align="left">
							<button type="submit" class="btn btn-primary" name="profilinfoChange" style="width:100px">Speichern</button>
			     		</div>
			     		<div class="col-md-4">
			     		</div>
			     		<div class="col-md-4" align="right">
			     			 <a href="/BlueCouch/User?p=Profilinfo" class="btn btn-default">Abbrechen</a>
			     		</div>
					 </div> 
			    </div>
			  </div>
			  </fieldset>
			</form>
			<br>
			<br>
      <%}
      }%>
    <!--  Kontaktinfo Anzeigen & aendern -->
     <%if(request.getAttribute("p") != null) {
      	if(request.getAttribute("p").equals("Kontaktinfo")){ %>
      	<form class="form-horizontal" action="/BlueCouch/User" method="post">
			  <fieldset>
			  	<div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Adresse
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputAdresse" name="adresse" placeholder="Adresse" 
				      		<%String adresse = aw.getAdresse((String) request.getAttribute("email")); %>
				      		<%if(adresse != null){%>value="<%=adresse%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  	 <div class="row">
				  	 <div class="col-md-2">
				  	 	  <div class="well well-sm">
				  	 	  	Wohnort
			  	 	 </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputWohnort" name="wohnort" placeholder="Wohnort" 
				     		<%String wohnort = aw.getWohnort((String) request.getAttribute("email")); %>
				      		<%if(wohnort != null){%>value="<%=wohnort%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  	 <div class="row">
				  	 <div class="col-md-2">
				  	 	  <div class="well well-sm">
				  	 	  	Telefonnummer
				  	 	  </div>
				  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputTelefonnr" name="telefonnr" placeholder="Telefonnr" 
				      		<%String telnr = aw.getTelNr((String) request.getAttribute("email")); %>
				      		<%if(telnr != null){%>value="<%=telnr%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  	 <div class="row">
				  	 <div class="col-md-2">
				  	 	  <div class="well well-sm">
				  	 	  	Email
				  	 	  </div>
				  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputEmailK" name="emailK" placeholder="Email" 
				      		<%String e_mail = aw.getEMail((String) request.getAttribute("email")); %>
				      		<%if(e_mail != null){%>value="<%=e_mail%>"<%}%>>
				      		<br>
			    </div>
			 	</div>
			    <div class="row">
			  <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Account-Email
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputUser" name="user" placeholder="User" readonly
				      		<%String accmail = aw.getAccountEmail((String) request.getAttribute("email")); %>
				      		<%if(accmail != null){%>value="<%=accmail%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  <div class="row">
			   <div class="col-md-10">
			   		<div class="row">
						<div class="col-md-4" align="left">
							<button type="submit" class="btn btn-primary" name="kontaktDatenChange" style="width:100px">Speichern</button>
			     		</div>
			     		<div class="col-md-4">
			     		</div>
			     		<div class="col-md-4" align="right">
			     			 <a href="/BlueCouch/User?p=Kontaktinfo" class="btn btn-default">Abbrechen</a>
			     		</div>
					 </div> 
			    </div>
			  </div>
			  </fieldset>
			</form>
			<br>
			<br>
      <%}
      }%>

    <!--  Pinnwand Anzeigen & aendern -->
     <%if(request.getAttribute("p") != null) {
      	if(request.getAttribute("p").equals("Pinnwand")){ %>
      		<!-- Neuen Beitrag erstellen -->
      		<form class="form-horizontal" action="/BlueCouch/User" method="post">
  				<fieldset>
  				<%if(request.getAttribute("w") != null){
	  				  if(request.getAttribute("w").equals("error1")){%>
		  				  <div class="row">
		  				  	<div class="col-md-10" align="center">
				     			<div class="alert alert-dismissible alert-danger">
				  					<strong>Kein Beitragstext eingegeben!</strong>
								</div>
				     		</div>
		  				  </div>
	  				<%}
	  				  if(request.getAttribute("w").equals("error2")){%>
		  				  <div class="row">
		  				  	<div class="col-md-10" align="center">
				     			<div class="alert alert-dismissible alert-danger">
				  					<strong>Kein Titel eingegeben!</strong>
								</div>
				     		</div>
		  				  </div>
	  				<% } 
  				   } %>
		  			<div class ="row">
		  				<div class="col-md-10">
		  					<legend>Neuer Beitrag</legend>
		  				</div>
		  			</div>
	   				<div class="row">
	 					<div class="col-md-10">
		     			 <input type="text" class="form-control" id="inputBeiTitel" name="beiTitel" placeholder="Titel"><br>
	    				</div>
	      			</div>
	   				<div class="row">
	 					<div class="col-md-10">
		     				 <textarea class="form-control" rows="3" type="text" class="form-control" id="inputBeiText" name="beiText" placeholder="Betragstext"></textarea><br>
	    				</div>
	      			</div>	
	   				<div class="row">
						<div class="col-md-10" align="left">
							<button type="submit" class="btn btn-primary" name="neuerBeitrag">Beitrag speichern</button>
	     			</div>
					</div> 
  			</fieldset>
  			<br>
		<!-- Liste aller Beiträge -->
		</form>
      	<form class="form-horizontal" action="/BlueCouch/User" method="post">
  			<fieldset>
  			<div class ="row">
  				<div class="col-md-10">
  					<legend>Pinnwand  (<%=aw.anzahlBeitraege((String) request.getAttribute("email")) %>) </legend>
  				</div>
  			</div>
                <%ArrayList<String> pinnwandTitel = aw.getBeitraegsTitel((String) request.getAttribute("email")); %>
 				<%ArrayList<String> pinnwandInhalt = aw.getBeitraegsInhalt((String) request.getAttribute("email")); %>
 				<%ArrayList<Integer> pinnwandID = aw.getBeitraegsID((String) request.getAttribute("email")); %>
   				<% for(int i=pinnwandTitel.size()-1;i>=0;i--) { %>
						<div class="row">
		 					<div class="col-md-10">
		  						<div class="panel panel-primary">
		  							<div class="panel-heading">
		    							<h4 class="panel-title"><%= pinnwandTitel.get(i) %></h4>
		 							 </div>
		 							 <div class="panel-body">
		   								 <%= pinnwandInhalt.get(i) %>
		 							 </div>
		 							 <div align="right">
		 							 <button type="submit" class="btn btn-info btn-xs" name="beitragLoeschen" value="<%=pinnwandID.get(i)%>">Beitrag löschen</button>
		 							 </div>
								</div>
		  					</div>
		      			 </div>
 				 <%} %>
  			</fieldset>
		</form>
       <%}
      }%>
    <!--  Freunde Anzeigen & aendern -->
     <%if(request.getAttribute("p") != null) {
      	if(request.getAttribute("p").equals("Freunde")){ %>
      		<%ArrayList<String> fausgehend = aw.getFAnfragenAusgehend((String)request.getAttribute("email")); %>
      		<%if(fausgehend.size() != 0){%>
      		<!--  versendete Freundschaftsanfragen sehen und ggfs stornieren -->
	      		<div class="row">
	      			<div class="col-md-10">
	      				<legend>Versendete Freundschaftsanfragen (<%=fausgehend.size()%>)</legend>
	      			</div>
	      		</div>
	      			      		<%for(int i = 0; i<fausgehend.size(); i++){ %>
	      			<% String freundBild = aw.getBenutzerbildFromUser(fausgehend.get(i)); %>
		      		<div class="row">
		      			<div class="col-md-2">
		      				<% if(freundBild != null){%>  								   		
	  									<img style="width:80%" class="img-rounded" src="<%=freundBild%>" alt="Freund.png"> 
    								<% } else  { %>
	  									<img style="width:80%" class="img-rounded" src="pic/Unknown.png" alt="Unknown">
							<% } %>
		      			</div>
		      			<div class="col-md-4">
		      			<br>
		      				<div class="well well-sm">
		      				<%String freundVorname = aw.getVorname(fausgehend.get(i)); %>
		      				<%String freundNachname = aw.getNachname(fausgehend.get(i)); %>
		      					<%if(freundVorname != null){%><%=freundVorname %><%} else {%>Unbekannt<%}%>
		      					<%if(freundNachname != null){%><%=freundNachname %><%} else {%>Unbekannt<%}%>
		      				</div>
		      			</div>
		      			<div class="col-md-2">
		      			<br>
		      				<form action="/BlueCouch/User" method="post">
		      				<%String freundEmailID = aw.getAccountEmail(fausgehend.get(i)); %>
					   			<input type="hidden" name="storno" class="form-control" value="<%=freundEmailID%>">
					   			<input type="submit" class="btn btn-info" value="stornieren">
  						    </form>
  						</div>
		      		</div>
		      		<br>
	      		<%} %>
      		<%} %>
			<%ArrayList<String> feingehend = aw.getFAnfragenEingehend((String)request.getAttribute("email")); %>
      		<%if(feingehend.size() != 0){%>
	      		<div class="row">
	      		<!--  Ausstehende Freundschaftsanfragen sehen und antworten (annehmen / ablehnen) -->
	      			<div class="col-md-10">
	      				<legend>Ausstehende Freundschaftsanfragen (<%=feingehend.size()%>)</legend>
	      			</div>
	      		</div>
	      		<%for(int i = 0; i<feingehend.size(); i++){ %>
	      			<% String freund_bild = aw.getBenutzerbildFromUser(feingehend.get(i)); %>
		      		<div class="row">
		      			<div class="col-md-2">
		      				<% if(freund_bild != null){%>  								   		
	  									<img style="width:80%" class="img-rounded" src="<%=freund_bild%>" alt="Freund.png"> 
    								<% } else  { %>
	  									<img style="width:80%" class="img-rounded" src="pic/Unknown.png" alt="Unknown">
							<% } %>
		      			</div>
		      			<div class="col-md-4">
		      			<br>
		      				<div class="well well-sm">
		      				<%String freund_vorname = aw.getVorname(feingehend.get(i)); %>
		      				<%String freund_nachname = aw.getNachname(feingehend.get(i)); %>
		      					<%if(freund_vorname != null){%><%=freund_vorname%><%}else{%> Unbekannt<%}%>
		      					<%if(freund_nachname != null){%><%=freund_nachname %><%} else {%> Unbekannt<%}%>
		      				</div>
		      			</div>
		      			<div class="col-md-2" align="center">
		      			<br>
		      				<form action="/BlueCouch/User" method="post">
		      				<%String freund_emailID = aw.getAccountEmail(feingehend.get(i)); %>
					   			<input type="hidden" name="freundschaftja" class="form-control" value="<%=freund_emailID%>">
					   			<input type="submit" class="btn btn-info" value="annehmen">
  						    </form>
  						</div>
  						<div class="col-md-2" align="center">
  						<br>
  						    <form action="/BlueCouch/User" method="post">
					   			<input type="hidden" name="freundschaftnein" class="form-control" value="<%=freund_emailID%>">
					   			<input type="submit" class="btn btn-danger" value="ablehnen">
  						    </form>
  						</div>
		      		</div>
		      		<br>
	      		<%} %>
      		<%} %>
      		<div class="row">
      		<!--  Freunde sehen und ggfs loeschen bzw. deren Seite besuchen sowie sie zu Gruppen hinzufuegen -->
  				<div class="col-md-10">
  				<%ArrayList<String> freundeID = aw.getFreundeID((String) request.getAttribute("email")); %>
  					<legend>Freunde (<%=freundeID.size() %>) </legend>
  				</div>
  			</div>
  				<%if(freundeID.size() != 0) { for(int i = 0; i<freundeID.size(); i++) { %>
  						<div class="row">
  							<div class="col-md-2">
  							<%String freund_bild = aw.getBenutzerbildFromUser(freundeID.get(i)); %>
  								   <% if(freund_bild != null){%>  								   		
	  									<img style="width:80%" class="img-rounded" src="<%=freund_bild%>" alt="Freund.png"> 
    								<% } else  { %>
	  									<img style="width:80%" class="img-rounded" src="pic/Unknown.png" alt="Unknown">
  									<% } %>
  							</div>
  							<div class="col-md-4">
						   		<div class="well well-sm">
						   		<%String freund_vorname = aw.getVorname(freundeID.get(i)); %>
						   		<%String freund_nachname = aw.getNachname(freundeID.get(i)); %>
						   			<%if(freund_vorname != null){%><%= freund_vorname%><%}else{%>Unbekannt<%}%>  
						   			<%if(freund_nachname != null){%><%= freund_nachname%><%}else{%>Unbekannt<%}%>
						   		</div>
						   		<form action="/BlueCouch/User" method="post">
							   		<div class="col-md-4" align="left">
							   			<button type="submit" name="gruppeAendern" value="<%=freundeID.get(i)%>" class="btn btn-default">Gruppe</button>
							   		</div>
							   		<div class="col-md-8">
							   			<select class="form-control" id="select" name="gruppeaendern">
								          <option <% if(aw.istGruppe((String)request.getAttribute("email"),freundeID.get(i)) == false){ %>
								         	 selected="selected"
								          <% } %>
								          >None</option>
								          <%ArrayList<String> gruppen = aw.getGruppen((String) request.getAttribute("email")); %>
								          <% for(int k = 0; k<gruppen.size();k++){ %>
									          <option <% if(aw.istInGruppe((String)request.getAttribute("email") ,freundeID.get(i), gruppen.get(k)) == true){ %>
									          	selected="selected"
									          <%} %>
									          ><%=gruppen.get(k) %></option>
								          <%} %>
								        </select>
							   		</div>
						   		</form>
						   		</div>
  							<div class="col-md-2" align="center">
  							<br>
						   		<form action="/BlueCouch/User" method="post">
						   			<input type="hidden" name="freundesseite" class="form-control" value="<%= freundeID.get(i)%>">
						   			<input type="submit" class="btn btn-info" value="ansehen">
						   		</form>
  							</div>
  							<div class="col-md-2" align="center">
  							<br>
						   		<form action="/BlueCouch/User" method="post">
						   			<input type="hidden" name="freunddel" class="form-control" value="<%= freundeID.get(i)%>">
						   			<input type="submit" class="btn btn-danger tn-xs" value="entfernen">
						   		</form>
						   	</div>
  						</div>
  						<br>
  				  <%} 
  				  }%>
  	  <!--  Gruppe aendern -->
      <%} if(request.getAttribute("p").equals("Gruppe")){%>
			<%if(request.getAttribute("m") != null) { if(request.getAttribute("m").equals("errordouble")) {%>
			<div class="row">
				<div class="alert alert-dismissible alert-danger">
				  <strong>Gruppe mit gleichem Namen bereits vorhanden!</strong>
				</div>
			</div>
			<%} if(request.getAttribute("m").equals("errortitel")) {%>
			<div class="row">
				<div class="alert alert-dismissible alert-danger">
				  <strong>Gruppe benötigt einen Namen!</strong>
				</div>
			</div>	
			<%}} %>
    	  	<div class="row">
    	  		<div class="col-md-10">
    	  			<legend>Neue Gruppe erstellen</legend>
    	  		</div>
   	  		</div>
   	  		<form class="form-horizontal" action="/BlueCouch/User" method="post">
	   	  		<fieldset>
				 	<div class="row">
	  		 			<div class="col-md-2" align="center">
		     	 			<label>Name der Gruppe</label>
		     	 		</div>
		     	 		<div class="col-md-8">
		     	 			<input type="text" name="nameGruppe" class="form-control" placeholder="Name">
		 		 	 	</div>
	   	  			</div>
	   	  			<br>
	   	  			<div class="row">
   	  					<div class="col-md-2">
   	  					<br>
		   	  				<label> Sichtbarkeiten </label>
	   	  				</div>
	   	  				<div class="col-md-2" align="left">
		   	  				<label> Freundesliste</label>
	   	  				</div>
	   	  				<div class="col-md-2" align="left">
		   	  				<input type="checkbox" name="sichtFreundesliste">
	   	  				</div>
	   	  				<div class="col-md-2" align="right">
		   	  				<label> Pinnwand </label>
	   	  				</div>
	   	  				<div class="col-md-2" align="right">
		   	  				<input type="checkbox" name="sichtPinnwand">
	   	  				</div>
	   	  			</div>
	   	  			<div class="row">
	   	  				<div class="col-md-2">
	   	  				</div>
	   	  				<div class="col-md-2" align="left">
		   	  				<label> Kontaktdaten </label>
	   	  				</div>
	   	  				<div class="col-md-2" align="left">
		   	  				<input type="checkbox" name="sichtKontaktdaten">
	   	  				</div>
	   	  				<div class="col-md-2" align="right">
		   	  				<label> Profilinfo </label>
	   	  				</div>
	   	  				<div class="col-md-2" align="right">
		   	  				<input type="checkbox" name="sichtProfilinfo">
	   	  				</div>
	   	  			</div>
	   	  			<br>
	   	  			<div class="row">
	   	  				<div class="col-md-10" align="center">
	   	  					<button type="submit" name="gruppeErstellen" class="btn btn-primary">Speichern</button>
	   	  				</div>
	   	  			</div>
	   	  		</fieldset>
   	  		</form>
   	  		<%ArrayList<String> gruppen = aw.getGruppen((String)request.getAttribute("email")); %>
   	  		<%if(gruppen.size() != 0) {%>
   	  		<div class="row">
    	  		<div class="col-md-10">
    	  			<legend>Gruppen (<%= gruppen.size()%>)</legend>
    	  		</div>
   	  		</div>
   	  		<div class="row"> 	  			
   	  		</div>
  	  		<%for(int i = 0; i<gruppen.size();i++){ %>
  	  			<div class="row">
  	  				<div class="col-md-5">
						<ul class="list-group">
							<li class="list-group-item active">
			 					 Name:
							</li>
							<li class="list-group-item">
							  <%= gruppen.get(i)%>
							</li>
		  							<li class="list-group-item active">
							  Sichtbarkeiten:
							</li>
		  							<li class="list-group-item">
							  <span class="badge"><input type="checkbox" 
							  <%boolean sichtFlist = aw.gruppeSichtFreundesliste((String)request.getAttribute("email"), gruppen.get(i));%>
							  <%if(sichtFlist) {%>
							  		checked
							  <%} %> onclick="return false"/></span>
							  Freundesliste
							</li>
							<li class="list-group-item">
							   <span class="badge"><input type="checkbox" 
							    <%boolean sichtPW = aw.gruppeSichtPinnwand((String)request.getAttribute("email"), gruppen.get(i));%>
							  <%if(sichtPW) {%>
							  		checked
							  <%} %> onclick="return false"/></span>
							  Pinnwand
							</li>
							<li class="list-group-item">
							  <span class="badge"><input type="checkbox" 
							   <%boolean sichtKDaten = aw.gruppeSichtKontaktdaten((String)request.getAttribute("email"), gruppen.get(i));%>
							  <%if(sichtKDaten) {%>
							  		checked
							  <%} %> onclick="return false"/></span>
							  Kontaktinfo
							</li>
							<li class="list-group-item">
							   <span class="badge"><input type="checkbox" 
							    <%boolean sichtPInfo = aw.gruppeSichtProfilinfo((String)request.getAttribute("email"), gruppen.get(i));%>
							  <%if(sichtPInfo) {%>
							  		checked
							  <%} %> onclick="return false"/></span>
							  Profilinfo
							</li>
							<li class="list-group-item active">
							  	<form action="/BlueCouch/User" method="post">
					   			<input type="hidden" name="gruppedel" class="form-control" value="<%= gruppen.get(i)%>">
					   			<input type="submit" class="btn btn-danger tn-xs" value="entfernen">
					   			</form>
							</li>
						</ul>
 					</div>
 			<%if(gruppen.size() > i+1){ i++; %>
  	  				<div class="col-md-5">
						<ul class="list-group">
							<li class="list-group-item active">
			 					 Name:
							</li>
							<li class="list-group-item">
							  <%= gruppen.get(i)%>
							</li>
		  							<li class="list-group-item active">
							  Sichtbarkeiten:
							</li>
		  							<li class="list-group-item">
							  <span class="badge"><input type="checkbox" 
							   <%boolean sichtFlist2 = aw.gruppeSichtFreundesliste((String)request.getAttribute("email"), gruppen.get(i));%>
							  <%if(sichtFlist2) {%>
							  		checked
							  <%} %> onclick="return false"/></span>
							  Freundesliste
							</li>
							<li class="list-group-item">
							   <span class="badge"><input type="checkbox" 
							   <%boolean sichtPW2 = aw.gruppeSichtPinnwand((String)request.getAttribute("email"), gruppen.get(i));%>
							  <%if(sichtPW2) {%>
							  		checked
							  <%} %> onclick="return false"/></span>
							  Pinnwand
							</li>
							<li class="list-group-item">
							  <span class="badge"><input type="checkbox" 
							  <%boolean sichtKDaten2 = aw.gruppeSichtKontaktdaten((String)request.getAttribute("email"), gruppen.get(i));%>
							  <%if(sichtKDaten2) {%>
							  		checked
							  <%} %> onclick="return false"/></span>
							  Kontaktinfo
							</li>
							<li class="list-group-item">
							   <span class="badge"><input type="checkbox" 
							<%boolean sichtPInfo2 = aw.gruppeSichtProfilinfo((String)request.getAttribute("email"), gruppen.get(i));%>
							  <%if(sichtPInfo2) {%>
							  		checked
							  <%} %> onclick="return false"/></span>
							  Profilinfo
							</li>
							<li class="list-group-item active">
							  	<form action="/BlueCouch/User" method="post">
					   			<input type="hidden" name="gruppedel" class="form-control" value="<%= gruppen.get(i)%>">
					   			<input type="submit" class="btn btn-danger tn-xs" value="entfernen">
					   			</form>
							</li>
						</ul>
 					</div>
 			<% }%>
  				</div>
  	  		<%} %>
    	  	<%} %>
      <% } }%>
	</div>
</div>
<!--  Bild aendern NICHT IMPLEMENTIERT -->
<% } %>
<% if(request.getAttribute("p") != null) {
	if(request.getAttribute("p").equals("Bild")){ %>
		
	<div class="col-md-4" align="center">
	</div>
	<div class="col-md-4" align="center">
		<div class = "row">
			            <h3> Wähle ein Bild zum Uploaden aus: </h3>
			            <br><br><br>
		</div>
		<form action="/BlueCouch/Upload" method="post" enctype="multipart/form-data">
		<div class = "row" align = "center">
            <input style="width: 400px;" type="file" name="file" />
        </div>
        <br>
        <div class = "row" align = "center">
			    <button name="bild" class="btn btn-primary" type="submit">Upload</button>
		</div>
		</form>
		<br><br>
<%if(request.getAttribute("s") != null) { %> 
<%if(request.getAttribute("s").equals("1")){ %>
		<div class = "row" align = "center">
			<div class="alert alert-dismissible alert-success">
			  	<strong>Hochladen erfolgreich</strong>
			</div>
		</div>
<%}if(request.getAttribute("s").equals("2")){ %>
		<div class = "row" align = "center">
			<div class="alert alert-dismissible alert-danger">
			  	<strong>Fehler beim Hochladen. Achten Sie auf ein gueltiges Dateiformat (Bilddatei)</strong>
			</div>
		</div>
<%}} %>
	</div>
	<div class="col-md-4" align="center">
	</div>
<%  } } %>

</body>
</html>