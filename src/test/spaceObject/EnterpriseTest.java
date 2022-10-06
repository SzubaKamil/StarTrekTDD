package test.spaceObject;

import galaxy.spaceObject.Enterprise;
import galaxy.spaceObject.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnterpriseTest {

    private Enterprise enterprise;
    private Position position;

    @org.junit.Before
    public void setup (){
        position = new Position(35,35);
        enterprise = new Enterprise(position);
        enterprise.setGalaxyPosition(position);
    }

    @Test
    public void getEnergy() {
        assertEquals(enterprise.getEnergy(), 600);
    }

    @Test
    public void getPosition() {
        assertEquals(enterprise.getPosition(), position);
    }

    @Test
    public void setPosition_getPosition() {
        Position position = new Position(0,0);
        enterprise.setPosition(position);
        assertEquals(position, enterprise.getPosition());
    }

    @Test
    public void getStar() {
        assertEquals(enterprise.getStar(), 30);
    }

    @Test
    public void getGalaxyPosition_setGalaxyPosition() {
        Position position = new Position(50,50);
        enterprise.setGalaxyPosition(position);
        assertEquals(position, enterprise.getGalaxyPosition());
    }

    @Test
    public void fillUpEnergyAndStar() {
        enterprise.decreaseEnergy(300);
        enterprise.updatePosition(20,20);
        System.out.println("Star: " + enterprise.getStar() + " Energy: " + enterprise.getEnergy());
        enterprise.fillUpEnergyAndStar();
        assertEquals(enterprise.getEnergy(), 600);
        assertEquals(enterprise.getStar(), 30);
    }

    @Test
    public void updatePosition() {
        assertFalse(enterprise.updatePosition(100, 100));

        assertFalse(enterprise.updatePosition(-100, -100));

        assertFalse(enterprise.updatePosition(-36, -36));
        enterprise.fillUpEnergyAndStar();

        assertTrue(enterprise.updatePosition(-35,-35));
        enterprise.setGalaxyPosition(new Position(35,35));


        assertTrue(enterprise.updatePosition(0,-35));
        enterprise.setGalaxyPosition(new Position(35,35));
        enterprise.fillUpEnergyAndStar();

        assertTrue(enterprise.updatePosition(28,28));
        enterprise.setGalaxyPosition(new Position(35,35));
        enterprise.fillUpEnergyAndStar();

        assertTrue(enterprise.updatePosition(0,28));
        enterprise.setGalaxyPosition(new Position(35,35));

        assertTrue(enterprise.updatePosition(28,0));
    }
}