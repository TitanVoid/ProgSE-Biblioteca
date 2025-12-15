package models.libri;

import models.ISBN;
import models.servizi.Archiviabile;
import models.servizi.Mappabile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @class Libri
 * 
 * @brief Classe che rappresenta l'archivio dei libri della biblioteca.
 * 
 *        La classe definisce i seguenti metodi fondamentali per la gestione e
 *        manipolazione dell'archivio dei libri:
 *        - Aggiunta di un nuovo libro;
 *        - Rimozione di un libro;
 *        - Modifica di un libro;
 *        - Ricerca di uno o più libri.
 * 
 *        Le prime tre funzionalità vengono ereditate implementando
 *        l'interfaccia Archiviabile.
 *        La classe eredita inoltre i metodi di utilità messi a disposizione
 *        dall'interfaccia Mappabile.
 * 
 * @see Libro
 * @see Archiviabile
 * @see Mappabile
 */
public class Libri implements Mappabile<ISBN, Libro>, Archiviabile<Libro>, Serializable {

    private final Map<ISBN, Libro> chiaviISBN;
    private final List<Libro> libri;

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Libri, istanziando al suo interno due
     *        collezioni di oggetti di tipo Libro.
     *
     * @post L'oggetto Libri è creato.
     * @post La lista dei libri è vuota.
     */
    public Libri() {
        this.libri = new ArrayList<>();
        this.chiaviISBN = new HashMap<>();
    }

    /**
     * @brief Metodo Getter per la lista dei libri.
     * @return La lista dei libri.
     */
    public List<Libro> getListaLibri() {
        return libri;
    }

    /**
     * @brief Ricerca di uno o più libri.
     * 
     *        Questo metodo effettua la ricerca di uno o più libri in base al loro
     *        titolo, autore e al loro codice ISBN, verificando se almeno uno di
     *        questi campi contiene la stringa passata come parametro.
     * 
     * @param[in] input Stringa che rappresenta il criterio di ricerca.
     * 
     * @return La lista di libri che soddisfano il criterio di ricerca.
     */
    public List<Libro> ricercaLibri(String input) {
        List<Libro> lis = new ArrayList<>();
        String inputLowerCase = input.toLowerCase();
        for (Libro l : libri) {
            String titoloLowerCase = l.getTitolo().toLowerCase();
            String codiceISBNLibroLowerCase = l.getCodiceISBNLibro().getCodiceISBN().toLowerCase();
            if (titoloLowerCase.contains(inputLowerCase) || codiceISBNLibroLowerCase.contains(inputLowerCase)) {
                lis.add(l);
            } else {
                for (Autore a : l.getAutori()) {
                    String nomeLowerCase = a.getNome().toLowerCase();
                    String cognomeLowerCase = a.getCognome().toLowerCase();
                    if (nomeLowerCase.contains(inputLowerCase) || cognomeLowerCase.contains(inputLowerCase)) {
                        lis.add(l);
                    }
                }
            }
        }
        return lis;
    }

    /**
     * @brief Aggiunge un nuovo libro all'archivio.
     * 
     * @post L'archivio contiene il libro passato come parametro.
     * 
     * @param[in] libro Libro da aggiungere.
     * 
     * @throws LibroGiaPresenteException nel caso in cui si provi ad aggiungere un
     *                                   libro già presente nell'archivio.
     */
    @Override
    public void aggiungi(Libro libro) throws LibroGiaPresenteException {
        if (this.esisteChiave(libro.getCodiceISBNLibro())) {
            throw new LibroGiaPresenteException("Il libro è già presente nella lista");
        } else {
            int i = Collections.binarySearch(libri, libro);
            if (i < 0) {
                i = -i - 1;
                libri.add(i, libro);
                chiaviISBN.put(libro.getCodiceISBNLibro(), libro);
            }
        }
    }

    /**
     * @brief Modifica un libro presente nell'archivio.
     * 
     * @post Il libro viene aggiornato all'interno dell'archivio con successo.
     * 
     * @param[in] originale Libro da modificare.
     * @param[in] modificato Libro modificato.
     */
    @Override
    public void modifica(Libro originale, Libro modificato) {
        rimuovi(originale);
        try {
            this.aggiungi(modificato);
        } catch (LibroGiaPresenteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @brief Rimuove un libro dall'archivio.
     * 
     * @post Il libro passato come parametro viene rimosso con successo
     *       dall'archivio.
     * 
     * @param[in] libro Libro da rimuovere.
     */
    @Override
    public void rimuovi(Libro libro) {
        chiaviISBN.remove(libro.getCodiceISBNLibro());
        int index = Collections.binarySearch(libri, libro);
        libri.remove(index);
    }

    /**
     * @brief Verifica se il libro con il codice ISBN specificato esiste
     *        nell'archivio.
     * 
     * @param[in] chiave Codice ISBN del libro da cercare.
     * 
     * @return true se il libro che corrisponde a quel codice ISBN esiste, false
     *         altrimenti.
     */
    @Override
    public boolean esisteChiave(ISBN chiave) {
        return chiaviISBN.containsKey(chiave);
    }

    /**
     * @brief Restituisce il libro associato ad un codice ISBN.
     * 
     * @param[in] chiave Codice ISBN del libro da ottenere.
     * 
     * @return Il libro associato al codice ISBN, oppure null se non presente
     *         nell'archivio.
     */
    @Override
    public Libro ottieni(ISBN chiave) {
        return chiaviISBN.get(chiave);
    }

}