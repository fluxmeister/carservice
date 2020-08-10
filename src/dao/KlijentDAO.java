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

public class KlijentDAO {

	// CRUD operations on a Klijent

	public static Klijent getKlijentByID(Connection conn, int id) {
		Klijent klijent = null;

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT klijent_id, ime, prezime, prebivaliste, telefon FROM klijenti WHERE klijent_id = "
					+ id;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				int index = 1;
				int klijent_id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String prebivaliste = rset.getString(index++);
				String telefon = rset.getString(index++);

				klijent = new Klijent(id, ime, prezime, prebivaliste, telefon);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
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

		return klijent;
	}
	
	

	public static Klijent getKlijentByTelefon(Connection conn, String telefon) {
			Klijent klijent = null;
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			try {
					String query = "SELECT klijent_id, ime, prezime, prebivaliste, telefon FROM klijenti WHERE telefon = ?";
					
					pstmt = conn.prepareStatement(query);
					int index = 1;
					pstmt.setString(index++, telefon);
					
					rset = pstmt.executeQuery();
					
					if(rset.next()) {
						index = 1;
						int klijent_id = rset.getInt(index++);
						String ime = rset.getString(index++);
						String prezime = rset.getString(index++);
						String prebivaliste = rset.getString(index++);
//						String telefon = rset.getString(index++);
						
						klijent = new Klijent (klijent_id, ime, prezime, prebivaliste, telefon);
					}
					
				} catch (SQLException ex) {
					System.out.println("Greska u SQL upitu!");
					ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}
			return klijent;
		}

	public static List<Klijent> getAll(Connection conn) {
		List<Klijent> klijenti = new ArrayList<>();

		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT klijent_id, ime, prezime, prebivaliste, telefon FROM klijenti";
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String prebivaliste = rset.getString(index++);
				String telefon = rset.getString(index++);
				
//				List<Vozilo> vozilaKojaPoseduje = PosedujeDAO.getVozilaByKlijentID(conn, id);
				
				Klijent klijent = new Klijent(id, ime, prezime, prebivaliste, telefon);
//				klijent.getKlijentImaVozila().addAll(vozilaKojaPoseduje);
				klijenti.add(klijent);
			}
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return klijenti;
		
	}
	
	public static boolean add(Connection conn, Klijent klijent) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO klijenti (klijent_id, ime, prezime, prebivaliste, telefon) VALUES (0, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, klijent.getIme());
			pstmt.setString(index++, klijent.getPrezime());
			pstmt.setString(index++, klijent.getPrebivaliste());
			pstmt.setString(index++, klijent.getTelefon());
			
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
			String update = "DELETE FROM klijenti WHERE klijent_id = " + id;
			
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



	public static boolean update(Connection conn, Klijent kl) {
		PreparedStatement pstmt = null;
		try {
			String query = "UPDATE klijenti SET ime = ?, prezime = ?, prebivaliste = ?, telefon = ? WHERE klijent_id = ?";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, kl.getIme());
			pstmt.setString(index++, kl.getPrezime());
			pstmt.setString(index++, kl.getPrebivaliste());
			pstmt.setString(index++, kl.getTelefon());
			pstmt.setInt(index++, kl.getId());
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	

}




