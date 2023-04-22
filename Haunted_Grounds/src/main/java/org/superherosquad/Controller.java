/**************** ReAnn ********************/
package org.superherosquad;

import java.util.Scanner;

public class Controller {
    private Scanner input;
    //private Model model;
    //private View view;

    public void gamePlay()
    {
        input = new Scanner(System.in);
        System.out.println("Welcome to the Haunted Grounds game!");

        // TODO: Implement game mechanics and logic here

        input.close();
        System.out.println("Thanks for playing!");
    }
}
