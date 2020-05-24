package fr.uvsq;

public class WrongArgumentNumber extends DrawingException {
    WrongArgumentNumber() {
        super("Wrong argument number in the command");
    }
}
