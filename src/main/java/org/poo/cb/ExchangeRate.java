package org.poo.cb;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExchangeRate {
    private final Map<String, Map<String, Double>> rates;

    public ExchangeRate() {
        this.rates = new HashMap<>();
    }

    public void incarcaRataSchimb(String fisier) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + fisier))) {
            String line;
            String[] headers = parseHeaders(br.readLine());
            while ((line = br.readLine()) != null) {
                processLine(line, headers);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private String[] parseHeaders(String headerLine) {
        if (headerLine == null || headerLine.isEmpty()) {
            return new String[0];
        }
        return headerLine.split(",");
    }

    private void processLine(String line, String[] headers) {
        String[] parts = line.split(",");
        if (parts.length != headers.length) {
            return;
        }
        String currency = parts[0];
        Map<String, Double> exchangeRates = new HashMap<>();
        for (int i = 1; i < parts.length; i++) {
            try {
                exchangeRates.put(headers[i], Double.parseDouble(parts[i]));
            } catch (NumberFormatException ignored) {
            }
        }
        rates.put(currency, exchangeRates);
    }

    public double getExchangeRate(String sourceCurrency, String destinationCurrency) {
        if (rates.containsKey(sourceCurrency)) {
            Map<String, Double> exchangeRates = rates.get(sourceCurrency);
            if (exchangeRates.containsKey(destinationCurrency)) {
                return exchangeRates.get(destinationCurrency);
            }
        }
        return -1;
    }
}
