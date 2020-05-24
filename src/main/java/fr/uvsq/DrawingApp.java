package fr.uvsq;

import java.sql.SQLException;
import java.util.Scanner;

public class DrawingApp {

    public static void run() throws DrawingException, SQLException {
        Scanner sc = new Scanner(System.in);
        DrawingTUI tui = new DrawingTUI();

        String input;

        do{
            input = sc.nextLine();
            try{
                tui.nextCommand(input).execute(input);
            } catch (WrongArgumentNumber | SQLException e ) {
                System.out.println(e.getMessage());
            }
        } while(!(input.equals("exit")));
        sc.close();
    }

    public static void main(final String[] args) throws DrawingException, SQLException {
        DrawingApp.run();
    }
}
