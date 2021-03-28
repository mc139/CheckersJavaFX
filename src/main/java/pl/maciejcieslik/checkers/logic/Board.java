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
    boolean moveFinished;

    public boolean isMoveFinished() {
        return moveFinished;
    }

    public Board() {
        for (int i = 0; i < 8; i++) {
            rows.add(new BoardRow());
        }
    }

    public void takeOff(int row1, int col1, int row2, int col2, Figure figure) {
        boolean nonBorderLocation = rangeBeforeTakeOff.contains(row2) && rangeBeforeTakeOff.contains(col2) ? true : false;
        boolean isPositionEmpty = getFigure(rowAfterTakeOff, colAfterTakeOff) instanceof None ? true : false;

        if (moveIsCorrect && nonBorderLocation) {
            if (row1 < row2 && col1 < col2) {
                // both axis increases
                rowAfterTakeOff = row2 + 1;
                colAfterTakeOff = col2 + 1;
                if (isPositionEmpty) {
                    setFigure(row1, col1, new None());
                    if (isMultiTakeOffAvailable(rowAfterTakeOff, colAfterTakeOff, figure)) {
                        moveFinished = false;
                    }
                } else {
                    setFigure(row1, col1, figure);
                    System.out.println("multi takeOff unavailable");
                    moveFinished = true;
                }
                moveFinished = true;
            }
            if (row1 > row2 && col1 > col2) {
                // both axis decreases
                rowAfterTakeOff = row2 - 1;
                colAfterTakeOff = col2 - 1;
                if (isPositionEmpty) {
                    setFigure(row1, col1, new None());
                    if (isMultiTakeOffAvailable(rowAfterTakeOff, colAfterTakeOff, figure)) {
                        moveFinished = false;
                    }
                } else {
                    setFigure(row1, col1, figure);
                    System.out.println("multi takeOff unavailable");
                    moveFinished = true;
                }
                moveFinished = true;
            }
            if (row1 > row2 && col1 < col2) {
                //row decreases and col increases
                rowAfterTakeOff = row2 - 1;
                colAfterTakeOff = col2 + 1;
                if (isPositionEmpty) {
                    setFigure(row1, col1, new None());
                    if (isMultiTakeOffAvailable(rowAfterTakeOff, colAfterTakeOff, figure)) {
                        moveFinished = false;
                    }
                } else {
                    setFigure(row1, col1, figure);
                    System.out.println("multi takeOff unavailable");
                    moveFinished = true;
                }
                moveFinished = true;
            }
            if (row1 < row2 && col1 > col2) {
                //row increases and col decreases
                rowAfterTakeOff = row2 + 1;
                colAfterTakeOff = col2 - 1;
                if (isPositionEmpty) {
                    setFigure(row1, col1, new None());
                    if (isMultiTakeOffAvailable(rowAfterTakeOff, colAfterTakeOff, figure)) {
                        moveFinished = false;
                    }
                } else {
                    setFigure(row1, col1, figure);
                    System.out.println("multi takeOff unavailable");
                    moveFinished = true;
                }
                moveFinished = true;
            }
        }
        if (getFigure(rowAfterTakeOff, colAfterTakeOff) instanceof None) {
            setFigure(rowAfterTakeOff, colAfterTakeOff, figure);
            setFigure(row2, col2, new None());
            setFigure(row1, col1, new None());
        }if (isMultiTakeOffAvailable(colAfterTakeOff,rowAfterTakeOff,figure)){
            moveFinished = false;
        }


//        if (!moveIsCorrect) {
//            System.out.println("INCORRECT MOVE");
//        }
    }




    public boolean isMultiTakeOffAvailable(int currentCol, int currentRow, Figure figure) {
        int rowAfterAnotherTakeOff = 0;
        int colAfterAnotherTakeOff = 0;
//        boolean isPositionEmpty = getFigure(rowAfterAnotherTakeOff,colAfterAnotherTakeOff) instanceof None || null ? true : false;

        //if (moveIsCorrect) {

        if (getFigure(currentCol + 1, currentRow + 1).getColor() != figure.getColor()) {
            System.out.println("Multi takeOff Available!");
            return true;
        } else if (getFigure(currentCol - 1, currentRow - 1).getColor() != figure.getColor()) {
            System.out.println("Multi takeOff Available!");
            return true;
        } else if (getFigure(currentCol + 1, currentRow - 1).getColor() != figure.getColor()) {
            System.out.println("Multi takeOff Available!");
            return true;
        } else if (getFigure(currentCol - 1, currentRow + 1).getColor() != figure.getColor()) {
            System.out.println("Multi takeOff Available!");
            return true;
        } else {
            return false;
        }


    }

//    public boolean isMultiTakeOffAvailable(int currentCol, int currentRow, Figure figure) {
//
//        //if (moveIsCorrect) {
//
//            if (getFigure(currentCol + 1, currentRow + 1).getColor() != figure.getColor()) {
//                System.out.println("Multi takeOff Available!");
//                return true;
//            } else if (getFigure(currentCol - 1, currentRow - 1).getColor() != figure.getColor()) {
//                System.out.println("Multi takeOff Available!");
//                return true;
//            } else if (getFigure(currentCol + 1, currentRow - 1).getColor() != figure.getColor()) {
//                System.out.println("Multi takeOff Available!");
//                return true;
//            } else if (getFigure(currentCol - 1, currentRow + 1).getColor() != figure.getColor()) {
//                System.out.println("Multi takeOff Available!");
//                return true;
//            } else {
//                return false;
//            }
//
//
//    }

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
            moveFinished = true;
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

}
