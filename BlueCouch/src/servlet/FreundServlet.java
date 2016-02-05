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
 * Klasse FreundServlet stellt Hauptservlet bei der Betrachtung der Seite eines anderen Users dar.
 * Zeigt dem Benutzer die entsprechenden Seiten, bzw. ueberprueft welche Berechtigungen der Benutzer beim Betrachten
 * der UserSeite des Freundes besitzt, und setzt entsprechende RequestAttribute.
 * 
 * @author Patrik Misurec, Raphael Kolhaupt, Christian Pfneisl, Mohamed Raouf
 * @version 2015/12/09
 */
@WebServlet("/Freund")
public class FreundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * AnwenderManagement aw vom Typ AnwenderManagement bietet die Moeglichkeit Methoden der Klasse
	 * AnwenderManagement anzuwenden.
	 */
    private static AnwenderManagement aw = new AnwenderManagement();

    /**
     * Post Methode des FreundServlets ueberperueft zuerst die Cookies auf Berechtigung. Je nach Erfuellung dieser wird dem User
     * entweder die "forbidden.jsp" Seite oder die "freund.jsp" Seite angezeigt. Kuemmert sich weiters um das Melden von Beitraegen
     * mittels Werten aus "freund.jsp" uebergeben.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = null;
		Cookie cookiefreund = null;
		if(request.getCookies() != null){
			for(int i = 0; i<request.getCookies().length;i++){
				if(request.getCookies()[i] != null){
					if(request.getCookies()[i].getName().equals("email")){
						cookie = request.getCookies()[i];
					}
					if(request.getCookies()[i].getName().equals("FREUNDseite")){
						cookiefreund = request.getCookies()[i];
					}
				}
			}
		}
		if(cookie == null) {
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		if(!(aw.getTyp(cookie.getValue()).equals("User"))){
			cookie.setMaxAge(0);
			if(!(aw.getTyp(cookiefreund.getValue()).equals("User"))){
				cookiefreund.setMaxAge(0);				
			}
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}

		if(aw.istGesperrt(cookie.getValue())==true){
			request.getRequestDispatcher("gesperrt.jsp").include(request, response);
			return;
		}
		
		if(aw.darfPinnwandSehen(cookie.getValue(), cookiefreund.getValue())){
			request.setAttribute("pw", "true");
		}
		
		request.setAttribute("email", cookie.getValue());
		request.setAttribute("FREUNDseite", cookiefreund.getValue());
		request.setAttribute("p", "Pinnwand");
		
		if(!(request.getParameter("beitragMelden") == null)){
				aw.beitragMelden(cookiefreund.getValue(), Integer.parseInt(request.getParameter("beitragMelden")));
		} 
		
		request.getRequestDispatcher("freund.jsp").include(request, response);
    	
    }
    
	/**
	 * GET Methode des FreundServlets entscheidet welche JSP dem Benutzer angezeigt werden soll, sowie im Falle einer UserSite, welche
	 * Berechtigungen der Benutzer beim Betrachten der Seite besitzt. Wird mittels RequestAttribute festgelegt.
	 * 
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = null;
		Cookie cookiefreund = null;
		if(request.getCookies() != null){
			for(int i = 0; i<request.getCookies().length;i++){
				if(request.getCookies()[i] != null){
					if(request.getCookies()[i].getName().equals("email")){
						cookie = request.getCookies()[i];
					}
					if(request.getCookies()[i].getName().equals("FREUNDseite")){
						cookiefreund = request.getCookies()[i];
					}
				}
			}
		}
		if(cookie == null) {
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		if(!(aw.getTyp(cookie.getValue()).equals("User"))){
			cookie.setMaxAge(0);
			if(!(aw.getTyp(cookiefreund.getValue()).equals("User"))){
				cookiefreund.setMaxAge(0);				
			}
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}

		if(aw.istGesperrt(cookie.getValue())==true){
			request.getRequestDispatcher("gesperrt.jsp").include(request, response);
			return;
		}
		
		request.setAttribute("email", cookie.getValue());
		request.setAttribute("FREUNDseite", cookiefreund.getValue());
			
		if(aw.darfFreundeslisteSehen(cookie.getValue(), cookiefreund.getValue())){
			request.setAttribute("flist", "true");
		}
		if(aw.darfPinnwandSehen(cookie.getValue(), cookiefreund.getValue())){
			request.setAttribute("pw", "true");
		}
		if(aw.darfKontaktinfoSehen(cookie.getValue(), cookiefreund.getValue())){
			request.setAttribute("kinfo", "true");
		}
		if(aw.darfProfilinfoSehen(cookie.getValue(), cookiefreund.getValue())){
			request.setAttribute("pinfo", "true");
		}
		if(request.getParameter("p") == null){
			request.setAttribute("p", "Pinnwand");
			request.getRequestDispatcher("freund.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Pinnwand")){
			request.setAttribute("p", "Pinnwand");
			request.getRequestDispatcher("freund.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Freunde")){
			request.setAttribute("p", "Freunde");
			request.getRequestDispatcher("freund.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Profilinfo")){
			request.setAttribute("p", "Profilinfo");
			request.getRequestDispatcher("freund.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Kontaktinfo")){
			request.setAttribute("p", "Kontaktinfo");
			request.getRequestDispatcher("freund.jsp").include(request, response);
		} else {
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
		}
	}

}
