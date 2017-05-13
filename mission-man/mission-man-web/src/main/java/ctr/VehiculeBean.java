package ctr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import persistence.Vehicule;
import services.BasicOpsLocal;
import services.VehiculeServicesLocal;

@ManagedBean
@ViewScoped
public class VehiculeBean {
	private Boolean showForm = false;
	private List<Vehicule> vehicules = new ArrayList<>();
	private List<Vehicule> vehiculesSelected = new ArrayList<>();
	private List<Vehicule> vehiculesByMission = new ArrayList<>();
	private Vehicule selectedVehicule = new Vehicule();;
	private Vehicule vehicule = new Vehicule();
	@EJB
	private VehiculeServicesLocal vehiculeServicesLocal;
	@EJB
	private BasicOpsLocal basicOpsLocal;
	@ManagedProperty(value = "#{missionBean}")
	private MissionBean missionBean;

	@PostConstruct
	public void init() {
		// vehicules = vehiculeServicesLocal.findAllVehicule();
	}

	public void view() {
		System.out.println(selectedVehicule.getImmatriculation());
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		RequestContext.getCurrentInstance().openDialog("viewCars", options, null);
	}

	public void select() {
		showForm = true;
	}

	public void doNew() {
		vehicule = new Vehicule();
		showForm = true;
	}

	public void cancel() {
		showForm = false;
		vehicule = new Vehicule();
	}

	public void delete() {
		showForm = false;
		vehiculeServicesLocal.deleteT(selectedVehicule);
		vehicule = new Vehicule();
	}

	public void doUpdate() {
		vehiculeServicesLocal.saveOrUpdateT(selectedVehicule);
	}

	public String doSaveOrUpdateVehiule() {
		vehiculeServicesLocal.saveOrUpdateT(vehicule);
		return "/pages/listVehicules?faces-redirect=true";
	}

	public List<Vehicule> getVehicules() {
		vehicules = vehiculeServicesLocal.findAllT();
		return vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

	public Vehicule getSelectedVehicule() {
		return selectedVehicule;
	}

	public void setSelectedVehicule(Vehicule selectedVehicule) {
		this.selectedVehicule = selectedVehicule;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Boolean getShowForm() {
		return showForm;
	}

	public void setShowForm(Boolean showForm) {
		this.showForm = showForm;
	}

	public List<Vehicule> getVehiculesByMission() {
		Integer idMission = missionBean.getMissionSelected().getId();
		vehiculesByMission = basicOpsLocal.findVehiculesByMission(idMission);
		return vehiculesByMission;
	}

	public void setVehiculesByMission(List<Vehicule> vehiculesByMission) {
		this.vehiculesByMission = vehiculesByMission;
	}

	public MissionBean getMissionBean() {
		return missionBean;
	}

	public void setMissionBean(MissionBean missionBean) {
		this.missionBean = missionBean;
	}

	public Object doFindVehiculeByImmatriculation(String value) {
		return vehiculeServicesLocal.findTById(value);
	}

	public void onRowSelect(SelectEvent event) {
		selectedVehicule = (Vehicule) event.getObject();
	}

	public void onRowUnselect(UnselectEvent event) {
		selectedVehicule = (Vehicule) event.getObject();
	}

	public List<Vehicule> getVehiculesSelected() {
		return vehiculesSelected;
	}

	public void setVehiculesSelected(List<Vehicule> vehiculesSelected) {
		this.vehiculesSelected = vehiculesSelected;
	}
}
