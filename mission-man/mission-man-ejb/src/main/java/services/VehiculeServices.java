package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.Vehicule;

/**
 * Session Bean implementation class VehiculeServices
 */
@Stateless
public class VehiculeServices implements VehiculeServicesRemote, VehiculeServicesLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public VehiculeServices() {
	}

	@Override
	public void saveOrUpdateT(Vehicule t) {
		entityManager.merge(t);

	}

	@Override
	public void deleteT(Vehicule t) {
		entityManager.remove(entityManager.merge(t));

	}

	@Override
	public Vehicule findTById(String id) {
		return entityManager.find(Vehicule.class, id);
	}

	@Override
	public List<Vehicule> findAllT() {
		return entityManager.createQuery("select v from Vehicule v ", Vehicule.class).getResultList();
	}

}
