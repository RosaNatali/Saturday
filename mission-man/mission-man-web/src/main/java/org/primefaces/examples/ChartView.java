package org.primefaces.examples;
 
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import persistence.EtatMission;
import services.BasicOpsLocal;
 
 
@ManagedBean
public class ChartView implements Serializable {
 
    private PieChartModel pieModel1;
 
    @PostConstruct
    public void init() {
        createPieModels();
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
 
 
    private void createPieModels() {
        createPieModel1();
    }
    
    @EJB
	private BasicOpsLocal basicOpsLocal;
     
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        
        
        
        pieModel1.set("Nouvelle", basicOpsLocal.findNbMissionsByEtat(EtatMission.NEW));
        pieModel1.set("En Cours", basicOpsLocal.findNbMissionsByEtat(EtatMission.ENCOURS));
        pieModel1.set("Terminee", basicOpsLocal.findNbMissionsByEtat(EtatMission.TERMINEE));
        pieModel1.set("Attente", basicOpsLocal.findNbMissionsByEtat(EtatMission.ATTENTE));
         
        pieModel1.setTitle("Diagramme des missions par état");
        pieModel1.setLegendPosition("w");
    }
 
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}