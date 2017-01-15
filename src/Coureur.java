
/** @brief Classe Coureur gerant les coureurs
 * 
 * @author Pierre et Anasse
 */

import java.util.ArrayList;
import java.util.Iterator;

public class Coureur {
	private String nom;
	private Course course;
	protected ArrayList<Pointages> horaireBalise; // Pointages(Balise b, Horaire h)
													
	/**@Role Constructeur avec parametres 
	 * 
	 * @param String nom, Course course
	 * 
	 */
	public Coureur(String nom, Course course) {
		this.nom = nom;
		this.course = course;
		horaireBalise = new ArrayList<Pointages>();
		int i = 0; // definition de l'iterateur pour la boucle
		while (i <= this.course.getBalisesTab().size()) { //initialisation de l'ArrayList pour gérer les eventuelles erreurs
			horaireBalise.add(new Pointages(new Balise("---", new Lieu(0, 0)), new Horaire(0, 0, 0)));
			i++;
		}
	}

	// ************ Getters ****************
	public String getNom() {
		return nom;
	}

	public Course getCourse() {
		return course;
	}

	// ************ Setters ****************
	public void setCourse(Course course) {
		this.course = course;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	// ************ Methodes ****************
	
	/**
	 * @Role : Methode de pointage avec plusieurs verifications 
	 * 
	 * @param Balise Bnew, Horaire Hnew
	 * @return true si la balise est pointe, sinon false
	 */
	public boolean pointer(Balise Bnew, Horaire Hnew) {
		boolean verif = false;
		Pointages Newpointage = new Pointages(Bnew, Hnew);

		for (Balise Bcourse : this.course.getBalisesTab()) {
			if (Bcourse.equals(Bnew))
				verif = true;
		}
		if (!verif)
			System.out.println("la balise n'existe pas dans la course");
		// 1er test passe

		else { // verif = true : la balise existe dans la course
			Iterator<Pointages> it = horaireBalise.iterator();
			while (it.hasNext()) {
				Pointages p = it.next();
				if (p.getB().equals(Bnew))
					verif = false; // la balise existe deja
			}
			if (!verif)
				System.out.println("la balise a deja etait pointee par le coureur");
			// 2eme test passe

			else { // horaireBalise contient au moins un element
				ArrayList<Balise> balisesCourse = this.course.getBalisesTab();
				int index = balisesCourse.indexOf(Bnew); // on prend l'index de la balise dans la course
				Horaire Hpre;
				// verif des precedents pointages
				int n = index;
				while (n != 0) {
					n--;
					/* Test de precedence pour toutes les balises precedentes
					 (au cas ou certaines des precedentes n'aurait pas ete ajoutees)
					*/ 
					Hpre = horaireBalise.get(n).getH();
					if (!Hnew.testPre(Hpre))
						verif = false;
				}
				if (!verif)
					System.out.println("Horaire INCORRECT pour la balise: "+Bnew.getNom());
				else {
					horaireBalise.add(index, Newpointage); // Ajout du pointage
				}
			}
		}
		return verif;
	}

	/**
	 * @Role : Methode renvoyant le temps de Course dans un horaire
	 * 
	 * @post: le coureur a pointe toutes ses balises
	 * @return un nouvel horaire Horaire
	 */	 
	public Horaire tempsCourse() {
		int h, m, s;
		Horaire Hfirst = this.horaireBalise.get(0).getH();
		int last = this.course.getBalisesTab().size() - 1;
		Horaire Hlast = this.horaireBalise.get(last).getH();
		int time = Hlast.convertEnSecondes() - Hfirst.convertEnSecondes();
		h = time / 3600;
		m = (time % 3600) / 60;
		s = (time % 3600) % 60;
		return new Horaire(h, m, s);
	}

	// ************ Affichage *************
	/**
	 * @Role : methode toString gerant l'affichage
	 * 
	 * @return une chaine de caractere
	 */
	public String toString() {
		String str = "Nom: " + this.nom + " \n" + "Course : " + this.course.getNom() + "\n";
		Iterator<Pointages> it = horaireBalise.iterator(); // definition de l'iterateur pour la boucle
		ArrayList<Balise> b = this.course.getBalisesTab();
		int i = 0;
		while (i <= b.size() - 1) {
			Pointages p;
			if (it.hasNext())
				p = it.next();
			else
				p = new Pointages(new Balise("---", new Lieu(0, 0)), new Horaire(0, 0, 0));

			if (b.get(i).equals(p.getB())) { // verifie si la balise a etait pointe										
				str += b.get(i).getNom(); // affiche le nom de la balise
				str += p.getH().toString(); // affiche la valeur
				str += "\n";
				i++;
			} else {// la balise n'a pas etait bien ajoutee
				str += b.get(i).getNom(); // affiche le nom de la balise
				str += " --:--:--  (pointage incorrect ou non renseigne)"; // affiche la valeur
				str += "\n";
				i++;
				if (i < b.size() && !(p.getB().getNom().equals("---"))) { // 
					/* si i est toujours inferieur a b.size et si le pointage
					  suivant n'est pas nul 											
					 */
					str += b.get(i).getNom(); // affiche le nom de la balise
					str += p.getH().toString(); // affiche la valeur
					str += "\n";
					i++;
				}
			}
		}
		return str;
	}
}
