package fr.uvsq;

import java.sql.SQLException;

public class ResetCommand implements Command{


    @Override
    public void execute(String in) throws NotEnoughArgumentException, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand {
        String cmd[] = in.split(" ");

        if(cmd[0].equals("reset")) {
            if(cmd.length != 1) {
                throw new NotEnoughArgumentException();
            }

            DataBase db = new DataBase();
            db.dropTable();
        }
        else {
            throw new InvalidCommand();
        }
    }
}
