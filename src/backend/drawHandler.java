package backend;

import application.MainWindowController;
import javafx.application.Application;

public class drawHandler {
    public static void main(String[] args) {
        MainWindowController app = new MainWindowController();
        app.showWindow(args);
        System.out.println("exit");
    }
}
