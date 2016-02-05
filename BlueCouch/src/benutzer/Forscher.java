package benutzer;

import java.util.ArrayList;
import java.util.Calendar;
/**
 * Klasse Forscher beinhaltet Methoden und Variablen auf die der Forscher indirekt zugreifen kann.
 * Forscher erbt von Klasse Anwender<br>
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf<br>
 * @version 2015_12_09<br>
 */
public class Forscher extends Anwender{

	/**
	 * Klassenvariable serialVersionUID, Versionsnummer um persistent gespeicherte Objekte wieder einlesen zu koennen.<br>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor der Klasse Forscher. Ruft mittels super den Konstruktor von Anwender auf
	 * und weisst den Instanzvariable Werte der uebergebenen Parameter zu.<br>
	 * @param emailID von aufrufender Instanz uebergebener Parameter, beinhaltet die einzigartige EmailID,
	 * die vom Forscher beim Registrieren festgelegt wird.<br>
	 * @param password von aufrufender Instanz uebergebener Parameter, beinhaltet das password,
	 * das vom Forscher beim Registrieren festgelegt wird.<br>
	 */
	public Forscher(String emailID, String password) {
		super(emailID, password);
	}

	/**
	 * anzahl_beitraege_durchschnitt berechnet die Anzahl an Beitraegen
	 * die pro User. Aus der gesamten Anwenderliste werden die User rausgefiltert und
	 * deren Beitraege aufsummiert, sowie dadurch die Anzahl der User bestimmt.<br>
	 * @param userlist von aufrufender Instanz uebergebener Parameter, beinhaltet eine Liste
	 * aller Anwender.<br>
	 * @return sumBei/AnzahlUser, wobei sumBei die Summe aller geposteten Beitraege beinhaltet
	 * und AnzahlUser die Summe aller User(keine Admin, keine Forscher) beinhaltet.<br>
	 */
	public double anzahl_beitraege_durchschnitt(ArrayList<Anwender> userlist){
		double sumBei = 0;
		double AnzahlUser = 0;
		for(int i = 0; i<userlist.size();i++){
			if(userlist.get(i) instanceof User){
				User help2 = (User) userlist.get(i);
				sumBei+= help2.anzahlBeitraege();
				AnzahlUser++;
			}
		}
		if(AnzahlUser == 0) return 0;
		return sumBei / AnzahlUser;
	}
	
	/**
	 * anzahl_beitraege_monat berechnet die Anzahl der Beitraege pro User vom letzten Monat. Zunaechst
	 * wird festgestellt ob es sich beim heutigen Monat um Jaenner handelt, dann wird ab Dezember vom 
	 * vorigen Jahr gerechnet, ansonst wird ein Monat vom heutigen Datum subtrahiert und dann
	 * mittels der anzahlBeitraegeZeit Methode die Anzahl der Beitraege von jedem User ab dem
	 * uebergebenen Datum(=stichtag) berechnet.<br>
	 * @param userlist von aufrufender Instanz uebergebener Parameter, beinhaltet eine ArrayList
	 * aller Anwender<br>
	 * @return sumBei/AnzahlUser, wobei sumBei die Summe aller geposteten Beitraege beinhaltet
	 * und AnzahlUser die Summe aller User(keine Admin, keine Forscher) beinhaltet.<br>
	 */
	public double anzahl_beitraege_monat(ArrayList<Anwender> userlist){
		double sumBei = 0;
		double AnzahlUser = 0;
		Calendar stichtag = Calendar.getInstance();
		if(stichtag.get(Calendar.MONTH) == 0){
			stichtag.set(Calendar.MONTH,11);
			stichtag.set(Calendar.YEAR, stichtag.get(Calendar.YEAR)-1);}
		else {
			stichtag.set(Calendar.MONTH, stichtag.get(Calendar.MONTH)-1);}
		//System.out.println(stichtag.get(Calendar.MONTH));
		for(int i = 0; i<userlist.size();i++){
			if(userlist.get(i) instanceof User){
				User help2 = (User) userlist.get(i);
				sumBei+= help2.anzahlBeitraegeZeit(stichtag);
				AnzahlUser++;
			}
		}
		if(AnzahlUser == 0) return 0;
		return sumBei / AnzahlUser;
	}
	
	/**
	 * anzahl_freundschaften_durchschnitt berechnet der geschlossenen Freundschaften
	 * pro User. Aus der gesamten Anwenderliste werden die User rausgefiltert und
	 * deren anzahlFreunde aufsummiert, sowie die Anzahl der User bestimmt.<br>
	 * @param userlist von aufrufender Instanz uebergebener Parameter, beinhaltet eine ArrayList
	 * aller Anwender<br>
	 * @return sumFreunde/AnzahlUser, wobei sumFreunde die Summe aller geschlossenen Freundschaftsbeziehungen
	 * beinhaltet und AnzahlUser die Summe aller User(keine Admin, keine Forscher) beinhaltet.<br>
	 */
	public double anzahl_freundschaften_durchschnitt(ArrayList<Anwender> userlist){
		double sumFreunde = 0;
		double AnzahlUser = 0;
		for(int i = 0; i<userlist.size();i++){
			if(userlist.get(i) instanceof User){
				User help2 = (User) userlist.get(i);
				sumFreunde+= help2.anzahlFreunde();
				AnzahlUser++;
			}
		}
		if(AnzahlUser == 0) return 0;
		return  sumFreunde / AnzahlUser;
	}
	
	/**
	 * anzahl_freundschaften_monat berechnet die Anzahl der geschlossenen Freundschaften
	 * pro User vom letzten Monat. Zunaechst wird festgestellt ob es sich beim heutigen Monat
	 * um Jaenner handelt, dann wird ab Dezember vom vorigen Jahr gerechnet, ansonst wird ein Monat
	 * vom heutigen Datum subtrahiert und dann mittels der anzahlFreundeZeit Methode die Anzahl der
	 * geschlossenen Freundschaften von jedem User ab dem uebergebenen Datum(=stichtag) berechnet.<br>
	 * @param userlist von aufrufender Instanz uebergebener Parameter, beinhaltet eine ArrayList
	 * aller Anwender<br>
	 * @return sumFreunde/AnzahlUser, wobei sumFreunde die Summe aller geschlossenen Freundschaftsbeziehungen
	 * beinhaltet und AnzahlUser die Summe aller User(keine Admin, keine Forscher) beinhaltet.<br>
	 */
	public double anzahl_freundschaften_monat(ArrayList<Anwender> userlist){
		double sumFreunde = 0;
		double AnzahlUser = 0;
		Calendar stichtag = Calendar.getInstance();
		if(stichtag.get(Calendar.MONTH) == 0){
			stichtag.set(Calendar.MONTH,11);
			stichtag.set(Calendar.YEAR, stichtag.get(Calendar.YEAR)-1);}
		else {
			stichtag.set(Calendar.MONTH, stichtag.get(Calendar.MONTH)-1);}
		//System.out.println(stichtag.get(Calendar.MONTH));
		for(int i = 0; i<userlist.size();i++){
			if(userlist.get(i) instanceof User){
				User help2 = (User) userlist.get(i);
				sumFreunde+= help2.anzahlFreundeZeit(stichtag);
				AnzahlUser++;
			}
		}
		if(AnzahlUser == 0) return 0;
		return sumFreunde/AnzahlUser;			// / AnzahlUser?
	}
	
	/**
	 * anzahl_freundschaften berechnet die Anzahl aller geschlossenen Freundschaften pro User.<br>
	 * @param userlist von aufrufender Instanz uebergebener Parameter, beinhaltet eine ArrayList
	 * aller Anwender<br>
	 * @return sumFreunde/2, wobei sumFreunde die Summe aller geschlossenen Freundschaftsbeziehungen
	 * beinhaltet<br>
	 */
	public double anzahl_freundschaften(ArrayList<Anwender> userlist){
		double sumFreunde = 0;
		for(int i = 0; i<userlist.size();i++){
			if(userlist.get(i) instanceof User){
				User help2 = (User) userlist.get(i);
				sumFreunde+= help2.anzahlFreunde() ;
			}
		}
		return sumFreunde/2;
	}
	
	/**
	 * anzahl_sperren  berechnet die Anzahl aktuell gesperrter User.<br>
	 * @param userlist von aufrufender Instanz uebergebener Parameter, beinhaltet eine ArrayList
	 * aller Anwender<br>
	 * @return sumSperr Anzahl aktuell gesperrter User<br>
	 */
	public int anzahl_sperren(ArrayList<Anwender> userlist){
		int sumSperr = 0;
		for(int i = 0; i<userlist.size();i++){
			if(userlist.get(i) instanceof User){
				User help2 = (User) userlist.get(i);
				if (help2.getGesperrt())
					sumSperr++ ;
			}
		}
		return sumSperr;
	}

}
