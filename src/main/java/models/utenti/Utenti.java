package models.utenti;

import models.Matricola;
import models.servizi.Archiviabile;
import models.servizi.Mappabile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @class Utenti
 * 
 * @brief Classe che rappresenta l'archivio degli utenti della biblioteca.
 * 
 *        La classe definisce i seguenti metodi fondamentali per la gestione e
 *        manipolazione dell'archivio degli utenti:
 *        - Aggiunta di un nuovo utente;
 *        - Rimozione di un utente;
 *        - Modifica di un utente;
 *        - Ricerca di uno o più utenti.
 * 
 *        Le prime tre funzionalità vengono ereditate implementando
 *        l'interfaccia Archiviabile.
 *        La classe eredita inoltre i metodi di utilità messi a disposizione
 *        dall'interfaccia Mappabile.
 * 
 * @see Utente
 * @see Archiviabile
 * @see Mappabile
 */
public class Utenti implements Archiviabile<Utente>, Mappabile<Matricola, Utente>, Serializable {

    private final Map<Matricola, Utente> chiaviMatricole;
    private final List<Utente> utenti;

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Utenti, istanziando al suo interno due
     *        collezioni di oggetti di tipo Utente.
     *
     * @post L'oggetto Utenti è creato.
     * @post La lista degli utenti è vuota.
     */
    public Utenti() {
        this.utenti = new ArrayList<>();
        this.chiaviMatricole = new HashMap<>();
    }

    /**
     * @brief Metodo Getter per la lista degli utenti.
     * @return La lista degli utenti.
     */
    public List<Utente> getListaUtenti() {
        return utenti;
    }

    /**
     * @brief Ricerca di uno o più utenti.
     * 
     *        Questo metodo effettua la ricerca di uno o più utenti in base al loro
     *        cognome e alla loro matricola, verificando se almeno uno di questi due
     *        campi contiene la stringa passata come parametro.
     * 
     * @param[in] input Stringa che rappresenta il criterio di ricerca.
     * 
     * @return La lista di utenti che soddisfano il criterio di ricerca.
     */
    public List<Utente> ricercaUtenti(String input) {
        List<Utente> l = new ArrayList<>();
        String inputLowerCase = input.toLowerCase();
        for (Utente u : utenti) {
            String cognomeLowerCase = u.getCognome().toLowerCase();
            String matricolaLowerCase = u.getMatricolaUtente().getMatricola().toLowerCase();
            if (cognomeLowerCase.contains(inputLowerCase) || matricolaLowerCase.contains(inputLowerCase)) {
                l.add(u);
            }
        }
        return l;
    }

    /**
     * @brief Aggiunge un nuovo utente all'archivio.
     * 
     * @post L'archivio contiene l'utente passato come parametro.
     * 
     * @param[in] utente Utente da aggiungere.
     * 
     * @throws UtenteGiaPresenteException nel caso in cui si provi ad aggiungere un
     *                                    utente già presente nell'archivio.
     */
    @Override
    public void aggiungi(Utente utente) throws UtenteGiaPresenteException {
        if (esisteChiave(utente.getMatricolaUtente())) {
            throw new UtenteGiaPresenteException("L'utente è già presente nella lista.");
        } else {
            int index = Collections.binarySearch(utenti, utente);
            if (index < 0) {
                index = -index - 1;
                utenti.add(index, utente);
                chiaviMatricole.put(utente.getMatricolaUtente(), utente);
            }
        }
    }

    /**
     * @brief Rimuove un utente dall'archivio.
     * 
     * @post L'utente passato come parametro viene rimosso con successo
     *       dall'archivio.
     * 
     * @param[in] utente Utente da rimuovere.
     */
    @Override
    public void rimuovi(Utente utente) {
        chiaviMatricole.remove(utente.getMatricolaUtente(), utente);
        int index = Collections.binarySearch(utenti, utente);
        utenti.remove(index);
    }

    /**
     * @brief Modifica un utente presente nell'archivio.
     * 
     * @post L'utente viene aggiornato all'interno dell'archivio con successo.
     * 
     * @param[in] originale Utente da modificare.
     * @param[in] modificato Utente modificato.
     */
    @Override
    public void modifica(Utente originale, Utente modificato) {
        rimuovi(originale);
        try {
            aggiungi(modificato);
        } catch (UtenteGiaPresenteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @brief Verifica se l'utente con la matricola specificata esiste
     *        nell'archivio.
     * 
     * @param[in] chiave Matricola dell'utente da cercare.
     * 
     * @return true se l'utente che corrisponde a quella matricola esiste, false
     *         altrimenti.
     */
    @Override
    public boolean esisteChiave(Matricola chiave) {
        return chiaviMatricole.containsKey(chiave);
    }

    /**
     * @brief Restituisce l'utente associato ad una matricola.
     * 
     * @param[in] chiave Matricola dell'utente da ottenere.
     * 
     * @return L'utente associato alla matricola, oppure null se non presente
     *         nell'archivio.
     */
    @Override
    public Utente ottieni(Matricola chiave) {
        return chiaviMatricole.get(chiave);
    }
}