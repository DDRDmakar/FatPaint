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

    @FXML private Canvas pic = new Canvas(500, 250);
    @FXML private Button clearImage;

    private final GraphicsContext gc = pic.getGraphicsContext2D();

    private Color colorAlpha, colorDraw;

    public MainWindowController() {
        System.out.println("init");
        colorAlpha = Color.BLACK;
        colorDraw = Color.WHITE;
    }

    @Override
    public void start(Stage s) throws Exception {

        System.out.println("start");

        primaryStage = s;
        //final GraphicsContext gc = pic.getGraphicsContext2D();

        gc.setFill(colorAlpha);
        gc.setStroke(colorDraw);

        pic.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        gc.beginPath();
                        gc.moveTo(event.getX(), event.getY());
                        gc.stroke();
                    }
                });

        pic.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        gc.lineTo(event.getX(), event.getY());
                        gc.stroke();
                    }
                });

        pic.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {}
                });

        primaryStage.setTitle("FatDraw");
        Group root = new Group();
        //reset();
        drawShapes();
        root.getChildren().add(FXMLLoader.load(getClass().getResource("draw.fxml")));
        root.getChildren().add(pic);
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    private void drawShapes() {

        System.out.println("draw shapes");

        //GraphicsContext gc = pic.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);

        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }

    private void reset() {
        GraphicsContext gc = pic.getGraphicsContext2D();

        gc.setFill(colorAlpha);
        gc.fillRect(0, 0, pic.getWidth(), pic.getHeight());
        System.out.println(pic.getWidth() + " " + pic.getHeight());
    }

    @FXML
    void clearAllPixels(ActionEvent event) {
        System.out.println("clear");
        reset();
        System.out.println(primaryStage);
    }

    void draw(ActionEvent event) {
        System.out.println("draw");
    }

    public void showWindow(String[] args) {
        launch(args);
    }
}
