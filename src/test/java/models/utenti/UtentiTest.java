/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utenti;

import java.util.ArrayList;
import java.util.List;
import models.Matricola;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class UtentiTest {
    private Utenti utenti;

    public UtentiTest() {
    }

    @Before
    public void setUp() throws Exception {
        utenti = new Utenti();

        utenti.aggiungi(new Utente("Luisa", "Genovese", new Matricola("0612708796"), "l.genovese20@studenti.unisa.it"));
        utenti.aggiungi(
                new Utente("Erica", "Brancaccio", new Matricola("0612709465"), "e.brancaccio@studenti.unisa.it"));
        utenti.aggiungi(new Utente("Francesco", "Altieri", new Matricola("0612712649"), "f.altieri@studenti.unisa.it"));
        utenti.aggiungi(new Utente("Paolo", "Alfe", new Matricola("0612708112"), "p.alfe@studenti.unisa.it"));
    }

    @Test
    public void testCostruttoreUtenti() {
        Utenti utenti2 = new Utenti();

        assertNotNull(utenti2.getListaUtenti());
        assertTrue(utenti2.getListaUtenti().isEmpty());
    }

    @Test
    public void testGetListaUtenti() {
        assertTrue(utenti.getListaUtenti() instanceof ArrayList);
    }

    @Test
    public void testRicercaUtenti() {
        List<Utente> risultati = new ArrayList<>();

        risultati = utenti.ricercaUtenti("GENOVESE");

        assertTrue(risultati.contains(utenti.ottieni(new Matricola("0612708796"))));
        assertEquals(risultati.size(), 1);
    }

    @Test
    public void testAggiungi() throws Exception {
        // Test utente nuovo:
        Matricola m = new Matricola("0612712345");
        Utente u = new Utente("Mario", "Rossi", m, "m.rossi10@studenti.unisa.it");

        utenti.aggiungi(u);

        assertTrue(utenti.getListaUtenti().contains(u));
        assertTrue(utenti.esisteChiave(m));

        // Test utente duplicato:
        assertThrows(UtenteGiaPresenteException.class, () -> {
            utenti.aggiungi(u);
        });
    }

    @Test
    public void testRimuovi() {
        Matricola m = new Matricola("0612708796");
        Utente u = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");

        utenti.rimuovi(u);

        assertFalse(utenti.getListaUtenti().contains(u));
        assertFalse(utenti.esisteChiave(m));
    }

    @Test
    public void testModifica() {
        Matricola m = new Matricola("0612708796");

        Utente u = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
        u.setEmail("l.genovese@studenti.unisa.it");

        utenti.modifica(utenti.ottieni(m), u);

        assertEquals(utenti.ottieni(m).getEmail(), "l.genovese@studenti.unisa.it");
    }

    @Test
    public void testEsisteChiave() {
        assertTrue(utenti.esisteChiave(new Matricola("0612708796")));
        assertTrue(utenti.esisteChiave(new Matricola("0612709465")));
        assertTrue(utenti.esisteChiave(new Matricola("0612712649")));
        assertTrue(utenti.esisteChiave(new Matricola("0612708112")));

        assertFalse(utenti.esisteChiave(new Matricola("0612712345")));
    }

    @Test
    public void testOttieni() {
        Matricola m1 = new Matricola("0612708796");
        Matricola m2 = new Matricola("0612709465");
        Matricola m3 = new Matricola("0612712649");
        Matricola m4 = new Matricola("0612708112");

        Utente u1 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        Utente u2 = new Utente("Erica", "Brancaccio", m2, "e.brancaccio@studenti.unisa.it");
        Utente u3 = new Utente("Francesco", "Altieri", m3, "f.altieri@studenti.unisa.it");
        Utente u4 = new Utente("Paolo", "Alfe", m4, "p.alfe@studenti.unisa.it");

        Matricola m5 = new Matricola("0612712345");

        assertEquals(utenti.ottieni(m1), u1);
        assertEquals(utenti.ottieni(m2), u2);
        assertEquals(utenti.ottieni(m3), u3);
        assertEquals(utenti.ottieni(m4), u4);

        assertEquals(utenti.ottieni(m5), null);
    }

}
