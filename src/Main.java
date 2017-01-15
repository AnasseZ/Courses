import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @brief fonction principale
 * 
 * @author Pierre et Anasse
 */ 

public class Main {

	public static void main(String[] args) {
// --------------- Ajout de la localisation des balises --------------------

		String fichier = "./doc/emplacement.txt";
		String course = "";
		// Lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(new File(fichier));
			InputStreamReader isr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(isr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				course += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		String[] tabLieu = course.split("\n"); // on stock chaques lignes dans un tableau
												
		// on stock chaque ligne dans une ArrayList de Lieu
		ArrayList<Lieu> lieux = new ArrayList<Lieu>();
		for (String s : tabLieu) {
			String[] latLong = s.split(",");
			lieux.add(new Lieu(Double.parseDouble(latLong[0].trim()), Double.parseDouble(latLong[1].trim())));
		}

// --------------- Initialisation de la course ------------------------------

		fichier = "./doc/marathon.txt";
		course = "";
		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(new File(fichier));
			InputStreamReader isr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(isr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				course += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		String[] tabCourse = course.split("\n");

		Iterator<Lieu> lieu = lieux.iterator();
		ArrayList<Balise> balises = new ArrayList<Balise>();
		String nomCourse = tabCourse[0];
		int i = 1;
		while (lieu.hasNext()) { // on parcourt le fichier texte et la ArrayList de Lieu a la fois
			Lieu newLieu = lieu.next();
			balises.add(new Balise(tabCourse[i], newLieu));// puis on les stock dans une Arraylist de Balise
			i++;
		}
		Course C = new Course(nomCourse, balises);
		System.out.println(C.toString());

// --------------- Ajout des coureurs  --------------------------------------------

		fichier = "./doc/coureurs.txt";
		course = "";
		// lecture du fichier texte
		try {
			InputStream ips = new FileInputStream(new File(fichier));
			InputStreamReader isr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(isr);
			String ligne;
			while ((ligne = br.readLine()) != null) {
				course += ligne + "\n";
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		String[] tabCoureur = course.split("\n");

		int nbCoureur = 1;
		for (int n = 0; n < tabCoureur.length; n++) { // on determine le nombre de coureurs
			if (tabCoureur[n].equals(";"))
				nbCoureur++;
		}

		ArrayList<Coureur> ListeCoureur = new ArrayList<Coureur>();
		i = 0;
		for (int n = 1; n <= nbCoureur; n++) { // Pour chaque coureur
			if (tabCoureur[i].equals(";"))
				i++; // changement de coureur delimite par un ";"
			Coureur coureur = new Coureur(tabCoureur[i], C); 
			// la ligne suivante est un coureur, on recupere son nom
																
			i++;
			Iterator<Balise> it = balises.iterator();
			while (it.hasNext()) { // on parcourt le fichier texte et la ArrayList de Balise a la fois
				Balise b = it.next();
				String[] horaire = tabCoureur[i].split(",");
				int h1 = Integer.parseInt(horaire[0].trim());// (trim() pour enlever les 0 devant les chiffres de 1 a 10)													
				int h2 = Integer.parseInt(horaire[1].trim());
				int h3 = Integer.parseInt(horaire[2].trim());
				if(h1>24 || h2>60 || h3>60){
					System.out.println("format de l'horaire incorrect ("+h1+":"+h2+":"+h3+")");
					//System.exit(0);
					h1=0;h2=0;h3=0;
				}
				coureur.pointer(b, new Horaire(h1, h2, h3)); // on pointe la balise concernee
				i++;
			}
			ListeCoureur.add(coureur); // on stock le coureur dans la liste
			System.out.println(coureur.toString()); // affichage de la course de chaque coureur
		}

// ------------------ Classement des coureurs  -------------------------------------

		Coureur[] Classement = new Coureur[nbCoureur];
		int place;
		for (i = 0; i < nbCoureur; i++) { // pour chaque coureur
			place = nbCoureur;
			Iterator<Coureur> coureurIt = ListeCoureur.iterator();
			Coureur c = ListeCoureur.get(i);
			while (coureurIt.hasNext()) {
				if (c.tempsCourse().convertEnSecondes() < coureurIt.next().tempsCourse().convertEnSecondes()) {
					place--;// gagne des places dans le classement
				}
			}
			Classement[place - 1] = c;// place-1 car le tableau demarre a 0
		}

		i = 1;
		
		// Affichage classement
		System.out.println("Classement general: \n");
		for (Coureur c : Classement) {
			System.out.println(i + "e place : " + c.getNom());
			i++;
		}

// ----------------------- Classement par Balise  ---------------------------------------

		ArrayList<Coureur[]> classmtBalise = new ArrayList<Coureur[]>(); // arraylist de classement
		
		// Pour chaque Balise (on commence par la 2eme balise car la premiere est le depart)
		for (int n = 1; n < C.getBalisesTab().size(); n++) { 
			Classement = new Coureur[nbCoureur];
			for (i = 0; i < nbCoureur; i++) { // pour chaque coureur
				place = nbCoureur;
				Coureur c = ListeCoureur.get(i);
				Iterator<Coureur> coureurIt = ListeCoureur.iterator();
				int h1 = c.horaireBalise.get(n).getH().convertEnSecondes();
				while (coureurIt.hasNext()) {// on compare avec les autres coureurs
					int h2 = coureurIt.next().horaireBalise.get(n).getH().convertEnSecondes();
					if (h1 < h2) {
						place--;// gagne des places dans le classement
					}
				}
				Classement[place - 1] = c;// place-1 car le tableau demarre a 0
			}
			classmtBalise.add(Classement);
		}

		//affichage classement
		System.out.println("\n\nClassement par Balise:");
		Iterator<Coureur[]> classmtIt = classmtBalise.iterator();
		int n = 1;
		while (classmtIt.hasNext()) {
			Classement = classmtIt.next();
			i = 1;
			System.out.println("\n" + C.getBalisesTab().get(n).getNom());
			for (Coureur c : Classement) {
				System.out.println(i + "e place : " + c.getNom());
				i++;
			}
			n++;
		}

/*
 * ------------------- Code sans lire les donnees dans des fichiers ------------------
		 * 
		 * // initialisation des Lieux 
		 * Lieu L1 = new Lieu(47.21544355211623,-1.549737453460693);
		 *  Lieu L2 = new Lieu(47.21296574686175,-1.557977199554443);
		 *  Lieu L3 = new Lieu(47.20903017114541,-1.566603183746338);
		 *  Lieu L4 = new Lieu(47.20672699508749,-1.564457416534424);
		 *  Lieu L5 = new Lieu(47.20159550909059,-1.572868824005127);
		 *  Lieu L6 = new Lieu(47.20591065551807,-1.567847728729248);
		 *  Lieu L7 = new Lieu(47.20693107801684,-1.548578739166260);
		 *  Lieu L8 = new Lieu(47.21249932351343,-1.55256986618042);
		 *  Lieu L9 = new Lieu(47.21540711464245,-1.546722650527954);
		 *  Lieu L10 = new Lieu(47.21544355211623,-1.549737453460693);
		 * 
		 * 
		 * // initialisation des balises
		 *  ArrayList<Balise> baliseList = new ArrayList<Balise>();
		 * 
		 * Balise balise1 = new Balise("Depart: Chateau des ducs", L1);
		 * baliseList.add(balise1);
		 * 
		 * Balise balise2 = new Balise("Place du Commerce", L2);
		 * baliseList.add(balise2);
		 * 
		 * Balise balise3 = new Balise("Quai de la Fosse", L3);
		 * baliseList.add(balise3);
		 * 
		 * Balise balise4 = new Balise("Les Machines", L4);
		 * baliseList.add(balise4);
		 * 
		 * Balise balise5 = new Balise("Hangar a bananes", L5);
		 * baliseList.add(balise5);
		 * 
		 * Balise balise6 = new Balise("Caroussel", L6);
		 * baliseList.add(balise6);
		 * 
		 * Balise balise7 = new Balise("Quai Hoche", L7);
		 * baliseList.add(balise7);
		 * 
		 * Balise balise8 = new Balise("Allee Baco", L8);
		 * baliseList.add(balise8);
		 * 
		 * Balise balise9 = new Balise("Lieu Unique", L9);
		 * baliseList.add(balise9);
		 * 
		 * Balise balise10 = new Balise("Arrivee : Chateau des Ducs", L10);
		 * baliseList.add(balise10);
		 * 
		 * // Initialisation de la course 
		 * Course Nantes = new Course("marathon Nantes", baliseList);
		 * 
		 * //initialisation des coureurs
		 *  Coureur louis = new Coureur("louis",Nantes);
		 * 
		 * louis.pointer(balise1,new Horaire(16,32,28));
		 * louis.pointer(balise2,new Horaire(16,34,07)); //temps inferieur a la balise pre -> erreur
		 * louis.pointer(balise3,new Horaire(16,35,37));
		 * louis.pointer(balise4,new Horaire(16,38,15));
		 * louis.pointer(balise5,new Horaire(16,42,51));
		 * louis.pointer(balise6,new Horaire(16,44,01));
		 * louis.pointer(balise7,new Horaire(16,48,28));
		 * louis.pointer(balise8,new Horaire(16,50,10));
		 * louis.pointer(balise9,new Horaire(16,52,12));
		 * louis.pointer(balise10,new Horaire(16,53,42));
		 * 
		 * 
		 * // affichage 
		 * System.out.println(louis.toString());
		 */
	}
}