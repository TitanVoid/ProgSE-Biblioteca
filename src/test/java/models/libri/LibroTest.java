/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class LibroTest {
    private Libro l;
    
    public LibroTest() {
    }
    
    @Before
    public void setUp() {
        ISBN isbn = new ISBN("883010471X");
        l = new Libro("Il Signore degli Anelli", 2020, isbn, 5, "J.R.R. Tolkien");
    }

    @Test
    public void testCostruttoreLibro() {
        ISBN isbn = new ISBN("883010471X");
        Libro libro = new Libro("Il Signore degli Anelli", 2020, isbn, 5, "J.R.R. Tolkien");
        
        assertNotNull(libro);
        assertEquals(libro.getTitolo(), "Il Signore degli Anelli");
        // CHIEDERE A BRANCACCIO
        // assertTrue(libro.getAutori().contains(new Autore("J.R.R.", "Tolkien")));
        assertEquals(libro.getAnnoPubblicazione(), 2020);
        assertEquals(libro.getCodiceISBNLibro(), isbn);
        assertEquals(libro.getCopieDisponibili(), 5);
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
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        
        libro.setTitolo("Lo Hobbit");
        
        assertEquals(libro.getTitolo(), "Lo Hobbit");
    }

    @Test
    public void testSetAnnoPubblicazione() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        
        libro.setAnnoPubblicazione(2018);
        
        assertEquals(libro.getAnnoPubblicazione(), 2018);
    }

    @Test
    public void testSetCopieDisponibili() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        
        libro.setCopieDisponibili(10);
        
        assertEquals(libro.getCopieDisponibili(), 10);
    }

    @Test
    public void testAggiungiAutore() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        Autore autore = new Autore("Mario", "Rossi");
        
        libro.aggiungiAutore(autore);
        
        assertTrue(libro.getAutori().contains(autore));
        assertEquals(libro.getAutori().size(), 2);
    }

    @Test
    public void testRimuoviAutore() {
        Libro libro = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        Autore autore = new Autore("J.R.R.", "Tolkien");
        
        libro.rimuoviAutore(autore);
        
        assertFalse(libro.getAutori().contains(autore));
        // assertEquals(libro.getAutori().size(), 0);
    }

    @Test
    public void testVerificaLibro() {
        // CHIEDERE A BRANCACCIO
    }

    @Test
    public void testEquals() {
        Libro l1 = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        Libro l2 = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        Libro l3 = new Libro("Il Signore degli Anelli", 2020, new ISBN("983010471X"), 5, "J.R.R. Tolkien");
        
        assertEquals(l1, l2);
        assertNotEquals(l1, l3);
    }

    @Test
    public void testCompareTo() {
        Libro l1 = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        // Test libro uguale:
        Libro l2 = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        // Test libro con titolo maggiore:
        Libro l3 = new Libro("Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        // Test libro con ISBN maggiore:
        Libro l4 = new Libro("Il Signore degli Anelli", 2020, new ISBN("983010471X"), 5, "J.R.R. Tolkien");
        // Test libro con titolo minore:
        Libro l5 = new Libro("Al Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        // Test libro con ISBN minore:
        Libro l6 = new Libro("Il Signore degli Anelli", 2020, new ISBN("783010471X"), 5, "J.R.R. Tolkien");
        
        assertTrue(l1.compareTo(l2) == 0);
        assertTrue(l1.compareTo(l3) < 0);
        assertTrue(l1.compareTo(l4) < 0);
        assertTrue(l1.compareTo(l5) > 0);
        assertTrue(l1.compareTo(l6) > 0);     
    }
    
}
