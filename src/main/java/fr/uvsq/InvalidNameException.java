package fr.uvsq;

public class InvalidNameException extends DrawingException {

    InvalidNameException() {
        super("Failure : invalid shape's name");
    }
}
