package fr.uvsq;

public class RadiusException extends DrawingException {
    RadiusException() {
        super("The radius can't be lower than 0");
    }
}
