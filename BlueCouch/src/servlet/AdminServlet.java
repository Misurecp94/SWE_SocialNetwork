package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import benutzer.Admin;
import benutzer.management.AnwenderManagement;


/**
 * AdminServlet regelt Adminfunktionen aka zeigt entsprechende JSPs an bzw loest Methoden d. AnwenderManagements aus
 * @author Patrik Misurec, Raphael Kolhaupt, Christian Pfneisl, Mohamed Raouf
 * @version 2016/01/10
 */
@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Hilfsobjekt um auf Methoden d. AnwenderManagements zugreifen zu koennen
	 */
	private static AnwenderManagement aw = new AnwenderManagement();
       
	/**
	 * doGet Methode, welche entsprechende JSPs anzeigt (admin.jsp bzw bei fehlenden Berechtigungen forbidden.jsp)
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
		if(!(aw.getTyp(cookie.getValue()).equals("Admin"))){
			cookie.setMaxAge(0);
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		//Admin admin = (Admin) aw.getAnwenderbyID(cookie.getValue());
		//for(int i = 0; i<((Admin)aw.getAnwenderbyID(cookie.getValue())).getGemeldeteBeitraege().size();i++ ){
		//	System.out.println(admin.getGemeldeteBeitraege().get(i).getID());
		//} // Ausgabe aller gemeldeten Beitraege zu Testzwecken
		request.setAttribute("email", cookie.getValue());
		request.getRequestDispatcher("admin.jsp").include(request, response);
		
	}

	/**
	 * doPost Methode, welche von admin.jsp uebergebene Werte ueberprueft und entsprechende Methoden des AnwenderManagements ausloest
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
		if(cookie == null) {
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		if(!(aw.getTyp(cookie.getValue()).equals("Admin"))){
			cookie.setMaxAge(0);
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		
		if(!(request.getParameter("userSperren")==null)){
				try{
				Calendar gebDat = null;
					if(!(request.getParameter("sperrenBis").equals(""))){
						gebDat = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						gebDat.setTime(sdf.parse(request.getParameter("sperrenBis")));
						if(gebDat.before(Calendar.getInstance())){
							throw new IllegalArgumentException();
						}
					}
					aw.userSperrenAdmin(request.getParameter("userSperren"), cookie.getValue(), gebDat);
				}catch(Exception e){
					request.setAttribute("warning", "1");
				}
		}
		
		if(!(request.getParameter("beitragLoeschen")== null)){
			aw.beitragLoeschenAdmin(request.getParameter("beitragLoeschen"),Integer.parseInt(request.getParameter("beitragsID")));
		}
		
		if(!(request.getParameter("beitragFreigeben")==null)){
			aw.beitragFreigebenAdmin(request.getParameter("beitragFreigeben"),Integer.parseInt(request.getParameter("beitragsID")));
		}
		
		request.setAttribute("email", cookie.getValue());
		request.getRequestDispatcher("admin.jsp").include(request, response);
	}

}
