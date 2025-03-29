package org.poo.cb;

public class ListUserCommand implements Command {
    private final String email;
    private final Aplicatie aplicatie;
    public ListUserCommand(Aplicatie aplicatie, String email) {
        this.email = email;
        this.aplicatie = aplicatie;
    }

    @Override
    public void execute() {
        Utilizator utilizator = aplicatie.cautaUtilizator(email);
        if (utilizator != null) {
            utilizator.List();
        } else {
            System.out.println("User with " + email + " doesn't exist");
        }
    }
}
