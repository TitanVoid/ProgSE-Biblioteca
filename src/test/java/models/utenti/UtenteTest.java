/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utenti;

import models.FormatoCampiErratoException;
import models.Matricola;
import models.prestiti.Prestito;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class UtenteTest {
    private Utente u;

    public UtenteTest() {
    }

    @Before
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

        assertNotNull(u.getMatricolaUtente());
        assertEquals(u.getMatricolaUtente(), m);
    }

    @Test
    public void testGetEmail() {
        assertNotNull(u.getEmail());
        assertEquals(u.getEmail(), "l.genovese20@studenti.unisa.it");
    }

    @Test
    public void testGetPrestitiAttivi() {
        assertNotNull(u.getPrestitiAttivi());
    }

    @Test
    public void testSetEmail() {
        u.setEmail("l.genovese@studenti.unisa.it");

        assertEquals(u.getEmail(), "l.genovese@studenti.unisa.it");
    }

    @Test
    public void testAggiungiPrestito() {
        Matricola m = new Matricola("0612708796");
        
        Prestito p = new Prestito(m, null, null, null);

        u.aggiungiPrestito(p);

        assertTrue(u.getPrestitiAttivi().contains(p));
        assertEquals(u.getPrestitiAttivi().size(), 1);
    }

    @Test
    public void testRimuoviPrestito() {
        Matricola m = new Matricola("0612708796");
       
        Prestito p = new Prestito(m, null, null, null);

        u.aggiungiPrestito(p);
        u.rimuoviPrestito(p);

        assertFalse(u.getPrestitiAttivi().contains(p));
        assertEquals(u.getPrestitiAttivi().size(), 0);
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
        // Test con tutti i campi validi:
        assertTrue(Utente.verificaUtente("Luisa", "Genovese", "0612708796", "l.genovese20@studenti.unisa.it"));
    
        // Test con il nome non valido:
        assertThrows(FormatoCampiErratoException.class, () -> { Utente.verificaUtente("Lu", "Genovese", "0612708796", "l.genovese20@studenti.unisa.it"); });
        // Test con il cognome non valido:
        assertThrows(FormatoCampiErratoException.class, () -> { Utente.verificaUtente("Luisa", "Ge", "0612708796", "l.genovese20@studenti.unisa.it"); });
        // Test con la matricola non valida:
        assertThrows(FormatoCampiErratoException.class, () -> { Utente.verificaUtente("Luisa", "Genovese", "06127", "l.genovese20@studenti.unisa.it"); });
        // Test con l'email non valida:
        assertThrows(FormatoCampiErratoException.class, () -> { Utente.verificaUtente("Luisa", "Genovese", "0612708796", "lgenovese20@studenti.unisa.it"); });
    }

    @Test
    public void testEquals() {
        Matricola m1 = new Matricola("0612708796");
        Matricola m2 = new Matricola("0612708797");

        // Test utente uguale:
        Utente u1 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        assertTrue(u.equals(u1));
        
        // Test utente diverso:
        Utente u2 = new Utente("Luisa", "Genovese", m2, "l.genovese20@studenti.unisa.it");
        assertFalse(u.equals(u2));
    }

    @Test
    public void testCompareTo() {
        Matricola m1 = new Matricola("0612708796");
        Matricola m2 = new Matricola("0612708797");
        Matricola m3 = new Matricola("0612708795");

        // Test utente uguale:
        Utente u1 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");

        // Test utente cognome maggiore:
        Utente u2 = new Utente("Luisa", "Russo", m1, "l.genovese20@studenti.unisa.it");

        // Test utente cognome minore:
        Utente u3 = new Utente("Luisa", "Apicella", m1, "l.genovese20@studenti.unisa.it");

        // Test utente cognome uguale, nome maggiore:
        Utente u4 = new Utente("Maria", "Genovese", m1, "l.genovese20@studenti.unisa.it");

        // Test utente cognome uguale, nome minore:
        Utente u5 = new Utente("Anna", "Genovese", m1, "l.genovese20@studenti.unisa.it");

        // Test utente nome e cognome uguali, matricola maggiore:
        Utente u6 = new Utente("Luisa", "Genovese", m2, "l.genovese20@studenti.unisa.it");

        // Test utente nome e cognome uguali, matricola minore:
        Utente u7 = new Utente("Luisa", "Genovese", m3, "l.genovese20@studenti.unisa.it");

        assertTrue(u.compareTo(u1) == 0);
        assertTrue(u.compareTo(u2) < 0);
        assertTrue(u.compareTo(u3) > 0);
        assertTrue(u.compareTo(u4) < 0);
        assertTrue(u.compareTo(u5) > 0);
        assertTrue(u.compareTo(u6) < 0);
        assertTrue(u.compareTo(u7) > 0);
    }

}
