/** @brief Classe Pointages gerant le pointage des coureurs
 * 
 * @author Pierre et Anasse
 */

public class Pointages {

	private Horaire h;
	private Balise b;
	
	/**@Role Constructeur avec parametres 
	 * @param Balise b, Horaire h
	 */
	Pointages(Balise b, Horaire h) {
		this.b = b;
		this.h = h;
	}
	
	// ************ Getters & Setters **************** 
	public Horaire getH() {
		return h;
	}

	public void setH(Horaire h) {
		this.h = h;
	}

	public Balise getB() {
		return b;
	}

	public void setB(Balise b) {
		this.b = b;
	}

}
