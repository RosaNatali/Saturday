package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Chauffeur
 *
 */
@Entity

public class Chauffeur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	private String raisonSocial;
	private String adresse;
	private String telephone;
	private String fax;
	private String mobile;
	private String rib;
	private String email;
	@Column(unique = true)
	private String utilisteur;
	private String motDePasse;
	@OneToMany(mappedBy = "chauffeur", cascade = CascadeType.REMOVE)
	private List<MChauffeurDetail> mChauffeurDetails;
	private static final long serialVersionUID = 1L;

	public Chauffeur() {
		super();
	}

	
	
	public Chauffeur(Integer id, String nom, String prenom, String raisonSocial, String adresse, String telephone,
			String fax, String mobile, String rib, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.raisonSocial = raisonSocial;
		this.adresse = adresse;
		this.telephone = telephone;
		this.fax = fax;
		this.mobile = mobile;
		this.rib = rib;
		this.email = email;
	}



	public Chauffeur(String nom, String prenom, String raisonSocial, String adresse, String telephone, String fax,
			String mobile, String rib, String email, String utilisteur, String motDePasse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.raisonSocial = raisonSocial;
		this.adresse = adresse;
		this.telephone = telephone;
		this.fax = fax;
		this.mobile = mobile;
		this.rib = rib;
		this.email = email;
		this.utilisteur = utilisteur;
		this.motDePasse = motDePasse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<MChauffeurDetail> getmChauffeurDetails() {
		return mChauffeurDetails;
	}

	public void setmChauffeurDetails(List<MChauffeurDetail> mChauffeurDetails) {
		this.mChauffeurDetails = mChauffeurDetails;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getRaisonSocial() {
		return raisonSocial;
	}

	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUtilisteur() {
		return utilisteur;
	}

	public void setUtilisteur(String utilisteur) {
		this.utilisteur = utilisteur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		Chauffeur other = (Chauffeur) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Chauffeur [nom=" + nom + ", prenom=" + prenom + "]";
	}

}
