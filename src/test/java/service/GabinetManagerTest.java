package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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
	
	
	private final static String GAB_NUMER_1 = "22";
	private final static String GAB_PIETRO_1 = "parter";
	private final static String GAB_LEKARZ_1 = "Kowalski";
	private final static int GAB_BADANIE_1 = 0;
	
	private final static String GAB_NUMER_2 = "8";
	private final static String GAB_PIETRO_2 = "I";
	private final static String GAB_LEKARZ_2 = "Nowak";
	private final static int GAB_BADANIE_2 = 0;
	

	private final static String GAB_NUMER_1_EDIT = "9999";
	private final static String GAB_PIETRO_1_EDIT = "XXX";
	private final static String GAB_LEKARZ_1_EDIT = "Ciemny";
	private final static int GAB_BADANIE_1_EDIT = 0;
	
	
	
	@Test
	public void checkConnection(){
		assertNotNull(GabinetManager.getConnection());
	}
	
	
	
	@Test
	public void checkAdding(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Gabinet gabinet = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, GAB_BADANIE_1);
		
		BadanieManager.deleteAllBadania();
		GabinetManager.deleteAllGabinety();
		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,GabinetManager.addGabinet(gabinet));

		List<Gabinet> Gabinety = GabinetManager.getAllGabinety();
		Gabinet GabinetOdebrany = Gabinety.get(0);

		assertEquals(GAB_NUMER_1, GabinetOdebrany.getNumer());
		assertEquals(GAB_PIETRO_1, GabinetOdebrany.getPietro());
		assertEquals(GAB_LEKARZ_1, GabinetOdebrany.getLekarz());
		assertEquals(GAB_BADANIE_1, GabinetOdebrany.getBadanieFK());
				
	}
	
	
	@Test
	public void checkGetGabinet(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Gabinet gabinet = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, GAB_BADANIE_1);
		
		BadanieManager.deleteAllBadania();
		GabinetManager.deleteAllGabinety();
		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,GabinetManager.addGabinet(gabinet));

		Gabinet gabinetSql = GabinetManager.getGabinet(0);

		assertEquals(GAB_NUMER_1, gabinetSql.getNumer());
		assertEquals(GAB_PIETRO_1, gabinetSql.getPietro());
		assertEquals(GAB_LEKARZ_1, gabinetSql.getLekarz());
		assertEquals(GAB_BADANIE_1, gabinetSql.getBadanieFK());
				
	}
	
	
	
	@Test
	public void checkGetGabinetNotExisting(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Gabinet gabinet = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, GAB_BADANIE_1);
		
		BadanieManager.deleteAllBadania();
		GabinetManager.deleteAllGabinety();
		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,GabinetManager.addGabinet(gabinet));

		Gabinet gabinetSql =GabinetManager.getGabinet(2);

		assertEquals(null, gabinetSql.getNumer());
		assertEquals(null, gabinetSql.getPietro());
		assertEquals(null, gabinetSql.getLekarz());
		assertEquals(0, gabinetSql.getBadanieFK());
				
	}
	
	
	
	@Test
	public void checkEditing(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Gabinet gabinet = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, GAB_BADANIE_1);
		Gabinet gabinet_edit = new Gabinet(GAB_NUMER_1_EDIT, GAB_PIETRO_1_EDIT, GAB_LEKARZ_1_EDIT, GAB_BADANIE_1_EDIT);
		
		BadanieManager.deleteAllBadania();
		GabinetManager.deleteAllGabinety();
		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,GabinetManager.addGabinet(gabinet));
		
		assertEquals(1,GabinetManager.editGabinet(gabinet_edit));
		
		List<Gabinet> Gabinety = GabinetManager.getAllGabinety();
		Gabinet GabinetOdebrany = Gabinety.get(0);
				
		assertEquals(GAB_NUMER_1_EDIT, GabinetOdebrany.getNumer());
		assertEquals(GAB_PIETRO_1_EDIT, GabinetOdebrany.getPietro());
		assertEquals(GAB_LEKARZ_1_EDIT, GabinetOdebrany.getLekarz());
		assertEquals(GAB_BADANIE_1_EDIT, GabinetOdebrany.getBadanieFK());
		
	}
	
	
	
	@Test
	public void checkEditingNotExistingBadanie(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Gabinet gabinet = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, GAB_BADANIE_1);
		Gabinet gabinet_edit = new Gabinet(GAB_NUMER_1_EDIT, GAB_PIETRO_1_EDIT, GAB_LEKARZ_1_EDIT, GAB_BADANIE_1_EDIT);
		
		BadanieManager.deleteAllBadania();
		GabinetManager.deleteAllGabinety();
		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,GabinetManager.addGabinet(gabinet));
		assertEquals(0,GabinetManager.editGabinet(gabinet_edit));
		
		List<Gabinet> Gabinety = GabinetManager.getAllGabinety();
		Gabinet GabinetOdebrany = Gabinety.get(0);
		
		assertEquals(GAB_NUMER_1, GabinetOdebrany.getNumer());
		assertEquals(GAB_PIETRO_1, GabinetOdebrany.getPietro());
		assertEquals(GAB_LEKARZ_1, GabinetOdebrany.getLekarz());
		assertEquals(GAB_BADANIE_1, GabinetOdebrany.getBadanieFK());
				
	}
	
	
	
	@Test
	public void checkDeleteOne(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, GAB_BADANIE_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2, GAB_PIETRO_2, GAB_LEKARZ_2, GAB_BADANIE_2);
		
		BadanieManager.deleteAllBadania();
		GabinetManager.deleteAllGabinety();
		
		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,GabinetManager.addGabinet(gabinet1));
		assertEquals(1,GabinetManager.addGabinet(gabinet2));
		
		List<Gabinet> Gabinety = GabinetManager.getAllGabinety();
		long usuwanyNr = Gabinety.get(1).getId(); // drugi numer z listy
		
		int usuniecie = GabinetManager.deleteGabinet(usuwanyNr);
		assertEquals(1, usuniecie);
		Gabinety = GabinetManager.getAllGabinety();
				
		Gabinet GabinetOdebrany = Gabinety.get(0);
		assertEquals(GAB_NUMER_1, GabinetOdebrany.getNumer());
		assertEquals(GAB_PIETRO_1, GabinetOdebrany.getPietro());
		assertEquals(GAB_LEKARZ_1, GabinetOdebrany.getLekarz());
		assertEquals(GAB_BADANIE_1, GabinetOdebrany.getBadanieFK());
		
	}
	
	
	
	@Test
	public void checkDeleteOneGabinet(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		Gabinet gabinet1 = new Gabinet(GAB_NUMER_1, GAB_PIETRO_1, GAB_LEKARZ_1, GAB_BADANIE_1);
		Gabinet gabinet2 = new Gabinet(GAB_NUMER_2, GAB_PIETRO_2, GAB_LEKARZ_2, GAB_BADANIE_2);
		
		BadanieManager.deleteAllBadania();
		GabinetManager.deleteAllGabinety();
		
		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,GabinetManager.addGabinet(gabinet1));
		assertEquals(1,GabinetManager.addGabinet(gabinet2));
		
		
		List<Badanie> Badania = BadanieManager.getAllBadania();
		long usuwanyNr = Badania.get(0).getId(); // pierwszy numer z listy
		int usuniecie = BadanieManager.deleteBadanie(usuwanyNr);
		
		assertEquals(1, usuniecie);
		List<Gabinet> Gabinety = GabinetManager.getAllGabinety();
		List<Gabinet> pusto = new ArrayList<Gabinet>();
		
		assertEquals(pusto,Gabinety);
			
	}
	
	
}
