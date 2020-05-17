package fr.uvsq;

public class EmptyObjectException extends DrawingException{
    EmptyObjectException() {
        super("Your object is not created and has a null value.");
    }
}
