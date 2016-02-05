package benutzer;

import java.io.Serializable;
import java.util.Calendar;
/**
 * Klasse Anwender beinhaltet Hauptmethoden und Variablen die an den Forscher, Admin und User vererbt
 * werden. Implementiert "Serializable" um persistente Speicherung zu ermoeglichen.<br>
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf<br>
 * @version 2015_12_09<br>
 */
public abstract class Anwender implements Serializable{

	/**
	 * Klassenvariable serialVersionUID, Versionsnummer um persistent gespeicherte Objekte wieder einlesen zu koennen.<br>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Instanzvariable:<br>
	 * Eine emailID gibt jeder Anwender beim registrieren ein, mit dieser kann er sich dann auch einloggen.
	 * Die emailID ist einzigartig.<br>
	 */
	private String emailID;
	/**
	 * Instanzvariable:<br>
	 * Ein password gibt jeder Anwender beim Registrieren ein, mit diesem kann er sich dann auch einloggen.<br>
	 */
	private String password;
	
	/**
	 * Instanzvariable:<br>
	 * Die Variable erstellt beinhaltet das Calendar Datum, an dem der Anwender sich registriert hat.<br>
	 */
	private Calendar erstellt;
	
	/**
	 * Konstruktor der Klasse Anwender. Weisst den Instanzvariablen mittels setter Methoden
	 * Werte der jeweils uebergebenen Parameter zu.<br>
	 * @param emailID von aufrufender Instanz uebergebener Parameter, beinhaltet die einzigartige vom
	 * Anwenders festgelegte EmailID.<br>
	 * @param password von aufrufender Instanz uebergebener Parameter, beinhaltet das vom Anwender
	 * festgelegte Password.<br>
	 */
	public Anwender(String emailID, String password){
		this.erstellt = Calendar.getInstance();
		setPassword(password);
		setEmailID(emailID);
	}
	
	/**
	 * getPassword gibt die Instanzvariable password zurueck.<br>
	 * @return password, das vom Anwender festgelegt wird.<br>
	 */
	public String getPassword(){
		return this.password;
	}
	
	/**
	 * setPassword setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param password von aufrufender Instanz uebergebener Parameter, beinhaltet das vom
	 * Anwender festgelegte Password.<br>
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * getEmailID gibt die Instanzvariable emailID zurueck.<br>
	 * @return emailID, die vom Anwender beim Registrieren festgelegt wird und einzigartig
	 * sein muss.<br>
	 */
	public String getEmailID(){
		return this.emailID;
	}
	
	/**
	 * setEmailID setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param emailID von aufrufender Instanz uebergebener Parameter, beinhaltet die vom
	 * Anwender festgelegte EmailID.<br>
	 */
	public void setEmailID(String emailID){
		this.emailID = emailID;
	}
	
	/**
	 * getErstellt gibt die Instanzvariable erstellt zurueck.<br>
	 * @return erstellt von aufrufender Instanz uebergebener Parameter, beinhaltet
	 * das Calendar Datum, an dem sich der Anwender registriert hat.<br>
	 */
	public Calendar getErstellt(){
		return this.erstellt;
	}
	
}
