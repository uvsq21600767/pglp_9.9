package fr.uvsq;

public abstract class Shape<Shape> implements Translate {

    /**
     * Translation of the shape
     * @param x translation on the x axe
     * @param y translation on the y axe
     */
    public abstract void translate(int x, int y);

    public abstract boolean isEqual(Shape s) throws EmptyObjectException;
}
