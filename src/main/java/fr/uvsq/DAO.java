package fr.uvsq;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO<S> {

    /**
     * connection : connection to the DataBase
     * dburl : url of the database for the connection
     */
    protected Connection connection = null;
    protected final static String dburl = "jdbc:derby:DBShape;create=true";

    /**
     * Setter of conn
     * @param conn : new Connection
     */
    public abstract void setConnection(Connection conn);

    /**
     * Getter of connection
     * @return this.connection
     */
    public abstract Connection getConnection();

    /**
     * Connection to the database
     * @throws SQLException if any error during DriverManager.getConnection(dburl)
     */
    public abstract void connect() throws SQLException;

    /**
     * Close the connection
     * @throws SQLException if error during the connection.close()
     */
    public abstract void closeConn() throws SQLException;

    /**
     * Create the object in the DataBase
     * @param shape the shape to insert in the DB
     * @return the inserted Object
     * @throws ShapeException if error invalid Shape
     * @throws SQLException if error during SQL request
     */
    public abstract S storeObj(S shape) throws ShapeException, SQLException;

    /**
     * Delete the Object of the DB
     * @param name the name of the object to delete
     * @throws InvalidNameException if invalid name of the Shape
     * @throws SQLException if error during SQL request
     */
    public abstract void deletObj(String name) throws InvalidNameException, SQLException;


    /**
     * Update a shape in the DB
     * @param shape the shape to update
     * @return the updated shape
     * @throws ShapeException if invalid shape
     * @throws SQLException if error during SQL request
     */
    public abstract S updateObj(S shape) throws ShapeException, SQLException;

    /**
     * Serach a shape in the DB
     * @param name the name of the searched shape
     * @return the searched shape
     * @throws InvalidNameException if invalid name of the shape
     * @throws SQLException if error during SQL request
     */
    public abstract S searchObj(String name) throws InvalidNameException, SQLException, EmptyObjectException, RadiusException, DimensionException;
}
