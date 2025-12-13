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
 * @brief Classe che rappresenta un insieme di prestiti.
 * @see Prestito
 * @implements Archiviabile<Prestito>
 * @see Archiviabile
 * 
 * 
 */
public class Prestiti implements Archiviabile<Prestito>, Serializable {

    private final List<Prestito> prestiti;

    /**
     * @brief Costruttore.
     */
    public Prestiti() {
        prestiti = new ArrayList<>();
    }

    /**
     * @brief Restituisce la lista dei prestiti.
     * @return Lista dei prestiti.
     */
    public List<Prestito> getListaPrestiti() {
        return prestiti;
    }

    /**
     * @brief Aggiunge un prestito alla lista.
     * @param[in] prestito Prestito da aggiungere.
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
     * @brief Estingue un prestito.
     * @param[in] prestito Prestito da estinguere.
     */
    @Override
    public void rimuovi(Prestito prestito) {
        int index = Collections.binarySearch(prestiti, prestito);
        prestiti.get(index).setDataRestituzione(LocalDate.now());
    }

    /**
     * @brief Modifica un prestito esistente.
     * @param[in] originale Prestito senza modifiche.
     * @param[in] modificato Nuovo prestito modificato.
     */
    @Override
    public void modifica(Prestito originale, Prestito modificato) {
        int index = Collections.binarySearch(prestiti, originale);
        prestiti.remove(index);
        aggiungi(modificato);
    }

    /**
     * @brief Filtra i prestiti secondo un determinato criterio di filtraggio.
     * @param[in] filtro Filtro da applicare.
     * @return Lista dei prestiti che soddisfano il criterio di filtraggio
     *         specificato.
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