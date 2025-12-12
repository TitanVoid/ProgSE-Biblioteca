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
 * @brief Gestisce una lista e una mappa di utenti.
 * @see Utente
 * @implements Archiviabile<Utente></Utente>
 * @see Archiviabile
 * @implements Mappabile<String, Utente>
 * @see Mappabile
 */
public class Utenti implements Archiviabile<Utente>, Mappabile<Matricola, Utente>, Serializable {

    private final Map<Matricola, Utente> chiaviMatricole;
    private final List<Utente> utenti;

    /**
     * @brief Costruttore.
     */
    public Utenti() {
        this.utenti = new ArrayList<>();
        this.chiaviMatricole = new HashMap<>();
    }

    /**
     * @brief Restituisce la lista degli utenti.
     * @return Lista degli utenti.
     */
    public List<Utente> getListaUtenti() {
        return utenti;
    }

    /**
     * @brief Ricerca uno o più utenti in base al loro cognome e alla loro
     *        matricola, verificando se almeno uno di questi due campi contenga una
     *        determinata stringa.
     * @param[in] input Criterio di ricerca.
     * @return Lista di utenti che soddisfano il criterio di ricerca.
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
     * @brief Aggiunge un utente alla lista.
     * @param[in] utente Utente da aggiungere.
     */
    @Override
    public void aggiungi(Utente utente) throws UtenteGiaPresenteException {
        if (esisteChiave(utente.getMatricolaUtente())) {
            throw new UtenteGiaPresenteException("L'utente è già presente nella lista.");
        } else {
            chiaviMatricole.put(utente.getMatricolaUtente(), utente);
            int index = Collections.binarySearch(utenti, utente);

            if (index < 0) {
                index = -index - 1;
            }
            utenti.add(index, utente);
        }
    }

    /**
     * @brief Rimuove un utente dalla lista.
     * @param[in] utente Utente da rimuovere.
     */
    @Override
    public void rimuovi(Utente utente) {
        chiaviMatricole.remove(utente.getMatricolaUtente(), utente);
        int index = Collections.binarySearch(utenti, utente);
        utenti.remove(index);
    }

    /**
     * @brief Modifica un utente esistente.
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
     * @brief Verifica se esiste l'utente che corrisponde a una determinata
     *        matricola.
     * @param[in] chiave Matricola del utente.
     * @return true se esiste, false altrimenti.
     */
    @Override
    public boolean esisteChiave(Matricola chiave) {
        return chiaviMatricole.containsKey(chiave);
    }

    /**
     * @brief Ottiene un utente tramite la sua matricola.
     * @param chiave Matricola dell'utente che si vuole ottenere.
     * @return Utente associato alla matricola, null altrimenti.
     */
    @Override
    public Utente ottieni(Matricola chiave) {
        return chiaviMatricole.get(chiave);
    }
}