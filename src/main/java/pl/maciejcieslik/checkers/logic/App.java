package pl.maciejcieslik.checkers.logic;

public class App {

    public static void main(String[] args) {
        Board board = new Board();
        board.createNewBoard();

        board.setFigure(1, 1, new Queen(Color.BLACK));
        System.out.println(board);
        board.setFigure(2, 2, new Pawn(Color.WHITE));
        System.out.println(board);

        board.move(2,2,4,4);
        System.out.println(board);

        board.moveForPawn(4,4,5,5);
        System.out.println(board);

        board.setFigure(1,1,new Pawn(Color.BLACK));

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(board);
        board.move(1,1,2,2);
        System.out.println(board);
        board.setFigure(3,3,new Queen(Color.WHITE));
        System.out.println(board);
        board.move(3,3,6,6);
        System.out.println(board);

    }
}
