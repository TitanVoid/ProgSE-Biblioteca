package models;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class MatricolaTest {
    private Matricola matricola;

    @Before
    public void setUp() {
        matricola = new Matricola("0612708796");
    }
    
    @Test
    public void testCostruttoreMatricola() {
        Matricola m = new Matricola("0612708796");
        
        assertNotNull(m.getMatricola());
        assertEquals("0612708796", m.getMatricola());
    }
    
    @Test
    public void testGetMatricola() {
        assertEquals("0612708796", matricola.getMatricola());
    }

    @Test
    public void testVerificaMatricola() {
        // Test matricola valida:
        assertTrue(Matricola.verificaMatricola("0612708796"));
        // Test matricola non valida:
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

        assertEquals(0, matricola.compareTo(m1));
        assertTrue(matricola.compareTo(m2) < 0);
        assertTrue(matricola.compareTo(m3) > 0);
    }
}
