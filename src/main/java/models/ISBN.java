package models;

import java.io.Serializable;

import models.libri.Libro;

/**
 * @class ISBN
 * 
 * @brief Classe che rappresenta il codice identificativo associato ad un libro.
 *        Ad ogni \ref Libro è associato univocamente un codice ISBN.
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
     * @param[in] codiceISBN Stringa di caratteri che compongono il codice ISBN.
     */
    public ISBN(String codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    /**
     * @brief Metodo Getter della stringa di caratteri dell'ISBN.
     * @return La stringa di caratteri che compongono il codice ISBN.
     */
    public String getCodiceISBN() {
        return codiceISBN;
    }

    /**
     * @brief Verifica del formato dell'ISBN.
     *        Verifica se la stringa passata come parametro corrisponda o meno ad un
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
        // Se la somma è divisibile per 10 allora l'ISBN13 è valido, altrimenti no.
        return sommaPonderata % 10 == 0;
    }

    private static boolean verificaISBN10(String codiceISBN) {
        // Controlliamo che la stringa sia non nulla e composta esattamente da 10 cifre
        // numeriche OPPURE da 9 cifre numeriche e il carattere 'X'.
        if (codiceISBN == null || !codiceISBN.matches("^\\d{9}[0-9X]$"))
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
        // Se la somma è divisibile per 11 allora l'ISBN10 è valido, altrimenti no.
        return sommaPonderata % 11 == 0;
    }

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

    @Override
    public int hashCode() {
        return codiceISBN != null ? codiceISBN.hashCode() : 0;
    }

    @Override
    public int compareTo(ISBN i) {
        return this.codiceISBN.compareTo(i.codiceISBN);
    }
}