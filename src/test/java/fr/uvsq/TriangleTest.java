package fr.uvsq;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TriangleTest {

    Triangle t;

    @Before
    public void init() {
        t = new Triangle();
    }

    @Test(expected = EmptyObjectException.class)
    public void TestTriangleEmptyObjectException() throws EmptyObjectException{
        t = new Triangle(null, null, null);
    }

    @Test
    public void TestSetP1() throws EmptyObjectException{
        Point p = new Point(4, 5);
        t.setP1(p);
        assertTrue(t.getP1().isEqual(p));
    }

    @Test(expected = EmptyObjectException.class)
    public void TestSetP1EmptyObjectException() throws EmptyObjectException{
        t.setP1(null);
    }

    @Test
    public void TestSetP2() throws EmptyObjectException{
        Point p = new Point(4, 5);
        t.setP2(p);
        assertTrue(t.getP2().isEqual(p));
    }

    @Test(expected = EmptyObjectException.class)
    public void TestSetP2EmptyObjectException() throws EmptyObjectException{
        t.setP2(null);
    }

    @Test
    public void TestSetP3() throws EmptyObjectException{
        Point p = new Point(4, 5);
        t.setP3(p);
        assertTrue(t.getP3().isEqual(p));
    }

    @Test(expected = EmptyObjectException.class)
    public void TestSetP3EmptyObjectException() throws EmptyObjectException{
        t.setP3(null);
    }

    @Test
    public void TestTranslateX() {
        t.translate(10, 0);
        assertEquals(t.getP1().getX(), 10);
    }

    @Test
    public void TestTranslateY() {
        t.translate(0, 10);
        assertEquals(t.getP1().getY(), 10);
    }

    @Test
    public void TestisEqual() throws EmptyObjectException{
        Triangle t2 = new Triangle(new Point(4, 8), new Point(8, 1), new Point(0, 15));
        t.setP1(t2.getP1());
        t.setP2(t2.getP2());
        t.setP3(t2.getP3());

        assertTrue(t.isEqual(t2));
    }

    @Test(expected = EmptyObjectException.class)
    public void TestisEqualEmptyObjectException() throws EmptyObjectException{
        t.isEqual(null);
    }
}