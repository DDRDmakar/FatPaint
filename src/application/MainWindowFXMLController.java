package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.ArcType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class MainWindowFXMLController {

    // +====================[VARIABLES]====================+
    @FXML private Canvas pic;
    @FXML private Button clearImage;

    @FXML private Slider slider_size, slider_R, slider_G, slider_B;
    @FXML private Label slider_size_label, slider_R_label, slider_G_label, slider_B_label;

    @FXML private Rectangle
            colorSampleBlack,
            colorSampleWhite,
            colorSampleRed,
            colorSampleGreen,
            colorSampleBlue,
            colorSampleYellow,
            colorSamplePink;

    private GraphicsContext gc;

    private Color colorAlpha, colorDraw;

    // CONSTRUCTOR
    public void MainWindowFXMLController() {
        colorAlpha = Color.BLACK;
        colorDraw = Color.WHITE;
    }

    // +====================[PRIVATE FUNCTIONS]====================+

    private void drawDemo() {

        System.out.println("draw shapes");

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
        gc.setFill(colorAlpha);
        gc.fillRect(0, 0, pic.getWidth(), pic.getHeight());
    }

    @FXML
    void clearAllPixels(ActionEvent event) {
        reset();
    }

    private void refreshColor() {
        colorDraw = new Color(slider_R.getValue(), slider_G.getValue(), slider_B.getValue(), 1);
        gc.setStroke(colorDraw);
    }

    private void refreshColor(Color c) {
        colorDraw = c;
        gc.setStroke(colorDraw);
        slider_R.setValue(colorDraw.getRed());
        slider_G.setValue(colorDraw.getGreen());
        slider_B.setValue(colorDraw.getBlue());
        initLables();
    }

    private void initLables() {
        slider_size_label.setText(String.format("%.2f", slider_size.getValue()));
        slider_R_label.setText(String.format("%.2f", slider_R.getValue()));
        slider_G_label.setText(String.format("%.2f", slider_G.getValue()));
        slider_B_label.setText(String.format("%.2f", slider_B.getValue()));
    }

    // +====================[PUBLIC FUNCTIONS]====================+

    public void initialize() {
        gc = pic.getGraphicsContext2D();

        //drawDemo();

        pic.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        gc.setLineWidth(slider_size.getValue());
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

        slider_size.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                slider_size_label.setText(String.format("%.2f", new_val));
            }
        });

        slider_R.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                slider_R_label.setText(String.format("%.2f", new_val));
                refreshColor();
            }
        });
        slider_G.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                slider_G_label.setText(String.format("%.2f", new_val));
                refreshColor();
            }
        });
        slider_B.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                slider_B_label.setText(String.format("%.2f", new_val));
                refreshColor();
            }
        });

        colorSampleBlack.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) { refreshColor(Color.BLACK); }
                });
        colorSampleWhite.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) { refreshColor(Color.WHITE); }
                });
        colorSampleRed.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) { refreshColor(Color.RED); }
                });
        colorSampleGreen.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) { refreshColor(Color.GREEN); }
                });
        colorSampleBlue.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) { refreshColor(Color.BLUE); }
                });
        colorSampleYellow.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) { refreshColor(Color.YELLOW); }
                });
        colorSamplePink.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) { refreshColor(new Color(1, 0, 0.65,1 )); }
                });

        refreshColor();
        initLables();
        colorAlpha = Color.BLACK;
        colorDraw = Color.WHITE;
        gc.setFill(colorAlpha);
        gc.setStroke(colorDraw);
        reset();
    }
}
