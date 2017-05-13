package persistence;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Agent
 *
 */
@Entity

public class Agent extends User implements Serializable {

	private String fonction;
	private static final long serialVersionUID = 1L;

	public Agent() {
		super();
	}

	public Agent(String name, String login, String password, String fonction) {
		super(name, login, password);
		this.fonction = fonction;
	}

	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

}
