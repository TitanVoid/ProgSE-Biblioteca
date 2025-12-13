package models;

import java.io.Serializable;

/**
 * @class Persona
 * @brief Classe che incapsula le informazioni di una persona.
 *
 *        Rappresenta una persona generica con nome e cognome, fornendo metodi
 *        di accesso, modifica e verifica.
 *
 */
public abstract class Persona implements Serializable, Comparable<Persona> {

    private String nome;
    private String cognome;

    /**
     * @brief Costruttore.
     *
     * @param[in] nome Nome della persona.
     * @param[in] cognome Cognome della persona.
     *
     */
    public Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    /**
     * @brief Restituisce il nome.
     *
     * @return nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @brief Restituisce il cognome della persona.
     *
     * @return cognome.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @brief Imposta il nome.
     *
     * @param[in] nome Nuovo nome da assegnare.
     *
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @brief Imposta il cognome.
     *
     * @param[in] cognome Nuovo cognome da assegnare.
     *
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @brief Verifica la correttezza del formato del nome.
     * 
     * @param[in] nome Nome da verificare.
     * @return true se il formato del nome è valido, false altrimenti.
     *
     */
    public static boolean verificaNome(String nome) {
        if (nome == null || !nome.matches("^\\D{3,25}$"))
            return false;
        return true;
    }

    /**
     * @brief Verifica la correttezza del formato del cognome.
     *
     * @param[in] cognome Cognome da verificare.
     *
     * @return true se il formato del cognome è valido, false altrimenti.
     *
     */
    public static boolean verificaCognome(String cognome) {
        if (cognome == null || !cognome.matches("^\\D{3,25}$"))
            return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (o.getClass() != this.getClass())
            return false;

        Persona p = (Persona) o;
        return this.nome.equals(p.nome) && this.cognome.equals(p.cognome);
    }

    @Override
    public int compareTo(Persona p) {
        if (this.cognome.equals(p.cognome)) {
            return this.nome.compareTo(p.nome);
        }
        return this.cognome.compareTo(p.cognome);
    }

    @Override
    public String toString() {
        return this.nome + " " + this.cognome;
    }
}