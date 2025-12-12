/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author luisagenovese
 */
public class ISBNTest {
    private ISBN i;
    
    public ISBNTest() {
    }

    @BeforeEach
    public void setUp() {
        i = new ISBN("883010471X");
    }
    
    @Test
    public void testCostruttoreISBN() {
        ISBN isbn = new ISBN("883010471X");
        
        assertNotNull(isbn.getCodiceISBN());
        assertEquals(isbn.getCodiceISBN(), "883010471X");
    }
    
    @Test
    public void testGetCodiceISBN() {
        assertEquals(i.getCodiceISBN(), "883010471X");
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
        // Test codice ISBN nullo:
        assertFalse(ISBN.verificaISBN(null));
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
        
        assertTrue(i.compareTo(i1) == 0);
        assertTrue(i.compareTo(i2) < 0);
        assertTrue(i.compareTo(i3) > 0);
    }
    
}
