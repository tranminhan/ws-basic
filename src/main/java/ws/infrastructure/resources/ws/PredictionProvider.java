package ws.infrastructure.resources.ws;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.Collection;

import javax.annotation.Resource;
import javax.management.MXBean;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.BindingType;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import javax.xml.ws.http.HTTPException;

import ws.domain.model.Prediction;
import ws.domain.model.PredictionRepository;

@WebServiceProvider
@ServiceMode(Mode.MESSAGE)
@BindingType(HTTPBinding.HTTP_BINDING)
public class PredictionProvider implements Provider<Source> {

	@Resource
	WebServiceContext wctx;

	public Source invoke(Source request) {
		if (wctx == null) {
			throw new RuntimeException("wctx is null");
		}

		MessageContext messageContext = wctx.getMessageContext();
		String httpVerb = (String) messageContext
				.get(MessageContext.HTTP_REQUEST_METHOD);
		httpVerb = httpVerb.trim().toUpperCase();

		if ("GET".equals(httpVerb)) {
			return doGet(messageContext);
		} else if ("POST".equals(httpVerb)) {
			return doPost(request);
		} else if ("PUT".equals(httpVerb)) {
			return doPut(request);
		}
		throw new HTTPException(405);
	}

	private Source doPut(Source request) {
		// TODO Auto-generated method stub
		return null;
	}

	private Source doPost(Source request) {
		// TODO Auto-generated method stub
		return null;
	}

	private Source doGet(MessageContext messageContext) {
		String queryString = (String) messageContext
				.get(MessageContext.QUERY_STRING);
		if (queryString == null) {
			Collection<Prediction> predictions = (Collection<Prediction>) PredictionRepository
					.instance().getPredictions();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			XMLEncoder xmlEncoder = new XMLEncoder(baos);
			xmlEncoder.writeObject(predictions.toArray());
			xmlEncoder.close();

			String content = baos.toString();
			return new StreamSource(new StringReader(content));
		}
		return null;
	}

}
