package pl.maciejcieslik.checkers.logic;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {

    private List<Figure> columns = new ArrayList<>();

    public List<Figure> BoardRow() {
        for (int i = 0; i < 8; i++){
            columns.add(new None());
        }
        return columns;
    }

    public List getColumn() {
        return columns;
    }
}
