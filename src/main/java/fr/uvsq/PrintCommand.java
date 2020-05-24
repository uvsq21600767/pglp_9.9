package fr.uvsq;

import java.sql.SQLException;

public class PrintCommand implements Command{

    @Override
    public void execute(String in) throws WrongArgumentNumber, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand, ConnectionException, CloseException {
        String[] cmd = in.split(" ");

        if (cmd[1].equals("shape")) {
            if (cmd.length != 3) {
                throw new WrongArgumentNumber();
            }

            try {
                DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
                DAO<Rectangle> rectangleDAO = DAOFactory.getRectangleDAO();
                DAO<Square> squareDAO = DAOFactory.getSquareDAO();
                DAO<Triangle> triangleDAO = DAOFactory.getTriangleDAO();

                if(circleDAO.inBase(cmd[2])) {
                    Circle c = circleDAO.searchObj(cmd[2]);
                    c.print();
                } else if(rectangleDAO.inBase(cmd[2])) {
                    Rectangle r = rectangleDAO.searchObj(cmd[2]);
                    r.print();
                } else if(squareDAO.inBase(cmd[2])) {
                    Square s = squareDAO.searchObj(cmd[2]);
                    s.print();
                } else if(triangleDAO.inBase(cmd[2])) {
                    Triangle t = triangleDAO.searchObj(cmd[2]);
                    t.print();
                } else {
                    throw new ShapeException();
                }
            } catch (ShapeException e) {
                System.out.println("Error during printint : invalid shape");
                throw new ShapeException();
            }
        } else if(cmd[1].equals("group")) {
            if (cmd.length != 3) {
                throw new WrongArgumentNumber();
            }

            try {
                DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
                CompositeShape comp = compositeShapeDAO.searchObj(cmd[2]);
                comp.print();
            } catch (Exception e) {
                System.out.println("Error during printing Group");
            }
        } else if(cmd[1].equals("table")) {
            if(cmd.length != 2) {
                throw new WrongArgumentNumber();
            }
            try {
                DataBase bd = new DataBase();
                bd.printTableGroup();
                bd.printTableShape();
            } catch (SQLException e) {
                System.out.println("Canno't print tables");
            }
        } else {
            throw new InvalidCommand();
        }
    }
}
