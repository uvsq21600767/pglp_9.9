package fr.uvsq;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    private Point p;

    @Before
    public void init() {
        p = new Point();
    }

    @Test
    public void TestsetX() {
        p.setX(10);
        assertEquals(10, p.getX());
    }

    @Test
    public void TestsetY() {
        p.setY(5);
        assertEquals(5, p.getY());
    }
}