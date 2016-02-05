package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import benutzer.management.AnwenderManagement;

/**
 *Die Klasse Login ("LoginServlet") ermoeglicht das Einlogen.<br>
 *falls die Email-Adresse als Cookie gespeichert ist, ist keine Login erforderlich - Es erfolgte eine automatische Weiterleitung zur Startseite des Anwenders (anderfalls erfoglt diese Wietrleritung nach erfolgreichem Login).<br>
 *
 *@author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raoul
 *@version 2015/12/09
 * */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * AnwenderManagement aw um Methoden der Klassen AnwenderManagement zur Verfuegung zu stellen
	 */
	private static AnwenderManagement aw = new AnwenderManagement();

	
	/** 
	 * liest aus, ob die Email-Adresse als Cookie gespeichert ist. <br>
	 * falls sie gespeichert ist, wird der User automatisch auf seine Startseite weitergeleitet.<br>
	 * falls nicht, kann er sich einlogen ("Login.jsp").<br>
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
			request.getRequestDispatcher("login.jsp").include(request, response);
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
	*  Ueberprueft (Inhalt / Vollstaendigkeit)  die Eingaben und passt gegebenfalls "login.jsp" mit entsprechenden Fehlermeldungen an.  <br>
	*  sind alle Felder ausgefuellt werden die Zugangsdaten ueberprueft (Ergebnis: Fehlermeldung oder erfoglreiches Login).<br>
	*  nach dem erfolgreichen Login erfolgt eine automatische Weiterleitung zur Startseite des entsprechenden Anwendertyps sowie eine Speicherung der EMailAdresse fuer 24h in den Cookies.<br>
	*  
	*  @param request Anfrage des Benutzers
	*  @param response Antwort an den Benutzer
	*  @throws ServletException
	*  @throws IOException
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("eMail").equals("")){
			request.setAttribute("Error", "1");
			request.getRequestDispatcher("login.jsp").include(request, response);
			return;
		}
		if(request.getParameter("password").equals("")){
			request.setAttribute("Error", "2");
			request.getRequestDispatcher("login.jsp").include(request, response);
			return;
		}
		if(aw.login(request.getParameter("eMail"), request.getParameter("password"))==null){
			request.setAttribute("Error", "3");
			request.getRequestDispatcher("login.jsp").include(request, response);
			return;
		}
		
		Cookie cookie = new Cookie("email",(String) request.getParameter("eMail"));
		cookie.setMaxAge(60*60*24); 
		response.addCookie(cookie);
		if(aw.getTyp(cookie.getValue()).equals("Admin")){
			response.sendRedirect("/BlueCouch/Admin");
			return;
		}
		
		if(aw.getTyp(cookie.getValue()).equals("Forscher")){
			response.sendRedirect("/BlueCouch/Forscher");
			return;
		}
		
		if(aw.getTyp(cookie.getValue()).equals("User")){
			response.sendRedirect("/BlueCouch/User");
			return;
		}
		
		
	}

}
