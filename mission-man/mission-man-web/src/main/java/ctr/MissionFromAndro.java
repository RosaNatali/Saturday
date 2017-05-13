package ctr;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import services.BasicOpsLocal;

@Stateless
@Path("/daly")
public class MissionFromAndro {
	@EJB
	private BasicOpsLocal basicOpsLocal;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	Response createWithImage(MultipartFormDataInput input) throws IOException {

		return Response.status(200).entity("IMAGE added successfully").build();
	}

}
