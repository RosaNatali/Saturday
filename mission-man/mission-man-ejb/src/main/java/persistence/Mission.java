package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Mission
 *
 */
@Entity

public class Mission implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date dateEstime;
	private Date dateCreation;
	private Date dateAffectation;
	private String site;
	@Enumerated(EnumType.STRING)
	private EtatMission etatMission;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> listImagesNames = new ArrayList<>();

	@OneToMany(mappedBy = "mission", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private Set<MChauffeurDetail> mChauffeurDetails;

	@OneToMany(mappedBy = "mission", fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private Set<MVehiculeDetail> mVehiculeDetails;

	private static final long serialVersionUID = 1L;

	public Mission() {
		super();
	}
	
	

	public Mission(Integer id, Date dateEstime, Date dateCreation, Date dateAffectation, String site,
			EtatMission etatMission) {
		super();
		this.id = id;
		this.dateEstime = dateEstime;
		this.dateCreation = dateCreation;
		this.dateAffectation = dateAffectation;
		this.site = site;
		this.etatMission = etatMission;
	}



	public Mission(Integer id, Date dateEstime) {
		super();
		this.id = id;
		this.dateEstime = dateEstime;
	}

	public Mission(Date dateCreation, String site) {
		super();
		this.dateCreation = dateCreation;
		this.site = site;
		this.etatMission = EtatMission.NEW;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateEstime() {
		return this.dateEstime;
	}

	public void setDateEstime(Date dateEstime) {
		this.dateEstime = dateEstime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<MChauffeurDetail> getmChauffeurDetails() {
		return mChauffeurDetails;
	}

	public void setmChauffeurDetails(Set<MChauffeurDetail> mChauffeurDetails) {
		this.mChauffeurDetails = mChauffeurDetails;
	}

	public Set<MVehiculeDetail> getmVehiculeDetails() {
		return mVehiculeDetails;
	}

	public void setmVehiculeDetails(Set<MVehiculeDetail> mVehiculeDetails) {
		this.mVehiculeDetails = mVehiculeDetails;
	}

	public List<String> getListImagesNames() {
		return listImagesNames;
	}

	public void setListImagesNames(List<String> listImagesNames) {
		this.listImagesNames = listImagesNames;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateAffectation() {
		return dateAffectation;
	}

	public void setDateAffectation(Date dateAffectation) {
		this.dateAffectation = dateAffectation;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public EtatMission getEtatMission() {
		return etatMission;
	}

	public void setEtatMission(EtatMission etatMission) {
		this.etatMission = etatMission;
	}

}
