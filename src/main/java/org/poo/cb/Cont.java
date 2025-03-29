package org.poo.cb;

public class Cont {
    private double sold;
    private final String tipValuta;

    public Cont(String valuta) {
        this.sold = 0;
        this.tipValuta = valuta;
    }

    public String getValuta() {
        return tipValuta;
    }

    public double getSold() {
        return sold;
    }

    public void adaugaBani(double suma) {
        this.sold += suma;
    }

    public void retrageBani(double suma) {
        this.sold -= suma;
    }

    @Override
    public String toString() {
        return "{\"currencyName\":\"" + tipValuta + "\",\"amount\":\"" + String.format("%.2f", sold) + "\"}";
    }
}