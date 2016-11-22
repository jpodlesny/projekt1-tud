package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import domain.Badanie;
import service.BadanieManager;

public class BadanieManagerTest {
	
	BadanieManager BadanieManager = new BadanieManager();
	GabinetManager GabinetManager = new GabinetManager();
	
	
	
	private final static String BAD_NAZWA_1 = "Morfologia";
	private final static String BAD_OPIS_1 = "Podstawowe, diagnostyczne badanie krwi";
	private final static String BAD_KOSZT_1 = "50";
	
	private final static String BAD_NAZWA_1_EDIT = "Mycoplazma pneumoniae IgM";
	private final static String BAD_OPIS_1_EDIT = "Badanie na obecność bakterii";
	private final static String BAD_KOSZT_1_EDIT = "35";
	
	
	
	
	@Test
	public void checkConnection(){
		assertNotNull(BadanieManager.getConnection());
	}
	
	
	
	@Test
	public void checkAdding(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		BadanieManager.deleteAllBadania();
		assertEquals(1,BadanieManager.addBadanie(badanie));

		List<Badanie> Badania = BadanieManager.getAllBadania();
		Badanie BadanieOdebrane = Badania.get(0);

		assertEquals(BAD_NAZWA_1, BadanieOdebrane.getNazwa());
		assertEquals(BAD_OPIS_1, BadanieOdebrane.getOpis());
		assertEquals(BAD_KOSZT_1, BadanieOdebrane.getKoszt());
				
	}
	
	
	
	@Test
	public void checkBadanie(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		BadanieManager.deleteAllBadania();
		assertEquals(1,BadanieManager.addBadanie(badanie));
		
		Badanie getBadanie = BadanieManager.getBadanie(0);
		
		assertEquals(BAD_NAZWA_1, getBadanie.getNazwa());
		assertEquals(BAD_OPIS_1, getBadanie.getOpis());
		assertEquals(BAD_KOSZT_1, getBadanie.getKoszt());
				
	}
	
	
	
	@Test
	public void checkBadanieNotExisting(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		
		BadanieManager.deleteAllBadania();
		assertEquals(1,BadanieManager.addBadanie(badanie));
		
		Badanie getBadanie = BadanieManager.getBadanie(1);

		assertEquals(null, getBadanie.getNazwa());
		assertEquals(null, getBadanie.getOpis());
		assertEquals(null, getBadanie.getKoszt());
				
	}
	
	
	
	@Test
	public void checkEditing(){
		
		Badanie badanie = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie_edit = new Badanie(BAD_NAZWA_1_EDIT, BAD_OPIS_1_EDIT, BAD_KOSZT_1_EDIT);

		
		BadanieManager.deleteAllBadania();

		assertEquals(1,BadanieManager.addBadanie(badanie));
		assertEquals(1,BadanieManager.editBadanie(badanie_edit));
		
		List<Badanie> Badania = BadanieManager.getAllBadania();
		Badanie BadanieOdebrane = Badania.get(0);

		assertEquals(BAD_NAZWA_1_EDIT, BadanieOdebrane.getNazwa());
		assertEquals(BAD_OPIS_1_EDIT, BadanieOdebrane.getOpis());
		assertEquals(BAD_KOSZT_1_EDIT, BadanieOdebrane.getKoszt());
				
	}
	
	
	@Test
	public void checkDeleteOne(){
		
		Badanie badanie1 = new Badanie(BAD_NAZWA_1, BAD_OPIS_1, BAD_KOSZT_1);
		Badanie badanie2 = new Badanie(BAD_NAZWA_1_EDIT, BAD_OPIS_1_EDIT, BAD_KOSZT_1_EDIT);
			
		BadanieManager.deleteAllBadania();
		
		assertEquals(1,BadanieManager.addBadanie(badanie1));
		assertEquals(1,BadanieManager.addBadanie(badanie2));
		
		List<Badanie> Badania = BadanieManager.getAllBadania();
		long usuwanyNr = Badania.get(0).getId();
		
		int usuniecie = BadanieManager.deleteBadanie(usuwanyNr);
		assertEquals(1, usuniecie);

		Badania = BadanieManager.getAllBadania();
		
		Badanie BadanieOdebrane = Badania.get(0);
		assertEquals(BAD_NAZWA_1_EDIT,BadanieOdebrane.getNazwa());
		assertEquals(BAD_OPIS_1_EDIT,BadanieOdebrane.getOpis());
		assertEquals(BAD_KOSZT_1_EDIT,BadanieOdebrane.getKoszt());	
	}

	
	
	
	
}