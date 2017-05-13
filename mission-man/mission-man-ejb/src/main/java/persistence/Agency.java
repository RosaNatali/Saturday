package persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Agency
 *
 */
@Entity(name="Agency")
public class Agency implements Serializable {
	@Id
	private int id;
	private String logo;
	private static final long serialVersionUID = 1L;

	public Agency() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}