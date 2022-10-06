package galaxy.spaceObject;

import java.util.Random;

public class SpaceShip extends SpaceObject{

    protected int energy;
    private int powerAttack = 50;

    public SpaceShip(Position position) {
        super(position);
    }

    public int getEnergy() {
        return energy;
    }

    public void setPowerAttack(int powerAttack) {
        this.powerAttack = powerAttack;
    }

    public boolean attack (SpaceShip spaceShip){
        double metricCity = Math.abs( (getPosition().getColumn() + getPosition().getRow() ) -
                                    (spaceShip.getPosition().getColumn() + spaceShip.getPosition().getRow()) );
        double hitProbability =  (5 / (metricCity + 4)) * 100;

        decreaseEnergy(powerAttack);
        if (new Random().nextInt(100) < hitProbability){
            System.out.println("Atak udany");
            return spaceShip.decreaseEnergy(powerAttack); // obiekt zniszczony
        } else {
            System.out.println("Atak chybiony");
        }
        return false;
    }

    public boolean decreaseEnergy (int energy){
        if (this.energy - energy <= 0 ){
            this.energy = 0;
            return true; //  obiekt zniszczony
        } else {
            this.energy -= energy;
            return false;
        }
    }
}
