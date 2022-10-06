package galaxy.space;

import galaxy.spaceObject.Enterprise;
import galaxy.spaceObject.Position;
import galaxy.spaceObject.SpaceObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quadrant extends SpacePrinter implements Space {

    private SpaceObject [][] sectors;
    private String scanInfo;
    private List<SpaceObject> spaceObjects;

    public Quadrant() {
        this.scanInfo = "***";
        createNew();
        spaceObjects = new ArrayList<>();
    }

    public void setScanInfo(String scanInfo) {
        this.scanInfo = scanInfo;
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    @Override
    public void createNew() {
        this.sectors = new SpaceObject[8][8];

        for (int i= 0 ; i < sectors.length; i ++){
            for (int j = 0; j < sectors[i].length; j ++){
                sectors[i][j] = new SpaceObject();
            }
        }
    }

    @Override
    public void print() {
        updateSectors();

        printUpLabel();
        for (int i = sectors.length -1; i > -1; i --){
            SpaceObject[] row = new SpaceObject[8];
            System.arraycopy(sectors[i], 0, row, 0, sectors[i].length);
            printRow(row, i);
        }
        printBottomLabel();
    }

    public void addSpaceObject (SpaceObject spaceObject){
        this.spaceObjects.add(spaceObject);
    }

    private void updateSectors(){
        Random random = new Random();
        Position newPosition;

        for (SpaceObject spaceObject: spaceObjects){
            newPosition = new Position(random.nextInt(8), random.nextInt(8));
            if (spaceObject.getClass().equals(Enterprise.class)){
                continue;
            }
            clearSector(spaceObject.getPosition().getColumn(), spaceObject.getPosition().getRow());
            spaceObject.setPosition(newPosition);
        }

        for (int i = 0 ; i < spaceObjects.size(); i ++){
            SpaceObject tempSpaceObject = spaceObjects.get(i);
            sectors[tempSpaceObject.getPosition().getRow()] [tempSpaceObject.getPosition().getColumn()] = tempSpaceObject;
        }
    }

    public void clearSector(int column, int row) {
        sectors[row][column] = new SpaceObject();
    }

    public boolean isSectorEmpty(Position position){
        int column = position.getColumn();
        int row = position.getRow();
        SpaceObject spaceObject = sectors[row][column];
        if (spaceObject.getView().equals(".") ){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return scanInfo;
    }
}
