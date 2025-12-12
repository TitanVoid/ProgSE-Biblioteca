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
        if (codiceISBN == null || !codiceISBN.matches("\\d{13}") || codiceISBN.length() != 13)
            return false;

        int sommaPonderata = 0;
        for (int i = 0; i < 12; i++) {
            // - '0' lo converte nel valore numerico (es. '7' - '0' = 7).
            int cifra = codiceISBN.charAt(i) - '0';
            // Posizioni dispari (1, 3, 5, ...) -> Moltiplica per 1
            if ((i + 1) % 2 != 0) {
                sommaPonderata += cifra * 1;
                // Posizioni pari (2, 4, 6, ...) -> Moltiplica per 3
            } else {
                sommaPonderata += cifra * 3;
            }
        }

        int resto = sommaPonderata % 10;
        int cifraControlloAttesa = (10 - resto) % 10;
        int cifraControlloEffettiva = codiceISBN.charAt(12) - '0';
        // Confronta la cifra calcolata con l'ultima cifra dell'ISBN fornito.
        return cifraControlloAttesa == cifraControlloEffettiva;
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