package benutzer;

import java.util.ArrayList;
import java.util.Calendar;
import benutzer.user.*;
/**
 * Klasse User beinhaltet Methoden und Variablen auf die der Endnutzer(User), der sich als User registriert indirekt
 * zugreifen kann.<br>
 * User erbt von Klasse Anwender.
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf
 * @version 2015_12_09
 */
public class User extends Anwender{

	/**
	 * Klassenvariable serialVersionUID, Versionsnummer um persistent gespeicherte Objekte wieder einlesen zu koennen.<br>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Instanzvariable:<br>
	 * User koenen die sichtbarkeitFreundesliste(Arraylist(Gruppe)) aendern, d.h. entwender sehen
	 * oder nicht sehen fuer eine Gruppe in der ArrayList(Gruppe).<br>
	 */
	private ArrayList<Gruppe> sichtbarkeitFreundesliste;
	/**
	 * Instanzvariable:<br>
	 * User koenen die sichtbarkeitPinnwand(Arraylist(Gruppe)) aendern, d.h. entwender sehen
	 * oder nicht sehen fuer eine Gruppe in der ArrayList(Gruppe).<br>
	 */
	private ArrayList<Gruppe> sichtbarkeitPinnwand;
	/**
	 * Instanzvariable:<br>
	 * User koenen die sichtbarkeitKontaktdaten(Arraylist(Gruppe)) aendern, d.h. entwender sehen
	 * oder nicht sehen fuer eine Gruppe in der ArrayList(Gruppe).<br>
	 */
	private ArrayList<Gruppe> sichtbarkeitKontaktdaten;
	/**
	 * Instanzvariable:<br>
	 * User koenen die sichtbarkeitProfilinfo(Arraylist((Gruppe)) aendern, d.h. entwender sehen
	 * oder nicht sehen fuer eine Gruppe in der ArrayList(Gruppe).<br>
	 */
	private ArrayList<Gruppe> sichtbarkeitProfilinfo;
	/**
	 * Instanzvariable:<br>
	 * User koennen vom Admin gesperrt(boolean) werden.<br>
	 */
	private boolean gesperrt;
	/**
	 * Instanzvariable:<br>
	 * User koennen vom Admin bis zum einem bestimmten Datum gesperrtBis(Calendar) werden.<br>
	 */
	private Calendar gesperrtBis;
	/**
	 * Instanzvariable:<br>
	 * sperrNr(int) gibt die Anzahl an, wie oft ein User gesperrt worden ist.<br>
	 */
	private int sperrNr;
	/**
	 * Instanzvariable:<br>
	 * kontaktdaten(Kontaktdaten) dient dazu um auf die Methoden der Klasse Kontaktdaten zuzugreifen.<br>
	 * Die kontaktdaten(Kontaktdaten) kann der User aendern, sie beinhalten unter anderem Wohnort, Adresse, etc.<br>
	 */
	private Kontaktdaten kontaktdaten;
	/**
	 * Instanzvariable:<br>
	 * profilinfo(Profilinfo) dient dazu um auf die Methoden der Klasse Profilinfo zuzugreifen.<br>
	 * Die profilinfo(Profilinfo) kann der User aendern, sie beinhalten unter anderem Vor/Nachname, 
	 * Hobbys, Geschlecht, etc.<br>
	 */
	private Profilinfo profilinfo;
	/**
	 * Instanzvariable:<br>
	 * Aus den Objekten der Klasse Beitrag entsteht ein ArrayList durch beitraege(ArrayList(Beitrag)). 
	 * beitraege(ArrayList(Beitrag)) dient dazu auf die Objekte der Klasse Beitrag zuzugreifen.<br>
	 * User kann beitraege(ArrayList(Beitrag)) hinzufuegen, loeschen und melden.<br>
	 */
	private ArrayList<Beitrag> beitraege;
	/**
	 * Instanzvariable:<br>
	 * Aus den Objekten der Klasse Freund entsteht ein ArrayList durch freunde(ArrayList(Freund)). 
	 * freunde(ArrayList(Freund)) dient dazu auf die Objekte der Klasse Freund zuzugreifen. <br>
	 * User kann freunde(ArrayList(Freund)) hinzufuegen, loeschen.<br>
	 */
	private ArrayList<Freund> freunde;
	/**
	 * Instanzvariable:<br>
	 * User kann ein benutzerbild(File) hochladen<br>
	 */
	private String benutzerbild;							// Typ vlt aendern (je nachdem wie hochladen implementiert)
	/**
	 * Instanzvariable:<br>
	 * Aus den Objekten der Klasse Gruppe entsteht ein ArrayList durch gruppen(ArrayList(Gruppe)). <br>
	 * gruppen(ArrayList(Gruppe)) dient dazu auf die Objekte der Klasse Gruppe zuzugreifen. <br>
	 * User kann gruppen(ArrayList(Gruppe)) hinzufuegen, loeschen.<br>
	 */
	private ArrayList<Gruppe> gruppen;
	/**
	 * Instanzvariable:<br>
	 * fAnfragenAusgehend(ArrayList(String)) speichert alle Freundschaftsanfragen ab, die ein User 
	 * versendet hat.<br>
	 */
	private ArrayList<String> fAnfragenAusgehend;
	/**
	 * Instanzvariable:<br>
	 * fAnfragenEingehend(ArrayList(String)) speichert alle (eingehenden) Freundschaftsanfragen ab,
	 * die ein User erhalten hat.<br>
	 */
	private ArrayList<String> fAnfragenEingehend;
	
	/**
	 * Konstruktor des Users weisst allen ArrayList Instanzvariablen eine ArrayList vom selben Typ zu.<br> 
	 * Konstruktor weisst mittels setter Methoden Werte der jeweils uebergebenen Parameter zu. <br>
	 * setGesperrt wird zunaechst auf false gesetzt. <br>
	 * set GesperrtBis beinhaltet auch noch kein Datum. <br>
	 * setSperrNr wird auf 0 gesetzt. <br>
	 * setBenutzerbild zeigt zunaechst auf kein Benutzerbild.<br> 
	 * @param emailID von aufrufender Instanz uebergebener Parameter, 
	 * beinhaltet die Email des Users.<br>
	 * @param password von aufrufender Instanz uebergebener Parameter, 
	 * User kann das Password festlegen mit dem er sich einloggt.<br>
	 */
	public User(String emailID, String password){
		super(emailID, password);
		this.sichtbarkeitFreundesliste = new ArrayList<Gruppe>();
		this.sichtbarkeitPinnwand = new ArrayList<Gruppe>();
		this.sichtbarkeitKontaktdaten = new ArrayList<Gruppe>();
		this.sichtbarkeitProfilinfo = new ArrayList<Gruppe>();
		setGesperrt(false);
		setGesperrtBis(null);
		setSperrNr(0);
		this.kontaktdaten = new Kontaktdaten(this.getEmailID());
		this.profilinfo = new Profilinfo(this.getEmailID());
		this.beitraege = new ArrayList<Beitrag>();
		this.freunde = new ArrayList<Freund>();
		setBenutzerbild(null);
		this.gruppen = new ArrayList<Gruppe>();
		this.fAnfragenAusgehend = new ArrayList<String>();
		this.fAnfragenEingehend = new ArrayList<String>();
	}
	
	// Methode zum Hochladen des Bilds fehlt!! (in AnwenderManagement??)

	/**
	 * addSichtbarkeitFreundesliste fuegt der sichtbarkeitFreundesliste(ArrayList(Gruppe)) einen weiteren name(String) einer Gruppe hinzu.<br> 
	 * Falls der Name in der Liste vorhanden ist, wird keine weitere Gruppe hinzugefuegt, falls nicht, 
	 * dann wird die Gruppe mit dem uebergebenen Namen hinzugefuegt.<br>
	 * @param name von aufrufender Instanz uebergebener Parameter,
	 * name ist der Name einer Gruppe.<br>
	 */
	public void addSichtbarkeitFreundesliste(String name){
		for(int i = 0; i<this.gruppen.size();i++){
			if(name.equals(this.gruppen.get(i).getName())){
				for(int j = 0; j<this.getSichtbarkeitFreundeslist().size();j++){
					if(name.equals(this.getSichtbarkeitFreundeslist().get(j).getName())){
						return;
					}
				}
				this.getSichtbarkeitFreundeslist().add(this.gruppen.get(i));
			}
		}
	}
	
	/**
	 * addSichtbarkeitPinnwand fuegt der sichtbarkeitPinnwand(ArrayList(Gruppe)) einen weiteren name(String) einer Gruppe hinzu. <br>
	 * Falls der Name in der Liste vorhanden ist, wird keine weitere Gruppe hinzugefuegt, falls nicht,
	 * dann wird die Gruppe mit dem uebergebenen Namen hinzugefuegt.<br>
	 * @param name von aufrufender Instanz uebergebener Parameter,
	 * name ist der Name einer Gruppe.<br>
	 */
	public void addSichtbarkeitPinnwand(String name){
		for(int i = 0; i<this.gruppen.size();i++){
			if(name.equals(this.gruppen.get(i).getName())){
				for(int j = 0; j<this.getSichtbarkeitPinnwand().size();j++){
					if(name.equals(this.getSichtbarkeitPinnwand().get(j).getName())){
						return;
					}
				}
				this.getSichtbarkeitPinnwand().add(this.gruppen.get(i));
			}
		}
	}
	/**
	 * add SichtbarkeitKontaktdaten fuegt der sichtbarkeitKontaktdaten(ArrayList(Gruppe)) einen weiteren name(String) einer Gruppe hinzu.<br>
	 * Falls der Name in der Liste vorhanden ist, wird keine weitere Gruppe hinzugefuegt, falls nicht,
	 * dann wird die Gruppe mit dem uebergebenen Namen hinzugefuegt.<br>
	 * @param name von aufrufender Instanz uebergebener Parameter,
	 * name ist der Name einer Gruppe.<br>
	 */
	public void addSichtbarkeitKontaktdaten(String name){
		for(int i = 0; i<this.gruppen.size();i++){
			if(name.equals(this.gruppen.get(i).getName())){
				for(int j = 0; j<this.getSichtbarkeitKontaktdaten().size();j++){
					if(name.equals(this.getSichtbarkeitKontaktdaten().get(j).getName())){
						return;
					}
				}
				this.getSichtbarkeitKontaktdaten().add(this.gruppen.get(i));
			}
		}
	}
	
	/**
	 * addSichtbarkeitProfilinfo fuegt der sichtbarkeitProfilinfo(ArrayList(Gruppe)) einen weiteren name(String) einer Gruppe hinzu.<br>
	 * Falls der Name in der Liste vorhanden ist, wird keine weitere Gruppe hinzugefuegt, falls nicht,
	 * dann wird die Gruppe mit dem uebergebenen Namen hinzugefuegt.<br>
	 * @param name von aufrufender Instanz uebergebener Parameter,
	 * name ist der Name einer Gruppe.<br>
	 */
	public void addSichtbarkeitProfilinfo(String name){
		for(int i = 0; i<this.gruppen.size();i++){
			if(name.equals(this.gruppen.get(i).getName())){
				for(int j = 0; j<this.getSichtbarkeitProfilinfo().size();j++){
					if(name.equals(this.getSichtbarkeitProfilinfo().get(j).getName())){
						return;
					}
				}
				this.getSichtbarkeitProfilinfo().add(this.gruppen.get(i));
			}
		}
	}
	
	/**
	 * 
	 * gruppeLoeschen loescht eine Gruppe aus dem ArrayList(Gruppe) und aus den Sichtbarkeitsgruppen
	 * (sichtbarkeitFreundesliste, sichtbarkeitPinnwand, sichtbarkeitKontaktdaten, sichtbarkeitProfilinfo).<br>
	 * @param name von aufrufender Instanz uebergebener Parameter,
	 * name ist der Name einer Gruppe.<br>
	 */
	public void gruppeLoeschen(String name){
		for(int i = 0; i<this.sichtbarkeitFreundesliste.size();i++){
			if(this.sichtbarkeitFreundesliste.get(i).getName().equals(name)){
				this.sichtbarkeitFreundesliste.remove(i);
				break;
			}
		}
		for(int i = 0; i<this.sichtbarkeitPinnwand.size();i++){
			if(this.sichtbarkeitPinnwand.get(i).getName().equals(name)){
				this.sichtbarkeitPinnwand.remove(i);
				break;
			}
		}
		for(int i = 0; i<this.sichtbarkeitKontaktdaten.size();i++){
			if(this.sichtbarkeitKontaktdaten.get(i).getName().equals(name)){
				this.sichtbarkeitKontaktdaten.remove(i);
				break;
			}
		}
		for(int i = 0; i<this.sichtbarkeitProfilinfo.size();i++){
			if(this.sichtbarkeitProfilinfo.get(i).getName().equals(name)){
				this.sichtbarkeitProfilinfo.remove(i);
				break;
			}
		}
		for(int i = 0; i<this.gruppen.size();i++){
			if(this.gruppen.get(i).getName().equals(name)){
				for(int j = 0; j<this.gruppen.get(i).getMitglieder().size(); j++){
					this.gruppen.get(i).getMitglieder().get(j).setGruppe(null);
				}
				this.gruppen.remove(i);
				break;
			}
		}
	}
	
	/**
	 * gruppeErstellen erstellt eine Gruppe und fuegt der ArrayList(Gruppe) eine weiter Gruppe mit
	 * dem uebergebenen name(String) hinzu.<br>
	 * @param name von aufrufender Instanz uebergebener Parameter, wobei name ein Name einer Gruppe ist.<br>
	 */
	public void gruppeErstellen(String name){
		Gruppe help = new Gruppe(this.getEmailID(), name);
		this.gruppen.add(help);
	}
	
	/**
	 * beitragHinzufuegen fuegt einen Beitrag, der einen titel, inhalt, EmailID(des beitrag hinzufuegenden Users)
	 * und eine id(=BeitragsID) beinhaltet.<br>
	 * @param titel von aufrufender Instanz uebergebener Parameter beinhaltet den Titel als String des Beitrags.<br>
	 * @param inhalt von aufrufender Instanz uebergebener Parameter beinhaltet den eigentlichen Inhalt
	 * bzw. Text als String des Beitrags.<br>
	 */
	public void beitragHinzufuegen(String titel, String inhalt){
		if(beitraege.size() != 0){
			Beitrag help = new Beitrag(titel, inhalt, this.getEmailID(), beitraege.get(beitraege.size()-1).getID()+1);
			this.beitraege.add(help);
			return;
		} else {
			Beitrag help = new Beitrag(titel, inhalt, this.getEmailID(), 0);
			this.beitraege.add(help);
			return;
		}
	}
	
	/**
	 * anzahlBeitraegeZeit returniert die Anzahl der Beitraege, die seit dem uebergebenen Datum=
	 * help(Calendar) erstellt wurden.<br>
	 * @param help von aufrufender Instanz uebergebener Parameter beinhaltet das Datum, nach welchem
	 * die Anzahl der Beitraege gezaehlt werden sollen.<br>
	 * @return die Anzahl der Beitraege nach uebergebenen Datum help.<br>
	 */
	public int anzahlBeitraegeZeit(Calendar help){
		int anzahlBeitraege = 0;
		for(int i = 0; i<this.beitraege.size();i++){
			if(this.beitraege.get(i).getErstellt().after(help)){
				anzahlBeitraege++;
			}
		}
		return anzahlBeitraege;
	}
	
	/**
	 * anzahlBeitraege gibt die Anzahl aller erstellten Beitraege, anhand der Groesse der
	 * beitraege(ArrayList(Beitrag)) zurueck.<br>
	 * @return anzahl aller erstellten Beitraege.<br>
	 */
	public int anzahlBeitraege(){
		return this.beitraege.size();
	}
	
	/**
	 * beitragLoeschen loescht einen Beitrag mit einer bestimmten id.<br>
	 * @param id der von aufrufenden Instanz uebergebene Parameter, beinhaltet die id des
	 * zu loeschenden Beitrages.<br>
	 */
	public void beitragLoeschen(int id){
		for(int i = 0; i<this.beitraege.size();i++){
			if(this.beitraege.get(i).getID() == id){
				this.beitraege.remove(i);
				break;
			}
		}
	}
	
	/**
	 * freundschaftsanfrageBeantwortet fuegt den User, dessen name(=EmailID) uebergeben worden
	 * ist zu der Liste freunde(ArrayList(Freund)) hinzu und loescht denselben name(=EmailID) aus der Liste
	 * fAnfragenAusgehend(ArrayList(String)) heraus.<br>
	 * Diese Methode wird durchgefuehrt, wenn eine versendete Freundschaftsanfrage positiv beantwortet
	 * worden ist.<br>
	 * @param name der von der aufrufenden Instanz uebergebene Parameter, name ist die EmailID eines
	 * Users.<br>
	 */
	public void freundschaftsanfrageBeantwortet(String name){
		for(int i = 0; i<this.fAnfragenAusgehend.size();i++){
			if(this.fAnfragenAusgehend.get(i).equals(name)){
				Freund help = new Freund(this.getEmailID(), this.fAnfragenAusgehend.get(i));
				this.freunde.add(help);
				this.fAnfragenAusgehend.remove(i);
			}
		}
	}
	
	/**
	 * freundschaftsanfrageBeantworten fuegt den User, dessen name(=EmailID) uebergeben worden
	 * ist zu der Liste freunde(ArrayList(Freund)) hinzu und loescht denselben name(=EmailID) aus der Liste
	 * fAnfragenEingehend(ArrayList(String)) heraus.<br>
	 * Diese Methode wird durchgefuehrt, wenn eine eingehende Freundschaftsanfrage positiv beantwortet
	 * worden ist.<br>
	 * @param name der von der aufrufenden Instanz uebergebene Parameter, name ist die EmailID eines
	 * Users.<br>
	 */
	public void freundschaftsanfrageBeantworten(String name){
		for(int i = 0; i<this.fAnfragenEingehend.size();i++){
			if(this.fAnfragenEingehend.get(i).equals(name)){
				Freund help = new Freund(this.getEmailID(), this.fAnfragenEingehend.get(i));
				this.freunde.add(help);
				this.fAnfragenEingehend.remove(i);
				break;
			}
		}
	}
	/**
	 * freundschaftsanfrageVersenden fuegt den User, dessen name(=EmailID) uebergeben worden
	 * ist zu der Liste fAnfragenAusgehend(ArrayList(String)) hinzu.<br>
	 * Diese Methode wird durchgefuehrt, wenn ein User eine Freundschaftsanfrage an einen
	 * anderen User versendet.<br>
	 * @param name der von der aufrufenden Instanz uebergebene Parameter, name ist die EmailID eines
	 * Users.<br>
	 */
	public void freundschaftsanfrageVersenden(String name){
		this.fAnfragenAusgehend.add(name);
	}
	
	/**
	 * freundLoeschen gleicht zunaechst einen User, dessen name(=EmailID) uebergeben worden ist, mit der liste
	 * freunde und der Mitglieder der gruppen ab und dann wird dieser User(name=EmailID)
	 * aus der liste gruppe und der liste freunde geloescht.<br>
	 * Diese Methode wird aufgerufen, wenn ein User einen Freund entfernt.<br>
	 * @param name der von der aufrufenden Instanz uebergebene Parameter, name ist die
	 * EmailID eines Users.<br>
	 */
	public void freundLoeschen(String name){
		for(int i = 0; i<this.freunde.size();i++){
			if(this.freunde.get(i).getFreunduser().equals(name)){
				if(this.freunde.get(i).getGruppe() != null){
					for(int j = 0; j<this.gruppen.size();j++){
						if(this.freunde.get(i).getGruppe().equals(this.gruppen.get(j).getName())){
								this.gruppen.get(j).getMitglieder().remove(this.freunde.get(i));
								break;
						}
					}
				}
				this.freunde.remove(i);
				break;
			}
		}
	}
	
	/**
	 * anzahlFreundeZeit returniert die Anzahl der Freunde, die seit dem uebergebenen Datum=
	 * zeit(Calendar) erstellt wurden.<br>
	 * @param zeit von aufrufender Instanz uebergebener Parameter beinhaltet das Datum, nach welchem
	 * die Anzahl der Freunde gezaehlt werden sollen.<br>
	 * @return die Anzahl der Freunde nach uebergebenen Datum zeit.<br>
	 */
	public int anzahlFreundeZeit(Calendar zeit){
		int anzahl = 0;
		for(int i = 0; i<this.freunde.size(); i++){
			if(this.freunde.get(i).getBeginn().after(zeit)){
				anzahl++;
			}
		}
		return anzahl;
	}
	
	/**
	 * anzahlFreunde gibt die Anzahl aller erstellten Freunde, anhand der Groesse der
	 * freunde(ArrayList(Freund)) zurück.<br>
	 * @return Instanzvariable freunde, anzahl aller Freunde aller User.<br>
	 */
	public int anzahlFreunde(){
		return this.freunde.size();
	}
	
	/**
	 * getFAnfragenEingehend gibt die eingehenden Freundschaftsanfragen als ArrayList(String) zurueck.<br>
	 * @return ArrayList fAnfragenEingehend(=Instanzvariable) der eingehenden Freundschaftsanfragen.<br>
	 */
	public ArrayList<String> getFAnfragenEingehend(){
		return this.fAnfragenEingehend;
	}
	
	/**
	 * getFAnfragenAusgehend gibt die ausgehenden Freundschaftsanfragen als ArrayList(String) zurueck.<br>
	 * @return ArrayList fAnfragenAusgehend(=Instanzvariable) der eingehenden Freundschaftsanfragen.<br>
	 */
	public ArrayList<String> getFAnfragenAusgehend(){
		return this.fAnfragenAusgehend;
	}
	
	/**
	 * getGruppen gibt Objekte der Klasse Gruppe als ArrayList(Gruppe) zurueck.<br>
	 * @return Instanzvariable gruppen(ArrayList(Gruppen)) in denen mehrere User abgespeichert sind.<br>
	 */
	public ArrayList<Gruppe> getGruppen(){
		return this.gruppen;
	}
	
	/**
	 * getFreunde gibt Objekte der Klasse Freund als ArrayList(Freund) zurueck.<br>
	 * @return Instanzvariable freunde(ArrayList(Freund)) in der mehrere User abgespeichert sind.<br>
	 */
	public ArrayList<Freund> getFreunde(){
		return this.freunde;
	}
	
	/**
	 * getBeitraege gibt Objekte der Klasse Beitrag als ArrayList(Beitrag) zurueck.<br>
	 * @return Instanzvariable beitraege(ArrayList(Beitrag)) in der mehrere Beitraege abgespeichert sind.<br>
	 */
	public ArrayList<Beitrag> getBeitraege(){
		return this.beitraege;
	}
	
	/**
	 * getProfilinfo gibt ein Objekt der Klasse Profilinfo zurueck.<br>
	 * @return Instanzvariable profilinfo, die ein User hat, die unter anderem Vor_Nachname,
	 * Geburtsdatum, Hobbies, etc. beinhaltet.<br>
	 */
	public Profilinfo getProfilinfo(){
		return this.profilinfo;
	}
	/**
	 * getKontaktdaten gibt ein Objekt der Klasse Kontaktdaten zurueck.<br>
	 * @return Instanzvariable kontaktdaten, die ein User hat, die unter anderem Adresse,
	 * Wohnort, etc. beinhaltet.<br>
	 */
	public Kontaktdaten getKontaktdaten(){
		return this.kontaktdaten;
	}
	/**
	 * getSichtbarkeitProfilinfo gibt die sichtbarkeitProfilinfo als ArrayList(Gruppe) zurueck.<br>
	 * @return Instanzvariable sichtbarkeitProfilinfo(ArrayList(Gruppe)), die eine Liste all derer
	 * beinhaltet, die die Profilinfo sehen duerfen.<br>
	 */
	public ArrayList<Gruppe> getSichtbarkeitProfilinfo(){
		return this.sichtbarkeitProfilinfo;
	}
	
	/**
	 * getSichtbarkeitKontaktdaten gibt die sichtbarkeitKontaktdaten als ArrayList(Gruppe) zurueck.<br>
	 * @return Instanzvariable sichtbarkeitKontaktdaten(ArrayList(Gruppe)), die eine Liste all derer
	 * beinhaltet, die die Kontaktdaten sehen duerfen.<br>
	 */
	public ArrayList<Gruppe> getSichtbarkeitKontaktdaten(){
		return this.sichtbarkeitKontaktdaten;
	}
	
	/**
	 * getSichtbarkeitPinnwand gibt die sichtbarkeitPinnwand als ArrayList(Gruppe) zurueck.<br>
	 * @return Instanzvariable sichtbarkeitPinnwand(ArrayList(Gruppe)), die eine Liste all derer
	 * beinhaltet, die die Pinnwand sehen duerfen.<br>
	 */
	public ArrayList<Gruppe> getSichtbarkeitPinnwand(){
		return this.sichtbarkeitPinnwand;
	}
	
	/**
	 * getSichtbarkeitFreundeslist gibt die sichtbarkeitFreundesliste als ArrayList(Gruppe) zurueck.<br>
	 * @return Instanzvariable sichtbarkeitFreundesliste(ArrayList(Gruppe)), die eine Liste all derer
	 * beinhaltet, die die Freundesliste sehen duerfen.<br>
	 */
	public ArrayList<Gruppe> getSichtbarkeitFreundeslist(){
		return this.sichtbarkeitFreundesliste;
	}
	
	/**
	 * switchGesperrt switcht(=wechselt) die Instanzvariabel auf false, wenn sie true ist und sonst
	 * auf true.<br>
	 */
	public void switchGesperrt(){
		if(this.gesperrt == true){
			this.gesperrt = false;
		} else {
			this.gesperrt = true;
		}
	}
	
	/**
	 * setGesperrt setzt die Instanzvariable auf den uebergebenen boolean gesperrt Wert.<br>
	 * @param gesperrt von aufrufender Instanz uebergebener Parameter, der einen boolean Wert
	 * beinhaltet.<br>
	 */
	public void setGesperrt(boolean gesperrt){
		this.gesperrt = gesperrt;
	}
	
	/**
	 * getGesperrt gibt die Instanzvariable gesperrt zurueck.<br>
	 * @return Instanzvariable gesperrt, die einen boolean Wert beinhaltet.<br>
	 */
	public boolean getGesperrt(){
		return this.gesperrt;
	}
	
	/**
	 * setGesperrtBis setzt die Instanzvariable auf das uebergebene gesperrtBis(Calendar) Datum.<br>
	 * @param gesperrtBis von aufrufender Instanz uebergebener Parameter, der das gesperrtBis(Calendar)
	 * Datum beinhaltet, das vom Admin festgesetzt wird.<br>
	 */
	public void setGesperrtBis(Calendar gesperrtBis){
		this.gesperrtBis = gesperrtBis;
	}
	
	/**
	 * getGesperrtBis gibt die die Instanzvariable gesperrtBis zurueck.<br>
	 * @return Instanzvariable gesperrtBis, die das gesperrtBis(Calendar) Datum beinhaltet, das
	 * vom Admin festgesetzt wird.<br>
	 */
	public Calendar getGesperrtBis(){
		return this.gesperrtBis;
	}
	
	/**
	 * addSperrNr inkrementiert die Instanzvariable sperrNr um 1.<br>
	 */
	public void addSperrNr(){
		this.sperrNr++;
	}
	
	/**
	 * setSperrNr setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param sperrNr von aufrufender Instanz uebergebener Parameter, der die Anzahl der Sperrungen beinhaltet.<br>
	 */
	public void setSperrNr(int sperrNr){
		this.sperrNr = sperrNr;
	}
	
	/**
	 * getSperrNr gibt die Instanzvariable sperrNr zurueck.<br>
	 * @return Instanzvariable sperrNr, die die Anzahl der Sperrungen eines Users beinhaltet.<br>
	 */
	public int getSperrNr(){
		return this.sperrNr;
	}
	/**
	 * setBenutzerbild weisst der Instanzvariable benutzerbild das uebergebene File zu.<br>
	 * @param benutzerbild der von aufrufender Instanz uebergebener Parameter, beinhaltet das eine File
	 * mit dem Benutzerbild eines Users.<br>
	 */
	public void setBenutzerbild(String benutzerbild){
		this.benutzerbild = benutzerbild;
	}
	
	/**
	 * getBenutzerbild gibt das File mit dem Benutzerbild zurueck.<br>
	 * @return Instanzvariable benutzerbild, die das File mit dem Benutzerbild beinhaltet.<br>
	 */
	public String getBenutzerbild(){
		return this.benutzerbild;
	}
	
}
