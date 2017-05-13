package ctr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@ManagedBean
@ViewScoped
public class UploadPage {
	private Part uploadedFile;
	private String fileContent;

	@ManagedProperty(value = "#{missionBean}")
	private MissionBean missionBean;

	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		// if (file.getSize() > 1024) {
		// msgs.add(new FacesMessage("file too big"));
		// }
		// if (!"text/plain".equals(file.getContentType())) {
		// msgs.add(new FacesMessage("not a text file"));
		// }
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}

	public String uploadFile() throws IOException {
		InputStream input = uploadedFile.getInputStream();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] image = new byte[10240];
		for (int length = 0; (length = input.read(image)) > 0;)
			output.write(image, 0, length);

		missionBean.addMissionFromPhoto(image);
		try {
			fileContent = new Scanner(uploadedFile.getInputStream()).useDelimiter("\\A").next();

		} catch (IOException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error uploading file", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		return "/home?faces-redirect=true";
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFileContent() {
		return fileContent;
	}

	public MissionBean getMissionBean() {
		return missionBean;
	}

	public void setMissionBean(MissionBean missionBean) {
		this.missionBean = missionBean;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

}