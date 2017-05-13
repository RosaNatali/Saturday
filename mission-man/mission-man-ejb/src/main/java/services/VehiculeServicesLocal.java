package services;

import javax.ejb.Local;

import persistence.Vehicule;
import utilities.Crud;

@Local
public interface VehiculeServicesLocal extends Crud<Vehicule> {
}
