package fr.uvsq;

import java.sql.SQLException;

public class MoveCommand implements Command {
    @Override
    public void execute(String in) throws NotEnoughArgumentException, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand {
        String[] cmd = in.split(" ");

        if (cmd[1].equals("shape")) {
            if (cmd.length != 5) {
                throw new NotEnoughArgumentException();
            }

            try {
                DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
                DAO<Rectangle> rectangleDAO = DAOFactory.getRectangleDAO();
                DAO<Square> squareDAO = DAOFactory.getSquareDAO();
                DAO<Triangle> triangleDAO = DAOFactory.getTriangleDAO();

                if (circleDAO.inBase(cmd[2])) {
                    Circle c = circleDAO.searchObj(cmd[2]);
                    c.translate(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]));
                    circleDAO.updateObj(c);
                } else if (rectangleDAO.inBase(cmd[2])) {
                    Rectangle r = rectangleDAO.searchObj(cmd[2]);
                    r.translate(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]));
                    rectangleDAO.updateObj(r);
                } else if (squareDAO.inBase(cmd[2])) {
                    Square s = squareDAO.searchObj(cmd[2]);
                    s.translate(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]));
                    squareDAO.updateObj(s);
                } else if (triangleDAO.inBase(cmd[2])) {
                    Triangle t = triangleDAO.searchObj(cmd[2]);
                    t.translate(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]));
                    triangleDAO.updateObj(t);
                } else {
                    throw new ShapeException();
                }
            } catch (ShapeException e) {
                System.out.println("Error updating shape");
                throw new ShapeException();
            }
        } else if(cmd[1].equals("group")) {
            if (cmd.length != 5) {
                throw new NotEnoughArgumentException();
            }

            try{
                DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
                CompositeShape comp = compositeShapeDAO.searchObj(cmd[2]);
                comp.translate(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]));
                compositeShapeDAO.updateObj(comp);
            } catch (ShapeException e) {
                System.out.println("Error updating group");
                throw new ShapeException();
            }
        } else {
            throw new InvalidCommand();
        }
    }
}
