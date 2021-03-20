package pl.maciejcieslik.checkers.logic;

import java.util.ArrayList;
import java.util.List;


public class Board extends BoardRow {
    private List<BoardRow> rows = new ArrayList<>();
    private List<Figure> cols = new ArrayList<>();


    public Board() {
        for (int i = 0; i < 9; i++) {
            rows.add(new BoardRow());
        }
    }

    public void takeOff(int row1, int col1, int row2, int col2, Figure figure) {
        int rowAfterTakeOff = 0;
        int colAfterTakeOff = 0;
        if (row1 < row2 && col1 < col2) {
            // both axis increases
            rowAfterTakeOff = row2 + 1;
            colAfterTakeOff = col2 + 1;
        }
        if (row1 > row2 && col1 > col2) {
            // both axis decreases
            rowAfterTakeOff = row2 - 1;
            colAfterTakeOff = col2 - 1;
        }
        if (row1 > row2 && col1 < col2) {
            //row decreases and col increases
            rowAfterTakeOff = row2 - 1;
            colAfterTakeOff = col2 + 1;
        }
        if (row1 < row2 && col1 > col2) {
            //row increases and col decreases
            rowAfterTakeOff = row2 + 1;
            colAfterTakeOff = col2 - 1;
        }
        if (getFigure(rowAfterTakeOff, colAfterTakeOff) instanceof None) {
            setFigure(rowAfterTakeOff, colAfterTakeOff, figure);
            setFigure(row2, col2, new None());
        } else {
            setFigure(row1, col1, figure);
            System.out.println("INCORRECT MOVE");
        }

    }

    public boolean moveForPawn(int row1, int col1, int row2, int col2) {
        boolean isDiagonal = false;
        if (Math.abs(row2 - row1) == 1 && Math.abs(col2 - col1) == 1) {
            isDiagonal = true;
        } else {
            isDiagonal = false;
            System.out.println("Incorrect move !");
        }
        if (isDiagonal) {
            Figure newPosFigure = getFigure(row2, col2);
            Figure figure = getFigure(row1, col1);
            if (newPosFigure instanceof None) {
                setFigure(row2, col2, figure);
                setFigure(row1, col1, new None());
                return true;
            }
            if (figure.getColor() != newPosFigure.getColor()) {
                takeOff(row1, col1, row2, col2, figure);
                return true;
            }

        }
        return false;
    }

    public boolean moveForQueen(int row1, int col1, int row2, int col2) {
        boolean isDiagonal = false;
        if (Math.abs(row2 - row1) == Math.abs(col2 - col1)) {
            isDiagonal = true;
        } else {
            isDiagonal = false;
        }
        if (isDiagonal) {
            Figure figure = getFigure(row1, col1);
            setFigure(row2, col2, figure);
            setFigure(row1, col1, new None());
            return true;
        }
        return false;
    }


    public void availableMoves(int row1, int col1, int row2, int col2) {
        Figure figureToCheck = getFigure(row1, col1);
        boolean isQueen = false;
        if (figureToCheck instanceof Pawn) {
            isQueen = false;
        } else if (figureToCheck.toString().contains("q")) {
            isQueen = true;
        }

    }

    public boolean move(int row1, int col1, int row2, int col2) {
        Figure nextPosFigure = getFigure(row2, col2);
        Figure figure = getFigure(row1, col1);

        if (figure instanceof Pawn) {
            if (nextPosFigure.getColor().equals(figure.getColor())) {
                System.out.println("Illegal MOVE !");
                return false;
            }
            if (nextPosFigure.getColor() != figure.getColor()) {
                return moveForPawn(row1, col1, row2++, col2++);
            }
            return moveForPawn(row1, col1, row2, col2);
        } else if (figure instanceof Queen) {
            return moveForQueen(row1, col1, row2, col2);
        }
        return false;
    }

    public Figure getFigure(int row, int col) {
        return (Figure) rows.get(row).getColumn().get(col);
    }


    public void setFigure(int row, int col, Figure figure) {
        rows.get(row).getColumn().set(col, figure);
    }

    public Board createNewBoard() {
        Board board = new Board();
        for (BoardRow rowsIteration : rows) {
            rowsIteration.BoardRow();
        }
        return board;
    }

    @Override
    public String toString() {
        String board = "";
        for (int row = 0; row < 9; row++) {
            board += "|";
            for (int col = 0; col < 9; col++) {
                board += rows.get(row).getColumn().get(col) + "|";
            }
            board += "\n";
        }
        return board;
    }

    public static void main(String[] args) {
        Board board1 = new Board();
        board1.createNewBoard();
        board1.setFigure(1, 2, new Pawn(Color.WHITE));
        System.out.println(board1);
    }

}
