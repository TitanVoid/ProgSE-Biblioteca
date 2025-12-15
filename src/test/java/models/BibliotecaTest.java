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
    private static final String testFile = "test.obj";

    private Matricola m;
    private Utente u;
    private Libro l;
    private ISBN i;
    private Prestito p;

    @Before
    public void setUp() {
        b = new Biblioteca();

        m = new Matricola("1234567890");
        u = new Utente("Mario", "Rossi", m, "m.rossi@studenti.unisa.it");

        i = new ISBN("883010471X");
        l = new Libro("Il Signore degli Anelli", 1954, i, 5, "J.R.R. Tolkien");

        p = new Prestito(m, i, LocalDate.now(), LocalDate.now().plusDays(3));
    }

    @After
    public void tearDown() {
        File f = new File(testFile);
        if (f.exists()) {
            f.delete();
        }
    }

    @Test
    public void testCostruttoreInizializzazioneArchivi() {
        Biblioteca biblio = new Biblioteca();

        assertNotNull(biblio.getPrestiti());
        assertNotNull(biblio.getLibri());
        assertNotNull(biblio.getUtenti());        
    }

    @Test
    public void testCostruttoreArchiviVuoti() {
        Biblioteca biblio = new Biblioteca();
        
        assertTrue(biblio.getPrestiti().getListaPrestiti().isEmpty());
        assertTrue(biblio.getLibri().getListaLibri().isEmpty());
        assertTrue(biblio.getUtenti().getListaUtenti().isEmpty());
    }
    
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

    @Test
    public void testSalvaBibliotecaObj() throws Exception {
        b.getPrestiti().aggiungi(p);
        b.getLibri().aggiungi(l);
        b.getUtenti().aggiungi(u);

        try {
            b.salvaBibliotecaObj(testFile);
        } catch (IOException ex) {
            fail(ex.toString());
        }

        File f = new File(testFile);
        assertTrue(f.exists());
        assertTrue(f.length() > 0);
    }

    @Test
    public void testLeggiBibliotecaObj() throws Exception {
        b.getPrestiti().aggiungi(p);
        b.getLibri().aggiungi(l);
        b.getUtenti().aggiungi(u);

        b.salvaBibliotecaObj(testFile);
        Biblioteca letta = Biblioteca.leggiBibliotecaObj(testFile);

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
