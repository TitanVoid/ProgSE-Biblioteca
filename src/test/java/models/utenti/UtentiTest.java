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

    @Before
    public void setUp() throws Exception {
        utenti = new Utenti();

        utenti.aggiungi(new Utente("Luisa", "Genovese", new Matricola("0612708796"), "l.genovese@studenti.unisa.it"));
        utenti.aggiungi(new Utente("Erica", "Brancaccio", new Matricola("0612709465"), "e.brancaccio@studenti.unisa.it"));
        utenti.aggiungi(new Utente("Francesco", "Altieri", new Matricola("0612712649"), "f.altieri@studenti.unisa.it"));
        utenti.aggiungi(new Utente("Paolo", "Alfe", new Matricola("0612708112"), "p.alfe@studenti.unisa.it"));
    }

    // TEST COSTRUTTORE
    @Test
    public void testCostruttoreUtentiInizializzazioneLista() {
        Utenti utenti2 = new Utenti();

        assertNotNull(utenti2);
        
        assertNotNull(utenti2.getListaUtenti());
    }
    
    @Test
    public void testCostruttoreUtentiListaVuota() {
        Utenti utenti2 = new Utenti();

        assertTrue(utenti2.getListaUtenti().isEmpty());
    }    

    // TEST METODO GETTER
    @Test
    public void testGetListaUtenti() {
        assertNotNull(utenti.getListaUtenti());
        assertTrue(utenti.getListaUtenti() instanceof ArrayList);
    }

    // TEST RICERCA UTENTI
    @Test
    public void testRicercaUtenti() {
        List<Utente> risultati = utenti.ricercaUtenti("GeNoVeSe");

        assertTrue(risultati.contains(utenti.ottieni(new Matricola("0612708796"))));
        assertEquals(1, risultati.size());
    }

    // TEST AGGIUNGI
    @Test
    public void testAggiungiNuovo() throws Exception {
        Matricola m = new Matricola("0612712345");
        Utente u = new Utente("Mario", "Rossi", m, "m.rossi10@studenti.unisa.it");

        utenti.aggiungi(u);

        assertTrue(utenti.getListaUtenti().contains(u));
        assertTrue(utenti.esisteChiave(m));
    }
    
    @Test
    public void testAggiungiDuplicato() throws Exception {
        assertThrows(UtenteGiaPresenteException.class, () -> {
            utenti.aggiungi(new Utente("Luisa", "Genovese", new Matricola("0612708796"), "l.genovese@studenti.unisa.it"));
        }); 
    }

    // TEST RIMUOVI
    @Test
    public void testRimuovi() {
        Matricola m = new Matricola("0612708796");
        Utente u = new Utente("Luisa", "Genovese", m, "l.genovese@studenti.unisa.it");

        utenti.rimuovi(u);

        assertFalse(utenti.getListaUtenti().contains(u));
        assertFalse(utenti.esisteChiave(m));
    }

    // TEST MODIFICA
    @Test
    public void testModifica() {
        Matricola m = new Matricola("0612708796");

        Utente u = new Utente("Luisa", "Genovese", m, "l.genovese@studenti.unisa.it");
        u.setEmail("l.genovese20@studenti.unisa.it");

        utenti.modifica(utenti.ottieni(m), u);

        assertEquals("l.genovese20@studenti.unisa.it", utenti.ottieni(m).getEmail());
    }

    // TEST ESISTECHIAVE
    @Test
    public void testEsisteChiaveTrue() {
        assertTrue(utenti.esisteChiave(new Matricola("0612708796")));
        assertTrue(utenti.esisteChiave(new Matricola("0612709465")));
        assertTrue(utenti.esisteChiave(new Matricola("0612712649")));
        assertTrue(utenti.esisteChiave(new Matricola("0612708112")));
    }
    
    @Test
    public void testEsisteChiaveFalse() {
        assertFalse(utenti.esisteChiave(new Matricola("0612712345")));
    }   

    // TEST OTTIENI
    @Test
    public void testOttieniUtentePresente() {
        Matricola m1 = new Matricola("0612708796");
        Matricola m2 = new Matricola("0612709465");
        Matricola m3 = new Matricola("0612712649");
        Matricola m4 = new Matricola("0612708112");
        
        Utente u1 = new Utente("Luisa", "Genovese", m1, "l.genovese20@studenti.unisa.it");
        Utente u2 = new Utente("Erica", "Brancaccio", m2, "e.brancaccio@studenti.unisa.it");
        Utente u3 = new Utente("Francesco", "Altieri", m3, "f.altieri@studenti.unisa.it");
        Utente u4 = new Utente("Paolo", "Alfe", m4, "p.alfe@studenti.unisa.it");
                
        assertEquals(u1, utenti.ottieni(m1));
        assertEquals(u2, utenti.ottieni(m2));
        assertEquals(u3, utenti.ottieni(m3));
        assertEquals(u4, utenti.ottieni(m4));
    }
    
    @Test
    public void testOttieniUtenteAssente() {
        Matricola m5 = new Matricola("0612712345");
        
        assertNull(utenti.ottieni(m5));
    }       

}
