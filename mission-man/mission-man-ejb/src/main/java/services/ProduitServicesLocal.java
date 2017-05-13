package services;

import javax.ejb.Local;

import persistence.Produit;
import utilities.Crud;

@Local
public interface ProduitServicesLocal extends Crud<Produit> {

}
