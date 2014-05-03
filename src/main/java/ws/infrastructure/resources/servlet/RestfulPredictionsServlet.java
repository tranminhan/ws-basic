package ws.infrastructure.resources.servlet;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.XMLConstants;
import javax.xml.ws.http.HTTPException;

import org.codehaus.jackson.map.ObjectMapper;

import ws.domain.model.Prediction;
import ws.domain.model.PredictionRepository;

public class RestfulPredictionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idString = req.getParameter("id");
		Integer id = (idString == null) ? null : Integer.valueOf(idString
				.trim());

		boolean json = false;
		String acceptHeader = req.getHeader("accept");
		if (acceptHeader != null && acceptHeader.contains("json")) {
			json = true;
		}

		if (id == null) {
			Collection<Prediction> predictions = (Collection<Prediction>) PredictionRepository
					.instance().getPredictions();
			String content = "";
			if (json) {
				content = new ObjectMapper().writeValueAsString(predictions);
			} else {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				XMLEncoder xmlEncoder = new XMLEncoder(baos);
				xmlEncoder.writeObject(predictions.toArray());
				xmlEncoder.close();

				content = baos.toString();
			}
			resp.getOutputStream().println(content);
		} else {
			Prediction prediction = PredictionRepository.instance().find(id);
			String content = "";

			if (json) {
				content = new ObjectMapper().writeValueAsString(prediction);
			} else {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				XMLEncoder xmlEncoder = new XMLEncoder(baos);
				xmlEncoder.writeObject(prediction);
				xmlEncoder.close();

				content = baos.toString();
			}

			resp.getOutputStream().println(content);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String who = req.getParameter("who");
		String what = req.getParameter("what");

		if (who == null) {
			throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
		}
		if (what == null) {
			throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
		}

		Prediction prediction = PredictionRepository.instance().addPrediction(who, what);
		resp.getOutputStream().print("Prediction id:" + prediction.getId() + " is created.\n");
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		throw new HTTPException(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}
}
