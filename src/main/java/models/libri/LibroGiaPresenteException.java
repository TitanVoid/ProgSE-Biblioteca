/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.libri;

import models.OggettoGiaPresenteException;

/**
 *
 * @author erica
 */
public class LibroGiaPresenteException extends OggettoGiaPresenteException {

    /**
     * Creates a new instance of <code>LibroGiaPresenteException</code> without
     * detail message.
     */
    public LibroGiaPresenteException() {
    }

    /**
     * Constructs an instance of <code>LibroGiaPresenteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LibroGiaPresenteException(String msg) {
        super(msg);
    }
}
