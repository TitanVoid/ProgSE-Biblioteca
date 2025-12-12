/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.libri;

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
        assertNotNull(a);
    }

    @Test
    public void testGetNome() {
        assertEquals(a.getNome(), "Luigi");
    }
    
    @Test
    public void testGetCognome() {
        assertEquals(a.getCognome(), "Pirandello");
    }
}
