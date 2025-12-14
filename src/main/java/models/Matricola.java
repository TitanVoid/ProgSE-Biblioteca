package models;

import java.io.Serializable;

import models.utenti.Utente;

/**
 * @class Matricola
 *
 * @brief Classe che rappresenta il codice identificativo associato ad un utente
 *        della biblioteca.
 * 
 *        Ad ogni Utente è associata univocamente una matricola universitaria.
 *        Una matricola è composta esattamente da 10 cifre numeriche.
 *
 * @see Utente
 */
public class Matricola implements Serializable, Comparable<Matricola> {

    private final String matricola;

    /**
     * @brief Costruttore.
     *
     *        Costruisce un nuovo oggetto Matricola a partire dalla stringa fornita
     *        come
     *        parametro in ingresso.
     * 
     * @pre La stringa passata come parametro deve essere una matricola
     *      valida.
     * @post L'oggetto Matricola è creato e la stringa ad esso associata è
     *       immutabile.
     * 
     * 
     * @param[in] matricola Stringa di caratteri che compone la matricola.
     *
     */
    public Matricola(String matricola) {
        assert (verificaMatricola(matricola));
        this.matricola = matricola;
    }

    /**
     * @brief Metodo Getter per la stringa di caratteri della matricola.
     *
     * @return La stringa di caratteri che compone la matricola.
     *
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     * @brief Verifica del formato di una matricola.
     * 
     *        Verifica se la stringa passata come parametro corrisponde o meno ad
     *        una matricola valida.
     *        Una matricola è valida se è composta esattamente da 10 cifre
     *        numeriche.
     * 
     * @param[in] matricola Stringa di caratteri da verificare.
     * @return true se la stringa corrisponde ad una matricola valida, false
     *         altrimenti.
     */
    public static boolean verificaMatricola(String matricola) {
        if (matricola == null || !matricola.matches("\\d{10}"))
            return false;
        return true;
    }

    /**
     * @brief Verifica dell'uguaglianza tra un oggetto e l'istanza corrente.
     *        Aderisce al contratto del metodo equals() di Object.
     * 
     *        Due oggetti Matricola si dicono uguali se le stringhe ad essi
     *        associate sono uguali.
     * @param o Oggetto da confrontare.
     * @return true se i due oggetti Matricola sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o.getClass() != this.getClass())
            return false;

        Matricola m = (Matricola) o;
        return this.matricola.equals(m.getMatricola());
    }

    /**
     * @brief Hashcode per l'oggetto Matricola.
     *        Aderisce al contratto del metodo hashCode() di Object.
     * 
     * @return L'hashcode associato all'oggetto Matricola.
     */
    @Override
    public int hashCode() {
        return matricola != null ? matricola.hashCode() : 0;
    }

    /**
     * @brief Confronto della Matricola corrente con un altra Matricola.
     *        Aderisce al contratto del metodo compareTo() di Comparable<T>.
     * 
     *        Le matricole vengono confrontate in base all'ordine lessicografico
     *        della stringa di caratteri ad esse associata.
     * 
     * @param m Matricola da confrontare con l'istanza corrente.
     * @return Valore negativo, zero o positivo se la Matricola corrente è
     *         rispettivamente minore, uguale o maggiore della Matricola passata
     *         come parametro.
     */
    @Override
    public int compareTo(Matricola m) {
        return this.matricola.compareTo(m.getMatricola());
    }

}