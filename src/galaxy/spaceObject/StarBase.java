package galaxy.spaceObject;

public class StarBase extends SpaceObject {

    public StarBase(Position position) {
        super(position);
        this.view = ">!<";
    }

    public void fillUp (Enterprise enterprise){
        System.out.println("Flota gwiezdna uzupełniła zapasy Enterprise!");
        enterprise.fillUpEnergyAndStar();
    }

}
