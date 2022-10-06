package galaxy.spaceObject;

public class Enterprise extends SpaceShip{

    private int star;
    private Position galaxyPosition;

    public Enterprise(Position position) {
        super(position);
        this.star = 30;
        this.energy = 600;
        this.view = "<*>";
    }

    public int getStar() {
        return star;
    }

    public Position getGalaxyPosition() {
        return galaxyPosition;
    }

    public void setGalaxyPosition(Position galaxyPosition) {
        this.galaxyPosition = galaxyPosition;
    }

    public void fillUpEnergyAndStar (){
        this.energy = 600;
        this.star = 30;
    }

    public void status (){
        System.out.println("\n-------------Enterprise Status--------------------\n" +
                            "Energia: " + energy + "\tPozycja: " +
                            galaxyPosition.getColumn() + "," +
                            galaxyPosition.getRow() +"\tGwiezdne daty: " + star +
                            "\n--------------------------------------------------\n");
    }

    public boolean updatePosition(int column, int row) {
        int newColumn = galaxyPosition.getColumn() + column;
        int newRow = galaxyPosition.getRow() + row;

        // czy po przesunięciu enterprise będzie znajdowal sie w galaktyce
        if (newColumn >= 0 && newColumn < 64 && newRow >= 0 && newRow <64 ){

            int energyCost = Math.abs( (newColumn + newRow) - (galaxyPosition.getColumn() + galaxyPosition.getRow() ));
            int starCost = Math.abs( ( (galaxyPosition.getColumn()/ 8) + (galaxyPosition.getRow()/8) ) -
                                        ( (newColumn /8) + (newRow / 8)) );

            if ( (this.energy - energyCost) < 0  || this.star - starCost < 0 ) {
                System.out.println("Brak wystarczającej ilości energi lub gwiazd do wykonania tego przemieszczenia");
            }
            this.energy -= energyCost;
            this.star -= starCost;

            galaxyPosition.setColumn(newColumn);
            galaxyPosition.setRow(newRow);

            newColumn = (galaxyPosition.getColumn() % 8) - 1;
            if (newColumn == -1){
                newColumn = 7;
            }
            newRow = (galaxyPosition.getRow() % 8 ) - 1;
            if (newRow == -1){
                newRow =7;
            }
            setPosition(new Position(newColumn, newRow));
            return true;
        }
        return false;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
