package immo.portal.bean;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletOutputStream;

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
	
	public ObjektBean() {
		
	}
	
	public ObjektBean(Long id, String haustyp, String bautyp, String titel, int baujahr, int wohnflaeche, int grundstuecksflaeche, String standort, Date datum,
						int startgebot, String beschreibung, byte[] bilder) {
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
	
//	public BufferedImage getBild() throws SQLException, IOException {
//		Blob b = bilder.getBilder();
//		InputStream is = b.getBinaryStream();
//	    return ImageIO.read(is);
//	}
	
	
}
