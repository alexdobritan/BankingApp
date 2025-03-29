package org.poo.cb;

import static org.poo.cb.Aplicatie.exchangeRate;

public class Fatada {
    private final Aplicatie aplicatie;

    // Singleton
    public Fatada() {
        this.aplicatie = Aplicatie.getInstance();
    }

    // Command
    public void createUser(String email, String prenume, String nume, String adresa) {
        Command command = new CreateUserCommand(aplicatie, email, prenume, nume, adresa);
        command.execute();
    }

    // Command
    public void addFriend(String emailUtilizator, String emailPrieten) {
        Command command = new AddFriendCommand(aplicatie, emailUtilizator, emailPrieten);
        command.execute();
    }

    // Command
    public void addAccount(String email, String valuta) {
        Command command = new AddAccountCommand(aplicatie, email, valuta);
        command.execute();
    }

    // Command
    public void addMoney(String email, String valuta, double suma) {
        Command command = new AddMoneyCommand(aplicatie, email, valuta, suma);
        command.execute();
    }

    // Command
    public void listUser(String email) {
        Command command = new ListUserCommand(aplicatie, email);
        command.execute();
    }

    // Command
    public void listPortfolio(String email) {
        Command command = new ListPortfolioCommand(aplicatie, email);
        command.execute();
    }

    // Command
    public void buyStocks(String email, String company, int noOfStocks, String stockPriceFilePath) {
        Command command = new BuyStocksCommand(aplicatie, email, company, noOfStocks, stockPriceFilePath);
        command.execute();
    }

    // Command
    public void exchangeMoney(String email, String sourceCurrency, String destinationCurrency, double amount) {
        Command command = new ExchangeMoneyCommand(aplicatie, email, sourceCurrency, destinationCurrency, amount);
        command.execute();
    }

    // Command
    public void transferMoney(String email, String emailDestinatar, String valuta, double suma) {
        Command command = new TransferMoneyCommand(aplicatie, email, emailDestinatar, valuta, suma);
        command.execute();
    }

    // Command
    public void recommendStocks(String stockPriceFilePath) {
        Command command = new RecommendStocksCommand(aplicatie, stockPriceFilePath);
        command.execute();
    }

    public void loadExchangeRate(String caleRataSchimb) {
        exchangeRate.incarcaRataSchimb(caleRataSchimb);
    }
}
