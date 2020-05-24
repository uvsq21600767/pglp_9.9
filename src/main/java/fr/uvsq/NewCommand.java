package fr.uvsq;

import java.sql.SQLException;

public class NewCommand implements Command {

    @Override
    public void execute(String in) throws WrongArgumentNumber, EmptyObjectException, RadiusException, SQLException, ShapeException, DimensionException, SizeException, InvalidNameException, InvalidCommand, ConnectionException, CloseException {
        String cmd[] = in.split(" ");

        if(cmd[1].equals("circle")) {

            if(cmd.length != 6) {
                throw new WrongArgumentNumber();
            }
            Circle c = new Circle(new Point(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4])), Integer.parseInt(cmd[5]), cmd[2]);
            DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
            try {
                circleDAO.storeObj(c);
            } catch (ShapeException e) {
                System.out.println("Object with this name already in DB");
                throw new ShapeException();
            }
        } else if(cmd[1].equals("rectangle")) {

            if(cmd.length != 7) {
                throw new WrongArgumentNumber();
            }
            Rectangle r = new Rectangle(new Point(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4])), Integer.parseInt(cmd[5]), Integer.parseInt(cmd[6]), cmd[2]);
            DAO<Rectangle> rectangleDAO = DAOFactory.getRectangleDAO();
            try {
                rectangleDAO.storeObj(r);
            } catch (ShapeException e) {
                System.out.println("Object with this name already in DB");
                throw new ShapeException();
            }
        } else if(cmd[1].equals("square")) {

            if(cmd.length != 6) {
                throw new WrongArgumentNumber();
            }
            Square r = new Square(new Point(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4])), Integer.parseInt(cmd[5]), cmd[2]);
            DAO<Square> squareDAO = DAOFactory.getSquareDAO();
            try {
                squareDAO.storeObj(r);
            } catch (ShapeException e) {
                System.out.println("Object with this name already in DB");
                throw new ShapeException();
            }
        } else if(cmd[1].equals("triangle")) {

            if(cmd.length != 9) {
                throw new WrongArgumentNumber();
            }
            Triangle t = new Triangle(new Point(Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4])), new Point(Integer.parseInt(cmd[5]), Integer.parseInt(cmd[6])), new Point(Integer.parseInt(cmd[7]), Integer.parseInt(cmd[8])), cmd[2]);
            DAO<Triangle> triangleDAO = DAOFactory.getTriangleDAO();
            try {
                triangleDAO.storeObj(t);
            } catch (ShapeException e) {
                System.out.println("Object with this name already in DB");
                throw new ShapeException();
            }
        } else if(cmd[1].equals("group")) {

            if(cmd.length != 4) {
                throw new WrongArgumentNumber();
            }
            CompositeShape comp = new CompositeShape(cmd[2]);
            DAO<CompositeShape> compositeShapeDAO = DAOFactory.getCompositeShapeDAO();
            try {
                DAO<Circle> circleDAO = DAOFactory.getCircleDAO();
                DAO<Rectangle> rectangleDAO = DAOFactory.getRectangleDAO();
                DAO<Square> squareDAO = DAOFactory.getSquareDAO();
                DAO<Triangle> triangleDAO = DAOFactory.getTriangleDAO();

                if(circleDAO.inBase(cmd[3])) {
                    Circle c = circleDAO.searchObj(cmd[3]);
                    comp.add(c);
                } else if(rectangleDAO.inBase(cmd[3])) {
                    Rectangle r = rectangleDAO.searchObj(cmd[3]);
                    comp.add(r);
                } else if(squareDAO.inBase(cmd[3])) {
                    Square s = squareDAO.searchObj(cmd[3]);
                    comp.add(s);
                } else if(triangleDAO.inBase(cmd[3])) {
                    Triangle t = triangleDAO.searchObj(cmd[3]);
                    comp.add(t);
                } else {
                    throw new ShapeException();
                }

                compositeShapeDAO.storeObj(comp);
            } catch (ShapeException e) {
                System.out.println("Object with this name already in DB");
                throw new ShapeException();
            }
        } else {
            throw new InvalidCommand();
        }
    }
}
