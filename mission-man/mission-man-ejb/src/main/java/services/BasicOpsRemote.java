package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Chauffeur;
import persistence.EtatMission;
import persistence.Mission;
import persistence.Produit;
import persistence.User;
import persistence.Vehicule;

@Remote
public interface BasicOpsRemote {
	void creerMission(Mission mission);

	void creerNewMission(Mission mission);

	void deleteMission(Mission mission);

	void creerMChauffeurDetail(Mission mission, Chauffeur chauffeur);

	void creerMProduitDetail(Mission mission, Produit produit);

	void creerMVehiculedetail(Mission mission, Vehicule vehicule);

	Mission findMissionById(Integer id);

	List<Mission> findAllMission();

	void affecterChauffeurMission(Chauffeur chauffeur, Mission mission);

	void affecterChauffeursMission(List<Chauffeur> chauffeurs, Mission mission);

	void affecterVehiculesMission(List<Vehicule> vehicules, Mission mission);

	void affecterVehiculeMission(Vehicule vehicule, Mission mission);

	void affecterProduitMission(Produit produit, Mission mission);

	void affecterProduitsMission(List<Produit> produits, Mission mission);

	List<Vehicule> findVehiculesByMission(Integer idMission);

	List<Chauffeur> findChauffeursByMission(Integer idMission);

	List<Produit> findProduitsByMission(Integer idMission);

	void saveOrUpdateUser(User user);

	User findUserById(Integer id);

	User login(String login, String password);

	void deleteChauffeurFromMission(List<Chauffeur> chauffeursSelectedToBeDeleted, Mission missionSelected);

	void etatUpdateMission(Mission missionSelected, EtatMission etatMission);

	void addImage(String imagePath, Mission mission);

	void affecterChauffeurNewMission(Chauffeur chauffeur, Mission mission);

	void affecterChauffeursNewMission(List<Chauffeur> chauffeurs, Mission mission);
}
