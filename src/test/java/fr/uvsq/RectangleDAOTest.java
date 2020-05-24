package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class RectangleDAOTest {

    private DataBase db;
    private Rectangle shape;
    private Rectangle shape2;
    private DAO<Rectangle> rectangleDAO;

    @Before
    public void init() throws SQLException {
        db = new DataBase();
        db.createTable();
        shape = new Rectangle();
        shape2 = new Rectangle();
        rectangleDAO = DAOFactory.getRectangleDAO();
    }

    @After
    public void reset() throws SQLException {
        db.dropShape();
        db.dropGroup();
    }

    @Test
    public void testInsertCircle() throws SQLException, ShapeException, EmptyObjectException {

        shape2 = rectangleDAO.storeObj(shape);
        db.printTableShape();

        assertTrue(shape.isEqual(shape2));
    }

    @Test(expected = ShapeException.class)
    public void testInsertSame() throws SQLException, ShapeException {
        shape2 = new Rectangle();
        rectangleDAO.storeObj(shape);
        rectangleDAO.storeObj(shape2);

        db.printTableShape();
    }

    @Test
    public void testDelete() throws InvalidNameException, SQLException, ShapeException, EmptyObjectException {
        rectangleDAO.storeObj(shape);
        rectangleDAO.deletObj(shape.getName());

        shape2 = rectangleDAO.storeObj(shape);
        assertTrue(shape2.isEqual(shape));
    }

    @Test
    public void testUpdate() throws SQLException, ShapeException, EmptyObjectException {
        rectangleDAO.storeObj(shape);
        db.printTableShape();
        shape.setBl(new Point(4, 7));
        shape2 = rectangleDAO.updateObj(shape);
        db.printTableShape();
        assertTrue(shape2.isEqual(shape));
    }

    @Test
    public void testSearch() throws SQLException, ShapeException, RadiusException, InvalidNameException, EmptyObjectException, DimensionException, SizeException {
       rectangleDAO.storeObj(shape);
        shape2 = rectangleDAO.searchObj(shape.getName());
        assertTrue(shape2.isEqual(shape));
    }

}