package fr.uvsq;

public class DoubleInsertException extends DrawingException {

    DoubleInsertException() {
        super("Object already in DB : change the name or delete the other shape");
    }
}
