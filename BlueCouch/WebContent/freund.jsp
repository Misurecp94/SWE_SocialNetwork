<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- freund.jsp wird dem Benutzer bei Betrachten einer User-Seite eines befreundeten Benutzers praesentiert
Die Tatsaechlich angezeigten Inhalte definieren sich durch die Zugehoerigkeit in einer Gruppe mit entsprechenden
Berechtigungen. -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<title>Freund</title>

</head>
<body>
<%@ page import="benutzer.management.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.File" %>
<%@ page import="java.text.*" %>
<% AnwenderManagement aw = new AnwenderManagement(); %>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header" align="center">
      <a class="navbar-brand" style="width:250px"><%=request.getAttribute("FREUNDseite")%></a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" align="center" aria-expanded="false" style="height: 1px;">
      <ul class="nav navbar-nav">
        <li>
           	<form class="navbar-form navbar-left" action="/BlueCouch/User" method="post">
        		<div class="form-group" style="width: 150px">
         			 <input type="text" name="usersuche" class="form-control" placeholder="SearchUser" >
        		</div>
       			 <button type="submit" class="btn btn-default">Suchen</button>
    			</form>
        </li>
        <li <% if(request.getAttribute("p") != null) {
	        	   if(request.getAttribute("p").equals("Pinnwand")){%>
	        			class="active"   
	        	<% }
       			} %>>
        	<a href="/BlueCouch/Freund?p=Pinnwand" style="width:90px"> Pinnwand </a>
        </li>
        <li <% if(request.getAttribute("p") != null) {
	        	   if(request.getAttribute("p").equals("Freunde") || request.getAttribute("p").equals("Gruppe")){%>
	        			class="active"   
	        	<% }
       			} %>>
        	<a href="/BlueCouch/Freund?p=Freunde" style="width:90px" > Freunde</a>
        </li>
        <li <% if(request.getAttribute("p") != null) {
	        	   if(request.getAttribute("p").equals("Profilinfo")){%>
	        			class="active"   
	        	<% }
       			} %>>
        	<a href="/BlueCouch/Freund?p=Profilinfo" style="width:90px" > Profilinfo</a>
        </li>
        <li <% if(request.getAttribute("p") != null) {
	        	   if(request.getAttribute("p").equals("Kontaktinfo")){%>
	        			class="active"   
	        	<% }
       			} %>>
        	<a href="/BlueCouch/Freund?p=Kontaktinfo" style="width:100px" > Kontaktinfo</a>
        </li>
        <li>
        <!-- Leitet User wieder auf seine eigene Seite zurueck -->
        	<a href="/BlueCouch/User" style="width:90px" > MyCouch </a>
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
<br>
<% if(request.getAttribute("p") == null || (!request.getAttribute("p").equals("Bild"))){ %>
<div class="row">
	<div class="col-md-2" align="center">
	<!--  Bild anzeigen-->
   	<%  String freundBild = aw.getBenutzerbildFromUser((String)request.getAttribute("FREUNDseite"));
  	   if(freundBild != null){%>
	  		<img style="width:80%" class="img-rounded" src="<%=freundBild%>" alt="BlueCouch.png">
	  		<br/>
	  		<br/>
	  		<div class="well well-sm">
	  			Freundschaftsmodus
	  		</div>
    <% } else  { %>
	  		<img style="width:80%" class="img-rounded" src="pic/Unknown.png" alt="Unknown">
	  		<br/>
	  		<br/>
	  		<div class="well well-sm">
	  			Freundschaftsmodus
	  		</div>
  	<% } %>
  	<% if(request.getAttribute("p") != null){ %>

    </div>
    <div class="col-md-10"> 
    
    <!--  Profilinfo Anzeigen  -->
    <%if(request.getAttribute("p") != null) {
    	if(request.getAttribute("p").equals("Profilinfo")){ 
      		%>
      		<form class="form-horizontal">
			  <fieldset>
			
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Vorname
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputVorname" name="vorname"  readonly
				      		<%String vorname = aw.getVorname((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputNachname" name="nachname"  readonly
				      		<%String nachname = aw.getNachname((String) request.getAttribute("FREUNDseite"));%>
				      		<%if(nachname != null){%>value="<%=nachname%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  	<%if(request.getAttribute("pinfo") != null){ %>
			  <div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Geburtsdatum
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputGeburtsdatum" name="geburtsdatum"  readonly
				      		<%String bday = aw.getBday((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputHobbies" name="hobbies"  readonly
				      		<%String hobbies = aw.getHobbies((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputGeschlecht" name="geschlecht"  readonly
				      		<%String geschlecht = aw.getGeschlecht((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputReligion" name="religion"  readonly
				      		<%String religion = aw.getReligion((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputInteressen" name="interessen"  readonly
				      		<%String interessen = aw.getInteressen((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputFamilienstatus" name="familienstatus"  readonly
				      		<%String familienstatus = aw.getFamilienstatus((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputSexOrientierung" name="sexOrientierung"  readonly
				      		<%String sexOrientierung = aw.getSexOrientierung((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputPolEinstellung" name="polEinstellung"  readonly
				     <%String polEinstellung = aw.getPolEinstellung((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputZusatzangaben" name="zusatzangaben"  readonly
				      	 <%String zusatzangaben = aw.getZusatzangaben((String) request.getAttribute("FREUNDseite"));%>
				      		<%if(zusatzangaben != null){%>value="<%=zusatzangaben%>"<%}%>>
				      		<br>
			    </div>
			  </div>
			  
			   </fieldset>
			</form>
			<br>
			<br>
      <%}
      }%>
      <%} %>
    <!--  Kontaktinfo Anzeigen  -->
 	 <%if(request.getAttribute("kinfo") != null){ %>
     <%if(request.getAttribute("p") != null) {
      	if(request.getAttribute("p").equals("Kontaktinfo")){ %>
      	<form class="form-horizontal" >
			  <fieldset>
			  	<div class="row">
			  	 <div class="col-md-2">
			  	 	  <div class="well well-sm">
			  	 	  	Adresse
			  	 	  </div>
			  	 </div>
			  	 <div class="col-md-8">
				      <input type="text" class="form-control" id="inputAdresse" name="adresse"  readonly
				      		<%String adresse = aw.getAdresse((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputWohnort" name="wohnort"  readonly
				      		<%String wohnort = aw.getWohnort((String) request.getAttribute("FREUNDseite"));%>
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
				      <input type="text" class="form-control" id="inputTelefonnr" name="telefonnr"  readonly
				      		<%String telNr = aw.getTelNr((String) request.getAttribute("FREUNDseite"));%>
				      		<%if(telNr != null){%>value="<%=telNr%>"<%}%>>
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
				      <input type="text" class="form-control" id="inputEmailK" name="emailK"  readonly
				      		<%String e_mail = aw.getEMail((String) request.getAttribute("FREUNDseite"));%>
				      		<%if(e_mail != null){%>value="<%=e_mail%>"<%}%>>
				      		<br>
			    </div>
			 	</div>
			 
			  </fieldset>
			</form>
			<br>
			<br>
      <%}
      }%>
	  <%} %>
    <!--  Pinnwand Anzeigen  -->
    <%if (request.getAttribute("pw") != null) { %>
     <%if(request.getAttribute("p") != null) {
      	if(request.getAttribute("p").equals("Pinnwand")){ %>
      		
		<!-- Liste aller Beitraege -->
		</form>
      	<form class="form-horizontal" action="/BlueCouch/Freund" method="post">
  			<fieldset>
  			<div class ="row">
  				<div class="col-md-10">
  				<%ArrayList<String> pinnwandTitel = aw.getBeitraegsTitel((String) request.getAttribute("FREUNDseite")); %>
 				<%ArrayList<String> pinnwandInhalt = aw.getBeitraegsInhalt((String) request.getAttribute("FREUNDseite")); %>
 				<%ArrayList<Integer> pinnwandID = aw.getBeitraegsID((String) request.getAttribute("FREUNDseite")); %>
 				<%ArrayList<Boolean> pinnwandGemeldet = aw.getBeitragsMeldung((String) request.getAttribute("FREUNDseite")); %>
  					<legend>Pinnwand  (<%=pinnwandTitel.size()%>) </legend>
  				</div>
  			</div>
   				<%  for(int i=pinnwandTitel.size()-1;i>=0;i--) { %>
						<div class="row">
		 					<div class="col-md-10">
		  						<div class="panel panel-primary">
		  							<div class="panel-heading">
		    							<h4 class="panel-title"><%= pinnwandTitel.get(i) %></h4>
		 							 </div>
		 							 <div class="panel-body">
		   								 <%= pinnwandInhalt.get(i) %>
		 							 </div>
		 							 <% if(pinnwandGemeldet.get(i) == false){ %>
		 							 <div align="right">
		 							 <button type="submit" class="btn btn-danger btn-xs" name="beitragMelden" value="<%=pinnwandID.get(i)%>">Beitrag melden</button>
		 							 </div>
		 							 <%} else { %>
		 							 <div align="right">
		 							 <button class="btn btn-danger btn-xs" >gemeldet</button>
		 							 </div>
		 							 <% }%>
								</div>
		  					</div>
		      			 </div>
 				 <%} %>
  			</fieldset>
		</form>
       <%}
      }%>
    <%} %>
    
    <!--  Freunde Anzeigen  -->
    <%if(request.getAttribute("flist") != null) {%>
     <%if(request.getAttribute("p") != null) {
      	if(request.getAttribute("p").equals("Freunde")){ %>
      		
      		<div class="row">
      		<!--  Freunde sehen -->
  				<div class="col-md-10">
  				<%ArrayList<String> freundeID = aw.getFreundeID((String) request.getAttribute("FREUNDseite")); %>
  				<%ArrayList<String> meinefreundeID = aw.getFreundeID((String) request.getAttribute("email")); %>
  					<legend>Freunde (<%=freundeID.size()%>) </legend>
  				</div>
  			</div>
  				<%if(freundeID.size() != 0) { for(int i = 0; i<freundeID.size(); i++) { %>
  						<div class="row">
  							<div class="col-md-2">
  							<%String freundBild2 = aw.getBenutzerbildFromUser(freundeID.get(i)); %>
  								   <% if(freundBild2 != null){%>  								   		
	  									<img style="width:80%" class="img-rounded" src="<%=freundBild2%>" alt="Freund.png"> 
    								<% } else  { %>
	  									<img style="width:80%" class="img-rounded" src="pic/Unknown.png" alt="Unknown">
  									<% } %>
  							</div>
  							<div class="col-md-4">
  							<br>
						   		<div class="well well-sm">
						   		<%String freund_vorname = aw.getVorname(freundeID.get(i)); %>
						   		<%String freund_nachname = aw.getNachname(freundeID.get(i)); %>
						   			<%if(freund_vorname != null){%><%=freund_vorname%><%}else{%>Unbekannt<%}%>  
						   			<%if(freund_nachname != null){%><%=freund_nachname%><%}else{%>Unbekannt<%}%>
						   		</div>
  							</div>
  							<%String emailID = aw.getAccountEmail(freundeID.get(i)); %>
  							<%if(!(emailID.equals(request.getAttribute("email")))) {%>
  							<%if((aw.istInEinerFreundesliste((String)request.getAttribute("email"), emailID))) {
       								boolean helpfriend = true;
       								for(int k = 0; k<meinefreundeID.size();k++){
       								if(meinefreundeID.get(k).equals(freundeID.get(i))){
       									helpfriend = false; } }
       								if(helpfriend){%>
  							<div class="col-md-2" align="center">
  							<br>
						   		<form action="/BlueCouch/User" method="post">
						   			<input type="hidden" name="freundadd" class="form-control" value="<%=freundeID.get(i)%>">
						   			<input type="submit" class="btn btn-info" value="Freundschaftsanfrage versenden">
						   		</form>
  							</div>
  							<%}}} %>
  						</div>
  						<br>
  				  <%} 
  				  }%>
  	 		<%} %>
    	  	<%} %>
      <% } }%>
      <%} %>
	</div>
</div>

</body>
</html>