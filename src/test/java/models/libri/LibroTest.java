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
        l = new Libro("Il Signore degli Anelli", 1954, isbn, 5, "J.R.R. Tolkien");
    }

    // TEST COSTRUTTORE
    @Test
    public void testCostruttoreLibro() {
        ISBN isbn = new ISBN("883010471X");
        Libro libro = new Libro("Il Signore degli Anelli", 1954, isbn, 5, "J.R.R. Tolkien");

        assertNotNull(libro);
        
        assertEquals("Il Signore degli Anelli", libro.getTitolo());
        assertNotNull(libro.getAutori());
        assertTrue(libro.getAutori().contains(new Autore("J.R.R.", "Tolkien")));
        assertEquals(1954, libro.getAnnoPubblicazione());
        assertEquals(libro.getCodiceISBNLibro(), isbn);
        assertEquals(5, libro.getCopieDisponibili());
    }

    // TEST METODI GETTER
    @Test
    public void testGetTitolo() {
        assertEquals("Il Signore degli Anelli", l.getTitolo());
    }

    @Test
    public void testGetAnnoPubblicazione() {
        assertEquals(1954, l.getAnnoPubblicazione());
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

    // TEST METODI SETTER
    @Test
    public void testSetTitolo() {
        l.setTitolo("Lo Hobbit");

        assertEquals("Lo Hobbit", l.getTitolo());
    }

    @Test
    public void testSetAnnoPubblicazione() {
        l.setAnnoPubblicazione(1950);

        assertEquals(1950, l.getAnnoPubblicazione());
    }

    @Test
    public void testSetCopieDisponibili() {
        l.setCopieDisponibili(10);

        assertEquals(10, l.getCopieDisponibili());
    }

    // TEST AGGIUNGIAUTORE
    @Test
    public void testAggiungiAutore() {
        Autore autore = new Autore("Luigi", "Pirandello");

        l.aggiungiAutore(autore);

        assertTrue(l.getAutori().contains(autore));
        assertEquals(2, l.getAutori().size());
    }

    // TEST RIMUOVIAUTORE
    @Test
    public void testRimuoviAutore() {
        Autore autore = new Autore("J.R.R.", "Tolkien");

        l.rimuoviAutore(autore);

        assertFalse(l.getAutori().contains(autore));
        assertEquals(0, l.getAutori().size());
    }

    // TEST VERIFICA LIBRO
    @Test
    public void testVerificaLibroValido() {
        assertTrue(Libro.verificaLibro("J.R.R. Tolkien", "Il Signore degli Anelli", "1954", "883010471X", "5"));
    }
    
    @Test
    public void testVerificaLibroTitoloNonValido() {
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. Tolkien", null, "1954", "883010471X", "5");
        });
    }
    
    @Test
    public void testVerificaLibroNomeAutoreNonValido() {
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J Tolkien", "Il Signore degli Anelli", "1954", "883010471X", "5");
        });        
    }
    
    @Test
    public void testVerificaLibroCognomeAutoreNonValido() {
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. To", "Il Signore degli Anelli", "1954", "883010471X", "5");
        });
    }
    
    @Test
    public void testVerificaLibroISBNNonValido() {
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. Tolkien", "Il Signore degli Anelli", "1954", "883010471", "5");
        });
    }
    
    @Test
    public void testVerificaLibroAnnoPubblicazioneNonValido() {
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. Tolkien", "Il Signore degli Anelli", "2054", "883010471X", "5");
        });
    }
    
    @Test
    public void testVerificaLibroNumeroCopieNonValido() {
        assertThrows(FormatoCampiErratoException.class, () -> {
            Libro.verificaLibro("J.R.R. Tolkien", "Il Signore degli Anelli", "1954", "883010471X", "-5");
        });
    }

    // TEST EQUALS
    @Test
    public void testEqualsLibriUguali() {
        assertEquals(l, new Libro("Il Signore degli Anelli", 1954, new ISBN("883010471X"), 5, "J.R.R. Tolkien"));
    }
    
    @Test
    public void testEqualsLibriDiversi() {
        assertNotEquals(l, new Libro("La Fattoria degli Animali", 1945, new ISBN("888337908X"), 8, "George Orwell"));
    }
    
    @Test
    public void testEqualsLibroConNull() {
        assertNotEquals(l, null);
    }

    // TEST COMPARETO
    @Test
    public void testCompareToUguale() {
        assertEquals(0, l.compareTo(new Libro("Il Signore degli Anelli", 1954, new ISBN("883010471X"), 5, "J.R.R. Tolkien")));
    }
    
    @Test
    public void testCompareToMinore() {
        assertTrue(l.compareTo(new Libro("La Fattoria degli Animali", 1945, new ISBN("888337908X"), 8, "George Orwell")) < 0);
    }
    
    @Test
    public void testCompareToMaggiore() {
        assertTrue(l.compareTo(new Libro("Carrie", 1974, new ISBN("8830110515"), 10, "Stephen King")) > 0);
    }
}
