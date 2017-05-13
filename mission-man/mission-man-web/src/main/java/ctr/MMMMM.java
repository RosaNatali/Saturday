package ctr;

import java.io.FileOutputStream;
import java.util.Date;
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
public class MMMMM {
	private UploadedFile uploadedFile;
	private Mission mission = new Mission();
	@EJB
	private BasicOpsLocal basicOpsLocal;

	public void create() {
		String shortImagePath = UUID.randomUUID().toString() + ".jpg";

		if (uploadedFile != null && uploadedFile.getContents() != null) {
			try {

				byte[] contents = uploadedFile.getContents();
				FileOutputStream fos = new FileOutputStream(DirectoryInitilizer.IMAGE_DIR + "/" + shortImagePath);
				System.out.println("FileOutputStream");
				fos.write(contents);
				fos.close();

			} catch (Exception e) {
			}
			mission.setDateAffectation(new Date());
			mission.setDateCreation(new Date());
			mission.setSite("ghazela");
			mission.getListImagesNames().add(shortImagePath);
			basicOpsLocal.creerMission(mission);

		}

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

}
