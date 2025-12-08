package Models.Prestiti;

import Models.Servizi.Archiviabile;
import Models.Servizi.Filtro;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * @class Prestiti
 * @brief Gestisce una lista di prestiti.
 * @see Prestito
 * @implements Archiviabile<Prestito>
 * @see Archiviabile

 */
public class Prestiti implements Archiviabile<Prestito>, Serializable {

    private final List<Prestito> prestiti;

    /**
     * @brief Costruttore.
     */
    public Prestiti(){
        this.prestiti = new ArrayList<>();
    }

    /**
     * @brief Restituisce la lista dei prestiti.
     * @return Lista dei prestiti.
     */
    public List<Prestito> getPrestiti(){
        return prestiti;
    }
    /**
     * @brief Aggiunge un prestito alla collezione.
     * @param[in] prestito Prestito da aggiungere.
     */
    @Override
    public void aggiungi(Prestito prestito){}

    /**
     * @brief Rimuove un prestito dalla collezione.
     * @param[in] prestito Prestito da rimuovere.
     */
    @Override
    public void rimuovi(Prestito prestito){}

    /**
     * @brief Modifica un prestito esistente.
     * @param[in] originale Prestito da modificare.
     * @param[in] modificato Nuovo prestito modificato.
     */
    @Override
    public void modifica(Prestito originale, Prestito modificato){}

    /**
     * @brief Filtra i prestiti secondo un determinato filtro.
     * @param[in] filtro Filtro da applicare.
     * @return Lista dei prestiti che soddisfano il filtro.
     */
    public List<Prestito> filtra(Filtro filtro){
        return null;
    }

}