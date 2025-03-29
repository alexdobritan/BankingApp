package org.poo.cb;

public class ExchangeMoneyCommand implements Command {
    private final Aplicatie aplicatie;
    private final String email;
    private final String sourceCurrency;
    private final String destinationCurrency;
    private final double amount;

    public ExchangeMoneyCommand(Aplicatie aplicatie, String email, String sourceCurrency, String destinationCurrency, double amount) {
        this.aplicatie = aplicatie;
        this.email = email;
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.amount = amount;
    }

    @Override
    public void execute() {
        aplicatie.exchangeMoney(email, sourceCurrency, destinationCurrency, amount);
    }
}
