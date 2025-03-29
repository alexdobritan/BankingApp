package org.poo.cb;

public class AddAccountCommand implements Command {
    private final Aplicatie aplicatie;
    private final String email;
    private final String valuta;

    public AddAccountCommand(Aplicatie aplicatie, String email, String valuta) {
        this.aplicatie = aplicatie;
        this.email = email;
        this.valuta = valuta;
    }

    @Override
    public void execute() {
        aplicatie.adaugaCont(email, valuta);
    }
}
