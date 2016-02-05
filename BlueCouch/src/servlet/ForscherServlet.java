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
 * Die Klasse "ForscherServlet" ueberprueft ob die Berechtigung als Forscher vorhanden ist (andernfalls kommt eine entsprechende Fehlermeldung<br>
 * Klasse entscheidet anhand Parameter, welche Ausgabe der Endanwender erhaelt.<br>
 * @see servlet.UserServlet
 * @see servlet.AdminServlet
 * @see servlet.Login
 * @see servlet.Register
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf
 * @version 2015/12/09
 */
@WebServlet("/Forscher")
public class ForscherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static AnwenderManagement aw = new AnwenderManagement();

	/** 
	 * Methode doGet ueberprueft Cookies und leitet Benutzer ggfs zur forscher.jsp weiter <br>
	 * 
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
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
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		if(!(aw.getTyp(cookie.getValue()).equals("Forscher"))){
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		request.setAttribute("email", cookie.getValue());
		request.getRequestDispatcher("forscher.jsp").include(request, response);
		
	}

	/**
	 * POST Methode des Servers, welche entsprechende Eingaben des Benutzers erhaelt (uebermittelt durch forscher.jsp) und die Antworten weiterleitet (in Form von RequestAttributen)
	 * 
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		if( request.getParameter("alleBerechnen")!=null){
			request.setAttribute("FreundeUser", aw.durchschnittlicheFreunde(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
			request.setAttribute("FreundeMonat", aw.FreundeMonat(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
			request.setAttribute("FreundeGesamt", aw.FreundeGesamt(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
			request.setAttribute("BeitragUser", aw.durchschnittlicheBeitraege(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
			request.setAttribute("BeitragMonat", aw.beitraegeMonat(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
			request.setAttribute("UserSperren", aw.anzahl_userSperren(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
			request.setAttribute("email", cookie.getValue());
			request.getRequestDispatcher("forscher.jsp").include(request, response);
			return;
		}
		
		
		if( request.getParameter("cbxFreundeUser")!=null){
			request.setAttribute("FreundeUser", aw.durchschnittlicheFreunde(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
		}
		if( request.getParameter("cbxFreundeMonat")!=null){
			request.setAttribute("FreundeMonat", aw.FreundeMonat(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
		}
		if( request.getParameter("cbxFreundeGesamt")!=null){
			request.setAttribute("FreundeGesamt", aw.FreundeGesamt(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
		}
		if( request.getParameter("cbxBeitragUser")!=null){
			request.setAttribute("BeitragUser", aw.durchschnittlicheBeitraege(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
		}
		if( request.getParameter("cbxBeitragMonat")!=null){
			request.setAttribute("BeitragMonat", aw.beitraegeMonat(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
		}
		if( request.getParameter("cbxUserSperren")!=null){
			request.setAttribute("UserSperren", aw.anzahl_userSperren(aw.getAnwenderbyID(cookie.getValue()).getEmailID()));
		}
		
		request.setAttribute("email", cookie.getValue());		
		request.getRequestDispatcher("forscher.jsp").include(request, response);
	}

}
