package services;

import javax.ejb.Remote;

import persistence.Produit;
import utilities.Crud;

@Remote
public interface ProduitServicesRemote extends Crud<Produit> {

}
