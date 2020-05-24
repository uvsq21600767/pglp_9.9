package fr.uvsq;

import java.sql.SQLException;

public class NullCommand implements Command {
    @Override
    public void execute(String in) throws WrongArgumentNumber, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand {
        System.out.println("Invalid command : please use the command given in the READ.ME");
    }
}
