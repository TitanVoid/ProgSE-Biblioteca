package models.prestiti;

import models.servizi.Archiviabile;
import models.servizi.Filtro;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @class Prestiti
 * 
 * @brief Classe che rappresenta l'archivio dei prestiti della biblioteca.
 * 
 *        La classe definisce i seguenti metodi per la gestione e manipolazione
 *        dell'archivio dei prestiti:
 *        - Aggiunta di un nuovo prestito;
 *        - Registrazione di una restituzione;
 *        - Estensione di un prestito (ovvero modifica della data di scadenza
 *        relativa ad un prestito);
 *        - Filtraggio dell'archivio.
 * 
 *        Le prime tre funzionalità vengono ereditate implementando
 *        l'interfaccia Archiviabile.
 * 
 * @see Prestito
 * @see Archiviabile
 */
public class Prestiti implements Archiviabile<Prestito>, Serializable {

    private final List<Prestito> prestiti;

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Prestiti, istanziando al suo interno una
     *        collezione di oggetti di tipo Prestito.
     *
     * @post L'oggetto Prestiti è creato.
     * @post La lista dei prestiti è vuota.
     */
    public Prestiti() {
        prestiti = new ArrayList<>();
    }

    /**
     * @brief Metodo Getter per la lista dei prestiti.
     * @return La lista dei prestiti.
     */
    public List<Prestito> getListaPrestiti() {
        return prestiti;
    }

    /**
     * @brief Aggiunge un nuovo prestito all'archivio.
     * 
     * @post L'archivio contiene il prestito passato come parametro.
     * 
     * @param[in] prestito Prestito da aggiungere.
     * 
     */
    @Override
    public void aggiungi(Prestito prestito) {
        int index = Collections.binarySearch(prestiti, prestito);
        if (index < 0) {
            index = -index - 1;
        }
        prestiti.add(index, prestito);
    }

    /**
     * @brief Estingue un prestito presente nell'archivio.
     * 
     *        Si tratta di un metodo che rimuove logicamente un prestito
     *        dall'archivio.
     *        Questo metodo cerca il prestito passato come parametro all'interno
     *        della lista e lo segna come restituito, modificandone la data di
     *        restituzione.
     * 
     * @post Il prestito viene estinto con successo.
     * 
     * @param[in] prestito Prestito da estinguere.
     */
    @Override
    public void rimuovi(Prestito prestito) {
        int index = Collections.binarySearch(prestiti, prestito);
        prestiti.get(index).setDataRestituzione(LocalDate.now());
    }

    /**
     * @brief Modifica un prestito presente nell'archivio.
     * 
     * @post Il prestito viene aggiornato all'interno dell'archivio con successo.
     * 
     * @param[in] originale Prestito da modificare.
     * @param[in] modificato Prestito modificato.
     */
    @Override
    public void modifica(Prestito originale, Prestito modificato) {
        int index = Collections.binarySearch(prestiti, originale);
        prestiti.remove(index);
        aggiungi(modificato);
    }

    /**
     * @brief Filtra i prestiti secondo un determinato criterio di filtraggio.
     * 
     * @param[in] filtro Filtro da applicare.
     * 
     * @return Lista dei prestiti che soddisfano il criterio di filtraggio
     *         specificato.
     * 
     * @see Filtro
     */
    public List<Prestito> filtra(Filtro filtro) {
        List<Prestito> l = new ArrayList<>();

        switch (filtro) {
            case TUTTI:
                l.addAll(prestiti);
                break;
            case ATTIVI:
                for (Prestito p : prestiti) {
                    if (p.getDataRestituzione() == null) {
                        l.add(p);
                    }
                }
                break;
            case CONCLUSI:
                for (Prestito p : prestiti) {
                    if (p.getDataRestituzione() != null) {
                        l.add(p);
                    }
                }
                break;
            default:
                System.out.println("Filtro sconosciuto.");
        }

        return l;
    }

}