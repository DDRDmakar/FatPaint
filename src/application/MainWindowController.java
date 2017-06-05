package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.shape.ArcType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.awt.event.*;
import javafx.event.EventHandler;
//import java.beans.EventHandler;

public class MainWindowController extends Application {

    Stage primaryStage;

    public MainWindowController() {
        System.out.println("init windowcontroller");
    }

    @Override
    public void start(Stage s) throws Exception {

        System.out.println("start");

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
