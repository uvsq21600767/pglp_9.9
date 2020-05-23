package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

public class CircleDAOTest {

    private DataBase db;
    @Before
    public void init() throws SQLException {
        db = new DataBase();
        db.createTable();
    }

    @After
    public void reset() throws SQLException {
        db.dropShape();
    }

    @Test
    public void testInsertCircle() throws SQLException, ShapeException, EmptyObjectException {
        Circle c = new Circle();
        DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
        Circle c2 = circleDAO.storeObj(c);
        db.printTableShape();

        assertTrue(c.isEqual(c2));
    }

    @Test(expected = ShapeException.class)
    public void testInsertSame() throws SQLException, ShapeException {
        Circle c = new Circle();
        Circle c2 = new Circle();

        DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
        circleDAO.storeObj(c);
        circleDAO.storeObj(c2);

        db.printTableShape();
    }

}