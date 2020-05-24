package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class TriangleDAOTest {
    private DataBase db;
    private Triangle shape;
    private Triangle shape2;
    private DAO<Triangle> triangleDAO;

    @Before
    public void init() throws SQLException {
        db = new DataBase();
        db.createTable();
        shape = new Triangle();
        shape2 = new Triangle();
        triangleDAO = DAOFactory.getTriangleDAO();
    }

    @After
    public void reset() throws SQLException {
        db.dropShape();
        db.dropGroup();
    }

    @Test
    public void testInsert() throws SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {

        shape2 = triangleDAO.storeObj(shape);
        db.printTableShape();

        assertTrue(shape.isEqual(shape2));
    }

    @Test(expected = ShapeException.class)
    public void testInsertSame() throws SQLException, ShapeException, ConnectionException, CloseException {
        shape2 = new Triangle();
        triangleDAO.storeObj(shape);
        triangleDAO.storeObj(shape2);

        db.printTableShape();
    }

    @Test
    public void testDelete() throws InvalidNameException, SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {
        triangleDAO.storeObj(shape);
        triangleDAO.deletObj(shape.getName());

        shape2 = triangleDAO.storeObj(shape);
        assertTrue(shape2.isEqual(shape));
    }

    @Test
    public void testUpdate() throws SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {
        triangleDAO.storeObj(shape);
        db.printTableShape();
        shape.setP1(new Point(4, 7));
        shape2 = triangleDAO.updateObj(shape);
        db.printTableShape();
        assertTrue(shape2.isEqual(shape));
    }

    @Test
    public void testSearch() throws SQLException, ShapeException, RadiusException, InvalidNameException, EmptyObjectException, DimensionException, SizeException, ConnectionException, CloseException {
        triangleDAO.storeObj(shape);
        shape2 = triangleDAO.searchObj(shape.getName());
        assertTrue(shape2.isEqual(shape));
    }
}