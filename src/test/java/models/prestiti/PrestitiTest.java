package models.prestiti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.ISBN;
import models.Matricola;
import models.servizi.Filtro;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author luisagenovese
 */
public class PrestitiTest {
    private Prestiti prestiti;

    @Before
    public void setUp() {
        prestiti = new Prestiti();
    }
    
    @Test
    public void testCostruttorePrestiti() {
        Prestiti prestiti2 = new Prestiti();
        
        assertNotNull(prestiti2.getListaPrestiti());
        assertTrue(prestiti2.getListaPrestiti().isEmpty());
    }
    
    @Test
    public void testGetListaPrestiti() {
        assertNotNull(prestiti.getListaPrestiti());
        assertTrue(prestiti.getListaPrestiti() instanceof ArrayList);
    }

    @Test
    public void testAggiungi() {
        Prestito p = new Prestito(new Matricola("0612701523"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(20));

        prestiti.aggiungi(p);
        
        assertTrue(prestiti.getListaPrestiti().contains(p));
        assertEquals(1, prestiti.getListaPrestiti().size());
    }

    @Test
    public void testRimuovi() {
        Prestito p = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(5));

        prestiti.aggiungi(p);
        prestiti.rimuovi(p);
        
        assertTrue(prestiti.getListaPrestiti().contains(p));
        assertEquals(1, prestiti.getListaPrestiti().size());
        assertFalse(prestiti.getListaPrestiti().get(prestiti.getListaPrestiti().indexOf(p)).isAttivo());
    }

    @Test
    public void testModifica() {
        Prestito p1 = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(5));
        Prestito p2 = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(5));
        prestiti.aggiungi(p1);
        p2.setDataScadenza(LocalDate.now().plusDays(20));

        prestiti.modifica(p1, p2);
        
        assertFalse(prestiti.getListaPrestiti().contains(p1));
        assertTrue(prestiti.getListaPrestiti().contains(p2));
        assertEquals(1, prestiti.getListaPrestiti().size());
        assertEquals(prestiti.getListaPrestiti().get(prestiti.getListaPrestiti().indexOf(p2)).getDataScadenza(), LocalDate.now().plusDays(20));
    }

    @Test
    public void testFiltra() {
        LocalDate now = LocalDate.now();
        Prestito p1 = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), now, now.plusDays(5));
        Prestito p2 = new Prestito(new Matricola("0612709452"), new ISBN("9788867582143"), now, now.plusDays(10));
        Prestito p3 = new Prestito(new Matricola("0612712846"), new ISBN("8883379071"), now, now.plusDays(15));

        prestiti.aggiungi(p1);
        prestiti.aggiungi(p2);
        prestiti.aggiungi(p3);
        prestiti.rimuovi(p1);
        prestiti.rimuovi(p3);
        
        // Test con filtro TUTTI:
        List<Prestito> tutti = prestiti.filtra(Filtro.TUTTI);
        
        assertEquals(3, tutti.size());
        
        // Test con filtro ATTIVI:
        List<Prestito> attivi = prestiti.filtra(Filtro.ATTIVI);
        
        assertEquals(1, attivi.size());
        
        // Test con filtro CONCLUSI:
        List<Prestito> conclusi = prestiti.filtra(Filtro.CONCLUSI);
        
        assertEquals(2, conclusi.size());
    }
    
}
