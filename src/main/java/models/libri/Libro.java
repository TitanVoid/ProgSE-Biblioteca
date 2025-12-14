package models.libri;

import models.FormatoCampiErratoException;
import models.ISBN;
import models.Persona;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @class Libro
 * 
 * @brief Classe che rappresenta un libro presente nell'archivio della
 *        biblioteca.
 * 
 *        La classe contiene le informazioni associate ad un libro contenuto
 *        nell'archivio della biblioteca, ovvero:
 *        - Titolo;
 *        - Lista degli autori;
 *        - Codice ISBN;
 *        - Anno di pubblicazione;
 *        - Numero di copie disponibili.
 * 
 *        Ogni Libro è identificato univocamente dal proprio codice ISBN.
 * 
 * @see ISBN
 */
public class Libro implements Comparable<Libro>, Serializable {

    private String titolo;
    private final List<Autore> autori;
    private final ISBN codiceISBNLibro;
    private int annoPubblicazione;
    private int copieDisponibili;

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Libro a partire dai parametri forniti in
     *        ingresso, che ne rappresentano rispettivamente il titolo, l'anno di
     *        pubblicazione, il codice ISBN, il numero di copie disponibili e la
     *        lista degli autori.
     * 
     * @pre I parametri devono avere un formato valido.
     * 
     * @post L'oggetto Libro è creato.
     *
     * @param[in] titolo Stringa di caratteri che compone il titolo del libro.
     * @param[in] annoPubblicazione Numero che rappresenta l'anno di pubblicazione
     *            del libro.
     * @param[in] codiceISBNLibro Codice ISBN identificativo del libro.
     * @param[in] copieDisponibili Numero di copie disponibili del libro.
     * @param[in] autori Stringa che rappresenta la lista degli autori del libro.
     */
    public Libro(String titolo, int annoPubblicazione, ISBN codiceISBNLibro, int copieDisponibili, String autori) {
        assert (verificaLibro(autori, titolo, annoPubblicazione + "", codiceISBNLibro.getCodiceISBN(),
                copieDisponibili + ""));
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.codiceISBNLibro = codiceISBNLibro;
        this.copieDisponibili = copieDisponibili;
        this.autori = StringAutoriToList(autori);
    }

    /**
     * @brief Metodo Getter per la stringa di caratteri del titolo di un libro.
     * @return La stringa di caratteri che compone il titolo del libro.
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @brief Metodo Getter per l'anno di pubblicazione di un libro.
     * @return L'anno di pubblicazione del libro.
     */
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    /**
     * @brief Metodo Getter per il codice ISBN di un libro.
     * @return Il codice ISBN associato al libro.
     */
    public ISBN getCodiceISBNLibro() {
        return codiceISBNLibro;
    }

    /**
     * @brief Metodo Getter per il numero di copie disponibili di un libro.
     * @return Il numero di copie disponibili del libro.
     */
    public int getCopieDisponibili() {
        return copieDisponibili;
    }

    /**
     * @brief Metodo getter per la lista degli autori di un libro.
     * @return La lista degli autori del libro.
     */
    public List<Autore> getAutori() {
        return autori;
    }

    /**
     * @brief Metodo getter per la stringa che rappresenta la lista degli autori di
     *        un libro.
     * @return La stringa che rappresenta la lista degli autori del libro.
     */
    public String getStringaAutori() {
        return ListAutoriToString(autori);
    }

    /**
     * @brief Metodo Setter per la stringa di caratteri del titolo di un libro.
     *
     * @post L'oggetto Libro sarà modificato, impostando come suo nuovo titolo la
     *       stringa titolo.
     * 
     * @param[in] titolo La stringa di caratteri da impostare come nuovo titolo del
     *            libro.
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @brief Metodo Setter per l'anno di pubblicazione di un libro.
     *
     * @post L'oggetto Libro sarà modificato, impostando come suo nuovo anno di
     *       pubblicazione il parametro annoPubblicazione.
     * 
     * @param[in] annoPubblicazione Il nuovo anno di pubblicazione del libro.
     */
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    /**
     * @brief Metodo Setter per le copie disponibili di un libro.
     *
     * @post L'oggetto Libro sarà modificato, impostando come suo nuovo numero di
     *       copie disponibili il parametro copieDisponibili.
     * 
     * @param[in] copieDisponibili Il nuovo numero di copie disponibili del libro.
     */
    public void setCopieDisponibili(int copieDisponibili) {
        this.copieDisponibili = copieDisponibili;
    }

    /**
     * @brief Aggiunge un autore alla lista degli autori di un libro.
     * 
     * @param[in] autore Autore da aggiungere.
     */
    public void aggiungiAutore(Autore autore) {
        autori.add(autore);
    }

    /**
     * @brief Rimuove un autore dalla lista degli autori di un libro.
     * 
     * @param[in] autore Autore da rimuovere.
     */
    public void rimuoviAutore(Autore autore) {
        autori.remove(autore);
    }

    /**
     * @brief Converte una stringa di autori in una lista di oggetti di tipo Autore.
     * 
     *        Metodo interno che effettua la conversione di una stringa in una lista
     *        di oggetti.
     *        Viene utilizzato nel costruttore di Libro.
     * 
     * @param[in] autori Stringa che rappresenta la lista di autori da convertire.
     * 
     * @return Una lista di autori.
     */
    private static List<Autore> StringAutoriToList(String autori) {
        List<Autore> al = new ArrayList<Autore>();
        // spazio fra due o più autori
        String[] x = autori.split(", ");
        for (int i = 0; i < x.length; i++) {
            // spazio fra nome autore e cognome autore
            String[] y = x[i].split(" ");
            String nome = y[0];
            String cognome = y[1];
            al.add(new Autore(nome, cognome));
        }
        return al;
    }

    /**
     * @brief Converte una lista di oggetti di tipo Autore in una stringa di autori.
     * 
     * @param[in] autori Lista di autori da convertire.
     * 
     * @return Una stringa che rappresenta la lista di autori.
     */
    private String ListAutoriToString(List<Autore> autori) {
        StringBuffer autoriStringa = new StringBuffer();
        for (Autore a : autori) {
            autoriStringa.append(a.toString());
            autoriStringa.append(", ");
        }
        return autoriStringa.toString();
    }

    /**
     * @brief Verifica il formato di un libro.
     * 
     *        Verifica il formato di tutti i campi del libro, passati come parametri
     *        in ingresso, richiamando al suo interno gli appositi metodi di
     *        verifica.
     * 
     * @param[in] autori Stringa corrispondente alla lista degli autori del libro.
     * @param[in] titolo Stringa corrispondente al titolo del libro.
     * @param[in] annoPubblicazione Stringa corrispondente all'anno di pubblicazione
     *            del libro.
     * @param[in] isbn Stringa corrispondente al codice ISBN del libro.
     * @param[in] copieDisponibili Stringa corrispondente al numero di copie
     *            disponibili del libro.
     * 
     * @return true se il formato di tutti i campi del libro è corretto.
     * 
     * @throws FormatoCampiErratoException se si riscontra un errore nel formato di
     *                                     uno o più campi.
     */
    public static boolean verificaLibro(String autori, String titolo, String annoPubblicazione, String codiceISBNLibro,
            String copieDisponibili) throws RuntimeException {
        String msg = "";

        // Controllo sul formato del titolo:
        if (titolo == null || titolo.length() > 100) {
            msg = msg + '0';
        } else {
            msg = msg + '1';
        }

        // Controllo sul formato degli autori:
        try {
            List<Autore> al = StringAutoriToList(autori);
            String correct = "1";
            for (Autore a : al) {
                if (!Persona.verificaNome(a.getNome()) || !Persona.verificaCognome(a.getCognome())) {
                    correct = "0";
                }
            }
            msg = msg + correct;
        } catch (Exception e) {
            msg = msg + '0';
        }

        // Controllo sul formato dell'ISBN:
        if (!ISBN.verificaISBN(codiceISBNLibro)) {
            msg = msg + '0';
        } else {
            msg = msg + '1';
        }

        // Controllo sulle copie disponibili:
        if (copieDisponibili.matches("\\d{1,3}")) {
            int copie = Integer.parseInt(copieDisponibili);
            if (copie < 0 || copie > 100) {
                msg = msg + '0';
            } else {
                msg = msg + '1';
            }
        } else {
            msg = msg + '0';
        }

        // Controllo sull'anno di pubblicazione:
        if (annoPubblicazione.matches("\\d{1,4}")) {
            int anno = Integer.parseInt(annoPubblicazione);
            if (anno < 0 || anno > LocalDate.now().getYear()) {
                msg = msg + '0';
            } else {
                msg = msg + '1';
            }
        } else {
            msg = msg + '0';
        }

        String check = "11111";
        if (!msg.equals(check)) {
            throw new FormatoCampiErratoException(msg);
        } else {
            return true;
        }
    }

    /**
     * @brief Hashcode per l'oggetto Libro.
     *        Aderisce al contratto del metodo hashCode() di Object.
     * 
     * @return L'hashcode associato all'oggetto Libro.
     */
    @Override
    public int hashCode() {
        return this.codiceISBNLibro == null ? 0 : codiceISBNLibro.hashCode() * 31;
    }

    /**
     * @brief Verifica dell'uguaglianza tra un oggetto e l'istanza corrente.
     *        Aderisce al contratto del metodo equals() di Object.
     * 
     *        Due oggetti Libro si dicono uguali se gli ISBN ad essi associati sono
     *        uguali.
     * 
     * @param[in] o Oggetto da confrontare con l'istanza corrente.
     * @return true se i due oggetti Libro sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o.getClass() != this.getClass())
            return false;

        Libro l = (Libro) o;
        return this.codiceISBNLibro.equals(l.getCodiceISBNLibro());
    }

    /**
     * @brief Confronto del Libro corrente con un altro Libro.
     *        Aderisce al contratto del metodo compareTo() di Comparable<T>.
     * 
     *        I libri vengono confrontati in base all'ordine lessicografico del loro
     *        codice ISBN.
     * 
     * @param[in] l Libro da confrontare con l'istanza corrente.
     * @return Valore negativo, zero o positivo se il Libro corrente è
     *         rispettivamente minore, uguale o maggiore del Libro passato come
     *         parametro.
     */
    @Override
    public int compareTo(Libro l) {
        if (this.titolo.equals(l.getTitolo()))
            return this.codiceISBNLibro.compareTo(l.getCodiceISBNLibro());
        return this.titolo.compareTo(l.getTitolo());
    }
}