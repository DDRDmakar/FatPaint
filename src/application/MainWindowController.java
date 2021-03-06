package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowController extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage s) throws Exception {
        primaryStage = s;

        javafx.scene.Node fl = FXMLLoader.load(getClass().getResource("draw.fxml"));

        primaryStage.setTitle("FatDraw");
        Group root = new Group();
        root.getChildren().add(fl);
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    public void showWindow(String[] args) {
        launch(args);
    }
}
