package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SquareDAOTest {
    private DataBase db;
    private Square shape;
    private Square shape2;
    private DAO<Square> squareDAO;

    @Before
    public void init() throws SQLException {
        db = new DataBase();
        db.createTable();
        shape = new Square();
        shape2 = new Square();
        squareDAO = DAOFactory.getSquareDAO();
    }

    @After
    public void reset() throws SQLException {
        db.dropShape();
        db.dropGroup();
    }

    @Test
    public void testInsert() throws SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {

        shape2 = squareDAO.storeObj(shape);
        db.printTableShape();

        assertTrue(shape.isEqual(shape2));
    }

    @Test(expected = ShapeException.class)
    public void testInsertSame() throws SQLException, ShapeException, ConnectionException, CloseException {
        shape2 = new Square();
        squareDAO.storeObj(shape);
        squareDAO.storeObj(shape2);

        db.printTableShape();
    }

    @Test
    public void testDelete() throws InvalidNameException, SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {
        squareDAO.storeObj(shape);
        squareDAO.deletObj(shape.getName());

        shape2 = squareDAO.storeObj(shape);
        assertTrue(shape2.isEqual(shape));
    }

    @Test
    public void testUpdate() throws SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {
        squareDAO.storeObj(shape);
        db.printTableShape();
        shape.setBl(new Point(4, 7));
        shape2 = squareDAO.updateObj(shape);
        db.printTableShape();
        assertTrue(shape2.isEqual(shape));
    }

    @Test
    public void testSearch() throws SQLException, ShapeException, RadiusException, InvalidNameException, EmptyObjectException, DimensionException, SizeException, ConnectionException, CloseException {
        squareDAO.storeObj(shape);
        shape2 = squareDAO.searchObj(shape.getName());
        assertTrue(shape2.isEqual(shape));
    }

}