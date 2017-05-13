package persistence;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Voiture
 *
 */
@Entity

public class Voiture extends Vehicule implements Serializable {

	private static final long serialVersionUID = 1L;

	public Voiture() {
		super();
	}

	public Voiture(String immatriculation, Carburant carburant, Marque marque, Modele modele,
			Transmission transmission) {
		super(immatriculation, carburant, marque, modele, transmission);
	}

}
