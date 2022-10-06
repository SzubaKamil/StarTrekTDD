package test.spaceObject;

import galaxy.spaceObject.Klingon;
import galaxy.spaceObject.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    private Position position;

    @org.junit.Before
    public void setup (){
        position = new Position(20,20);
    }

    @Test
    public void getRow() {
        assertEquals(position.getRow(), 20);
    }

    @Test
    public void setRow_set() {
        int row = 50;
        position.setRow(row);
        assertEquals(row, position.getRow());
    }

    @Test
    public void getColumn() {
        assertEquals(position.getColumn(), 20);
    }

    @Test
    public void setColumn() {
        int column = 50;
        position.setColumn(column);
        assertEquals(column, position.getColumn());
    }
}