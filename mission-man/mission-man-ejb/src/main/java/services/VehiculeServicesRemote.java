package services;

import javax.ejb.Remote;

import persistence.Vehicule;
import utilities.Crud;

@Remote
public interface VehiculeServicesRemote extends Crud<Vehicule> {

}
