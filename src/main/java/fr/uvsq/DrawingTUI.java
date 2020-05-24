package fr.uvsq;

public class DrawingTUI {

    public Command nextCommand(String in) {
        String cmd[] = in.split(" ");
        if(cmd[0].equals("new")) {
            return new NewCommand();
        } else if(cmd[0].equals("del")) {
            return new DelCommand();
        }
        return null;
    }
}
