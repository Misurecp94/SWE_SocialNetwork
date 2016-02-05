package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout Servlet loescht entsprechende Cookies und leitet  an IndexSeite weiter.
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raoul
 * @version 2015/12/09
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * GET Methode des Servlets, welches das entsprechende Cookie zur Identifizierung loescht, und zurueck zur Indexpage weiterleitet.
	 * 
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = null;
			for(int i = 0; i<request.getCookies().length;i++){
				if(request.getCookies()[i] != null){
					if(request.getCookies()[i].getName().equals("email")){
						cookie = request.getCookies()[i];
					}
				}
			}
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.sendRedirect("/BlueCouch/");
	}

}
