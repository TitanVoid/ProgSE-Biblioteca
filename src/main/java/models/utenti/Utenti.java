package models.utenti;

import models.Matricola;
import models.servizi.Archiviabile;
import models.servizi.Mappabile;

import java.io.Serializable;
import java.util.ArrayList;
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

    private final Map<Matricola, Utente> matricole;
    private final List<Utente> utenti;

    /**
     * @brief Costruttore.
     */
    public Utenti() {
        this.utenti = new ArrayList<>();
        this.matricole = new HashMap<>();
    }

    /**
     * @brief Restituisce la lista degli utenti.
     * @return Lista degli utenti.
     */
    public List<Utente> getUtenti() {
        return utenti;
    }

    /**
     * @brief Ricerca utenti in base a un input.
     * @param[in] input Criterio di ricerca.
     * @return Lista di utenti che corrispondono al criterio di ricerca.
     */
    public List<Utente> ricerca(String input) {
        return null;
    }

    /**
     * @brief Aggiunge un utente alla collezione.
     * @param[in] utente Utente da aggiungere.
     */
    @Override
    public void aggiungi(Utente utente) {}

    /**
     * @brief Rimuove un utente dalla collezione.
     * @param[in] utente Utente da rimuovere.
     */
    @Override
    public void rimuovi(Utente utente) {}

    /**
     * @brief Modifica un utente esistente.
     * @param[in] originale Utente da modificare.
     * @param[in] modificato Nuovo utente modificato.
     */
    @Override
    public void modifica(Utente originale, Utente modificato) {}

    /**
     * @briefVerifica se esiste un uetnte con un determinata matricola.
     * @param[in] chiave Matricola del utente.
     * @return true se esiste, false altrimenti.
     */
    @Override
    public boolean esisteChiave(Matricola chiave){
        return false;
    }

    /**
     * @brief Ottiene un utente tramite matricola.
     * @param chiave Matricola dell'utente.
     * @return Utente associato alla matricola, null altrimenti.
     */
    @Override
    public Utente ottieni(Matricola chiave) {
        return null;
    }
}