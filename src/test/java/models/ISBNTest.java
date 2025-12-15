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
    
    // TEST COSTRUTTORE
    @Test
    public void testCostruttoreISBN() {
        ISBN isbn = new ISBN("883010471X");
        
        assertNotNull(isbn);
        
        assertNotNull(isbn.getCodiceISBN());
        assertEquals("883010471X", isbn.getCodiceISBN());
    }
    
    // TEST METODO GETTER
    @Test
    public void testGetCodiceISBN() {
        assertEquals("883010471X", i.getCodiceISBN());
    }

    // TEST VERIFICA ISBN-10
    @Test
    public void testVerificaISBN10Valido() {
        assertTrue(ISBN.verificaISBN("8863114927"));
    }
    
    @Test
    public void testVerificaISBN10ValidoConX() {
        assertTrue(ISBN.verificaISBN("883010471X"));
    }
    
    @Test
    public void testVerificaISBN10NonValido() {
        // Test con codice non numerico:
        assertFalse(ISBN.verificaISBN("886E11A9L7"));
        // Test con ultimo carattere diverso da X o da una cifra:
        assertFalse(ISBN.verificaISBN("883010471Y"));
    }
    
    // TEST VERIFICA ISBN-13
    @Test
    public void testVerificaISBN13Valido() {
        assertTrue(ISBN.verificaISBN("9788867582143"));
    }
    
    @Test
    public void testVerificaISBN13NonValido() {
        assertFalse(ISBN.verificaISBN("9T8886758L14E"));
    }

    // TEST VERIFICA ISBN 
    @Test
    public void testVerificaISBNTroppoCorto() {
        assertFalse(ISBN.verificaISBN("9788807"));
    }
    
    @Test
    public void testVerificaISBNTroppoLungo() {
        assertFalse(ISBN.verificaISBN("978880788229836846"));
    }
    
    // TEST EQUALS
    @Test
    public void testEqualsISBNUguali() {
        assertEquals(i, new ISBN("883010471X"));
    }
    
    @Test
    public void testEqualsISBNDiversi() {
        assertNotEquals(i, new ISBN("9788867582143"));
    }
    
    @Test
    public void testEqualsISBNConNull() {
        assertNotEquals(i, null);
    }

    // TEST COMPARETO
    @Test
    public void testCompareToUguale() {    
        assertEquals(0, i.compareTo(new ISBN("883010471X")));
    }
    
    @Test
    public void testCompareToMinore() {
        assertTrue(i.compareTo(new ISBN("8863114927")) < 0);
    }
    
    @Test
    public void testCompareToMaggiore() {
        assertTrue(i.compareTo(new ISBN("8807900327")) > 0);
    }
}
