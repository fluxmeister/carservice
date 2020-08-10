package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Klijent;
import model.Vozilo;

public class VoziloDAO {

	public static List<Vozilo> getAll(Connection conn) {
		List<Vozilo> vozila = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT vozilo_id, registracija, tip, marka_model, gorivo, godiste, mot_typ FROM vozila";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				int index = 1;
				int voziloID = rset.getInt(index++);
				String registracija = rset.getString(index++);
				String tip = rset.getString(index++);
				String markaModel = rset.getString(index++);
				String gorivo = rset.getString(index++);
				String godiste = rset.getString(index++);
				String motTyp = rset.getString(index++);
				
				Klijent vlasnik = PosedujeDAO.getVlasnikByVoziloID(conn, voziloID);
				
				Vozilo vozilo = new Vozilo(voziloID, registracija, tip, markaModel, gorivo, godiste, motTyp);
				vlasnik = vozilo.getVlasnik();
				vozila.add(vozilo);
			}
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return vozila;
	}

	public static Vozilo getVoziloByID(Connection conn, int id) {
		Vozilo vozilo = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT vozilo_id, registracija, tip, marka_model, gorivo, godiste, mot_typ FROM vozila WHERE vozilo_id = "
					+ id;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				int voziloID = rset.getInt(1);
				String registracija = rset.getString(2);
				String tip = rset.getString(3);
				String markaModel = rset.getString(4);
				String gorivo = rset.getString(5);
				String godiste = rset.getString(6);
				String motTyp = rset.getString(7);

				vozilo = new Vozilo(voziloID, registracija, tip, markaModel, gorivo, godiste, motTyp);

			}
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rset.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return vozilo;
	}
	

	public static boolean add(Connection conn, Vozilo vzl) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO vozila (registracija, tip, marka_model, gorivo, godiste, mot_typ) values (?, ?, ?, ?, ?, ?)";
			
			/* 0, registracija, tip, markaModel, gorivo, godiste, motTyp */
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, vzl.getRegistracija().toUpperCase());
			pstmt.setString(index++, vzl.getTip());
			pstmt.setString(index++, vzl.getMarkaModel());
			pstmt.setString(index++, vzl.getGorivo());
			pstmt.setString(index++, vzl.getGodiste());
			pstmt.setString(index++, vzl.getMotTyp());
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	public static boolean update(Connection conn, Vozilo vzl) {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE vozila SET registracija = ?, tip = ?, marka_model = ?, gorivo = ?, godiste = ?, mot_typ = ? WHERE vozilo_id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, vzl.getRegistracija());
			pstmt.setString(index++, vzl.getTip());
			pstmt.setString(index++, vzl.getMarkaModel());
			pstmt.setString(index++, vzl.getGorivo());
			pstmt.setString(index++, vzl.getGodiste());
			pstmt.setString(index++, vzl.getMotTyp());
			pstmt.setInt(index++,  vzl.getId());
			
			return pstmt.executeUpdate() == 1;
			
	} catch (SQLException ex) {
		System.out.println("Greška u SQL upitu!");
		ex.printStackTrace();
	} finally {
		try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	}
	return false;
	}
	
	public static boolean delete(Connection conn, int id) {
		Statement stmt = null;
		try {
			String update = "DELETE FROM vozila WHERE vozilo_id = " + id;
			
			stmt = conn.createStatement();
			return stmt.executeUpdate(update) == 1;
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
}
