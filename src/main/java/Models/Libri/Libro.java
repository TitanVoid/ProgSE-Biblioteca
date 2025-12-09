package Models.Libri;

import Models.ISBN;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @class Libro
 * @brief Classe che rappresenta un libro e la verifica.
 *@see Autore
 *@see ISBN
 */
public class Libro implements Comparable<Libro>, Serializable {

    private String titolo;
    private final List<Autore> autori;
    private final ISBN codiceISBNLibro;
    private int annoPubblicazione;
    private int copieDisponibili;

    /**
     * @brief Costruttore.
     * @param[in] titolo Titolo del libro.
     * @param[in] annoPubblicazione Anno di pubblicazione.
     * @param[in] codiceISBNLibro Codice ISBN.
     * @param[in] copieDisponibili Numero di copie disponibili.
     * @param[in] autori Lista degli autori.
     */
    public Libro(String titolo, int annoPubblicazione, ISBN codiceISBNLibro, int copieDisponibili, List<Autore> autori) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.codiceISBNLibro = codiceISBNLibro;
        this.copieDisponibili = copieDisponibili;
        this.autori = autori;
    }

    /**
     * @brief Restituisce il titolo del libro.
     * @return Titolo del libro.
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @brief Restituisce l'anno di pubblicazione.
     * @return Anno di pubblicazione.
     */
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }


    /**
     * @brief Restituisce il codice ISBN del libro.
     * @return Codice ISBN del libro.
     */
    public ISBN getCodiceISBNLibro(){
        return codiceISBNLibro;
    }

    /**
     * @brief Restituisce il numero di copie disponibili.
     * @return Numero di copie disponibili.
     */
    public int getCopieDisponibili() {
        return copieDisponibili;
    }

    /**
     * @brief Restituisce la lista degli autori.
     * @return Lista degli autori.
     */
    public List<Autore> getAutori() {
        return autori;
    }

    /**
     * @brief Imposta il titolo del libro.
     * @param[in] titolo Nuovo titolo.
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @brief Imposta l'anno di pubblicazione.
     * @param[in] annoPubblicazione Nuovo anno di pubblicazione.
     */
    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    /**
     * @brief Imposta il numero di copie disponibili.
     * @param[in] copieDisponibili Nuovo numero di copie disponibili.
     */
    public void setCopieDisponibili(int copieDisponibili) {
        this.copieDisponibili = copieDisponibili;
    }



    /**
     * @brief Aggiunge un autore alla lista degli autori.
     * @param[in] autore Autore da aggiungere.
     */
    public void aggiungAutore(Autore autore) {
        autori.add(autore);
    }

    /**
     * @brief Rimuove un autore dalla lista degli autori.
     * @param[in] autore Autore da rimuovere.
     */
    public void rimuoviAutore(Autore autore) {
        autori.remove(autore);
    }


    /**
     * @brief Verifica il formato dei dati di un libro.
     * @param[in] autori Lista degli autori.
     * @param[in] titolo Titolo del libro.
     * @param[in] annoPubblicazione Anno di pubblicazione.
     * @param[in] isbn Codice ISBN.
     * @param[in] copieDisponibili Numero di copie disponibili.
     * @return true se il formato dei dati sono corretti, false altrimenti.
     */
    public static boolean verificaLibro(List<Autore> autori, String titolo, int annoPubblicazione, ISBN codiceISBNLibro, int copieDisponibili) {
        Libro l = new Libro(titolo, annoPubblicazione, codiceISBNLibro, copieDisponibili, autori);
        
        if(l.getTitolo().length() > 100){
            return false;
        }
        
        if(l.getAnnoPubblicazione() < 0 && l.getAnnoPubblicazione() > LocalDate.now().getYear()){
            return false;
        }
        
        if(!l.getCodiceISBNLibro().verificaISBN(l.getCodiceISBNLibro().getCodiceISBN())){
            return false;
        }
        
        if(l.getCopieDisponibili() < 0 || l.getCopieDisponibili() > 100){
            return false;
        }
        
        for(Autore a : l.getAutori()){
            if(!a.verificaNome(a.getNome()) && !a.verificaCognome(a.getCognome())){
                return false;
            }
        }
        return true;
    }

    /**
     * @brief Calcola l'hash code del libro.
     * @return Valore hashcode.
     */
    @Override
    public int hashCode() {
        return this.codiceISBNLibro == null ? 0 : codiceISBNLibro.hashCode() * 31;
    }

    /**
     * @brief Verifica l'uguaglianza tra questo libro e un altro oggetto.
     * @param o Oggetto da confrontare.
     * @return true se sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(o.getClass() != this.getClass()) return false;
        
        Libro l = (Libro) o;
        return this.codiceISBNLibro.equals(l.getCodiceISBNLibro());
    }

    /**
     * @brief Confronta questo libro con un altro libro.
     * @param l Libro da confrontare.
     * @return 0 se sono uguali, valore negativo se questo libro precede l, positivo altrimenti.
     */
    @Override
    public int compareTo(Libro l) {
        if(this.titolo.equals(l.getTitolo())) return this.codiceISBNLibro.compareTo(l.getCodiceISBNLibro());
        return this.codiceISBNLibro.compareTo(l.getCodiceISBNLibro());
    }

}