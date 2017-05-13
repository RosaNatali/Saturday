package utilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import persistence.Agent;
import persistence.Carburant;
import persistence.Chauffeur;
import persistence.EtatMission;
import persistence.Marque;
import persistence.Mission;
import persistence.Modele;
import persistence.Produit;
import persistence.Transmission;
import persistence.Voiture;
import services.BasicOpsLocal;
import services.ChauffeurServiceLocal;
import services.ProduitServicesLocal;
import services.VehiculeServicesLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Session Bean implementation class Util
 */
@Singleton
@LocalBean
@Startup
public class Util {
	@EJB
	private VehiculeServicesLocal vehiculeServicesLocal;
	@EJB
	private ChauffeurServiceLocal chauffeurServiceLocal;
	@EJB
	private ProduitServicesLocal produitServicesLocal;

	@EJB
	private BasicOpsLocal basicOpsRemote;

	/**
	 * Default constructor.
	 */
	public Util() {
	}
	public static void copyFileUsingFileStreams(InputStream input, String filename) throws IOException {
		OutputStream output = null;
		try {
			output = new FileOutputStream(filename);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}
	@PostConstruct
	public void initDb() throws ParseException
	{
		Voiture voiture = new Voiture("111TU8040", Carburant.DIESEL, Marque.AUDI, Modele.A3, Transmission.MANUEL);
		Voiture voiture2 = new Voiture("198TU1234", Carburant.DIESEL, Marque.FIAT, Modele.FIATducato, Transmission.MANUEL);
		Voiture voiture3 = new Voiture("118TU15487", Carburant.ESSENCE, Marque.HONDA, Modele.F500, Transmission.AUTOMATIQUE);

		Chauffeur chauffeur = new Chauffeur(1, "ali", "ali", "r", "tunis", "98", "98", "98", "rib", "a@a");
//		Chauffeur chauffeur = new Chauffeur( "ali", "ali", "r", "tunis", "98", "98", "98", "rib", "a@a", "zz", "aa");
		Chauffeur chauffeur2 = new Chauffeur(2, "med", "med", "r", "tunis", "98", "98", "98", "rib", "a@a");
		Chauffeur chauffeur3 = new Chauffeur( "salah", "salah", "r", "tunis", "98", "98", "98", "rib", "a@a", "s", "s");

		Produit produit = new Produit("aze145", "chocolat");
		Produit produit2 = new Produit("mft225", "kosksi");

		Agent agent = new Agent("daly", "admin", "admin", "responsable parc auto");

//		Mission mission = new Mission(new Date(), "Gabes");
//		Mission mission2 = new Mission(new Date(), "sfax");
//		Mission mission3 = new Mission(new Date(), "sousse");
//		Mission mission4 = new Mission(new Date(), "tunis");
//		Mission mission5 = new Mission(new Date(), "beja");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = sdf.parse("5/5/2016");
		Date date2 = sdf.parse("7/5/2016");
		Date date3 = sdf.parse("9/5/2016");
		Date date4 = sdf.parse("11/5/2016");
		Date date5 = sdf.parse("13/5/2016");
		Date date6 = sdf.parse("15/5/2016");
//		Date date7 = sdf.parse("17/5/2016");
//		Date date8 = sdf.parse("19/5/2016");
//		Date date9 = sdf.parse("21/5/2016");
//		Date date10 = sdf.parse("23/5/2016");
		
		Mission mission = new Mission(1, date2, date1, date3, "Siliana", EtatMission.NEW);
		Mission mission1 = new Mission(2, date5, date4, date6, "Makther", EtatMission.NEW);
		Mission mission2 = new Mission(3, date5, date4, date6, "Monastir", EtatMission.NEW);
		Mission mission3 = new Mission(4, date5, date4, date6, "Kairouan", EtatMission.NEW);
		Mission mission4 = new Mission(5, date5, date4, date6, "Bizerte", EtatMission.NEW);
		Mission mission5 = new Mission(6, date5, date4, date6, "Sousse", EtatMission.NEW);
		Mission mission6 = new Mission(7, date5, date4, date6, "Kef", EtatMission.NEW);
		Mission mission7 = new Mission(8, date5, date4, date6, "Ariana", EtatMission.ENCOURS);
		Mission mission8 = new Mission(9, date5, date4, date6, "Raoued", EtatMission.ENCOURS);
		Mission mission9 = new Mission(10, date5, date4, date6, "Gazelle", EtatMission.ENCOURS);
		Mission mission10 = new Mission(11, date5, date4, date6, "Ennaser", EtatMission.ENCOURS);
		Mission mission11 = new Mission(12, date5, date4, date6, "Lac", EtatMission.ENCOURS);
		Mission mission12 = new Mission(13, date5, date4, date6, "Marsa", EtatMission.TERMINEE);
		Mission mission13 = new Mission(14, date5, date4, date6, "Megrine", EtatMission.TERMINEE);
		Mission mission14 = new Mission(15, date5, date4, date6, "Jammel", EtatMission.TERMINEE);
		Mission mission15 = new Mission(16, date5, date4, date6, "RasJebel", EtatMission.TERMINEE);
		Mission mission16 = new Mission(17, date5, date4, date6, "Rades", EtatMission.TERMINEE);
		Mission mission17 = new Mission(18, date5, date4, date6, "Nabeul", EtatMission.TERMINEE);
		Mission mission18 = new Mission(19, date5, date4, date6, "Mahdiya", EtatMission.TERMINEE);
		Mission mission19 = new Mission(20, date5, date4, date6, "Jerba", EtatMission.ATTENTE);
		Mission mission20 = new Mission(21, date5, date4, date6, "Sfax", EtatMission.ATTENTE);
		
		
		
		
//		basicOpsRemote.affecterChauffeurMission(chauffeur, mission);
//		basicOpsRemote.affecterChauffeurMission(chauffeur2, mission);
//
//		basicOpsRemote.affecterVehiculeMission(voiture, mission);
//		basicOpsRemote.affecterVehiculeMission(voiture2, mission);
//		
		chauffeurServiceLocal.saveOrUpdateT(chauffeur);
		chauffeurServiceLocal.saveOrUpdateT(chauffeur2);
		chauffeurServiceLocal.saveOrUpdateT(chauffeur3);

		produitServicesLocal.saveOrUpdateT(produit);
		produitServicesLocal.saveOrUpdateT(produit2);

		vehiculeServicesLocal.saveOrUpdateT(voiture);
		vehiculeServicesLocal.saveOrUpdateT(voiture2);
		vehiculeServicesLocal.saveOrUpdateT(voiture3);

		basicOpsRemote.saveOrUpdateUser(agent);

		basicOpsRemote.creerNewMission(mission);
		basicOpsRemote.creerNewMission(mission1);
		basicOpsRemote.creerNewMission(mission2);
		basicOpsRemote.creerNewMission(mission3);
		basicOpsRemote.creerNewMission(mission4);
		basicOpsRemote.creerNewMission(mission5);
		basicOpsRemote.creerNewMission(mission6);
		basicOpsRemote.creerNewMission(mission7);
		basicOpsRemote.creerNewMission(mission8);
		basicOpsRemote.creerNewMission(mission9);
		basicOpsRemote.creerNewMission(mission10);
		basicOpsRemote.creerNewMission(mission11);
		basicOpsRemote.creerNewMission(mission12);
		basicOpsRemote.creerNewMission(mission13);
		basicOpsRemote.creerNewMission(mission14);
		basicOpsRemote.creerNewMission(mission15);
		basicOpsRemote.creerNewMission(mission16);
		basicOpsRemote.creerNewMission(mission17);
		basicOpsRemote.creerNewMission(mission18);
		basicOpsRemote.creerNewMission(mission19);
		basicOpsRemote.creerNewMission(mission20);
		
		basicOpsRemote.affecterChauffeurMission(chauffeur, mission);
		basicOpsRemote.affecterChauffeurMission(chauffeur2, mission);
		
		basicOpsRemote.affecterVehiculeMission(voiture, mission);
		
//		basicOpsRemote.creerNewMission(mission);
//		basicOpsRemote.creerNewMission(mission2);
//		basicOpsRemote.creerNewMission(mission3);
//		basicOpsRemote.creerNewMission(mission4);
//		basicOpsRemote.creerNewMission(mission5);
	}
}
