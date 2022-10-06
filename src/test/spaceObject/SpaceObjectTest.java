package test.spaceObject;

import galaxy.spaceObject.Position;
import galaxy.spaceObject.SpaceObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceObjectTest {

    private SpaceObject spaceObject;

    @org.junit.Before
    public void setup (){
        spaceObject = new SpaceObject();
    }

    @Test
    public void setPosition_getPosition() {
        Position position = new Position(10,10);
        spaceObject.setPosition(position);
        assertEquals(position, spaceObject.getPosition());
    }

    @Test
    public void testToString_getView() {
        assertEquals(spaceObject.toString(), spaceObject.getView());
    }
}