package services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import persistence.Chauffeur;
import persistence.EtatMission;
import persistence.MChauffeurDetail;
import persistence.MChauffeurDetailId;
import persistence.MVehiculeDetail;
import persistence.MVehiculeDetailId;
import persistence.Mission;
import persistence.Produit;
import persistence.User;
import persistence.Vehicule;
import utilities.DirectoryInitilizer;
import utilities.Util;

/**
 * Session Bean implementation class BasicOps
 */
@Stateless
public class BasicOps implements BasicOpsRemote, BasicOpsLocal {
	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private BasicOpsLocal basicOpsLocal;

	/**
	 * Default constructor.
	 */
	public BasicOps() {
	}

	@Override
	public void creerMission(Mission mission) {
//		mission.setEtatMission(EtatMission.ENCOURS);
		entityManager.merge(mission);
	}

	@Override
	public void creerMChauffeurDetail(Mission mission, Chauffeur chauffeur) {
		MChauffeurDetail mChauffeurDetail = new MChauffeurDetail(mission, chauffeur);

		entityManager.merge(mChauffeurDetail);

	}

	@Override
	public void creerMProduitDetail(Mission mission, Produit produit) {

	}

	@Override
	public void creerMVehiculedetail(Mission mission, Vehicule vehicule) {
		MVehiculeDetail mVehiculeDetail = new MVehiculeDetail(mission, vehicule);

		entityManager.merge(mVehiculeDetail);
	}

	@Override
	public Mission findMissionById(Integer id) {
		return entityManager.find(Mission.class, id);
	}

	@Override
	public void affecterChauffeurMission(Chauffeur chauffeur, Mission mission) {
		if (!basicOpsLocal.findChauffeursByMission(mission.getId()).contains(chauffeur)) {
			MChauffeurDetail mChauffeurDetail = new MChauffeurDetail(mission, chauffeur);
			entityManager.merge(mChauffeurDetail);
		} else {
			System.out.println("already chauffeur");
		}

	}

	@Override
	public void affecterChauffeursMission(List<Chauffeur> chauffeurs, Mission mission) {
		if (chauffeurs != null) {
			for (Chauffeur c : chauffeurs) {
				affecterChauffeurMission(c, mission);
			}
		} else {
			System.out.println("vide");
		}

	}

	@Override
	public void affecterVehiculesMission(List<Vehicule> vehicules, Mission mission) {
		if (vehicules != null) {
			for (Vehicule v : vehicules) {
				affecterVehiculeMission(v, mission);
			}
		} else {
			System.out.println("ouups ...");
		}

	}

	@Override
	public void affecterVehiculeMission(Vehicule vehicule, Mission mission) {
		if (!basicOpsLocal.findVehiculesByMission(mission.getId()).contains(vehicule)) {
			MVehiculeDetail mVehiculeDetail = new MVehiculeDetail(mission, vehicule);

			entityManager.merge(mVehiculeDetail);
		} else {
			System.out.println("vehicule affecté");
		}

	}

	@Override
	public void affecterProduitMission(Produit produit, Mission mission) {
	}

	@Override
	public void affecterProduitsMission(List<Produit> produits, Mission mission) {
		if (produits != null) {
			for (Produit p : produits) {
				affecterProduitMission(p, mission);
			}
		}
		{
			System.out.println("ouups ...");
		}

	}

	@Override
	public List<Mission> findAllMission() {
		return entityManager.createQuery("SELECT m FROM Mission m", Mission.class).getResultList();
	}

	@Override
	public List<Vehicule> findVehiculesByMission(Integer idMission) {
		return entityManager.createQuery("SELECT v FROM Vehicule v JOIN v.mVehiculeDetails vms WHERE vms.mission.id=:p",
				Vehicule.class).setParameter("p", idMission).getResultList();
	}
	
	
	@Override
	public List<Chauffeur> findChauffeursByMission(Integer idMission) {
		return entityManager
				.createQuery("SELECT v FROM Chauffeur v JOIN v.mChauffeurDetails vms WHERE vms.mission.id=:p",
						Chauffeur.class)
				.setParameter("p", idMission).getResultList();
	}
	
	
	@Override
	public List<Produit> findProduitsByMission(Integer idMission) {
		return entityManager.createQuery("SELECT v FROM Produit v JOIN v.mProduitDetails vms WHERE vms.mission.id=:p",
				Produit.class).setParameter("p", idMission).getResultList();
	}

	@Override
	public void saveOrUpdateUser(User user) {
		entityManager.merge(user);

	}

	@Override
	public User findUserById(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User login(String login, String password) {
		User user = null;
		try {
			user = entityManager.createQuery("SELECT u FROM User u WHERE u.login=:l AND u.password=:p", User.class)
					.setParameter("l", login).setParameter("p", password).getSingleResult();
		} catch (Exception e) {
		}
		return user;
	}

	@Override
	public void deleteMission(Mission mission) {
		entityManager.remove(entityManager.merge(mission));
	}

	@Override
	public void deleteChauffeurFromMission(List<Chauffeur> chauffeursSelectedToBeDeleted, Mission missionSelected) {
		if (chauffeursSelectedToBeDeleted != null) {
			for (Chauffeur c : chauffeursSelectedToBeDeleted) {
				entityManager.remove(entityManager.find(MChauffeurDetail.class,
						new MChauffeurDetailId(missionSelected.getId(), c.getId())));
			}
		} else {
			System.out.println("ooops");
		}

	}

	@Override
	public void etatUpdateMission(Mission missionSelected, EtatMission etatMission) {
		missionSelected.setEtatMission(etatMission);
		entityManager.merge(missionSelected);
	}

	@Override
	public void creerNewMission(Mission mission) {
//		mission.setEtatMission(EtatMission.NEW);
		entityManager.merge(mission);

	}

	@Override
	public void addImage(String imagePath, Mission mission) {
		mission.getListImagesNames().add(imagePath);

		entityManager.merge(mission);

	}

	@Override
	public void affecterChauffeurNewMission(Chauffeur chauffeur, Mission mission) {
		MChauffeurDetail mChauffeurDetail = new MChauffeurDetail(mission, chauffeur);
		entityManager.merge(mChauffeurDetail);

	}

	@Override
	public void affecterChauffeursNewMission(List<Chauffeur> chauffeurs, Mission mission) {
		Mission m = entityManager.merge(mission);
		if (chauffeurs != null) {
			for (Chauffeur c : chauffeurs) {
				affecterChauffeurNewMission(c, m);
			}
		} else {
			System.out.println("vide");
		}

	}

	@Override
	public void deleteVehiculesFromMission(List<Vehicule> vehiculesSelectedToBeDeeleted, Mission missionSelected) {
		if (vehiculesSelectedToBeDeeleted != null) {
			for (Vehicule c : vehiculesSelectedToBeDeeleted) {
				entityManager.remove(entityManager.find(MVehiculeDetail.class,
						new MVehiculeDetailId(c.getImmatriculation(), missionSelected.getId())));
			}
		} else {
			System.out.println("ooops");
		}

	}

	@Override
	public int findNbMissionsByEtat(EtatMission etat) {
		List<Mission> missions = findAllMission();
		int result = 0;
		for (Mission m : missions) {
			if (m.getEtatMission() == etat) {
				result++;
			}
		}

		return result;
	}

	@Override
	public boolean addMissionWithoutApp(MultipartFormDataInput input) {
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		ObjectMapper mapper = new ObjectMapper();
		String chauffeurJson = null;
		List<String> images = new ArrayList<>();
		try {
			chauffeurJson = uploadForm.get("chauffeur").get(0).getBody(String.class, null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (chauffeurJson != null) {

			Chauffeur chauffeur = null;
			try {
				chauffeur = mapper.readValue(chauffeurJson, Chauffeur.class);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<InputPart> inputParts = uploadForm.get("picture");
			if (inputParts != null) {
				String shortImagePath = UUID.randomUUID().toString();
				String imagePath = DirectoryInitilizer.IMAGE_DIR + "/" + shortImagePath + ".jpg";
				images.add(shortImagePath);
				for (InputPart inputPart : inputParts) {
					try {
						InputStream inputStream = inputPart.getBody(InputStream.class, null);
						Util.copyFileUsingFileStreams(inputStream, imagePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			List<InputPart> inputParts2 = uploadForm.get("picture2");
			if (inputParts2 != null) {
				String shortImagePath = UUID.randomUUID().toString();
				String imagePath = DirectoryInitilizer.IMAGE_DIR + "/" + shortImagePath + ".jpg";
				images.add(shortImagePath);
				for (InputPart inputPart : inputParts2) {
					try {
						InputStream inputStream = inputPart.getBody(InputStream.class, null);
						Util.copyFileUsingFileStreams(inputStream, imagePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			List<InputPart> inputParts3 = uploadForm.get("picture3");
			if (inputParts3 != null) {
				String shortImagePath = UUID.randomUUID().toString();
				String imagePath = DirectoryInitilizer.IMAGE_DIR + "/" + shortImagePath + ".jpg";
				images.add(shortImagePath);
				for (InputPart inputPart : inputParts3) {
					try {
						InputStream inputStream = inputPart.getBody(InputStream.class, null);
						Util.copyFileUsingFileStreams(inputStream, imagePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<MVehiculeDetail> findVehiculesByMissionSE(Integer idMission) {
		return entityManager.createQuery("SELECT v FROM MVehiculeDetail v WHERE v.id.idMission=:p",
				MVehiculeDetail.class).setParameter("p", idMission).getResultList();
	}

	@Override
	public List<MChauffeurDetail> findChauffeursByMissionSE(Integer idMission) {
		return entityManager
				.createQuery("SELECT v FROM MChauffeurDetail v WHERE v.id.idMission=:p",
						MChauffeurDetail.class)
				.setParameter("p", idMission).getResultList();
	}

	@Override
	public boolean loginExists(String login) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from Mission u where u.site=:login";
		TypedQuery<Boolean> query = entityManager.createQuery(jpql, Boolean.class);
		query.setParameter("login", login);
		try {
			exists = query.getSingleResult();
		} catch (NoResultException e) {
			Logger.getLogger(BasicOps.class.getName()).log(
					Level.WARNING, "no user registred with login=" + login);
		}
		return exists;
	}
	
}
