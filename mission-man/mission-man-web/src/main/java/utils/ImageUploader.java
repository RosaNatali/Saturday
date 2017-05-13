package utils;

import java.io.FileOutputStream;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ctr.MissionBean;
import utilities.DirectoryInitilizer;

@ManagedBean
@ViewScoped
public class ImageUploader {
	private UploadedFile uploadedFile;
	@ManagedProperty(value = "#{missionBean}")
	private MissionBean missionBean;

	
	
	public void handleFileUpload(FileUploadEvent event) {
		uploadedFile = event.getFile();
		String shortImagePath = UUID.randomUUID().toString() + ".jpg";

		if (uploadedFile != null && uploadedFile.getContents() != null) {
			try {

				byte[] contents = uploadedFile.getContents();
				FileOutputStream fos = new FileOutputStream(DirectoryInitilizer.IMAGE_DIR + "/" + shortImagePath);
				fos.write(contents);
				fos.close();

				missionBean.getImagesToBeLoaded().add(shortImagePath);
				FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);

			} catch (Exception e) {
				FacesMessage message = new FacesMessage("pb", event.getFile().getFileName() + " is not uploaded.");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}

	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public MissionBean getMissionBean() {
		return missionBean;
	}

	public void setMissionBean(MissionBean missionBean) {
		this.missionBean = missionBean;
	}

}
