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

    // TEST COSTRUTTORE
    @Test
    public void testCostruttorePrestito() {
        Prestito prestito = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(3));
        
        assertNotNull(prestito);
        
        assertEquals(new Matricola("0612708796"), prestito.getMatricolaUtente());
        assertEquals(new ISBN("883010471X"), prestito.getCodiceISBNLibro());
        assertEquals(LocalDate.now(), prestito.getDataInizio());
        assertEquals(LocalDate.now().plusDays(3), prestito.getDataScadenza());
    }

    // TEST METODI GETTER
    @Test
    public void testGetMatricolaUtente() {
        Matricola m = new Matricola("0612708796");
        
        assertNotNull(m);
        assertEquals(m, p.getMatricolaUtente());
    }

    @Test
    public void testGetCodiceISBNLibro() {
        ISBN isbn = new ISBN("883010471X");
        
        assertNotNull(isbn);
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
    public void testGetDataRestituzioneAttivo() {
        assertNull(p.getDataRestituzione());
    }
    
    @Test
    public void testGetDataRestituzioneConcluso() {
        p.setDataRestituzione(LocalDate.now());
        
        assertEquals(LocalDate.now(), p.getDataRestituzione());
    }    

    // TEST METODI SETTER
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

    // TEST ISATTIVO
    @Test
    public void testIsAttivoTrue() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");

        Prestito p1 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3));

        assertTrue(p1.isAttivo());
    }
    
    @Test
    public void testIsAttivoFalse() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");

        Prestito p2 = new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3));
        p2.setDataRestituzione(LocalDate.now());

        assertFalse(p2.isAttivo());
    }

    // TEST VERIFICA DATA SCADENZA
    @Test
    public void testVerificaDataScadenzaValida() {
        assertTrue(Prestito.verificaDataScadenza("2026-12-20"));
    }
    
    @Test
    public void testVerificaDataScadenzaFormatoNonValido() {
        assertThrows(FormatoCampiErratoException.class, () -> {
            Prestito.verificaDataScadenza("2025/12/20");
        });
    }
    
    @Test
    public void testVerificaDataScadenzaDataNonValida() {
        // Data antecedente alla data odierna:
        assertThrows(FormatoCampiErratoException.class, () -> {
            Prestito.verificaDataScadenza("2025-12-12");
        });
        // Data non valida:
        assertThrows(DateTimeException.class, () -> {
            Prestito.verificaDataScadenza("2025-13-50");
        });
    }

    // TEST COMPARETO
    @Test
    public void testCompareToUguale() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");

        assertEquals(0, p.compareTo(new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(3))));
    }
    
    @Test
    public void testCompareToMinore() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");

        assertTrue(p.compareTo(new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(4))) < 0);
    }
    
    @Test
    public void testCompareToMaggiore() {
        Matricola m = new Matricola("0612708796");
        ISBN isbn = new ISBN("883010471X");

        assertTrue(p.compareTo(new Prestito(m, isbn, LocalDate.now(), LocalDate.now().plusDays(2))) > 0);
    }
}
