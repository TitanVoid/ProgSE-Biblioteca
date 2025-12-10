package models.utenti;

import models.OggettoGiaPresenteException;

public class UtenteGiaPresenteException extends OggettoGiaPresenteException {

    /**
     * Creates a new instance of <code>LibroGiaPresenteException</code> without
     * detail message.
     */
    public UtenteGiaPresenteException() {
    }

    /**
     * Constructs an instance of <code>LibroGiaPresenteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UtenteGiaPresenteException(String msg) {
        super(msg);
    }
}
