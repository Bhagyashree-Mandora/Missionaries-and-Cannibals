package main.usu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of Missionaries on left bank: ");
        String nMissionaries = scanner.next();
        System.out.print("Enter number of Cannibals on left bank: ");
        String nCannibals = scanner.next();

        Game game = new Game();
        game.play(Integer.valueOf(nMissionaries), Integer.valueOf(nCannibals));

        System.out.println("Game has ended!");
    }
}
