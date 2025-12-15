package models.servizi;

import models.OggettoGiaPresenteException;

/**
 * @interface Archiviabile
 * 
 * @brief Interfaccia generica per la gestione di un archivio di elementi.
 * 
 *        L'interfaccia definisce le seguenti operazioni per la manipolazione di
 *        un archivio di elementi: aggiunta, rimozione e modifica.
 * 
 * @param <E> Tipo degli elementi contenuti nell'archivio.
 */
public interface Archiviabile<E> {

    /**
     * @brief Aggiunge un nuovo elemento all'archivio.
     * 
     * @post L'archivio contiene l'elemento passato come parametro.
     * 
     * @param[in] elemento Elemento da aggiungere.
     * 
     * @throws OggettoGiaPresenteException nel caso in cui si provi ad aggiungere un
     *                                     elemento già presente nell'archivio.
     */
    public void aggiungi(E elemento) throws OggettoGiaPresenteException;

    /**
     * @brief Rimuove un elemento dall'archivio.
     * 
     *        Questo metodo segna un elemento come rimosso dall'archivio.
     *        In base alla collezione da gestire, può trattarsi sia di una rimozione
     *        logica che di una fisica.
     * 
     * @post L'elemento passato come parametro viene rimosso con successo
     *       dall'archivio.
     * 
     * @param[in] elemento Elemento da rimuovere.
     */
    public void rimuovi(E elemento);

    /**
     * @brief Modifica un elemento presente nell'archivio.
     * 
     * @post L'elemento viene aggiornato all'interno dell'archivio con successo.
     * 
     * @param[in] originale Elemento da modificare.
     * @param[in] modificato Elemento modificato.
     */
    public void modifica(E originale, E modificato);

}
