/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.prestiti;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
    public Prestiti prestiti;
    
    public PrestitiTest() {
    }

    @Before
    public void setUp() {
        prestiti = new Prestiti();
        
        LocalDate now = LocalDate.now();
        
        prestiti.aggiungi(new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), now, now.plusDays(5)));
        prestiti.aggiungi(new Prestito(new Matricola("0612709452"), new ISBN("9788867582143"), now, now.plusDays(10)));
        prestiti.aggiungi(new Prestito(new Matricola("0612712846"), new ISBN("8883379071"), now, now.plusDays(15)));
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
        assertEquals(prestiti.getListaPrestiti().size(), 4);
    }

    @Test
    public void testRimuovi() {
        Prestito p = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(5));
    
        prestiti.rimuovi(p);
        
        p.setDataRestituzione(LocalDate.now());
        
        assertTrue(prestiti.getListaPrestiti().contains(p));
        assertEquals(prestiti.getListaPrestiti().size(), 3);
        assertFalse(prestiti.getListaPrestiti().get(Collections.binarySearch(prestiti.getListaPrestiti(), p)).isAttivo());
    }

    @Test
    public void testModifica() {
        Prestito p1 = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(5));

        Prestito p2 = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(5));
        p2.setDataScadenza(LocalDate.now().plusDays(20));
        
        prestiti.modifica(p1, p2);
        
        assertFalse(prestiti.getListaPrestiti().contains(p1));
        assertTrue(prestiti.getListaPrestiti().contains(p2));
        assertEquals(prestiti.getListaPrestiti().size(), 3);
        assertEquals(prestiti.getListaPrestiti().get(Collections.binarySearch(prestiti.getListaPrestiti(), p2)).getDataScadenza(), LocalDate.now().plusDays(20));        
    }

    @Test
    public void testFiltra() {
        Prestito p = new Prestito(new Matricola("0612708796"), new ISBN("883010471X"), LocalDate.now(), LocalDate.now().plusDays(5));

        prestiti.rimuovi(p);
        
        // Test con filtro TUTTI:
        List<Prestito> tutti = prestiti.filtra(Filtro.TUTTI);
        
        assertEquals(tutti.size(), 3);
        
        // Test con filtro ATTIVI:
        List<Prestito> attivi = prestiti.filtra(Filtro.ATTIVI);
        
        assertEquals(attivi.size(), 2);
        
        // Test con filtro CONCLUSI:
        List<Prestito> conclusi = prestiti.filtra(Filtro.CONCLUSI);
        
        assertEquals(conclusi.size(), 1);
    }
    
}
