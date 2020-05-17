package fr.uvsq;

public class DimensionException extends DrawingException {

    DimensionException() {
        super("You can't have a length or a height < 0");
    }
}
