package fr.uvsq;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest {

    private Square s;

    @Before
    public void init() {
        s = new Square();
    }

   @Test(expected = EmptyObjectException.class)
    public void TestSquareEmptyObjectException() throws EmptyObjectException, SizeException {
        s = new Square(null, 1);
   }

   @Test(expected = SizeException.class)
    public void TestSquareSizeException() throws EmptyObjectException, SizeException {
        s = new Square(new Point(), -1);
   }

   @Test(expected = EmptyObjectException.class)
    public void TestSquareCopyEmptyObjectException() throws EmptyObjectException {
        s = new Square(null);
   }

   @Test
    public void TestsetSize() throws SizeException {
        s.setSize(10);
        assertEquals(10, s.getSize());
   }

   @Test(expected = SizeException.class)
    public void TestsetSizeSizeException() throws SizeException {
        s.setSize(-1);
   }

   @Test
    public void TestsetBl() throws EmptyObjectException {
        s.setBl(new Point(10,5));
        assertTrue(s.getBl().isEqual(new Point(10,5)));
   }

   @Test(expected = EmptyObjectException.class)
    public void TestsetBlEmptyObjectException() throws EmptyObjectException {
        s.setBl(null);
    }

    @Test
    public void TestTranslateX() throws EmptyObjectException {
        s.translate(10,0);
        Point p = new Point(s.getBl());
        assertEquals(10, p.getX());
    }

    @Test
    public void TestTranslateY() throws EmptyObjectException {
        s.translate(0,5);
        Point p = new Point(s.getBl());
        assertEquals(5, p.getY());
    }

    @Test(expected = EmptyObjectException.class)
    public void TestIsEqualEmptyObjectException() throws EmptyObjectException {
        s.isEqual(null);
    }

    @Test
    public void TestIsEqual() throws EmptyObjectException, SizeException {
        s = new Square(new Point(10,5), 4);
        Square s2 = new Square(new Point(10,5), 4);

        assertTrue(s.isEqual(s2));
    }
}