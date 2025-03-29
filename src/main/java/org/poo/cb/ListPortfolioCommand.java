package org.poo.cb;

public class ListPortfolioCommand implements Command {
    private final Aplicatie aplicatie;
    private final String email;

    public ListPortfolioCommand(Aplicatie aplicatie, String email) {
        this.aplicatie = aplicatie;
        this.email = email;
    }

    @Override
    public void execute() {
        Utilizator utilizator = aplicatie.cautaUtilizator(email);
        if (utilizator != null) {
            utilizator.getPortofoliu().List();
        } else {
            System.out.println("User with " + email + " doesn't exist");
        }
    }
}
