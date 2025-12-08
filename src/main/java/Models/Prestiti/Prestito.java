package Models.Prestiti;

import Models.ISBN;
import Models.Matricola;

import java.time.LocalDate;

/**
 * @class Prestito
 * @brief Classe che rappresenta un prestito di un libro da parte di un utente.
 */
public class Prestito implements Comparable<Prestito> {
    private final Matricola matricola;
    private final ISBN id;
    private final LocalDate dataInizio;
    private LocalDate dataScadenza;
    private LocalDate dataRestituzione;

    /**
     * @brief Costruttore.
     * @param[in] matricola Matricola dell'utente che effettua il prestito.
     * @param[in] id ISBN del libro prestato.
     * @param[in] dataInizio Data di inizio del prestito.
     * @param[in] dataScadenza Data di scadenza del prestito.
    */
    public Prestito(Matricola matricola, ISBN id, LocalDate dataInizio, LocalDate dataScadenza) {
        this.matricola = matricola;
        this.id = id;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        this.dataRestituzione = null;
    }

    /**
     * @brief Imposta la data di scadenza del prestito.
     * @param[in] data Data di scadenza da impostare.
     */
    public void setDataScadenza(LocalDate data) {
        this.dataScadenza = data;
    }

    /**
     * @brief Restituisce la data di scadenza del prestito.
     * @return Data di scadenza del prestito.
     */
    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    /**
     * @brief Restituisce la data di inizio del prestito.
     * @return Data di inizio del prestito.
     */
    public LocalDate getDataInizio() {
        return dataInizio;
    }

    /**
     * @brief Imposta la data di restituzione del prestito.
     * @param[in] data Data di restituzione da impostare.
     */
    public void setDataRestituzione(LocalDate data) {
        this.dataRestituzione = data;
    }

    /**
     * @brief Restituisce la data di restituzione del prestito.
     * @return Data di restituzione del prestito.
     */
    public LocalDate getDataRestituzione() {
        return dataRestituzione;
    }

    /**
     * @brief Verifica se il prestito è attivo.
     * @return true se il prestito è attivo, false altrimenti.
     */
    public boolean isAttivo(){
        return false;
    }

    /**
     * @brief Verifica se la data di scadenza è passata.
     * @param[in] dataScadenza Data di scadenza da verificare.
     * @return true se la data di scadenza è passata, false altrimenti.
     */
    public static boolean verificaDataScadenza(LocalDate dataScadenza) {
        LocalDate today = LocalDate.now();
        return dataScadenza.isBefore(today);
    }

    /**
     * @brief Verifica l'uguaglianza tra due prestiti.
     * @param[in] o Oggetto da confrontare.
     * @return true se i prestiti sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o){
        return false;
    }

    /**
     * @brief Confronta due prestiti in base alla data di scadenza.
     * @param[in] p Prestito da confrontare.
     * @return Valore negativo, zero o positivo se questo prestito è rispettivamente
     *         minore, uguale o maggiore del prestito p.
     */
    @Override
    public int compareTo(Prestito p){
        return 0;
    }
}