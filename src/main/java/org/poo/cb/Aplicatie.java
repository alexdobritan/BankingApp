package org.poo.cb;

import java.util.*;

public class Aplicatie {
    private static Aplicatie instance;

    public static HashMap<String, Utilizator> utilizatori;
    private static Map<String, Cont> conturi;

    public static ExchangeRate exchangeRate;

    //Constructor privat
    private Aplicatie() {
        utilizatori = new HashMap<>();
        conturi = new HashMap<>();
        exchangeRate = new ExchangeRate();
    }

    //Singleton
    public static Aplicatie getInstance() {
        if (instance == null) {
            synchronized (Aplicatie.class) {
                if (instance == null) {
                    instance = new Aplicatie();
                }
            }
        }
        return instance;
    }
    //Builder
    public static void adaugaUtilizator(String email, String prenume, String nume, String adresa) {
        if (!utilizatori.containsKey(email)) {
            Utilizator utilizator = new Utilizator.Builder(email)
                    .prenume(prenume)
                    .nume(nume)
                    .adresa(adresa)
                    .build();
            utilizatori.put(email, utilizator);
        } else {
            System.out.println("User with " + email + " already exists");
        }
    }


    public static void adaugaCont(String email, String valuta) {
        Utilizator utilizator = utilizatori.get(email);
        if (utilizator != null) {
            Cont cont = new Cont(valuta);
            utilizator.getPortofoliu().adaugaCont(cont);
            conturi.put(email, cont);
        } else {
            System.out.println("User with " + email + " doesn’t exist");
        }
    }

    public static void adaugaPrieten(String emailUtilizator, String emailPrieten) {
        Utilizator utilizator = utilizatori.get(emailUtilizator);
        Utilizator prieten = utilizatori.get(emailPrieten);
        if (utilizator != null && prieten != null) {
            utilizator.getPrieteni().add(prieten);
            prieten.getPrieteni().add(utilizator);
        } else {
            if (utilizator == null) {
                System.out.println("User with " + emailUtilizator + " doesn’t exist");
            }
            if (prieten == null) {
                System.out.println("User with " + emailPrieten + " doesn’t exist");
            }
        }
    }

    public static Utilizator cautaUtilizator(String email) {
        if (utilizatori.containsKey(email)) {
            return utilizatori.get(email);
        } else {
            System.out.println("User with " + email + " doesn't exist");
        }
        return null;
    }



    public void buyStocks(String email, String company, int noOfStocks, PretActiune stockPrice) {
        Utilizator utilizator = utilizatori.get(email);
        if (utilizator != null) {
            Actiune actiune = new Actiune(company, noOfStocks);
            double price = stockPrice.getStockPrice(company).get(0);
            if (price * noOfStocks >= utilizator.getPortofoliu().getCont("USD").getSold()) {
                System.out.println("Insufficient amount in account for buying stock");
            } else {
                utilizator.getPortofoliu().adaugaActiune(actiune);
                utilizator.getPortofoliu().getCont("USD").retrageBani(price * noOfStocks);
            }
        } else {
            System.out.println("User with " + email + " doesn’t exist");
        }
    }
    public void alimentareCont(String email, String valuta, double suma) {
        Utilizator utilizator = utilizatori.get(email);
        if (utilizator != null) {
            Cont cont = utilizator.getPortofoliu().getCont(valuta);
            if (cont != null) {
                cont.adaugaBani(suma);
            } else {
                System.out.println("User with " + email + " doesn’t have an account in " + valuta);
            }
        } else {
            System.out.println("User with " + email + " doesn’t exist");
        }
    }

    public void exchangeMoney(String email, String sourceCurrency, String destinationCurrency, double amount) {
        Utilizator utilizator = utilizatori.get(email);
        if (utilizator != null) {
            Cont contSursa = utilizator.getPortofoliu().getCont(sourceCurrency);
            Cont contDestinatie = utilizator.getPortofoliu().getCont(destinationCurrency);

            if (contSursa != null && contDestinatie != null) {
                if (contSursa.getSold() >= amount) {
                    double rataSchimb = exchangeRate.getExchangeRate(destinationCurrency, sourceCurrency);
                    double sumaDinSursa = amount * rataSchimb;

                    if (sumaDinSursa >= contSursa.getSold() / 2) {
                        sumaDinSursa += sumaDinSursa * 0.01;
                    }

                    contSursa.retrageBani(sumaDinSursa);
                    contDestinatie.adaugaBani(amount);
                } else {
                    System.out.println("Insufficient amount in account " + sourceCurrency + " for exchange");
                }
            } else {
                if (contSursa == null) {
                    System.out.println("User with " + email + " doesn’t have an account in " + sourceCurrency);
                }
                if (contDestinatie == null) {
                    System.out.println("User with " + email + " doesn’t have an account in " + destinationCurrency);
                }
            }
        } else {
            System.out.println("User with " + email + " doesn’t exist");
        }
    }

    public void transferMoney(String email, String emailDestinatar, String valuta, double suma) {
        Utilizator utilizator = utilizatori.get(email);
        Utilizator destinatar = utilizatori.get(emailDestinatar);

        if (utilizator != null && destinatar != null) {
            if (!utilizator.getPrieteni().contains(destinatar)) {
                System.out.println("You are not allowed to transfer money to " + emailDestinatar);
                return;
            }

            Cont contSursa = utilizator.getPortofoliu().getCont(valuta);
            Cont contDestinatar = destinatar.getPortofoliu().getCont(valuta);

            if (contSursa != null && contDestinatar != null) {
                if (contSursa.getSold() >= suma) {
                    contSursa.retrageBani(suma);
                    contDestinatar.adaugaBani(suma);
                } else {
                    System.out.println("Insufficient amount in account " + valuta + " for transfer");
                }
            } else {
                if (contSursa == null) {
                    System.out.println("User with " + email + " doesn’t have an account in " + valuta);
                }
                if (contDestinatar == null) {
                    System.out.println("User with " + emailDestinatar + " doesn’t have an account in " + valuta);
                }
            }
        } else {
            if (utilizator == null) {
                System.out.println("User with " + email + " doesn’t exist");
            }
            if (destinatar == null) {
                System.out.println("User with " + emailDestinatar + " doesn’t exist");
            }
        }
    }

    public void recomandaActiuni(String fisier) {
        double shortSMA;
        double longSMA;
        PretActiune pretActiune = new PretActiune();
        pretActiune.stockPrice(fisier);
        Map<String, Double[]> actiuni = pretActiune.getActiuni();
        int ok = 0;
        for (Map.Entry<String, Double[]> entry : actiuni.entrySet()) {
            String company = entry.getKey();
            Double[] preturi = entry.getValue();
            if (preturi != null) {
                longSMA = 0;
                shortSMA = 0;
                for (int i = 0; i < preturi.length; i++) {
                    if (preturi[i] != null) {
                        longSMA += preturi[i];
                    }
                    if (i < 5) {
                        shortSMA += preturi[i];
                    }
                }
                shortSMA /= 5;
                longSMA /= 10;
                if (shortSMA >= longSMA) {
                    if (ok == 1) {
                        System.out.print(",");
                    } else {
                        ok = 1;
                    }
                    System.out.print("\"" + company + "\"");
                }
            }
        }
    }
}