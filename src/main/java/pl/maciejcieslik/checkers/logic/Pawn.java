package pl.maciejcieslik.checkers.logic;

public class Pawn extends Figure {

    public Pawn(Color color) {
        super(color);
    }
    @Override
    public String toString() {
        String s = null;
        if(getColor() == Color.WHITE){
            s = "Pw";
        } else {
            s = "Pb";
        }
        return s;
    }

}
