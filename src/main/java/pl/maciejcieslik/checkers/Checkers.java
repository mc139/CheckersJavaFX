package pl.maciejcieslik.checkers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.input.MouseDragEvent.MOUSE_DRAG_ENTERED;
import static javafx.scene.input.MouseEvent.copyForMouseDragEvent;

public class Checkers extends Application {

    private Image board = new Image("file:src/main/resources/board.png");
    private Image pawn = new Image("file:src/main/resources/blackpawn.png");
    private FlowPane figures = new FlowPane(Orientation.HORIZONTAL);


//    @Override
//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        grid.setHgap(3.5);
        grid.setVgap(3.5);
        grid.setBackground(background);


        ImageView img = new ImageView(pawn);
        figures.getChildren().add(img);

        grid.add(figures, 1, 1, 3, 1);

        Scene scene = new Scene(grid, 1100, 1100, Color.WHITE);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();




        grid.setOnMouseClicked(e -> {
            double y = e.getX()/ 137;
            double x = e.getY()/ 137;
            int xToInteger = (int) Math.round(x);
            int yToInteger = (int) Math.round(y);

            System.out.println("[" + (8-xToInteger) + ", " + yToInteger + "]");
        });


        // TO DO
        // CONNECT TO CHECKERS LOGIC, CREATE AVAILABLE MOVES ON THAT PROJECT!
    }
}

