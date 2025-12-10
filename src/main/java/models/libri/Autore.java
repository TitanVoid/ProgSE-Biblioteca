package models.libri;
import models.Persona;
import java.io.Serializable;

/**
 * @class Autore
 * @brief Classe che rappresenta un autore.
 * @see Persona
 */
public class Autore extends Persona implements Serializable {

    /**
     * @brief Costruttore.
     *
     * @param[in] nome Nome dell'autore.
     * @param[in] cognome Cognome dell'autore.
     *
     */
    public Autore(String nome, String cognome) {
        super(nome, cognome);
    }
}