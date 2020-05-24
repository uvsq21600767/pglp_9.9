package fr.uvsq;

public class ConnectionException extends DrawingException {

    ConnectionException() {
        super("Failure : Can't connect to the DB");
    }
}
