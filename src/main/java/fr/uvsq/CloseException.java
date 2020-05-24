package fr.uvsq;

public class CloseException extends DrawingException {

    CloseException() {
        super("Failure : can't close the cnnection");
    }
}
