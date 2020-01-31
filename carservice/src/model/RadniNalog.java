package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RadniNalog {

	private static int brojacID = 0;

	protected Klijent kl;
	protected Vozilo vzl;
	protected RadniTermin rt;

	public int radniNalogId;
	protected int radniTerminId;
	public Date datumRadnogNaloga;
	protected String registracija;
	protected String saobDozvola;
	protected String markaModel;
	protected int godiste;
	protected String tipMotora;
	protected int kilometraza;
	protected String nalogodavac;
	protected String primedbe;
	protected String otklonjeno;
	protected String ugradjeno;
	protected String proizvodjac;
	protected String uzetoU;
	protected String pakovanje;
	protected int kolicina;
	protected int ukupno;

	/**** KONSTRUKTORI ****/

	public RadniNalog(Vozilo vzl, Klijent kl, RadniTermin rt, int radniNalogId, int radniTerminId,
			Date datumRadnogNaloga, String registracija, String saobDozvola, String markaModel, int godiste,
			String tipMotora, int kilometraza, String nalogodavac, String primedbe, String otklonjeno, String ugradjeno,
			String proizvodjac, String pakovanje, String uzetoU, int kolicina, int ukupno) {
		if (radniNalogId == 0) {
			brojacID++;
			radniNalogId = brojacID;
		}
		this.radniNalogId = radniNalogId;
		this.radniTerminId = radniTerminId;
		this.datumRadnogNaloga = datumRadnogNaloga;
		this.registracija = registracija;
		this.saobDozvola = saobDozvola;
		this.markaModel = markaModel;
		this.godiste = godiste;
		this.tipMotora = tipMotora;
		this.kilometraza = kilometraza;
		this.nalogodavac = nalogodavac;
		this.primedbe = primedbe;
		this.otklonjeno = otklonjeno;
		this.ugradjeno = ugradjeno;
		this.proizvodjac = proizvodjac;
		this.uzetoU = uzetoU;
		this.pakovanje = pakovanje;
		this.kolicina = kolicina;
		this.ukupno = ukupno;
	}

	@Override
	public String toString() {
		String ispis = "\n ***************************************\n" + radniNalogId + ". \n | Radni termin: "
				+ radniTerminId + ". \n | Radni nalog datum: " + datumRadnogNaloga + "\n | Registracija: "
				+ registracija + "\n | Saob. dozvola: " + saobDozvola + "\n | marka/model: " + markaModel
				+ "\n | Godište: " + godiste + "\n | Tip motora: " + tipMotora + "\n | Kilometraža: " + kilometraza
				+ "\n | Nalogodavac: " + nalogodavac + "\n | Primedbe: " + primedbe + "\n | Otklonjeno: " + otklonjeno
				+ "\n | Ugrađeno: " + ugradjeno + "\n | Proizvođač: " + proizvodjac + "\n | Uzeto u: " + uzetoU
				+ "\n | Pakovanje: " + pakovanje + "\n | Količina: " + kolicina + "\n | Ukupno: " + ukupno
				+ "\n ***************************************";
		return ispis;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RadniNalog other = (RadniNalog) obj;
		if (rt == null) {
			if (other.rt != null)
				return false;
		} else if (!rt.equals(other.rt))
			return false;
		if (vzl == null) {
			if (other.vzl != null)
				return false;
		} else if (!vzl.equals(other.vzl))
			return false;
		if (kl == null) {
			if (other.kl != null)
				return false;
		} else if (!kl.equals(other.kl))
			return false;
		return true;
	}

	/*** Metode ***/

	// metoda koja kreira tekstualnu reprezentaciju Radnog naloga za datoteku

	public String toFileRepresentation() {
		StringBuilder bild = new StringBuilder();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		String date = formatter.format(datumRadnogNaloga);
		bild.append(radniNalogId + "," + radniTerminId + "," + date + "," + registracija + "," + saobDozvola + ","
				+ markaModel + "," + godiste + "," + tipMotora + "," + kilometraza + "," + nalogodavac + "," + primedbe
				+ "," + otklonjeno + "," + ugradjeno + "," + proizvodjac + "," + uzetoU + "," + pakovanje + ","
				+ kolicina + "," + ukupno + "\n");
		return bild.toString();
	}

	public Klijent getKl() {
		return kl;
	}

	public void setKl(Klijent kl) {
		this.kl = kl;
	}

	public Vozilo getVzl() {
		return vzl;
	}

	public void setVzl(Vozilo vzl) {
		this.vzl = vzl;
	}

	public RadniTermin getRt() {
		return rt;
	}

	public void setRt(RadniTermin rt) {
		this.rt = rt;
	}

	public int getRadniNalogId() {
		return radniNalogId;
	}

	public void setRadniNalogId(int radniNalogId) {
		this.radniNalogId = radniNalogId;
	}

	public int getRadniTerminId() {
		return radniTerminId;
	}

	public void setRadniTerminId(int radniTerminId) {
		this.radniTerminId = radniTerminId;
	}

	public Date getDatumRadnogNaloga() {
		return datumRadnogNaloga;
	}

	public void setDatumRadnogNaloga(Date datumRadnogNaloga) {
		this.datumRadnogNaloga = datumRadnogNaloga;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getSaobDozvola() {
		return saobDozvola;
	}

	public void setSaobDozvola(String saobDozvola) {
		this.saobDozvola = saobDozvola;
	}

	public String getMarkaModel() {
		return markaModel;
	}

	public void setMarkaModel(String markaModel) {
		this.markaModel = markaModel;
	}

	public int getGodiste() {
		return godiste;
	}

	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}

	public String getTipMotora() {
		return tipMotora;
	}

	public void setTipMotora(String tipMotora) {
		this.tipMotora = tipMotora;
	}

	public int getKilometraza() {
		return kilometraza;
	}

	public void setKilometraza(int kilometraza) {
		this.kilometraza = kilometraza;
	}

	public String getNalogodavac() {
		return nalogodavac;
	}

	public void setNalogodavac(String nalogodavac) {
		this.nalogodavac = nalogodavac;
	}

	public String getPrimedbe() {
		return primedbe;
	}

	public void setPrimedbe(String primedbe) {
		this.primedbe = primedbe;
	}

	public String getOtklonjeno() {
		return otklonjeno;
	}

	public void setOtklonjeno(String otklonjeno) {
		this.otklonjeno = otklonjeno;
	}

	public String getUgradjeno() {
		return ugradjeno;
	}

	public void setUgradjeno(String ugradjeno) {
		this.ugradjeno = ugradjeno;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public String getUzetoU() {
		return uzetoU;
	}

	public void setUzetoU(String uzetoU) {
		this.uzetoU = uzetoU;
	}

	public String getPakovanje() {
		return pakovanje;
	}

	public void setPakovanje(String pakovanje) {
		this.pakovanje = pakovanje;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public int getUkupno() {
		return ukupno;
	}

	public void setUkupno(int ukupno) {
		this.ukupno = ukupno;
	}

}
