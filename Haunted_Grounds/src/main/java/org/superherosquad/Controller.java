/**************** ReAnn ********************/
package org.superherosquad;

import java.util.Scanner;

public class Controller {
    private Scanner input;
    //private Game game = new Game();
    //private Model model;
    //private View view;

    public static void main(String[] args) {
        Controller c1 = new Controller();
        c1.gamePlay();
    }

    public void gamePlay()
    {
        input = new Scanner(System.in);
        String playerInput;
        System.out.println("Welcome to the Haunted Grounds game!");

        while (true) {
            System.out.println("Please enter a command.");
            playerInput = input.nextLine().toLowerCase();
            String[] commands = playerInput.split(" ");
            System.out.println("You entered: " + playerInput);

            // TODO: Implement game mechanics and logic here
            //Main menu
            switch (commands[0]) {
                case "quit":
                    System.out.println("Thanks for playing!");
                    System.exit(0);
                    break;
                case "new":
                    System.out.println("Starting a New Game!");
                    //newGame logic
                    break;
                case "load":
                    System.out.println("Loading game!");
                    //newGame logic
                    break;
            }
        }




    }
}
