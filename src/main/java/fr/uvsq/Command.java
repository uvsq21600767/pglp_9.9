package fr.uvsq;

import java.sql.SQLException;

public interface Command {

    public void execute(String in) throws WrongArgumentNumber, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand, ConnectionException, CloseException, InvalidArgument, DoubleInsertException;
}
