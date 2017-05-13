package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.Chauffeur;

/**
 * Session Bean implementation class ChauffeurService
 */
@Stateless
public class ChauffeurService implements ChauffeurServiceRemote, ChauffeurServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ChauffeurService() {
	}

	@Override
	public void saveOrUpdateT(Chauffeur t) {
		entityManager.merge(t);
	}

	@Override
	public void deleteT(Chauffeur t) {
		entityManager.remove(entityManager.merge(t));
	}

	@Override
	public List<Chauffeur> findAllT() {
		return entityManager.createQuery("SELECT c FROM Chauffeur c", Chauffeur.class).getResultList();
	}

	@Override
	public Chauffeur findTByName(String name) {
		Chauffeur chauffeur = null;
		try {
			chauffeur = entityManager.createQuery("SELECT c FROM Chauffeur c WHERE c.nom = :p", Chauffeur.class)
					.setParameter("p", name).getSingleResult();
		} catch (Exception e) {
			System.out.println(name + " not found");
		}
		return chauffeur;
	}

	@Override
	public Chauffeur findTById(String id) {
		return entityManager.find(Chauffeur.class, id);
	}

	@Override
	public Chauffeur findTByIDNew(Integer id) {
		return entityManager.find(Chauffeur.class, id);
	}

}
