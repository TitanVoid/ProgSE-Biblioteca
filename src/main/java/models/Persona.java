package models;

import java.io.Serializable;

/**
 * @class Persona
 * @brief Classe che incapsula le informazioni di una persona.
 *
 *        La classe rappresenta una generica persona con nome e cognome,
 *        fornendo metodi di accesso, modifica e verifica dei propri attributi.
 *        Inoltre, essa implementa l'interfaccia Comparable<Persona>,
 *        consentendo di confrontare tra di loro oggetti di tipo Persona sulla
 *        base del proprio cognome e, a parità di cognome, del nome.
 * 
 *        Si specializza nelle classi Utente ed Autore.
 * 
 * @see Utente
 * @see Autore
 */
public abstract class Persona implements Serializable, Comparable<Persona> {

    private String nome;
    private String cognome;

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Persona a partire dalle stringhe fornite
     *        come parametri in ingresso, che ne rappresentano il nome ed il
     *        cognome.
     * 
     * @post L'oggetto Persona è creato.
     *
     * @param[in] nome Stringa di caratteri che compone il nome.
     * @param[in] cognome Stringa di caratteri che compone il cognome.
     *
     */
    public Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    /**
     * @brief Metodo Getter per la stringa di caratteri del nome.
     * @return La stringa di caratteri che compone il nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @brief Metodo Getter per la stringa di caratteri del cognome.
     * @return La stringa di caratteri che compone il cognome.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @brief Metodo Setter per la stringa di caratteri del nome.
     *
     * @post L'oggetto Persona sarà modificato, impostando come suo nuovo nome la
     *       stringa nome.
     * 
     * @param[in] nome La stringa di caratteri da impostare come nuovo nome della
     *            Persona.
     *
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @brief Metodo Setter per la stringa di caratteri del cognome.
     *
     * @post L'oggetto Persona sarà modificato, impostando come suo nuovo cognome la
     *       stringa cognome.
     * 
     * @param[in] nome La stringa di caratteri da impostare come nuovo cognome della
     *            Persona.
     *
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @brief Verifica del formato di un nome.
     * 
     *        Verifica se la stringa passata come parametro corrisponde ad un
     *        nome valido.
     *        Un nome è valido se è composto da 3-25 cifre non decimali.
     * 
     * @param[in] nome Stringa di caratteri da verificare.
     * 
     * @return true se la stringa corrisponde ad un nome valido, false altrimenti.
     *
     */
    public static boolean verificaNome(String nome) {
        if (nome == null || !nome.matches("^\\D{3,25}$"))
            return false;
        return true;
    }

    /**
     * @brief Verifica del formato di un cognome.
     * 
     *        Verifica se la stringa passata come parametro corrisponde ad un
     *        cognome valido.
     *        Un cognome è valido se è composto da 3-25 cifre non decimali.
     *
     * @param[in] cognome Stringa di caratteri da verificare.
     *
     * @return true se la stringa corrisponde ad un cognome valido, false
     *         altrimenti.
     *
     */
    public static boolean verificaCognome(String cognome) {
        if (cognome == null || !cognome.matches("^\\D{3,25}$"))
            return false;
        return true;
    }

    /**
     * @brief Verifica dell'uguaglianza tra un oggetto e l'istanza corrente.
     *        Aderisce al contratto del metodo equals() di Object.
     * 
     *        Due oggetti Persona si dicono uguali se le stringhe corrispondenti a
     *        nome e cognome ad essi associate sono uguali.
     * 
     * @param[in] o Oggetto da confrontare con l'istanza corrente.
     * @return true se i due oggetti Persona sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (o.getClass() != this.getClass())
            return false;

        Persona p = (Persona) o;
        return this.cognome.equals(p.cognome);
    }

    /**
     * @brief Confronto della Persona corrente con un altra Persona.
     *        Aderisce al contratto del metodo compareTo() di Comparable<T>.
     * 
     *        Le persone vengono confrontate in base all'ordine lessicografico della
     *        stringa di caratteri che compone il loro cognome e, a parità di
     *        cognome, il loro nome.
     * 
     * @param[in] p Persona da confrontare con l'istanza corrente.
     * @return Valore negativo, zero o positivo se la Persona corrente è
     *         rispettivamente minore, uguale o maggiore della Persona passata come
     *         parametro.
     */
    @Override
    public int compareTo(Persona p) {
        if (this.cognome.equals(p.cognome)) {
            return this.nome.compareTo(p.nome);
        }
        return this.cognome.compareTo(p.cognome);
    }

    /**
     * @brief Rappresentazione testuale dell'oggetto corrente.
     *        Aderisce al contratto del metodo toString() di Object.
     * 
     * @return Una stringa che rappresenta l'oggetto.
     */
    @Override
    public String toString() {
        return this.nome + " " + this.cognome;
    }
}