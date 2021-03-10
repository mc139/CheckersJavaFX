package pl.maciejcieslik.checkers;

import javafx.scene.layout.GridPane;
import pl.maciejcieslik.checkers.logic.Board;
import pl.maciejcieslik.checkers.logic.Color;

public class GameController {
    private GridPane grid;
    private Board board;
    private Color whoseMove = Color.WHITE;
    private int oldCol = -1;
    private int oldRow = -1;

    public GameController(GridPane grid, Board board) {
        this.grid = grid;
        this.board = board;
    }

    public void showOnBoard() {
        grid.getChildren().clear();
        // w petli odczytywac figury z board przez getfigure i umieszczac odpowiedznie obrazki na planszy
    }

    public void doClick(int col, int row) {
        if (oldCol == -1) {
            oldCol = col;
            oldRow = row;

        }else {
            if(board.move(oldRow,oldCol,row,col)){
                whoseMove = whoseMove == Color.WHITE ? Color.BLACK : Color.WHITE;
                oldCol = -1;
                oldRow = -1;

            }
        }
        showOnBoard();
    }
}
