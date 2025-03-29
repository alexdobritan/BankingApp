package org.poo.cb;

public class AddMoneyCommand implements Command {
    private final Aplicatie aplicatie;
    private final String email;
    private final String valuta;
    private final double suma;

    public AddMoneyCommand(Aplicatie aplicatie, String email, String valuta, double suma) {
        this.aplicatie = aplicatie;
        this.email = email;
        this.valuta = valuta;
        this.suma = suma;
    }

    @Override
    public void execute() {
        aplicatie.alimentareCont(email, valuta, suma);
    }
}
