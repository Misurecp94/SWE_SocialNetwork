package benutzer.user;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Durch Klasse Beitrag werden Objekte der Klasse Beitrag erstellt die einen Beitrag beinhalten, der an
 * der eigenen Pinnwand eines Users gepostet wird.<br>
 * Klasse Beitrag definiert nur Methoden zur Abfrage und Neusetzung der Instanzvariablen.<br>
 * Implementiert "Serializable" um persistente Speicherung zu ermoeglichen.<br>
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf<br>
 * @version 2015_12_09<br>
 */
public class Beitrag implements Serializable{

	/**
	 * Klassenvariable serialVersionUID, Versionsnummer um persistent gespeicherte Objekte wieder einlesen zu koennen.<br>
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Instanzvariable:<br>
	 * user ist vom Typ String und beinhaltet die EmailID eines Users.<br>
	 */
	private String user;
	
	/**
	 * Instanzvariable:<br>
	 * inhalt ist vom Typ String und beinhaltet den Text eines Beitrages, der an eine Pinnwand
	 * gepostet wird.<br>
	 */
	private String inhalt;
	
	/**
	 * Instanzvariable:<br>
	 * titel beinhaltet den Titel eines Beitrages, der an der Pinnwand gepostet wird.<br>
	 */
	private String titel;
	
	/**
	 * Instanzvariable:<br>
	 * erstellt beinhaltet das Calendar Datum, an welchem der Beitrag erstellt bzw. an die Pinnwand
	 * gepostet worden ist.<br>
	 */
	private Calendar erstellt;
	
	/**
	 * Instanzvariable:<br>
	 * gemeldet gibt an ob ein Beitrag gemeldet ist oder nicht. Ist by default auf 0 gesetzt.<br>
	 */
	private boolean gemeldet;
	
	/**
	 * Instanzvariable:<br>
	 * id beinhaltet die Beitrags-ID die einzigartig fuer jeden User ist und jeder Beitrag, der gepostet wird erhaelt.<br>
	 */
	private int id;
	
	/**
	 * Konstruktor der Klasse Beitrag definiert mittels setter Methoden den Wert der Instanzvariablen.<br>
	 * @param titel von aufrufender Instanz uebergebener Parameter, beinhaltet den Titel des Beitrags.<br>
	 * @param inhalt von aufrufender Instanz uebergebener Parameter, beinhaltet den inhalt des Beitrags.<br>
	 * @param user von aufrufender Instanz uebergebener Parameter, beinhaltet die emailID eines
	 * Users.<br>
	 * @param id von aufrufender Instanz uebergebener Parameter, beinhaltet die id des Beitrags.<br>
	 */
	public Beitrag(String titel, String inhalt, String user, int id){
		this.user = user;
		setID(id);
		setTitel(titel);
		setInhalt(inhalt);
		this.erstellt = Calendar.getInstance();
		setGemeldet(false);
	}
	
	/**
	 * getID gibt die Instanzvariable id des Beitrags zurueck.<br>
	 * @return id des Beitrags.<br>
	 */
	public int getID(){
		return this.id;
	}
	
	/**
	 * setID setzt die Instanzvariable id des Beitrags auf den uebergebenen Wert.<br>
	 * @param id des Beitrags.<br>
	 */
	public void setID(int id){
		this.id=id;
	}
	
	/**
	 * getGemeldet gibt den Wert der Instanzvariable zurueck true or false, ob der Beitrag
	 * gemeldet ist oder nicht.<br>
	 * @return true or false.<br>
	 */
	public boolean getGemeldet(){
		return gemeldet;
	}
	
	/**
	 * setGemeldet setzt die Instanzvariable gemeldet auf den uebergebenen Wert.<br>
	 * @param wert von aufrufender Instanz uebergebener Parameter beinhaltet false or true.<br>
	 */
	public void setGemeldet(boolean wert){
		this.gemeldet = wert;
	}
	
	/**
	 * getUser gibt die Instanzvariable user zurueck.<br>
	 * @return user ist die emailID eines Users.<br>
	 */
	public String getUser(){
		return this.user;
	}
	
	/**
	 * getErstellt gibt die Instanzvariable erstellt zurueck.<br>
	 * @return erstellt beinhaltet das Calendar Datum, an dem der Beitrag gepostet worden ist.<br>
	 */
	public Calendar getErstellt(){
		return this.erstellt;
	}
	
	/**
	 * getTitel gibt die Instanzvariable titel zurueck.<br>
	 * @return titel beinhaltet den Titel von einem Beitrag, der gepostet worden ist.<br>
	 */
	public String getTitel(){
		return this.titel;
	}
	
	/**
	 * setTitel setzt die Instanzvariable titel auf den uebergebenen Wert.<br>
	 * @param titel von aufrufender Instanz uebergebener Wert beinhaltet den Titel eines Beitrags.<br>
	 */
	public void setTitel(String titel){
		this.titel = titel;
	}
	
	/**
	 * getInhalt gibt die Instanzvariable inhalt zurueck.<br>
	 * @return inhalt beinhaltet den eigentlichen Text von einem geposteten Beitrag.<br>
	 */
	public String getInhalt(){
		return this.inhalt;
	}
	
	/**
	 * setInhalt setzt die Instanzvariable auf den uebergebenen Wert.<br>
	 * @param inhalt von aufrufender Instanz uebergebener Wert beinhaltet den eigentlichen Text
	 * von einem geposteten Beitrag.<br>
	 */
	public void setInhalt(String inhalt){
		this.inhalt = inhalt;
	}
	
}
