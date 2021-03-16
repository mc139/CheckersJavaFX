package pl.maciejcieslik.checkers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        grid.setPadding(new Insets(0, 0, 0, 12.5));
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setBackground(background);

        Scene scene = new Scene(grid, 1100, 1100, Color.BLACK);
        Board board = new Board();
        GameController controller = new GameController(grid, board);
        controller.createBoard();
        controller.showOnBoard();

        Button newGameButton = new Button();
        newGameButton.setText("New Game");
        newGameButton.setOnAction((e) -> {
            controller.createBoard();
        });
        controller.gridPaneInit();

        Button debugButton = new Button();
        debugButton.setText("DEBUG");
        debugButton.setOnAction((e) -> {
            controller.ShowBoardOnConsole();

        });

        Button moveButton = new Button();
        moveButton.setText("MOVE");
        moveButton.setOnAction((e) -> {
            board.move(2, 6, 3, 7);
        });
        Button incorrectMove = new Button();
        incorrectMove.setText("INCORECT MOVE");
        incorrectMove.setOnAction((e) -> {
            board.move(2, 6, 4, 7);
        });


//        grid.add(newGameButton, 4, 4);
//        grid.add(debugButton, 5, 5);
//        grid.add(incorrectMove,3,3);
//        grid.add(moveButton,2,5);


        grid.add(newGameButton, 4, 6);
        grid.add(debugButton, 4, 3);
        grid.add(incorrectMove,4,5);
        grid.add(moveButton,4,4);

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaxHeight(1100);
        primaryStage.setMaxWidth(1100);
        primaryStage.setMinHeight(1100);
        primaryStage.setMinWidth(1100);
        controller.showOnBoard();


        grid.setOnMouseClicked(e -> {
            double y = e.getX() / 135;
            double x = e.getY() / 135;
            int xToInteger = (int) Math.round(x);
            int yToInteger = (int) Math.round(y);
            controller.doClick(xToInteger, yToInteger);
            System.out.println("[" + xToInteger + ", " + yToInteger + "]");
        });

    }
}

