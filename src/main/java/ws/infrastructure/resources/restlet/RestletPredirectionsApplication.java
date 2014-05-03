package ws.infrastructure.resources.restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestletPredirectionsApplication extends Application {
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
		router.attach("/", PlainResource.class);
		router.attach("/xml", XmlAllResource.class);
		router.attach("/xml/{id}", XmlOneResource.class);
		router.attach("/json", JsonAllResource.class);
		router.attach("/create", CreateResource.class);
		router.attach("/update", UpdateResource.class);
		return router;
	}
}
