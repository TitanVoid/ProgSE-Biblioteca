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
public class MatricolaTest {
    private Matricola matricola;
    
    public MatricolaTest() {
    }

    @BeforeEach
    public void setUp() {
        matricola = new Matricola("0612708796");
    }
    
    @Test
    public void testCostruttoreMatricola() {
        Matricola m = new Matricola("0612708796");
        
        assertNotNull(m.getMatricola());
        assertEquals(m.getMatricola(), "0612708796");
    }
    
    @Test
    public void testGetMatricola() {
        assertEquals(matricola.getMatricola(), "0612708796");
    }

    @Test
    public void testVerificaMatricola() {
        assertTrue(Matricola.verificaMatricola("0612708796"));
        assertFalse(Matricola.verificaMatricola(null));
        assertFalse(Matricola.verificaMatricola("061270879"));
        assertFalse(Matricola.verificaMatricola("061L708796"));
    }

    @Test
    public void testEquals() {
        Matricola m1 = new Matricola("0612708796");
        Matricola m2 = new Matricola("0612708797");
        
        assertEquals(matricola, m1);
        assertNotEquals(matricola, m2);
    }

    @Test
    public void testCompareTo() {
        Matricola m1 = new Matricola("0612708796");
        Matricola m2 = new Matricola("0612708797");
        Matricola m3 = new Matricola("0612708795");
        
        assertTrue(matricola.compareTo(m1) == 0);
        assertTrue(matricola.compareTo(m2) < 0);
        assertTrue(matricola.compareTo(m3) > 0);
    }
    
}
