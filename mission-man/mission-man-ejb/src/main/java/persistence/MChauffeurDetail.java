package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: MChauffeurDetail
 *
 */
@Entity

public class MChauffeurDetail implements Serializable {
	@EmbeddedId
	private MChauffeurDetailId id;
	private Date dateAffectationMission;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idMission", referencedColumnName = "id", insertable = false, updatable = false)
	private Mission mission;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idChauffeur", referencedColumnName = "id", insertable = false, updatable = false)
	private Chauffeur chauffeur;
	private static final long serialVersionUID = 1L;

	public MChauffeurDetail() {
		super();
	}

	public MChauffeurDetail(Mission mission, Chauffeur chauffeur) {
		super();
		this.dateAffectationMission = new Date();
		this.mission = mission;
		this.chauffeur = chauffeur;
		this.id = new MChauffeurDetailId(mission.getId(), chauffeur.getId());
	}

	public Date getDateAffectationMission() {
		return this.dateAffectationMission;
	}

	public void setDateAffectationMission(Date dateAffectationMission) {
		this.dateAffectationMission = dateAffectationMission;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

}
