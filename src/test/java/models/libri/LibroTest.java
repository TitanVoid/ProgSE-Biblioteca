/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.libri;

import models.ISBN;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author luisagenovese
 */
public class LibroTest {
    private Libro l;
    
    public LibroTest() {
    }
    
    @BeforeEach
    public void setUp() {
        l = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. TOLKIEN");
    }

    @Test
    public void testGetTitolo() {
        assertEquals(l.getTitolo(), "Il Signore degli Anelli");
    }

    @Test
    public void testGetAnnoPubblicazione() {
        assertEquals(l.getAnnoPubblicazione(), 2020);
    }

    @Test
    public void testGetCodiceISBNLibro() {
        assertEquals(l.getCodiceISBNLibro(), new ISBN("883010471X"));
    }

    @Test
    public void testGetCopieDisponibili() {
        assertEquals(l.getCopieDisponibili(), 5);
    }

    @Test
    public void testGetAutori() {
        assertNotNull(l.getAutori());
    }

    @Test
    public void testSetTitolo() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. TOLKIEN");
        
        libro.setTitolo("Lo Hobbit");
        
        assertEquals(libro.getTitolo(), "Lo Hobbit");
    }

    @Test
    public void testSetAnnoPubblicazione() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. TOLKIEN");
        
        libro.setAnnoPubblicazione(2018);
        
        assertEquals(libro.getAnnoPubblicazione(), 2018);
    }

    @Test
    public void testSetCopieDisponibili() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. TOLKIEN");
        
        libro.setCopieDisponibili(10);
        
        assertEquals(libro.getCopieDisponibili(), 10);
    }

    @Test
    public void testAggiungiAutore() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. TOLKIEN");
        Autore autore = new Autore("Mario", "Rossi");
        
        libro.aggiungiAutore(autore);
        
        assertTrue(libro.getAutori().contains(autore));
    }

    @Test
    public void testRimuoviAutore() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. TOLKIEN");
        Autore autore = new Autore("Mario", "Rossi");
        
        libro.aggiungiAutore(autore);
        libro.rimuoviAutore(autore);
        
        assertFalse(libro.getAutori().contains(autore));
    }

    @Test
    public void testVerificaLibro() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testCompareTo() {
    }
    
}
