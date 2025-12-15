package models.prestiti;

import models.FormatoCampiErratoException;
import models.ISBN;
import models.Matricola;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @class Prestito
 * 
 * @brief Classe che rappresenta un prestito di un libro effettuato da un utente
 *        della biblioteca.
 * 
 *        La classe contiene le informazioni associate ad un prestito, ovvero:
 *        - Matricola dell'utente che ha richiesto il prestito;
 *        - Codice ISBN del libro prestato;
 *        - Data di inizio;
 *        - Data di scadenza;
 *        - Data di restituzione.
 * 
 * @see Matricola
 * @see ISBN
 */
public class Prestito implements Comparable<Prestito>, Serializable {
    private final Matricola matricolaUtente;
    private final ISBN codiceISBNLibro;
    private final LocalDate dataInizio;
    private LocalDate dataScadenza;
    private LocalDate dataRestituzione;

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Prestito a partire dai parametri forniti
     *        in ingresso.
     *
     * @post L'oggetto Prestito è creato.
     * @post La data di restituzione del prestito è impostata a null (valore per i
     *       prestiti attivi).
     *
     * @param[in] matricolaUtente Matricola dell'utente che ha effettuato il
     *            prestito.
     * @param[in] codiceISBNLibro Codice ISBN del libro prestato.
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
     * @brief Metodo Getter per la matricola di un utente che ha effettuato un
     *        prestito.
     * @return Matricola dell'utente che ha effettuato il prestito.
     */
    public Matricola getMatricolaUtente() {
        return matricolaUtente;
    }

    /**
     * @brief Metodo Getter per il codice ISBN di un libro prestato.
     * @return Codice ISBN del libro prestato.
     */
    public ISBN getCodiceISBNLibro() {
        return codiceISBNLibro;
    }

    /**
     * @brief Metodo Getter per la data di inizio di un prestito.
     * @return Data di inizio del prestito.
     */
    public LocalDate getDataInizio() {
        return dataInizio;
    }

    /**
     * @brief Metodo Getter per la data di scadenza di un prestito.
     * @return Data di scadenza del prestito.
     */
    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    /**
     * @brief Metodo Getter per la data di restituzione di un prestito.
     * @return Data di restituzione del prestito.
     */
    public LocalDate getDataRestituzione() {
        return dataRestituzione;
    }

    /**
     * 
     * @brief Metodo Setter per la data di scadenza di un prestito.
     *
     * @post L'oggetto Prestito sarà modificato, impostando come sua nuova data di
     *       scadenza il parametro dataScadenza.
     * 
     * @param[in] dataScadenza La data da impostare come nuova data di scadenza del
     *            prestito.
     */
    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * 
     * @brief Metodo Setter per la data di restituzione di un prestito.
     *
     * @post L'oggetto Prestito sarà modificato, impostando come sua nuova data di
     *       restituzione il parametro dataRestituzione.
     * 
     * @param[in] dataRestituzione La data da impostare come nuova data di
     *            restituzione del prestito.
     */
    public void setDataRestituzione(LocalDate dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    /**
     * @brief Restituisce lo stato di un prestito.
     * 
     *        Un prestito può essere attivo oppure concluso.
     * 
     * @return true se il prestito è attivo, false altrimenti.
     */
    public boolean isAttivo() {
        return dataRestituzione == null;
    }

    /**
     * @brief Converte una stringa che rappresenta una data di scadenza in un
     *        oggetto di tipo LocalDate.
     * 
     *        Metodo interno che effettua la conversione di una stringa in un
     *        oggetto.
     *        Viene utilizzato nel metodo verificaDataScadenza().
     * 
     * @param[in] autori Stringa che rappresenta la data di scadenza da convertire.
     * 
     * @return Una data di scadenza.
     */
    private static LocalDate StringDataScadenzaToLocalDate(String dataScadenza) {
        LocalDate data = null;
        // spazio fra nome autore e cognome autore
        String[] y = dataScadenza.split("-");
        int anno = Integer.parseInt(y[0]);
        int mese = Integer.parseInt(y[1]);
        int giorno = Integer.parseInt(y[2]);
        data = LocalDate.of(anno, mese, giorno);
        return data;
    }

    /**
     * @brief Verifica il formato di una data di scadenza.
     * 
     *        Verifica se la stringa passata come parametro corrisponde o meno ad
     *        una data di scadenza valida, sia formalmente che logicamente.
     * 
     * @param[in] dataScadenza Stringa da verificare.
     * 
     * @return true se la Stringa corrisponde ad una data di scadenza valida.
     * 
     * @throws FormatoCampiErratoException se si riscontra un errore nel formato del
     *                                     campo.
     */
    public static boolean verificaDataScadenza(String dataScadenza) {
        LocalDate data = null;
        String msg = "";
        if (!dataScadenza.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            msg = msg + "0";
        } else {
            data = StringDataScadenzaToLocalDate(dataScadenza);
            LocalDate today = LocalDate.now();
            if (data.isAfter(today)) {
                msg = msg + "1";
            }
        }
        if (!msg.equals("1")) {
            throw new FormatoCampiErratoException(msg);
        } else {
            return true;
        }

    }

    /**
     * @brief Confronto del Prestito corrente con un altro Prestito.
     *        Aderisce al contratto del metodo compareTo() di Comparable<T>.
     * 
     *        I prestiti vengono confrontati in base alla loro data di scadenza.
     * 
     * @param[in] p Prestito da confrontare con l'istanza corrente.
     * @return Valore negativo, zero o positivo se il Prestito corrente è
     *         rispettivamente minore, uguale o maggiore del Prestito passato come
     *         parametro.
     */
    @Override
    public int compareTo(Prestito p) {
        if (this.dataScadenza.equals(p.dataScadenza)){
            if (this.dataRestituzione == null && p.dataRestituzione != null) return -1;
            else if (this.dataRestituzione != null && p.dataRestituzione == null) return 1;
            return 0;
        }
        return this.dataScadenza.compareTo(p.dataScadenza);
    }

    /**
     * @brief Rappresentazione testuale dell'oggetto corrente.
     *        Aderisce al contratto del metodo toString() di Object.
     * 
     * @return Una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return "ISBN: " + codiceISBNLibro.getCodiceISBN() + " -> " + dataScadenza.toString();
    }
}