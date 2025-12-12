/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.libri;

import models.Persona;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author luisagenovese
 */
public class AutoreTest {
    private Autore a;
    
    public AutoreTest() {
    }
    
    @BeforeEach
    public void setUp() {
        a = new Autore("Luigi", "Pirandello");
    }
    
    @Test
    public void testCostruttoreAutore() {
        Autore autore = new Autore("Luigi", "Pirandello");
        
        assertNotNull(autore.getNome());
        assertEquals(autore.getNome(), "Luigi");
        
        assertNotNull(autore.getCognome());
        assertEquals(autore.getCognome(), "Pirandello");
    }

    @Test
    public void testGetNome() {
        assertEquals(a.getNome(), "Luigi");
    }
    
    @Test
    public void testGetCognome() {
        assertEquals(a.getCognome(), "Pirandello");
    }
    
    @Test
    public void testSetNome() {
        a.setNome("Eugenio");
        assertEquals(a.getNome(), "Eugenio");
    }
    
    @Test
    public void testSetCognome() {
        a.setCognome("Montale");
        assertEquals(a.getCognome(), "Montale");
    }
    
    @Test
    public void testVerificaNome() {
        // Test nome valido:
        assertTrue(Persona.verificaNome("Mario"));
        // Test nome non valido, poiché contenente numeri e caratteri speciali:
        assertFalse(Persona.verificaNome("M4rio!!!"));
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
        // Test cognome non valido, poiché contenente numeri e caratteri speciali:
        assertFalse(Persona.verificaCognome("R0ss1!!!"));
        // Test nome non valido, poiché troppo corto:
        assertFalse(Persona.verificaCognome("Ro"));
        // Test nome non valido, poiché troppo lungo:
        assertFalse(Persona.verificaCognome("Rossirossirossirossirossirossi"));
        // Test cognome non valido, poiché nullo:
        assertFalse(Persona.verificaCognome(null));
    }
    
}
