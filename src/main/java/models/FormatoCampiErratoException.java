package models;

/**
 * @brief Eccezione che segnala all'utente l'inserimento di uno o più campi dal
 *        formato invalido.
 */
public class FormatoCampiErratoException extends RuntimeException {

    /**
     * @brief Costruttore.
     *        Costruisce un'istanza dell'eccezione, senza specificare alcun
     *        messaggio di errore.
     */
    public FormatoCampiErratoException() {

    }

    /**
     * @brief Costruttore con messaggio di errore.
     *        Costruisce un'istanza dell'eccezione con il messaggio di errore
     *        passato come parametro.
     * 
     * @param message messaggio descrittivo dell'errore.
     */
    public FormatoCampiErratoException(String message) {
        super(message);
    }
}
