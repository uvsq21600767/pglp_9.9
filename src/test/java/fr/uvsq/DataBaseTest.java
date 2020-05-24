package fr.uvsq;


import org.junit.Test;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DataBaseTest {

    private DataBase d;

    @Test
    public void testCreateTable() throws SQLException {
        d = new DataBase();
        assertTrue(d.createTable());
        System.out.println("------");
        d.dropShape();
        d.dropGroup();
    }

    @Test
    public void testPrintTableShape() throws SQLException {
        d = new DataBase();
        d.createTable();
        assertTrue(d.printTableShape());
        System.out.println("------");
        d.dropShape();
        d.dropGroup();
    }

    @Test
    public void testDropShape() throws SQLException {
        d = new DataBase();
        d.createTable();
        assertTrue(d.dropShape());
    }

    @Test
    public void testPrintTableGroup() throws SQLException {
        d = new DataBase();
        d.createTable();
        assertTrue(d.printTableGroup());
        System.out.println("------");
        d.dropShape();
        d.dropGroup();
    }

}