package fr.uvsq;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
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
    }

    @Test
    public void testPrintTableShape() throws SQLException {
        d = new DataBase();
        d.createTable();
        assertTrue(d.printTableShape());
        System.out.println("------");
        d.dropShape();
    }

    @Test
    public void testDropShape() throws SQLException {
        d = new DataBase();
        d.createTable();
        assertTrue(d.dropShape());
    }

}