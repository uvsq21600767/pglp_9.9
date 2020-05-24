package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class CompositeShapeDAOTest {

    private DataBase db;
    private CompositeShape shape;
    private Square shapeS;
    private Circle shapeC;
    private Rectangle shapeR;
    private Triangle shapeT;
    private DAO<CompositeShape> compDAO;
    private DAO<Square> squareDAO;
    private DAO<Circle> circleDAO;
    private DAO<Rectangle> rectangleDAO;
    private DAO<Triangle> triangleDAO;

    @Before
    public void init() throws SQLException, ShapeException, ConnectionException, CloseException {
        db = new DataBase();
        db.createTable();
        shape = new CompositeShape();
        shapeS = new Square();
        shapeC = new Circle();
        shapeR = new Rectangle();
        shapeT = new Triangle();
        compDAO = DAOFactory.getCompositeShapeDAO();
        squareDAO = DAOFactory.getSquareDAO();
        circleDAO = DAOFactory.getCircleDAO();
        rectangleDAO = DAOFactory.getRectangleDAO();
        triangleDAO = DAOFactory.getTriangleDAO();

        squareDAO.storeObj(shapeS);
        circleDAO.storeObj(shapeC);
        rectangleDAO.storeObj(shapeR);
        triangleDAO.storeObj(shapeT);
    }

    @After
    public void reset() throws SQLException {
        db.dropTable();
    }

    @Test
    public void testStore() throws SQLException, ShapeException, ConnectionException, CloseException {
        CompositeShape comp2;
        shape.add(shapeC);
        shape.add(shapeR);
        comp2 = compDAO.storeObj(shape);
        assertEquals(comp2.getChildShape().size(), 2);
    }

    @Test(expected = ShapeException.class)
    public void testStortwice() throws SQLException, ShapeException, ConnectionException, CloseException {
        shape.add(shapeC);
        shape.add(shapeR);
        compDAO.storeObj(shape);
        compDAO.storeObj(shape);
    }

    @Test
    public void testDeleteAll() throws SQLException, ShapeException, InvalidNameException, ConnectionException, CloseException {
        shape.add(shapeR);
        shape.add(shapeT);
        compDAO.storeObj(shape);
        db.printTableGroup();
        compDAO.deletObj(shape.getName());
        db.printTableGroup();
        CompositeShape comp2 =  compDAO.storeObj(shape);
        assertEquals(comp2.getChildShape().size(), 2);
    }

    @Test
    public void testDeletOnce() throws SQLException, ShapeException, InvalidNameException, ConnectionException, CloseException {
        shape.add(shapeR);
        shape.add(shapeT);
        compDAO.storeObj(shape);
        db.printTableGroup();
        compDAO.deletObj(shape.getName(), shapeT.getName());
        db.printTableGroup();
        compDAO.deletObj(shape.getName(), shapeR.getName());
        db.printTableGroup();
        CompositeShape comp2 = compDAO.storeObj(shape);
        assertEquals(comp2.getChildShape().size(), 2);
    }

    @Test
    public void testSearch() throws SQLException, ShapeException, DimensionException, RadiusException, InvalidNameException, EmptyObjectException, SizeException, ConnectionException, CloseException {
        shape.add(shapeR);
        shape.add(shapeT);
        shape.add(shapeC);
        shape.add(shapeS);
        compDAO.storeObj(shape);
        db.printTableGroup();

        CompositeShape comp2 = compDAO.searchObj(shape.getName());
        assertEquals(4, comp2.getChildShape().size());
    }

}