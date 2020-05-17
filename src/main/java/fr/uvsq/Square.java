package fr.uvsq;

public class Square extends Shape<Square> {

    /**
     * bl : bottom left point of the Square
     * size : size of the square
     */
    private Point bl;
    private int size;

    Square() {
        this.bl = new Point();
        this.size = 1;
        this.name = "Square";
    }

    Square(Point position, int size, String name) throws EmptyObjectException, SizeException {
        if(position == null) {
            throw new EmptyObjectException();
        } else if(size < 0) {
            throw new SizeException();
        } else {
            this.bl = new Point(position);
            this.size = size;
            this.name = name;
        }
    }

    Square(Square origin) throws EmptyObjectException {
        if(origin == null) {
            throw new EmptyObjectException();
        } else {
            this.bl = new Point(origin.bl.getX(), origin.bl.getY());
            this.size = origin.getSize();
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
     * Getter of size
     * @return this.size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Getter of name
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter of bl
     * @param origin : the new bottom left point of the Square
     * @throws EmptyObjectException if origin is null
     */
    public void setBl(Point origin) throws EmptyObjectException {
        if(origin == null) {
            throw new EmptyObjectException();
        } else {
            this.bl = new Point(origin);
        }
    }

    /**
     * Setter of size
     * @param size : the new size of the Square
     * @throws SizeException if size < 0
     */
    public void setSize(int size) throws SizeException {
        if(size < 0) {
            throw new SizeException();
        } else {
            this.size = size;
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
     *
     * @param x translation on the x axe
     * @param y translation on the y axe
     */
    @Override
    public void translate(int x, int y) {
        this.bl.setX(this.bl.getX() + x);
        this.bl.setY(this.bl.getY() + y);
    }

    public boolean isEqual(Square comp) throws EmptyObjectException {
        if(comp == null) {
            throw new EmptyObjectException();
        } else {
            if(!this.bl.isEqual(comp.getBl())) {
                return false;
            } else if(this.size != comp.getSize()) {
                return false;
            }
        }
        return true;
    }
}
