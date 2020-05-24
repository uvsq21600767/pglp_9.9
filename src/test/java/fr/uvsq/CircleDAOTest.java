package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

public class CircleDAOTest {

    private DataBase db;
    private Circle c;

    @Before
    public void init() throws SQLException {
        db = new DataBase();
        db.createTable();
        c = new Circle();
    }

    @After
    public void reset() throws SQLException {
        db.dropShape();
        db.dropGroup();
    }

    @Test
    public void testInsertCircle() throws SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {
        DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
        Circle c2 = circleDAO.storeObj(c);
        db.printTableShape();

        assertTrue(c.isEqual(c2));
    }

    @Test(expected = ShapeException.class)
    public void testInsertSame() throws SQLException, ShapeException, ConnectionException, CloseException {
        Circle c2 = new Circle();

        DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
        circleDAO.storeObj(c);
        circleDAO.storeObj(c2);

        db.printTableShape();
    }

    @Test
    public void testDelete() throws InvalidNameException, SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {
        DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
        circleDAO.storeObj(c);
        circleDAO.deletObj(c.getName());

        Circle c2 = circleDAO.storeObj(c);
        assertTrue(c2.isEqual(c));
    }

    @Test
    public void testUpdate() throws SQLException, ShapeException, EmptyObjectException, ConnectionException, CloseException {
        DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
        circleDAO.storeObj(c);
        db.printTableShape();
        c.setCenter(new Point(4, 7));
        Circle c2 = circleDAO.updateObj(c);
        db.printTableShape();
        assertTrue(c2.isEqual(c));
    }

    @Test
    public void testSearch() throws SQLException, ShapeException, RadiusException, InvalidNameException, EmptyObjectException, DimensionException, SizeException, ConnectionException, CloseException {
        DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
        circleDAO.storeObj(c);
        Circle c2 = circleDAO.searchObj(c.getName());
        assertTrue(c2.isEqual(c));
    }

}