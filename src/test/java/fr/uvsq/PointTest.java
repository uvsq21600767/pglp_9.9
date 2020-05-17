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

    @Test(expected = EmptyObjectException.class)
    public void testPointCopyException() throws EmptyObjectException {
        p = new Point(null);
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

    @Test
    public void TestisEqual() throws EmptyObjectException {
        Point comp = new Point(10,5);
        p.setX(10);
        p.setY(5);

        assertTrue(p.isEqual(comp));
    }

    @Test(expected = EmptyObjectException.class)
    public void TestisEqualException() throws EmptyObjectException {
        p.isEqual(null);
    }
}