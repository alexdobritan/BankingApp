package org.poo.cb;

public class TransferMoneyCommand implements Command {
    private final Aplicatie aplicatie;
    private final String email;
    private final String emailDestinatar;
    private final String valuta;
    private final double suma;

    public TransferMoneyCommand(Aplicatie aplicatie, String email, String emailDestinatar, String valuta, double suma) {
        this.aplicatie = aplicatie;
        this.email = email;
        this.emailDestinatar = emailDestinatar;
        this.valuta = valuta;
        this.suma = suma;
    }

    @Override
    public void execute() {
        aplicatie.transferMoney(email, emailDestinatar, valuta, suma);
    }
}
