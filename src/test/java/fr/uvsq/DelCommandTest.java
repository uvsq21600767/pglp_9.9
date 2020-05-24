package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DelCommandTest {

    private DelCommand n;
    DataBase db;
    private Circle c;
    private Square s;

    @Before
    public void init() throws SQLException, ShapeException {
        db = new DataBase();
        db.createTable();
        c = new Circle();
        s = new Square();
        DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
        circleDAO.storeObj(c);
        DAO<Square> squareDAO = DAOFactory.getSquareDAO();
        squareDAO.storeObj(s);
        n = new DelCommand();
    }

    @After
    public void reset() throws SQLException {
        db.dropTable();
    }

    @Test
    public void testDelShape() throws SQLException, ShapeException, DimensionException, NotEnoughArgumentException, EmptyObjectException, InvalidNameException, RadiusException, SizeException, InvalidCommand {
        DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
        CompositeShape comp = new CompositeShape("G1");
        comp.add(c);
        comp.add(s);
        compositeShapeDAO.storeObj(comp);
        db.printTableShape();
        db.printTableGroup();
        String str = "del shape Circle";
        n.execute(str);
        db.printTableShape();
        db.printTableGroup();
    }

    @Test
    public void testDelShapeSquare() throws SQLException, ShapeException, DimensionException, NotEnoughArgumentException, EmptyObjectException, InvalidNameException, RadiusException, SizeException, InvalidCommand {
        DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
        CompositeShape comp = new CompositeShape("G1");
        comp.add(c);
        comp.add(s);
        compositeShapeDAO.storeObj(comp);
        db.printTableShape();
        String str = "del shape Square";
        n.execute(str);
        db.printTableShape();
    }

    @Test
    public void testDelGroup() throws SQLException, ShapeException, InvalidNameException, DimensionException, NotEnoughArgumentException, EmptyObjectException, RadiusException, SizeException, InvalidCommand {
        DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
        CompositeShape comp = new CompositeShape("G1");
        comp.add(c);
        comp.add(s);
        compositeShapeDAO.storeObj(comp);

        db.printTableGroup();
        String str = "del group G1";
        n.execute(str);
        db.printTableGroup();
    }

    @Test
    public void testDelLink() throws SQLException, ShapeException, DimensionException, NotEnoughArgumentException, EmptyObjectException, InvalidNameException, RadiusException, SizeException, InvalidCommand {
        DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
        CompositeShape comp = new CompositeShape("G1");
        comp.add(c);
        comp.add(s);
        compositeShapeDAO.storeObj(comp);

        db.printTableGroup();
        String str = "del unlink G1 Circle";
        n.execute(str);
        db.printTableGroup();
    }

}