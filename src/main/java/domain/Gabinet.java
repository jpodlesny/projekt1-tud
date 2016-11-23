package domain;

public class Gabinet {
	
	private int id;
	private String numer;
	private String pietro;
	private String lekarz;
	private int badanieFK;
	
	public Gabinet(){
		
	}
	
	public Gabinet(String numer, String pietro, String lekarz, int badanieFK){
		super();
		this.numer=numer;
		this.pietro=pietro;
		this.lekarz=lekarz;
		this.badanieFK=badanieFK;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumer() {
		return numer;
	}
	public void setNumer(String numer) {
		this.numer = numer;
	}
	public String getPietro() {
		return pietro;
	}
	public void setPietro(String pietro) {
		this.pietro = pietro;
	}
	public String getLekarz(){
		return lekarz;
	}
	public void setLekarz(String lekarz){
		this.lekarz=lekarz;
	}
	public int getBadanieFK() {
		return badanieFK;
	}
	public void setBadanieFK(int badanieFK) {
		this.badanieFK = badanieFK;
	}

	
}
