package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import static org.poo.cb.Aplicatie.utilizatori;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Running Main");
        } else {
            String numeTest = args[2];
            String caleIntrare = "src/main/resources/" + numeTest;
            //Facade
            Fatada fatada = new Fatada();
            String caleRataSchimb = args[0];
            fatada.loadExchangeRate(caleRataSchimb);

            try (BufferedReader br = new BufferedReader(new FileReader(caleIntrare))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    String command = parts[0];
                    String command2 = parts[1];

                    switch (command) {
                        case "CREATE" -> {
                            String email = parts[2];
                            String prenume = parts[3];
                            String nume = parts[4];
                            String adresa = String.join(" ", Arrays.copyOfRange(parts, 5, parts.length));
                            fatada.createUser(email, prenume, nume, adresa);
                        }
                        case "ADD" -> {
                            if ("FRIEND".equals(command2)) {
                                String emailUtilizator = parts[2];
                                String emailPrieten = parts[3];
                                fatada.addFriend(emailUtilizator, emailPrieten);
                            } else if ("ACCOUNT".equals(command2)) {
                                String email = parts[2];
                                String valuta = parts[3];
                                fatada.addAccount(email, valuta);
                            } else if ("MONEY".equals(command2)) {
                                String email = parts[2];
                                String valuta = parts[3];
                                double suma = Double.parseDouble(parts[4]);
                                fatada.addMoney(email, valuta, suma);
                            }
                        }
                        case "LIST" -> {
                            if ("USER".equals(command2)) {
                                String emailList = parts[2];
                                fatada.listUser(emailList);
                            } else if ("PORTFOLIO".equals(command2)) {
                                String emailList = parts[2];
                                fatada.listPortfolio(emailList);
                            }
                        }
                        case "BUY" -> {
                            String email = parts[2];
                            String company = parts[3];
                            int noOfStocks = Integer.parseInt(parts[4]);
                            fatada.buyStocks(email, company, noOfStocks, args[1]);
                        }
                        case "EXCHANGE" -> {
                            String email = parts[2];
                            String sourceCurrency = parts[3];
                            String destinationCurrency = parts[4];
                            double amount = Double.parseDouble(parts[5]);
                            fatada.exchangeMoney(email, sourceCurrency, destinationCurrency, amount);
                        }
                        case "TRANSFER" -> {
                            String email = parts[2];
                            String emailDestinatar = parts[3];
                            String valuta = parts[4];
                            double suma = Double.parseDouble(parts[5]);
                            fatada.transferMoney(email, emailDestinatar, valuta, suma);
                        }
                        case "RECOMMEND" -> fatada.recommendStocks(args[1]);
                        case null, default -> System.out.println("Unknown command: " + command);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            utilizatori.clear();
        }
    }
}
