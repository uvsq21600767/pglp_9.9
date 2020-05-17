package fr.uvsq;

public abstract class Shape<Shape> implements Translate {

    protected String name;

    /**
     * Getter of name
     * @return the name of the shape
     */
    public abstract String getName();

    /**
     * Setter of name
     * @param name : the new value of name
     */
    public abstract void setName(String name);

    /**
     * Translation of the shape
     * @param x translation on the x axe
     * @param y translation on the y axe
     */
    public abstract void translate(int x, int y);

    /**
     * Test if two shapes are equal (excluding the name of the shape)
     * @param s : the shape to test
     * @return true of identicall, false if not
     * @throws EmptyObjectException if s is null
     */
    public abstract boolean isEqual(Shape s) throws EmptyObjectException;
}
