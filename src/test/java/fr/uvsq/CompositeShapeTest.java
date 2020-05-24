package fr.uvsq;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CompositeShapeTest {

    private CompositeShape c;
    private Circle circ;
    private Rectangle r;
    private Square s;
    private Triangle t;

    @Before
    public void init() {
        c = new CompositeShape();
        circ = new Circle();
        r = new Rectangle();
        s = new Square();
        t = new Triangle();
    }

    @Test
    public void TestAdd() {
        c.add(circ);
        List<Shape> list = c.getChildShape();

        assertEquals(1, list.size());
    }

    @Test
    public void TestRemove() {
        c.add(r);
        c.remove(r);
        List<Shape> list = c.getChildShape();

        assertEquals(0, list.size());
    }

    @Test
    public void TestTranslate() {
        c.add(s);
        c.translate(10,0);

        assertEquals(10, s.getBl().getX());
    }

    @Test
    public void TestTranslateMultiple1() {
        c.add(t);
        c.add(circ);
        c.translate(10, 0);

        assertEquals(10, circ.getCenter().getX());
    }

    @Test
    public void TestTranslateMultiple2() {
        c.add(t);
        c.add(circ);
        c.translate(10, 0);

        assertEquals(10, t.getP1().getX());
    }

    @Test
    public void TestDeleteMultiple() {
        Circle c2 = new Circle();
        c.add(c);
        c.add(c2);
        c.remove(c2);

        assertEquals(1, c.getChildShape().size());
    }
}