package benutzer.user;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasse Gruppe, welche alle Attribute des Users hinsichtlich der Gruppe
 * und Methoden, welche der User zur Verwaltung der Gruppe benoetigt,
 * verwaltet.
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raoul
 * @version 2015/09/12
 */
public class Gruppe implements Serializable{
	
	/**
	 * serialVersionUID zur persistenten Speicherung<br>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * user beinhaltet den, der Gruppe zugehoerigen User (dessen EmailID genau gesagt)<br>
	 */
	private String user;
	
	/**
	 * ArrayList mitglieder beinhaltet Objekte vom Typ Freund. Stellen Mitglieder der Gruppe dar<br>
	 */
	private ArrayList<Freund> mitglieder;
	
	/**
	 * String der den Namen der Gruppe widerspiegelt.<br>
	 */
	private String name;
	
	/**
	 * Konstruktor der Klasse Gruppe legt eine neue Instanz von ArrayList an, welche Objekte vom Typ
	 * Freund speichern soll. <br>
	 * Setzt Name der Gruppe sowie User der der Gruppe zugehoerig ist mittels setter Methoden.<br>
	 * @param user Der User, dem das Objekt zugehoerig ist.
	 * @param name Der Name der der Gruppe zugewiesen werden soll.
	 */
	public Gruppe(String user, String name){
		mitglieder = new ArrayList<Freund>();
		setName(name);
		setUser(user);
	}
	
	/**
	 * Getter fuer Instanzvariable name
	 * @return Name der Gruppe
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Setter fuer Instanzvariable name
	 * @param name Eingegebener Wert, dessen Wert der Instanzvariable name zugewiesen werden soll
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Getter fuer Instanzvariable user
	 * @return User, der der Gruppe zugehoerig ist.
	 */
	public String getUser(){
		return this.user;
	}
	
	/**
	 * Setter fuer Instanzvariable user
	 * @param user Eingegebener Wert, dessen Wert der Instanzvariable user zugewiesen werden soll. Beinhaltet im
	 * Normalfall die EmailID des Users, der dem Objekt der Klasse Gruppe zugehoerig ist.
	 */
	public void setUser(String user){
		this.user = user;
	}
	
	/**
	 * Getter fuer Instanzvariable mitglieder
	 * @return ArrayList welche Objekte vom Typ Freund beinhaltet, die die Mitglieder der Gruppe widerspiegeln
	 */
	public ArrayList<Freund> getMitglieder(){
		return this.mitglieder;
	}
	
	/**
	 * Methode freund_zu_gruppe bietet die Moeglichkeit ein Objekt vom Typ Freund der Instanzvariable mitglieder
	 * hinzuzufuegen.
	 * @param freund Objekt vom Typ Freund, das der Instanzvariable mitglieder hinzugefuegt werden soll.
	 */
	public void freund_zu_gruppe(Freund freund){
		freund.setGruppe(this);
		mitglieder.add(freund);
	}
	
	/**
	 * Methode freund_aus_gruppe bietet die Moeglichkeit ein Objekt vom Typ Freund aus der Instanzvariable mitglieder
	 * zu entfernen.
	 * @param freund Objekt vom Typ Freund, das aus der Instanzvariable mitglieder entfernt werden soll.
	 */
	public void freund_aus_gruppe(Freund freund){
		freund.setGruppe(null);
		mitglieder.remove(freund);
	}
	
}
