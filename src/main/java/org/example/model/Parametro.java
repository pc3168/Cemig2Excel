package org.example.model;

public enum Parametro {

    CLI("--cli"),
    GUI("--gui");

    private String parametro;

    Parametro(String parametro) {
        this.parametro = parametro;
    }

    public String getParametro() {
        return parametro;
    }
}
