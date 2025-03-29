package org.poo.cb;


public class CreateUserCommand implements Command {
    private final Aplicatie aplicatie;
    private final String email;
    private final String prenume;
    private final String nume;
    private final String adresa;

    public CreateUserCommand(Aplicatie aplicatie, String email, String prenume, String nume, String adresa) {
        this.aplicatie = aplicatie;
        this.email = email;
        this.prenume = prenume;
        this.nume = nume;
        this.adresa = adresa;
    }

    @Override
    public void execute() {
        aplicatie.adaugaUtilizator(email, prenume, nume, adresa);
    }
}
