package persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MVehiculeDetailId implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String idVehicule;
	private Integer idMission;

	public MVehiculeDetailId() {
	}

	public MVehiculeDetailId(String idVehicule, Integer idMission) {
		super();
		this.idVehicule = idVehicule;
		this.idMission = idMission;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMission == null) ? 0 : idMission.hashCode());
		result = prime * result + ((idVehicule == null) ? 0 : idVehicule.hashCode());
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
		MVehiculeDetailId other = (MVehiculeDetailId) obj;
		if (idMission == null) {
			if (other.idMission != null)
				return false;
		} else if (!idMission.equals(other.idMission))
			return false;
		if (idVehicule == null) {
			if (other.idVehicule != null)
				return false;
		} else if (!idVehicule.equals(other.idVehicule))
			return false;
		return true;
	}

	public String getIdVehicule() {
		return idVehicule;
	}

	public void setIdVehicule(String idVehicule) {
		this.idVehicule = idVehicule;
	}

	public Integer getIdMission() {
		return idMission;
	}

	public void setIdMission(Integer idMission) {
		this.idMission = idMission;
	}

}
