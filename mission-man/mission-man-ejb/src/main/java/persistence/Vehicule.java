package persistence;

import java.awt.Color;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Vehicule
 *
 */
@Entity

public class Vehicule implements Serializable {

	@Id
	private String immatriculation;
	@Enumerated(EnumType.STRING)
	private Carburant carburant;
	@Enumerated(EnumType.STRING)
	private Marque marque;
	@Enumerated(EnumType.STRING)
	private Modele modele;
	@Enumerated(EnumType.STRING)
	private Transmission transmission;
	private Date dateMiseEnCirculation;
	private String typeCommercial;
	private String genre;
	private Color couleur;
	private String energie;
	private String numeroDeSerie;
	private String puissance;
	private String valeurNeuve;
	private String venale;
	private String indexKm;
	private String etat;
	private String chargeUtile;
	private String carosserie;
	private String typeDeCarosserie;
	private String nbPortes;
	@ElementCollection
	private List<String> options;

	@ManyToMany
	private List<Produit> produits;

	@OneToMany(mappedBy = "vehicule")
	private List<MVehiculeDetail> mVehiculeDetails;
	private static final long serialVersionUID = 1L;

	public Vehicule() {
		super();
	}

	public Vehicule(String immatriculation, Carburant carburant, Marque marque, Modele modele,
			Transmission transmission) {
		super();
		this.immatriculation = immatriculation;
		this.carburant = carburant;
		this.marque = marque;
		this.modele = modele;
		this.transmission = transmission;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Carburant getCarburant() {
		return carburant;
	}

	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}

	public Transmission getTransmission() {
		return transmission;
	}

	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Date getDateMiseEnCirculation() {
		return dateMiseEnCirculation;
	}

	public void setDateMiseEnCirculation(Date dateMiseEnCirculation) {
		this.dateMiseEnCirculation = dateMiseEnCirculation;
	}

	public String getTypeCommercial() {
		return typeCommercial;
	}

	public void setTypeCommercial(String typeCommercial) {
		this.typeCommercial = typeCommercial;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public String getEnergie() {
		return energie;
	}

	public void setEnergie(String energie) {
		this.energie = energie;
	}

	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	public String getPuissance() {
		return puissance;
	}

	public void setPuissance(String puissance) {
		this.puissance = puissance;
	}

	public String getValeurNeuve() {
		return valeurNeuve;
	}

	public void setValeurNeuve(String valeurNeuve) {
		this.valeurNeuve = valeurNeuve;
	}

	public String getVenale() {
		return venale;
	}

	public void setVenale(String venale) {
		this.venale = venale;
	}

	public String getIndexKm() {
		return indexKm;
	}

	public void setIndexKm(String indexKm) {
		this.indexKm = indexKm;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getChargeUtile() {
		return chargeUtile;
	}

	public void setChargeUtile(String chargeUtile) {
		this.chargeUtile = chargeUtile;
	}

	public String getCarosserie() {
		return carosserie;
	}

	public void setCarosserie(String carosserie) {
		this.carosserie = carosserie;
	}

	public String getTypeDeCarosserie() {
		return typeDeCarosserie;
	}

	public void setTypeDeCarosserie(String typeDeCarosserie) {
		this.typeDeCarosserie = typeDeCarosserie;
	}

	public String getNbPortes() {
		return nbPortes;
	}

	public void setNbPortes(String nbPortes) {
		this.nbPortes = nbPortes;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<MVehiculeDetail> getmVehiculeDetails() {
		return mVehiculeDetails;
	}

	public void setmVehiculeDetails(List<MVehiculeDetail> mVehiculeDetails) {
		this.mVehiculeDetails = mVehiculeDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((immatriculation == null) ? 0 : immatriculation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicule other = (Vehicule) obj;
		if (immatriculation == null) {
			if (other.immatriculation != null)
				return false;
		} else if (!immatriculation.equals(other.immatriculation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicule [immatriculation=" + immatriculation + "]";
	}

}
