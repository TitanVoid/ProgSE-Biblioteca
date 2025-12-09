package Models.Libri;

import Models.ISBN;
import Models.Servizi.Archiviabile;
import Models.Servizi.Mappabile;

import java.io.Serializable;
import java.util.ArrayList;
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

    private final Map<ISBN, Libro> ISBN;
    private final List<Libro> libri;

    /**
     * @brief Costruttore.
     * Inizializza la lista dei libri e la mappa degli ISBN.
     */
    public Libri() {
        this.libri = new ArrayList<>();
        this.ISBN = new HashMap<>();
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
        return null;
    }

    /**
     * @brief Aggiunge un libro alla collezione.
     * @param[in] libro Libro da aggiungere.
     */
    @Override
    public void aggiungi(Libro libro) {}

    /**
     * @brief Rimuove un libro dalla collezione.
     * @param[in] libro Libro da rimuovere.
     */
    @Override
    public void rimuovi(Libro libro) {}

    /**
     * @brief Modifica un libro esistente.
     * @param[in] originale Libro da modificare.
     * @param[in] modificato Nuovo libro modificato.
     */
    @Override
    public void modifica(Libro originale, Libro modificato) {}

    /**
     * @brief Ottiene un libro tramite ISBN.
     * @param[in] chiave ISBN del libro.
     * @return Libro associato all'ISBN, null altrimenti.
     */
    @Override
    public Libro ottieni(ISBN chiave) {
        return null;
    }

    /**
     * @brief Verifica se esiste un libro con un determinato ISBN.
     * @param[in] chiave ISBN da verificare.
     * @return true se esiste, false altrimenti.
     */
    @Override
    public boolean esisteChiave(ISBN chiave){
        return false;
    }

}