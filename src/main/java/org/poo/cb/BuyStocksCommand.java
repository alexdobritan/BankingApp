package org.poo.cb;

public class BuyStocksCommand implements Command {
    private final Aplicatie aplicatie;
    private final String email;
    private final String company;
    private final int noOfStocks;
    private final String stockPriceFilePath;

    public BuyStocksCommand(Aplicatie aplicatie, String email, String company, int noOfStocks, String stockPriceFilePath) {
        this.aplicatie = aplicatie;
        this.email = email;
        this.company = company;
        this.noOfStocks = noOfStocks;
        this.stockPriceFilePath = stockPriceFilePath;
    }

    @Override
    public void execute() {
        PretActiune stockPrice = new PretActiune();
        stockPrice.stockPrice(stockPriceFilePath);
        aplicatie.buyStocks(email, company, noOfStocks, stockPrice);
    }
}
