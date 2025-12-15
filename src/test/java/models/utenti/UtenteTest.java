package models.utenti;

import java.time.LocalDate;
import models.FormatoCampiErratoException;
import models.ISBN;
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

    @Before
    public void setUp() {
        Matricola m = new Matricola("0612708796");
        u = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");
    }

    // TEST COSTRUTTORE
    @Test
    public void testCostruttoreUtente() {
        Matricola m = new Matricola("0612708796");
        Utente utente = new Utente("Luisa", "Genovese", m, "l.genovese20@studenti.unisa.it");

        assertNotNull(utente);
        
        assertEquals("Luisa", utente.getNome());
        assertEquals("Genovese", utente.getCognome());
        assertEquals(m, utente.getMatricolaUtente());
        assertEquals("l.genovese20@studenti.unisa.it", utente.getEmail());
        assertNotNull(utente.getPrestitiAttivi());
        assertTrue(utente.getPrestitiAttivi().isEmpty());
    }

    // TEST METODI GETTER
    @Test
    public void testGetMatricolaUtente() {
        Matricola m = new Matricola("0612708796");

        assertNotNull(u.getMatricolaUtente());
        assertEquals(m, u.getMatricolaUtente());
    }

    @Test
    public void testGetEmail() {
        assertNotNull(u.getEmail());
        assertEquals("l.genovese20@studenti.unisa.it", u.getEmail());
    }

    @Test
    public void testGetPrestitiAttivi() {
        assertNotNull(u.getPrestitiAttivi());
    }

    // TEST METODO SETTER
    @Test
    public void testSetEmail() {
        u.setEmail("l.genovese@studenti.unisa.it");

        assertEquals("l.genovese@studenti.unisa.it", u.getEmail());
    }

    // TEST AGGIUNGIPRESTITO
    @Test
    public void testAggiungiPrestito() {
        Matricola m = new Matricola("0612708796");
        
        Prestito p = new Prestito(m, new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(3));

        u.aggiungiPrestito(p);

        assertTrue(u.getPrestitiAttivi().contains(p));
        assertEquals(1, u.getPrestitiAttivi().size());
    }

    // TEST RIMUOVIPRESTITO
    @Test
    public void testRimuoviPrestito() {
        Matricola m = new Matricola("0612708796");
       
        Prestito p = new Prestito(m, new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(3));

        u.aggiungiPrestito(p);
        u.rimuoviPrestito(p);

        assertFalse(u.getPrestitiAttivi().contains(p));
        assertEquals(0, u.getPrestitiAttivi().size());
    }

    // TEST VERIFICA EMAIL
    @Test
    public void testVerificaEmailValida() {
        assertTrue(Utente.verificaEmail("l.genovese20@studenti.unisa.it"));
    }
    
    @Test
    public void testVerificaEmailNonValida() {
        assertFalse(Utente.verificaEmail("luisagenovese@studenti.unisa.it"));
    }
    
    @Test
    public void testVerificaEmailDominioNonValido() {
        assertFalse(Utente.verificaEmail("l.genovese20@gmail.com"));
    }

    // TEST VERIFICA UTENTE
    @Test
    public void testVerificaUtenteValido() {
        assertTrue(Utente.verificaUtente("Luisa", "Genovese", "0612708796", "l.genovese20@studenti.unisa.it"));
    }
    
    @Test
    public void testVerificaUtenteNomeNonValido() {
        assertThrows(FormatoCampiErratoException.class, () -> { Utente.verificaUtente("L", "Genovese", "0612708796", "l.genovese20@studenti.unisa.it"); });
    }
    
    @Test
    public void testVerificaUtenteCognomeNonValido() {    
        assertThrows(FormatoCampiErratoException.class, () -> { Utente.verificaUtente("Luisa", "G", "0612708796", "l.genovese20@studenti.unisa.it"); });
    }
    
    @Test
    public void testVerificaUtenteMatricolaNonValida() {
        assertThrows(FormatoCampiErratoException.class, () -> { Utente.verificaUtente("Luisa", "Genovese", "06127", "l.genovese20@studenti.unisa.it"); });
    }
    
    @Test
    public void testVerificaUtenteEmailNonValida() {
        assertThrows(FormatoCampiErratoException.class, () -> { Utente.verificaUtente("Luisa", "Genovese", "0612708796", "lgenovese20@studenti.unisa.it"); });
    }

    // TEST EQUALS
    @Test
    public void testEqualsUtentiUguali() {
        assertEquals(u, new Utente("Luisa", "Genovese", new Matricola("0612708796"), "l.genovese20@studenti.unisa.it"));
    }
    
    @Test
    public void testEqualsUtentiDiversi() {
        assertNotEquals(u, new Utente("Luisa", "Genovese", new Matricola("0612708797"), "l.genovese20@studenti.unisa.it"));
    }
    
    @Test
    public void testEqualsUtenteConNull() {
        assertNotEquals(u, null);
    }

    // TEST COMPARETO
    @Test
    public void testCompareToUguale() {
        assertEquals(0, u.compareTo(new Utente("Luisa", "Genovese", new Matricola("0612708796"), "l.genovese20@studenti.unisa.it")));
    }
    
    @Test
    public void testCompareToMinore() {
        // Utente con cognome maggiore:
        assertTrue(u.compareTo(new Utente("Luisa", "Russo", new Matricola("0612708796"), "l.genovese20@studenti.unisa.it")) < 0);
        // Utente con cognome uguale, nome maggiore:
        assertTrue(u.compareTo(new Utente("Maria", "Genovese", new Matricola("0612708796"), "l.genovese20@studenti.unisa.it")) < 0);
        // Utente con cognome e nome uguali, matricola maggiore:
        assertTrue(u.compareTo(new Utente("Luisa", "Genovese", new Matricola("0612708797"), "l.genovese20@studenti.unisa.it")) < 0);
    }
    
    @Test
    public void testCompareToMaggiore() {
        // Utente con cognome minore:
        assertTrue(u.compareTo(new Utente("Luisa", "Apicella", new Matricola("0612708796"), "l.genovese20@studenti.unisa.it")) > 0);
        // Utente con cognome uguale, nome minore:
        assertTrue(u.compareTo(new Utente("Anna", "Genovese", new Matricola("0612708796"), "l.genovese20@studenti.unisa.it")) > 0);
        // Utente con cognome e nome uguali, matricola minore:
        assertTrue(u.compareTo(new Utente("Luisa", "Genovese", new Matricola("0612708795"), "l.genovese20@studenti.unisa.it")) > 0);
    }
}
