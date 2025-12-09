package Models;

import java.io.Serializable;

/**
 * @class Matricola
 *
 * @brief Classe che definisce una matricola universitaria e la sua verifica.
 *
 */
public class Matricola implements Serializable, Comparable<Matricola> {

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
     * @param[in] matricola Matricola universitaria.
     * @return true se il formato della matricola è valido, false altrimenti.
     */
    public static boolean verificaMatricola(String matricola){
        if(matricola == null || matricola.length() != 10 || !matricola.matches("\\d{10}")) return false;
        return true;
    }
    
    /**
     * @brief Verifica l'uguaglianza tra questa matricola e un altro oggetto in base alla matricola.
     * @param o Oggetto da confrontare.
     * @return true se sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(o == this) return true;
        if(o.getClass() != this.getClass()) return false;
        
        Matricola m = (Matricola) o;
        return this.matricola.equals(m.getMatricola());
    }

    /**
     * @brief Confronta questa matricola con un altra matricola in base alla matricola.
     * @param matricola matricola da confrontare.
     * @return 0 se sono uguali, valore negativo se questo libro precede l, positivo altrimenti.
     */
    @Override
    public int compareTo(Matricola matricola) {
        return this.matricola.compareTo(matricola.getMatricola());
    }

}