package fr.uvsq;

import java.util.ArrayList;
import java.util.List;

public class CompositeShape implements Shape {

    private String name;
    private List<Shape> childShape = new ArrayList<Shape>();

    CompositeShape() {
        this.name = "Group";
    }

    CompositeShape(String name) {
        this.name = name;
    }

    /**
     * translation on all component
     * @param x translation on the x axe
     * @param y translation on the y axe
     */
    @Override
    public void translate(int x, int y) {
        for (Shape shapes : childShape) {
            shapes.translate(x, y);
        }
    }

    /**
     * print the Shape
     */
    @Override
    public void print() {
        System.out.println("Name : " + this.getName());
        for (Shape shapes : childShape) {
            shapes.print();
        }
    }

    /**
     * Getter of childShape
     * @return this.childShape
     */
    public List<Shape> getChildShape() {
        return this.childShape;
    }

    /**
     * Adding shape in the composite
     * @param shape : the shape added in the composite
     */
    public void add(Shape shape) {
        childShape.add(shape);
    }

    /**
     * Removing shape in the composite
     * @param shape : the shape removed from the composite
     */
    public void remove(Shape shape) {
        childShape.remove(shape);
    }

    /**
     * Getter of name
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter of name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
