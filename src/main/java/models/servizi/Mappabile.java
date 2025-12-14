package models.servizi;

/**
 * @interface Mappabile
 * 
 * @brief Interfaccia generica per la gestione di un archivio di elementi
 *        mappabili.
 * 
 *        L'interfaccia definisce una serie di operazioni per accedere agli
 *        elementi di un archivio tramite una chiave univoca.
 *
 * @param <K> Tipo della chiave.
 * @param <V> Tipo degli elementi contenuti nell'archivio.
 */
public interface Mappabile<K, V> {

    /**
     * @brief Verifica se l'elemento con la chiave specificata esiste nell'archivio.
     * 
     * @param[in] key Chiave dell'elemento da cercare.
     * 
     * @return true se l'elemento che corrisponde a quella chiave esiste, false
     *         altrimenti.
     */
    public boolean esisteChiave(K key);

    /**
     * @brief Restituisce l'elemento associato ad una chiave.
     * 
     * @param[in] key Chiave dell'elemento da ottenere.
     * 
     * @return L'elemento associato alla chiave, oppure null se non presente
     *         nell'archivio.
     */
    public V ottieni(K key);

}
