package persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MChauffeurDetailId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idMission;
	private Integer idChauffeur;

	public MChauffeurDetailId() {
	}

	public MChauffeurDetailId(Integer idMission, Integer idChauffeur) {
		super();
		this.idMission = idMission;
		this.idChauffeur = idChauffeur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idChauffeur == null) ? 0 : idChauffeur.hashCode());
		result = prime * result + ((idMission == null) ? 0 : idMission.hashCode());
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
		MChauffeurDetailId other = (MChauffeurDetailId) obj;
		if (idChauffeur == null) {
			if (other.idChauffeur != null)
				return false;
		} else if (!idChauffeur.equals(other.idChauffeur))
			return false;
		if (idMission == null) {
			if (other.idMission != null)
				return false;
		} else if (!idMission.equals(other.idMission))
			return false;
		return true;
	}

	public Integer getIdMission() {
		return idMission;
	}

	public void setIdMission(Integer idMission) {
		this.idMission = idMission;
	}

	public Integer getIdChauffeur() {
		return idChauffeur;
	}

	public void setIdChauffeur(Integer idChauffeur) {
		this.idChauffeur = idChauffeur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
