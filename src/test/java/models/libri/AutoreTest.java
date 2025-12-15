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
    
    // TEST COSTRUTTORE
    @Test
    public void testCostruttoreAutore() {
        Autore autore = new Autore("Luigi", "Pirandello");
        
        assertNotNull(autore);
        
        assertNotNull(autore.getNome());
        assertEquals("Luigi", autore.getNome());
        
        assertNotNull(autore.getCognome());
        assertEquals("Pirandello", autore.getCognome());
    }

    // TEST METODI GETTER
    @Test
    public void testGetNome() {
        assertEquals("Luigi", a.getNome());
    }
    
    @Test
    public void testGetCognome() {
        assertEquals("Pirandello", a.getCognome());
    }
    
    // TEST METODI SETTER
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
    
    // TEST VERIFICA NOME
    @Test
    public void testVerificaNomeValido() {
        assertTrue(Persona.verificaNome("Luigi"));
    }
    
    @Test
    public void testVerificaNomeTroppoCorto() {
        assertFalse(Persona.verificaNome("A"));
    }
    
    @Test
    public void testVerificaNomeTroppoLungo() {
        assertFalse(Persona.verificaNome("Abcdefghijklmnopqrstuvwxyz"));
    }
    
    @Test
    public void testVerificaNomeNull() {
        assertFalse(Persona.verificaNome(null));
    }
    
    // TEST VERIFICA COGNOME
    @Test
    public void testVerificaCognomeValido() {
        assertTrue(Persona.verificaCognome("Pirandello"));
    }
    
    @Test
    public void testVerificaCognomeTroppoCorto() {
        assertFalse(Persona.verificaCognome("A"));
    }
    
    @Test
    public void testVerificaCognomeTroppoLungo() {
        assertFalse(Persona.verificaCognome("Abcdefghijklmnopqrstuvwxyz"));
    }
    
    @Test
    public void testVerificaCognomeNullo() {
        assertFalse(Persona.verificaCognome(null));
    }
    
    // TEST EQUALS
    @Test
    public void testEqualsAutoriUguali() {
        assertEquals(a, new Autore("Luigi", "Pirandello"));
    }
    
    @Test
    public void testEqualsAutoriDiversi() {
        assertNotEquals(a, new Autore("George", "Orwell"));
    }
    
    @Test
    public void testEqualsAutoreConNull() {
        assertNotEquals(a, null);
    }
}
