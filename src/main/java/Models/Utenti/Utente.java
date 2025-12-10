package Models.Utenti;

import Models.Matricola;
import Models.Persona;
import Models.Prestiti.Prestito;

import java.util.List;
import java.util.ArrayList;

/**
 * @class Utente
 * @brief Classe che rappresenta un utente della biblioteca.
 * @see Matricola
 * @see Prestito
 */

public class Utente extends Persona implements Comparable<Utente> {

    private final Matricola matricolaUtente;
    private String email;
    private final List<Prestito> prestitiAttivi;

    /**
     * @brief Costruttore.
     * @param[in] nome Nome dell'utente.
     * @param[in] cognome Cognome dell'utente.
     * @param[in] matricolaUtente Matricola identificativa dell'utente.
     * @param[in] email Email dell'utente.
     */

    public Utente(String nome, String cognome, Matricola matricolaUtente, String email) {
        super(nome, cognome);
        this.matricolaUtente = matricolaUtente;
        this.email = email;
        this.prestitiAttivi = new ArrayList<>();
    }

    /**
     * @brief Restituisce la matricola dell'utente.
     * @return Matricola dell'utente.
     */
    public Matricola getMatricolaUtente() {
        return matricolaUtente;
    }

    /**
     * @brief Restituisce l'email dell'utente.
     * @return Email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @brief Restituisce la lista dei prestiti attivi.
     * @return Lista dei prestiti attivi associati all'utente.
     */
    public List<Prestito> getPrestitiAttivi() {
        return prestitiAttivi;
    }

    /**
     * @brief Imposta l'email dell'utente.
     * @param[in] email Email da impostare.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @brief Aggiunge un prestito alla lista dei prestiti attivi dell'utente.
     * @param[in] prestito Prestito da aggiungere.
     */
    public void aggiungiPrestito(Prestito prestito) {
        prestitiAttivi.add(prestito);
    }

    /**
     * @brief Rimuove un prestito dalla lista dei prestiti attivi dell'utente.
     * @param[in] prestito Prestito da rimuovere.
     */
    public void rimuoviPrestito(Prestito prestito) {
        if (!prestitiAttivi.remove(prestito)) {
            System.out.println("Prestito inesistente nella lista.");
        }
    }

    /**
     * @brief Verifica il formato di un'email.
     * @param[in] email Email da verificare.
     * @return true se il formato dell'email è corretto, false altrimenti.
     */
    public static boolean verificaEmail(String email) {
        return email.matches("^\\w+@studenti\\.unisa\\.it$");
    }

    /**
     * @brief Verifica il formato dei campi di un utente.
     * @param[in] utente Utente da verificare.
     * @param[in] nome Nome dell'utente.
     * @param[in] cognome Cognome dell'utente.
     * @param[in] matricolaUtente Matricola dell'utente.
     * @param[in] email Email dell'utente.
     * @return true se il formato di tutti i campi dell'utente è corretto, false
     *         altrimenti.
     */
    public static boolean verificaUtente(String nome, String cognome, Matricola matricolaUtente, String email) {
        if (!Persona.verificaNome(nome) || !Persona.verificaCognome(cognome) || !Matricola.verificaMatricola(email)
                || !Utente.verificaEmail(email)) {
            return false;
        }
        return true;
    }

    /**
     * @brief Calcola l'hash code dell'utente.
     * @return Valore hashcode.
     */
    @Override
    public int hashCode() {
        return matricolaUtente == null ? 0 : matricolaUtente.hashCode() * 31;
    }

    /**
     * @brief Verifica l'uguaglianza tra questo utente e un altro oggetto.
     * @param[in] o Oggetto da confrontare.
     * @return true se sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (o.getClass() != this.getClass())
            return false;

        Utente u = (Utente) o;
        return this.matricolaUtente.equals(u.matricolaUtente);
    }

    /**
     * @brief Confronta questo utente con un altro utente.
     * @param[in] u Utente da confrontare.
     * @return 0 se sono uguali, valore negativo se questo utente precede u,
     *         positivo altrimenti.
     */
    @Override
    public int compareTo(Utente u) {
        return 0;
    }

}