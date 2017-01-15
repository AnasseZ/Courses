/**
 * @brief Classe Balise gerant les balises
 * 
 * @author Pierre et Anasse
 */
public class Balise {

	private String nom;
	private Lieu lieu;
	
	/**@Role Constructeur avec parametres
	 * @param String nom, Lieu lieu
	 */
	public Balise(String nom, Lieu lieu) {
		this.nom = nom;
		this.lieu = lieu;
	}
	// ************ Getters & Setters ****************
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	/**
	 * @Role : Methode qui calcule la distance entre 2 balises
	 * 
	 * @param Balise b2
	 * @return la distance entre deux balises
	 */
	public double distance(Balise b2) {
		return b2.getLieu().distance(this.lieu);
	}
	/**
	 * @Role : Methode testant l'egalite entre deux balises
	 * 
	 * @param Balise b2
	 * @return  true si egalite entre 2 balises, false sinon
	 */
	public boolean equals(Balise b2) {
		boolean b = false;
		if (this.nom == b2.nom && lieu.equals(b2.getLieu()))
			b = true;
		return b;
	}

}
