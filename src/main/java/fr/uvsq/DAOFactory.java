package fr.uvsq;

public class DAOFactory {

    public static DAO<Circle> getCircleDAO() {
        return new CircleDAO();
    }

    public static DAO<Rectangle> getRectangleDAO() {
        return new RectangleDAO();
    }

    public static DAO<Square> getSquareDAO() {
        return new SquareDAO();
    }

    public static DAO<Triangle> getTriangleDAO() {
        return new TriangleDAO();
    }

    public static DAO<CompositeShape> getCompositeShapeDAO() {
        return new CompositeShapeDAO();
    }
}
