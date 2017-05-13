package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Agency;

@Local
public interface AgencyServicesLocal {

	Agency findAgencyByName(String name);

	List<Agency> findAllAgencies();
}
