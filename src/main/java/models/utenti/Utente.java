package models.utenti;

import models.FormatoCampiErratoException;
import models.Matricola;
import models.Persona;
import models.prestiti.Prestito;

import java.util.List;
import java.util.ArrayList;

/**
 * @class Utente
 * 
 * @brief Classe che rappresenta un utente della biblioteca.
 * 
 *        La classe contiene le informazioni associate ad un utente della
 *        biblioteca, ovvero:
 *        - Nome;
 *        - Cognome;
 *        - Matricola;
 *        - Email;
 *        - Lista dei prestiti attivi.
 * 
 *        Ogni Utente è identificato univocamente dalla propria Matricola.
 * 
 *        È una specializzazione di Persona, ed in quanto tale ne eredita i
 *        metodi e gli attributi.
 * 
 * @see Matricola
 * @see Persona
 */

public class Utente extends Persona {

    private final Matricola matricolaUtente;
    private String email;
    private final List<Prestito> prestitiAttivi;

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Utente a partire dai parametri forniti
     *        in ingresso, che ne rappresentano rispettivamente il nome, il
     *        cognome, la matricola e l'indirizzo email.
     * 
     * 
     * @post L'oggetto Utente è creato.
     *
     * @param[in] nome Stringa di caratteri che compone il nome dell'utente.
     * @param[in] cognome Stringa di caratteri che compone il cognome dell'utente.
     * @param[in] matricolaUtente Matricola identificativa dell'utente.
     * @param[in] email Stringa di caratteri che compone l'indirizzo email
     *            dell'utente.
     */
    public Utente(String nome, String cognome, Matricola matricolaUtente, String email) {
        super(nome, cognome);
        this.matricolaUtente = matricolaUtente;
        this.email = email;
        this.prestitiAttivi = new ArrayList<>();
    }

    /**
     * @brief Metodo Getter per la matricola di un utente.
     * @return La matricola associata all'utente.
     */
    public Matricola getMatricolaUtente() {
        return matricolaUtente;
    }

    /**
     * @brief Metodo Getter per la stringa di caratteri dell'indirizzo email di un
     *        utente.
     * @return La stringa di caratteri che compone l'indirizzo email dell'utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @brief Metodo Getter per la lista di prestiti associati ad un utente.
     * @return La lista dei prestiti attivi associati all'utente.
     */
    public List<Prestito> getPrestitiAttivi() {
        return prestitiAttivi;
    }

    /**
     * @brief Metodo Setter per la stringa di caratteri dell'indirizzo email di un
     *        utente.
     *
     * @post L'oggetto Utente sarà modificato, impostando come suo nuovo indirizzo
     *       email la stringa email.
     * 
     * @param[in] email La stringa di caratteri da impostare come nuovo indirizzo
     *            email dell'utente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @brief Aggiunge un prestito alla lista dei prestiti attivi associati ad un
     *        utente.
     * 
     * @pre L'utente non deve avere più di 3 prestiti attivi contemporaneamente.
     * 
     * @param[in] prestito Prestito da aggiungere.
     */
    public void aggiungiPrestito(Prestito prestito) {
        assert (prestitiAttivi.size() < 3);
        prestitiAttivi.add(prestito);
    }

    /**
     * @brief Rimuove un prestito dalla lista dei prestiti attivi associati ad un
     *        utente.
     * 
     * @param[in] prestito Prestito da rimuovere.
     */
    public void rimuoviPrestito(Prestito prestito) {
        prestitiAttivi.remove(prestito);
    }

    /**
     * @brief Verifica il formato di un indirizzo email.
     * 
     *        Verifica se la stringa passata come parametro corrisponde o meno ad un
     *        indirizzo email valido, sulla base degli standard internazionali
     *        definiti all'interno del documento RFC 5322 (con dominio
     *        "studenti.unisa.it").
     * 
     * @param[in] email Stringa da verificare.
     * 
     * @return true se la stringa corrisponde ad un indirizzo email valido, false
     *         altrimenti.
     */
    public static boolean verificaEmail(String email) {
        return email.matches("^[a-z]{1}\\.[a-z0-9]{1,}@studenti\\.unisa\\.it$");
    }

    /**
     * @brief Verifica il formato di un utente.
     * 
     *        Verifica il formato di tutti i campi dell'utente, passati come
     *        parametri in ingresso, richiamando al suo interno gli appositi metodi
     *        di verifica.
     * 
     * @param[in] nome Stringa corrispondente al nome dell'utente.
     * @param[in] cognome Stringa corrispondente al cognome dell'utente.
     * @param[in] matricolaUtente Stringa corrispondente alla matricola dell'utente.
     * @param[in] email Stringa corrispondente all'indirizzo email dell'utente.
     * 
     * @return true se il formato di tutti i campi dell'utente è corretto.
     * 
     * @throws FormatoCampiErratoException se si riscontra un errore nel formato di
     *                                     uno o più campi.
     */
    public static boolean verificaUtente(String nome, String cognome, String matricolaUtente, String email) {
        String msg = "";
        if (!Persona.verificaNome(nome)) {
            msg = msg + "0";
        } else {
            msg = msg + "1";
        }

        if (!Persona.verificaCognome(cognome)) {
            msg = msg + "0";
        } else {
            msg = msg + "1";
        }

        if (!Matricola.verificaMatricola(matricolaUtente)) {
            msg = msg + "0";
        } else {
            msg = msg + "1";
        }

        if (!Utente.verificaEmail(email)) {
            msg = msg + "0";
        } else {
            msg = msg + "1";
        }

        String check = "1111";
        if (!msg.equals(check)) {
            throw new FormatoCampiErratoException(msg);
        }
        return true;
    }

    /**
     * @brief Hashcode per l'oggetto Utente.
     *        Aderisce al contratto del metodo hashCode() di Object.
     * 
     * @return L'hashcode associato all'oggetto Utente.
     */
    @Override
    public int hashCode() {
        return matricolaUtente == null ? 0 : matricolaUtente.hashCode() * 31;
    }

    /**
     * @brief Verifica dell'uguaglianza tra un oggetto e l'istanza corrente.
     *        Aderisce al contratto del metodo equals() di Object.
     * 
     *        Due oggetti Utente si dicono uguali se le matricole ad essi associate
     *        sono uguali.
     * 
     * @param[in] o Oggetto da confrontare con l'istanza corrente.
     * @return true se i due oggetti Utente sono uguali, false altrimenti.
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
     * @brief Confronto dell'Utente corrente con un altro Utente.
     *        Aderisce al contratto del metodo compareTo() di Comparable<T>, ed
     *        estende il contratto definito dalla sua superclasse Persona.
     * 
     *        Gli utenti vengono confrontati:
     *        - In base all'ordine lessicografico del loro cognome;
     *        - A parità di cognome, in base all'ordine lessicografico del loro
     *        cognome;
     *        - A parità dei campi precedenti, in base alla loro matricola.
     * 
     * @param[in] u Utente da confrontare con l'istanza corrente.
     * @return Valore negativo, zero o positivo se l'Utente corrente è
     *         rispettivamente minore, uguale o maggiore dell'Utente passato come
     *         parametro.
     */
    @Override
    public int compareTo(Persona u) {
        if (super.compareTo(u) == 0) {
            Utente utente = (Utente) u;
            return this.getMatricolaUtente().compareTo(utente.getMatricolaUtente());
        } else
            return super.compareTo(u);
    }
}