package fr.uvsq;

import java.util.ArrayList;
import java.util.List;

public class CompositeShape implements Shape {

    private List<Shape> childShape = new ArrayList<Shape>();

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
}
