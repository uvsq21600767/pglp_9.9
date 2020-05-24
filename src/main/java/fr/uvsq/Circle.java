package fr.uvsq;

public class Circle implements Shape {

    /**
     * center : center of the circle
     * radius : radius of the circle
     * name : name of the circle
     */
    private Point center;
    private int radius;
    private String name;

    Circle() {
        this.name = "Circle";
        this.center = new Point();
        this.radius = 1;
    }

    Circle(Point center, int radius, String name) throws EmptyObjectException, RadiusException {
        if(center == null) {
            throw new EmptyObjectException();
        } else if(radius < 0) {
            throw new RadiusException();
        } else {
            this.center = new Point(center);
            this.radius = radius;
            this.name = name;
        }
    }

    Circle(Circle origin) throws EmptyObjectException{
        if(origin == null) {
            throw new EmptyObjectException();
        } else {
            this.center = new Point(origin.center.getX(), origin.center.getY());
            this.radius = origin.getRadius();
            this.name = origin.getName();
        }
    }

    /**
     * Getter of Center
     * @return this.center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Getter of radius
     * @return this.radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Getter of name
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter of Center
     * @param origin : the new center of the circle
     * @throws EmptyObjectException if origin is null
     */
    public void setCenter(Point origin) throws EmptyObjectException {
        if(origin == null) {
            throw new EmptyObjectException();
        } else {
            this.center = new Point(origin);
        }
    }

    /**
     * Setter of radius
     * @param radius : the new radius of the circle
     * @throws RadiusException if radius lower than 0
     */
    public void setRadius(int radius) throws  RadiusException{
        if(radius < 0) throw new RadiusException();
        else {
            this.radius = radius;
        }
    }

    /**
     * Setter of name
     * @param name : the new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * translate method of circle
     * @param x translation on the x axe
     * @param y translation on the y axe
     */
    @Override
    public void translate(int x, int y) {
        this.center.setX(this.center.getX() + x);
        this.center.setY(this.center.getY() + y);
    }

    /**
     * print the Shape
     */
    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String str = "";
        str += "Name : " + this.getName() + " Center(" + this.getCenter().getX() + "," + this.getCenter().getY() + ") "
                + "Radius : " + this.getRadius();
        return str;
    }

    /**
     * Test if two circles are at the same position and have the same radius
     * @param comp : the comparison circle
     * @return true if the 2 circles have the same position and the same radius, false if not
     * @throws EmptyObjectException if comp is null
     */
    public boolean isEqual(Circle comp) throws EmptyObjectException{
        if(comp == null) {
            throw new EmptyObjectException();
        }
        else {
            if (!this.center.isEqual(comp.getCenter())) {
                return false;
            } else if (this.getRadius() != comp.getRadius()) {
                return false;
            }
            return true;
        }
    }
}
