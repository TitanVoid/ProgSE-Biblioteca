package models;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class MatricolaTest {
    private Matricola m;

    @Before
    public void setUp() {
        m = new Matricola("0612708796");
    }
    
    // TEST COSTRUTTORE
    @Test
    public void testCostruttoreMatricola() {
        Matricola matricola = new Matricola("0612708796");
        
        assertNotNull(matricola);
        
        assertNotNull(matricola.getMatricola());
        assertEquals("0612708796", matricola.getMatricola());
    }
    
    // TEST METODO GETTER
    @Test
    public void testGetMatricola() {
        assertEquals("0612708796", m.getMatricola());
    }

    // TEST VERIFICA MATRICOLA
    @Test
    public void testVerificaMatricolaValida() {
        assertTrue(Matricola.verificaMatricola("0612708796"));
    }
    
    @Test
    public void testVerificaMatricolaTroppoCorta() {
        assertFalse(Matricola.verificaMatricola("06127"));
    }
    
    @Test
    public void testVerificaMatricolaTroppoLunga() {
        assertFalse(Matricola.verificaMatricola("0612708796487562"));
    }
    
    @Test
    public void testVerificaMatricolaNonNumerica() {
        assertFalse(Matricola.verificaMatricola("06127ABCDE"));
    }
    
    @Test
    public void testVerificaMatricolaNull() {
        assertFalse(Matricola.verificaMatricola(null));
    }

    // TEST EQUALS
    @Test
    public void testEqualsMatricoleUguali() {
        assertEquals(m, new Matricola("0612708796"));
    }
    
    @Test
    public void testEqualsMatricoleDiverse() {
        assertNotEquals(m, new Matricola("0612709671"));
    }    
    
    @Test
    public void testEqualsMatricolaConNull() {
        assertNotEquals(m, null);
    }
    
    // TEST COMPARETO
    @Test
    public void testCompareToUguale() {
        assertEquals(0, m.compareTo(new Matricola("0612708796")));
    }
    
    @Test
    public void testCompareToMinore() {
        assertTrue(m.compareTo(new Matricola("0612708797")) < 0);
    }
    
    @Test
    public void testCompareToMaggiore() {
        assertTrue(m.compareTo(new Matricola("0612708795")) > 0);
    }
}
