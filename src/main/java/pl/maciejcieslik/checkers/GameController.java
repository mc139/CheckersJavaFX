package pl.maciejcieslik.checkers;


import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import pl.maciejcieslik.checkers.logic.*;

import static pl.maciejcieslik.checkers.logic.Color.BLACK;
import static pl.maciejcieslik.checkers.logic.Color.WHITE;


public class GameController {
    private GridPane grid;
    private Board board;
    private Color whoseMove = Color.WHITE;
    private int oldCol = -1;
    private int oldRow = -1;


    public Image pawnIMGWhite = new Image("file:src/main/resources/WhitePawnSmall.png");
    public Image pawnIMGBlack = new Image("file:src/main/resources/BlackPawnSmall.png");
    public Image queenIMGWhite = new Image("file:src/main/resources/WhiteQueenSmall.png");
    public Image queenIMGBlack = new Image("file:src/main/resources/BlackQueenSmall.png");

    public GameController(GridPane grid, Board board) {
        this.grid = grid;
        this.board = board;
    }


    void createBoard() {
        board.createNewBoard();
        board.setFigure(1, 1, new Pawn(BLACK));
        board.setFigure(1, 3, new Pawn(BLACK));
        board.setFigure(1, 5, new Pawn(BLACK));
        board.setFigure(1, 7, new Pawn(BLACK));
        board.setFigure(2, 3, new Pawn(BLACK));
        board.setFigure(2, 5, new Pawn(BLACK));
        board.setFigure(2, 7, new Pawn(BLACK));
        board.setFigure(2, 7, new Pawn(BLACK));

        board.setFigure(7, 1, new Pawn(WHITE));
        board.setFigure(7, 3, new Pawn(WHITE));
        board.setFigure(7, 5, new Pawn(WHITE));
//        board.setFigure(7, 8, new Pawn(BLACK));
        board.setFigure(6, 3, new Pawn(WHITE));
        board.setFigure(6, 5, new Pawn(WHITE));
//        board.setFigure(6, 8, new Pawn(WHITE));

    }


    public void showOnBoard() {

        createBoard();

//        for (int i = 0; i < 8; i++) {
//
//        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.getFigure(row, col) instanceof Pawn) {
                    if (board.getFigure(row, col).getColor().equals(BLACK)) {
                        ImageView pawnBlk = new ImageView(pawnIMGBlack);
                        grid.add(pawnBlk, col, row);
                        grid.setHalignment(pawnBlk, HPos.LEFT);

                    } else {
                        ImageView pawnWht = new ImageView(pawnIMGWhite);
                        grid.add(pawnWht, col, row);
                        grid.setHalignment(pawnWht, HPos.LEFT);

                    }
                }
                if (board.getFigure(row, col) instanceof Queen) {
                    if (board.getFigure(row, col).getColor().equals(BLACK)) {
                        ImageView queenBlk = new ImageView(queenIMGBlack);
                        grid.add(queenBlk, col, row);
                        grid.setHalignment(queenBlk, HPos.LEFT);

                    } else {
                        ImageView queenWht = new ImageView(queenIMGWhite);
                        grid.add(queenWht, col, row);
                        grid.setHalignment(queenWht, HPos.LEFT);

                    }
                }
            }
        }

    }
    // w petli odczytywac figury z board przez getfigure i umieszczac odpowiednie obrazki na planszy

    public void doClick(int col, int row) {
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
        showOnBoard();

    }

}
