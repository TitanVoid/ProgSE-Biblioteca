/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class BibliotecaTest {
    private Biblioteca b;
    
    public BibliotecaTest() {
    }

    @Before
    public void setUp() {
        b = new Biblioteca();
    }
    
    @Test
    public void testCostruttoreBiblioteca() {
        Biblioteca biblio = new Biblioteca();
        
        assertNotNull(biblio.getPrestiti());
        assertTrue(biblio.getPrestiti().getListaPrestiti().isEmpty());
        
        assertNotNull(biblio.getLibri());
        assertTrue(biblio.getLibri().getListaLibri().isEmpty());
        
        assertNotNull(biblio.getUtenti());
        assertTrue(biblio.getUtenti().getListaUtenti().isEmpty());
    }
    
    @Test
    public void testGetPrestiti() {
        assertNotNull(b.getPrestiti());     
    }

    @Test
    public void testGetLibri() {
        assertNotNull(b.getLibri());
    }

    @Test
    public void testGetUtenti() {
        assertNotNull(b.getUtenti());
    }

    @Test
    public void testSalvaBibliotecaObj() throws Exception {
        // TODO: chiedere ai ragazzi come vogliono farlo
    }

    @Test
    public void testLeggiBibliotecaObj() throws Exception {
        // TODO: chiedere ai ragazzi come vogliono farlo
    }
    
}
