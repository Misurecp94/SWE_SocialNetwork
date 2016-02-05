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
 *  Startservlet, Standardm‰ﬂiger Einstieg des Benutzers.
 *  @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raoul
 *  @version 2015/12/09
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * AnwenderManagement aw um Methoden des AnwenderManagements zur Verfuegung zu stellen
	 */
	private static AnwenderManagement aw = new AnwenderManagement();

	/**
	 * GET Methode des Servlets, welches Cookies ueberprueft und Nutzer ggfs an indexSeite (keine/falsche cookies) bzw an die
	 * Entsprechnde IndexSeite des Benutzers (je nachdem ob Benutzer = User/Admin/Forscher)
	 * 
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = null;
		if(request.getCookies()!= null){
			for(int i = 0; i<request.getCookies().length;i++){
				if(request.getCookies()[i] != null){
					if(request.getCookies()[i].getName().equals("email")){
						cookie = request.getCookies()[i];
						cookie.setMaxAge(0);
					}
				}
			}
		}
		if(cookie == null) {
			request.getRequestDispatcher("index.jsp").include(request, response);
			return;
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
}
