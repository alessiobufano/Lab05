package it.polito.tdp.anagrammi.DAO;

import java.sql.*;
//import java.util.*;


public class ModelDAO {
	
	/*
	public List<String> dizionario() {
		
		String sql = "SELECT * FROM parola";

		List<String> dizionario = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String parola = rs.getString("nome");
				dizionario.add(parola);
			}

			conn.close();
			
			return dizionario;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
	}
	*/
	
	public boolean anagrammaCorretto(String anagramma) {
		
		String sql = "SELECT * FROM parola WHERE nome=?";
		boolean trovato = false;
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, anagramma);

			ResultSet rs = st.executeQuery();
			
			if(rs.next()) 
				trovato = true;
			
			conn.close();
			
		} catch(SQLException e) {
			throw new RuntimeException("Errore DB", e);
		}
		
		return trovato;
	}

}
