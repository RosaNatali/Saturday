package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: MVehicule
 *
 */
@Entity

public class MVehiculeDetail implements Serializable {
	@EmbeddedId
	private MVehiculeDetailId id;
	private Date dateAffectationMission;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idMission", referencedColumnName = "id", insertable = false, updatable = false)
	private Mission mission;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idVehicule", referencedColumnName = "immatriculation", insertable = false, updatable = false)
	private Vehicule vehicule;
	private static final long serialVersionUID = 1L;

	public MVehiculeDetail() {
	}

	public MVehiculeDetail(Mission mission, Vehicule vehicule) {
		super();
		this.dateAffectationMission = new Date();
		this.mission = mission;
		this.vehicule = vehicule;
		this.id = new MVehiculeDetailId(vehicule.getImmatriculation(), mission.getId());
	}

	public Date getDateAffectationMission() {
		return this.dateAffectationMission;
	}

	public void setDateAffectationMission(Date dateAffectationMission) {
		this.dateAffectationMission = dateAffectationMission;
	}

	public MVehiculeDetailId getId() {
		return id;
	}

	public void setId(MVehiculeDetailId id) {
		this.id = id;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

}
