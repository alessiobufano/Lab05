package it.polito.tdp.anagrammi.model;

import java.util.*;
import it.polito.tdp.anagrammi.DAO.ModelDAO;

public class Model {
	
	private ModelDAO mDAO;
	//private List<String> dizionario;
	private List<String> anagrammiCorretti;
	private List<String> anagrammiSbagliati;
	
	public Model() {
		this.mDAO = new ModelDAO();
		//this.dizionario = this.dizionario();
		this.anagrammiCorretti = new LinkedList<>();
		this.anagrammiSbagliati = new LinkedList<>();
	}
	
	public List<String> getAnagrammiCorretti() {
		return anagrammiCorretti;
	}

	public List<String> getAnagrammiSbagliati() {
		return anagrammiSbagliati;
	}
	
	/*
	public List<String> getDizionario() {
		return dizionario;
	}

	public List<String> dizionario() {
		
		return this.mDAO.dizionario();
	}
	*/
	
	public boolean anagrammaCorretto(String anagramma) {
		
		return this.mDAO.anagrammaCorretto(anagramma);
	}
	
	public void anagrammi(String parola) {
		
		parola = parola.toLowerCase();
		
		List<Character> disponibili = new LinkedList<>();
		for(int i=0; i<parola.length(); i++)
			disponibili .add(parola.charAt(i));
		
		this.ricercaAnagrammi("", 0, disponibili);
		
	}
	
	private void ricercaAnagrammi(String parziale, int livello, List<Character> disponibili) {
		
		if(disponibili.size()==0 && !anagrammiCorretti.contains(parziale) && !anagrammiSbagliati.contains(parziale))
		{
			if(this.anagrammaCorretto(parziale))
				this.anagrammiCorretti.add(parziale);
			else
				this.anagrammiSbagliati.add(parziale);
		}
		
		for(Character ch : disponibili)
		{
			String attuale = parziale + ch;
			
			List<Character> rimanenti = new LinkedList<>(disponibili);
			rimanenti.remove(ch);
			
			this.ricercaAnagrammi(attuale, (livello+1), rimanenti);
		}
				
	}
	
	
}
