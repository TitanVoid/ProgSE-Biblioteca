package models;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.libri.Libri;
import models.libri.Libro;
import models.prestiti.Prestiti;
import models.prestiti.Prestito;
import models.servizi.Archiviabile;
import models.utenti.Utente;
import models.utenti.Utenti;

/**
 * @class Biblioteca
 * 
 * @brief Classe che rappresenta una biblioteca che gestisce prestiti, libri ed
 *        utenti.
 * 
 *        Una biblioteca contiene al suo interno un insieme di prestiti, libri
 *        ed
 *        utenti.
 *
 *        La classe implementa l'interfaccia Serializable e fornisce metodi per
 *        il salvataggio e il caricamento del proprio stato, in modo tale da
 *        garantire la persistenza su file.
 * 
 * @see Archiviabile
 * @see Utente
 * @see Libro
 * @see Prestito
 */
public class Biblioteca implements Serializable {

    private final Prestiti prestiti;
    private final Libri libri;
    private final Utenti utenti;

    /**
     * @brief Costruttore.
     * 
     *        Costruisce un nuovo oggetto Biblioteca, istanziando i tre oggetti per
     *        la gestione degli archivi.
     * 
     * @post Gli oggetti Libri, Prestiti ed Utenti vengono costruiti correttamente,
     *       e al loro interno contengono liste vuote.
     */
    public Biblioteca() {
        this.libri = new Libri();
        this.prestiti = new Prestiti();
        this.utenti = new Utenti();
    }

    /**
     * @brief Metodo Getter per l'oggetto che gestisce i prestiti.
     * @return Il gestore dei prestiti contenuto nella biblioteca.
     */
    public Prestiti getPrestiti() {
        return prestiti;
    }

    /**
     * @brief Metodo Getter per l'oggetto che gestisce i libri.
     * @return Il gestore dei libri contenuto nella biblioteca.
     */
    public Libri getLibri() {
        return libri;
    }

    /**
     * @brief Metodo Getter per l'oggetto che gestisce gli utenti.
     * @return Il gestore degli utenti contenuto nella biblioteca.
     */
    public Utenti getUtenti() {
        return utenti;
    }

    /**
     * @brief Salva lo stato della biblioteca su file.
     *
     * @param[in] nomeFile Nome del file in cui salvare lo stato.
     * 
     * @post Lo stato dell'oggetto Biblioteca viene salvato correttamente sul file.
     * 
     * @throws IOException nel caso di un errore nella scrittura.
     */

    public void salvaBibliotecaObj(String nomeFile) throws IOException {
        // Implementazione del salvataggio dello stato della biblioteca su file
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(nomeFile)))) {
            oos.writeObject(this);
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException("Errore di scrittura su file: " + nomeFile, ex);
        }
    }

    /**
     * @brief Carica lo stato della biblioteca da file.
     * 
     * @param[in] nomeFile Nome del file da cui caricare lo stato.
     * 
     *            Nel caso in cui il file non dovesse esistere, il metodo restituirà
     *            una biblioteca vuota.
     * 
     * @pre Il file deve esistere e contenere un oggetto Biblioteca serializzato.
     * @post L'oggetto Biblioteca verrà letto correttamente, conservando lo stato
     *       che aveva all'atto del salvataggio.
     * 
     * @return L'istanza di Biblioteca caricata dal file.
     * 
     * @throws IOException nel caso in cui il file sia corrotto, illegibile oppure
     *                     in caso di errore nella deserializzazione delle classi.
     */
    public static Biblioteca leggiBibliotecaObj(String nomeFile) throws IOException {

        File file = new File(nomeFile);
        if (!file.exists())
            return new Biblioteca();

        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nomeFile)))) {
            return (Biblioteca) ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException("Errore nella deserializzazione delle classi dal file: " + nomeFile, ex);
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException("File corrotto o illeggibile: " + nomeFile, ex);
        }
    }
}