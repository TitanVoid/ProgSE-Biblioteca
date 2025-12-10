package models.servizi;

import models.OggettoGiaPresenteException;

/**
 * @interface Archiviabile
 * @brief Interfaccia per la gestione di una collezione di elementi.
 *
 * @tparam E Tipo degli elementi della collezione.
 */
public interface Archiviabile<E>{

    /**
     * @brief Aggiunge un elemento all'archivio.
     * @param[in] elemento L'elemento da aggiungere.
     * @return La lista aggiornata degli elementi.
     */
    public void aggiungi(E elemento) throws OggettoGiaPresenteException;

    /**
     * @brief Rimuove un elemento dall'archivio.
     * @param[in] elemento L'elemento da rimuovere.
     * @return La lista aggiornata degli elementi.
     */
    public void rimuovi(E elemento);

    /**
     * @brief Modifica un elemento nell'archivio.
     * @param[in] originale L'elemento da modificare.
     * @param[in] modificato L'elemento modificato.
     * @return La lista aggiornata degli elementi.
     */
    public void modifica(E originale, E modificato);

}
