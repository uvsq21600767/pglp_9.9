package fr.uvsq;

import java.sql.SQLException;

public interface Command {

    public void execute(String in) throws NotEnoughArgumentException, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand;
}
