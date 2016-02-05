package benutzer.user;

import java.io.Serializable;

/**
 * Klasse Kontaktdaten, welche alle Attribute des Users hinsichtlich seiner Kontaktdaten und Methoden, 
 * welche der User zur Verwaltung seiner Kontaktdaten benoetigt,verwaltet.
 * 
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raoul
 * @version 2015/12/09
 */
public class Kontaktdaten implements Serializable{
	
	/**
	 * serialVersionUID zur persistenten Speicherung<br>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *  adresse beinhaltet Adresse die User angibt<br>
	 */
	private String adresse;
	
	/**
	 *  wohnort beinhaltet Wohnort den User angibt<br>
	 */
	private String wohnort;	
	
	/**
	 *  telefonnr beinhaltet TelefonNummer welche User angibt<br>
	 */
	private String telefonnr;
	
	/**
	 * email beinhaltet Email welche User angibt. Nicht verwechseln mit EmailID des Anwenders!<br>
	 */
	private String email;
	
	/**
	 * user beinhaltet den, der Kontaktinfo zugehoerigen User (dessen EmailID genau gesagt)<br>
	 */
	private String user;
	
	/**
	 * Konstruktor der Klasse Kontaktdaten, setzt Standartmaessig alle Werte auf null(mit setter Methoden) (ausgenommen user, welcher mittels
	 * Methode setUser gesetzt wird und von aufrufender Instanz uebergeben wird)
	 * @param user User der dem Objekt vom Typ Kontaktdaten zugehoerig ist.
	 */
	public Kontaktdaten(String user){
		setAdresse(null);
		setWohnort(null);
		setTelefonnr(null);
		setEmail(null);
		setUser(user);
	}
	
	/**
	 * Methode setAll zum gleichzeitigen Setzen aller Werte (vermeidet bzw. buendelt multiplen set Aufruf)
	 * 
	 * @param adresse Adresse dessen Wert die Instanzvariable adresse bekommen soll.
	 * @param wohnort Wohnort dessen Wert die Instanzvariable wohnort bekommen soll.
	 * @param telefonnr TelefonNummer dessen Wert die Instanzvariable telefonnr bekommen soll.
	 * @param email Email dessen Wert die Instanzvariable email bekommen soll.
	 */
	public void setAll(String adresse, String wohnort, String telefonnr, String email){
		setAdresse(adresse);
		setWohnort(wohnort);
		setTelefonnr(telefonnr);
		setEmail(email);
	}
	
	/**
	 * Getter fuer Instanzvariable adresse
	 * @return Adresse des Users
	 */
	public String getAdresse() {
		return adresse;
	}
	
	/**
	 * Setter fuer Instanzvariable adresse
	 * @param adresse Eingegebener Wert, dessen Wert der Instanzvariable adresse zugewiesen werden soll
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
	 * Getter fuer Instanzvariable wohnort
	 * @return Wohnort des Users
	 */
	public String getWohnort() {
		return wohnort;
	}
	
	/**
	 * Setter fuer Instanzvariable wohnort
	 * @param wohnort Eingegebener Wert, dessen Wert der Instanzvariable wohnort zugewiesen werden soll
	 */
	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}
	
	/**
	 * Getter fuer Instanzvariable telefonnr
	 * @return TelefonNummer des Users
	 */
	public String getTelefonnr() {
		return telefonnr;
	}
	
	/**
	 * Setter fuer Instanzvariable telefonnr
	 * @param telefonnr Eingegebener Wert, dessen Wert der Instanzvariable telefonnr zugewiesen werden soll
	 */
	public void setTelefonnr(String telefonnr) {
		this.telefonnr = telefonnr;
	}
	
	/**
	 * Getter fuer Instanzvariable email
	 * @return EMail des Users
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Setter fuer Instanzvariable email
	 * @param email Eingegebener Wert, dessen Wert der Instanzvariable email zugewiesen werden soll
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Getter fuer Instanzvariable user
	 * @return User der dem Objekt vom Typ Kontaktdaten zugehoerig ist
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Setter fuer Instanzvariable user
	 * @param user Eingegebener Wert, dessen Wert der Instanzvariable user zugewiesen werden soll. Beinhaltet im
	 * Normalfall die EmailID des Users, der dem Objekt der Klasse Kontaktdaten zugehoerig ist.
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
	
}
