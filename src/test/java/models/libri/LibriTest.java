package models.libri;

import java.util.ArrayList;
import java.util.List;
import models.ISBN;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class LibriTest {
    private Libri libri;

    @Before
    public void setUp() throws Exception {
        libri = new Libri();
        
        libri.aggiungi(new Libro("Il Signore degli Anelli", 1954, new ISBN("883010471X"), 5, "J.R.R. Tolkien"));
        libri.aggiungi(new Libro("1984", 1948, new ISBN("8883379071"), 11, "George Orwell"));
        libri.aggiungi(new Libro("La Fattoria degli Animali", 1945, new ISBN("888337908X"), 8, "George Orwell")); 
    }
    
    @Test
    public void testCostruttoreLibri() {
        Libri libri2 = new Libri();
        
        assertNotNull(libri2.getListaLibri());
        assertTrue(libri2.getListaLibri().isEmpty());
    }
    
    @Test
    public void testGetListaLibri() {
        assertTrue(libri.getListaLibri() instanceof ArrayList);
    }

    @Test
    public void testRicercaLibri() {
        List<Libro> risultati;
        risultati = libri.ricercaLibri("ORWELL");
        
        assertTrue(risultati.contains(libri.ottieni(new ISBN("8883379071"))));
        assertTrue(risultati.contains(libri.ottieni(new ISBN("888337908X"))));
        assertEquals(2, risultati.size());
    }

    @Test
    public void testAggiungi() throws Exception {
        // Test libro nuovo:
        ISBN i = new ISBN("8807900122");
        Libro l = new Libro("Cime Tempestose", 1847, i, 9, "Emily Brontë");
        
        libri.aggiungi(l);
        
        assertTrue(libri.getListaLibri().contains(l));
        assertTrue(libri.esisteChiave(i));
        
        // Test libro duplicato:
        assertThrows(LibroGiaPresenteException.class, () -> { libri.aggiungi(l); });
    }

    @Test
    public void testModifica() {
        ISBN i = new ISBN("8883379071");
        
        Libro l = new Libro("1984", 1948, i, 11, "George Orwell");
        l.setCopieDisponibili(20);
        
        libri.modifica(libri.ottieni(i), l);
        
        assertEquals(20, libri.ottieni(i).getCopieDisponibili());
    }

    @Test
    public void testRimuovi() {
        ISBN i = new ISBN("8883379071");
        Libro l = new Libro("1984", 1948, i, 11, "George Orwell");
        
        libri.rimuovi(l);
        
        assertFalse(libri.getListaLibri().contains(l));
        assertFalse(libri.esisteChiave(i));        
    }

    @Test
    public void testEsisteChiave() {
        assertTrue(libri.esisteChiave(new ISBN("883010471X")));
        assertTrue(libri.esisteChiave(new ISBN("8883379071")));
        assertTrue(libri.esisteChiave(new ISBN("888337908X")));
        
        assertFalse(libri.esisteChiave(new ISBN("8807900122")));   
    }

    @Test
    public void testOttieni() {
        ISBN i1 = new ISBN("883010471X");
        ISBN i2 = new ISBN("8883379071");
        ISBN i3 = new ISBN("888337908X");
        
        Libro l1 = new Libro("Il Signore degli Anelli", 1954, i1, 5, "J.R.R. Tolkien");
        Libro l2 = new Libro("1984", 1948, i2, 11, "George Orwell");
        Libro l3 = new Libro("La Fattoria degli animali", 1945, i3, 8, "George Orwell");
        
        ISBN i4 = new ISBN("8807900122");
        
        assertEquals(libri.ottieni(i1), l1);
        assertEquals(libri.ottieni(i2), l2);
        assertEquals(libri.ottieni(i3), l3);
        assertNull(libri.ottieni(i4));
    }
    
}
