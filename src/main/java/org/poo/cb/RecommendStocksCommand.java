package org.poo.cb;

public class RecommendStocksCommand implements Command {
    private final Aplicatie aplicatie;
    private final String stockPrice;

    public RecommendStocksCommand(Aplicatie aplicatie, String stockPrice) {
        this.aplicatie = aplicatie;
        this.stockPrice = stockPrice;
    }

    @Override
    public void execute() {
        System.out.print("{\"stocksToBuy\":[");
        aplicatie.recomandaActiuni(stockPrice);
        System.out.println("]}");
    }
}
