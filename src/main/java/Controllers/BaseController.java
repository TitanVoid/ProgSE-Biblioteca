package Controllers;

import Models.Biblioteca;

public abstract class BaseController {
    protected Biblioteca biblioteca;

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}