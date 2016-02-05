package benutzer.user;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Klasse Freund, welche alle Attribute des Users hinsichtlich des Freundes
 * und Methoden, welche der User zur Verwaltung des Freundes benoetigt,
 * verwaltet.
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raoul
 * @version 2015/09/12
 */
public class Freund implements Serializable{

	/**
	 * serialVersionUID zur persistenten Speicherung<br>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Beginndatum der Freundschaft
	 */
	private Calendar beginn;
	
	/**
	 * Gruppenzugehoerigkeit des Freundes
	 */
	private Gruppe in_gruppe;
	
	/**
	 * EmailID des Users, dem das Objekt vom Typ Freund zugehoerig ist.
	 */
	private String user;
	
	/**
	 * EmailID des freundes
	 */
	private String freunduser;
	
	/**
	 * Konstruktor der Klasse Freund. Setzt Gruppenzugehoerigkeit Standartmaessig auf null.<br>
	 * Beginndatum wird bei Erzeugung auf aktuelles Datum gesetzt.<br>
	 * emailID des User dem das Objekt vom Typ Freund zugehoerig ist, sowie emailID des freundes per
	 * setterMethoden setzen.
	 * 
	 * @param user EmailID des Users, dem das Objekt vom Typ Freund zugehoerig ist.
	 * @param freunduser EmailID des freundes
	 */
	public Freund(String user, String freunduser){
		this.beginn = Calendar.getInstance();
		this.in_gruppe = null;
		setUser(user);
		setFreunduser(freunduser);
	}
	
	/**
	 * Setter fuer Instanzvariable in_gruppe
	 * @param gruppe Eingegebener Wert, dessen Wert der Instanzvariable gruppe zugewiesen werden soll. Spiegelt
	 * die Zugehoerigkeit des Freundes zu einer bestimmten Gruppe wider.
	 */
	public void setGruppe(Gruppe gruppe){
		this.in_gruppe = gruppe;
	}
	
	/**
	 * Getter fuer Instanzvariable in_gruppe
	 * @return Gruppe, der der Freund zugehoerig ist.
	 */
	public Gruppe getGruppe(){
		return this.in_gruppe;
	}
	
	/**
	 * Getter fuer Instanzvariable beginn.
	 * @return Calendar an dem die Freundschaft begonnen hat.
	 */
	public Calendar getBeginn(){
		return this.beginn;
	}
	
	/**
	 * Getter fuer Instanzvariable user
	 * @return user , beinhaltet EmailID des Users dem das Objekt der Klasse Freund zugehoerig ist.
	 */
	public String getUser(){
		return this.user;
	}
	
	/**
	 * Setter fuer Instanzvariable user
	 * @param user Eingegebener Wert, dessen Wert der Instanzvariable user zugewiesen werden soll. Beinhaltet im
	 * Normalfall die EmailID des Users, der dem Objekt der Klasse Freund zugehoerig ist.
	 */
	public void setUser(String user){
		this.user = user;
	}
	
	/**
	 * Getter fuer Instanzvariable freunduser
	 * @return eMailID des Freundes
	 */
	public String getFreunduser(){
		return this.freunduser;
	}
	
	/**
	 * Setter fuer Instanzvariable freunduser
	 * @param freunduser Eingegebener Wert, dessen Wert der Instanzvariable freunduser zugewiesen werden soll
	 */
	public void setFreunduser(String freunduser){
		this.freunduser = freunduser;
	}
	
	
	
}
