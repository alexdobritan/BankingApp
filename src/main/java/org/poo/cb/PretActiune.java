package org.poo.cb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PretActiune {
    private final Map<String, Double[]> actiuni;

    public PretActiune() {
        this.actiuni = new HashMap<>();
    }

    public void stockPrice(String fisier) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + fisier))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                String codActiune = parts[0];
                Double[] pret = new Double[10];

                for (int i = 0; i < 10; i++) {
                    try {
                        pret[i] = Double.parseDouble(parts[parts.length - 1 - i]);
                    } catch (NumberFormatException e) {
                        pret[i] = null;
                    }
                }

                actiuni.put(codActiune, pret);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Double[]> getActiuni() {
        return actiuni;
    }

    public List<Double> getStockPrice(String company) {
        if (actiuni.containsKey(company)) {
            return List.of(actiuni.get(company));
        }
        return null;
    }
}
