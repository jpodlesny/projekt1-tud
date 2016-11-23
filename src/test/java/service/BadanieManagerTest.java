package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import domain.Badanie;
import domain.Gabinet;
import service.BadanieManager;

public class BadanieManagerTest {
	
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
	
	
	
	
	@Test
	public void checkConnection(){
		assertNotNull(BadanieManager.getConnection());
	}
	
	
	
	@Test
	public void checkAdding(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		BadanieManager.clearBadania();
		assertEquals(1,BadanieManager.addBadanie(badanie));

		List<Badanie> Badania = BadanieManager.getAllBadania();
		Badanie BadanieRetrieved = Badania.get(0);

		assertEquals(BAD_NAZWA_1, BadanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_1, BadanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_1, BadanieRetrieved.getKoszt());
				
	}
	
	
	
	@Test
	public void checkEditing(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie_edit = new Badanie(BAD_NAZWA_2, BAD_OPIS_2, BAD_KOSZT_2);

		
		BadanieManager.clearBadania();

		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,BadanieManager.editBadanie(badanie_edit));
		
		List<Badanie> Badania = BadanieManager.getAllBadania();
		Badanie BadanieRetrieved = Badania.get(0);

		assertEquals(BAD_NAZWA_2, BadanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_2, BadanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_2, BadanieRetrieved.getKoszt());
				
	}
	
	
	@Test
	public void checkDeleteOne(){
		
		Badanie badanie1 = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2, BAD_OPIS_2, BAD_KOSZT_2);
			
		BadanieManager.clearBadania();
		
		assertEquals(1,BadanieManager.addBadanie(badanie1));
		assertEquals(1,BadanieManager.addBadanie(badanie2));
		
		List<Badanie> Badania = BadanieManager.getAllBadania();
		
		long usuwaneID = Badania.get(0).getId();	
		int usun = BadanieManager.deleteBadanie(usuwaneID);
		assertEquals(1, usun);

		Badania = BadanieManager.getAllBadania();
		
		Badanie BadanieRetrieved = Badania.get(0);
		assertEquals(BAD_NAZWA_2,BadanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_2,BadanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_2,BadanieRetrieved.getKoszt());	
	}
	
	

	@Test
	public void checkAllDelete(){
		
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
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2, GAB_PIETRO_2, GAB_LEKARZ_2, badanie2Id);
		assertEquals(1,GabinetManager.addGabinet(gabinet1));
		assertEquals(1,GabinetManager.addGabinet(gabinet2));
		
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
		assertEquals(badanie2Id, gabinetRetrieved.getBadanieFK());
		
		int delete = BadanieManager.deleteBadanie(badanie1Id);
		assertEquals(1, delete);
		
		badania = BadanieManager.getAllBadania();
		
		Badanie badanieRetrieved = badania.get(0);		
		assertEquals(BAD_NAZWA_2, badanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_2, badanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_2, badanieRetrieved.getKoszt());
		
		
		gabinety = GabinetManager.getAllGabinety();
		gabinetRetrieved = gabinety.get(0);
		assertEquals(GAB_NUMER_2, gabinetRetrieved.getNumer());
		assertEquals(GAB_PIETRO_2, gabinetRetrieved.getPietro());
		assertEquals(GAB_LEKARZ_2, gabinetRetrieved.getLekarz());
		assertEquals(badanie2Id, gabinetRetrieved.getBadanieFK());
		
	}
	
	
	@Test
	public void checkGetAllBadania(){
		
		Badanie badanie1 = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2, BAD_OPIS_2, BAD_KOSZT_2);
		
		BadanieManager.clearBadania();
		assertEquals(1,BadanieManager.addBadanie(badanie1));
		assertEquals(1,BadanieManager.addBadanie(badanie2));
		
		List<Badanie> badania = BadanieManager.getAllBadania();
		
		Badanie badanieRetrieved = badania.get(0);
		assertEquals(BAD_NAZWA_1, badanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_1, badanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_1, badanieRetrieved.getKoszt());
				
		badanieRetrieved = badania.get(1);
		assertEquals(BAD_NAZWA_2, badanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_2, badanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_2, badanieRetrieved.getKoszt());
		
	}
	
	
	@Test
	public void checkGetOne(){
		
		Badanie badanie1 = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_2, BAD_OPIS_2, BAD_KOSZT_2);
		
		BadanieManager.clearBadania();
		assertEquals(1,BadanieManager.addBadanie(badanie1));
		assertEquals(1,BadanieManager.addBadanie(badanie2));
		
		List<Badanie> badania = BadanieManager.getAllBadania();		
		badania = BadanieManager.getAllBadania();
		
		Badanie badanieRetrieved = badania.get(1);
		assertEquals(BAD_NAZWA_2, badanieRetrieved.getNazwa());
		assertEquals(BAD_OPIS_2, badanieRetrieved.getOpis());
		assertEquals(BAD_KOSZT_2, badanieRetrieved.getKoszt());
	}
	
	
	
	@Test
	public void checkBadanieNotExisting(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		BadanieManager.clearBadania();
		assertEquals(1,BadanieManager.addBadanie(badanie));
		
		Badanie getBadanie = BadanieManager.getBadanie(19);

		assertEquals(null, getBadanie.getNazwa());
		assertEquals(null, getBadanie.getOpis());
		assertEquals(null, getBadanie.getKoszt());
				
	}
	
	
	
}