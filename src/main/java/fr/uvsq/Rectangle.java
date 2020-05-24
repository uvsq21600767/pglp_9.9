package fr.uvsq;

public class Rectangle implements Shape {

    /**
     * bl : the bottom left point of the rectangle
     * L : the length
     * H : the height
     * name : name of the rectangle
     */
    private Point bl;
    private int L;
    private int H;
    private String name;

    Rectangle() {
        this.bl = new Point();
        this.L = 2;
        this.H = 1;
        this.name = "Rectangle";
    }

    Rectangle(Point bl, int L, int H, String name) throws EmptyObjectException, DimensionException {
        if(bl == null) {
            throw new EmptyObjectException();
        } else if((L < 0) || (H < 0)) {
            throw new DimensionException();
        } else {
            this.bl = new Point(bl);
            this.L = L;
            this.H = H;
            this.name = name;
        }
    }

    Rectangle(Rectangle origin) throws EmptyObjectException {
        if(origin == null) {
            throw new EmptyObjectException();
        } else {
            this.bl = new Point(origin.getBl());
            this.L = origin.getL();
            this.H = origin.getH();
            this.name = origin.getName();
        }
    }

    /**
     * Getter of bl
     * @return this.bl
     */
    public Point getBl() {
        return this.bl;
    }

    /**
     * Getter of L
     * @return this.L
     */
    public int getL() {
        return this.L;
    }

    /**
     * Getter of H
     * @return this.H
     */
    public int getH() {
        return this.H;
    }

    /**
     * Getter of name
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter of bl
     * @param bl : the new value of bl
     * @throws EmptyObjectException if bl is null
     */
    public void setBl(Point bl) throws EmptyObjectException {
        if(bl == null) {
            throw new EmptyObjectException();
        } else {
            this.bl = bl;
        }
    }

    /**
     * Setter of L
     * @param L : the new value of L
     * @throws DimensionException if L lower than 0
     */
    public void setL(int L) throws DimensionException {
        if(L < 0) {
            throw new DimensionException();
        } else {
            this.L = L;
        }
    }

    /**
     * Setter of H
     * @param H : the new value of H
     * @throws DimensionException if H lower than 0
     */
    public void setH(int H) throws DimensionException {
        if(H < 0) {
            throw new DimensionException();
        } else {
            this.H = H;
        }
    }

    /**
     * Setter of name
     * @param name : the new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void translate(int x, int y) {
        this.bl.setX(this.bl.getX() + x);
        this.bl.setY(this.bl.getY() + y);
    }

    @Override
    public String toString() {
        String str = "";
        str += "Name : " + this.getName() + " "
                + "Bottom Left Point (" + this.getBl().getX() + "," + this.getBl().getY() + ") L : " + this.getL() + " H : " + this.getL();
        return str;
    }
    /**
     * print the Shape
     */
    @Override
    public void print() {
        System.out.println(this.toString());
    }

    public boolean isEqual(Rectangle comp) throws EmptyObjectException {
        if(comp == null) {
            throw new EmptyObjectException();
        } else {
            if(!this.bl.isEqual(comp.getBl())) {
                return false;
            } else if((this.getL() != comp.getL()) || (this.getH() != comp.getH())) {
                return false;
            }
        }
        return true;
    }
}
