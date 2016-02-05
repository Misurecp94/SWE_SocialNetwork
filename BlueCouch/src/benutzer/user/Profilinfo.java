package benutzer.user;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Die Klasse Profilinfo beinhaltet die Eigenschaften bzw. Instanzvariablen und Methoden die vom User
 * bei der Profilinfo geaendert oder indirekt aufgerufen werden koennen.<br>
 * Implementiert "Serializable" um persistente Speicherung zu ermoeglichen.<br>
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf<br>
 * @version 2015_12_09<br>
 */
public class Profilinfo implements Serializable{
	
	/**
	 * Klassenvariable serialVersionUID, Versionsnummer um persistent gespeicherte Objekte wieder einlesen zu koennen.<br>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instanzvariable:
	 * vorname beinhaltet den vom user angegebenen Vornamen.<br>
	 */
	private String vorname;
	
	/**
	 * Instanzvariable:
	 * nachname beinhaltet den vom user angegebenen Nachnamen.<br>
	 */
	private String nachname;
	
	/**
	 * Instanzvariable:
	 * geburtsdatum beinhaltet das vom user angegebene Geburtsdatum.<br>
	 */
	private Calendar geburtsdatum;
	
	/**
	 * Instanzvariable:
	 * hobbies beinhaltet die vom user angegebenen Hobbies.<br>
	 */
	private String hobbies;
	
	/**
	 * Instanzvariable:
	 * geschlecht beinhaltet das vom user angegebene Geschlecht.<br>
	 */
	private String geschlecht;
	
	/**
	 * Instanzvariable:
	 * religion beinhaltet die vom user angegebenen Religionen.<br>
	 */
	private String religion;
	
	/**
	 * Instanzvariable:
	 * interessen beinhaltet die vom user angegebenen Interessen.<br>
	 */
	private String interessen;
	
	/**
	 * Instanzvariable:
	 * familienstatus beinhaltet den vom user angegebenen Familienstatus.<br>
	 */
	private String familienstatus;
	
	/**
	 * Instanzvariable:
	 * sexOrientierung beinhaltet die vom user angegebene sexuelle Orientierung/en.<br>
	 */
	private String sexOrientierung;
	
	/**
	 * Instanzvariable:
	 * polEinstellung beinhaltet die vom user angegebene politische Einstellung.<br>
	 */
	private String polEinstellung;
	
	/**
	 * Instanzvariable:
	 * zusatzangaben beinhaltet zus‰tzliche Angaben, die der user angeben moechte.<br>
	 */
	private String zusatzangaben;
	
	/**
	 * Instanzvariable:
	 * user beinhaltet die einzigartige EmailID eines Users.<br>
	 */
	private String user;
	
	/**
	 * Konstruktor der Klasse Profilinfo laesst mittels setter Methoden alle
	 * Instanzvariable, auﬂer user zunaechst auf null (bzw. beinhalten nichts) zeigen.<br>
	 * @param user von aufrufender Instanz uebergebener Wert beinhaltet die emailID eines users.<br>
	 */
	public Profilinfo(String user){
		setVorname(null);
		setNachname(null);
		setGeburtsdatum(null);
		setHobbies(null);
		setGeschlecht(null);
		setReligion(null);
		setInteressen(null);
		setFamilienstatus(null);
		setSexOrientierung(null);
		setPolEinstellung(null);
		setZusatzangaben(null);
		setUser(user);
	}
	
	/**
	 * setAll ist die Methode die alle Instanzvariabel auf die uebergebenen Werte mittels set Methoden setzt.<br>
	 * @param vorname von aufrufender Instanz uebergebener Parameter beinhaltet den vom user
	 * festgelegten Vornamen.<br>
	 * @param nachname von aufrufender Instanz uebergebener Parameter beinhaltet den vom user
	 * festgelegten Nachnamen.<br>
	 * @param geburtsdatum von aufrufender Instanz uebergebener Parameter beinhaltet das vom user
	 * festgelegte Geburtsdatum .<br>
	 * @param hobbies von aufrufender Instanz uebergebener Parameter beinhaltet die vom user
	 * festgelegten Hobbies.<br>
	 * @param geschlecht von aufrufender Instanz uebergebener Parameter beinhaltet das vom user
	 * festgelegte Geschlecht.<br>
	 * @param religion von aufrufender Instanz uebergebener Parameter beinhaltet die vom user
	 * festgelegte/n Religionen .<br>
	 * @param interessen von aufrufender Instanz uebergebener Parameter beinhaltet die vom user
	 * festgelegten Interessen.<br>
	 * @param familienstatus von aufrufender Instanz uebergebener Parameter beinhaltet den vom user
	 * festgelegten Familienstatus.<br>
	 * @param sexOrientierung von aufrufender Instanz uebergebener Parameter beinhaltet die vom user
	 * festgelegte sexuelle Orientierung.<br>
	 * @param polEinstellung von aufrufender Instanz uebergebener Parameter beinhaltet die vom user
	 * festgelegte politische Einstellung.<br>
	 * @param zusatzangaben von aufrufender Instanz uebergebener Parameter beinhaltet die vom user
	 * festgelegten Zusatzangaben.<br>
	 */
	public void setAll(String vorname, String nachname, Calendar geburtsdatum, String hobbies, String geschlecht, String religion,
			String interessen, String familienstatus, String sexOrientierung, String polEinstellung, String zusatzangaben){
		setVorname(vorname);
		setNachname(nachname);
		setGeburtsdatum(geburtsdatum);
		setHobbies(hobbies);
		setGeschlecht(geschlecht);
		setReligion(religion);
		setInteressen(interessen);
		setFamilienstatus(familienstatus);
		setSexOrientierung(sexOrientierung);
		setPolEinstellung(polEinstellung);
		setZusatzangaben(zusatzangaben);
	}
	
	/**
	 * getVorname gibt die Instanzvariable vorname zurueck.<br>
	 * @return vorname der vom user festgelegt wurde.<br>
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * setVorname setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param vorname von aufrufender Instanz uebergebener Parameter beinhaltet den vom
	 * user festgelegten Vornamen.<br>
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * getNachname gibt die Instanzvariable nachname zurueck.<br>
	 * @return nachname der vom user festgelegt wurde.<br>
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * setNachname setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param nachname von aufrufender Instanz uebergebener Parameter beinhaltet den vom
	 * user festgelegten Nachnamen.<br>
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * getGeburtsdatum gibt die Instanzvariable geburtsdatum zurueck.<br>
	 * @return geburtsdatum das vom user festgelegt wurde.<br>
	 */
	public Calendar getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * setGeburtsdatum setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param geburtsdatum von aufrufender Instanz uebergebener Parameter beinhaltet das vom
	 * user festgelegte Geburtsdatum.<br>
	 */
	public void setGeburtsdatum(Calendar geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	/**
	 * getHobbies gibt die Instanzvariable hobbies zurueck.<br>
	 * @return hobbies die vom user festgelegt wurden.<br>
	 */
	public String getHobbies() {
		return hobbies;
	}

	/**
	 * setHobbies setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param hobbies von aufrufender Instanz uebergebener Parameter beinhaltet die vom
	 * user festgelegten Hobbies.<br>
	 */
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	/**
	 * getGeschlecht gibt die Instanzvariable geschlecht zurueck.<br>
	 * @return geschlecht das vom user festgelegt wurde.<br>
	 */
	public String getGeschlecht() {
		return geschlecht;
	}

	/**
	 * setGeschlecht setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param geschlecht von aufrufender Instanz uebergebener Parameter beinhaltet das vom
	 * user festgelegte Geschlecht.<br>
	 */
	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}

	/**
	 * getReligion gibt die Instanzvariable religion zurueck.<br>
	 * @return religion die vom user festgelegt wurden.<br>
	 */
	public String getReligion() {
		return religion;
	}

	/**
	 * setReligion setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param religion von aufrufender Instanz uebergebener Parameter beinhaltet die vom
	 * user festgelegte/n Religion/en.<br>
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}

	/**
	 * getInteressen gibt die Instanzvariable interessen zurueck.<br>
	 * @return interessen die vom user festgelegt wurden.<br>
	 */
	public String getInteressen() {
		return interessen;
	}

	/**
	 * setInteressen setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param interessen von aufrufender Instanz uebergebener Parameter beinhaltet die vom
	 * user festgelegten Interessen.<br>
	 */
	public void setInteressen(String interessen) {
		this.interessen = interessen;
	}

	/**
	 * getFamilienstatus gibt die Instanzvariable familienstatus zurueck.<br>
	 * @return familienstatus der vom user festgelegt wurde.<br>
	 */
	public String getFamilienstatus() {
		return familienstatus;
	}

	/**
	 * setFamilienstatus setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param familienstatus von aufrufender Instanz uebergebener Parameter beinhaltet den vom
	 * user festgelegten Familienstatus.<br>
	 */
	public void setFamilienstatus(String familienstatus) {
		this.familienstatus = familienstatus;
	}

	/**
	 * getSexOrientierung gibt die Instanzvariable sexOrientierung zurueck.<br>
	 * @return sexOrientierung die vom user festgelegt wurde.<br>
	 */
	public String getSexOrientierung() {
		return sexOrientierung;
	}

	/**
	 * setSexOrientierung setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param sexOrientierung von aufrufender Instanz uebergebener Parameter beinhaltet die vom
	 * user festgelegte sexuelle Orientierung .<br>
	 */
	public void setSexOrientierung(String sexOrientierung) {
		this.sexOrientierung = sexOrientierung;
	}

	/**
	 * getPolEinstellung gibt die Instanzvariable polEinstellung zurueck.<br>
	 * @return polEinstellung die vom user festgelegt wurde.<br>
	 */
	public String getPolEinstellung() {
		return polEinstellung;
	}

	/**
	 * setPolEinstellung setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param polEinstellung von aufrufender Instanz uebergebener Parameter beinhaltet die vom
	 * user festgelegte politische Einstellung.<br>
	 */
	public void setPolEinstellung(String polEinstellung) {
		this.polEinstellung = polEinstellung;
	}

	/**
	 * getZusatzangaben gibt die Instanzvariable zusatzangaben zurueck.<br>
	 * @return zusatzangaben die vom user festgelegt wurden.<br>
	 */
	public String getZusatzangaben() {
		return zusatzangaben;
	}

	/**
	 * setZusatzangaben setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param zusatzangaben von aufrufender Instanz uebergebener Parameter beinhaltet die vom
	 * user festgelegten Zusatzangaben.<br>
	 */
	public void setZusatzangaben(String zusatzangaben) {
		this.zusatzangaben = zusatzangaben;
	}

	/**
	 * getUser gibt die Instanzvariabel user zurueck.<br>
	 * @return user ist die einzigartige emailID eines user.<br>
	 */
	public String getUser() {
		return user;
	}

	/**
	 * setUser setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param user von aufrufender Instanz uebergebener Parameter beinhaltet die
	 * einzigartige emailID die der user beim Registrieren festgelegt hat.<br>
	 */
	public void setUser(String user) {
		this.user = user;
	}
}
