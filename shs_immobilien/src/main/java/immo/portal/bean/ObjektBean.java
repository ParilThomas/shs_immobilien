/** @author Thomas Paril */

package immo.portal.bean;

import java.io.Serializable;
import java.sql.Date;

public class ObjektBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String haustyp;
	private String bautyp;
	private String titel;
	private Integer baujahr;
	private Integer wohnflaeche;
	private Integer grundstuecksflaeche;
	private String standort;
	private Date datum;
	private Integer startgebot;
	private String beschreibung;
	private byte[] bilder;
	private Integer besitzer;
	private Integer hoechstbietender;
	
	public ObjektBean() {	
	}
	
	public ObjektBean(Long id, String haustyp, String bautyp, String titel, int baujahr, int wohnflaeche, int grundstuecksflaeche, String standort, Date datum,
						int startgebot, String beschreibung, byte[] bilder, Integer besitzer, Integer hoechstbietender) {
		super();
		this.id = id;
		this.haustyp = haustyp;
		this.bautyp = bautyp;
		this.titel = titel;
		this.baujahr = baujahr;
		this.wohnflaeche = wohnflaeche;
		this.grundstuecksflaeche = grundstuecksflaeche;
		this.standort = standort;
		this.datum = datum;
		this.startgebot = startgebot;
		this.beschreibung = beschreibung;
		this.bilder = bilder;
		this.besitzer = besitzer;
		this.hoechstbietender = hoechstbietender;
	}

	public Integer getHoechstbietender() {
		return hoechstbietender;
	}

	public void setHoechstbietender(Integer hoechstbietender) {
		this.hoechstbietender = hoechstbietender;
	}

	public Integer getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(Integer besitzer) {
		this.besitzer = besitzer;
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
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
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
	public byte[] getBilder() {
		return bilder;
	}
	public void setBilder(byte[] bilder) {
		this.bilder = bilder;
	}
	
}
