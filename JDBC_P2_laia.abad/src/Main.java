import Controller.Controller;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Controller mysqlController = new Controller();

        System.out.println("Connecting to Database...");
        if (!mysqlController.startRemoteConnection()) System.exit(1);

        System.out.println("Getting database...");
        try {
            mysqlController.loadRemoteInfo();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
