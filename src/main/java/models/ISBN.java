package models;

import java.io.Serializable;

import models.libri.Libro;

/**
 * @class ISBN
 * 
 * @brief Classe che rappresenta il codice identificativo associato ad un libro.
 * 
 *        Ad ogni Libro è associato univocamente un codice ISBN.
 *        Un codice ISBN può essere composto da 10 oppure 13 cifre, e il proprio
 *        formato deve essere conforme allo standard internazionale ISO 2108.
 * @see Libro
 */
public class ISBN implements Serializable, Comparable<ISBN> {

    private final String codiceISBN;

    /**
     * @brief Costruttore.
     *        Costruisce un nuovo oggetto ISBN a partire dalla stringa fornita come
     *        parametro in ingresso.
     * 
     * @pre La stringa passata come parametro deve essere un codice ISBN
     *      valido.
     * @post L'oggetto ISBN è creato e il codice ad esso associato è immutabile.
     * 
     * @param[in] codiceISBN Stringa di caratteri che compongono il codice ISBN.
     */
    public ISBN(String codiceISBN) {
        assert (verificaISBN(codiceISBN));
        this.codiceISBN = codiceISBN;
    }

    /**
     * @brief Metodo Getter per la stringa di caratteri del codice ISBN.
     * @return La stringa di caratteri che compongono il codice ISBN.
     */
    public String getCodiceISBN() {
        return codiceISBN;
    }

    /**
     * @brief Verifica del formato dell'ISBN.
     *        Verifica se la stringa passata come parametro corrisponde o meno ad un
     *        codice ISBN valido, sulla base degli standard internazionali definiti
     *        all'interno del documento ISO 2108.
     * @param[in] codiceISBN Stringa di caratteri da verificare.
     * @return true se la stringa corrisponde ad un codice
     *         ISBN valido, false altrimenti.
     */
    public static boolean verificaISBN(String codiceISBN) {
        if (codiceISBN.length() == 13)
            return verificaISBN13(codiceISBN);
        else if (codiceISBN.length() == 10)
            return verificaISBN10(codiceISBN);
        else
            return false;

    }

    /**
     * @brief Verifica la validità di un codice ISBN-13.
     *        Verifica se la stringa passata come parametro corrisponde o meno ad un
     *        codice ISBN-13 valido.
     *        Affinché lo sia, la stringa deve:
     *        - essere lunga esattamente 13 caratteri;
     *        - essere composta soltanto da caratteri numerici.
     *        Inoltre, la somma delle sue cifre moltiplicate per un determinato peso
     *        (le cifre di posizione pari hanno peso pari a 3, quelle di posizione
     *        dispari pari ad 1) deve essere divisibile per 10.
     * @param[in] codiceISBN Stringa di caratteri da verificare.
     * @return true se la stringa corrisponde ad un codice
     *         ISBN-13 valido, false altrimenti.
     */
    private static boolean verificaISBN13(String codiceISBN) {
        // Controlliamo che la stringa sia non nulla e composta esattamente da 13 cifre
        // numeriche.
        if (codiceISBN == null || !codiceISBN.matches("^\\d{13}$"))
            return false;

        int sommaPonderata = 0;
        for (int i = 0; i < 13; i++) {
            // Convertiamo ogni carattere nel suo corrispondente valore numerico.
            int cifra = codiceISBN.charAt(i) - '0';
            // Moltiplichiamo le cifre di indice dispari per 3, mentre quelle di indice pari
            // restano inalterate.
            if (i % 2 != 0)
                cifra = cifra * 3;

            sommaPonderata += cifra;

        }
        // Se la somma è divisibile per 10 allora l'ISBN-13 è valido, altrimenti no.
        return sommaPonderata % 10 == 0;
    }

    /**
     * @brief Verifica la validità di un codice ISBN-10.
     *        Verifica se la stringa passata come parametro corrisponde o meno ad un
     *        codice ISBN-10 valido.
     *        Affinché lo sia, la stringa deve:
     *        - essere lunga 10 caratteri;
     *        - essere composta soltanto da caratteri numerici oppure avere 'X' come
     *        ultimo carattere.
     *        Inoltre, la somma delle sue cifre moltiplicate per un determinato peso
     *        (la prima cifra ha peso pari a 10, la seconda pari a 9, e così via...)
     *        deve essere divisibile per 11.
     * @param[in] codiceISBN Stringa di caratteri da verificare.
     * @return true se la stringa corrisponde ad un codice
     *         ISBN-10 valido, false altrimenti.
     */
    private static boolean verificaISBN10(String codiceISBN) {
        // Controlliamo che la stringa sia non nulla e composta esattamente da 10 cifre
        // numeriche OPPURE da 9 cifre numeriche e il carattere 'X'.
        if (codiceISBN == null)
            return false;

        if (!codiceISBN.matches("^\\d{10}$") && !codiceISBN.matches("^\\d{9}X$"))
            return false;

        int sommaPonderata = 0;
        for (int i = 0; i < 10; i++) {
            int cifra;
            char carattere = codiceISBN.charAt(i);

            // Convertiamo ogni carattere nel suo corrispondente valore numerico.
            // Se l'ultimo carattere è 'X', il suo valore numerico corrispondente sarà 10.
            if (i == 9 && carattere == 'X')
                cifra = 10;
            else
                cifra = carattere - '0';

            // Calcoliamo il peso di ogni cifra.
            int peso = 10 - i;

            // Sommiamo tutte le cifre moltiplicate per il proprio peso.
            sommaPonderata += cifra * peso;

        }
        // Se la somma è divisibile per 11 allora l'ISBN-10 è valido, altrimenti no.
        return sommaPonderata % 11 == 0;
    }

    /**
     * @brief Verifica dell'uguaglianza tra un oggetto e l'istanza corrente.
     *        Aderisce al contratto del metodo equals() di Object.
     * 
     *        Due ISBN si dicono uguali se le stringhe ad essi associate sono
     *        uguali.
     * 
     * @param[in] o Oggetto da confrontare con l'istanza corrente.
     * @return true se i due ISBN sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o.getClass() != this.getClass())
            return false;

        ISBN i = (ISBN) o;
        return this.codiceISBN.equals(i.codiceISBN);
    }

    /**
     * @brief Hashcode per l'oggetto ISBN.
     *        Aderisce al contratto del metodo hashCode() di Object.
     * 
     * @return L'hashcode associato all'oggetto ISBN.
     */
    @Override
    public int hashCode() {
        return codiceISBN == null ? 0 : codiceISBN.hashCode() * 31;
    }

    /**
     * @brief Confronto dell'ISBN corrente con un altro ISBN. Aderisce al contratto
     *        del metodo compareTo() di Comparable<T>.
     * 
     *        Gli ISBN vengono confrontati in base all'ordine
     *        lessicografico della stringa di caratteri ad essi associata.
     * 
     * @param[in] i ISBN da confrontare con l'istanza corrente.
     * @return Valore negativo, zero o positivo se l'ISBN corrente è
     *         rispettivamente minore, uguale o maggiore dell'ISBN passato come
     *         parametro.
     */
    @Override
    public int compareTo(ISBN i) {
        return this.codiceISBN.compareTo(i.codiceISBN);
    }
}