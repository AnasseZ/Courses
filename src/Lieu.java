/**
 * @brief Classe Lieu gerant les lieux
 * 
 * @author Pierre et Anasse
 */

import java.lang.Math;

public class Lieu {
	private static final double R = 6374.8925;
	private double latitude;
	private double longitude;

	/** @Role Constructeur avec parametres 
	 * @param double latitude, longitude
	 */
	public Lieu(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	// ************ Getters & Setters ****************
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	// ************ Methodes ****************
	
	/**
	 * @Role : methode toString gerant l'affichage d'un lieu
	 * 
	 * @return une chaine de caractere decrivant un lieu 
	 */
	public String toString() {
		return "Lieu: latitude=" + this.latitude + ", longitude=" + this.longitude;
	}
	
	/**
	 * @Role : Methode qui test l egalite entre deux lieux
	 * 
	 * @param Lieu L
	 * @return renvoyant true si egalite entre 2 Lieux, false si non
	 */
	public boolean equals(Lieu L) {
		boolean b = false;
		if (this.latitude == L.latitude && this.longitude == L.longitude)
			b = true;
		return b;
	}
	/**
	 * @Role : Methode calculant la distance entre deux lieux 
	 * 
	 * @param Lieu L
	 * @return la distance entre deux lieux
	 */
	public double distance(Lieu L) {
		double lat1 = Math.toRadians(this.latitude);
		double lat2 = Math.toRadians(L.latitude);
		double lon1 = Math.toRadians(this.longitude);
		double lon2 = Math.toRadians(L.longitude);
		return R * (Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)));
	}
}
