package ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import persistence.Chauffeur;
import services.ChauffeurServiceLocal;

@ManagedBean
@ViewScoped
public class ChauffeurBean {
	private Boolean showForm = false;
	private Chauffeur chauffeur = new Chauffeur();
	private Chauffeur chauffeurSelected = new Chauffeur();
	private List<Chauffeur> chauffeurs = new ArrayList<>();
	private Boolean show = false;

	@EJB
	private ChauffeurServiceLocal chauffeurServiceLocal;

	public Chauffeur doFindChauffeur(String name) {
		chauffeur = chauffeurServiceLocal.findTByName(name);
		return chauffeur;

	}

	public void doSome() {
	}

	public void cancel() {
		showForm = false;
	}

	public void select() {
		showForm = true;
	}

	public String doSaveOrUpdate() {
		chauffeurServiceLocal.saveOrUpdateT(chauffeur);
		showForm = false;
		return "/pages/listChauffeurs?faces-redirect=true";
	}
	public String doUpdate() {
		chauffeurServiceLocal.saveOrUpdateT(chauffeurSelected);
		showForm = false;
		return "/pages/listChauffeurs?faces-redirect=true";
	}
	public void delete() {
		chauffeurServiceLocal.deleteT(chauffeurSelected);
		showForm = false;
	}

	public void doNew() {
		chauffeur = new Chauffeur();
		showForm = true;
	}

	public Chauffeur getChauffeur() {
		return chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public List<Chauffeur> getChauffeurs() {
		chauffeurs = chauffeurServiceLocal.findAllT();
		return chauffeurs;
	}

	public void setChauffeurs(List<Chauffeur> chauffeurs) {

		this.chauffeurs = chauffeurs;
	}

	public Boolean getShowForm() {
		return showForm;
	}

	public void setShowForm(Boolean showForm) {
		this.showForm = showForm;
	}

	public void onRowSelect(SelectEvent event) {
		chauffeurSelected = (Chauffeur) event.getObject();
	}

	public void onRowUnselect(UnselectEvent event) {
		chauffeurSelected = (Chauffeur) event.getObject();
	}

	public Chauffeur getChauffeurSelected() {
		return chauffeurSelected;
	}

	public void setChauffeurSelected(Chauffeur chauffeurSelected) {
		this.chauffeurSelected = chauffeurSelected;
	}

}
