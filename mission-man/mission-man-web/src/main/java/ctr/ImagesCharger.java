package ctr;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.UploadedFile;

import persistence.Mission;
import services.BasicOpsLocal;
import utilities.DirectoryInitilizer;

@ManagedBean
@ViewScoped
public class ImagesCharger {
	private UploadedFile uploadedFile;
	private Mission mission = new Mission();
	private String site = "";
	private Boolean showMissionSelected = false;
	@EJB
	private BasicOpsLocal basicOpsLocal;

	public void create() {
		String shortImagePath = UUID.randomUUID().toString() + ".jpg";

		if (uploadedFile != null && uploadedFile.getContents() != null) {
			try {

				byte[] contents = uploadedFile.getContents();
				FileOutputStream fos = new FileOutputStream(DirectoryInitilizer.IMAGE_DIR + "/" + shortImagePath);
				fos.write(contents);
				fos.close();

			} catch (Exception e) {
			}

		}
		if (showMissionSelected) {
			basicOpsLocal.addImage(shortImagePath, mission);
		} else {
			List<String> listImagesNames = new ArrayList<>();
			listImagesNames.add(shortImagePath);
			mission.setDateCreation(new Date());
			mission.setListImagesNames(listImagesNames);
			basicOpsLocal.creerNewMission(mission);
		}

	}

	public void doShowMissionSelected() {
		showMissionSelected = true;
	}

	public void doNotShowMissionSelected() {
		showMissionSelected = false;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Boolean getShowMissionSelected() {
		return showMissionSelected;
	}

	public void setShowMissionSelected(Boolean showMissionSelected) {
		this.showMissionSelected = showMissionSelected;
	}

}
