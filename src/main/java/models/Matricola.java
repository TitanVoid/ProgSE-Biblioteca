package models;

import java.io.Serializable;

/**
 * @class Matricola
 *
 * @brief Classe che definisce una matricola universitaria e la sua verifica.
 *
 */
public class Matricola implements Serializable {

    private final String matricola;

    /**
     * @brief Costruttore.
     *
     * @param[in] matricola Matricola universitaria.
     *
     */
    public Matricola(String matricola) {

        this.matricola = matricola;
    }

    /**
     * @brief Restituisce la matricola.
     *
     * @return matricola.
     *
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * @brief Verifica la correttezza del formato della matricola.
     *
     * @return true se il formato della matricola è valido, false altrimenti.
     */
    public static boolean verificaMatricola(){
        return false;
    }

}