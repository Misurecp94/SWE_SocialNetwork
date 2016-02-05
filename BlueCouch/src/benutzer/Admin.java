package benutzer;

import java.util.ArrayList;
import java.util.Calendar;

import benutzer.user.Beitrag;

/**
 * Die Klasse Admin regelt die Moeglichkeiten die ein Admin besitzt. Umfasst Beitrag loeschen, freigeben sowie User sperren.
 * @author Patrik Misurec, Raphael Kohlhaupt, Christian Pfneisl, Mohamed Raouf
 * @version 10/01/15
 */
public class Admin extends Anwender{
	private static final long serialVersionUID = 1L;

	/**
	 * Liste gemeldeter Beitraege
	 */
	private ArrayList<Beitrag> gemeldeteBeitraege = new ArrayList<Beitrag>();
	
	/**
	 * Konstruktor .... Trivial ............
	 */
	public Admin(String emailID, String password) {
		super(emailID, password);
	}
	
	/**
	 * meldungAufheben entfernt Meldung eines Beitrages aka loescht ihn aus entsprechender Instanzvariable d. Admins.
	 * @param beitrag Beitrag. ..
	 */
	public void meldungAufheben(Beitrag beitrag){
		for(int i = 0; i<gemeldeteBeitraege.size();i++){
			if(this.gemeldeteBeitraege.get(i).getID() == beitrag.getID() && this.gemeldeteBeitraege.get(i).getUser().equals(beitrag.getUser())){
				gemeldeteBeitraege.remove(i);
				return;
			}
		}
	}

	/**
	 * loescheBeitrag loescht gemeldeten Beitrag, aka entfernt diesen beim jeweiligen User und aus entsprechender Instanzvariable d. Admins.
	 * @param id ID des Beitrages
	 * @param user User dem der Beitrag gehoert
	 */ 
	public void loescheBeitrag(int id, User user){
		user.beitragLoeschen(id);
		for(int i = 0; i<gemeldeteBeitraege.size();i++){
			if(this.gemeldeteBeitraege.get(i).getID() == id && this.gemeldeteBeitraege.get(i).getUser().equals(user.getEmailID())){
				gemeldeteBeitraege.remove(i);
				return;
			}
		}
	}
	
	/**
	 * sperrt User. Increments SperrNr d. Users, sperrt diesen bis zu einem bestimmten Datum
	 * @param user der zu sperrende User
	 * @param endDatum Enddatum d. Sperre
	 */
	public void userSperren(User user, Calendar endDatum){
		user.addSperrNr();
		user.setGesperrt(true);
		user.setGesperrtBis(endDatum);
	}
	
	/**
	 * Liste gemeldeter Beitraege w. zurueckggegeben
	 * @return Liste gemeldeter Beitraege
	 */
	public ArrayList<Beitrag> getGemeldeteBeitraege(){
		return this.gemeldeteBeitraege;
	}
	
	/**
	 * weist INstanzvariable gemeldete Beitraege uebergebenen Wert zu
	 * @param gBeitrag Liste vom Typ ArrayList welche Beitraege vom Typ Beitrag beinhaltet
	 */
	public void setGemeldeteBeitraege(ArrayList<Beitrag> gBeitrag){
		this.gemeldeteBeitraege = gBeitrag;
	}

}
