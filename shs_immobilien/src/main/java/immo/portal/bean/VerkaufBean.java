package immo.portal.bean;

import java.io.Serializable;
import java.util.Date;


public class VerkaufBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String haustyp;
	private String bautyp;
	private String titel;
	private Integer baujahr;
	private Integer wohnflaeche;
	private Integer grundstuecksflaeche;
	private String standort;
	private Integer startgebot;
	private String beschreibung;
	private String dateiname;
	private byte[] bilder;

	public VerkaufBean() {
	}

	public VerkaufBean(Long id, String haustyp, String bautyp, String titel, Integer baujahr, Integer wohnflaeche,
			Integer grundstuecksflaeche, String standort, Integer startgebot, String beschreibung, String dateiname,
			byte[] bilder) {
		super();
		this.id = id;
		this.haustyp = haustyp;
		this.bautyp = bautyp;
		this.titel = titel;
		this.baujahr = baujahr;
		this.wohnflaeche = wohnflaeche;
		this.grundstuecksflaeche = grundstuecksflaeche;
		this.standort = standort;
		this.startgebot = startgebot;
		this.beschreibung = beschreibung;
		this.dateiname = dateiname;
		this.bilder = bilder;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHaustyp() {
		return haustyp;
	}

	public void setHaustyp(String haustyp) {
		this.haustyp = haustyp;
	}

	public String getBautyp() {
		return bautyp;
	}

	public void setBautyp(String bautyp) {
		this.bautyp = bautyp;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Integer getBaujahr() {
		return baujahr;
	}

	public void setBaujahr(Integer baujahr) {
		this.baujahr = baujahr;
	}

	public Integer getWohnflaeche() {
		return wohnflaeche;
	}

	public void setWohnflaeche(Integer wohnflaeche) {
		this.wohnflaeche = wohnflaeche;
	}

	public Integer getGrundstuecksflaeche() {
		return grundstuecksflaeche;
	}

	public void setGrundstuecksflaeche(Integer grundstuecksflaeche) {
		this.grundstuecksflaeche = grundstuecksflaeche;
	}

	public String getStandort() {
		return standort;
	}

	public void setStandort(String standort) {
		this.standort = standort;
	}

	public Integer getStartgebot() {
		return startgebot;
	}

	public void setStartgebot(Integer startgebot) {
		this.startgebot = startgebot;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getDateiname() {
		return dateiname;
	}

	public void setDateiname(String dateiname) {
		this.dateiname = dateiname;
	}

	public byte[] getBilder() {
		return bilder;
	}

	public void setBilder(byte[] bilder) {
		this.bilder = bilder;
	}

}
