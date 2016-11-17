package domain;

public class Gabinet {
	
	private long id;
	private String numer;
	private String pietro;
	private String lekarz;
	private int badanie;
	
	public Gabinet(){
		
	}
	
	public Gabinet(String numer, String pietro, String lekarz, int badanie){
		super();
		this.numer=numer;
		this.pietro=pietro;
		this.lekarz=lekarz;
		this.badanie=badanie;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public int getBadanie() {
		return badanie;
	}
	public void setBadanie(int badanie) {
		this.badanie = badanie;
	}

	
}
