package fr.uvsq;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    private Rectangle r;
    @Before
    public void init() {
       r = new Rectangle();
    }

    @Test
    public void TestsetBl() throws EmptyObjectException {
        r.setBl(new Point(10, 5));
        assertTrue(r.getBl().isEqual(new Point(10, 5)));
    }

    @Test(expected = EmptyObjectException.class)
    public void TestsetBlEmptyObjectException() throws EmptyObjectException {
        r.setBl(null);
    }

    @Test
    public void TestsetL() throws DimensionException {
        r.setL(10);
        assertEquals(r.getL(), 10);
    }

    @Test(expected = DimensionException.class)
    public void TestsetLDimensionException() throws DimensionException {
        r.setL(-1);
    }

    @Test
    public void TestsetH() throws DimensionException {
        r.setH(15);
        assertEquals(r.getH(), 15);
    }

    @Test(expected = DimensionException.class)
    public void TestsetHDimensionException() throws DimensionException {
        r.setH(-1);
    }

    @Test
    public void TesttranslateX() {
        r.translate(10,0);
        assertEquals(new Point(10,0).getX(), r.getBl().getX());
    }

    @Test
    public void TesttranslateY() {
        r.translate(0, 10);
        assertEquals(new Point(0, 10).getY(), r.getBl().getY());
    }

    @Test
    public void TestisEqual() throws EmptyObjectException, DimensionException {
        Rectangle r2 = new Rectangle(new Point(10, 5), 4, 7, "Rectangle");
        r.setL(r2.getL());
        r.setH(r2.getH());
        r.setBl(new Point(r2.getBl()));

        assertTrue(r.isEqual(r2));
    }

    @Test(expected = EmptyObjectException.class)
    public void TestisEqualEmptyObjectException() throws EmptyObjectException {
        r.isEqual(null);
    }
}