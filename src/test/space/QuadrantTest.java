package test.space;

import galaxy.space.Quadrant;
import galaxy.spaceObject.Enterprise;
import galaxy.spaceObject.Position;
import galaxy.spaceObject.Star;
import galaxy.spaceObject.StarBase;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuadrantTest {

    private Quadrant quadrant;

    @org.junit.Before
    public void setup (){
        quadrant = new Quadrant();
    }

    @Test
    public void setScanInfo() {
        String scanInfo = "111";
        quadrant.setScanInfo(scanInfo);
        assertEquals(quadrant.toString(), scanInfo);
    }

    @Test
    public void getSpaceObjects() {
        assertEquals(quadrant.getSpaceObjects().size(), 0);
    }

    @Test
    public void addSpaceObject() {
        Star star = new Star(new Position(10,0));
        quadrant.addSpaceObject(star);
        assertEquals(quadrant.getSpaceObjects().get(0), star );
    }

    @Test
    public void isSectorEmpty() {
        Position position = new Position(2,5);
        StarBase starBase = new StarBase(position);
        quadrant.addSpaceObject(starBase);
        assertFalse(quadrant.isSectorEmpty(position));
        assertFalse(quadrant.isSectorEmpty(new Position(3,3)));
    }

    @Test
    public void testToString() {
        assertEquals(quadrant.toString(), "***");
    }
}