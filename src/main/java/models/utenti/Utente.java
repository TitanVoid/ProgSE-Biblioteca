package models.utenti;

import models.Matricola;
import models.Persona;
import models.prestiti.Prestito;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * @class Utente
 * @brief Classe che rappresenta un utente della biblioteca.
 * @see Matricola
 * @see Prestito
 */

public class Utente extends Persona implements Comparable<Utente> , Serializable {

    private final Matricola matricola;
    private String email;
    private final List<Prestito> prestitiAttivi;

    /**
     * @brief Costruttore.
     * @param[in] nome Nome dell'utente.
     * @param[in] cognome Cognome dell'utente.
     * @param[in] matricola Matricola identificativa.
     * @param[in] email Email dell'utente.
     * @param[in] persona Persona associata all'utente.
     */

    public Utente(String nome, String cognome, Matricola matricola, String email) {
        super(nome, cognome);
        this.matricola = matricola;
        this.email = email;
        this.prestitiAttivi = new ArrayList<>();
    }

    /**
     * @brief Restituisce l'email dell'utente.
     * @return Email.
     */

    public String getEmail() {
        return email;
    }

    /**
     * @brief Imposta l'email dell'utente.
     * @param[in] email Nuova email da impostare.
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @brief Restituisce la matricola dell'utente.
     * @return persona.
     */

    public Matricola getMatricola() {
        return matricola;
    }

    /**
     * @brief Restituisce la lista dei prestiti attivi.
     * @return Lista dei prestiti attivi dell'utente.
     */

    public List<Prestito> getPrestitiAttivi() {
        return prestitiAttivi;
    }

    /**
     * @brief Aggiunge un prestito alla lista dei prestiti attivi.
     * @param[in] prestito Prestito da aggiungere.
     */

    public void aggiungiPrestito(Prestito prestito) {
        prestitiAttivi.add(prestito);
    }

    /**
     * @brief Rimuove un prestito dalla lista dei prestiti attivi.
     * @param[in] prestito Prestito da rimuovere.
     */

    public void rimuoviPrestito(Prestito prestito) {

    }

    /**
     * @brief Verifica il formato di una email.
     * @param[in] email Email da verificare.
     * @return true se il formato dell'email è corretto, false altrimenti.
     */

    public static boolean verificaEmail(String email) {
        return false;
    }

    /**
     * @brief Verifica il formato di un utente.
     * @param[in] utente Utente da verificare.
     * @param[in] nome Nome dell'utente.
     * @param[in] cognome Cognome dell'utente.
     * @param[in] matricola Matricola dell'utente.
     * @param[in] email Email dell'utente.
     * @return true se il formato dell'utente è corretto, false altrimenti.
     */

    public static boolean verificaUtente(String nome, String cognome, Matricola matricola, String email) {
        return false;
    }

    /**
     * @brief Calcola l'hash code dell'utente.
     * @return Valore hashcode.
     */

    @Override
    public int hashCode(){
        return 0;
    }

    /**
     * @brief Verifica l'uguaglianza tra questo utente e un altro oggetto.
     * @param[in] o Oggetto da confrontare.
     * @return true se sono uguali, false altrimenti.
     */

    @Override
    public boolean equals(Object o) {
        return false;
    }

    /**
     * @brief Confronta questo utente con un altro utente.
     * @param[in] u Utente da confrontare.
     * @return 0 se sono uguali, valore negativo se questo utente precede u, positivo altrimenti.
     */
    @Override
    public int compareTo(Utente u) {
        return 0;
    }

}