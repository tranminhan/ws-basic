package ws.domain.model;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "predictionCollection")
public class PredictionRepository {

	ConcurrentMap<Integer, Prediction> predictions;
	AtomicInteger nextIdentity;

	public PredictionRepository() {
		predictions = new ConcurrentHashMap<Integer, Prediction>();
		nextIdentity = new AtomicInteger();
	}

	static PredictionRepository instance;

	public static PredictionRepository instance() {
		if (instance == null) {
			PredictionRepository repository = new PredictionRepository();
			repository.add(new Prediction(repository.nextIdentity
					.incrementAndGet(), "Robin Hood", "Get them all"));
			repository.add(new Prediction(repository.nextIdentity
					.incrementAndGet(), "Mr Bean", "ahh uhmm"));
			repository.add(new Prediction(repository.nextIdentity
					.incrementAndGet(), "Mourinho", "go go go"));
			instance = repository;
		}
		
		return instance;
	}

	public void add(Prediction prediction) {
		predictions.put(prediction.getId(), prediction);
	}

	@XmlElement(name = "predictionElement")
	public Collection<Prediction> getPredictions() {
		return Collections.unmodifiableCollection(predictions.values());
	}

	public Prediction find(int id) {
		return predictions.get(new Integer(id));
	}

	public Prediction addPrediction(String who, String what) {
		int id = nextIdentity.incrementAndGet();
		Prediction prediction = new Prediction(id, who, what);
		add(prediction);

		return prediction;
	}
}
