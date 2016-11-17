package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.BadanieGabinet;

public class BadanieGabinetManager {
	
	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private PreparedStatement getBadanieGabinetStmt;
	private PreparedStatement getAllBadanieGabinetStmt;
	
	private Statement statement;
	
	
	public BadanieGabinetManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			getAllBadanieGabinetStmt = connection
					.prepareStatement("SELECT Badanie.id, Badanie.nazwa, opis, koszt, Gabinet.id, Gabinet.numer, Gabinet.pietro, Gabinet.lekarz FROM Badanie JOIN Gabinet ON (Badanie.id = Gabinet.badanie)");
			getBadanieGabinetStmt = connection
					.prepareStatement("SELECT Badanie.id, Badanie.nazwa, opis, koszt, Gabinet.id, Gabinet.numer, Gabinet.pietro, Gabinet.lekarz, Gabinet.badanie FROM Badanie JOIN Gabinet ON (Badanie.id = Gabinet.badanie) where Badanie.id=?");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	Connection getConnection() {
		return connection;
	}
	
	
	
	public List<BadanieGabinet> getBadanieGabinet(long id) {
		List<BadanieGabinet> Badania = new ArrayList<BadanieGabinet>();

		try {
			getBadanieGabinetStmt.setLong(1, id);
			ResultSet rs = getBadanieGabinetStmt.executeQuery();
			
			while (rs.next()) {
				BadanieGabinet b = new BadanieGabinet();
				b.setId(rs.getInt("Badanie.id"));
				b.setNazwa(rs.getString("Badanie.nazwa"));
				b.setOpis(rs.getString("opis"));
				b.setKoszt(rs.getString("koszt"));
				b.setIdGabinet(rs.getInt("Gabinet.id"));
				b.setNumerGabinet(rs.getString("Gabinet.numer"));
				b.setPietroGabinet(rs.getString("Gabinet.pietro"));
				b.setLekarzGabinet(rs.getString("Gabinet.lekarz"));
				Badania.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return Badania;
	}
	
	

	public List<BadanieGabinet> getAllBadanieGabinet() {
		List<BadanieGabinet> Badania = new ArrayList<BadanieGabinet>();

		try {
			ResultSet rs = getAllBadanieGabinetStmt.executeQuery();

			while (rs.next()) {
				BadanieGabinet b = new BadanieGabinet();
				b.setId(rs.getInt("Badanie.id"));
				b.setNazwa(rs.getString("Badanie.nazwa"));
				b.setOpis(rs.getString("opis"));
				b.setKoszt(rs.getString("koszt"));
				b.setIdGabinet(rs.getInt("Gabinet.id"));
				b.setNumerGabinet(rs.getString("Gabinet.numer"));
				b.setPietroGabinet(rs.getString("Gabinet.pietro"));
				b.setLekarzGabinet(rs.getString("Gabinet.lekarz"));
				Badania.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Badania;
	}
	
	
	
}
