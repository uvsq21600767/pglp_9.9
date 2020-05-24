package fr.uvsq;

import java.sql.SQLException;

public class ExitCommand implements Command {
    @Override
    public void execute(String in) throws WrongArgumentNumber, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand {
        System.out.println("Exiting programm");
    }
}
