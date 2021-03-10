package pl.maciejcieslik.checkers.logic;

public class Queen extends Figure {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        String s = null;
        if (getColor() == Color.WHITE) {
            s = "Wq";
        } else {
            s = "Bq";
        }
        return s;
    }

}
