package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import benutzer.management.*;


/**
 * 
 *Die Klasse Register ("RegisterServlet") ermoeglicht das Registrieren eines neune Anwenders (User, Admin, Forscher).<br>
 *Ist die EmailAdresse (= eindeutige AnwederID) bereits vorhanden, ist eine Registrierung (mit dieser EmailAdresse) nicht moeglich.<br>
 *Nach der Registrierung erfolgt eine automatische Weiterleitung zur Startseite des entsprechenden Anwendertyps.
 *
 *@author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raoul
 *@version 2015/12/09
 * */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * AnwenderManagement aw um Methoden der Klassen AnwenderManagement zur Verfuegung zu stellen
	 */
	private static AnwenderManagement aw = new AnwenderManagement();

	/** 
	 * liest aus, ob die EmailAdresse als Cookie gespeichert ist. <br>
	 * falls sie gespeichert ist, wird der User automatisch auf seine Startseite weitergeleitet.<br>
	 * falls nicht, kann er sich registrieren ("register.jsp").<br>
	 * 
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 * */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = null;
		if(request.getCookies() != null){
			for(int i = 0; i<request.getCookies().length;i++){
				if(request.getCookies()[i] != null){
					if(request.getCookies()[i].getName().equals("email")){
						cookie = request.getCookies()[i];
					}
				}
			}
		}
		if(cookie == null) {
			{request.getRequestDispatcher("register.jsp").include(request, response);
			response.setContentType("text/html");}	// some browser need this (not all)
		} else if(aw.getTyp(cookie.getValue()).equals("User")){
			response.sendRedirect("/BlueCouch/User");
			return;
		} else if(aw.getTyp(cookie.getValue()).equals("Admin")){
			response.sendRedirect("/BlueCouch/Admin");
			return;
		} else if(aw.getTyp(cookie.getValue()).equals("Forscher")){
			response.sendRedirect("/BlueCouch/Forscher");
			return;
		}
	}

	 
	 
	 /** 
	  * Ueberprueft die die Eingaben (Inhalt / Vollstaendigkeit) und passt gegebenfalls "register.jsp" mit entsprechenden Fehlermeldungen an.  <br>
	 *  sind alle Felder ausgefuellt und ist die EMail nocht nicht vergeben wird ein neuer Anwender angelegt und persistent gespeichert.<br>
	 *  nach der erfolgreichen Registrierung erfolgt eine automatische Weiterleitung zur Startseite des entsprechenden Anwendertyps sowie eine Speicherung der EMailAdresse fuer 24h in den Cookies.<br>
	 *  
	 *  @param request Anfrage des Benutzers
	 *  @param response Antwort an den Benutzer
	 *  @throws ServletException
	 *  @throws IOException
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("eMail").equals("")){
			request.setAttribute("Error", "1");
			request.getRequestDispatcher("register.jsp").include(request, response);
			return;
		}
		
		if(request.getParameter("password").equals("")){
			request.setAttribute("Error", "2");
			request.getRequestDispatcher("register.jsp").include(request, response);
			return;
		}
		
		if(request.getParameter("typ") == null){
			request.setAttribute("Error", "3");
			request.getRequestDispatcher("register.jsp").include(request, response);
			return;
		}
		
		// Fehlermeldung falls User schon vorhanden
		if(aw.registrieren(request.getParameter("eMail"), request.getParameter("password"), request.getParameter("typ")) == null){
			request.setAttribute("Error", "4");
			request.getRequestDispatcher("register.jsp").include(request, response);
			return;
		}
		
		Cookie cookie = new Cookie("email",(String) request.getParameter("eMail"));
		cookie.setMaxAge(60*60*24); 
		response.addCookie(cookie);
		if(request.getParameter("typ").equals("admin")){
			
			response.sendRedirect("/BlueCouch/Admin");
			return;
		}
		
		if(request.getParameter("typ").equals("forscher")){
	
			response.sendRedirect("/BlueCouch/Forscher");
			return;
		}
		
		if(request.getParameter("typ").equals("user")){
			
			response.sendRedirect("/BlueCouch/User");
			return;
		}
	}

}
