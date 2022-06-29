/** @author Simon Schrödl */

package immo.portal.bean;

import java.io.Serializable;

public class BenutzerBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String vorname;
	private String nachname;
	private String anschrift;
	private String plz;
	private String wohnort;
	private String telefon;
	private String email;
	private String passwort1;
	
	public BenutzerBean(Integer id, String vorname, String nachname, String anschrift, String plz, String wohnort,
			String telefon, String email, String passwort1) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.anschrift = anschrift;
		this.plz = plz;
		this.wohnort = wohnort;
		this.telefon = telefon;
		this.email = email;
		this.passwort1 = passwort1;
	}

	public BenutzerBean() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getAnschrift() {
		return anschrift;
	}

	public void setAnschrift(String anschrift) {
		this.anschrift = anschrift;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getWohnort() {
		return wohnort;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswort1() {
		return passwort1;
	}

	public void setPasswort1(String passwort1) {
		this.passwort1 = passwort1;
	}

}
