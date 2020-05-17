package fr.uvsq;

public class SizeException extends DrawingException {

    SizeException() {
        super("You can't have a square with a size lower than 0");
    }
}
