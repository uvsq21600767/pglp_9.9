package fr.uvsq;

import java.sql.SQLException;

public class DrawingTUI {
    DrawingTUI() throws SQLException {
        DataBase db = new DataBase();
        db.createTable();
    }
    public Command nextCommand(String in) throws SQLException {
        String cmd[] = in.split(" ");
        if(cmd[0].equals("new")) {
            return new NewCommand();
        } else if(cmd[0].equals("del")) {
            return new DelCommand();
        } else if(cmd[0].equals("move")) {
            return new MoveCommand();
        } else if(cmd[0].equals("print")) {
            return new PrintCommand();
        } else if(cmd[0].equals("reset")) {
            return new ResetCommand();
        } else if(cmd[0].equals("exit")) {
            return new ExitCommand();
        }
        return new NullCommand();
    }
}
