package fr.uvsq;

public interface Shape {

    String getName();
    /**
     * Translation of the shape
     * @param x translation on the x axe
     * @param y translation on the y axe
     */
    void translate(int x, int y);

    /**
     * print the Shape
     */
    void print();

}
