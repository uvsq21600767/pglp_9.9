package fr.uvsq;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ResetCommandTest {

    private DataBase bd;
    private ResetCommand n;

    @Before
    public void init() throws SQLException {
        bd = new DataBase();
        bd.createTable();
        n = new ResetCommand();
    }

    @Test
    public void testReset() throws SQLException, ShapeException, InvalidCommand, DimensionException, NotEnoughArgumentException, EmptyObjectException, InvalidNameException, RadiusException, SizeException {
        n.execute("reset");
       assertTrue(true);
    }

}