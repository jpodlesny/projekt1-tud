package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import domain.Badanie;
import domain.Gabinet;
import service.GabinetManager; 

public class GabinetManagerTest {

	BadanieManager BadanieManager = new BadanieManager();
	GabinetManager GabinetManager = new GabinetManager();
	
	
	private final static String BAD_NAZWA_1 = "Morfologia";
	private final static String BAD_OPIS_1 = "Podstawowe, diagnostyczne badanie krwi";
	private final static String BAD_KOSZT_1 = "50";
	
	private final static String BAD_NAZWA_2 = "Mycoplazma pneumoniae IgM";
	private final static String BAD_OPIS_2 = "Badanie na obecność bakterii";
	private final static String BAD_KOSZT_2 = "35";
	 
	
	
	private final static String GAB_NUMER_1 = "22";
	private final static String GAB_PIETRO_1 = "parter";
	private final static String GAB_LEKARZ_1 = "Kowalski";
	
	private final static String GAB_NUMER_2 = "8";
	private final static String GAB_PIETRO_2 = "pierwsze";
	private final static String GAB_LEKARZ_2 = "Nowak";
	
	private final static String GAB_NUMER_3 = "99";
	private final static String GAB_PIETRO_3 = "drugie";
	private final static String GAB_LEKARZ_3 = "Ciemny";
	
	private final static String GAB_NUMER_4 = "35";
	private final static String GAB_PIETRO_4 = "dwunaste";
	private final static String GAB_LEKARZ_4 = "Jasny";

	
	
	@Test
	public void checkConnection(){
		assertNotNull(GabinetManager.getConnection());
	}
	
	
	
	@Test
	public void checkAdding(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1,BAD_KOSZT_1);
		
		GabinetManager.clearGabinety();
		BadanieManager.clearBadania();

		
		assertEquals(1,BadanieManager.addBadanie(badanie));

		List<Badanie> badania = BadanieManager.getAllBadania();
		
		int badanieId = badania.get(0).getId();
		
		Gabinet gabinet = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, badanieId);
		
		assertEquals(1,GabinetManager.addGabinet(gabinet));

		List<Gabinet> gabinety = GabinetManager.getAllGabinety();
		Gabinet gabinetRetrieved = gabinety.get(0);
		
		assertEquals(GAB_NUMER_1, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_1, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_1, gabinetRetrieved.getLekarz());
		assertEquals(badanieId, gabinetRetrieved.getBadanieFK());
		
	}
	
	
	@Test
	public void checkGetGabinet(){
		
		Badanie badanie1 = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2, BAD_OPIS_2, BAD_KOSZT_2);
		
		GabinetManager.clearGabinety();
		BadanieManager.clearBadania();

		assertEquals(1,BadanieManager.addBadanie(badanie1));
		assertEquals(1,BadanieManager.addBadanie(badanie2));

		List<Badanie> badania = BadanieManager.getAllBadania();
		int badanie1Id = badania.get(0).getId();
		int badanie2Id = badania.get(1).getId();
		
		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, badanie1Id);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2, GAB_PIETRO_2, GAB_LEKARZ_2, badanie1Id);
		Gabinet gabinet3 = new Gabinet(GAB_NUMER_3, GAB_PIETRO_3, GAB_LEKARZ_3, badanie2Id);
		Gabinet gabinet4 = new Gabinet(GAB_NUMER_4, GAB_PIETRO_4, GAB_LEKARZ_4, badanie2Id);
		
		assertEquals(1,GabinetManager.addGabinet(gabinet1));
		assertEquals(1,GabinetManager.addGabinet(gabinet2));
		assertEquals(1,GabinetManager.addGabinet(gabinet3));
		assertEquals(1,GabinetManager.addGabinet(gabinet4));

		List<Gabinet> gabinety = GabinetManager.getAllGabinety();
		gabinety = GabinetManager.getAllGabinety();
				
		Gabinet gabinetRetrieved = gabinety.get(2);
		assertEquals(GAB_NUMER_3, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_3, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_3, gabinetRetrieved.getLekarz());
		assertEquals(badanie2Id, gabinetRetrieved.getBadanieFK());
		
	}
	
	
	
	@Test
	public void checkGetGabinetNotExisting(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		GabinetManager.clearGabinety();
		BadanieManager.clearBadania();

		
		assertEquals(1,BadanieManager.addBadanie(badanie));

		List<Badanie> badania = BadanieManager.getAllBadania();
		int badanieId = badania.get(0).getId();

		Gabinet gabinet = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, badanieId);
		assertEquals(1,GabinetManager.addGabinet(gabinet));
		
		Gabinet getGabinet = GabinetManager.getGabinet(29);
		
		assertEquals(null, getGabinet.getNumer());
		assertEquals(null, getGabinet.getPietro());
		assertEquals(null, getGabinet.getLekarz());
		assertEquals(0, getGabinet.getBadanieFK());
				
	}
		
	
	
	@Test
	public void checkDeleteOne(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		GabinetManager.clearGabinety();
		BadanieManager.clearBadania();

		
		assertEquals(1,BadanieManager.addBadanie(badanie));
		
		List<Badanie> badania = BadanieManager.getAllBadania();
		int badanieId = badania.get(0).getId();
		
		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, badanieId);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2, GAB_PIETRO_2, GAB_LEKARZ_2, badanieId);
		
		assertEquals(1,GabinetManager.addGabinet(gabinet1));
		assertEquals(1,GabinetManager.addGabinet(gabinet2));
		
		List<Gabinet> gabinety = GabinetManager.getAllGabinety();
		int deletedId = gabinety.get(0).getId();
		int delete = GabinetManager.deleteGabinet(deletedId);
		assertEquals(1, delete);
		
		gabinety = GabinetManager.getAllGabinety();
		
		Gabinet characterRetrieved = gabinety.get(0);		
		
		assertEquals(GAB_NUMER_2, characterRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, characterRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, characterRetrieved.getLekarz());
		assertEquals(badanieId, characterRetrieved.getBadanieFK());
		
	}
	
	
	@Test
	public void checkGetAll(){
		

		Badanie badanie1 = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2, BAD_OPIS_2, BAD_KOSZT_2);
		
		GabinetManager.clearGabinety();
		BadanieManager.clearBadania();

		
		assertEquals(1,BadanieManager.addBadanie(badanie1));
		assertEquals(1,BadanieManager.addBadanie(badanie2));
		
		List<Badanie> badania = BadanieManager.getAllBadania();
		int badanie1Id = badania.get(0).getId();
		int badanie2Id = badania.get(1).getId();
		
		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, badanie1Id);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2, GAB_PIETRO_2, GAB_LEKARZ_2, badanie1Id);
		Gabinet gabinet3 = new Gabinet(GAB_NUMER_3, GAB_PIETRO_3, GAB_LEKARZ_3, badanie2Id);
		Gabinet gabinet4 = new Gabinet(GAB_NUMER_4, GAB_PIETRO_4, GAB_LEKARZ_4, badanie2Id);
		
		assertEquals(1,GabinetManager.addGabinet(gabinet1));
		assertEquals(1,GabinetManager.addGabinet(gabinet2));
		assertEquals(1,GabinetManager.addGabinet(gabinet3));
		assertEquals(1,GabinetManager.addGabinet(gabinet4));
		
		List<Gabinet> gabinety = GabinetManager.getAllGabinety();
		
		Gabinet gabinetRetrieved = gabinety.get(0);
		assertEquals(GAB_NUMER_1, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_1, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_1, gabinetRetrieved.getLekarz());
		assertEquals(badanie1Id, gabinetRetrieved.getBadanieFK());
		
		gabinetRetrieved = gabinety.get(1);
		assertEquals(GAB_NUMER_2, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved.getLekarz());
		assertEquals(badanie1Id, gabinetRetrieved.getBadanieFK());
		
		gabinetRetrieved = gabinety.get(2);
		assertEquals(GAB_NUMER_3, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_3, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_3, gabinetRetrieved.getLekarz());
		assertEquals(badanie2Id, gabinetRetrieved.getBadanieFK());
		
		gabinetRetrieved = gabinety.get(3);
		assertEquals(GAB_NUMER_4, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_4, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_4, gabinetRetrieved.getLekarz());
		assertEquals(badanie2Id, gabinetRetrieved.getBadanieFK());
		
	}
	
	
	@Test
	public void getGabinetyPoBadaniach(){
		
		Badanie badanie1 = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2, BAD_OPIS_2, BAD_KOSZT_2);
		
		GabinetManager.clearGabinety();
		BadanieManager.clearBadania();
		
		assertEquals(1,BadanieManager.addBadanie(badanie1));
		assertEquals(1,BadanieManager.addBadanie(badanie2));
		
		List<Badanie> badania = BadanieManager.getAllBadania();
		int badanie1Id = badania.get(0).getId();
		int badanie2Id = badania.get(1).getId();
		
		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, badanie1Id);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2, GAB_PIETRO_2, GAB_LEKARZ_2, badanie1Id);
		Gabinet gabinet3 = new Gabinet(GAB_NUMER_3, GAB_PIETRO_3, GAB_LEKARZ_3, badanie2Id);
		Gabinet gabinet4 = new Gabinet(GAB_NUMER_4, GAB_PIETRO_4, GAB_LEKARZ_4, badanie2Id);
		
		assertEquals(1,GabinetManager.addGabinet(gabinet1));
		assertEquals(1,GabinetManager.addGabinet(gabinet2));
		assertEquals(1,GabinetManager.addGabinet(gabinet3));
		assertEquals(1,GabinetManager.addGabinet(gabinet4));
		
		List<Gabinet> gabinety = GabinetManager.getAllGabinety();
		
		Gabinet gabinetRetrieved = gabinety.get(0);
		assertEquals(GAB_NUMER_1, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_1, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_1, gabinetRetrieved.getLekarz());
		assertEquals(badanie1Id, gabinetRetrieved.getBadanieFK());
		
		gabinetRetrieved = gabinety.get(2);
		assertEquals(GAB_NUMER_3, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_3, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_3, gabinetRetrieved.getLekarz());
		assertEquals(badanie2Id, gabinetRetrieved.getBadanieFK());
		
		gabinetRetrieved = gabinety.get(3);
		assertEquals(GAB_NUMER_4, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_4, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_4, gabinetRetrieved.getLekarz());
		assertEquals(badanie2Id, gabinetRetrieved.getBadanieFK());
		
		gabinetRetrieved = gabinety.get(1);
		assertEquals(GAB_NUMER_2, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved.getLekarz());
		assertEquals(badanie1Id, gabinetRetrieved.getBadanieFK());
		
		List<Gabinet> gabinetyPoBadaniu = GabinetManager.getAllGabinetyPoBadaniu(badanie1Id);
		
		Gabinet gabinetRetrieved2 = gabinetyPoBadaniu.get(0);
		assertEquals(GAB_NUMER_1, gabinetRetrieved2.getNumer());
		assertEquals(GAB_PIETRO_1, gabinetRetrieved2.getPietro());
		assertEquals(GAB_LEKARZ_1, gabinetRetrieved2.getLekarz());
		assertEquals(badanie1Id, gabinetRetrieved2.getBadanieFK());
		
		gabinetRetrieved2 = gabinetyPoBadaniu.get(1);
		assertEquals(GAB_NUMER_2, gabinetRetrieved2.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved2.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved2.getLekarz());
		assertEquals(badanie1Id, gabinetRetrieved2.getBadanieFK());
		
	}
	
	
	
}
