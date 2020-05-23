package fr.uvsq;

import java.sql.*;

public class SquareDAO extends DAO<Square> {

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
    public Square storeObj(Square shape) throws ShapeException, SQLException {
        this.connect();
        PreparedStatement sta;

        try {
            sta = this.connection.prepareStatement("INSERT INTO SHAPE(Name, p1x, p1y, size) VALUES (?, ?, ?, ?)");
            sta.setString(1, shape.getName());
            sta.setInt(2, shape.getBl().getX());
            sta.setInt(3, shape.getBl().getY());
            sta.setInt(4, shape.getSize());
            sta.execute();
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
            sta = this.connection.prepareStatement("DELETE FROM SHAPE WHERE Name = ?");
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
     * Update a shape in the DB
     *
     * @param shape the shape to update
     * @return the updated shape
     * @throws ShapeException if invalid shape
     * @throws SQLException   if error during SQL request
     */
    @Override
    public Square updateObj(Square shape) throws ShapeException, SQLException {
        this.connect();
        PreparedStatement sta;

        try {
            sta = this.connection.prepareStatement("UPDATE SHAPE SET p1x = ? , p1y = ? , size = ? WHERE Name = ?");
            sta.setInt(1, shape.getBl().getX());
            sta.setInt(2, shape.getBl().getY());
            sta.setInt(3, shape.getSize());
            sta.setString(4, shape.getName());
            sta.execute();
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
    public Square searchObj(String name) throws InvalidNameException, SQLException, EmptyObjectException, RadiusException, DimensionException {
        this.connect();
        PreparedStatement sta;
        ResultSet res;
        Square shape;

        try {
            sta = this.connection.prepareStatement("SELECT Name, p1x, p2y, size FROM SHAPE WHERE Name = ?");
            sta.setString(1, name);
            sta.execute();
            res = sta.getResultSet();
            System.out.println("Check execute");
            if(res.next()) {
                Point p = new Point(res.getInt("p1x"), res.getInt("p2y"));
                shape = new Square(p, res.getInt("size"), res.getString("Name"));
            } else {
                this.closeConn();
                throw new InvalidNameException();
            }
        } catch (SQLException | SizeException e) {
            System.out.println("Failed sql request");
            this.closeConn();
            e.printStackTrace();
            throw new InvalidNameException();
        }

        this.closeConn();
        return shape;
    }
}

