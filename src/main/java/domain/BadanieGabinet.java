package domain;

public class BadanieGabinet {
	
	private long id;
	private String nazwa;
	private String opis;
	private String koszt;
	private long idGabinet;
	private String numerGabinet;
	private String pietroGabinet;
	private String lekarzGabinet;
	
	public BadanieGabinet(){
		
	}
	
	public BadanieGabinet(String nazwa, String opis, String koszt, long idGabinet, String numerGabinet, 
			String pietroGabinet, String lekarzGabinet){
		super();
		this.nazwa=nazwa;
		this.opis=opis;
		this.koszt=koszt;
		this.idGabinet=idGabinet;
		this.numerGabinet=numerGabinet;
		this.pietroGabinet=pietroGabinet;
		this.lekarzGabinet=lekarzGabinet;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getKoszt() {
		return koszt;
	}
	public void setKoszt(String koszt) {
		this.koszt = koszt;
	}
	public long getIdGabinet() {
		return idGabinet;
	}
	public void setIdGabinet(long idGabinet) {
		this.idGabinet = idGabinet;
	}
	public String getNumerGabinet() {
		return numerGabinet;
	}
	public void setNumerGabinet(String numerGabinet) {
		this.numerGabinet = numerGabinet;
	}
	public String getPietroGabinet() {
		return pietroGabinet;
	}
	public void setPietroGabinet(String pietroGabinet) {
		this.pietroGabinet = pietroGabinet;
	}
	public String getLekarzGabinet() {
		return lekarzGabinet;
	}
	public void setLekarzGabinet(String lekarzGabinet) {
		this.lekarzGabinet = lekarzGabinet;
	}

	
	
}
