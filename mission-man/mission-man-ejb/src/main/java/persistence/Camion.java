package persistence;

import java.io.Serializable;
import javax.persistence.*;
import persistence.Vehicule;

/**
 * Entity implementation class for Entity: Camion
 *
 */
@Entity

public class Camion extends Vehicule implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Camion() {
		super();
	}

	public Camion(String immatriculation, Carburant carburant, Marque marque, Modele modele,
			Transmission transmission) {
		super(immatriculation, carburant, marque, modele, transmission);
	}
   
}
