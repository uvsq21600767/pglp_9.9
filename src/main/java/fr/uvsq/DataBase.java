package fr.uvsq;

import java.sql.*;

public class DataBase {

    /**
     * connection : connection to the DataBase
     * dburl : url of the database for the connection
     */
    private Connection connection = null;
    private final static String dburl = "jdbc:derby:DBShape;create=true";

    /**
     * Getter of connection
     * @return this.connection
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Setter of connection
     * @param c : new connection
     */
    public void setConnection(Connection c) {
        this.connection = c;
    }

    DataBase() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(dburl);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
        this.connection.close();
        this.connection = null;
    }

    /**
     * Connection to the database
     * @throws SQLException if any error during DriverManager.getConnection(dburl)
     */
    public void connect() throws SQLException {
        try {
            this.setConnection(DriverManager.getConnection(dburl));
        } catch (SQLException e) {
            System.out.println("Connection failed");
            throw new SQLException("Failure : Can't connect to the DB");
        }
    }

    /**
     * Creattion of table SHAPE
     * @return true if the creation is successful
     * @throws SQLException if error during the creation of the table
     */
    public boolean createTable() throws SQLException {
        String Shape = "CREATE TABLE SHAPE ("
                + "Name varchar(50),"
                + "p1x int,"
                + "p1y int,"
                + "p2x int,"
                + "p2y int,"
                + "p3x int,"
                + "p3y int,"
                + "radius int,"
                + "size int,"
                + "L int,"
                + "H int,"
                + "PRIMARY KEY(Name))";

        String Group = "CREATE TABLE GROUPSHAPE ("
                + "NameG varchar(50),"
                + "NameS varchar(50),"
                + "PRIMARY KEY(NameG, NameS))";

        this.connect();
        Statement sta = this.connection.createStatement();

        try {
            sta.execute(Shape);
        } catch (SQLException e) {
            System.out.println("Failed to create Table SHAPE : Table Already Exist");
        }

        try {
            sta.execute(Group);
        } catch (SQLException e) {
            System.out.println("Failed to create Table GROUPSHAPE : Table already exist");
        }

        System.out.println("Tables SHAPE and GROUPSHAPE in the DataBase");
        this.closeConn();
        return true;
    }

    /**
     * Print table SHAPE
     * @return true if the printing is correct
     * @throws SQLException if error during the execution of the request
     */
    public boolean printTableShape() throws SQLException {

        this.connect();
        PreparedStatement req = this.connection.prepareStatement("SELECT * FROM SHAPE");
        try {
            String rep = "";
            req.execute();
            ResultSet res = req.getResultSet();
            rep = rep + "---Printing SHAPE---\n";
            while (res.next()) {
                rep = rep + res.getObject(1) + " "
                        + res.getObject(2) + " "
                        + res.getObject(3)  + " "
                        + res.getObject(4) + " "
                        + res.getObject(5) + " "
                        + res.getObject(6) + " "
                        + res.getObject(7) + " "
                        + res.getObject(8) + " "
                        + res.getObject(9 ) + " "
                        + res.getObject(10) + " "
                        + res.getObject(11) + "\n";

            }
            System.out.println(rep);
        } catch (SQLException e) {
            System.out.println("Printing Table SHAPE failed");
            this.closeConn();
            throw new SQLException("Failure : can't select table SHAPE");
        }
        System.out.println("---Ending printing---");
        this.closeConn();
        return true;
    }

    /**
     * Print table GROUP
     * @return true if the printing is correct
     * @throws SQLException if error during the execution of the request
     */
    public boolean printTableGroup() throws SQLException {

        this.connect();
        PreparedStatement req = this.connection.prepareStatement("SELECT * FROM GROUPSHAPE");
        try {
            String rep = "";
            req.execute();
            ResultSet res = req.getResultSet();
            rep = rep + "---Printing GROUP---\n";
            while (res.next()) {
                rep = rep + res.getObject(1) + " "
                        + res.getObject(2) + "\n";
            }
            System.out.println(rep);
        } catch (SQLException e) {
            System.out.println("Printing Table GROUPSHAPE failed");
            this.closeConn();
            throw new SQLException("Failure : can't select table GROUPSHAPE");
        }
        System.out.println("---Ending printing---");
        this.closeConn();
        return true;
    }

    /**
     * Drop the table SHAPE
     * @return true if the drop is successful
     * @throws SQLException if error during the execution of the request
     */
    public boolean dropShape() throws SQLException {
        this.connect();
        Statement sta = this.connection.createStatement();

        try {
            sta.execute("DROP TABLE SHAPE");
        } catch (SQLException e) {
            System.out.println("Failed to drop Table SHAPE");
            e.printStackTrace();
            this.closeConn();
            return false;
        }
        System.out.println("Table SHAPE dropped with success");
        this.closeConn();
        return true;
    }

    /**
     * Drop the table GROUP
     * @return true if the drop is successful
     * @throws SQLException if error during the execution of the request
     */
    public boolean dropGroup() throws SQLException {
        this.connect();
        Statement sta = this.connection.createStatement();

        try {
            sta.execute("DROP TABLE GROUPSHAPE");
        } catch (SQLException e) {
            System.out.println("Failed to drop Table GROUPSHAPE");
            e.printStackTrace();
            this.closeConn();
            return false;
        }
        System.out.println("Table GROUPSHAPE dropped with success");
        this.closeConn();
        return true;
    }

    public boolean dropTable() throws SQLException {
        if(!dropShape()) {
            return false;
        } else if(!dropGroup()) {
            return false;
        }
        return true;
    }

    /**
     * Close the connection
     * @throws SQLException if error during the connection.close()
     */
    public void closeConn() throws SQLException {
        this.connection.close();
    }
}
