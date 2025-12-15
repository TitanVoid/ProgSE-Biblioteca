package models;

/**
 * @brief Eccezione che segnala all'utente l'inserimento di un oggetto
 *        già presente nell'archivio della biblioteca.
 *        Si specializza in UtenteGiaPresenteException e
 *        LibroGiaPresenteException.
 * 
 * @see UtenteGiaPresenteException
 * @see LibroGiaPresenteException
 */
public class OggettoGiaPresenteException extends Exception {

    /**
     * @brief Costruttore.
     *        Costruisce un'istanza dell'eccezione, senza specificare alcun
     *        messaggio di errore.
     */
    public OggettoGiaPresenteException() {

    }

    /**
     * @brief Costruttore con messaggio di errore.
     *        Costruisce un'istanza dell'eccezione con il messaggio di errore
     *        passato come parametro.
     * 
     * @param msg messaggio descrittivo dell'errore.
     */
    public OggettoGiaPresenteException(String msg) {
        super(msg);
    }
}
