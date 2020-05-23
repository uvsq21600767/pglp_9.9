package fr.uvsq;

public class DAOFactory {

    public static DAO<Circle> getCircleDAO() {
        return new CircleDAO();
    }

    public static DAO<Rectangle> getRectangleDAO() {
        return new RectangleDAO();
    }
}
