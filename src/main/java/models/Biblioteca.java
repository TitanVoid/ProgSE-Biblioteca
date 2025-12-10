package models;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import models.libri.Libri;
import models.prestiti.Prestiti;
import models.utenti.Utenti;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.prestiti = new Prestiti();
        this.utenti = new Utenti();
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
     * @param[in] nomeFile Nome del file su cui salvare lo stato.
     */

    public void salvaBibliotecaObj(String nomeFile) throws IOException {
        // Implementazione del salvataggio dello stato della biblioteca su file
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(nomeFile)))){
            oos.writeObject(this);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @brief Carica lo stato della biblioteca da file.
     * @param[in] nomeFile Nome del file da cui caricare lo stato.
     * @return Istanza di Biblioteca caricata dal file.
     */
    public static Biblioteca leggiBibliotecaObj(String nomeFile) throws IOException{
        // Implementazione del caricamento dello stato della biblioteca dal file
        Biblioteca x = null;
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomeFile)))){
            x = (Biblioteca) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

}