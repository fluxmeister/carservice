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

//CRUD operacije nad veznom tabelom poseduje
public class PosedujeDAO {

	public static List<Vozilo> getVozilaByKlijentID(Connection conn, int klijentID) {
		List<Vozilo> vozilaKojaKlijentPoseduje = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			String query = "SELECT vozilo_id FROM poseduje WHERE klijent_id = " + klijentID;
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				int voziloID = rset.getInt(1);
				
				Vozilo vozilo = VoziloDAO.getVoziloByID(conn, voziloID);
				vozilaKojaKlijentPoseduje.add(vozilo);
			}
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		System.out.println("******** lista posedovanja ************");
		return vozilaKojaKlijentPoseduje;
	}

	public static Klijent getVlasnikByVoziloID(Connection conn, int voziloId) {
		Klijent vlasnikVozila = new Klijent();
		
		Statement stmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT klijent_id FROM poseduje WHERE vozilo_id = " + voziloId;
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while (rset.next()) {
				int klijentID = rset.getInt(1);
				
				Klijent klijent = KlijentDAO.getKlijentByID(conn, klijentID);
				vlasnikVozila = klijent;
			}
		} catch (SQLException ex ){
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return vlasnikVozila;
	}
	
	public static boolean add(Connection conn, int klijentID, int voziloID) {
		PreparedStatement pstmt = null;
		try {
			String query = "INSERT INTO poseduje (klijent_id, vozilo_id) VALUES (?, ?)";
			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setInt(index++, klijentID);
			pstmt.setInt(index++, voziloID);
			
			return pstmt.executeUpdate() == 1;
		} catch(SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	//update svih posedovanja jednog klijenta
	public static boolean update(Connection conn, Klijent klijent) {
		try {
			conn.setAutoCommit(false); 	// isključivanje automatske transakcije (pri čemu je svaki upit bio transakcija za sebe) 
			conn.commit();				// početak transakcije
			
			//brisanje prethodnih posedovanja
			boolean uspeh = deletePosedovanjaKlijenta(conn, klijent.getId());
			if( !uspeh ) throw new Exception("Brisanje nije uspelo!");
			
			for( Vozilo vozilo : klijent.getKlijentImaVozila() ) {
				uspeh = add( conn, klijent.getId(), vozilo.getId() );
				if(!uspeh) throw new Exception("Dodavanje nije uspelo!");
			}
			
			conn.commit();  // kraj transakcije
			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			try {conn.rollback();} catch(SQLException ex1) {ex1.printStackTrace();}
		} finally {
			try {conn.setAutoCommit(true);} catch(SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	
	
	
	// update svih posedovanja jednog vozila
	public static boolean update(Connection conn, Vozilo vozilo) {
		try {
			conn.setAutoCommit(false);
			conn.commit();

			// brisanje prethodnih događanja
			boolean uspeh = deletePosedovanjaVozila(conn, vozilo.getId());
			if (!uspeh)
				throw new Exception("Brisanje nije uspelo");

			// dodavanje po jednog posedovanja za vlasnika ovog vozila
			Klijent klijent = vozilo.getVlasnik();
			uspeh = add(conn, klijent.getId(), vozilo.getId());
			if (!uspeh)
				throw new Exception("Dodavanje nije uspelo!");

			conn.commit(); // kraj transakcije

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			try {
				conn.rollback();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}
	
	
	

	private static boolean deletePosedovanjaVozila(Connection conn, int voziloID) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM poseduje WHERE vozilo_id = " + voziloID;
			
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch(SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch(SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	private static boolean deletePosedovanjaKlijenta(Connection conn, int klijentID) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM poseduje WHERE klijent_id = " + klijentID;
			
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			return true;
		} catch(SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch(SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}

	public static boolean delete(Connection conn, int klijentID, int voziloID) {
		Statement stmt = null;
		try {
			String query = "DELETE FROM poseduje WHERE klijent_id = " + klijentID + " AND vozilo_id = " + voziloID;
			stmt = conn.createStatement();
			return stmt.executeUpdate(query) == 1;
		} catch (SQLException ex) {
			System.out.println("Greška u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {stmt.close();} catch(SQLException ex1) {ex1.printStackTrace();}
		}
		return false;
	}
	
	
	
	
	
	
}
