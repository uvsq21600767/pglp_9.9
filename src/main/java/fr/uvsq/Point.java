package fr.uvsq;

public class Point {

    /**
     * x : postion on the x axe
     * y : position on the y axe
     */
    private int x;
    private int y;

    Point() {
        this.x = 0;
        this.y = 0;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Point(Point origin) throws EmptyObjectException {
        if(origin == null) {
            throw new EmptyObjectException();
        } else {
            this.x = origin.getX();
            this.y = origin.getY();
        }
    }
    /**
     * Getter of x
     * @return this.x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter of y
     * @return this.y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Setter of x
     * @param newX the new value of x
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Setter of y
     * @param newY the new value of y
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Test if 2 circle are at the same position
     * @param comp : comparison circle
     * @return true if the 2 circles are at the same position, false if not
     */
    public boolean isEqual(Point comp) throws EmptyObjectException {
        if(comp == null) {
            throw new EmptyObjectException();
        } else {
            if(this.getX() != comp.getX()) {
                return false;
            } else if(this.getY() != comp.getY()) {
                return false;
            }
            return true;
        }
    }
}
