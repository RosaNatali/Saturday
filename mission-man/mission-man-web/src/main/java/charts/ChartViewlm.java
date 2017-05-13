package charts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import persistence.EtatMission;
import persistence.Mission;
import services.BasicOpsLocal;

 
/**
 * @author Saria ESSID
 *
 */
@ManagedBean
public class ChartViewlm implements Serializable {

	private List<Mission> missions = new ArrayList<>();
	private PieChartModel pieModel1;
	private PieChartModel pieModel2;
	@EJB
	private BasicOpsLocal basicOpsLocal;

	@PostConstruct
	public void init() {
		createPieModels();
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	private void createPieModels() {
		createPieModel1();
		createPieModel2();
	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();

		pieModel1.set("NOUVELLE", basicOpsLocal.findNbMissionsByEtat(EtatMission.NEW));
		pieModel1.set("ENCOURS", basicOpsLocal.findNbMissionsByEtat(EtatMission.ENCOURS));
		pieModel1.set("TERMINEE", basicOpsLocal.findNbMissionsByEtat(EtatMission.TERMINEE));
		pieModel1.set("ATTENTE", basicOpsLocal.findNbMissionsByEtat(EtatMission.ATTENTE));

		pieModel1.setTitle("Missions");
		pieModel1.setLegendPosition("w");
		pieModel1.setFill(false);
		pieModel1.setShowDataLabels(true);
		pieModel1.setDiameter(150);
		
		pieModel1.setSeriesColors("58BA27,FFCC33,F74A4A,8CC6D7");
	}

	private void createPieModel2() {
		pieModel2 = new PieChartModel();

		pieModel2.set("Brand 1", 540);
		pieModel2.set("Brand 2", 325);
		pieModel2.set("Brand 3", 702);
		pieModel2.set("Brand 4", 421);

		pieModel2.setTitle("Custom Pie");
		pieModel2.setLegendPosition("e");
		pieModel2.setFill(false);
		pieModel2.setShowDataLabels(true);
		pieModel2.setDiameter(150);
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}

}