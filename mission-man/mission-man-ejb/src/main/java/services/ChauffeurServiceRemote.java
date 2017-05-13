package services;

import javax.ejb.Remote;

import persistence.Chauffeur;
import utilities.Crud;

@Remote
public interface ChauffeurServiceRemote extends Crud<Chauffeur> {
	Chauffeur findTByName(String name);

	Chauffeur findTByIDNew(Integer id);
}
