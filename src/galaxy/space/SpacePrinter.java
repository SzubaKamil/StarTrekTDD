package galaxy.space;

public class SpacePrinter {

    protected void printUpLabel(){
        System.out.printf("%1$-7s %2$-3d %3$-3d %4$-3d %5$-3d %6$-3d %7$-3d %8$-3d %9$-3d", "", 0, 1, 2, 3, 4, 5, 6, 7);
        System.out.println("\n     -----------------------------------");
    }

    protected void printBottomLabel (){
        System.out.println("     -----------------------------------");
        System.out.printf("%1$-7s %2$-3d %3$-3d %4$-3d %5$-3d %6$-3d %7$-3d %8$-3d %9$-3d", "", 0, 1, 2, 3, 4, 5, 6, 7);
    }

    protected void printRow (Object[] row, int i){
        System.out.printf("%1$-3d %2$-3s %3$-3s %4$-3s %5$-3s %6$-3s %7$-3s %8$-3s %9$-3s %10$-3s %11$-3s %12$-3d",
                i, "|", row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], "|", i);
        System.out.println();
    }

}
