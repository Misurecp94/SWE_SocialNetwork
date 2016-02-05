package benutzer.management;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.commons.lang3.*;
import benutzer.*;
import benutzer.management.DAO.*;
import benutzer.user.*;

/**
 * Klasse AnwenderManagement dient als Schnittstelle zwischen innerer Struktur und Servlets/Jsp's<br>
 * Bearbeitet und ueberwacht die gesamte Logik des Programms.
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf
 * @version 2015/12/09
 */
public class AnwenderManagement {
	
	/**
	 * Instanzvariable anwenderDAO vom Typ AnwenderDAO stellt M�glichkeit zur Verwendung von Methoden der Klasse 
	 * AnwenderDAO zur Verfuegung <br>
	 */
	private AnwenderDAO anwenderDAO;
	
	/**
	 * Konstruktor erzeugt lediglich eine neue Instanz der Klasse AnwenderDAO und weist der Instanzvariable 
	 * anwenderDAO die Referenz zu.<br>
	 */
	public AnwenderManagement(){
		this.anwenderDAO = new AnwenderDAO();
	}
	
	/**
	 * Methode getAnwenderbyID retourniert unter Zuhilfenahme der Klasse AnwenderDAO den gefundenen Anwender.<br>
	 * Sollte kein Anwender gefunden worden sein, erfolgt eine Rueckgabe von null
	 * @param email Eindeutige Email bzw. String anhand welcher der User identifizierbar ist.
	 * @return Entsprechender Anwender wird retourniert, mithilfe der Methode getAnwenderbyID der Klasse AnwenderDAO.
	 */
	public Anwender getAnwenderbyID(String email){
		return anwenderDAO.getAnwenderbyID(email);
	}
	
	/**
	 * Methode userSperrenAdmin sperrt einen User unter Zuhilfename der Klasse Admin.java.
	 * @param emailUser Eindeutige EmailID des zu sperrenden Users
	 * @param emailAdmin Eindeutige EmailID des Admins
	 * @param cal Datum bis zu welchem der User gesperrt werden soll (null = dauerhaft)
	 */
	public void userSperrenAdmin(String emailUser, String emailAdmin,  Calendar cal){
		Admin admin = (Admin) this.getAnwenderbyID(emailAdmin);
		User user = (User) this.getAnwenderbyID(emailUser);
		admin.userSperren(user, cal);
		this.resaveAnwender((Anwender)user);
	}
	
	/**
	 * Methode istGesperrt ueberprueft ob der User gesperrt ist.
	 * @param emailUser Eindeutige EmailID des zu ueberpruefenden Users.
	 * @return true wenn User gesperrt, sonst false
	 */
	public boolean istGesperrt(String emailUser){
		return ((User)this.getAnwenderbyID(emailUser)).getGesperrt();
	}
	
	/**
	 * Entsperrt den user
	 * @param emailUser EmailID des Users
	 */
	public void entsperren(String emailUser){
		User help = (User) this.getAnwenderbyID(emailUser);
		if(help.getGesperrt()){
			help.setGesperrt(false);
			help.setGesperrtBis(null);
			this.resaveAnwender((Anwender)help);
		} else {
			return;
		}
	}
	
	/**
	 * Methode get Gesperrt gibt Gueltigkeit der Sperre zurueck
	 * @param emailUser emailID des Users
	 * @return false wenn Sperre nicht aktuell, true anderfalls
	 */
	public boolean getGesperrt(String emailUser){
		User help = (User) this.getAnwenderbyID(emailUser);
		if(help.getGesperrt()){
			if(help.getGesperrtBis() == null){
				return true;
			}
			if(help.getGesperrtBis().before(Calendar.getInstance())){
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Methode getGemeldeteBeitraege gibt alle gemeldeten Beitraege unter Zuhilfenahme der Klasse Admin zurueck.
	 * @param emailAdmin Eindeutige EmailID des Admins
	 * @return ArrayList mit gemeldeten Beitraegen
	 */
	public ArrayList<Beitrag> getGemeldeteBeitraege(String emailAdmin){
		Admin help = (Admin) this.getAnwenderbyID(emailAdmin);
		return help.getGemeldeteBeitraege();
	}
	
	/**
	 * Methode beitragFreigebenAdmin gibt einen gemeldeten Beitrag unter Zuhilfenahme der Klasse Admin.java frei.
	 * @param emailUser Eindeutige EmailID des Users dem der freizugebene Beitrag gehoert
	 * @param id Innerhalb des Users eindeutige ID des Beitrages
	 */
	public void beitragFreigebenAdmin(String emailUser,  int id){
		User user = (User) this.getAnwenderbyID(emailUser);
		ArrayList<Beitrag> beitraege = user.getBeitraege();
		Beitrag beitrag = null;
		for(int i = 0; i<beitraege.size();i++){
			if(beitraege.get(i).getID() == id){
				beitrag = beitraege.get(i);
				break;
			}
		}
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		for(int i = 0; i<help.size();i++){
			if(help.get(i) instanceof Admin){
				((Admin)help.get(i)).meldungAufheben(beitrag);
				beitrag.setGemeldet(false);
				this.resaveAnwender(help.get(i));
				this.resaveAnwender((Anwender)user);
			}
		}
	}
	
	/**
	 * Methode beitragLoeschenAdmin loescht einen gemeldeten Beitrag unter Zuhilfenahme der Klasse Admin.java sowie der Klasse AnwenderManagement.java.
	 * @param emailUser Eindeutige EmailID des Users dem der zu loeschende Beitrag gehoert
	 * @param id Innerhalb des Users eindeutige ID des Beitrages
	 */
	public void beitragLoeschenAdmin(String emailUser,  int id){
		User user = (User) this.getAnwenderbyID(emailUser);
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		for(int i = 0; i<help.size();i++){
			if(help.get(i) instanceof Admin){
				((Admin)help.get(i)).loescheBeitrag(id, user);;
				this.resaveAnwender(help.get(i));
				this.resaveAnwender((Anwender)user);
			}
		}
	}
	
	/**
	 * Ueberprueft die Logindaten
	 * @param email Einlog_Email des Anwenders
	 * @param password Einliog_Passwort des Anwenders
	 * @return der entsprechende Anwender bzw. "null" falls die EMail nicht vorhanden ist, oder das Passwort falsch ist.
	*/
	public Anwender login(String email, String password){
		Anwender help = anwenderDAO.getAnwenderbyID(email);
		if(help == null) return null;
		if(!(help.getPassword().equals(password))) return null;
		return help;
	}
	
	/**
	 * freundStorno storniert eine Freundschaftsanfrage des Users
	 * @param email Einlog_Email des Users
	 * @param freundmail Einlog_Email des zu addenden Freundes
	 */
	public void freundStorno(String email, String freundmail){
		User user = (User) this.getAnwenderbyID(email);
		User freund = (User) this.getAnwenderbyID(freundmail);
		user.getFAnfragenAusgehend().remove(freundmail);
		freund.getFAnfragenEingehend().remove(email);
		this.resaveAnwender((Anwender) user);
		this.resaveAnwender((Anwender) freund);
	}
	
	/**
	 * freundAdd versendet eine Freundschaftsanfrage an den zu addenden User
	 * @param email Einlog_Email des Users
	 * @param freundmail Einlog_Email des zu addenden Freundes
	 */
	public void freundAdd(String email, String freundmail){
		User user = (User) this.getAnwenderbyID(email);
		User freund = (User) this.getAnwenderbyID(freundmail);
		user.getFAnfragenAusgehend().add(freundmail);
		freund.getFAnfragenEingehend().add(email);
		this.resaveAnwender((Anwender) user);
		this.resaveAnwender((Anwender) freund);
	}
	
	/**
	 * freundDel loescht einen Freund aus den bestehenden Freunden des Benutzers
	 * @param email Einlog_Email des Users
	 * @param freundmail Einlog_Email des zu addenden Freundes
	 */
	public void freundDel(String email, String freundmail){
		User user = (User) this.getAnwenderbyID(email);
		User freund = (User) this.getAnwenderbyID(freundmail);
		user.freundLoeschen(freundmail);
		freund.freundLoeschen(email);
		this.resaveAnwender((Anwender) user);
		this.resaveAnwender((Anwender) freund);
	}
	
	/**
	 * freundschaftNein loescht eine eingehende Freundschaftsanfrage.
	 * @param email Einlog_Email des Users
	 * @param freundmail Einlog_Email des zu addenden Freundes
	 */
	public void freundschaftNein(String email, String freundmail){
		User user = (User) this.getAnwenderbyID(email);
		User freund = (User) this.getAnwenderbyID(freundmail);
		user.getFAnfragenEingehend().remove(freundmail);
		freund.getFAnfragenAusgehend().remove(email);
		this.resaveAnwender((Anwender) user);
		this.resaveAnwender((Anwender) freund);
	}
	
	/**
	 * freundschaftJa akzeptiert eine eingehende Freundschaftsanfrage positiv.
	 * @param email Einlog_Email des Users
	 * @param freundmail Einlog_Email des zu addenden Freundes
	 */
	public void freundschaftJa(String email, String freundmail){
		User user = (User) this.getAnwenderbyID(email);
		User freund = (User) this.getAnwenderbyID(freundmail);
		user.freundschaftsanfrageBeantworten(freundmail);
		freund.freundschaftsanfrageBeantwortet(email);
		this.resaveAnwender((Anwender) user);
		this.resaveAnwender((Anwender) freund);
	}
	
	/** Speichert einen neuen Anwender persinstent in die Datenquelle und retouniert diesen.
	* @param email Einlog_email des neu anzulegenden Users (Unique)
	* @param password Passwort des zu erzeugenden Anwenders
	* @param typ Typ (User, Admin, Forscher) des zu erzeugenden Anwenders
	* @return der neu registrierte Anwender bzw. "null" falls der  Anwender (EMail) bereits vorhanden ist
	*/
	public Anwender registrieren(String email, String password, String typ){
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		for(int i = 0; i<help.size();i++){
			if(help.get(i).getEmailID().equals(email)){
				return null;
			}
		}
		// Ueberpruefen ob bereits ein Admin vorhanden ... wenn nicht -> SystemAdmin wird erzeugt
		Admin firstAdmin = null;
		for(int i = 0; i<help.size(); i++){
			if(help.get(i) instanceof Admin){
				firstAdmin = (Admin) help.get(i);
				break;
			}
		}
		if(firstAdmin == null){
			Anwender fAdmin = new Admin("SystemAdmin", "123");
			anwenderDAO.saveAnwender(fAdmin);
		}
		if(typ.equals("admin")){
			Anwender help2 = new Admin(email, password);
			// Anderen Admi nsuchen f. Zuweisung d. gemeldeten Beitraege
			ArrayList<Anwender> awHelp = anwenderDAO.loadAnwenderList();
			Admin helpAdmin = null;
			for(int i = 0; i<awHelp.size(); i++){
				if(awHelp.get(i) instanceof Admin){
					helpAdmin =(Admin) awHelp.get(i);
					break;
				}
			}
			if(helpAdmin != null){
				((Admin) help2).setGemeldeteBeitraege(helpAdmin.getGemeldeteBeitraege());
			}
			anwenderDAO.saveAnwender(help2);
			return help2;
		} else if(typ.equals("forscher")){
			Anwender help2 = new Forscher(email, password);
			anwenderDAO.saveAnwender(help2);
			return help2;
		} else if(typ.equals("user")){
			Anwender help2 = new User(email, password);
			anwenderDAO.saveAnwender(help2);
			return help2;
		}
		return null;
	}
	
	/**
	 * gruppeSichtProfilinfo ueberprueft ob die Gruppe mit dem Titel gruppennamen die Berechtigung
	 * hat die Profilinfo zu sehen
	 * @param email Einlog_Email des Benutzers
	 * @param gruppennamen ist der Titel der Gruppe dessen Sichtbarkeitsrecht ueberprueft wird
	 * @return true falls die Gruppe die Profilinfo sehen darf, false falls sie die Profilinfo nicht
	 * sehen darf
	 */
	public boolean gruppeSichtProfilinfo(String email, String gruppennamen){
		User user = (User) this.getAnwenderbyID(email);
		Gruppe gruppe = null;
		for(int i=0; i<user.getGruppen().size();i++){
			if(user.getGruppen().get(i).getName().equals(gruppennamen)){
				gruppe = user.getGruppen().get(i);
				break;
			}
		}
		if(user.getSichtbarkeitProfilinfo().contains(gruppe)){
			return true;
		}
		return false;
	}
	
	/**
	 * gruppeSichtKontaktdaten ueberprueft ob die Gruppe mit dem Titel gruppennamen die Berechtigung
	 * hat die Kontaktdaten zu sehen.
	 * @param email Einlog_Email des Benutzers
	 * @param gruppennamen ist der Titel der Gruppe dessen Sichtbarkeitsrecht ueberprueft wird
	 * @return true falls die Gruppe die Kontaktdaten sehen darf, false falls sie die Kontaktdaten nicht
	 * sehen darf
	 */
	public boolean gruppeSichtKontaktdaten(String email, String gruppennamen){
		User user = (User) this.getAnwenderbyID(email);
		Gruppe gruppe = null;
		for(int i=0; i<user.getGruppen().size();i++){
			if(user.getGruppen().get(i).getName().equals(gruppennamen)){
				gruppe = user.getGruppen().get(i);
				break;
			}
		}
		if(user.getSichtbarkeitKontaktdaten().contains(gruppe)){
			return true;
		}
		return false;
	}
	
	/**
	 * gruppeSichtPinnwand ueberprueft ob die Gruppe mit dem Titel gruppennamen die Berechtigung
	 * hat die Pinnwand zu sehen.
	 * @param email Einlog_Email des Benutzers
	 * @param gruppennamen ist der Titel der Gruppe dessen Sichtbarkeitsrecht ueberprueft wird
	 * @return true falls die Gruppe die Pinnwand sehen darf, false falls sie die Pinnwand nicht
	 * sehen darf
	 */
	public boolean gruppeSichtPinnwand(String email, String gruppennamen){
		User user = (User) this.getAnwenderbyID(email);
		Gruppe gruppe = null;
		for(int i=0; i<user.getGruppen().size();i++){
			if(user.getGruppen().get(i).getName().equals(gruppennamen)){
				gruppe = user.getGruppen().get(i);
				break;
			}
		}
		if(user.getSichtbarkeitPinnwand().contains(gruppe)){
			return true;
		}
		return false;
	}
	
	/**
	 * gruppeSichtFreundesliste ueberprueft ob die Gruppe mit dem Titel gruppennamen die Berechtigung
	 * hat die Freundesliste zu sehen.
	 * @param email Einlog_Email des Benutzers
	 * @param gruppennamen ist der Titel der Gruppe dessen Sichtbarkeitsrecht ueberprueft wird
	 * @return true falls die Gruppe die Freundesliste sehen darf, false falls sie die Freundesliste nicht
	 * sehen darf
	 */
	public boolean gruppeSichtFreundesliste(String email, String gruppennamen){
		User user = (User) this.getAnwenderbyID(email);
		Gruppe gruppe = null;
		for(int i=0; i<user.getGruppen().size();i++){
			if(user.getGruppen().get(i).getName().equals(gruppennamen)){
				gruppe = user.getGruppen().get(i);
				break;
			}
		}
		if(user.getSichtbarkeitFreundeslist().contains(gruppe)){
			return true;
		}
		return false;
	}
	
	/**
	 * getGruppen gibt eine ArrayList der Gruppen des Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return ausgabe(ArrayList(Gruppe)) beinhaltet die Gruppen mit dessen Namen der User in den
	 * Gruppen
	 */
	public ArrayList<String> getGruppen(String email){
		User user = (User) this.getAnwenderbyID(email);
		ArrayList<String> ausgabe = new ArrayList<String>();
		ArrayList<Gruppe> gruppen = user.getGruppen();
		for(int i = 0; i<gruppen.size();i++){
			ausgabe.add(gruppen.get(i).getName());
		}
		
		return ausgabe;
	}
	
	/**
	 * getFreundeID gibt eine ArrayList der Freunde des Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return ausgabe(ArrayList(Freund)) beinhaltet die Namen aller Freunde des Users
	 */
	public ArrayList<String> getFreundeID(String email){
		User user = (User) this.getAnwenderbyID(email);
		ArrayList<String> ausgabe = new ArrayList<String>();
		ArrayList<Freund> flist = user.getFreunde();
		for(int i = 0; i<flist.size();i++){
			ausgabe.add(flist.get(i).getFreunduser());
		}
		return ausgabe;
	}
	
	/**
	 * getFAnfragenEingehend gibt die eingehenden Freundschaftsanfragen zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return eine ArrayList der eingehenden Freundschaftsanfragen
	 */
	public ArrayList<String> getFAnfragenEingehend(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getFAnfragenEingehend();
	}
	
	/**
	 * getBeitragMeldungen gibt eine ArrayList mit den jeweiligen MeldeStati der Beitraege des angegebenen Users
	 * zurueck.<br>
	 * @param email Einlog_Email des Benutzers
	 * @return ArrayListe vom Typ Boolean mit gemeldeten und nicht gemeldeten Beitraegen
	 */
	public ArrayList<Boolean> getBeitragsMeldung(String email){
		ArrayList<Boolean> ausgabe = new ArrayList<Boolean>();
		User user = (User) this.getAnwenderbyID(email);
		for(int i = 0; i<user.getBeitraege().size();i++){
			ausgabe.add(user.getBeitraege().get(i).getGemeldet());
		}
		return ausgabe;
	}
	
	/**
	 * getFAnfragenAusgehend gibt die ausgehenden Freundschaftsanfragen zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return eine ArrayList der ausgehenden Freundschaftsanfragen
	 */
	public ArrayList<String> getFAnfragenAusgehend(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getFAnfragenAusgehend();
	}
	
	/**
	 * getBeitraegsID gibt alle Beitrags IDs des Users als ArrayList zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return eine ArrayList der Beitraege des Users
	 */
	public ArrayList<Integer> getBeitraegsID(String email){
		ArrayList<Integer> ausgabe = new ArrayList<Integer>();
		User user = (User) this.getAnwenderbyID(email);
		for(int i = 0; i<user.getBeitraege().size();i++){
			ausgabe.add(user.getBeitraege().get(i).getID());
		}
		return ausgabe;
	}

	/**
	 * getBeitraegsInhalt gibt den gesamten Inhalt aller Beitraege eines User als ArrayList zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return ArrayList der Inhalte aller Beitraeg des Users
	 */
	public ArrayList<String> getBeitraegsInhalt(String email){
		ArrayList<String> ausgabe = new ArrayList<String>();
		User user = (User) this.getAnwenderbyID(email);
		for(int i = 0; i<user.getBeitraege().size();i++){
			ausgabe.add(user.getBeitraege().get(i).getInhalt());
		}
		return ausgabe;
	}
	
	/**
	 * getBeitraegsTitel gibt die Titel aller Beitraege eines Users als ArrayList zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return ArrayList vom Typ String der Titel aller Beitraege eines Users
	 */
	public ArrayList<String> getBeitraegsTitel(String email){
		ArrayList<String> ausgabe = new ArrayList<String>();
		User user = (User) this.getAnwenderbyID(email);
		for(int i = 0; i<user.getBeitraege().size();i++){
			ausgabe.add(user.getBeitraege().get(i).getTitel());
		}
		return ausgabe;
	}
	
	/**
	 * getBenutzerbildFromUser gibt das File des Benutzerbildes zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return String vom Benutzerbild des Users
	 */
	public String getBenutzerbildFromUser(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getBenutzerbild();
	}
	
	/**
	 * setBenutzerbildFuerUser fuegt den Pfad des Benutzerbildes in der Instanzvariable des Users hinzu
	 * @param email Einlog_Email des Benutzers
	 * @param filename Pfad der die BildDatei beinhaltet
	 */
	public void setBenutzerbildFuerUser(String email, String filename){
		User user = (User) this.getAnwenderbyID(email);
		user.setBenutzerbild(filename);
		this.resaveAnwender(user);
	}
	
	/**
	 * getBday gibt das Geburtsdatum eines Users zurueck im Datumsformat yyyy/MM/dd.
	 * @param email Einlog_Email des Benutzers
	 * @return sdf im Datumsformat yyyy-MM-dd beinhaltet das Geburtsdatum des Users.
	 */
	public String getBday(String email){
		User user = (User) this.getAnwenderbyID(email);
		Calendar bday = user.getProfilinfo().getGeburtsdatum();
		if(bday!=null){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(bday.getTime());
		} else return null;
	}
	
	/**
	 * getNachname gibt den Nachnamen eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return nachname des uebergebenen Users
	 */
	public String getNachname(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getNachname();
	}
	
	/**
	 * anzahlBeitraege gibt die Anzahl der erstellten Beitraege eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return anzahl aller Beitraege des uebergebenen Users
	 */
	public int anzahlBeitraege(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.anzahlBeitraege();
	}
	
	/**
	 * getAccountEmail gibt die emailID eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return emailID des uebergebenen Users
	 */
	public String getAccountEmail(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getEmailID();
	}
	
	/**
	 * getEmail gibt die Email der Kontaktdaten eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return Email aus den Kontaktdaten des uebergebenen Users
	 */
	public String getEMail(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getKontaktdaten().getEmail();
	}
	
	/**
	 * getTelNr gibt die Telefonnummer eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return telefonnr aus den Kontaktdaten des uebergebenen Users
	 */
	public String getTelNr(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getKontaktdaten().getTelefonnr();
	}
	
	/**
	 * getWohnort gibt den Wohnort eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return wohnort aus den Kontaktdaten des uebergebenen Users
	 */
	public String getWohnort(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getKontaktdaten().getWohnort();
	}
	
	/**
	 * getAdresse gibt die Adresse eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return adresse aus den Kontaktdaten des uebergebenen Users
	 */
	public String getAdresse(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getKontaktdaten().getAdresse();
	}
	
	/**
	 * getZusatzangaben gibt die Zusatzangaben eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return zusatzangaben aus den Profilinfo des uebergebenen Users
	 */
	public String getZusatzangaben(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getZusatzangaben();
	}
	
	/**
	 * getPolEinstellung gibt die politische Einstellung eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return polEinstellung aus den Profilinfo des uebergebenen Users
	 */
	public String getPolEinstellung(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getPolEinstellung();
	}
	
	/**
	 * getSexOrientierung gibt die sexuelle Orientierung eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return sexOrientierung aus den Profilinfo des uebergebenen Users
	 */
	public String getSexOrientierung(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getSexOrientierung();
	}
	
	/**
	 * getFamilienstatus gibt den Famielienstatus eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return familienstatus aus den Profilinfo des uebergebenen Users
	 */
	public String getFamilienstatus(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getFamilienstatus();
	}
	
	/**
	 * getReligion gibt die Religion/en eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return religion aus den Profilinfo des uebergebenen Users
	 */
	public String getReligion(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getReligion();
	}
	
	/**
	 * getInteressen gibt die Interessen eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return interessen aus den Profilinfo des uebergebenen Users
	 */
	public String getInteressen(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getInteressen();
	}
	
	/**
	 * getGeschlecht gibt das angegebene Geschlecht eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return geschlecht aus den Profilinfo des uebergebenen Users
	 */
	public String getGeschlecht(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getGeschlecht();
	}
	
	/**
	 * getHobbies gibt die Hobbies eines Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return hobbies aus den Profilinfo des uebergebenen Users
	 */
	public String getHobbies(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getHobbies();
	}
	
	/**
	 * getVorname gibt den Vornamen des Users zurueck.
	 * @param email Einlog_Email des Benutzers
	 * @return vorname aus den Profilinfo des uebergebenen Users
	 */
	public String getVorname(String email){
		User user = (User) this.getAnwenderbyID(email);
		return user.getProfilinfo().getVorname();
	}
	
	/**
	 * istInEinerFreundesliste gibt true zurueck, falls der eine User email in irgendeiner Form
	 * mit dem uebergebenen User freundmail in einer Freundschaftbeziehung oder auf der Freundschaftsanfragenliste
	 * des einen oder des anderen ist.
	 * @param email Einlog_Email des Benutzers
	 * @param freundmail email des zweiten uebergebenen User
	 * @return true or false
	 */
	public boolean istInEinerFreundesliste(String email, String freundmail){
		User user = (User) this.getAnwenderbyID(email);
		User help = (User) this.getAnwenderbyID(freundmail);
		if(!(user.getFAnfragenAusgehend().contains(help.getEmailID()))) {
			if(!(user.getFAnfragenEingehend().contains(help.getEmailID()))){
				for(int k = 0; k<user.getFreunde().size();k++){
					if(user.getFreunde().get(k).getFreunduser().equals(help.getEmailID())){
						return false;
					}
				}
				return true;
			} else {return false;}
		} else {return false;}
	}
	
	/**
	 * darfFreundeslisteSehen ueberprueft ob der betrachter die Freundesliste des eigentuemer
	 * sehen darf.
	 * @param betrachter Einlog_Email des Users dessen Sichtbarkeitsrechte ueberprueft werden
	 * @param eigentuemer Einlog_Email des Users dessen Sichtbarkeitsrechtevergebung ueberprueft wird
	 * @return true or false, true wenn der betrachter die Freundesliste des eigentuemer sehen darf
	 */
	public boolean darfFreundeslisteSehen(String betrachter, String eigentuemer){
		User eigentuemer_user = (User) this.getAnwenderbyID(eigentuemer);
		User betrachter_user = (User) this.getAnwenderbyID(betrachter);
		Gruppe inGruppe = null;
		for(int i = 0; i<eigentuemer_user.getGruppen().size();i++){
			if(this.istInGruppe(eigentuemer_user.getEmailID(), betrachter_user.getEmailID(), eigentuemer_user.getGruppen().get(i).getName())){
				inGruppe = eigentuemer_user.getGruppen().get(i);
				break;
			}
		}
		if(eigentuemer_user.getSichtbarkeitFreundeslist().contains(inGruppe)) return true;
		return false;
	}
	
	/**
	 * darfPinnwandSehen ueberprueft ob der betrachter die Pinnwand des eigentuemer
	 * sehen darf.
	 * @param betrachter Einlog_Email des Users dessen Sichtbarkeitsrechte ueberprueft werden
	 * @param eigentuemer Einlog_Email des Users dessen Sichtbarkeitsrechtevergebung ueberprueft wird
	 * @return true or false, true wenn der betrachter die Pinnwand des eigentuemer sehen darf
	 */
	public boolean darfPinnwandSehen(String betrachter, String eigentuemer){
		User eigentuemer_user = (User) this.getAnwenderbyID(eigentuemer);
		User betrachter_user = (User) this.getAnwenderbyID(betrachter);
		Gruppe inGruppe = null;
		for(int i = 0; i<eigentuemer_user.getGruppen().size();i++){
			if(this.istInGruppe(eigentuemer_user.getEmailID(),betrachter_user.getEmailID(), eigentuemer_user.getGruppen().get(i).getName())){
				inGruppe = eigentuemer_user.getGruppen().get(i);
				break;
			}
		}
		if(eigentuemer_user.getSichtbarkeitPinnwand().contains(inGruppe)) return true;
		return false;
	}
	
	/**
	 * darfKontaktinfoSehen ueberprueft ob der betrachter die Kontaktinfo des eigentuemer
	 * sehen darf.
	 * @param betrachter Einlog_Email des Users dessen Sichtbarkeitsrechte ueberprueft werden
	 * @param eigentuemer Einlog_Email des Users dessen Sichtbarkeitsrechtevergebung ueberprueft wird
	 * @return true or false, true wenn der betrachter die Kontaktinfo des eigentuemer sehen darf
	 */
	public boolean darfKontaktinfoSehen(String betrachter, String eigentuemer){
		User eigentuemer_user = (User) this.getAnwenderbyID(eigentuemer);
		User betrachter_user = (User) this.getAnwenderbyID(betrachter);
		Gruppe inGruppe = null;
		for(int i = 0; i<eigentuemer_user.getGruppen().size();i++){
			if(this.istInGruppe(eigentuemer_user.getEmailID(),betrachter_user.getEmailID(), eigentuemer_user.getGruppen().get(i).getName())){
				inGruppe = eigentuemer_user.getGruppen().get(i);
				break;
			}
		}
		if(eigentuemer_user.getSichtbarkeitKontaktdaten().contains(inGruppe)) return true;
		return false;
	}
	
	/**
	 * darfProfilinfo ueberprueft ob der betrachter die Profilinfo des eigentuemer
	 * sehen darf.
	 * @param betrachter Einlog_Email des Users dessen Sichtbarkeitsrechte ueberprueft werden
	 * @param eigentuemer Einlog_Email des Users dessen Sichtbarkeitsrechtevergebung ueberprueft wird
	 * @return true or false, true wenn der betrachter die Profilinfo des eigentuemer sehen darf
	 */
	public boolean darfProfilinfoSehen(String betrachter, String eigentuemer){
		User eigentuemer_user = (User) this.getAnwenderbyID(eigentuemer);
		User betrachter_user = (User) this.getAnwenderbyID(betrachter);
		Gruppe inGruppe = null;
		for(int i = 0; i<eigentuemer_user.getGruppen().size();i++){
			if(this.istInGruppe(eigentuemer_user.getEmailID(), betrachter_user.getEmailID(), eigentuemer_user.getGruppen().get(i).getName())){
				inGruppe = eigentuemer_user.getGruppen().get(i);
				break;
			}
		}
		if(eigentuemer_user.getSichtbarkeitProfilinfo().contains(inGruppe)) return true;
		return false;
	}
	
	/**
	 * istInGruppe ueberprueft ob der User freundmail in der Gruppe mit dem Titel gruppenname
	 * des Users usermail enthalten ist.
	 * @param usermail Einlog_Email des Users, dessen Gruppe durchsucht wird
	 * @param freundmail Einlog_Email des Users, der in der Gruppe gruppenname gesucht wird
	 * @param gruppenname Titel der Gruppe des Users usermail die uebergeben und durchsucht wird
	 * @return true or false, true falls der User freundmail in der Gruppe mit dem Titel gruppenname
	 * enthalten ist
	 */
	public boolean istInGruppe(String usermail, String freundmail, String gruppenname){
		User user = (User) this.getAnwenderbyID(usermail);
		User freund = (User) this.getAnwenderbyID(freundmail);
		Gruppe gruppe = null;
		for(int i = 0; i<user.getGruppen().size();i++){
			if(user.getGruppen().get(i).getName().equals(gruppenname)){
				gruppe = user.getGruppen().get(i);
				break;
			}
		}
		for(int i = 0; i<gruppe.getMitglieder().size();i++){
			if(freund.getEmailID().equals(gruppe.getMitglieder().get(i).getFreunduser())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * istGruppe ueberprueft ob der User freundmail in irgendeiner Gruppe des Users usermail
	 * enthalten ist.
	 * @param usermail Einlog_Email des Users, dessen Gruppen durchsucht werden
	 * @param freundmail Einlog_Email des Users, der in den Gruppen gesucht wird
	 * @return true or false, true falls der User freundmail in einer oder mehreren Gruppen
	 * des Users usermail enthalten ist
	 */
	public boolean istGruppe(String usermail, String freundmail){
		User user = (User) this.getAnwenderbyID(usermail);
		User freund = (User) this.getAnwenderbyID(freundmail);
		for(int i = 0; i<user.getGruppen().size();i++){
			for(int j = 0; j<user.getGruppen().get(i).getMitglieder().size();j++){
				if(user.getGruppen().get(i).getMitglieder().get(j).getFreunduser().equals(freund.getEmailID())){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * gruppeErstellen erstellt eine Gruppe und kann diese zu einer der Sichtbarkeitsliste
	 * hinzufuegen.
	 * @param email Einlog_Email des Users
	 * @param sicht Auswahl der Sichtbarkeitsliste
	 * @param name ist der Name der zu erstellenden Gruppe
	 * @return true or false, false falls der Name der Gruppe schon vorhanden ist.
	 */
	public boolean gruppeErstellen(String email,boolean[] sicht, String name){
		User user = (User) this.getAnwenderbyID(email);
		for(int i = 0; i<user.getGruppen().size();i++){
			if(user.getGruppen().get(i).getName().equals(name)){
				return false;
			}
		}
		user.gruppeErstellen(name);
		if(sicht[0]){
			user.addSichtbarkeitFreundesliste(name);
		}
		if(sicht[1]){
			user.addSichtbarkeitPinnwand(name);
		}
		if(sicht[2]){
			user.addSichtbarkeitKontaktdaten(name);
		}
		if(sicht[3]){
			user.addSichtbarkeitProfilinfo(name);
		}
		this.resaveAnwender(user);
		return true;
	}
	
	/**
	 * gruppeLoeschen loescht eine Gruppe des uebergebenen Users.
	 * @param email Einlog_Email des Users
	 * @param gruppe ist der Name der uebergebenen Gruppe, die geloescht werden soll.
	 */
	public void gruppeLoeschen(String email, String gruppe){
		User user = (User) this.getAnwenderbyID(email);
		user.gruppeLoeschen(gruppe);
		this.resaveAnwender((Anwender) user);
	}

	/**
	 * gruppeAendern aendert die Gruppenzugehoerigkeit des User freundmail der mit dem User email
	 * befreundet ist.
	 * @param email Einlog_Email des uebergebenen Users, dessen Gruppe geaendert wird
	 * @param freundmail Einlog_Email des uebergebenen Users, dessen Gruppenzugehoerigkeit geaendert wird
	 * @param gruppenname ist der Name der Gruppe der der User freundmail bei der Uebergabe zugehoert
	 */
	public void gruppeAendern(String email, String freundmail, String gruppenname){
		User user = (User) this.getAnwenderbyID(email);
		Freund freund = null;
		for(int i = 0; i<user.getFreunde().size();i++){
			if(user.getFreunde().get(i).getFreunduser().equals(freundmail)){
				freund = user.getFreunde().get(i);
			}
		}
		if(gruppenname.equals("None")){
			for(int i = 0; i<user.getGruppen().size();i++){
				user.getGruppen().get(i).freund_aus_gruppe(freund);
			}
		} else {
			for(int i = 0; i<user.getGruppen().size();i++){
				user.getGruppen().get(i).freund_aus_gruppe(freund);
			}
			for(int i = 0; i<user.getGruppen().size();i++){
				if(user.getGruppen().get(i).getName().equals(gruppenname)){
					user.getGruppen().get(i).freund_zu_gruppe(freund);
				}
			}
		}
		this.resaveAnwender((Anwender) user);
		this.resaveAnwender(this.getAnwenderbyID(freund.getFreunduser()));
	}
	
	/**
	 * profilinfoAendern aendert die Profilinfo des uebergebenen Users.
	 * @param email Einlog_Email des Users
	 * @param vorname der in den Profilinfo vom User festgelegte Vorname
	 * @param nachname der in den Profilinfo vom User festgelegte Nachname
	 * @param geburtsdatum das in den Profilinfo vom User festgelegte Geburtsdatum
	 * @param hobbies die in den Profilinfo vom User festgelegten Hobbies
	 * @param geschlecht das in den Profilinfo vom User festgelegte Geschlecht
	 * @param religion die in den Profilinfo vom User festgelegte/n Religion/en
	 * @param interessen die in den Profilinfo vom User festgelegten Interessen
	 * @param familienstatus der in den Profilinfo vom User festgelegte Familienstatus
	 * @param sexOrientierung die in den Profilinfo vom User festgelegte sexuelle Orientierung
	 * @param polEinstellung die in den Profilinfo vom User festgelegte politische Einstellung
	 * @param zusatzangaben die in den Profilinfo vom User festgelegte Zusatzangaben
	 * @throws ParseException bei fehlerhafter Eingabe
	 */
	public void profilinfoAendern(String email, String vorname, String nachname, String geburtsdatum, String hobbies, String geschlecht, String religion,
			String interessen, String familienstatus, String sexOrientierung, String polEinstellung, String zusatzangaben) throws ParseException{
		Calendar gebDat = null;
		User user = (User) this.getAnwenderbyID(email);
		if(geburtsdatum != null){
			gebDat = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			gebDat.setTime(sdf.parse(geburtsdatum));
		}
		user.getProfilinfo().setAll(vorname, nachname, gebDat, hobbies, geschlecht, religion, interessen, 
				familienstatus, sexOrientierung, polEinstellung, zusatzangaben);
		this.resaveAnwender((Anwender) user);
	}
	
	/**
	 * kontaktDatenAendern aendert die Kontaktdaten des Users
	 * @param emailID Einlog_Email des Users
	 * @param adresse die in den Kontaktdaten festgelegte Adresse
	 * @param wohnort der in den Kontaktdaten festgelegte Wohnort
	 * @param telefonnr die in den Kontaktdaten festgelegte Telefonnummer
	 * @param email die in den Kontaktdaten festgelegte Email
	 */
	public void kontaktDatenAendern(String emailID, String adresse, String wohnort, String telefonnr, String email){
		User user = (User) this.getAnwenderbyID(emailID);
		user.getKontaktdaten().setAll(adresse, wohnort,telefonnr, email);
		this.resaveAnwender((Anwender) user);
	}
	
	/**
	 * durchschnittlicheFreunde gibt die Anzahl der geschlossenen Freundschaften pro User zurueck.
	 * @param berechtigung Email vom Forscher
	 * @return Anzahl der geschlossenen Freundschaften pro User in DecimalFormat ###.00
	 */
	public String durchschnittlicheFreunde(String berechtigung){
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		Forscher forscher = (Forscher) anwenderDAO.getAnwenderbyID(berechtigung);
		DecimalFormat df = new DecimalFormat("###0.00");
		
		return df.format(forscher.anzahl_freundschaften_durchschnitt(help));
	}
	
	/**
	 * FreundeMonat gibt Anzahl der geschlossenen Freundschaften pro User vom letzten Monat
	 * zurueck.
	 * @param berechtigung Email vom Forscher
	 * @return Anzahl der geschlossenen Freundschaften pro User vom letzten Monat in DecimalFormat ###.00
	 */
	public String FreundeMonat(String berechtigung){
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		Forscher forscher = (Forscher) anwenderDAO.getAnwenderbyID(berechtigung);
		DecimalFormat df = new DecimalFormat("###0.00");
		
		return df.format((forscher.anzahl_freundschaften_monat(help)));
	}
	
	/**
	 * FreundeGesamt gibt die Anzahl aller geschlossenen Freundschaften pro User
	 * zurueck.
	 * @param berechtigung Email vom Forscher
	 * @return Anzahl aller geschlossenen Freundschaften pro User in DecimalFormat ###.00
	 */
	public String FreundeGesamt(String berechtigung){
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		Forscher forscher = (Forscher) anwenderDAO.getAnwenderbyID(berechtigung);
		DecimalFormat df = new DecimalFormat("###0.00");
		
		return df.format((forscher.anzahl_freundschaften(help)));
	}
	
	/**
	 * durchschnittlicheBeitraege gibt die Anzahl der geposteteten Beitraege pro User.
	 * @param berechtigung Email vom Forscher
	 * @return Anzahl der geposteten Beitraege pro User in DecimalFormat ###.00
	 */
	public String durchschnittlicheBeitraege(String berechtigung){
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		Forscher forscher = (Forscher) anwenderDAO.getAnwenderbyID(berechtigung);
		DecimalFormat df = new DecimalFormat("###0.00");

		return df.format((forscher.anzahl_beitraege_durchschnitt(help)));
	}
	
	/**
	 * beitraegeMonat gibt die Anzahl der geposteten Beitraege pro User vom letzten Monat
	 * zurueck.
	 * @param berechtigung Email vom Forscher
	 * @return Anzahl der geposteten Beitraege pro User vom letzten Monat in DecimalFormat ###.00
	 */
	public String beitraegeMonat(String berechtigung){
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		Forscher forscher = (Forscher) anwenderDAO.getAnwenderbyID(berechtigung);
		DecimalFormat df = new DecimalFormat("###0.00");

		return df.format((forscher.anzahl_beitraege_monat(help)));
	}
	
	/**
	 * anzahl_userSperren gibt die Anzahl der aktuellen gesperrten User zurueck.
	 * @param berechtigung Email vom Forscher
	 * @return anzahl der aktuell gesperrten User
	 */
	public String anzahl_userSperren(String berechtigung){
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		Forscher forscher = (Forscher) anwenderDAO.getAnwenderbyID(berechtigung);

		return (Integer.toString(forscher.anzahl_sperren(help)));
	}
	
	/**
	 * usersuche ermoeglicht dem User anhand einer Eingabe einen User zu suchen.
	 * @param email Einlog_Email des Benutzers
	 * @param nutzersuche Referenz auf das Objekt, in welches das Ergebnis geschrieben werden soll
	 * @param id Referenz auf das Objekt, in welches das Ergebnis geschrieben werden soll
	 * @param suchwort Eingabe anhan der ein Nutzer gesucht werden soll
	 */
	public void usersuche(String email, ArrayList<String> nutzersuche, ArrayList<String> id, String suchwort){
		User user = (User) this.getAnwenderbyID(email);
		ArrayList<Freund> freunde = user.getFreunde();
		ArrayList<String> freundein = user.getFAnfragenEingehend();
		ArrayList<String> freundeout = user.getFAnfragenAusgehend();
		ArrayList<User> freundhelp = new ArrayList<User>();
		ArrayList<String> freundouthelp = new ArrayList<String>();
		ArrayList<String> freundinhelp = new ArrayList<String>();
		for(int i = 0; i<freunde.size();i++){
			freundhelp.add((User)this.getAnwenderbyID(freunde.get(i).getFreunduser()));
		}
		for(int i = 0; i<freundein.size();i++){
			freundinhelp.add(this.getAnwenderbyID(freundein.get(i)).getEmailID());
		}
		for(int i = 0; i<freundeout.size();i++){
			freundouthelp.add(this.getAnwenderbyID(freundeout.get(i)).getEmailID());
		}
		ArrayList<User> nutzer = this.userSuchenHelp(suchwort);
		boolean vorhanden = false;
		for(int i = 0; i<nutzer.size();i++){
			for(int j = 0; j<freundhelp.size();j++){
				if(freundhelp.get(j) == nutzer.get(i)){
					vorhanden=true;
				}
			}
			if(!vorhanden){
				if(!(nutzer.get(i).getEmailID().equals(user.getEmailID()))){
					String vn;
					String nn;
					if (nutzer.get(i).getProfilinfo().getVorname() != null) {
						vn = nutzer.get(i).getProfilinfo().getVorname();
					}
					else {
						vn="";
					}
					if (nutzer.get(i).getProfilinfo().getNachname() != null) {
						nn = nutzer.get(i).getProfilinfo().getNachname();
					}
					else {
						nn="";
					}
												
					nutzersuche.add(vn + " " + nn);
					id.add(nutzer.get(i).getEmailID());
				}
			}
			vorhanden=false;
		}
		
	}
	
	/**
	 * userSuchenHelp eigentliche Suche und sucht User anhand der Eingabe
	 * @param stichwort uebergebenes Suchwort
	 * @return Liste gefundener User
	 */
	public ArrayList<User> userSuchenHelp(String stichwort){
		ArrayList<Anwender> help = anwenderDAO.loadAnwenderList();
		ArrayList<User> ausgabe = new ArrayList<User>();
		boolean nichtdoppelt = true;
		for(int i = 0; i<help.size();i++){
			if(help.get(i) instanceof User){
				User help2 = (User) help.get(i);
				nichtdoppelt = true;
				if(help2.getProfilinfo().getNachname() != null){
					if(!(help2.getProfilinfo().getNachname().equals("")) ){
						if((StringUtils.getJaroWinklerDistance(help2.getProfilinfo().getNachname(), stichwort) >= 0.7)){
							ausgabe.add(help2);
							nichtdoppelt = false;
						}
					}
				}
				if(nichtdoppelt & help2.getProfilinfo().getVorname() != null){
					if(!(help2.getProfilinfo().getVorname().equals(""))){
						if ((StringUtils.getJaroWinklerDistance(help2.getProfilinfo().getVorname(), stichwort) >= 0.7)){
							ausgabe.add(help2);
						}
					}
					
				}
			}
		}
		return ausgabe;
	}
	
	/**
	 * beitrag melden ermoeglicht das Melden eines Beitrags, der daraufhin von einem Admin ueberprueft
	 * wird.
	 * @param email emailID des Users
	 * @param id id des Beitrags
	 */
	public void beitragMelden(String email, int id){
		User user = (User) this.getAnwenderbyID(email);
		Beitrag beitrag = user.getBeitraege().get(id);
		ArrayList<Anwender> anwender = anwenderDAO.loadAnwenderList();
		for(int i = 0; i<anwender.size(); i++){
			if(anwender.get(i) instanceof Admin){
				Admin help = (Admin) anwender.get(i);
				ArrayList<Beitrag> help2 = help.getGemeldeteBeitraege();
				for(int j = 0; j<help2.size();j++){
					if(help2.get(j) == beitrag){
						break;
					}
				}
				help2.add(beitrag);
				resaveAnwender(anwender.get(i));
			}
		}
		beitrag.setGemeldet(true);
		resaveAnwender(user);
		return;
	}
	
	/**
	 * beitragLoeschen ermoeglicht das Loeschen von einem Beitrag mittels der uebergebenen id.
	 * @param email Einlog_Email des Benutzers
	 * @param id des zu loeschenden Beitrags
	 */
	public void beitragLoeschen(String email, int id){
		User user = (User) this.getAnwenderbyID(email);
		user.beitragLoeschen(id);
		this.resaveAnwender((Anwender) user);
	}
	
	/**
	 * beitragHinzufuegen ermoeglicht einen Beitrag hinzuzufuegen mit einem Titel und einem Inhalt.
	 * @param email Einlog_Email des Benutzers
	 * @param titel ist der Titel des Beitrags
	 * @param inhalt ist der eigentliche Text des Beitrags
	 */
	public void beitragHinzufuegen(String email, String titel, String inhalt){
		User user = (User) this.getAnwenderbyID(email);
		user.beitragHinzufuegen(titel, inhalt);    
		this.resaveAnwender((Anwender) user);
	}
	
	/**
	 * resaveAnwender loescht zunaechst das gesamte anwenderDAO und schreibt dann alles neu rein.
	 * @param anwender vom Typ Anwender, der uebergeben wird und gespeichert werden soll.
	 */
	public void resaveAnwender(Anwender anwender){
		anwenderDAO.loescheAnwender(anwender);
		anwenderDAO.saveAnwender(anwender);
	}
	
	/**
	 * getTyp gibt zurueck ob es sich bei der uebergebenen Email um einen Admin, Forscher oder
	 * User handelt.
	 * @param email Einlog_Email des Benutzers
	 * @return Forscher, Admin oder User Klasse
	 */
	public String getTyp(String email){
		Anwender help = this.getAnwenderbyID(email);
		if(help instanceof Forscher){
			return"Forscher";
		}
		if(help instanceof Admin){
			return"Admin";
		}
		if(help instanceof User){
			return"User";
		}
		return null;
	}
	
}
