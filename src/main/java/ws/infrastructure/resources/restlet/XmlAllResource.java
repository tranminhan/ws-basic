package ws.infrastructure.resources.restlet;

import java.io.IOException;
import java.util.Collection;

import javax.xml.transform.dom.DOMResult;

import org.restlet.data.MediaType;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ws.domain.model.Prediction;
import ws.domain.model.PredictionRepository;

public class XmlAllResource extends ServerResource {

	public XmlAllResource() {
	}

	@Get
	public Representation toXml() {
		try {
			DomRepresentation dom = new DomRepresentation(MediaType.TEXT_XML);
			dom.setIndenting(true);
			Document document = dom.getDocument();

			Element root = document.createElement("predictions");
			Collection<Prediction> predictions = PredictionRepository
					.instance().getPredictions();
			for (Prediction aPrediction : predictions) {
				Element next = document.createElement("prediction");
				next.appendChild(document.createTextNode(aPrediction.toString()));
				root.appendChild(next);
			}

			document.appendChild(root);
			return dom;

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
