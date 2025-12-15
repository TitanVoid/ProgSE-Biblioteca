package models;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import models.libri.Libri;
import models.libri.Libro;
import models.prestiti.Prestiti;
import models.prestiti.Prestito;
import models.utenti.Utente;
import models.utenti.Utenti;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class BibliotecaTest {
    private Biblioteca b;
    private static final String FILE = "test.obj";

    private Matricola m;
    private Utente u;
    private Libro l;
    private ISBN i;
    private Prestito p;

    @Before
    public void setUp() {
        b = new Biblioteca();

        m = new Matricola("0612708796");
        u = new Utente("Mario", "Rossi", m, "m.rossi@studenti.unisa.it");

        i = new ISBN("883010471X");
        l = new Libro("Il Signore degli Anelli", 1954, i, 5, "J.R.R. Tolkien");

        p = new Prestito(m, i, LocalDate.now(), LocalDate.now().plusDays(3));
    }

    @After
    public void tearDown() {
        File f = new File(FILE);
        if (f.exists()) {
            f.delete();
        }
    }

    // TEST COSTRUTTORE
    @Test
    public void testCostruttoreInizializzazioneArchivi() {
        Biblioteca biblioteca = new Biblioteca();
        
        assertNotNull(biblioteca);

        assertNotNull(biblioteca.getPrestiti());
        assertNotNull(biblioteca.getLibri());
        assertNotNull(biblioteca.getUtenti());        
    }

    @Test
    public void testCostruttoreArchiviVuoti() {
        Biblioteca biblioteca = new Biblioteca();
        
        assertTrue(biblioteca.getPrestiti().getListaPrestiti().isEmpty());
        assertTrue(biblioteca.getLibri().getListaLibri().isEmpty());
        assertTrue(biblioteca.getUtenti().getListaUtenti().isEmpty());
    }
    
    // TEST METODI GETTER
    @Test
    public void testGetPrestiti() {
        assertNotNull(b.getPrestiti());
        assertTrue(b.getPrestiti() instanceof Prestiti);
    }

    @Test
    public void testGetLibri() {
        assertNotNull(b.getLibri());
        assertTrue(b.getLibri() instanceof Libri);
    }

    @Test
    public void testGetUtenti() {
        assertNotNull(b.getUtenti());
        assertTrue(b.getUtenti() instanceof Utenti);
    }

    // TEST SALVATAGGIO BIBLIOTECA SU FILE
    @Test
    public void testSalvaBibliotecaObj() throws Exception {
        b.getPrestiti().aggiungi(p);
        b.getLibri().aggiungi(l);
        b.getUtenti().aggiungi(u);

        try {
            b.salvaBibliotecaObj(FILE);
        } catch (IOException ex) {
            fail(ex.toString());
        }

        File f = new File(FILE);
        assertTrue(f.exists());
        assertTrue(f.length() > 0);
    }

    // TEST LETTURA BIBLIOTECA DA FILE
    @Test
    public void testLeggiBibliotecaObj() throws Exception {
        b.getPrestiti().aggiungi(p);
        b.getLibri().aggiungi(l);
        b.getUtenti().aggiungi(u);

        b.salvaBibliotecaObj(FILE);
        Biblioteca letta = Biblioteca.leggiBibliotecaObj(FILE);

        assertNotNull(letta);

        assertNotNull(letta.getPrestiti());
        assertNotNull(letta.getLibri());
        assertNotNull(letta.getUtenti());

        assertEquals(letta.getPrestiti().getListaPrestiti().size(), b.getPrestiti().getListaPrestiti().size());
        assertEquals(letta.getLibri().getListaLibri(), b.getLibri().getListaLibri());
        assertEquals(letta.getUtenti().getListaUtenti(), b.getUtenti().getListaUtenti());
    }

    @Test
    public void testLeggiBibliotecaObjFileInesistente() throws Exception {
        Biblioteca letta = Biblioteca.leggiBibliotecaObj("file_inesistente.obj");

        assertNotNull(letta);

        assertNotNull(letta.getPrestiti());
        assertNotNull(letta.getLibri());
        assertNotNull(letta.getUtenti());

        assertTrue(letta.getPrestiti().getListaPrestiti().isEmpty());
        assertTrue(letta.getLibri().getListaLibri().isEmpty());
        assertTrue(letta.getUtenti().getListaUtenti().isEmpty());
    }
}
