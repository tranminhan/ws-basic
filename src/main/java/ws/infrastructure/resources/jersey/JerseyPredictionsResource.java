package ws.infrastructure.resources.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ws.domain.model.PredictionRepository;

@Path("/predicti")
public class JerseyPredictionsResource {

	PredictionRepository predictionRepository = PredictionRepository
			.instance();

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/")
	public Response getPredictions() {
		return Response.ok(predictionRepository, MediaType.APPLICATION_XML)
				.build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/json")
	public Response getPredictionsAsJson() {
		return Response.ok(predictionRepository, MediaType.APPLICATION_JSON)
				.build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	@Path("/{id: \\d+}")
	public Response getPredictionAsXml(@PathParam("id") int id) {
		if (predictionRepository.find(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(predictionRepository.find(id),
				MediaType.APPLICATION_XML).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/json/{id: \\d+}")
	public Response getPredictionAsJson(@PathParam("id") int id) {
		if (predictionRepository.find(id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(predictionRepository.find(id),
				MediaType.APPLICATION_JSON).build();
	}
}
