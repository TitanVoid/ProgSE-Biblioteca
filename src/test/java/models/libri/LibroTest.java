package models.libri;

import models.FormatoCampiErratoException;
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
        assertEquals("Il Signore degli Anelli", libro.getTitolo());
        assertTrue(libro.getAutori().contains(new Autore("J.R.R.", "Tolkien")));
        assertEquals(2020, libro.getAnnoPubblicazione());
        assertEquals(libro.getCodiceISBNLibro(), isbn);
        assertEquals(5, libro.getCopieDisponibili());
    }

    @Test
    public void testGetTitolo() {
        assertEquals("Il Signore degli Anelli", l.getTitolo());
    }

    @Test
    public void testGetAnnoPubblicazione() {
        assertEquals(2020, l.getAnnoPubblicazione());
    }

    @Test
    public void testGetCodiceISBNLibro() {
        assertEquals(new ISBN("883010471X"), l.getCodiceISBNLibro());
    }

    @Test
    public void testGetCopieDisponibili() {
        assertEquals(5, l.getCopieDisponibili());
    }

    @Test
    public void testGetAutori() {
        assertNotNull(l.getAutori());
    }

    @Test
    public void testSetTitolo() {
        l.setTitolo("Lo Hobbit");

        assertEquals("Lo Hobbit", l.getTitolo());
    }

    @Test
    public void testSetAnnoPubblicazione() {
        l.setAnnoPubblicazione(2018);

        assertEquals(2018, l.getAnnoPubblicazione());
    }

    @Test
    public void testSetCopieDisponibili() {
        l.setCopieDisponibili(10);

        assertEquals(10, l.getCopieDisponibili());
    }

    @Test
    public void testAggiungiAutore() {
        Autore autore = new Autore("Mario", "Rossi");

        l.aggiungiAutore(autore);

        assertTrue(l.getAutori().contains(autore));
        assertEquals(2, l.getAutori().size());
    }

    @Test
    public void testRimuoviAutore() {
        Autore autore = new Autore("J.R.R.", "Tolkien");

        l.rimuoviAutore(autore);

        assertFalse(l.getAutori().contains(autore));
        assertEquals(0, l.getAutori().size());
    }

    @Test
    public void testVerificaLibro() {
        // Test con tutti i campi validi:
        assertTrue(Libro.verificaLibro("J.R.R. Tolkien", "Il Signore degli Anelli", "1954", "883010471X", "5"));

        // Test con il nome dell'autore non valido:
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J Tolkien", "Il Signore degli Anelli", "1954", "883010471X", "5");
        });
        // Test con il cognome dell'autore non valido:
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. To", "Il Signore degli Anelli", "1954", "883010471X", "5");
        });
        // Test con il titolo non valido:
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. Tolkien", null, "1954", "883010471X", "5");
        });
        // Test con il codice ISBN non valido:
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. Tolkien", "Il Signore degli Anelli", "1954", "883010471", "5");
        });
        // Test con l'anno di pubblicazione non valido:
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. Tolkien", "Il Signore degli Anelli", "2054", "883010471X", "5");
        });
        // Test con il numero di copie disponibili non valido:
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. Tolkien", "Il Signore degli Anelli", "1954", "883010471X", "500");
        });
    }

    @Test
    public void testEquals() {
        Libro l1 = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        Libro l2 = new Libro("Il Signore degli Anelli", 2020, new ISBN("983010471X"), 5, "J.R.R. Tolkien");

        assertEquals(l, l1);
        assertNotEquals(l, l2);
    }

    @Test
    public void testCompareTo() {
        // Test libro uguale:
        Libro l1 = new Libro("Il Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        // Test libro con titolo maggiore:
        Libro l2 = new Libro("Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        // Test libro con ISBN maggiore:
        Libro l3 = new Libro("Il Signore degli Anelli", 2020, new ISBN("983010471X"), 5, "J.R.R. Tolkien");
        // Test libro con titolo minore:
        Libro l4 = new Libro("Al Signore degli Anelli", 2020, new ISBN("883010471X"), 5, "J.R.R. Tolkien");
        // Test libro con ISBN minore:
        Libro l5 = new Libro("Il Signore degli Anelli", 2020, new ISBN("783010471X"), 5, "J.R.R. Tolkien");

        assertEquals(0, l.compareTo(l1));
        assertTrue(l.compareTo(l2) < 0);
        assertTrue(l.compareTo(l3) < 0);
        assertTrue(l.compareTo(l4) > 0);
        assertTrue(l.compareTo(l5) > 0);
    }
}
