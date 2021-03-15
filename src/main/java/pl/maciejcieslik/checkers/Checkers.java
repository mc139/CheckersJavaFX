package pl.maciejcieslik.checkers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.maciejcieslik.checkers.logic.Board;


public class Checkers extends Application {

    public Image boardImage = new Image("file:src/main/resources/board1100.png");

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(boardImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.setPadding(new Insets(0, 0, 0, 10));
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1100, 1100, Color.BLACK);
        Board board = new Board();
        GameController controller = new GameController(grid,board);
        controller.createBoard();
        controller.showOnBoard();

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaxHeight(1100);
        primaryStage.setMaxWidth(1100);
        primaryStage.setMinHeight(1100);
        primaryStage.setMinWidth(1100);

        grid.setOnMouseClicked(e -> {
            double y = e.getX()/ 135;
            double x = e.getY()/ 135;
            int xToInteger = (int) Math.round(x);
            int yToInteger = (int) Math.round(y);
            controller.doClick(xToInteger,yToInteger);
            System.out.println("[" + xToInteger + ", " + yToInteger + "]");
        });

    }
}

