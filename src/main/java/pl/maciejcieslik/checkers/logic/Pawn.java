package pl.maciejcieslik.checkers.logic;

import javax.swing.text.html.ImageView;

public class Pawn extends Figure {

    public Pawn(Color color) {
        super(color);
    }

//    public ImageView getImage(ImageView img){
//        return img;
//    }

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
