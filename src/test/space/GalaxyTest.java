package test.space;

import galaxy.space.Galaxy;
import galaxy.spaceObject.Enterprise;
import galaxy.spaceObject.Klingon;
import galaxy.spaceObject.Position;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GalaxyTest {

    private Galaxy galaxy;

    @org.junit.Before
    public void setup (){
        galaxy = new Galaxy();
    }

    @Test
    public void moveEnterpriseInGalaxy() {
    }

    @Test
    public void attackKlingdon() {
        Klingon klingon = new Klingon(new Position(1,1));
        galaxy.attackKlingdon(800);
    }

    @Test
    public void isGameOver() {
        Enterprise fakeEnterprise = new Enterprise(new Position(1,1));
        fakeEnterprise.setPowerAttack(300);
        fakeEnterprise.attack(galaxy.getEnterprise());
        fakeEnterprise.fillUpEnergyAndStar();
        fakeEnterprise.attack(galaxy.getEnterprise());
        assertTrue(galaxy.isGameOver());

        galaxy.getEnterprise().setStar(0);
        assertTrue(galaxy.isGameOver());
    }

    @Test
    public void isGameWin() {
        galaxy.setKlingons(new ArrayList<>());
        assertTrue(galaxy.isGameWin());
    }
}