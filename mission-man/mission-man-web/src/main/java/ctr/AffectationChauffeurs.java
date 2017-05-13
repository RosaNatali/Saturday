package ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.Chauffeur;
import persistence.Mission;
import services.BasicOpsLocal;
import services.ChauffeurServiceLocal;

@ManagedBean
@ViewScoped
public class AffectationChauffeurs {
	private Mission mission = new Mission();
	private Chauffeur chauffeurNew = new Chauffeur();
	private Chauffeur chauffeurSelected = new Chauffeur();
	private List<Chauffeur> chauffeurs = new ArrayList<>();
	private List<Chauffeur> chauffeursDisponibles = new ArrayList<>();
	@EJB
	private BasicOpsLocal basicOpsLocal;
	@EJB
	private ChauffeurServiceLocal chauffeurServiceLocal;

	public void affecter() {
		System.out.println(chauffeurSelected.getId());
		basicOpsLocal.affecterChauffeurMission(chauffeurSelected, mission);
	}

	public void creerChauffeur() {
		chauffeurServiceLocal.saveOrUpdateT(chauffeurNew);
		chauffeurNew = new Chauffeur();
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public List<Chauffeur> getChauffeurs() {
		chauffeurs = basicOpsLocal.findChauffeursByMission(mission.getId());
		return chauffeurs;
	}

	public void setChauffeurs(List<Chauffeur> chauffeurs) {
		this.chauffeurs = chauffeurs;
	}

	public Chauffeur getChauffeurSelected() {
		return chauffeurSelected;
	}

	public void setChauffeurSelected(Chauffeur chauffeurSelected) {
		this.chauffeurSelected = chauffeurSelected;
	}

	public List<Chauffeur> getChauffeursDisponibles() {
		chauffeursDisponibles = chauffeurServiceLocal.findAllT();
		return chauffeursDisponibles;
	}

	public void setChauffeursDisponibles(List<Chauffeur> chauffeursDisponibles) {
		this.chauffeursDisponibles = chauffeursDisponibles;
	}

	public Chauffeur getChauffeurNew() {
		return chauffeurNew;
	}

	public void setChauffeurNew(Chauffeur chauffeurNew) {
		this.chauffeurNew = chauffeurNew;
	}
}
