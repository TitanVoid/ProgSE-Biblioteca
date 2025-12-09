package Models.Libri;

import Models.ISBN;
import Models.Servizi.Archiviabile;
import Models.Servizi.Mappabile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @class Libri
 * @brief Gestisce una lista e una mappa di libri.
 * @see Libro
 * @implements Mappabile<ISBN, Libro>
 * @see Mappabile
 * @implements Archiviabile<Libro>
 * @see Archiviabile
 */
public class Libri implements Mappabile<ISBN, Libro>, Archiviabile<Libro>, Serializable {

    private final Map<ISBN, Libro> chiaviISBN;
    private final List<Libro> libri;

    /**
     * @brief Costruttore.
     * Inizializza la lista dei libri e la mappa degli ISBN.
     */
    public Libri() {
        this.libri = new ArrayList<>();
        this.chiaviISBN = new HashMap<>();
    }

    /**
     * @brief Restituisce la lista dei libri.
     * @return Lista dei libri.
     */
    public List<Libro> getLibri() {
        return libri;
    }

    /**
     * @brief Ricerca libri in base a un criterio.
     * @param[in] input Criterio di ricerca.
     * @return Lista di libri che corrispondono al criterio di ricerca.
     */
    public List<Libro> ricerca(String input) {
        List<Libro> lis = new ArrayList<>();
        for(Libro l : libri){
            if(l.getTitolo().matches(input)){
                lis.add(l);
            }
        }
        return lis;
    }

    /**
     * @brief Aggiunge un libro alla collezione.
     * @param[in] libro Libro da aggiungere.
     */
    @Override
    public void aggiungi(Libro libro) {
        if(!this.esisteChiave(libro.getCodiceISBNLibro())){
            int i = Collections.binarySearch(libri, libro);
            if(i < 0){
                i = - i - 1;
                libri.add(i, libro);
            } 
        }
    }

    /**
     * @brief Rimuove un libro dalla collezione.
     * @param[in] libro Libro da rimuovere.
     */
    @Override
    public void rimuovi(Libro libro) {
        ISBN i = libro.getCodiceISBNLibro();
        libri.remove(libro);
        chiaviISBN.remove(i);
    }

    /**
     * @brief Modifica un libro esistente.
     * @param[in] originale Libro da modificare.
     * @param[in] modificato Nuovo libro modificato.
     */
    @Override
    public void modifica(Libro originale, Libro modificato){
        libri.remove(originale);
        this.aggiungi(modificato);
    }

    /**
     * @brief Ottiene un libro tramite ISBN.
     * @param[in] chiave ISBN del libro.
     * @return Libro associato all'ISBN, null altrimenti.
     */
    @Override
    public Libro ottieni(ISBN chiave) {
        if(esisteChiave(chiave)) return chiaviISBN.get(chiave);
        return null;
    }

    /**
     * @brief Verifica se esiste un libro con un determinato ISBN.
     * @param[in] chiave ISBN da verificare.
     * @return true se esiste, false altrimenti.
     */
    @Override
    public boolean esisteChiave(ISBN chiave){
        return chiaviISBN.containsKey(chiave);
    }

}