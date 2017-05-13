package services;

import javax.ejb.Local;

import persistence.Chauffeur;
import utilities.Crud;

@Local
public interface ChauffeurServiceLocal extends Crud<Chauffeur> {

	Chauffeur findTByName(String name);
	
	Chauffeur findTByIDNew(Integer id);

}
