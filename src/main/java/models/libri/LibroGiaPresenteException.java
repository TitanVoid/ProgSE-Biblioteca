/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.libri;

import models.OggettoGiaPresenteException;

/**
 * @brief Eccezione che segnala all'utente l'inserimento di un libro già
 *        presente nell'archivio della biblioteca.
 *        È una specializzazione di OggettoGiaPresenteException.
 * 
 * @see OggettoGiaPresenteException
 */
public class LibroGiaPresenteException extends OggettoGiaPresenteException {

    /**
     * @brief Costruttore.
     *        Costruisce un'istanza dell'eccezione, senza specificare alcun
     *        messaggio di errore.
     */
    public LibroGiaPresenteException() {

    }

    /**
     * @brief Costruttore con messaggio di errore.
     *        Costruisce un'istanza dell'eccezione con il messaggio di errore
     *        passato come parametro.
     * 
     * @param msg messaggio descrittivo dell'errore.
     */
    public LibroGiaPresenteException(String msg) {
        super(msg);
    }
}
