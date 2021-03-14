package pl.maciejcieslik.checkers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.maciejcieslik.checkers.logic.Board;


public class Checkers extends Application {

    public Image board = new Image("file:src/main/resources/board.png");
    public Image pawnIMGWhite = new Image("file:src/main/resources/WhitePawnSmall.png");
    public Image pawnIMGBlack = new Image("file:src/main/resources/BlackPawnSmall.png");
    public Image queenIMGWhite = new Image("file:src/main/resources/WhiteQueenSmall.png");
    public Image queenIMGBlack = new Image("file:src/main/resources/BlackQueenSmall.png");



    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        ColumnConstraints columns = new ColumnConstraints(80);
        RowConstraints rows = new RowConstraints(80);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BOTTOM_LEFT);
        grid.setPadding(new Insets(1, 1, 1, 1));
        grid.setHgap(1.5);
        grid.setVgap(1.5);
        grid.setBackground(background);
        grid.getRowConstraints().add(rows);
        grid.getColumnConstraints().add(columns);


//        ImageView img = new ImageView(pawn);
//        figuresWhite.getChildren().add(img);


        Scene scene = new Scene(grid, 1000, 1000, Color.WHITE);
        Board board = new Board();
        GameController controller = new GameController(grid,board);
        controller.showOnBoard();

        primaryStage.setTitle("Checkers");
        primaryStage.setScene(scene);
        primaryStage.show();

        grid.setOnMouseClicked(e -> {
            double y = e.getX()/ 125;
            double x = e.getY()/ 125;
            int xToInteger = (int) Math.round(x);
            int yToInteger = (int) Math.round(y);
            controller.doClick(xToInteger,yToInteger);
            System.out.println("[" + xToInteger + ", " + yToInteger + "]");
        });


        // TO DO
        // CONNECT TO CHECKERS LOGIC, CREATE AVAILABLE MOVES ON THAT PROJECT!
    }
}

