package pl.maciejcieslik.checkers;


import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import pl.maciejcieslik.checkers.logic.Board;
import pl.maciejcieslik.checkers.logic.Color;
import pl.maciejcieslik.checkers.logic.Pawn;
import pl.maciejcieslik.checkers.logic.Queen;

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

    void gridPaneInit() {
        for (int n = 0; n < 8; n++) {
            grid.getColumnConstraints().add(new ColumnConstraints(135));
            grid.getRowConstraints().add(new RowConstraints(135));
        }
    }

    void createBoard() {

        grid.setAlignment(Pos.CENTER);
        board.createNewBoard();

        board.setFigure(1, 1, new Pawn(WHITE));
        board.setFigure(1, 3, new Pawn(WHITE));
        board.setFigure(1, 5, new Pawn(WHITE));
        board.setFigure(1, 7, new Pawn(WHITE));
        board.setFigure(2, 2, new Pawn(WHITE));
        board.setFigure(2, 4, new Pawn(WHITE));
        board.setFigure(2, 6, new Pawn(WHITE));
        board.setFigure(2, 8, new Pawn(WHITE));

        board.setFigure(8, 2, new Pawn(BLACK));
        board.setFigure(8, 4, new Pawn(BLACK));
        board.setFigure(8, 6, new Pawn(BLACK));
        board.setFigure(8, 8, new Pawn(BLACK));
        board.setFigure(7, 1, new Pawn(BLACK));
        board.setFigure(7, 3, new Pawn(BLACK));
        board.setFigure(7, 5, new Pawn(BLACK));
        board.setFigure(7, 7, new Pawn(BLACK));

    }

    void ShowBoardOnConsole() {
        System.out.println(board.toString());
    }

    public void showOnBoard() {


        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getFigure(row, col) instanceof Pawn) {
                    if (board.getFigure(row, col).getColor().equals(BLACK)) {
                        ImageView pawnBlk = new ImageView(pawnIMGBlack);
                        grid.add(pawnBlk, col - 1, row - 1);

                    }
                    if (board.getFigure(row, col).getColor().equals(WHITE)) {
                        ImageView pawnWht = new ImageView(pawnIMGWhite);
                        grid.add(pawnWht, col - 1, row - 1);
                    }
                }
                if (board.getFigure(row, col) instanceof Queen) {
                    if (board.getFigure(row, col).getColor().equals(BLACK)) {
                        ImageView queenBlk = new ImageView(queenIMGBlack);
                        grid.add(queenBlk, col - 1, row - 1);

                    }
                    if (board.getFigure(row, col).getColor().equals(BLACK)) {
                        ImageView queenWht = new ImageView(queenIMGWhite);
                        grid.add(queenWht, col - 1, row - 1);

                    }
                    if (board.getFigure(row, col) == null) {
                        ImageView none = new ImageView();
                    }
                }
            }
        }
    }

    // w petli odczytywac figury z board przez getfigure i umieszczac odpowiednie obrazki na planszy
    // dlaczego figury kopiuja sie na gridzie a na planszy sie przesuwaja ?


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
