package galaxy.space;

import galaxy.spaceObject.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Galaxy extends SpacePrinter implements Space {

    private Quadrant[][] quadrants;
    private List<SpaceObject> klingons;
    private Enterprise enterprise;
    private boolean gameOver;

    public Galaxy() {
        createNew();
        klingons = newKlingons();
        enterprise = newEnterprise();
        addSpaceObjectToQuadrant(klingons);
        newStarBases();
        newStars();
        gameOver = false;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    @Override
    public void createNew() {
        this.quadrants = new Quadrant[8][8];

        for (int i= 0 ; i < quadrants.length; i ++){
            for (int j = 0; j < quadrants[i].length; j ++){
                quadrants[i][j] = new Quadrant();
            }
        }
    }

    @Override
    public void print() {
        printUpLabel();

        for (int i = quadrants.length -1; i > -1; i --){
            Quadrant[] row = new Quadrant[8];
            System.arraycopy(quadrants[i], 0, row, 0, quadrants[i].length);
            printRow(row, i);
        }
        printBottomLabel();
    }

    private List<SpaceObject> newKlingons (){
        List<SpaceObject> klingons = new ArrayList<>();

        for (int i = 0; i < 7; i ++ ){
            klingons.add(new Klingon(generatePosition()));
        }
        return klingons;
    }

    private void newStarBases (){
        List<SpaceObject> starBases = new ArrayList<>();
        starBases.add(new StarBase(generatePosition()));
        starBases.add(new StarBase(generatePosition()));

        addSpaceObjectToQuadrant(starBases);
    }

    private void newStars (){
        List<SpaceObject> stars = new ArrayList<>();
        for (int i =0; i < 20; i ++){
            stars.add(new Star(generatePosition()));
        }
        addSpaceObjectToQuadrant(stars);
    }

    private Enterprise newEnterprise (){
        Enterprise enterprise = new Enterprise(new Position(0,0));
        quadrants[4][4].addSpaceObject(enterprise);
        enterprise.setGalaxyPosition(new Position(33, 33));
        return enterprise;
    }

    private Position generatePosition(){
        Random random = new Random();
        return new Position(random.nextInt(8), random.nextInt(8));
    }

    private void addSpaceObjectToQuadrant(List<SpaceObject> spaceObjects){
        Position position = generatePosition();
        for (int i = 0; i < spaceObjects.size(); i ++){
            position = generatePosition();
            SpaceObject spaceObject = spaceObjects.get(i);
            quadrants[position.getColumn()] [position.getRow()].addSpaceObject(spaceObject);
        }
    }

    public boolean moveEnterpriseInGalaxy (int column, int row){
        int oldColumn = enterprise.getPosition().getColumn();
        int oldRow = enterprise.getPosition().getRow();

        int oldColumnGalaxy = enterprise.getGalaxyPosition().getColumn() / 8;
        int oldRowGalaxy = enterprise.getGalaxyPosition().getRow() / 8;

        if (enterprise.updatePosition(column, row)){
            int newColumnGalaxy = enterprise.getGalaxyPosition().getColumn() / 8;
            int newRowGalaxy = enterprise.getGalaxyPosition().getRow() / 8;
            // remove from old quadrant list
            quadrants[oldRowGalaxy][oldColumnGalaxy].getSpaceObjects().remove(enterprise);
            // clear sector
            quadrants[oldRowGalaxy][oldColumnGalaxy].clearSector(oldColumn, oldRow);
            // is sector empty
            gameOver = quadrants[newRowGalaxy][newColumnGalaxy].isSectorEmpty(enterprise.getPosition());
            if (gameOver){
                System.out.println("KOSMICZNA KATASTROFA - zniszczenie statku ENTERPRISE");
            }
            // add to new quadrant
            quadrants[newRowGalaxy][newColumnGalaxy].getSpaceObjects().add(enterprise);

            // check is it klingdon in quadrant

            List<SpaceObject> spaceObjects = quadrants[newRowGalaxy][newColumnGalaxy].getSpaceObjects();

            Position currentPosition = enterprise.getPosition();

            for (SpaceObject spaceObject : spaceObjects) {

                if (spaceObject.getClass().equals(Klingon.class)) {

                    System.out.println("Atak Klingdonu");
                    ((Klingon) spaceObject).attack(enterprise);
                }

                if (spaceObject.getClass().equals(StarBase.class)){
                    if ( (spaceObject.getPosition().getColumn() == currentPosition.getColumn()) &&
                            (spaceObject.getPosition().getRow() == currentPosition.getRow()+1 ||
                             spaceObject.getPosition().getRow() == currentPosition.getRow()-1  )){
                        ((StarBase) spaceObject).fillUp(enterprise);
                    }
                    if ( (spaceObject.getPosition().getRow() == currentPosition.getRow()) &&
                            (spaceObject.getPosition().getColumn() == currentPosition.getColumn() +1 ||
                             spaceObject.getPosition().getColumn() == currentPosition.getColumn() -1)  ){
                        ((StarBase) spaceObject).fillUp(enterprise);
                    }
                }

            }
            return true;
        }
        return false;
    }

    public void shortScan (){
        Position position = enterprise.getGalaxyPosition();

        int row = enterprise.getGalaxyPosition().getRow() / 8;
        int column = enterprise.getGalaxyPosition().getColumn() / 8;

        quadrants[row][column].print();
    }

    public void longScan (){
        int klingons = 0;
        int starBases = 0;
        int stars = 0;
        String scanInfo;

        int row = enterprise.getGalaxyPosition().getRow() / 8;
        if (row == 0){
            row = 1;
        } else if (row == 7 ){
            row = 6;
        }

        int column = enterprise.getGalaxyPosition().getColumn() / 8;

        if (column == 0){
            column = 1;
        } else if (column == 7 ){
            column = 6;
        }

        System.out.printf("%1$-4s %2$-3s %3$-3s %4$-3s", "", column-1, column, column+1);
        System.out.println();
        for (int i = row +1; i >= row -1; i-- ){
            System.out.print(i + " |\t");

            for (int j = column-1; j <= column +1; j ++){
                List<SpaceObject> spaceObjects = this.quadrants[i][j].getSpaceObjects();

                for (SpaceObject spaceObject: spaceObjects){
                    if (spaceObject.getClass().equals(Klingon.class)){
                        klingons ++;
                    }
                    if (spaceObject.getClass().equals(StarBase.class)){
                        starBases ++;
                    }
                    if (spaceObject.getClass().equals(Star.class)){
                        stars ++;
                    }
                }
                scanInfo = klingons + "" + starBases + "" + stars;
                System.out.print(scanInfo + "\t");
                this.quadrants[i][j].setScanInfo(scanInfo);
                klingons = 0;
                starBases = 0;
                stars = 0;
            }
            System.out.print(" | " + i +"\n");
        }
        System.out.printf("%1$-4s %2$-3s %3$-3s %4$-3s", "", column-1, column, column+1);
    }

    public void attackKlingdon (int energy){
        int row = enterprise.getGalaxyPosition().getRow() / 8;
        int column = enterprise.getGalaxyPosition().getColumn() / 8;

        if (energy > enterprise.getEnergy()){
            System.out.println("Masz za mało energii");
        } else {

            List<SpaceObject> spaceObjects = quadrants[row][column].getSpaceObjects();

            if (spaceObjects != null){
                for (SpaceObject spaceObject : spaceObjects) {

                    if (spaceObject.getClass().equals(Klingon.class)) {
                        enterprise.setPowerAttack(energy);
                        boolean isDestroy = enterprise.attack((SpaceShip) spaceObject);
                        if (isDestroy){
                            System.out.println("Klingdon został zniszczony");
                            klingons.remove(spaceObject);
                            quadrants[row][column].clearSector(spaceObject.getPosition().getColumn(), spaceObject.getPosition().getRow());
                            spaceObjects.remove(spaceObject);
                        } else {
                            ((Klingon) spaceObject).attack(enterprise);
                        }
                    }
                }
            }
        }
    }

    public boolean isGameOver() {
        if (enterprise.getEnergy() <=0){
            System.out.println("ENTERPRISE NIE MA ENERGI!!!");
            return true;
        }
        if (enterprise.getStar() <=0 ){
            int row = enterprise.getGalaxyPosition().getRow() / 8;
            int column = enterprise.getGalaxyPosition().getColumn() / 8;
            int klingdonInQuadrant = 0;
            List<SpaceObject> spaceObjects = quadrants[row][column].getSpaceObjects();
            if (spaceObjects != null){
                for (SpaceObject spaceObject : spaceObjects) {
                    if (spaceObject.getClass().equals(Klingon.class)){
                        klingdonInQuadrant ++;
                    }
                }
            }
            if (klingons.size() > klingdonInQuadrant){
                System.out.println("BRAK GWIEZDNYCH DAT. " + (klingons.size()-klingdonInQuadrant) +
                        " Klingdon znajduje się poza Twoim kwadrantem");
                return true;
            }
        }
        return gameOver;
    }

    public boolean isGameWin(){
        return klingons.size() == 0;
    }

    public void setKlingons(List<SpaceObject> klingons) {
        this.klingons = klingons;
    }
}
