package models.utenti;

import models.OggettoGiaPresenteException;

/**
 * @brief Eccezione che segnala all'utente l'inserimento di un utente già
 *        presente nell'archivio della biblioteca.
 *        È una specializzazione di OggettoGiaPresenteException.
 * 
 * @see OggettoGiaPresenteException
 */
public class UtenteGiaPresenteException extends OggettoGiaPresenteException {

    /**
     * @brief Costruttore.
     *        Costruisce un'istanza dell'eccezione, senza specificare alcun
     *        messaggio di errore.
     */
    public UtenteGiaPresenteException() {

    }

    /**
     * @brief Costruttore con messaggio di errore.
     *        Costruisce un'istanza dell'eccezione con il messaggio di errore
     *        passato come parametro.
     * 
     * @param msg messaggio descrittivo dell'errore.
     */
    public UtenteGiaPresenteException(String msg) {
        super(msg);
    }
}
