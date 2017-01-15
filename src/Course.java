/** @brief Classe Course gerant la Course
 * 
 * @author Pierre et Anasse
 */


import java.util.ArrayList;

public class Course {

	private ArrayList<Balise> baliseList;
	private String nom;

	/**@Role Constructeur avec parametres
	 * 
	 * @param String nom, ArrayList<Balise> baliseList
	 * 
	 */
	public Course(String nom, ArrayList<Balise> baliseList) {
		this.baliseList = baliseList;
		this.nom = nom;
	}
	
	// ************ Getters & Setters ****************
	public ArrayList<Balise> getBalisesTab() {
		return this.baliseList;
	}

	public void setBalisesTab(ArrayList<Balise> baliseList) {
		this.baliseList = baliseList;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	// ************ Methodes ****************
	
	/**
	 *  @Role : Ajoute une balise en verifiant qu'elle n'existe pas deja
	 * 
	 * @param Balise b
	 */
	public void addBalise(Balise b) {
		boolean t = true;
		int length = baliseList.size();
		for (int i = 0; i <= length; i++) {
			if (baliseList.get(i).equals(b))
				t = false; // verifie que la balise n'est pas deja presente
		}
		if (t)
			baliseList.set(length + 1, b);
	}
	/**
	 *  @Role : Methode renvoyant la position d une Balise.
	 * 
	 * @param Balise b
	 * @return la position d une balise
	 */
	public int position(Balise b) {
		int pos = 0;
		for (int i = 0; i <= baliseList.size(); i++) {
			if (baliseList.get(i).equals(b))
				pos = i;
		}
		if (pos == 0) {
			System.out.println("balise inexistente");
			return 0;
		} else
			return pos;
	}
	/**
	 * @Role : Methode calculant la distance d une course
	 * 
	 * @return la distance de la Course
	 */
	public double distance() {
		double dist = 0;
		for (int i = 0; i < baliseList.size() - 1; i++) {
			dist += baliseList.get(i).distance(baliseList.get(i + 1));
		}
		return dist;
	}
	
	/**
	 * @Role : Methode d'affichage
	 * 
	 * 
	 * @return le titre de la course, les Balises ainsi que leurs longitudes et latitudes
	 */
	public String toString() {
		String str = "\t\t\tCourse " + nom + "\n\t\t\t "+this.distance()+"km\n"+"\t\t\t  Liste des Balises\n\n";
		for (Balise b : baliseList) {
			str += b.getNom() + "\t ( Latitude : " + b.getLieu().getLatitude() + " , Longitude: "
					+ b.getLieu().getLongitude() + ")\n";
		}
		return str;
	}
}
