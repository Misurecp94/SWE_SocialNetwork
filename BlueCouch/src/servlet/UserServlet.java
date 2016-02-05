package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import benutzer.management.AnwenderManagement;

/**
 * Klasse UserServlet stellt Hauptservlet bei der Betrachtung der eigenen Seite, so wie
 * Pinnwand, Freunde, etc., dar. Zeigt dem Benutzer die entsprechenden Seiten(Pinnwand,
 * Freunde, Profilinfo, Kontaktdaten, etc.) und setzt entsprechende RequestAttribute.
 * Stellt die Schnittstelle zur Aenderung der Daten dar.<br>
 * 
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf<br>
 * @version 2015_12_09<br>
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Instanzvariable:
	 * aw vom Typ AnwenderManagement bietet die Moeglichkeit Methoden der Klasse
	 * AnwenderManagement anzuwenden.
	 */
    private static AnwenderManagement aw = new AnwenderManagement();

	/**
	 * doGet Methode des UserServlet entscheidet welche JSP dem User angezeigt werden soll.
	 * Wird mittel RequestAttribute festgelegt.<br>
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
		if(!(aw.getTyp(cookie.getValue()).equals("User"))){
			cookie.setMaxAge(0);
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}

		if(aw.istGesperrt(cookie.getValue())==true){
			if(aw.getGesperrt(cookie.getValue())){
				request.getRequestDispatcher("gesperrt.jsp").include(request, response);
				return;
			} else { 
				aw.entsperren(cookie.getValue());
			}
		}
		
		request.setAttribute("email", cookie.getValue());
		if(request.getParameter("p") == null){
			request.setAttribute("p", "Pinnwand");
			request.getRequestDispatcher("user.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Pinnwand")){
			request.setAttribute("p", "Pinnwand");
			request.getRequestDispatcher("user.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Freunde")){
			request.setAttribute("p", "Freunde");
			request.getRequestDispatcher("user.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Profilinfo")){
			request.setAttribute("p", "Profilinfo");
			request.getRequestDispatcher("user.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Kontaktinfo")){
			request.setAttribute("p", "Kontaktinfo");
			request.getRequestDispatcher("user.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Bild")){
			request.setAttribute("p", "Bild");
			request.getRequestDispatcher("user.jsp").include(request, response);
		} else if(request.getParameter("p").equals("Gruppe")){
			request.setAttribute("p", "Gruppe");
			request.getRequestDispatcher("user.jsp").include(request, response);
		} else {
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
		}
	}

	/**
	 * doPost Methode des Servlets entscheidet welche Aenderungen durchzufuehren sind und
	 * ruft entsprechende Methoden (innerhalb des UserServlets) auf. Entscheidung welche
	 * Aenderungen wie durchzufuehren sind mittels request Parameter mitgeteilt.
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
		if(cookie == null) {
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		if(!(aw.getTyp(cookie.getValue()).equals("User"))){
			cookie.setMaxAge(0);
			request.getRequestDispatcher("forbidden.jsp").include(request, response);
			return;
		}
		
		if(aw.istGesperrt(cookie.getValue())==true){
			request.getRequestDispatcher("gesperrt.jsp").include(request, response);
			return;
		}
		
		if(request.getParameter("usersuche") != null){
			if(!(request.getParameter("usersuche").equals(""))){
				this.usersuche(cookie, request, response);
			}
		}
		if (request.getParameter("neuerBeitrag") != null){
			this.neuerBeitrag(cookie, request, response);
		}
		if (request.getParameter("beitragLoeschen") != null){
			this.beitragloeschen(cookie, request, response);
		}
		
		if (request.getParameter("profilinfoChange") != null){
			this.profilinfoaendern(cookie,request,response);
		}

		if (request.getParameter("kontaktDatenChange") != null){
			this.kontaktdatenaendern(cookie,request,response);
		}
		
		if (request.getParameter("freundadd") != null){
			this.freundAdd(cookie, request, response);
		}
		
		if(request.getParameter("storno") != null){
			this.freundschaftStorno(cookie, request, response);
		}
		
		if(request.getParameter("freundschaftja") != null){
			this.freundschaftJa(cookie, request, response);
		}
		
		if(request.getParameter("freundschaftnein") != null){
			this.freundschaftNein(cookie, request, response);
		}
		if(request.getParameter("freunddel") != null) {
			this.freundDel(cookie, request, response);
		}
		
		if(request.getParameter("freundesseite") != null) {
			this.freundesseite(cookie, request, response);
		}

		if(request.getParameter("gruppeErstellen") != null){
			this.gruppeErstellen(cookie, request, response);
		}
		if(request.getParameter("gruppedel") != null){
			this.gruppeDel(cookie, request, response);
		}
		
		if(request.getParameter("gruppeaendern") != null){
			this.gruppeAendern(cookie, request, response);
		}
		
		request.setAttribute("email", cookie.getValue());
		request.getRequestDispatcher("user.jsp").include(request, response);
	
	}

	/**
	 * freundAdd Methode fuegt(versendet) unter Zuhilfename des AnwenderManagement
	 * eine Freundschaftsanfrage dem Benutzer hinzu(an einen Benutzer).
	 * Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void freundAdd(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aw.freundAdd(cookie.getValue(), request.getParameter("freundadd"));
		response.sendRedirect("/BlueCouch/User?p=Freunde");
	}

	/**
	 * freundschaftStorno loescht unter Zuhilfename des AnwenderManagement eine ausgehende
	 * Freundschaftsanfrage des Benutzers. User kann eine Freundschaftsanfrage stornieren.
	 * Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void freundschaftStorno(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aw.freundStorno(cookie.getValue(), request.getParameter("storno"));
		response.sendRedirect("/BlueCouch/User?p=Freunde");
	}

	/**
	 * freundschaftNein loescht unter Zuhilfename des AnwenderManagement eine eingehende
	 * Freundschaftsanfrage des Benutzers. User kann eine Freundschaftsanfrage ablehnen.
	 * Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void freundschaftNein(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aw.freundschaftNein(cookie.getValue(), request.getParameter("freundschaftnein"));
		response.sendRedirect("/BlueCouch/User?p=Freunde");
	}

	/**
	 * freundschaftJa beantwortet unter Zuhilfename eine eingehende Freundschaftsanfrage
	 * positiv und fuegt dadurch einen Freund dem Benutzer hinzu.
	 * Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void freundschaftJa(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aw.freundschaftJa(cookie.getValue(), request.getParameter("freundschaftja"));
		response.sendRedirect("/BlueCouch/User?p=Freunde");
	}

	/**
	 * freunDel loescht unter Zuhilfename des AnwenderManagements einen Freund aus den bestehenden Freunden.
	 * Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void freundDel(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aw.freundDel(cookie.getValue(), request.getParameter("freunddel"));
		response.sendRedirect("/BlueCouch/User?p=Freunde");
	}

	/**
	 * freundesseite Methode leitet an FreundServlet weiter und setzt entsprechendes Cookie, dass
	 * die emailID des anzuzeigenden Nutzers enthaelt.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void freundesseite(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookiefreund = new Cookie("FREUNDseite",(String) request.getParameter("freundesseite"));
		cookiefreund.setMaxAge(60*60*24); 
		response.addCookie(cookiefreund);
		response.sendRedirect("/BlueCouch/Freund");
		return;
	}

	/**
	 * gruppeErstellen ueberprueft eingegebene Paramter. Bei Fehlern folgt die Weiterleitung zum
	 * user.jsp mit entsprechend gesetztem Attribut zur Anzeige einer entsprechenden Fehlermeldung.
	 * Sollten Parameter richtig gesetzt sein, wird mit Hilfe des AnwenderManagements versucht eine
	 * Gruppe mit durch Parameter angegebenen Sichtbarkeitsrechten anzulegen. Sollte dies Fehlschlagen
	 * wird erneut auf user.jsp samt Fehlermeldung verwiesen. Andernfalls erfolgt die Weiterleitung
	 * an die GET Methode des UserServlets
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void gruppeErstellen(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean[] sicht = new boolean[4];
		if(request.getParameter("nameGruppe") == null || request.getParameter("nameGruppe").equals("")){
			request.setAttribute("m", "errortitel");
			request.setAttribute("p", "Gruppe");
			request.setAttribute("email", cookie.getValue());
			request.getRequestDispatcher("user.jsp").include(request, response);;
			return;
		}
		if(request.getParameter("sichtFreundesliste") != null){
			sicht[0] = true;
		}
		if(request.getParameter("sichtPinnwand") != null){
			sicht[1] = true;
		}
		if(request.getParameter("sichtKontaktdaten") != null){
			sicht[2] = true;
		}
		if(request.getParameter("sichtProfilinfo") != null){
			sicht[3] = true;
		}
		if(!(aw.gruppeErstellen(cookie.getValue(),sicht,request.getParameter("nameGruppe")))){
			request.setAttribute("m", "errordouble");
			request.setAttribute("p", "Gruppe");
			request.setAttribute("email", cookie.getValue());
			request.getRequestDispatcher("user.jsp").include(request, response);
			return;
		}
		response.sendRedirect("/BlueCouch/User?p=Gruppe");
		
	}

	/**
	 * gruppeDel loescht unter Zuhilfename des AnwenderManagement eine Gruppe aus den bestehenden
	 * Gruppen. Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void gruppeDel(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aw.gruppeLoeschen(cookie.getValue(), request.getParameter("gruppedel"));
		response.sendRedirect("/BlueCouch/User?p=Gruppe");
	}

	/**
	 * gruppeAendern aendert unter Zuhilfename des AnwenderManagement eine Gruppe aus den bestehenden
	 * Gruppen. Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void gruppeAendern(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aw.gruppeAendern(cookie.getValue(), request.getParameter("gruppeAendern"), request.getParameter("gruppeaendern"));
		response.sendRedirect("/BlueCouch/User?p=Freunde");
	}

	/**
	 * neuerBeitrag fuegt unter Zuhilfename des AnwenderManagement einen neuen Beitrag zu den bestehenden
	 * Beitraegen hinzu. Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * Im Fehlerfall wird zu user.jsp weitergeleitet wo entsprechende Fehlermeldung
	 * ausgegeben wird.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void neuerBeitrag(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!(request.getParameter("beiText").equals("") )&& !(request.getParameter("beiTitel").equals(""))){
			aw.beitragHinzufuegen(cookie.getValue(), request.getParameter("beiTitel"), request.getParameter("beiText"));
			response.sendRedirect("/BlueCouch/User?p=Pinnwand");
			return;
		} else {
			if(request.getParameter("beiText").equals("")){
				request.setAttribute("p", "Pinnwand");
				request.setAttribute("w", "error1");
			}
			if(request.getParameter("beiTitel").equals("")){
				request.setAttribute("p", "Pinnwand");
				request.setAttribute("w", "error2");
			}
		}
	}

	/**
	 * profilinfoaendern aendert unter Zuhilfename des AnwenderManagement die bestehende
	 * Profilinfo des Benutzers. Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem
	 * Parameter p. Im Fehlerfall wird zu user.jsp weitergeleitet wo entsprechende Fehlermeldung
	 * ausgegeben wird.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void profilinfoaendern(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ausgabe = new String[11];
		if(!(request.getParameter("vorname").equals(""))){
			ausgabe[0] = request.getParameter("vorname");
		}
		if(!(request.getParameter("nachname").equals(""))){
			ausgabe[1] = request.getParameter("nachname");
		}
		if(!(request.getParameter("geburtsdatum").equals(""))){
			ausgabe[2] = request.getParameter("geburtsdatum");
		}
		if(!(request.getParameter("hobbies").equals(""))){
			ausgabe[3] = request.getParameter("hobbies");
		}
		if(!(request.getParameter("geschlecht").equals(""))){
			ausgabe[4] = request.getParameter("geschlecht");
		}
		if(!(request.getParameter("religion").equals(""))){
			ausgabe[5] = request.getParameter("religion");
		}
		if(!(request.getParameter("interessen").equals(""))){
			ausgabe[6] = request.getParameter("interessen");
		}
		if(!(request.getParameter("familienstatus").equals(""))){
			ausgabe[7] = request.getParameter("familienstatus");
		}
		if(!(request.getParameter("sexOrientierung").equals(""))){
			ausgabe[8] = request.getParameter("sexOrientierung");
		}
		if(!(request.getParameter("polEinstellung").equals(""))){
			ausgabe[9] = request.getParameter("polEinstellung");
		}
		if(!(request.getParameter("zusatzangaben").equals(""))){
			ausgabe[10] = request.getParameter("zusatzangaben");
		}
		try {
			aw.profilinfoAendern(cookie.getValue(), ausgabe[0], ausgabe[1], ausgabe[2], ausgabe[3], ausgabe[4], 
					ausgabe[5], ausgabe[6], ausgabe[7], ausgabe[8], ausgabe[9], ausgabe[10]);
		} catch (ParseException e) {
			request.setAttribute("p", "Profilinfo");
			request.setAttribute("w", "error1");
			request.setAttribute("email", cookie.getValue());
			request.getRequestDispatcher("user.jsp").include(request, response);
			return;
		}
		response.sendRedirect("/BlueCouch/User?p=Profilinfo");
	}

	/**
	 * kontaktdatenaendern aendert unter Zuhilfename des AnwenderManagement die bestehenden
	 * Kontaktdaten des Benutzers. Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem
	 * Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void kontaktdatenaendern(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ausgabe = new String[4];
		if(!(request.getParameter("adresse").equals(""))){
			ausgabe[0] = request.getParameter("adresse");
		}
		if(!(request.getParameter("wohnort").equals(""))){
			ausgabe[1] = request.getParameter("wohnort");
		}
		if(!(request.getParameter("telefonnr").equals(""))){
			ausgabe[2] = request.getParameter("telefonnr");
		}
		if(!(request.getParameter("emailK").equals(""))){
			ausgabe[3] = request.getParameter("emailK");
		}			
		aw.kontaktDatenAendern(cookie.getValue(), ausgabe[0], ausgabe[1], ausgabe[2], ausgabe[3]);
		response.sendRedirect("/BlueCouch/User?p=Kontaktinfo");
	}

	/**
	 * beitragloeschen loescht unter Zuhilfename des AnwenderManagement einen Beitrag aus den bestehenden
	 * Beitraegen des Benutzers. Anschließend erfolgt Weiterleitung zu User Seite mit gesetztem Parameter p.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void beitragloeschen(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		aw.beitragLoeschen(cookie.getValue(), Integer.parseInt(request.getParameter("beitragLoeschen")));
		response.sendRedirect("/BlueCouch/User?p=Pinnwand");
		return;
	}

	/**
	 * usersuche sucht unter Zuhilfename des AnwenderManagement einen User aus den bestehenden
	 * Usern.
	 * @param cookie uebergebener Wert vom Typ Cookie
	 * @param request Anfrage des Benutzers
	 * @param response Antwort an den Benutzer
	 * @throws ServletException
	 * @throws IOException
	 */
	public void usersuche(Cookie cookie, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> nutzersuche = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		aw.usersuche(cookie.getValue(), nutzersuche, id, request.getParameter("usersuche"));
		request.setAttribute("nutzersuche", nutzersuche);
		request.setAttribute("emailID", id);
	}
	
}
