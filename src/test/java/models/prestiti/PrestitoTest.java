/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.prestiti;

import java.time.LocalDate;
import models.FormatoCampiErratoException;
import models.ISBN;
import models.Matricola;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class PrestitoTest {
    private Prestito p;

    public PrestitoTest() {
    }

    @Before
    public void setUp() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");
        p = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3));
    }

    @Test
    public void testCostruttorePrestito() {
    }

    @Test
    public void testGetMatricolaUtente() {
        Matricola m = new Matricola("0612708796");
        assertEquals(p.getMatricolaUtente(), m);
    }

    @Test
    public void testGetCodiceISBNLibro() {
        ISBN isbn = new ISBN("883010471X");
        assertEquals(p.getCodiceISBNLibro(), isbn);
    }

    @Test
    public void testGetDataInizio() {
        assertEquals(p.getDataInizio(), LocalDate.now());
    }

    @Test
    public void testGetDataScadenza() {
        assertEquals(p.getDataScadenza(), LocalDate.now().plusDays(3));
    }

    @Test
    public void testGetDataRestituzione() {
        assertEquals(p.getDataRestituzione(), null);
    }

    @Test
    public void testSetDataScadenza() {
        p.setDataScadenza(LocalDate.now().plusDays(10));

        assertEquals(p.getDataScadenza(), LocalDate.now().plusDays(10));
    }

    @Test
    public void testSetDataRestituzione() {
        p.setDataRestituzione(LocalDate.now());

        assertEquals(p.getDataRestituzione(), LocalDate.now());
    }

    @Test
    public void testIsAttivo() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");
        // Prestito attivo:
        Prestito p1 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3));
        // Prestito estinto:
        Prestito p2 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3));
        p2.setDataRestituzione(LocalDate.now());

        assertTrue(p1.isAttivo());
        assertFalse(p2.isAttivo());
    }

    @Test
    public void testVerificaDataScadenza() {
        // Test con data di scadenza valida:
        assertTrue(Prestito.verificaDataScadenza("2025-12-20"));
        // Test con data di scadenza non valida:
        assertThrows(FormatoCampiErratoException.class, () -> {
            Prestito.verificaDataScadenza("2025/12/20");
        });
        assertThrows(FormatoCampiErratoException.class, () -> {
            Prestito.verificaDataScadenza("2025-12-12");
        });
    }

    @Test
    public void testCompareTo() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");

        // Test prestito con data di scadenza uguale:
        Prestito p1 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3));
        assertTrue(p.compareTo(p1) == 0);
        // Test prestito con data di scadenza precedente:
        Prestito p2 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(2));
        assertTrue(p.compareTo(p2) > 0);
        // Test prestito con data di scadenza successiva:
        Prestito p3 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(4));
        assertTrue(p.compareTo(p3) < 0);
    }

}
