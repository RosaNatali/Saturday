package ctr;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import persistence.Chauffeur;
import persistence.EtatMission;
import persistence.MChauffeurDetail;
import persistence.MVehiculeDetail;
import persistence.Mission;
import persistence.Produit;
import persistence.Vehicule;
import services.BasicOpsLocal;
import services.ChauffeurServiceLocal;
import services.VehiculeServicesLocal;

@ManagedBean
@ViewScoped
public class MissionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Chauffeur> chauffeursSelectedToBeAdded = new ArrayList<>();
	private String imageNameSelected = "";
	private List<String> imagesToBeLoaded = new ArrayList<>();
	private List<Vehicule> vehiculesSelected = new ArrayList<>();
	private List<Vehicule> vehiculesSelectedToBeDeeleted = new ArrayList<>();
	private Vehicule vehiculeToAdd = new Vehicule();
	private List<Mission> filtredMissions = new ArrayList<>();
	private Boolean showAdd = false;
	private List<String> imagesByMission = new ArrayList<>();
	private List<Chauffeur> chauffeursSelectedToBeDeleted = new ArrayList<>();
	private String imageName = "";
	private Mission missionSelected = new Mission();
	private Mission missionNew = new Mission();
	private Vehicule vehicule = new Vehicule();
	private List<Mission> missions = new ArrayList<>();
	private Boolean formDisplay = false;
	private List<Chauffeur> chauffeurs = new ArrayList<>();
	private List<Produit> produits = new ArrayList<>();
	private List<Vehicule> vehicules = new ArrayList<>();
	private Chauffeur chauffeurSelected = new Chauffeur();
	private List<Chauffeur> chauffeursSelected = new ArrayList<>();
	private Boolean showListChauffeur = false;
	private Boolean showListVehicule = false;
	private Boolean showId = true;
	@EJB
	private BasicOpsLocal basicOpsLocal;
	@EJB
	private ChauffeurServiceLocal chauffeurServiceLocal;
	@EJB
	private VehiculeServicesLocal vehiculeServicesLocal;
	private List<Chauffeur> chauffeursByMission = new ArrayList<>();
	private List<Produit> produitsByMission = new ArrayList<>();
	private List<Vehicule> vehiculesByMission = new ArrayList<>();
	@ManagedProperty(value = "#{affectationChauffeurs}")
	private AffectationChauffeurs affectationChauffeurs;
	private SecureRandom random = new SecureRandom();

	private Mission sampleMission;
	private List<Vehicule> sampleVehicules;
	private List<Chauffeur> sampleChauffeurs;
	private List<MVehiculeDetail> sampleMVDs;
	private List<MChauffeurDetail> sampleMCDs;
	
	private Mission sampleMissiona;
	private List<MVehiculeDetail> sampleVehiculesa;
	private List<MChauffeurDetail> sampleChauffeursa;
	
	private Mission sampleMissionb;
	private List<MVehiculeDetail> sampleVehiculesb;
	private List<MChauffeurDetail> sampleChauffeursb;
	
	private boolean displayDriversList = true;
	private boolean displayVehiclesList = false;
	
	private List<String> listMissionSites;
	private String doyes;
	
	private String sarsoura="lol";
	private Chauffeur selectedChauffeur;
	
	private Mission mission;
	
	private Integer newMission;
	private Integer enCoursMission;
	private Integer termineeMission;
	private Integer attenteMission;
	
	private String sample = "NEW";
	
	@PostConstruct
	public void init() {
		
		newMission = basicOpsLocal.findNbMissionsByEtat(EtatMission.NEW);
		enCoursMission = basicOpsLocal.findNbMissionsByEtat(EtatMission.ENCOURS);
        termineeMission = basicOpsLocal.findNbMissionsByEtat(EtatMission.TERMINEE);
        attenteMission = basicOpsLocal.findNbMissionsByEtat(EtatMission.ATTENTE);
        
        
		missions = basicOpsLocal.findAllMission();
		missionNew = new Mission();
		mission = new Mission();
		listMissionSites = new ArrayList<String>();
		List<Mission> lms = basicOpsLocal.findAllMission();
		System.out.println("---------------------------------SIZE: " + lms.size());
		for(Mission m : lms)
		{
			listMissionSites.add(m.getSite());
		}
		System.out.println("-------------------------------------------ALL: " + listMissionSites.size());
		
		sampleMission = basicOpsLocal.findMissionById(1);
		sampleVehicules = basicOpsLocal.findVehiculesByMission(1);
		sampleChauffeurs = basicOpsLocal.findChauffeursByMission(1);
		sampleMVDs = basicOpsLocal.findVehiculesByMissionSE(1);
		sampleMCDs = basicOpsLocal.findChauffeursByMissionSE(1);
		
		
		System.out.println("-----------------------------------Sample Mission: " + sampleMission.getSite());
		System.out.println("-----------------------------------Sample Mission: " + sampleMission.getDateCreation());
		System.out.println("-----------------------------------Sample Mission: " + sampleMission.getDateAffectation());
		System.out.println("-----------------------------------Sample Mission: " + sampleMission.getEtatMission());
		System.out.println("-----------------------------------Sample Mission: " + sampleMission.getmChauffeurDetails().size());
		System.out.println("-----------------------------------Sample Mission: " + sampleMission.getmVehiculeDetails().size());
		
		System.out.println("-----------------------------------1 Size Vehicules: " + sampleVehicules.size());
		System.out.println("-----------------------------------1 Size Chauffeurs: " + sampleChauffeurs.size());
	
		System.out.println("-----------------------------------2 Size Vehicules: " + sampleMVDs.size());
		System.out.println("-----------------------------------2 Size Chauffeurs: " + sampleMCDs.size());
		
	}
	
	
	
	public void createMission()
	{
		basicOpsLocal.creerMission(mission);
	}
	
	public void handleFileUpload(FileUploadEvent event) {

	}

	public void plus() {
		showListChauffeur = true;
		chauffeurs = chauffeurServiceLocal.findAllT();
	}

	public void addVehiculeToMission() {
		basicOpsLocal.affecterVehiculeMission(vehiculeToAdd, missionSelected);
	}

	public void closeMission() {
		EtatMission etatMission = EtatMission.TERMINEE;
		basicOpsLocal.etatUpdateMission(missionSelected, etatMission);
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('t').clearFilters()");
	}

	public void doUpdateMission() {
		missionSelected.getListImagesNames().addAll(imagesToBeLoaded);

		basicOpsLocal.creerMission(missionSelected);

		imagesToBeLoaded = new ArrayList<>();
	}

	public void plusVehicules() {
		showListVehicule = true;
		vehicules = vehiculeServicesLocal.findAllT();
	}

	public void affecterChauffeursMission() {
		basicOpsLocal.affecterChauffeursMission(chauffeursSelected, missionSelected);
	}

	public void affecterVehiculesMission() {
		basicOpsLocal.affecterVehiculesMission(vehiculesSelected, missionSelected);
	}

	public void affecterVehiculeMission() {
		basicOpsLocal.affecterVehiculeMission(vehiculeToAdd, missionSelected);
	}

	public void affecterChauffeursMissionNew() {
		basicOpsLocal.affecterChauffeursNewMission(chauffeursSelectedToBeAdded, missionNew);
	}

	public void deleteChauffeurFromMission() {
		basicOpsLocal.deleteChauffeurFromMission(chauffeursSelectedToBeDeleted, missionSelected);
	}

	public void deleteVehiculesFromMission() {
		basicOpsLocal.deleteVehiculesFromMission(vehiculesSelectedToBeDeeleted, missionSelected);
	}

	public void addMissionFromPhoto(byte[] image) {
		Mission mission = new Mission();
		mission.setDateEstime(new Date());

		basicOpsLocal.affecterChauffeurMission(affectationChauffeurs.getChauffeurSelected(), mission);
	}

	public void updateSelectedMission() {
		basicOpsLocal.creerMission(missionSelected);
	}

	public void aah() {
		missionSelected = new Mission();
		missionNew = new Mission();
		System.out.println(missionNew.getSite());
	}

	public String formNew() {
		return "/pages/missionHome/newMission?faces-redirect=true";
	}

	public void doS() {
		basicOpsLocal.creerMission(missionSelected);
	}

	public String doDeleteMission() {
		basicOpsLocal.deleteMission(missionSelected);
		cancel();
		showListChauffeur = false;
		showId = true;
		return "";
	}

	public String doSaveOrUpdateMission() {
		basicOpsLocal.creerNewMission(missionNew);
		showAdd = false;
		return "/pages/missionHome/mission?faces-redirect=true";
	}

	public void doShowAdd() {
		showAdd = true;
	}

	public String updateMission() {
		basicOpsLocal.creerMission(missionSelected);
		missions = basicOpsLocal.findAllMission();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('t').clearFilters()");
		return "";
	}

	public void cancel() {
		missionSelected = new Mission();
		formDisplay = false;
		showListChauffeur = false;
		showId = true;
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.execute("PF('t').clearFilters()");
	}

	public List<Chauffeur> allDrivers(ActionEvent event)
	{
		setDisplayDriversList(true);
		setDisplayVehiclesList(false);
		return basicOpsLocal.findChauffeursByMission(1);
	}
	
	public List<Vehicule> allVehicles(ActionEvent event)
	{
		setDisplayVehiclesList(true);
		setDisplayDriversList(false);
		return basicOpsLocal.findVehiculesByMission(1);
	}
	
	public String verifySite(String currentSite)
	{
		List<Mission> lms = basicOpsLocal.findAllMission();
		List<String> lss = new ArrayList<String>();
		
		for(Mission m : lms)
		{
			lss.add(m.getSite());
		}
		
		if(lss.contains(currentSite))
		{
			doyes = "This username is already exists !";
			System.out.println("************************************YES");
		}
		else
		{
			doyes = "";
			System.out.println("************************************NO");
		}
		return doyes;
	}
	
	public void validateLoginUnicity(FacesContext context, UIComponent component, Object value) throws ValidatorException 
	{
		String loginToValidate = (String) value;
		if (loginToValidate == null || loginToValidate.trim().isEmpty())
		{
			return;
		}
		boolean loginInUse = basicOpsLocal.loginExists(loginToValidate);
		if (loginInUse)
		{
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "This username is already exists !"));
		}
	}
	
	

	public List<Mission> getMissions() {
		missions = basicOpsLocal.findAllMission();
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

	public Mission getMissionSelected() {
		return missionSelected;
	}

	public void setMissionSelected(Mission missionSelected) {
		this.missionSelected = missionSelected;
	}

	public Boolean getFormDisplay() {
		return formDisplay;
	}

	public void setFormDisplay(Boolean formDisplay) {
		this.formDisplay = formDisplay;
	}

	public List<Chauffeur> getChauffeurs() {
		chauffeurs = chauffeurServiceLocal.findAllT();
		return chauffeurs;
	}

	public void setChauffeurs(List<Chauffeur> chauffeurs) {
		this.chauffeurs = chauffeurs;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public List<Vehicule> getVehicules() {
		return vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

	public Chauffeur getChauffeurSelected() {
		return chauffeurSelected;
	}

	public void setChauffeurSelected(Chauffeur chauffeurSelected) {
		this.chauffeurSelected = chauffeurSelected;
	}

	public List<Chauffeur> getChauffeursSelected() {
		return chauffeursSelected;
	}

	public void setChauffeursSelected(List<Chauffeur> chauffeursSelected) {
		this.chauffeursSelected = chauffeursSelected;
	}

	public Boolean getShowListChauffeur() {
		return showListChauffeur;
	}

	public void setShowListChauffeur(Boolean showListChauffeur) {
		this.showListChauffeur = showListChauffeur;
	}

	public List<Chauffeur> getChauffeursByMission() {
		chauffeursByMission = basicOpsLocal.findChauffeursByMission(missionSelected.getId());
		return chauffeursByMission;
	}

	public void setChauffeursByMission(List<Chauffeur> chauffeursByMission) {
		this.chauffeursByMission = chauffeursByMission;
	}

	public List<Produit> getProduitsByMission() {
		return produitsByMission;
	}

	public void setProduitsByMission(List<Produit> produitsByMission) {
		this.produitsByMission = produitsByMission;
	}

	public List<Vehicule> getVehiculesByMission() {
		return vehiculesByMission;
	}

	public void setVehiculesByMission(List<Vehicule> vehiculesByMission) {
		this.vehiculesByMission = vehiculesByMission;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Boolean getShowListVehicule() {
		return showListVehicule;
	}

	public void setShowListVehicule(Boolean showListVehicule) {
		this.showListVehicule = showListVehicule;
	}

	public BasicOpsLocal getBasicOpsLocal() {
		return basicOpsLocal;
	}

	public void setBasicOpsLocal(BasicOpsLocal basicOpsLocal) {
		this.basicOpsLocal = basicOpsLocal;
	}

	public ChauffeurServiceLocal getChauffeurServiceLocal() {
		return chauffeurServiceLocal;
	}

	public void setChauffeurServiceLocal(ChauffeurServiceLocal chauffeurServiceLocal) {
		this.chauffeurServiceLocal = chauffeurServiceLocal;
	}

	public VehiculeServicesLocal getVehiculeServicesLocal() {
		return vehiculeServicesLocal;
	}

	public void setVehiculeServicesLocal(VehiculeServicesLocal vehiculeServicesLocal) {
		this.vehiculeServicesLocal = vehiculeServicesLocal;
	}

	public Boolean getShowId() {
		return showId;
	}

	public void setShowId(Boolean showId) {
		this.showId = showId;
	}

	public AffectationChauffeurs getAffectationChauffeurs() {
		return affectationChauffeurs;
	}

	public void setAffectationChauffeurs(AffectationChauffeurs affectationChauffeurs) {
		this.affectationChauffeurs = affectationChauffeurs;
	}

	public SecureRandom getRandom() {
		return random;
	}

	public void setRandom(SecureRandom random) {
		this.random = random;
	}

	public void onRowSelect(SelectEvent event) {
		missionSelected = (Mission) event.getObject();
		FacesMessage msg = new FacesMessage("mission choisit", String.valueOf(((Mission) event.getObject()).getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		missionSelected = new Mission();
		FacesMessage msg = new FacesMessage("mission Unselected",
				((Mission) event.getObject()).getDateEstime().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void destroyWorld() {
		addMessage("System info", "Please try again later.");
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Mission getMissionNew() {
		return missionNew;
	}

	public void setMissionNew(Mission missionNew) {
		this.missionNew = missionNew;
	}

	public List<String> getImagesByMission() {
		return imagesByMission;
	}

	public void setImagesByMission(List<String> imagesByMission) {
		this.imagesByMission = imagesByMission;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public List<Chauffeur> getChauffeursSelectedToBeDeleted() {
		return chauffeursSelectedToBeDeleted;
	}

	public void setChauffeursSelectedToBeDeleted(List<Chauffeur> chauffeursSelectedToBeDeleted) {
		this.chauffeursSelectedToBeDeleted = chauffeursSelectedToBeDeleted;
	}

	public Boolean getShowAdd() {
		return showAdd;
	}

	public void setShowAdd(Boolean showAdd) {
		this.showAdd = showAdd;
	}

	public List<Mission> getFiltredMissions() {
		return filtredMissions;
	}

	public void setFiltredMissions(List<Mission> filtredMissions) {
		this.filtredMissions = filtredMissions;
	}

	public Vehicule getVehiculeToAdd() {
		return vehiculeToAdd;
	}

	public void setVehiculeToAdd(Vehicule vehiculeToAdd) {
		this.vehiculeToAdd = vehiculeToAdd;
	}

	public List<Vehicule> getVehiculesSelected() {
		return vehiculesSelected;
	}

	public void setVehiculesSelected(List<Vehicule> vehiculesSelected) {
		this.vehiculesSelected = vehiculesSelected;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getImagesToBeLoaded() {
		return imagesToBeLoaded;
	}

	public void setImagesToBeLoaded(List<String> imagesToBeLoaded) {
		this.imagesToBeLoaded = imagesToBeLoaded;
	}

	public String getImageNameSelected() {
		return imageNameSelected;
	}

	public void setImageNameSelected(String imageNameSelected) {
		this.imageNameSelected = imageNameSelected;
	}

	public List<Chauffeur> getChauffeursSelectedToBeAdded() {
		return chauffeursSelectedToBeAdded;
	}

	public void setChauffeursSelectedToBeAdded(List<Chauffeur> chauffeursSelectedToBeAdded) {
		this.chauffeursSelectedToBeAdded = chauffeursSelectedToBeAdded;
	}

	public List<Vehicule> getVehiculesSelectedToBeDeeleted() {
		return vehiculesSelectedToBeDeeleted;
	}

	public void setVehiculesSelectedToBeDeeleted(List<Vehicule> vehiculesSelectedToBeDeeleted) {
		this.vehiculesSelectedToBeDeeleted = vehiculesSelectedToBeDeeleted;
	}

	public Mission getSampleMission() {
		return sampleMission;
	}

	public void setSampleMission(Mission sampleMission) {
		this.sampleMission = sampleMission;
	}

	public List<MVehiculeDetail> getSampleVehiculesa() {
		return sampleVehiculesa;
	}

	public void setSampleVehiculesa(List<MVehiculeDetail> sampleVehiculesa) {
		this.sampleVehiculesa = sampleVehiculesa;
	}

	public List<MChauffeurDetail> getSampleChauffeursa() {
		return sampleChauffeursa;
	}

	public void setSampleChauffeursa(List<MChauffeurDetail> sampleChauffeursa) {
		this.sampleChauffeursa = sampleChauffeursa;
	}

	public List<MVehiculeDetail> getSampleVehiculesb() {
		return sampleVehiculesb;
	}

	public void setSampleVehiculesb(List<MVehiculeDetail> sampleVehiculesb) {
		this.sampleVehiculesb = sampleVehiculesb;
	}

	public List<MChauffeurDetail> getSampleChauffeursb() {
		return sampleChauffeursb;
	}

	public void setSampleChauffeursb(List<MChauffeurDetail> sampleChauffeursb) {
		this.sampleChauffeursb = sampleChauffeursb;
	}

	public Mission getSampleMissiona() {
		return sampleMissiona;
	}

	public void setSampleMissiona(Mission sampleMissiona) {
		this.sampleMissiona = sampleMissiona;
	}

	public Mission getSampleMissionb() {
		return sampleMissionb;
	}

	public void setSampleMissionb(Mission sampleMissionb) {
		this.sampleMissionb = sampleMissionb;
	}

	public List<Vehicule> getSampleVehicules() {
		return sampleVehicules;
	}

	public void setSampleVehicules(List<Vehicule> sampleVehicules) {
		this.sampleVehicules = sampleVehicules;
	}

	public List<Chauffeur> getSampleChauffeurs() {
		return sampleChauffeurs;
	}

	public void setSampleChauffeurs(List<Chauffeur> sampleChauffeurs) {
		this.sampleChauffeurs = sampleChauffeurs;
	}

	public List<MVehiculeDetail> getSampleMVDs() {
		return sampleMVDs;
	}

	public void setSampleMVDs(List<MVehiculeDetail> sampleMVDs) {
		this.sampleMVDs = sampleMVDs;
	}

	public List<MChauffeurDetail> getSampleMCDs() {
		return sampleMCDs;
	}

	public void setSampleMCDs(List<MChauffeurDetail> sampleMCDs) {
		this.sampleMCDs = sampleMCDs;
	}

	public boolean isDisplayDriversList() {
		return displayDriversList;
	}

	public void setDisplayDriversList(boolean displayDriversList) {
		this.displayDriversList = displayDriversList;
	}

	public boolean isDisplayVehiclesList() {
		return displayVehiclesList;
	}

	public void setDisplayVehiclesList(boolean displayVehiclesList) {
		this.displayVehiclesList = displayVehiclesList;
	}

	/**************************************************************************************/
	private int index = 0;
	private String messageNum1 = "Tab#1 Content Is Loaded";
	private String messageNum2 = "Tab#2 Content Is Loaded";
	
	public String getMessageNum1() {
		System.out.println(messageNum1);
		return messageNum1;
	}

	public void setMessageNum1(String messageNum1) {
		this.messageNum1 = messageNum1;
	}

	public String getMessageNum2() {
		System.out.println(messageNum2);
		return messageNum2;
	}

	public void setMessageNum2(String messageNum2) {
		this.messageNum2 = messageNum2;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public List<String> getListMissionSites() {
		return listMissionSites;
	}

	public void setListMissionSites(List<String> listMissionSites) {
		this.listMissionSites = listMissionSites;
	}

	public String getDoyes() {
		return doyes;
	}

	public void setDoyes(String doyes) {
		this.doyes = doyes;
	}

	public String getSarsoura() {
		return sarsoura;
	}

	public void setSarsoura(String sarsoura) {
		this.sarsoura = sarsoura;
	}

	public Chauffeur getSelectedChauffeur() {
		return selectedChauffeur;
	}

	public void setSelectedChauffeur(Chauffeur selectedChauffeur) {
		this.selectedChauffeur = selectedChauffeur;
	}



	public Integer getNewMission() {
		return newMission;
	}



	public void setNewMission(Integer newMission) {
		this.newMission = newMission;
	}



	public Integer getEnCoursMission() {
		return enCoursMission;
	}



	public void setEnCoursMission(Integer enCoursMission) {
		this.enCoursMission = enCoursMission;
	}



	public Integer getTermineeMission() {
		return termineeMission;
	}



	public void setTermineeMission(Integer termineeMission) {
		this.termineeMission = termineeMission;
	}



	public Integer getAttenteMission() {
		return attenteMission;
	}



	public void setAttenteMission(Integer attenteMission) {
		this.attenteMission = attenteMission;
	}



	public String getSample() {
		return sample;
	}



	public void setSample(String sample) {
		this.sample = sample;
	}

	
	
	
}
