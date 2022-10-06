package galaxy.spaceObject;

public class SpaceObject {

    private Position position;
    protected String view;

    public SpaceObject(Position position) {
        this.position = position;
    }

    public SpaceObject() {
        this.view = ".";
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getView() {
        return view;
    }

    @Override
    public String toString() {
        return view;
    }
}
