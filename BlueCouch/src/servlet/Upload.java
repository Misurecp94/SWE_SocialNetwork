package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import benutzer.management.AnwenderManagement;

/**
 * Klasse Upload ist fuer den Upload der Bilddateien zustaendig. Sie prueft ob eine Uploadbare Datei vorliegt, und ob diese
 * einem Image entspricht. Wenn ja, leitet Sie das AnwenderManagement dazu dem jeweiligen User das Bild zuzuordnen.
 * 
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf<br>
 * @version 2015_12_27<br>
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instanzvariable id stellt die id der UploadDateien dar.
	 */
	private static int id = 0;
	
	/**
	 * Instanzvariable:
	 * aw vom Typ AnwenderManagement bietet die Moeglichkeit Methoden der Klasse
	 * AnwenderManagement anzuwenden.
	 */
	private static AnwenderManagement aw = new AnwenderManagement();
	
	/**
	 * doPost Methode fuer Upload der Datei welche der Benutzer hochladen will
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
		
        request.setAttribute("email", cookie.getValue());
        request.setAttribute("p", "Bild");
		String filepath = null;
		String filename = null;
		
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        if(name != "") {
                        	filename = ("pic/" + id++ + name);
                        	filepath = ("../webapps/BlueCouch/" + filename);
	                        String mimeType = getServletContext().getMimeType(filepath);
	                        if (mimeType.startsWith("image/")) {
	                        	 item.write( new File(filepath));
	                        	 request.setAttribute("s", "1");
	                        	 aw.setBenutzerbildFuerUser(cookie.getValue(), filename);
	                        } else {
	                        	request.setAttribute("s", "2");
	                        }
                        } else {
                        	request.setAttribute("s", "2");
                        }
                    }
                }
            } catch (Exception ex) {
            	request.setAttribute("s", "2");
            }         
        }else{
        	request.setAttribute("s", "2");
        }
        request.getRequestDispatcher("user.jsp").include(request, response);
	}

}
