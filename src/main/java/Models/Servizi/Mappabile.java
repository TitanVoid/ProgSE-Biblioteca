package Models.Servizi;

/**
 * @interface Mappabile
 * @brief Interfaccia per ///// .
 *
 * @tparam K Tipo della chiave.
 * @tparam V Tipo del valore.
 */
public interface Mappabile<K, V> {

    /**
     * @brief Verifica se una chiave esiste nell'archivio.
     * @param[in] key La chiave da verificare.
     * @return true se la chiave esiste, false altrimenti.
     */
    public boolean esisteChiave(K key);

    /**
     * @brief Restituisce l'elemento associato alla chiave.
     * @param[in] key La chiave dell'elemento da recuperare.
     * @return L'elemento associato alla chiave, null se non esiste.
     */
    public V ottieni(K key);

}