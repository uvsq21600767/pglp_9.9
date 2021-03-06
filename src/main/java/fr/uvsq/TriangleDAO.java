package fr.uvsq;

import java.sql.*;

public class TriangleDAO extends DAO<Triangle> {

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
    public Triangle storeObj(Triangle shape) throws ShapeException, SQLException {
        this.connect();
        PreparedStatement sta;

        try {
            sta = this.connection.prepareStatement("INSERT INTO SHAPE(Name, p1x, p1y, p2x, p2y, p3x, p3y) VALUES (?, ?, ?, ?, ?, ?, ?)");
            sta.setString(1, shape.getName());
            sta.setInt(2, shape.getP1().getX());
            sta.setInt(3, shape.getP1().getY());
            sta.setInt(4, shape.getP2().getX());
            sta.setInt(5, shape.getP2().getY());
            sta.setInt(6, shape.getP3().getX());
            sta.setInt(7, shape.getP3().getY());
            sta.execute();
        } catch (SQLException e) {
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
            sta = this.connection.prepareStatement("DELETE FROM SHAPE WHERE Name = ?");
            sta.setString(1, name);
            sta.execute();
            sta = this.connection.prepareStatement("DELETE FROM GROUPSHAPE WHERE NameS = ?");
            sta.setString(1, name);
            sta.execute();
        } catch (SQLException e) {
            this.closeConn();
            throw new InvalidNameException();
        }

        this.closeConn();
    }

    /**
     * Delete the Object of the DB for GROUPSHAPE
     *
     * @param nameG name of the Group
     * @param nameS name of the Shape
     * @throws InvalidNameException if invalid name of the Shape or Group
     * @throws SQLException         if error during SQL request
     */
    @Override
    public void deletObj(String nameG, String nameS) throws InvalidNameException, SQLException {

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
    public Triangle updateObj(Triangle shape) throws ShapeException, SQLException {
        this.connect();
        PreparedStatement sta;

        try {
            sta = this.connection.prepareStatement("UPDATE SHAPE SET p1x = ? , p1y = ? , p2x = ? , p2y = ? , p3x = ? , p3y = ? WHERE Name = ?");
            sta.setInt(1, shape.getP1().getX());
            sta.setInt(2, shape.getP1().getY());
            sta.setInt(3, shape.getP2().getX());
            sta.setInt(4, shape.getP2().getY());
            sta.setInt(5, shape.getP3().getX());
            sta.setInt(6, shape.getP1().getY());
            sta.setString(7, shape.getName());
            sta.execute();
        } catch (SQLException e) {
            this.closeConn();
            throw new ShapeException();
        }

        this.closeConn();
        return shape;
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
     */
    @Override
    public Triangle searchObj(String name) throws InvalidNameException, SQLException, EmptyObjectException, RadiusException, DimensionException {
        this.connect();
        PreparedStatement sta;
        ResultSet res;
        Triangle shape;

        try {
            sta = this.connection.prepareStatement("SELECT Name, p1x, p1y, p2x, p2y, p3x, p3y FROM SHAPE WHERE Name = ?");
            sta.setString(1, name);
            sta.execute();
            res = sta.getResultSet();
            if(res.next()) {
                Point p1 = new Point(res.getInt("p1x"), res.getInt("p1y"));
                Point p2 = new Point(res.getInt("p2x"), res.getInt("p2y"));
                Point p3 = new Point(res.getInt("p3x"), res.getInt("p3y"));
                shape = new Triangle(p1, p2, p3, res.getString("Name"));
            } else {
                this.closeConn();
                throw new InvalidNameException();
            }
        } catch (SQLException e) {
            this.closeConn();
            throw new InvalidNameException();
        }

        this.closeConn();
        return shape;
    }

    @Override
    public boolean inBase(String name) throws SQLException, ShapeException {
        this.connect();
        PreparedStatement sta;
        ResultSet res;

        try{
            sta = this.connection.prepareStatement("SELECT * FROM SHAPE WHERE Name = ?");
            sta.setString(1, name);
            sta.execute();
            res = sta.getResultSet();

            if(res.next()) {
                if(res.getObject("p2x") == null) {
                    this.closeConn();
                    return false;
                }
            } else {
                this.closeConn();
                return false;
            }
        } catch (SQLException e) {
            this.closeConn();
            throw new SQLException();
        }
        this.closeConn();
        return true;
    }

}