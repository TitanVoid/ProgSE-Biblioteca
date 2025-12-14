package models.servizi;

/**
 * @class Filtro
 * 
 * @brief Classe che definisce le tipologie di filtro disponibili.
 * 
 *        Utilizzata in Prestiti, permette di filtrare l'archivio dei prestiti
 *        secondo tre criteri di visualizzazione:
 * 
 *        - TUTTI: Filtro che mostra tutti i prestiti presenti nell'archivio;
 *        - ATTIVI: Filtro che mostra soltanto i prestiti attivi;
 *        - CONCLUSI: Filtro che mostra soltanto i prestiti conclusi.
 * 
 * @see Prestiti
 */
public enum Filtro {
    TUTTI,
    ATTIVI,
    CONCLUSI
}