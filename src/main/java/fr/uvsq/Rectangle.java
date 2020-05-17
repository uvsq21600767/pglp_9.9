package fr.uvsq;

public class Rectangle extends Shape<Rectangle> {

    /**
     * bl : the bottom left point of the rectangle
     * L : the length
     * H : the height
     */
    private Point bl;
    private int L;
    private int H;

    Rectangle() {
        this.bl = new Point();
        this.L = 2;
        this.H = 1;
    }

    Rectangle(Point bl, int L, int H) throws EmptyObjectException, DimensionException {
        if(bl == null) {
            throw new EmptyObjectException();
        } else if((L < 0) || (H < 0)) {
            throw new DimensionException();
        } else {
            this.bl = new Point(bl);
            this.L = L;
            this.H = H;
        }
    }

    Rectangle(Rectangle origin) throws EmptyObjectException {
        if(origin == null) {
            throw new EmptyObjectException();
        } else {
            this.bl = new Point(origin.getBl());
            this.L = origin.getL();
            this.H = origin.getH();
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
     * @throws DimensionException if L < 0
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
     * @throws DimensionException if H < 0
     */
    public void setH(int H) throws DimensionException {
        if(H < 0) {
            throw new DimensionException();
        } else {
            this.H = H;
        }
    }

    @Override
    public void translate(int x, int y) {
        this.bl.setX(this.bl.getX() + x);
        this.bl.setY(this.bl.getY() + y);
    }

    @Override
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
