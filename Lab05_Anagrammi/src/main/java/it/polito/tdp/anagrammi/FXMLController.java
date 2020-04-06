package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtSbagliati;
    

    @FXML
    void doReset(ActionEvent event) {
    	
    	this.txtParola.clear();
    	this.txtCorretti.clear();
    	this.txtSbagliati.clear();
    }

    @FXML
    void doRicercaAnagrammi(ActionEvent event) {
    	
    	String anagramma = this.txtParola.getText();
    	this.txtCorretti.clear();
    	this.txtSbagliati.clear();
    	
    	String pattern = "[A-Za-z]*";
    	if(!anagramma.matches(pattern) || anagramma.length()==0) 
    	{
    		txtCorretti.appendText("Non ci sono anagrammi corretti perché la parola inserita da anagrammare non è valida, deve avere almeno un carattere e gli unici caratteri ammessi sono quelli alfabetici\n");
    		txtSbagliati.appendText("Non ci sono anagrammi sbagliati perché la parola inserita da anagrammare non è valida, deve avere almeno un carattere e gli unici caratteri ammessi sono quelli alfabetici\n");
    		return;
    	}
    	
    	try {
    		
    		this.model.anagrammi(anagramma);
    		
    		for(String s : this.model.getAnagrammiCorretti())
    			this.txtCorretti.appendText(s+"\n");
    		
    		for(String s : this.model.getAnagrammiSbagliati())
    			this.txtSbagliati.appendText(s+"\n");
    		
    	} catch(RuntimeException e) {
    		txtCorretti.appendText("Non ci sono anagrammi corretti perché c'è stato un errore di connessione al database!\n");
    		txtSbagliati.appendText("Non ci sono anagrammi sbagliati perché c'è stato un errore di connessione al database!\n");
    	}

    }

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSbagliati != null : "fx:id=\"txtSbagliati\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
