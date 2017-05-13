package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import persistence.Produit;

/**
 * Session Bean implementation class ProduitServices
 */
@Stateless
public class ProduitServices implements ProduitServicesRemote, ProduitServicesLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ProduitServices() {
	}

	@Override
	public void saveOrUpdateT(Produit t) {
		entityManager.merge(t);
	}

	@Override
	public void deleteT(Produit t) {
		entityManager.remove(entityManager.merge(t));
	}

	@Override
	public Produit findTById(String id) {
		return entityManager.find(Produit.class, id);
	}

	@Override
	public List<Produit> findAllT() {
		return entityManager.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
	}

}
