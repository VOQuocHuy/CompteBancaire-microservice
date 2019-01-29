package fr.dauphine.miageif.msa.projet;

public class CompteBancaireNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "Compte bancaire n'est pas trouvé";

    public CompteBancaireNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public CompteBancaireNotFoundException(String message) {
        super(message);
    }
	
} 
