package immo.portal.bean;

import java.io.Serializable;

public class Haustyp_Bean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String typ;
	
	public Haustyp_Bean() {
		
	}
	
	public Haustyp_Bean(int id, String typ) {
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
