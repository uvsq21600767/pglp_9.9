package fr.uvsq;

public class NotEnoughArgumentException extends DrawingException {
    NotEnoughArgumentException() {
        super("Not engough argument in the command");
    }
}
