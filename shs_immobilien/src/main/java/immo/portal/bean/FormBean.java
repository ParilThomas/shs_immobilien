package immo.portal.bean;

import java.io.Serializable;
// Bean-Test-- TS
public class FormBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String vorname;
	private String nachname;
	private Integer telefon;
	private String email;
	private String anliegen;
	private String filename;
	private byte[] image;

	public FormBean() {
		
	}
	
	public FormBean(String vorname, String nachname, Integer telefon, String email, String anliegen,String filename, byte[] image) {
		super();
		this.vorname=vorname;
		this.nachname= nachname;
		this.telefon= telefon;
		this.email= email;
		this.anliegen= anliegen;
		this.filename= filename;
		this.image=image;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

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

	public Integer getTelefon() {
		return telefon;
	}

	public void setTelefon(Integer telefon) {
		this.telefon = telefon;
	}

	public String getAnliegen() {
		return anliegen;
	}

	public void setAnliegen(String anliegen) {
		this.anliegen = anliegen;
	}

}
