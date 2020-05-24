package fr.uvsq;

public class InvalidArgument extends DrawingException {

    InvalidArgument() {
        super("Invalid argument : integer expected");
    }
}
