package models.prestiti;

import java.time.DateTimeException;
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

    @Before
    public void setUp() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");
        p = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3));
    }

    @Test
    public void testCostruttorePrestito() {
        Prestito p = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(3));
        assertNotNull(p);
        assertEquals(new Matricola("0612708796"), p.getMatricolaUtente());
        assertEquals(new ISBN("883010471X"), p.getCodiceISBNLibro());
        assertEquals(LocalDate.now(), p.getDataInizio());
        assertEquals(LocalDate.now().plusDays(3), p.getDataScadenza());
    }

    @Test
    public void testGetMatricolaUtente() {
        Matricola m = new Matricola("0612708796");
        assertEquals(m, p.getMatricolaUtente());
    }

    @Test
    public void testGetCodiceISBNLibro() {
        ISBN isbn = new ISBN("883010471X");
        assertEquals(isbn, p.getCodiceISBNLibro());
    }

    @Test
    public void testGetDataInizio() {
        assertEquals(LocalDate.now(), p.getDataInizio());
    }

    @Test
    public void testGetDataScadenza() {
        assertEquals(LocalDate.now().plusDays(3), p.getDataScadenza());
    }

    @Test
    public void testGetDataRestituzione() {
        assertNull(p.getDataRestituzione());
    }

    @Test
    public void testSetDataScadenza() {
        p.setDataScadenza(LocalDate.now().plusDays(10));

        assertEquals(LocalDate.now().plusDays(10), p.getDataScadenza());
    }

    @Test
    public void testSetDataRestituzione() {
        p.setDataRestituzione(LocalDate.now());

        assertEquals(LocalDate.now(), p.getDataRestituzione());
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
        // Test con formato corretto ma data non valida:
        assertThrows(DateTimeException.class, () -> {
            Prestito.verificaDataScadenza("2025-13-50");
        });
    }

    @Test
    public void testCompareTo() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");

        // Test prestito con data di scadenza uguale:
        Prestito p1 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3));
        assertEquals(0, p.compareTo(p1));
        // Test prestito con data di scadenza precedente:
        Prestito p2 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(2));
        assertTrue(p.compareTo(p2) > 0);
        // Test prestito con data di scadenza successiva:
        Prestito p3 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(4));
        assertTrue(p.compareTo(p3) < 0);
    }
}
