package pl.maciejcieslik.checkers.logic;

import java.util.ArrayList;
import java.util.List;


public class Board extends BoardRow {
    private List<BoardRow> rows = new ArrayList<>();
    private List<Figure> cols = new ArrayList<>();

    Range rangeAfterTakeOff = new Range(0, 7);
    Range rangeBeforeTakeOff = new Range(1, 6);
    private int rowAfterTakeOff;
    private int colAfterTakeOff;
    private boolean moveIsCorrect = rangeAfterTakeOff.contains(rowAfterTakeOff) && rangeAfterTakeOff.contains(colAfterTakeOff) ? true : false;
    private boolean multiTakeOff;
    boolean nonBorderLocation;


    public boolean isMultiTakeOff() {
        return multiTakeOff;
    }

    public void setRowAfterTakeOff(int rowAfterTakeOff) {
        this.rowAfterTakeOff = rowAfterTakeOff;
    }

    public void setColAfterTakeOff(int colAfterTakeOff) {
        this.colAfterTakeOff = colAfterTakeOff;
    }


    public Board() {
        for (int i = 0; i < 8; i++) {
            rows.add(new BoardRow());
        }
    }

    public void takeOff(int row1, int col1, int row2, int col2, Figure figure) {
        int rowAfterTakeOff = 0;
        int colAfterTakeOff = 0;
        boolean nonBorderLocation = rangeBeforeTakeOff.contains(row2) && rangeBeforeTakeOff.contains(col2) ? true : false;
        boolean isPositionEmpty = getFigure(rowAfterTakeOff, colAfterTakeOff) instanceof None ? true : false;

        if (moveIsCorrect && nonBorderLocation) {
            if (row1 < row2 && col1 < col2) {
                // both axis increases
                rowAfterTakeOff = row2 + 1;
                colAfterTakeOff = col2 + 1;
                if (isPositionEmpty) {
                    setFigure(row1, col1, new None());
                } else {
                    setFigure(row1, col1, figure);
                }
            }
            if (row1 > row2 && col1 > col2) {
                // both axis decreases
                rowAfterTakeOff = row2 - 1;
                colAfterTakeOff = col2 - 1;
                if (isPositionEmpty) {
                    setFigure(row1, col1, new None());
                } else {
                    setFigure(row1, col1, figure);
                }
            }
            if (row1 > row2 && col1 < col2) {
                //row decreases and col increases
                rowAfterTakeOff = row2 - 1;
                colAfterTakeOff = col2 + 1;
                if (isPositionEmpty) {
                    setFigure(row1, col1, new None());
                } else {
                    setFigure(row1, col1, figure);
                }
            }
            if (row1 < row2 && col1 > col2) {
                //row increases and col decreases
                rowAfterTakeOff = row2 + 1;
                colAfterTakeOff = col2 - 1;
                if (isPositionEmpty) {
                    setFigure(row1, col1, new None());
                } else {
                    setFigure(row1, col1, figure);
                }
            }
            if (getFigure(rowAfterTakeOff, colAfterTakeOff) instanceof None) {
                setFigure(rowAfterTakeOff, colAfterTakeOff, figure);
                setFigure(row2, col2, new None());
                setFigure(row1,col1,new None());
            }
        }
        if (!moveIsCorrect) {
            System.out.println("INCORRECT MOVE");
        }
    }


    public boolean checkFiguresAround(int currentCol, int currentRow, Figure figure) {
        if (moveIsCorrect) {
            if (getFigure(currentCol + 1, currentRow + 1).getColor() != figure.getColor()) {
                return true;
            }
            if (getFigure(currentCol - 1, currentRow - 1).getColor() != figure.getColor()) {
                return true;
            }
            if (getFigure(currentCol + 1, currentRow - 1).getColor() != figure.getColor()) {
                return true;
            }
            if (getFigure(currentCol - 1, currentRow + 1).getColor() != figure.getColor()) {
                return true;
            }
        }
        return false;
    }

    public boolean moveForPawn(int row1, int col1, int row2, int col2) {
        boolean isDiagonal = false;
        boolean isQueen = false;
        if (Math.abs(row2 - row1) == 1 && Math.abs(col2 - col1) == 1) {
            isDiagonal = true;
        } else {
            isDiagonal = false;
            System.out.println("Incorrect move !");
        }
        if (isDiagonal && isQueen == false && moveIsCorrect) {
            Figure newPosFigure = getFigure(row2, col2);
            Figure figure = getFigure(row1, col1);
            if (newPosFigure instanceof None) {
                setFigure(row2, col2, figure);
                setFigure(row1, col1, new None());
                return true;
            }
            if (figure.getColor() != newPosFigure.getColor() && moveIsCorrect) {
                takeOff(row1, col1, row2, col2, figure);
                return true;
            }
        }
        return false;
    }

    // TO DO
    // multi TakeOff

    public boolean moveForQueen(int row1, int col1, int row2, int col2) {
        boolean isDiagonal = false;
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
        return false;
    }

    public boolean move(int row1, int col1, int row2, int col2) {
        Figure nextPosFigure = getFigure(row2, col2);
        Figure figure = getFigure(row1, col1);

        if (moveIsCorrect) {
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
        }
        return false;
    }

    public Figure getFigure(int row, int col) {
        Object figure = rows.get(row).getColumn().get(col);
        if (figure instanceof Pawn && ((Pawn) figure).getColor().equals(Color.WHITE) && row == 7) {
            setFigure(row, col, new None());
            setFigure(row, col, new Queen(Color.WHITE));
        }
        if (figure instanceof Pawn && ((Pawn) figure).getColor().equals(Color.BLACK) && row == 0) {
            setFigure(row, col, new None());
            setFigure(row, col, new Queen(Color.BLACK));
        }
        return (Figure) figure;
    }

    public void setFigure(int row, int col, Figure figure) {
        rows.get(row).getColumn().set(col, figure);
    }

    public int getRowAfterTakeOff() {
        return rowAfterTakeOff;
    }

    public int getColAfterTakeOff() {
        return colAfterTakeOff;
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
        for (int row = 0; row < 8; row++) {
            board += "|";
            for (int col = 0; col < 8; col++) {
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
