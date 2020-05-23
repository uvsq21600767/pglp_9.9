package fr.uvsq;

import java.sql.*;

public class DataBase {

    private Connection connection;
    private String dburl;

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection c) {
        this.connection = c;
    }

    DataBase() throws SQLException {
        this.dburl = "jdbc:derby:DBShape;create=true";
        try {
            this.connection = DriverManager.getConnection(dburl);
        } catch (Exception e) {
            System.out.println("Connection failed");
        }
        this.connection.close();
        this.connection = null;
    }

    public void connect() throws SQLException {
        try {
            this.setConnection(DriverManager.getConnection(this.dburl));
            System.out.println("Successful connection");
        } catch (Exception e) {
            System.out.println("Connection failed");
        }

    }
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

        this.connect();
        Statement sta = this.connection.createStatement();

        try {
            sta.execute(Shape);
        } catch (Exception e) {
            System.out.println("Failed to create Table SHAPE");
            e.printStackTrace();
            this.closeConn();
            return false;
        }

        System.out.println("Table SHAPE created with success");
        this.closeConn();
        return true;
    }

    public boolean printTableShape() throws SQLException {

        this.connect();
        PreparedStatement req = this.connection.prepareStatement("SELECT * FROM SHAPE");
        try {
            String rep = "";
            req.execute();
            ResultSet res = req.getResultSet();
            rep = rep + "---Printing SHAPE---\n";
            while (res.next()) {
                rep = rep + res.getObject(1) + res.getObject(2) + "\n";
            }
            System.out.println(rep);
        } catch (Exception e) {
            System.out.println("Printing Table SHAPE failed");
            this.closeConn();
            return false;
        }
        System.out.println("---Ending printing---");
        this.closeConn();
        return true;
    }

    public boolean dropShape() throws SQLException {
        this.connect();
        Statement sta = this.connection.createStatement();

        try {
            sta.execute("DROP TABLE SHAPE");
        } catch (Exception e) {
            System.out.println("Failed to drop Table SHAPE");
            e.printStackTrace();
            this.closeConn();
            return false;
        }
        System.out.println("Table SHAPE dropped with success");
        this.closeConn();
        return true;
    }

    public void closeConn() throws SQLException {
        this.connection.close();
        System.out.println("Connection closed with success");
    }
}
