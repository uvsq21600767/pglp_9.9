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
    
    Point(Point origin) {
        this.x = origin.getX();
        this.y = origin.getY();
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
}
