package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.RadniTermin;


// CRUD operations on RadniTermin object
public class RadniTerminDAO {
	
	public static RadniTermin getRadniTerminByID(Connection conn, int id) {
		RadniTermin rTermin = null;
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT * FROM radni_termini WHERE termin_id = " + id;
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if (rset.next()) {
				int index = 1;
//				int rtId = rset.getInt(index++);
				String naziv = rset.getString(index++);
				String pocetak =  rset.getString(index++);
				String kraj = rset.getString(index++);
				
				rTermin = new RadniTermin(id, naziv, pocetak, kraj);
			}
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return rTermin;
	}

	public static List<RadniTermin> getAll(Connection conn) {
		List<RadniTermin> radniTermini = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT termin_id, naziv, pocetak, kraj FROM radni_termini";
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				int index = 1;
				int rtId = rset.getInt(index++);
				String naziv = rset.getString(index++);
				String pocetak = rset.getString(index++);
				String kraj = rset.getString(index++);
				
//				Klijent klijentKojiPosedujeVozilo = PosedujeDAO.getVlasnikByVoziloID(conn, rtId);
				
				RadniTermin rTermin = new RadniTermin(rtId, naziv, pocetak, kraj);
//				rTermin.getKlijenti().addAll(klijentKojiPosedujeVozilo);
				radniTermini.add(rTermin);
			}
		} catch (Exception ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return radniTermini;
	}

	public static boolean add(Connection conn, RadniTermin rt) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO `radni_termini` (`naziv`, `pocetak`, `kraj`) VALUES (?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
//			pstmt.setInt(index++, rt.getId());
			pstmt.setString(index++, rt.getNaziv());
			pstmt.setString(index++,  rt.getPocetak());
			pstmt.setString(index++, rt.getKraj());
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	public static boolean update(Connection conn, RadniTermin rTermin) {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE radni_termini SET naziv = ?, pocetak = ?, kraj = ? WHERE termin_id = ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			
			pstmt.setString(index++, rTermin.getNaziv());
			pstmt.setString(index++, rTermin.getPocetak());
			pstmt.setString(index++, rTermin.getKraj());
			pstmt.setInt(index++, rTermin.getId());

			return pstmt.executeUpdate() == 1;
			
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;

	}
	
	public static boolean delete(Connection conn, int id) {
		Statement stmt = null;
		try {
			String delete = "DELETE FROM radni_termini WHERE termin_id = " + id;
			
			stmt = conn.createStatement();
			return stmt.executeUpdate(delete) == 1;
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

}
