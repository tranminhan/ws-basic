package ws.domain.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "prediction")
public class Prediction implements Serializable, Comparable<Prediction> {

	public Prediction(int id, String who, String what) {
		super();
		this.id = id;
		this.who = who;
		this.what = what;
	}

	public Prediction() {
		super();
	}

	private static final long serialVersionUID = 1L;

	String who;
	String what;
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public int compareTo(Prediction o) {
		return this.id = o.id;
	}
}
