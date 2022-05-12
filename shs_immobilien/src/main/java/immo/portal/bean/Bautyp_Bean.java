package immo.portal.bean;

import java.io.Serializable;

public class Bautyp_Bean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String typ;
	
	public Bautyp_Bean() {
		
	}
	
	public Bautyp_Bean(int id, String typ) {
		super();
		this.id = id;
		this.typ = typ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	
	
}
