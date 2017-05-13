package ctr;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import services.BasicOpsLocal;

@ManagedBean
@SessionScoped
public class imageLoaderFromDB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private BasicOpsLocal basicOpsLocal;

	public StreamedContent getImageFromDB(String id) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {

			// ByteArrayOutputStream bos = new ByteArrayOutputStream();

			// Reading image from database assuming that product image (bytes)
			// of product id I1 which is already stored in the database.

			// byte[] image = null;
			// image = basicOpsLocal.findMissionById(id).getImage();

			// return new DefaultStreamedContent(new
			// ByteArrayInputStream(image), "image/png");
			return null;
		}
	}

}
