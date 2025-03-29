package org.poo.cb;

public class Actiune {
    private final String numeCompanie;
    private final int numarActiuni;

    public Actiune(String numeCompanie, int valoare) {
        this.numeCompanie = numeCompanie;
        this.numarActiuni = valoare;
    }
    @Override
    public String toString() {
        return "{\"stockName\":\"" + numeCompanie + "\",\"amount\":" + numarActiuni + "}";
    }
}