package models.prestiti;

import models.ISBN;
import models.Matricola;

import java.time.LocalDate;

/**
 * @class Prestito
 * @brief Classe che rappresenta un prestito di un libro effettuato ad un
 *        utente.
 */
public class Prestito implements Comparable<Prestito> {
    private final Matricola matricolaUtente;
    private final ISBN codiceISBNLibro;
    private final LocalDate dataInizio;
    private LocalDate dataScadenza;
    private LocalDate dataRestituzione;

    /**
     * @brief Costruttore.
     * @param[in] matricolaUtente Matricola dell'utente che ha effettuato il
     *            prestito.
     * @param[in] codiceISBNLibro ISBN del libro prestato.
     * @param[in] dataInizio Data di inizio del prestito.
     * @param[in] dataScadenza Data di scadenza del prestito.
     */
    public Prestito(Matricola matricolaUtente, ISBN codiceISBNLibro, LocalDate dataInizio, LocalDate dataScadenza) {
        this.matricolaUtente = matricolaUtente;
        this.codiceISBNLibro = codiceISBNLibro;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        dataRestituzione = null;
    }

    /**
     * @brief Restituisce la matricola dell'utente che ha effettuato il prestito.
     * @return Matricola dell'utente che ha effettuato il prestito.
     */
    public Matricola getMatricolaUtente() {
        return matricolaUtente;
    }

    /**
     * @brief Restituisce l'ISBN del libro prestato.
     * @return ISBN del libro prestato.
     */
    public ISBN getCodiceISBNLibro() {
        return codiceISBNLibro;
    }

    /**
     * @brief Restituisce la data di inizio del prestito.
     * @return Data di inizio del prestito.
     */
    public LocalDate getDataInizio() {
        return dataInizio;
    }

    /**
     * @brief Restituisce la data di scadenza del prestito.
     * @return Data di scadenza del prestito.
     */
    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    /**
     * @brief Restituisce la data di restituzione del prestito.
     * @return Data di restituzione del prestito.
     */
    public LocalDate getDataRestituzione() {
        return dataRestituzione;
    }

    /**
     * @brief Imposta la data di scadenza del prestito.
     * @param[in] dataScadenza Data di scadenza da impostare.
     */
    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * @brief Imposta la data di restituzione del prestito.
     * @param[in] dataRestituzione Data di restituzione da impostare.
     */
    public void setDataRestituzione(LocalDate dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    /**
     * @brief Verifica se il prestito è attivo.
     * @return true se il prestito è attivo, false altrimenti.
     */
    public boolean isAttivo() {
        return dataRestituzione != null;
    }

    /**
     * @brief Verifica se la data di scadenza inserita è valida.
     * @param[in] dataScadenza Data di scadenza da verificare.
     * @return true se la data di scadenza inserita è logicamente valida (ovvero
     *         successiva alla data corrente), false altrimenti.
     */
    public static boolean verificaDataScadenza(LocalDate dataScadenza) {
        LocalDate today = LocalDate.now();
        return dataScadenza.isAfter(today);
    }

    /**
     * @brief Confronta due prestiti in base alla loro data di scadenza.
     * @param[in] p Prestito da confrontare col prestito corrente.
     * @return Valore minore di zero, pari a zero oppure maggiore di zero se la data
     *         di scadenza del prestito corrente è rispettivamente precedente,
     *         uguale o successiva alla data di scadenza del prestito p.
     */
    @Override
    public int compareTo(Prestito p) {
        return this.dataScadenza.compareTo(p.dataScadenza);
    }
}