package ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.Produit;
import services.ProduitServicesLocal;

@ManagedBean
@ViewScoped
public class ProduitBean {
	private Produit productSelected = new Produit();
	private List<Produit> produits = new ArrayList<>();
	private Boolean show = false;

	@EJB
	private ProduitServicesLocal produitServicesLocal;

	public void doSaveOrUpdateProduit() {
		produitServicesLocal.saveOrUpdateT(productSelected);
	}

	public void doNew() {
		productSelected = new Produit();
		show = true;
	}

	public void select() {
		show = true;
	}

	public void cancel() {
		show = false;
		productSelected = new Produit();
	}

	public void delete() {
		show = false;
		produitServicesLocal.deleteT(productSelected);
		productSelected = new Produit();
	}

	public List<Produit> getProduits() {
		produits = produitServicesLocal.findAllT();
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Produit getProductSelected() {
		return productSelected;
	}

	public void setProductSelected(Produit productSelected) {
		this.productSelected = productSelected;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

}
