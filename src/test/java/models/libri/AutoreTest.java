package models.libri;

import models.Persona;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class AutoreTest {
    private Autore a;
    
    @Before
    public void setUp() {
        a = new Autore("Luigi", "Pirandello");
    }
    
    @Test
    public void testCostruttoreAutore() {
        Autore autore = new Autore("Luigi", "Pirandello");
        
        assertNotNull(autore.getNome());
        assertEquals("Luigi", autore.getNome());
        
        assertNotNull(autore.getCognome());
        assertEquals("Pirandello", autore.getCognome());
    }

    @Test
    public void testGetNome() {
        assertEquals("Luigi", a.getNome());
    }
    
    @Test
    public void testGetCognome() {
        assertEquals("Pirandello", a.getCognome());
    }
    
    @Test
    public void testSetNome() {
        a.setNome("Eugenio");
        assertEquals("Eugenio", a.getNome());
    }
    
    @Test
    public void testSetCognome() {
        a.setCognome("Montale");
        assertEquals("Montale", a.getCognome());
    }
    
    @Test
    public void testVerificaNome() {
        // Test nome valido:
        assertTrue(Persona.verificaNome("Mario"));
        // Test nome con caratteri speciali valido:
        assertTrue(Persona.verificaNome("J.K."));
        // Test nome non valido, poiché troppo corto:
        assertFalse(Persona.verificaNome("Ma"));
        // Test nome non valido, poiché troppo lungo:
        assertFalse(Persona.verificaNome("Mariomariomariomariomariomario"));
        // Test nome non valido, poiché nullo:
        assertFalse(Persona.verificaNome(null));
    }
    
    @Test
    public void testVerificaCognome() {
        // Test cognome valido:
        assertTrue(Persona.verificaCognome("Rossi"));
        // Test nome non valido, poiché troppo corto:
        assertFalse(Persona.verificaCognome("Ro"));
        // Test nome non valido, poiché troppo lungo:
        assertFalse(Persona.verificaCognome("Rossirossirossirossirossirossi"));
        // Test cognome non valido, poiché nullo:
        assertFalse(Persona.verificaCognome(null));
    }
    
}
