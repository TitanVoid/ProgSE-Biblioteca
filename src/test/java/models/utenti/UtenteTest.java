/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utenti;

import models.Matricola;
import models.prestiti.Prestito;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author luisagenovese
 */
public class UtenteTest {
    private Utente u;
    
    public UtenteTest() {
    }
    
    @BeforeEach
    public void setUp() {
        Matricola m = new Matricola("0612708796");
        u = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
    }
    
    @Test
    public void testCostruttoreUtente() {
        Matricola m = new Matricola("0612708796");
        Utente utente = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
        
        assertNotNull(utente);
        assertEquals(utente.getNome(), "Luisa");
        assertEquals(utente.getCognome(), "Genovese");
        assertEquals(utente.getMatricolaUtente(), m);
        assertEquals(utente.getEmail(), "l.genovese20@studenti.unisa.it");
        assertNotNull(utente.getPrestitiAttivi());
        assertTrue(utente.getPrestitiAttivi().isEmpty());
    }

    @Test
    public void testGetMatricolaUtente() {
        Matricola m = new Matricola("0612708796");
        Utente utente = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
        
        assertNotNull(utente.getMatricolaUtente());
        assertEquals(utente.getMatricolaUtente(), m);
    }

    @Test
    public void testGetEmail() {
        Matricola m = new Matricola("0612708796");
        Utente utente = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
        
        assertNotNull(utente.getEmail());
        assertEquals(utente.getEmail(), "l.genovese20@studenti.unisa.it");
    }

    @Test
    public void testGetPrestitiAttivi() {
        Matricola m = new Matricola("0612708796");
        Utente utente = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
        
        assertNotNull(utente.getPrestitiAttivi());
    }

    @Test
    public void testSetEmail() {
        Matricola m = new Matricola("0612708796");
        Utente utente = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
        
        utente.setEmail("l.genovese@studenti.unisa.it");
        
        assertEquals(utente.getEmail(), "l.genovese@studenti.unisa.it");
    }

    @Test
    public void testAggiungiPrestito() {
        Matricola m = new Matricola("0612708796");
        Utente utente = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
        
        // MODIFICARE
        Prestito p = new Prestito(m, null, null, null);
        
        utente.aggiungiPrestito(p);
        
        assertTrue(utente.getPrestitiAttivi().contains(p));  
        assertEquals(utente.getPrestitiAttivi().size(), 1);
    }

    @Test
    public void testRimuoviPrestito() {
        Matricola m = new Matricola("0612708796");
        Utente utente = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
        
        // MODIFICARE
        Prestito p = new Prestito(m, null, null, null);
        
        utente.aggiungiPrestito(p);
        utente.rimuoviPrestito(p);
        
        assertFalse(utente.getPrestitiAttivi().contains(p));  
        assertEquals(utente.getPrestitiAttivi().size(), 0);
    }

    @Test
    public void testVerificaEmail() {
        // Test email valida:
        assertTrue(Utente.verificaEmail("l.genovese20@studenti.unisa.it"));
        // Test email non valida:
        assertFalse(Utente.verificaEmail("luisagenovese@studenti.unisa.it"));
    }

    @Test
    public void testVerificaUtente() {
        // CHIEDERE A BRANCACCIO
    }

    @Test
    public void testEquals() {
        Matricola m1 = new Matricola("0612708796");
        Utente u1 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente uguale:
        Utente u2 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente diverso:
        Matricola m2 = new Matricola("0612708797");
        Utente u3 = new Utente("Luisa", "Genovese", m2, "l.genovese20@studenti.unisa.it");
        
        assertTrue(u1.equals(u2));
        assertFalse(u1.equals(u3));
    }

    @Test
    public void testCompareTo() {
        Matricola m1 = new Matricola("0612708796");
        Matricola m2 = new Matricola("0612708797");
        Matricola m3 = new Matricola("0612708795");
        
        Utente u1 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente uguale:
        Utente u2 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente cognome maggiore:
        Utente u3 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente cognome minore:
        Utente u4 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente cognome uguale, nome maggiore:
        Utente u5 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente cognome uguale, nome minore:
        Utente u6 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente nome e cognome uguali, matricola maggiore:
        Utente u7 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
        // Test utente nome e cognome uguali, matricola minore:
        Utente u8 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        
    }
    
}
