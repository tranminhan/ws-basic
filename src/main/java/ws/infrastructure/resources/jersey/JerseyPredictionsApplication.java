package ws.infrastructure.resources.jersey;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/jersey")
public class JerseyPredictionsApplication extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<Class<?>>();
		set.add(JerseyPredictionsResource.class);
		return set;
	}
}
