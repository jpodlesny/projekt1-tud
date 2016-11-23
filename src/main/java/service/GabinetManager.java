package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Gabinet;


public class GabinetManager {

	private Connection connection;

	//private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableGabinet = "CREATE TABLE Gabinet(id INTEGER GENERATED BY DEFAULT AS IDENTITY," 
			+ "numer varchar(5), pietro varchar(10), lekarz varchar(30), badanieFK INTEGER, FOREIGN KEY (badanieFK) "
			+ "REFERENCES PUBLIC.BADANIE(id) ON DELETE CASCADE ON UPDATE CASCADE)";
	
	
	private PreparedStatement addGabinetStmt;
	private PreparedStatement editGabinetStmt;
	private PreparedStatement deleteGabinetStmt;
	private PreparedStatement deleteAllGabinetyStmt;
	private PreparedStatement getAllGabinetyStmt;
	private PreparedStatement getGabinetStmt;
	private PreparedStatement getAllGabinetyPoBadaniuStmt;

	private Statement statement;
	
	
	public GabinetManager() {
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:testdb;ifexists=false", "SA", "");
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Gabinet".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
			{
				statement.executeUpdate(createTableGabinet);
			}
			

			addGabinetStmt = connection
					.prepareStatement("INSERT INTO Gabinet (numer, pietro, lekarz, badanieFK) VALUES (?, ?, ?, ?)");
			editGabinetStmt = connection
					.prepareStatement("UPDATE Gabinet SET numer=?, pietro=?, lekarz=?, badanieFK=? WHERE id=?");
			deleteGabinetStmt = connection
					.prepareStatement("DELETE FROM Gabinet where id=?");
			deleteAllGabinetyStmt = connection
					.prepareStatement("DELETE FROM Gabinet");
			getAllGabinetyStmt = connection
					.prepareStatement("SELECT id, numer, pietro, lekarz, badanieFK FROM Gabinet");
			getGabinetStmt = connection
					.prepareStatement("SELECT id, numer, pietro, lekarz, badanieFK FROM Gabinet where id=?");
			getAllGabinetyPoBadaniuStmt = connection
					.prepareStatement("SELECT Gabinet.id, Gabinet.numer, Gabinet.pietro, Gabinet.lekarz, Gabinet.badanieFK,"
							+ "Badanie.id, Badanie.nazwa, Badanie.opis, Badanie.koszt FROM Badanie JOIN Gabinet ON"
							+ "(Badanie.id = Gabinet.badanieFK) WHERE Badanie.id=?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	public Connection getConnection() {
		return connection;
	}
	
	
	
	public int addGabinet(Gabinet Gabinet) {
		int count = 0;
		
		try {
			addGabinetStmt.setString(1, Gabinet.getNumer());
			addGabinetStmt.setString(2, Gabinet.getPietro());
			addGabinetStmt.setString(3, Gabinet.getLekarz());
			addGabinetStmt.setInt(4, Gabinet.getBadanieFK());
			
			count = addGabinetStmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	public int editGabinet(int id) {
		int count = 0;
		
		try {
			editGabinetStmt.setInt(1, id);
			
			count = editGabinetStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	public int deleteGabinet(int id) {
		int count = 0;
		try {
			deleteGabinetStmt.setInt(1, id);
			count = deleteGabinetStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	public void clearGabinety() {
		try {
			deleteAllGabinetyStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public List<Gabinet> getAllGabinety() {
		List<Gabinet> Gabinety = new ArrayList<Gabinet>();

		try {
			ResultSet rs = getAllGabinetyStmt.executeQuery();

			while (rs.next()) {
				Gabinet g = new Gabinet();
				g.setId(rs.getInt("id"));
				g.setNumer(rs.getString("numer"));
				g.setPietro(rs.getString("pietro"));
				g.setLekarz(rs.getString("lekarz"));
				g.setBadanieFK(rs.getInt("badanieFK"));
				Gabinety.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Gabinety;
	}
	
	
	
	public Gabinet getGabinet(int id) {
		Gabinet g = new Gabinet();

		try {
			getGabinetStmt.setInt(1, id);
			ResultSet rs = getGabinetStmt.executeQuery();
			while (rs.next()) {
				
				g.setId(rs.getInt("id"));
				g.setNumer(rs.getString("numer"));
				g.setPietro(rs.getString("pietro"));
				g.setLekarz(rs.getString("lekarz"));
				g.setBadanieFK(rs.getInt("badanieFK"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return g;
	}
	
	
	
	public List<Gabinet> getAllGabinetyPoBadaniu(int id){
		List<Gabinet> gabinety = new ArrayList<Gabinet>();
		
		try {
			getAllGabinetyPoBadaniuStmt.setInt(1, id);
			ResultSet rs = getAllGabinetyPoBadaniuStmt.executeQuery();

			while (rs.next()) {
				Gabinet g = new Gabinet();
				g.setId(rs.getInt("Gabinet.id"));
				g.setNumer(rs.getString("Gabinet.numer"));
				g.setPietro(rs.getString("Gabinet.pietro"));
				g.setLekarz(rs.getString("Gabinet.lekarz"));
				g.setBadanieFK(rs.getInt("Gabinet.badanieFK"));
				gabinety.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gabinety;
		
	}
	
}
