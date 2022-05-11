package immo.portal.bean;

import java.io.Serializable;

public class htyp_bean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String typ;
	
	public htyp_bean(String _typ) {
		super();
		typ = _typ;
	}
	
	
	public String getTyp() {
		return typ;
	}
}
