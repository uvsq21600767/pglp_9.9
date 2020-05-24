package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class PrintCommandTest {

    private PrintCommand n;
    DataBase db;
    private Circle c;
    private Square s;
    private Rectangle r;
    private Triangle t;
    private CompositeShape comp;
    private DAO<Circle> circleDAO;
    private DAO<Square> squareDAO;
    private DAO<Rectangle> rectangleDAO;
    private DAO<Triangle> triangleDAO;
    private DAO<CompositeShape> compositeShapeDAO;

    @Before
    public void init() throws SQLException, ShapeException {
        n = new PrintCommand();
        db = new DataBase();
        db.createTable();
        c = new Circle();
        s = new Square();
        r = new Rectangle();
        t = new Triangle();
        comp = new CompositeShape("G1");
        comp.add(c);
        comp.add(s);
        comp.add(r);
        comp.add(t);
        circleDAO = DAOFactory.getCircleDAO();
        squareDAO = DAOFactory.getSquareDAO();
        rectangleDAO = DAOFactory.getRectangleDAO();
        triangleDAO = DAOFactory.getTriangleDAO();
        compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
        circleDAO.storeObj(c);
        squareDAO.storeObj(s);
        rectangleDAO.storeObj(r);
        triangleDAO.storeObj(t);
        compositeShapeDAO.storeObj(comp);
    }

    @After
    public void reset() throws SQLException {
        db.dropTable();
    }

    @Test
    public void testPrintShape() throws SQLException, ShapeException, InvalidCommand, DimensionException, NotEnoughArgumentException, EmptyObjectException, InvalidNameException, RadiusException, SizeException {
        db.printTableShape();
        db.printTableGroup();
        String str = "print shape Circle";
        n.execute(str);
        n.execute("print shape Square");
        n.execute("print shape Rectangle");
        n.execute("print shape Triangle");
        assertTrue(true);
    }

}