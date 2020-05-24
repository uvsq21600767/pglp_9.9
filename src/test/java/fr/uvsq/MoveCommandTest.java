package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class MoveCommandTest {

    private MoveCommand n;
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
    public void init() throws SQLException, ShapeException, ConnectionException, CloseException {
        n = new MoveCommand();
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
    public void TestTranslate() throws SQLException, ShapeException, InvalidCommand, DimensionException, WrongArgumentNumber, EmptyObjectException, InvalidNameException, RadiusException, SizeException, ConnectionException, CloseException {
        db.printTableShape();
        String str = "move shape Circle 1 5";
        n.execute(str);
        db.printTableShape();
        assertTrue(true);
    }

    @Test
    public void testTranslateGroup() throws SQLException, ShapeException, InvalidCommand, DimensionException, WrongArgumentNumber, EmptyObjectException, InvalidNameException, RadiusException, SizeException, ConnectionException, CloseException {
        db.printTableShape();
        String str = "move group G1 1 5";
        n.execute(str);
        db.printTableShape();
        assertTrue(true);
    }

}