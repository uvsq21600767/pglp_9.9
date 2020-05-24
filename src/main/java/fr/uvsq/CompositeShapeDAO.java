package fr.uvsq;

import java.sql.*;
import java.util.List;

public class CompositeShapeDAO extends DAO<CompositeShape> {
    /**
     * Setter of conn
     *
     * @param conn : new Connection
     */
    @Override
    public void setConnection(Connection conn) {
        this.connection = conn;
    }

    /**
     * Getter of connection
     *
     * @return this.connection
     */
    @Override
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Connection to the database
     *
     * @throws SQLException if any error during DriverManager.getConnection(dburl)
     */
    @Override
    public void connect() throws SQLException {
        try {
            this.setConnection(DriverManager.getConnection(dburl));
        } catch (SQLException e) {
            System.out.println("Connection failed");
            throw new SQLException("Failure : Can't connect to the DB");
        }
    }

    /**
     * Close the connection
     *
     * @throws SQLException if error during the connection.close()
     */
    @Override
    public void closeConn() throws SQLException {
        this.connection.close();
    }

    /**
     * Create the object in the DataBase
     *
     * @param shape the shape to insert in the DB
     * @return the inserted Object
     * @throws ShapeException if error invalid Shape
     * @throws SQLException   if error during SQL request
     */
    @Override
    public CompositeShape storeObj(CompositeShape shape) throws ShapeException, SQLException {
        this.connect();
        PreparedStatement sta;
        List<Shape> listShape = shape.getChildShape();
        Shape tmp;
        try {
           for (int i = 0; i < listShape.size(); i++) {
               tmp = listShape.get(i);
               sta = this.connection.prepareStatement("INSERT INTO GROUPSHAPE(NameG, NameS) VALUES (?, ?)");
               sta.setString(1, shape.getName());
               sta.setString(2, tmp.getName());
               sta.execute();
           }
        } catch (SQLException e) {
            System.out.println("Failed sql request");
            e.printStackTrace();
            this.closeConn();
            throw new ShapeException();
        }

        this.closeConn();
        return shape;
    }

    /**
     * Delete the Object of the DB
     *
     * @param name the name of the object to delete
     * @throws InvalidNameException if invalid name of the Shape
     * @throws SQLException         if error during SQL request
     */
    @Override
    public void deletObj(String name) throws InvalidNameException, SQLException {
        this.connect();
        PreparedStatement sta;

        try {
            sta = this.connection.prepareStatement("DELETE FROM GROUPSHAPE WHERE NameG = ?");
            sta.setString(1, name);
            sta.execute();
        } catch (SQLException e) {
            System.out.println("Failed sql request");
            e.printStackTrace();
            this.closeConn();
            throw new InvalidNameException();
        }

        this.closeConn();
    }

    /**
     * Delete the Object of the DB
     *
     * @param nameG the name of the Group
     * @param nameS the name of the Shape
     * @throws InvalidNameException if invalid name of the Shape
     * @throws SQLException         if error during SQL request
     */
    @Override
    public void deletObj(String nameG, String nameS) throws InvalidNameException, SQLException {
        this.connect();
        PreparedStatement sta;

        try {
            sta = this.connection.prepareStatement("DELETE FROM GROUPSHAPE WHERE NameG = ? AND NameS = ?");
            sta.setString(1, nameG);
            sta.setString(2, nameS);
            sta.execute();
        } catch (SQLException e) {
            System.out.println("Failed sql request");
            e.printStackTrace();
            this.closeConn();
            throw new InvalidNameException();
        }

        this.closeConn();
    }

    /**
     * Update a shape in the DB
     *
     * @param shape the shape to update
     * @return the updated shape
     * @throws ShapeException if invalid shape
     * @throws SQLException   if error during SQL request
     */
    @Override
    public CompositeShape updateObj(CompositeShape shape) throws ShapeException, SQLException {
        return null;
    }

    /**
     * Serach a shape in the DB
     *
     * @param name the name of the searched shape
     * @return the searched shape
     * @throws InvalidNameException if invalid name of the shape
     * @throws SQLException         if error during SQL request
     * @throws EmptyObjectException if error during construction of the copy
     * @throws RadiusException      if error during construction of the circle
     * @throws DimensionException   if error during contsruction of the rectangle
     * @throws SizeException        if erro rduring constrcution of the square
     */
    @Override
    public CompositeShape searchObj(String name) throws InvalidNameException, SQLException, EmptyObjectException, RadiusException, DimensionException, SizeException {
        this.connect();
        PreparedStatement sta;
        ResultSet res;
        ResultSet resShape;
        CompositeShape comp;

        try {
            sta = this.connection.prepareStatement("SELECT * FROM GROUPSHAPE WHERE NameG = ?");
            sta.setString(1, name);
            sta.execute();
            res = sta.getResultSet();
            comp = new CompositeShape(name);
            while(res.next()) {
                try {
                    sta = this.connection.prepareStatement("SELECT * FROM SHAPE WHERE Name = ?");
                    String tmp = (String) res.getObject("NameS");
                    sta.setString(1, tmp);
                    sta.execute();
                    resShape = sta.getResultSet();
                    if(resShape.next()) {
                        String rad = resShape.getString("radius");
                        String L = resShape.getString("L");
                        String size = resShape.getString("size");
                        String p2x = resShape.getString("p2x");

                        if(rad != null) {
                            Point p = new Point(resShape.getInt("p1x"), resShape.getInt("p1y"));
                            Circle c = new Circle(p, resShape.getInt("radius"), tmp);
                            comp.add(c);
                        }
                        if(L != null) {
                            Point p = new Point(resShape.getInt("p1x"), resShape.getInt("p1y"));
                            Rectangle r = new Rectangle(p, resShape.getInt("L"), resShape.getInt("H"), tmp);
                            comp.add(r);
                        }

                        if(size != null) {
                            Point p = new Point(resShape.getInt("p1x"), resShape.getInt("p1y"));
                            Square s = new Square(p, resShape.getInt("size"), tmp);
                            comp.add(s);
                        }

                        if(p2x != null) {
                            Point p = new Point(resShape.getInt("p1x"), resShape.getInt("p1y"));
                            Point p2 = new Point(resShape.getInt("p2x"), resShape.getInt("p2y"));
                            Point p3 = new Point(resShape.getInt("p3x"), resShape.getInt("p3y"));
                            Triangle t = new Triangle(p, p2, p3, tmp);
                            comp.add(t);
                        }
                    } else {
                        System.out.println("Invalid row");
                    }
                } catch (Exception e) {
                    System.out.println("Failed sql request : selection of shape in group");
                    e.printStackTrace();
                    this.closeConn();
                    throw new InvalidNameException();
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed sql request : selection of group");
            this.closeConn();
            e.printStackTrace();
            throw new InvalidNameException();
        }

        this.closeConn();
        return comp;
    }

}
