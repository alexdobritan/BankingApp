package org.poo.cb;

public class AddFriendCommand implements Command {
    private final Aplicatie aplicatie;
    private final String emailUtilizator;
    private final String emailPrieten;

    public AddFriendCommand(Aplicatie aplicatie, String emailUtilizator, String emailPrieten) {
        this.aplicatie = aplicatie;
        this.emailUtilizator = emailUtilizator;
        this.emailPrieten = emailPrieten;
    }

    @Override
    public void execute() {
        aplicatie.adaugaPrieten(emailUtilizator, emailPrieten);
    }
}
