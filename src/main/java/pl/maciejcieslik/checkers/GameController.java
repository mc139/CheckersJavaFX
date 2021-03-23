package pl.maciejcieslik.checkers;


import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import pl.maciejcieslik.checkers.logic.Board;
import pl.maciejcieslik.checkers.logic.Color;
import pl.maciejcieslik.checkers.logic.Pawn;
import pl.maciejcieslik.checkers.logic.Queen;

import static pl.maciejcieslik.checkers.logic.Color.BLACK;
import static pl.maciejcieslik.checkers.logic.Color.WHITE;


public class GameController {
    private static GridPane grid;
    private Board board;
    private Color whoseMove = Color.WHITE;
    private int oldCol = -1;
    private int oldRow = -1;


    public Image pawnIMGWhite = new Image("file:src/main/resources/WhitePawnSmall.png");
    public Image pawnIMGBlack = new Image("file:src/main/resources/blackpawn.png");
    public Image queenIMGWhite = new Image("file:src/main/resources/WhiteQueenSmall.png");
    public Image queenIMGBlack = new Image("file:src/main/resources/BlackQueenSmall.png");


    public GameController(GridPane grid, Board board) {
        this.grid = grid;
        this.board = board;
    }


    void createBoard() {

        grid.setAlignment(Pos.CENTER);
        board.createNewBoard();

        board.setFigure(0, 0, new Pawn(WHITE));
        board.setFigure(0, 2, new Pawn(WHITE));
        board.setFigure(0, 4, new Pawn(WHITE));
        board.setFigure(0, 6, new Pawn(WHITE));
        board.setFigure(1, 1, new Pawn(WHITE));
        board.setFigure(1, 3, new Pawn(WHITE));
        board.setFigure(1, 5, new Pawn(WHITE));
        board.setFigure(1, 7, new Pawn(WHITE));

        board.setFigure(7, 1, new Pawn(BLACK));
        board.setFigure(7, 3, new Pawn(BLACK));
        board.setFigure(7, 5, new Pawn(BLACK));
        board.setFigure(7, 7, new Pawn(BLACK));
        board.setFigure(6, 0, new Pawn(BLACK));
        board.setFigure(6, 2, new Pawn(BLACK));
        board.setFigure(6, 4, new Pawn(BLACK));
        board.setFigure(6, 6, new Pawn(BLACK));

    }

    public void ShowBoardOnConsole() {
        System.out.println(board.toString());
    }

    public void showOnBoard() {
        grid.getChildren().clear();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ImageView iv = null;
                if (board.getFigure(row, col) instanceof Pawn) {
                    if (board.getFigure(row, col).getColor().equals(BLACK)) {
                        iv = new ImageView(pawnIMGBlack);
                    }
                    if (board.getFigure(row, col).getColor().equals(WHITE)) {
                        iv = new ImageView(pawnIMGWhite);
                    }
                }
                if (board.getFigure(row, col) instanceof Queen) {
                    if (board.getFigure(row, col).getColor().equals(BLACK)) {
                        iv = new ImageView(queenIMGBlack);
                    }
                    if (board.getFigure(row, col).getColor().equals(WHITE)) {
                        iv = new ImageView(queenIMGWhite);
                    }
                }
                if (iv != null)
                    grid.add(iv, col, row);
                if (col == oldCol && row == oldRow) {
                    Rectangle rectangle = new Rectangle(135.5, 135.5);
                    rectangle.setStroke(javafx.scene.paint.Color.RED);
                    rectangle.setFill(javafx.scene.paint.Color.TRANSPARENT.darker().invert());
                    grid.add(rectangle, col, row);
                }
            }

        }
    }

    public void doClick(int col, int row) {
        Color color = board.getFigure(row, col).getColor();
        if (color == whoseMove || oldCol != -1) {
            if (oldCol == -1) {
                oldCol = col;
                oldRow = row;
            } else {
                if (board.move(oldRow, oldCol, row, col)) {
                    whoseMove = whoseMove == Color.WHITE ? BLACK : Color.WHITE;
                    oldCol = -1;
                    oldRow = -1;
                }
            }
        } else {
            oldCol = -1;
            oldRow = -1;
        }
        showOnBoard();
        ShowBoardOnConsole();
    }
}
