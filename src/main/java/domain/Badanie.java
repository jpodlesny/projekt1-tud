package domain;

public class Badanie {
	
	private int id;
	private String nazwa;
	private String opis;
	private String koszt;
	
	public Badanie(){
		
	}
	
	public Badanie(String nazwa, String opis, String koszt){
		super();
		this.nazwa=nazwa;
		this.opis=opis;
		this.koszt=koszt;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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

}
