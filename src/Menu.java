import controller.Controller;

import java.util.Scanner;

public class Menu {

    private Controller controller;

    public Menu() {
        this.controller = new Controller();
    }

    public void menu (){
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        while (flag){
            controller.printEnterpriseStatus();
            printMenu();
            switch (scanner.nextLine()){
                case "0":
                    System.out.println("VECTOR ? ");
                    String vector = scanner.nextLine();
                    controller.moveEnterprise(vector);
                    break;
                case "1":
                    controller.shortScan();
                    break;
                case "2":
                    controller.longScan();
                    break;
                case "3":
                    controller.fight();
                    break;
                case "4":
                    controller.map();
                    break;
                case "Q":
                    flag = false;
                    break;
                default:
                    System.out.println("Nie ma takiej opcji!");
                    break;
            }
            if (controller.isOver()){
                System.out.println("KONIEC GRY");
                flag = false;
            }
            if (controller.isWin()){
                System.out.println("GRATULACJE WYGRAŁEŚ GRĘ");
                flag = false;
            }
        }
    }

    private void printMenu (){
        System.out.println("Dostępne opcje:" +
                "\n0\t Przemieść statek Enterprise" +
                "\n1\t Skan bliskiego zasięgu" +
                "\n2\t Skan dalekiego zasięgu" +
                "\n3\t Tryb walki" +
                "\n4\t Mapa galaktyki" +
                "\nQ\t Wyjście");
    }
}
