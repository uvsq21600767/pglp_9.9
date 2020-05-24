package fr.uvsq;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircleTest {

    private Circle c;

    @Before
    public void init() {
        c = new Circle();
    }

    @Test(expected = RadiusException.class)
    public void TestCircleRadiusException() throws RadiusException, EmptyObjectException {
        c = new Circle(new Point(),-1, "Circle");
    }

    @Test(expected = EmptyObjectException.class)
    public void TestCircleEmptyObjectException() throws RadiusException, EmptyObjectException {
        c = new Circle(null, 0, "Circle");
    }

    @Test(expected = EmptyObjectException.class)
    public void TestCircleCopyEmptyObjectException() throws EmptyObjectException {
        c = new Circle(null);
    }

    @Test
    public void TestsetCenter() throws EmptyObjectException {
        Point newCenter = new Point(10, 5);
        c.setCenter(newCenter);
        assertTrue(c.getCenter().isEqual(newCenter));
    }

    @Test
    public void TestsetRadius() throws RadiusException {
        c.setRadius(10);
        assertEquals(10, c.getRadius());
    }

    @Test(expected = RadiusException.class)
    public void TestsetRadiusException() throws RadiusException {
        c.setRadius(-1);
    }

    @Test
    public void TesttranslateX() throws EmptyObjectException {
        c.translate(10,0);
        Point p = new Point(c.getCenter());
        assertEquals(10, p.getX());
    }

    @Test
    public void TesttranslateY() throws EmptyObjectException {
        c.translate(0,10);
        Point p = new Point(c.getCenter());
        assertEquals(10, p.getY());
    }

    @Test
    public void TestisEqual() throws RadiusException, EmptyObjectException {
        Circle comp = new Circle(new Point(10, 5), 10, "Circle");
        c.setCenter(comp.getCenter());
        c.setRadius(comp.getRadius());

        assertTrue(c.isEqual(comp));
    }

    @Test(expected = EmptyObjectException.class)
    public void TestisEqualException() throws RadiusException, EmptyObjectException {
        c.isEqual(null);
    }

    @Test
    public void testPrint() {
        Circle c2 = new Circle();
        c2.print();
        assertTrue(true);
    }
}