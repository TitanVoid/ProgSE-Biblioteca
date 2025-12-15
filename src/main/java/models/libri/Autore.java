package models.libri;

import models.Persona;

/**
 * @class Autore
 * 
 * @brief Classe che rappresenta un autore di libri.
 * 
 *        Un Autore è un'entità associata ad uno o più oggetti Libro contenuti
 *        nell'archivio della biblioteca.
 *        Ad un Libro possono essere associati uno o più autori.
 * 
 *        È una specializzazione di Persona, ed in quanto tale ne eredita i
 *        metodi e gli attributi.
 * 
 * @see Libro
 * @see Persona
 */
public class Autore extends Persona {

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Autore a partire dalle stringhe fornite
     *        come parametri in ingresso, che ne rappresentano il nome ed il
     *        cognome.
     * 
     * @post L'oggetto Autore è creato.
     *
     * @param[in] nome Stringa di caratteri che compone il nome.
     * @param[in] cognome Stringa di caratteri che compone il cognome.
     *
     */
    public Autore(String nome, String cognome) {
        super(nome, cognome);
    }
}