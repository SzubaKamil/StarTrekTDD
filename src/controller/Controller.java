package controller;

import galaxy.space.Galaxy;

import java.util.Scanner;

public class Controller {

    private Galaxy galaxy;

    public Controller() {
        galaxy = new Galaxy();
    }

    public void moveEnterprise(String vector){
        String[] temp = vector.split(",");

        try {
            int column = Integer.parseInt(temp[0]);
            int row = Integer.parseInt(temp[1]);

            if (!galaxy.moveEnterpriseInGalaxy(column, row)){
                System.out.println("Wektor jest poza zasięgiem galaktyki");
                return;
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e){
            System.out.println("BŁĄD wprowadzenia wektora!");
        }
    }

    public void shortScan (){
        galaxy.shortScan();
    }

    public void longScan(){
        galaxy.longScan();
    }

    public void map (){
        galaxy.print();
    }

    public void fight(){
        System.out.println("ENERGIA: " + galaxy.getEnterprise().getEnergy());
        System.out.println("Ile energi chesz przeznaczyć na ostrzał: ");
        int energy = new Scanner(System.in).nextInt();
        if (energy == 0){
            return;
        }
        galaxy.attackKlingdon(energy);
    }

    public void printEnterpriseStatus (){
        galaxy.getEnterprise().status();
    }

    public boolean isOver (){
        return galaxy.isGameOver();
    }

    public boolean isWin (){
        return galaxy.isGameWin();
    }
}
