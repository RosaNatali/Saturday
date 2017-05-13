package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.Agency;
import services.interfaces.AgencyServicesRemote;

/**
 * Session Bean implementation class AgencyServices
 */
@Stateless
public class AgencyServices implements AgencyServicesRemote, AgencyServicesLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AgencyServices() {
	}

	@Override
	public Agency findAgencyByName(String name) {
		return entityManager.createQuery("select a from Agency a where a.logo=:param1", Agency.class)
				.setParameter("param1", name).getSingleResult();
	}

	@Override
	public List<Agency> findAllAgencies() {
		return entityManager.createQuery("select a from Agency a", Agency.class).getResultList();
	}
}
