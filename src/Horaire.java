/**
 * @brief Classe Horaire gerant les horaires
 * 
 * @author Pierre et Anasse
 */

public class Horaire {
	private int heures;
	private int minutes;
	private int secondes;

	/**@Role Constructeur avec parametres
	 * 
	 * @param int heures, minutes, secondes
	 * 
	 */
	public Horaire(int heures, int minutes, int secondes) {
		this.heures = heures;
		this.minutes = minutes;
		this.secondes = secondes;
	}

	// ************ Getters ****************
	public int getHeures() {
		return this.heures;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public int getSecondes() {
		return this.secondes;
	}

	// ************ Setters ****************
	public void setHeures(int heures) {
		this.heures = heures;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}


	// ************ Methodes ****************

	/**
	 * @Role : methode qui convertit l'horaire en secondes
	 * 
	 * @return  le temps total d'un horaire en secondes
	 */
	public int convertEnSecondes() {
		return this.heures * 3600 + this.minutes * 60 + this.secondes;
	}
	
	/**
	 * @Role : Methode testant la precedence entre deux horaires
	 * 
	 * @param Horaire h
	 * @return renvoie true si mon horaire(this) 
	 *        est superieur a l'horaire que je compare (h) (celle de la balise precedente), false sinon
	 */
	
	
	public boolean testPre(Horaire h){
		return ( h.convertEnSecondes() < this.convertEnSecondes());
	}
	/**
	 * @Role : Methode  testant l egalite entre deux horaires
	 * 	  
	 * @param Horaire h
	 * @return Boolean  vraie si un horaire(this) est egale a un autre (h)
	 */
	public boolean equals(Horaire h){
		return ( h.convertEnSecondes() == this.convertEnSecondes());
	}
	/**
	 * @Role : Methode qui renvoie l'intervalle en secondes entre 2 horaires
	 * 
	 * @pre this.testPre(h) renvoie true
	 * @param Horaire h
	 * @return l'intervalle en secondes entre deux horaires si testPre == true, sinon -1
	 */
	public int intervalleHoraire(Horaire h) {		
		if(this.testPre(h)) return h.convertEnSecondes() - this.convertEnSecondes();
		else return -1;
		}
	
	/** @b Role : Decrit un horaire 
	 * 
	 * @return une chaine de caractere decrivant un horaire
	 */
	public String toString() {
		String sec = "",m="",h = "";
		if(secondes<10) sec =  "0";
		if(heures<10) h =  "0";
		if(minutes<10) m =  "0";
		return "  " +h+ heures + ":" +m+ minutes
				+ ":" + sec + secondes + " ";
	}

}
	
