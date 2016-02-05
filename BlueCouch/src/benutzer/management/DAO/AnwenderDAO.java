package benutzer.management.DAO;

import java.io.*;
import java.util.ArrayList;
import benutzer.*;

/**
 * Klasse AnwenderDAO dient der persistenten Speicherung und zum Auslesen und Einlesen von Objekten
 * in eine Datei.<br>
 * @author Patrik Misurec, Christian Pfneisl, Raphael Kolhaupt, Mohamed Raouf<br>
 * @version 2015_12_09<br>
 */
public class AnwenderDAO {

	/**
	 * Instanzvariable:<br>
	 * DATEIPFAD definiert Dateipfad und/bzw. Dateinamen der zu speichernden/lesenden Objekte.<br>
	 */
	private final String DATEIPFAD = "userlist.ser";
	
	/**
	 * Konstruktor von der Klasse AnwenderDAO erzeugt nur das Objekt, sonst keine interne Funktionalitaet<br>
	 */
	public AnwenderDAO(){
		// do nothing
	}
	
	/**
	 * saveAnwender fuegt den uebergebenen Anwender(alle Informationen, die ein Anwender hat)
	 * der anwenderlist hinzu. Beim Speichern wird die gesamte Datei neu ueberschrieben.<br>
	 * @param anwender von aufrufender Instanz uebergebener Parameter, beinhaltet ein Objekt
	 * der Klasse Anwender.<br>
	 */
	public synchronized void saveAnwender(Anwender anwender){
		ArrayList<Anwender> anwenderlist = loadAnwenderList();
		
		if(anwender != null){
			anwenderlist.add(anwender);
		}
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(DATEIPFAD);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(anwenderlist);		
			oos.close();
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * getDATEIPFAD gibt den DATEIPFAD mit dem Namen der Datei zurueck.<br>
	 * @return DATEIPFAD, der den Dateipfad und den Namen der Datei beinhaltet, in der
	 * die gesamte persistente Speicherung abläuft.<br>
	 */
	public final String getDATEIPFAD() {
		return DATEIPFAD;
	}

	/**
	 * loadAnwenderList laedt die anwenderlist, die ein ArrayList von den Objekten der Anwender
	 * enthaelt.<br> 
	 * @return anwenderlist die ein ArrayList von den Objekten der Anwender enthaelt.<br>
	 */
	@SuppressWarnings("unchecked")
	public synchronized ArrayList<Anwender> loadAnwenderList(){
		ArrayList<Anwender> anwenderlist = new ArrayList<Anwender>();
		
		if (checkIfFileExists()) {
			InputStream is = null;
			try {
				is = new FileInputStream(DATEIPFAD);
				ObjectInputStream ois = new ObjectInputStream(is);
				anwenderlist =  (ArrayList<Anwender>) ois.readObject();
				ois.close();
			} catch (IOException e) {
				System.err.println(e);
			} catch (ClassNotFoundException e) {
				System.err.println(e);
			} finally {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
		}	
		return anwenderlist;
	}
	
	/**
	 * checkIfFileExists ueberprueft ob die Datei DATEIPFAD existiert.<br>
	 * @return true or false, true: wenn die File existiert und DATEIPFAD ein Ordner ist, ansonst
	 * false.<br>
	 */
	public synchronized boolean checkIfFileExists() {
		File f = new File(DATEIPFAD);
		if (f.exists() && !f.isDirectory()) {
			return true;
		} else
			return false;
	}
	
	/**
	 * getAnwenderbyID gibt den Anwender aus der anwenderlist zurueck, dessen emailID uebergeben
	 * worden ist. Wird unter anderem beim Login aufgerufen.<br>
	 * @param emailID von aufrufender Instanz ubergebener Parameter, beinhaltet die einzigartige
	 * emailID eines Anwenders.<br>
	 * @return Anwender aus der anwenderlist, der dieselbe emailID hat, wie die die uebergeben
	 * worden ist.<br>
	 */
	public synchronized Anwender getAnwenderbyID(String emailID){
		ArrayList<Anwender> anwenderlist = this.loadAnwenderList();
		for(int i = 0; i<anwenderlist.size(); i++){
			if(anwenderlist.get(i).getEmailID().equals(emailID)) return anwenderlist.get(i);
		}
		return null;
	}
	
	/**
	 * loescheAnwender loescht einen Anwender aus der anwenderlist.<br>
	 * @param anwender anwender von aufrufender Instanz uebergebener Parameter, beinhaltet ein Objekt
	 * der Klasse Anwender.<br>
	 */
	public synchronized void loescheAnwender(Anwender anwender){
		ArrayList<Anwender> anwenderlist = this.loadAnwenderList();
		for(int i = 0; i<anwenderlist.size();i++){
			if(anwenderlist.get(i).getEmailID().equals(anwender.getEmailID())){
				anwenderlist.remove(i);
			}
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(DATEIPFAD);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(anwenderlist);		
			oos.close();
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
	
