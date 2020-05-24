package fr.uvsq;

import java.sql.SQLException;

public class DelCommand implements Command {
    @Override
    public void execute(String in) throws NotEnoughArgumentException, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand {
        String[] cmd = in.split(" ");

        if(cmd[1].equals("group")) {
            if(cmd.length != 3) {
                throw new NotEnoughArgumentException();
            }

            DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
            try {
                compositeShapeDAO.deletObj(cmd[2]);
            } catch (InvalidNameException e) {
                System.out.println("Failed to delete " + cmd[2]);
            }
        } else if(cmd[1].equals("shape")) {
            if(cmd.length != 3) {
                throw new NotEnoughArgumentException();
            }

            DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
            try {
                circleDAO.deletObj(cmd[2]);
            } catch (InvalidNameException e) {
                System.out.println("Failed to delete " + cmd[2]);
            }
        } else if(cmd[1].equals("unlink")) {
            if(cmd.length != 4) {
                throw new NotEnoughArgumentException();
            }

            DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
            try {
                compositeShapeDAO.deletObj(cmd[2], cmd[3]);
            } catch (InvalidNameException e) {
                System.out.println("Failed to delete association between" + cmd[2] + " and " + cmd[3]);
            }
        } else {
            throw new InvalidCommand();
        }
    }
}
