package test.spaceObject;

import galaxy.spaceObject.Enterprise;
import galaxy.spaceObject.Klingon;
import galaxy.spaceObject.Position;
import galaxy.spaceObject.SpaceObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class KlingonTest {

    private Klingon klingon;

    @org.junit.Before
    public void setup (){
        klingon = new Klingon(new Position(35,35));
    }

    @Test
    public void attack() {
        Enterprise enterprise = new Enterprise(new Position(34,34));
        assertFalse(klingon.attack(enterprise));
    }

    @Test
    public void decreaseEnergy() {
        assertFalse(klingon.decreaseEnergy(50));
        assertTrue(klingon.decreaseEnergy(150));
        assertTrue(klingon.decreaseEnergy(200));
    }
}