package fr.uvsq;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class NewCommandTest {

    private NewCommand n;
    DataBase db;

    @Before
    public void init() throws SQLException {
        db = new DataBase();
        db.createTable();
        n = new NewCommand();
    }

    @After
    public void reset() throws SQLException {
        db.dropTable();
    }

    @Test(expected = ShapeException.class)
    public void testInsertCircle() throws RadiusException, WrongArgumentNumber, SQLException, EmptyObjectException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand, ConnectionException, CloseException {
        String str = "next circle Test1 0 0 60";
        n.execute(str);
        db.printTableShape();
        n.execute(str);
    }

    @Test(expected = ShapeException.class)
    public void testInsertRectangle() throws RadiusException, WrongArgumentNumber, SQLException, EmptyObjectException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand, ConnectionException, CloseException {
        String str = "next rectangle Test1 0 0 5 4";
        n.execute(str);
        db.printTableShape();
        n.execute(str);
    }

    @Test(expected = ShapeException.class)
    public void testInsertSquare() throws RadiusException, WrongArgumentNumber, SQLException, EmptyObjectException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand, ConnectionException, CloseException {
        String str = "next square Test1 0 0 5";
        n.execute(str);
        db.printTableShape();
        n.execute(str);
    }

    @Test(expected = ShapeException.class)
    public void testInsertTriangle() throws RadiusException, WrongArgumentNumber, SQLException, EmptyObjectException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand, ConnectionException, CloseException {
        String str = "next triangle Test1 0 0 5 4 2 1";
        n.execute(str);
        db.printTableShape();
        n.execute(str);
    }

    @Test(expected = ShapeException.class)
    public void testInsertGroup() throws SQLException, ShapeException, DimensionException, WrongArgumentNumber, EmptyObjectException, InvalidNameException, RadiusException, SizeException, InvalidCommand, ConnectionException, CloseException {
        String str = "new group G1 circ";
        String str2 = "new circle circ 0 0 10";

        n.execute(str2);
        db.printTableShape();
        n.execute(str);
        db.printTableGroup();
        n.execute(str);
    }

    @Test(expected = ShapeException.class)
    public void testInsertMultiple() throws SQLException, ShapeException, DimensionException, WrongArgumentNumber, EmptyObjectException, InvalidNameException, RadiusException, SizeException, InvalidCommand, ConnectionException, CloseException {
        String str = "new circle cr 0 0 10";
        String str2 = "new square sq 0 0 5";
        String str3 = "new rectangle rq 0 0 10 5";
        String str4 = "new triangle tr 0 0 1 2 3 4";
        String str5 = "new group G1 cr";
        String str6 = "new group G1 sq";
        String str7 = "new group G1 rq";
        String str8 = "new group G1 tr";

        n.execute(str);
        n.execute(str2);
        n.execute(str3);
        n.execute(str4);
        db.printTableShape();
        n.execute(str5);
        n.execute(str6);
        n.execute(str7);
        n.execute(str8);
        db.printTableGroup();
        n.execute(str8);
    }

}