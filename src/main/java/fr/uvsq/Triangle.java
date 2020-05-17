package fr.uvsq;

public class Triangle extends Shape<Triangle> {

    /**
     * p1 : first point of the Triangle
     * p2 : Second point of the Triangle
     * p3 : third point of the Triangle
     */
    private Point p1;
    private Point p2;
    private Point p3;

    Triangle() {
        this.p1 = new Point();
        this.p2 = new Point(1, 2);
        this.p3 = new Point(2, 0);
        this.name = "Triangle";
    }

    Triangle(Point p1, Point p2, Point p3, String name) throws EmptyObjectException{
        if(p1 == null || p2 == null || p3 == null) {
            throw new EmptyObjectException();
        } else {
            this.p1 = new Point(p1);
            this.p2 = new Point(p2);
            this.p3 = new Point(p3);
            this.name = name;
        }
    }

    Triangle(Triangle origin) throws EmptyObjectException {
        if(origin == null) {
            throw new EmptyObjectException();
        } else {
            this.p1 = new Point(origin.getP1());
            this.p2 = new Point(origin.getP2());
            this.p3 = new Point(origin.getP3());
            this.name = origin.getName();
        }
    }

    /**
     * Getter of p1
     * @return this.p1
     */
    public Point getP1(){
        return this.p1;
    }

    /**
     * Getter of p2
     * @return this.p2
     */
    public Point getP2(){
        return this.p2;
    }

    /**
     * Getter of p3
     * @return this.p3
     */
    public Point getP3(){
        return this.p3;
    }

    /**
     * Getter of name
     * @return this.name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter of p1
     * @param p : new value of p1
     * @throws EmptyObjectException if p is null
     */
    public void setP1(Point p) throws EmptyObjectException {
        if(p == null) {
            throw new EmptyObjectException();
        } else {
            this.p1 = new Point(p);
        }
    }

    /**
     * Setter of p2
     * @param p : new value of p2
     * @throws EmptyObjectException if p is null
     */
    public void setP2(Point p) throws EmptyObjectException {
        if(p == null) {
            throw new EmptyObjectException();
        } else {
            this.p2 = new Point(p);
        }
    }

    /**
     * Setter of p3
     * @param p : new value of p3
     * @throws EmptyObjectException if p is null
     */
    public void setP3(Point p) throws EmptyObjectException {
        if(p == null) {
            throw new EmptyObjectException();
        } else {
            this.p3 = new Point(p);
        }
    }

    /**
     * Setter of name
     * @param name : the new value of name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * translation of the three points of the triangle
     * @param x translation on the x axe
     * @param y translation on the y axe
     */
    public void translate(int x, int y){
        this.p1.setX(this.p1.getX() + x);
        this.p1.setY(this.p1.getY() + y);

        this.p2.setX(this.p2.getX() + x);
        this.p2.setY(this.p2.getY() + y);

        this.p3.setX(this.p3.getX() + x);
        this.p3.setY(this.p3.getY() + y);
    }

    /**
     * Test if two traingle are equals
     * @param comp : the comparison Traignale
     * @return true if the two triangle are equals, false if not
     * @throws EmptyObjectException is comp is null
     */
    public boolean isEqual(Triangle comp) throws EmptyObjectException{
        if(comp == null) {
            throw new EmptyObjectException();
        } else {
            if(!this.p1.isEqual(comp.p1)) {
                return false;
            } else if(!this.p2.isEqual(comp.p2)) {
                return false;
            } else if(!this.p3.isEqual(comp.p3)) {
                return false;
            }
            return true;
        }
    }
}
