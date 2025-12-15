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
    
    // TEST COSTRUTTORE
    @Test
    public void testCostruttoreLibriInizializzazione() {
        Libri libri2 = new Libri();
        
        assertNotNull(libri2);
        assertNotNull(libri2.getListaLibri());
    }
    
    @Test
    public void testCostruttoreLibriListaVuota() {
        Libri libri2 = new Libri();
                
        assertTrue(libri2.getListaLibri().isEmpty());
    }
    
    // TEST METODO GETTER
    @Test
    public void testGetListaLibri() {
        assertNotNull(libri.getListaLibri());
        assertTrue(libri.getListaLibri() instanceof ArrayList);
    }

    // TEST RICERCA
    @Test
    public void testRicercaLibri() {
        List<Libro> risultati;
        risultati = libri.ricercaLibri("OrWeLl");
        
        assertTrue(risultati.contains(libri.ottieni(new ISBN("8883379071"))));
        assertTrue(risultati.contains(libri.ottieni(new ISBN("888337908X"))));
        assertEquals(2, risultati.size());
    }

    // TEST AGGIUNTA
    @Test
    public void testAggiungiNuovo() throws Exception {
        ISBN i = new ISBN("8807900122");
        Libro l = new Libro("Cime Tempestose", 1847, i, 9, "Emily Brontë");
        
        libri.aggiungi(l);
        
        assertTrue(libri.getListaLibri().contains(l));
        assertTrue(libri.esisteChiave(i));
    }

    @Test
    public void testAggiungiDuplicato() throws Exception {
        assertThrows(LibroGiaPresenteException.class, () -> { libri.aggiungi(new Libro("1984", 1948, new ISBN("8883379071"), 11, "George Orwell")); });
    }
    
    // TEST MODIFICA
    @Test
    public void testModifica() {
        ISBN i = new ISBN("8883379071");
        
        Libro l = new Libro("1984", 1948, i, 11, "George Orwell");
        l.setCopieDisponibili(20);
        
        libri.modifica(libri.ottieni(i), l);
        
        assertEquals(20, libri.ottieni(i).getCopieDisponibili());
    }

    // TEST RIMUOVI
    @Test
    public void testRimuovi() {
        ISBN i = new ISBN("8883379071");
        Libro l = new Libro("1984", 1948, i, 11, "George Orwell");
        
        libri.rimuovi(l);
        
        assertFalse(libri.getListaLibri().contains(l));
        assertFalse(libri.esisteChiave(i));        
    }

    // TEST ESISTECHIAVE
    @Test
    public void testEsisteChiaveTrue() {
        assertTrue(libri.esisteChiave(new ISBN("883010471X")));
        assertTrue(libri.esisteChiave(new ISBN("8883379071")));
        assertTrue(libri.esisteChiave(new ISBN("888337908X"))); 
    }
    
    @Test
    public void testEsisteChiaveFalse() {
        assertFalse(libri.esisteChiave(new ISBN("8807900122")));   
    }    

    // TEST OTTIENI
    @Test
    public void testOttieniLibroPresente() {
        ISBN i1 = new ISBN("883010471X");
        ISBN i2 = new ISBN("8883379071");
        ISBN i3 = new ISBN("888337908X");
        
        Libro l1 = new Libro("Il Signore degli Anelli", 1954, i1, 5, "J.R.R. Tolkien");
        Libro l2 = new Libro("1984", 1948, i2, 11, "George Orwell");
        Libro l3 = new Libro("La Fattoria degli animali", 1945, i3, 8, "George Orwell");
                
        assertEquals(libri.ottieni(i1), l1);
        assertEquals(libri.ottieni(i2), l2);
        assertEquals(libri.ottieni(i3), l3);
    }
    
    @Test
    public void testOttieniLibroAssente() {
        ISBN i4 = new ISBN("8807900122");
        
        assertNull(libri.ottieni(i4));
    }    
    
}
