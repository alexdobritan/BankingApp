package org.poo.cb;

import java.util.*;

public class Portofoliu {
    private final List<Cont> conturi;
    private final List<Actiune> actiuni;
    private int nrActiuni;
    private int nrConturi;

    public Portofoliu() {
        conturi = new ArrayList<>();
        actiuni = new ArrayList<>();
        nrActiuni = 0;
        nrConturi = 0;
    }

    public void adaugaCont(Cont cont) {
        conturi.add(cont);
        nrConturi++;
    }

    public void adaugaActiune(Actiune actiune) {
        actiuni.add(actiune);
        nrActiuni++;
    }

    public void List() {
        System.out.print("{\"stocks\": [");
        for (int i = 0; i < nrActiuni; i++) {
            System.out.print(actiuni.get(i).toString());
            if (i < nrActiuni - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("], \"accounts\": [");
        for (int i = 0; i < nrConturi; i++) {
            System.out.print(conturi.get(i).toString());
            if (i < nrConturi - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println("}");
    }

    public Cont getCont(String sourceCurrency) {
        for (Cont cont : conturi) {
            if (cont.getValuta().equals(sourceCurrency)) {
                return cont;
            }
        }
        return null;
    }
}