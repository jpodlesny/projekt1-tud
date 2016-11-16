package domain;

public class Badanie {
	
	private long id;
	private String nazwa;
	private String opis;
	private int koszt;
	
	public Badanie(String nazwa, String opis, int koszt){
		super();
		this.nazwa=nazwa;
		this.opis=opis;
		this.koszt=koszt;
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
	public int getKoszt() {
		return koszt;
	}
	public void setKoszt(int koszt) {
		this.koszt = koszt;
	}

}