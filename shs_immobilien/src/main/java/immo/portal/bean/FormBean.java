package immo.portal.bean;

import java.io.Serializable;
// Bean-Test-- TS
public class FormBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String vorname;
	private String nachname;
	private String telefon;
	private String email;
	private String anliegen;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAnliegen() {
		return anliegen;
	}

	public void setAnliegen(String anliegen) {
		this.anliegen = anliegen;
	}

}
