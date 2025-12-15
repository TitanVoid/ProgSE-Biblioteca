package models;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class ISBNTest {
    private ISBN i;

    @Before
    public void setUp() {
        i = new ISBN("883010471X");
    }
    
    @Test
    public void testCostruttoreISBN() {
        ISBN isbn = new ISBN("883010471X");
        
        assertNotNull(isbn.getCodiceISBN());
        assertEquals("883010471X", isbn.getCodiceISBN());
    }
    
    @Test
    public void testGetCodiceISBN() {
        assertEquals("883010471X", i.getCodiceISBN());
    }

    @Test
    public void testVerificaISBN() {
        // Test codice ISBN-10 valido:
        assertTrue(ISBN.verificaISBN("883010471X"));
        // Test codice ISBN-10 non valido:
        assertFalse(ISBN.verificaISBN("88E010471X"));
        // Test codice ISBN-13 valido:
        assertTrue(ISBN.verificaISBN("9788867582143"));
        // Test codice ISBN-13 non valido:
        assertFalse(ISBN.verificaISBN("978886758214E"));
        // Test codice ISBN troppo corto:
        assertFalse(ISBN.verificaISBN("9788807"));
        // Test codice ISBN troppo lungo:
        assertFalse(ISBN.verificaISBN("978880788229836846"));
    }

    @Test
    public void testEquals() {
        ISBN i1 = new ISBN("883010471X");
        ISBN i2 = new ISBN("9788867582143");
        
        assertEquals(i, i1);
        assertNotEquals(i, i2);
    }

    @Test
    public void testCompareTo() {    
        ISBN i1 = new ISBN("883010471X");
        ISBN i2 = new ISBN("883010481X");
        ISBN i3 = new ISBN("883010461X");

        assertEquals(0, i.compareTo(i1));
        assertTrue(i.compareTo(i2) < 0);
        assertTrue(i.compareTo(i3) > 0);
    }
}
