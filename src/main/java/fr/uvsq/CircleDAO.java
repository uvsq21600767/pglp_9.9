package fr.uvsq;

import java.sql.*;

public class CircleDAO extends DAO<Circle> {
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
     * @throws ConnectionException if any error during DriverManager.getConnection(dburl)
     */
    @Override
    public void connect() throws ConnectionException {
        try {
            this.setConnection(DriverManager.getConnection(dburl));
        } catch (SQLException e) {
            System.out.println("Connection failed");
            throw new ConnectionException();
        }
    }

    /**
     * Close the connection
     *
     * @throws CloseException if error during the connection.close()
     */
    @Override
    public void closeConn() throws CloseException {
        try {
            this.connection.close();
        }catch (SQLException e) {
            throw new CloseException();
        }

    }

    /**
     * Create the object in the DataBase
     *
     * @param shape the shape to insert in the DB
     * @return the inserted Object
     * @throws ShapeException if error invalid Shape
     * @throws ConnectionException   if error during Connection
     * @throws CloseException if error during closing of the connection
     */
    @Override
    public Circle storeObj(Circle shape) throws ShapeException, ConnectionException, CloseException {
        try {
            this.connect();
        } catch (ConnectionException e) {
            throw new ConnectionException();
        }
        PreparedStatement sta;
        try {
            sta = this.connection.prepareStatement("INSERT INTO SHAPE(Name, p1x, p1y, radius) VALUES (?, ?, ?, ?)");
            sta.setString(1, shape.getName());
            sta.setInt(2, shape.getCenter().getX());
            sta.setInt(3, shape.getCenter().getY());
            sta.setInt(4, shape.getRadius());
            sta.execute();
        } catch (SQLException e) {
            try {
                this.closeConn();
            } catch (CloseException f) {
                throw new CloseException();
            }
            throw new ShapeException();
        }
        try {
            this.closeConn();
        } catch (CloseException f) {
            throw new CloseException();
        }
        return shape;
    }

    /**
     * Delete the Object of the DB
     *
     * @param name the name of the object to delete
     * @throws InvalidNameException if invalid name of the Shape
     * @throws ConnectionException   if error during Connection
     * @throws CloseException if error during closing of the connection
     */
    @Override
    public void deletObj(String name) throws InvalidNameException, ConnectionException, CloseException {
        try {
            this.connect();
        } catch (ConnectionException e) {
            throw new ConnectionException();
        }
        PreparedStatement sta;
        try {
            sta = this.connection.prepareStatement("DELETE FROM SHAPE WHERE Name = ?");
            sta.setString(1, name);
            sta.execute();
            sta = this.connection.prepareStatement("DELETE FROM GROUPSHAPE WHERE NameS = ?");
            sta.setString(1, name);
            sta.execute();
        } catch (SQLException e) {
            try {
                this.closeConn();
            } catch (CloseException f) {
                throw new CloseException();
            }
            throw new InvalidNameException();
        }
        try {
            this.closeConn();
        } catch (CloseException f) {
            throw new CloseException();
        }
    }

    /**
     * Delete the Object of the DB for GROUPSHAPE
     *
     * @param nameG name of the Group
     * @param nameS name of the Shape
     */
    @Override
    public void deletObj(String nameG, String nameS) {
    }

    /**
     * Update a shape in the DB
     *
     * @param shape the shape to update
     * @return the updated shape
     * @throws ShapeException if invalid shape
     * @throws ConnectionException   if error during Connection
     * @throws CloseException if error during closing of the connection
     */
    @Override
    public Circle updateObj(Circle shape) throws ShapeException, ConnectionException, CloseException {
        try {
            this.connect();
        } catch (ConnectionException e) {
            throw new ConnectionException();
        }
        PreparedStatement sta;

        try {
            sta = this.connection.prepareStatement("UPDATE SHAPE SET p1x = ? , p1y = ? , radius = ? WHERE Name = ?");
            sta.setInt(1, shape.getCenter().getX());
            sta.setInt(2, shape.getCenter().getY());
            sta.setInt(3, shape.getRadius());
            sta.setString(4, shape.getName());
            sta.execute();
        } catch (SQLException e) {
            try {
                this.closeConn();
            } catch (CloseException f) {
                throw new CloseException();
            }
            throw new ShapeException();
        }

        try {
            this.closeConn();
        } catch (CloseException f) {
            throw new CloseException();
        }
        return shape;
    }

    /**
     * Serach a shape in the DB
     *
     * @param name the name of the searched shape
     * @return the searched shape
     * @throws InvalidNameException if invalid name of the shape
     * @throws EmptyObjectException if error during creation of the Circle
     * @throws RadiusException if error during creation of the circle
     * @throws ConnectionException if errro during connection to the db
     * @throws CloseException if error during the close of the db
     */
    @Override
    public Circle searchObj(String name) throws InvalidNameException, EmptyObjectException, RadiusException, ConnectionException, CloseException {
        try {
            this.connect();
        } catch (ConnectionException e) {
            throw new ConnectionException();
        }
        PreparedStatement sta;
        ResultSet res;
        Circle c;

        try {
            sta = this.connection.prepareStatement("SELECT Name, p1x, p1y, radius FROM SHAPE WHERE Name = ?");
            sta.setString(1, name);
            sta.execute();
            res = sta.getResultSet();
            if(res.next()) {
                Point p = new Point(res.getInt("p1x"), res.getInt("p1y"));
                c = new Circle(p, res.getInt("radius"), res.getString("Name"));
            } else {
                try {
                    this.closeConn();
                } catch (CloseException f) {
                    throw new CloseException();
                }
                throw new InvalidNameException();
            }
        } catch (SQLException | CloseException e) {
            try {
                this.closeConn();
            } catch (CloseException f) {
                throw new CloseException();
            }
            e.printStackTrace();
            throw new InvalidNameException();
        }

        try {
            this.closeConn();
        } catch (CloseException f) {
            throw new CloseException();
        }
        return c;
    }

    /**
     * Check if the obejct name is a Circle
     * @param name name of the object
     * @return true if it's a circle, false if not
     * @throws SQLException if sql exception during the request
     * @throws ConnectionException if exception during connecting
     * @throws CloseException if exception during closing
     */
    @Override
    public boolean inBase(String name) throws SQLException, ConnectionException, CloseException {
        try {
            this.connect();
        } catch (ConnectionException e) {
            throw new ConnectionException();
        }
        PreparedStatement sta;
        ResultSet res;

        try{
            sta = this.connection.prepareStatement("SELECT * FROM SHAPE WHERE Name = ?");
            sta.setString(1, name);
            sta.execute();
            res = sta.getResultSet();

            if(res.next()) {
                if(res.getObject("radius") == null) {
                    try {
                        this.closeConn();
                    } catch (CloseException f) {
                        throw new CloseException();
                    }
                    return false;
                }
            } else {
                try {
                    this.closeConn();
                } catch (CloseException f) {
                    throw new CloseException();
                }
                return false;
            }
        } catch (SQLException | CloseException e) {
            try {
                this.closeConn();
            } catch (CloseException f) {
                throw new CloseException();
            }
            throw new SQLException();
        }
        try {
            this.closeConn();
        } catch (CloseException f) {
            throw new CloseException();
        }
        return true;
    }

}
