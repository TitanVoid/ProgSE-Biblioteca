package models.utenti;

import models.Matricola;
import models.Persona;
import models.prestiti.Prestito;

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
     * @brief Restituisce la lista dei prestiti attivi associati ad un utente.
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
     * @brief Aggiunge un prestito alla lista dei prestiti attivi associati ad un
     *        utente.
     * @param[in] prestito Prestito da aggiungere.
     */
    public void aggiungiPrestito(Prestito prestito) {
        prestitiAttivi.add(prestito);
    }

    /**
     * @brief Rimuove un prestito dalla lista dei prestiti attivi associati ad un
     *        utente.
     * @param[in] prestito Prestito da rimuovere.
     */
    public void rimuoviPrestito(Prestito prestito) {
        prestitiAttivi.remove(prestito);
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
     * @return true se il formato dei campi dell'utente è corretto, lancia eccezione altrimenti.
     */
    public static boolean verificaUtente(String nome, String cognome, String matricolaUtente, String email) {


        String msg= "";
        if(!Persona.verificaNome(nome)){
            msg = msg+ "0";
        }else {
            msg = msg+ "1";
        }

        if(!Persona.verificaCognome(cognome)){
            msg = msg+ "0";
        }else  {
            msg = msg+ "1";
        }

        if(!Matricola.verificaMatricola(matricolaUtente)){
            msg = msg+ "0";
        }else{
            msg = msg+ "1";
        }

        if(!Utente.verificaEmail(email)){
            msg = msg+ "0";
        }else  {
            msg = msg+ "1";
        }

        String check= "1111";
        if(!msg.equals(check)){
            throw new RuntimeException(msg);
        }
        return true;

    }

    /**
     * @brief Calcola l'hashcode di un utente.
     * @return Valore hashcode.
     */
    @Override
    public int hashCode() {
        return matricolaUtente == null ? 0 : matricolaUtente.hashCode() * 31;
    }

    /**
     * @brief Verifica l'uguaglianza tra l'oggetto corrente ed un altro oggetto.
     * @param[in] o Oggetto da confrontare.
     * @return true se i due oggetti sono uguali, false altrimenti.
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
     * @brief Confronta due utenti.
     *        Il confronto è effettuato in base ai seguenti campi:
     *        - In primo luogo, in base al loro cognome;
     *        - A parità di cognome, in base al loro nome;
     *        - A parità dei campi precedenti, in base alla loro matricola.
     * @param[in] u Utente da confrontare con l'utente corrente.
     * @return Valore minore di zero, pari a zero oppure maggiore di zero se
     *         l'utente corrente precede, è uguale o segue l'utente u nell'ordine
     *         lessicografico.
     */
    @Override
    public int compareTo(Utente u) {
        if (this.getCognome().equals(u.getCognome())) {
            if (this.getNome().equals(u.getNome())) {
                return this.matricolaUtente.compareTo(u.matricolaUtente);
            }
            return this.getNome().compareTo(u.getNome());
        }
        return this.getCognome().compareTo(u.getCognome());
    }
}