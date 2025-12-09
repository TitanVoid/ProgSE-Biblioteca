package Models;

import Models.Libri.Libri;
import Models.Prestiti.Prestiti;
import Models.Utenti.Utenti;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @class Biblioteca
 * @brief Classe che aggrega le collezioni di prestiti, libri e utenti.
 *
 * Fornisce metodi per il salvataggio e il caricamento dello stato della biblioteca da file.
 */
public class Biblioteca implements Serializable {

    private final Prestiti prestiti;
    private final Libri libri;
    private final Utenti utenti;

    /**
     * @brief Costruttore.
     */

    public Biblioteca() {
        this.libri = new Libri();
        this.prestiti = null;
        this.utenti = null;
        try {
            leggiBibliotecaObj ("biblioteca.obj");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Prestiti getPrestiti() {
        return prestiti;
    }

    public Libri getLibri() {
        return libri;
    }

    public Utenti getUtenti() {
        return utenti;
    }

    /**
     * @brief Salva lo stato della biblioteca su file.
     * @param[in] fileName Nome del file su cui salvare lo stato.
     */

    public void salvaBibliotecaObj(String nomeFile) throws IOException {
        // Implementazione del salvataggio dello stato della biblioteca su file

    }

    /**
     * @brief Carica lo stato della biblioteca da file.
     * @param[in] fileName Nome del file da cui caricare lo stato.
     * @return Istanza di Biblioteca caricata dal file.
     */
    public static Biblioteca leggiBibliotecaObj(String nomeFile) throws IOException{
        // Implementazione del caricamento dello stato della biblioteca dal file
        return null;
    }

}