package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class Utilizator {

    private final String email;
    private final String prenume;
    private final String nume;
    private final String adresa;
    private final Portofoliu portofoliu;
    private final List<Utilizator> prieteni;

    private Utilizator(Builder builder) {
        this.email = builder.email;
        this.prenume = builder.prenume;
        this.nume = builder.nume;
        this.adresa = builder.adresa;
        this.portofoliu = new Portofoliu();
        this.prieteni = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public Portofoliu getPortofoliu() {
        return portofoliu;
    }

    public List<Utilizator> getPrieteni() {
        return prieteni;
    }

    public void List() {
        System.out.print("{" + "\"email\":" + "\"" + email + "\"," + "\"firstname\":" + "\"" + prenume + "\"," +
                "\"lastname\":" + "\"" + nume + "\"," + "\"address\":" + "\"" + adresa + "\"," + "\"friends\":[");
        for (int i = 0; i < prieteni.size(); i++) {
            System.out.print("\"" + prieteni.get(i).getEmail() + "\"");
            if (i != prieteni.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]}");
    }

    //Builder
    public static class Builder {
        private final String email;
        private String prenume;
        private String nume;
        private String adresa;

        public Builder(String email) {
            this.email = email;
        }

        public Builder prenume(String prenume) {
            this.prenume = prenume;
            return this;
        }

        public Builder nume(String nume) {
            this.nume = nume;
            return this;
        }

        public Builder adresa(String adresa) {
            this.adresa = adresa;
            return this;
        }

        public Utilizator build() {
            return new Utilizator(this);
        }
    }
}
