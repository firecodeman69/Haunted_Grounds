/***********************Cody************************/
package org.superherosquad;

import java.util.ArrayList;
import java.util.Scanner;

public class Combat {
    private View view = new View();
    private String blue = "\u001B[34m";
    private String red = "\u001B[31m";
    private String reset = "\u001B[0m";
    private String orange = "\u001B[38;2;255;165;0m";
    private String green =  "\u001B[32m";
    private String darkGreen = "\u001B[32";

    public int combatLoop(Player p, Scanner input, int prevMode) {
        boolean playerTurn = true;
        boolean defending = false;
        Monster monster = p.getCurrentRoom().getRoomMonster();
        int decision = this.initialCombat(monster, input, p); // if 0, player turn first. if 1, monster turn first, if -1, escape combat, change mode to prevMode
        boolean slaying = true;

        if (decision == 0 || decision == 1) { //didn't run from monster
            if (decision == 1) playerTurn = false; //ignored the monster
            while (slaying) {
                if (playerTurn) {
                    view.print(blue + "What would you like to do?" + reset + "\n(A)ttack, (D)efend, (U)se {item name}, (R)un\n" +
                            "Use '(I)tem' to open your inventory menu.");
                    String playerInput = input.nextLine().toLowerCase();
                    String[] tokens = playerInput.split(" ");
                    switch (tokens[0]) {
                        case "attack", "a" -> {
                            monster.loseHP(p.getAttack()); //if player attacks, deal damage to monster
                            view.print("You hit the monster for " + p.getAttack() + "! "
                                    + "Monster has " + red + monster.getHP() + "hp left." + reset);
                            if (monster.getHP() <= 0) slaying = false;
                            playerTurn = false;
                        }
                        case "defend", "d" -> {
                            defending = true;
                            view.print("You are defending.");
                            playerTurn = false;
                        }
                        case "use", "u" -> {
                            if (tokens.length == 2) {
                                if (p.hasItem(tokens[1])) {
                                    p.useConsumableItem(tokens[1]); //add item effect to player's health
                                }
                            } else if (tokens.length == 3){
                                if (p.hasItem(tokens[1] + " " + tokens[2])) {
                                    p.useConsumableItem(tokens[1] + " " + tokens[2]); //add item effect to player's health
                                }
                            } else if (tokens.length == 4){
                                if (p.hasItem(tokens[1] + " " + tokens[2] + " " + tokens[3])) {
                                    p.useConsumableItem(tokens[1] + " " + tokens[2] + " " + tokens[3]); //add item effect to player's health
                                }
                            }
                            playerTurn = false;
                        }
                        case "run", "r" -> {
                            p.setRunChance(monster);
                            view.print("Player run percentage is " + p.getRunChance() + "%\nRun away successfully? " + p.runSuccess() + "\n"); // fancy way to print a boolean in printf
                            if (p.runSuccess()) {
                                return prevMode;
                            }
                            else {
                                playerTurn = false;
                            }
                        }
                        case "item", "i" -> itemMenu(p.getPlayerInventory(), input);
                    }
                } else {
                    if (defending) {
                        p.loseHP(monster.getAttack() / 2); //if player defends, deal half of monster attack
                        view.print("Monster attacked and hit you for " + (monster.getAttack() / 2) + ". "
                                + green + "Remaining HP: " + p.getHP() + reset);
                        playerTurn = true;
                    } else {
                        p.loseHP(monster.getAttack());
                        view.print("Monster attacked and hit you for " + (monster.getAttack()) + ". "
                                + green + "Remaining HP: " + p.getHP() + reset);
                        playerTurn = true;
                    }
                }
            }
            if (!p.isAlive()) {
                view.print(red + "You have been defeated in battle. Regroup and try again!" + reset);
                return p.playerDeath();
            } else {
                view.print("You successfully defeated " + monster.getName() + "! " +
                        green + "You have earned " + monster.getCurrency() + reset + " claw bucks and gained items: " + blue
                         + monster.getMonsterInventory() + reset);
                p.addItemsToInventory(monster.getMonsterInventory());
                p.addCurrency(monster.getCurrency());
                p.getCurrentRoom().removeMonster();
                return prevMode;
            }
        } else {
            return prevMode;
        }
    }

    public void itemMenu(ArrayList<Item> playerInventory, Scanner input) {
            boolean itemMenuOpen = true;
            view.print("Inventory Menu\nUse 'Exit' to leave this menu any time.");
            while (itemMenuOpen) {
                view.print(playerInventory.toString());
                String playerInput = input.nextLine().toLowerCase();
                String[] tokens = playerInput.split(" ");
                if (tokens[0].equalsIgnoreCase("exit")) {
                    itemMenuOpen = false;
                }
            }
    }

    public int initialCombat(Monster monster, Scanner input, Player p) {
        int playerTurn = -1;
        view.print("You see " + orange + monster.getName() + reset + " in the room with you.\n"
                + blue + "What would you like to do?" + reset + "\n(A)ttack, (I)gnore, (R)un");
        String playerInput = input.nextLine().toLowerCase();
        String[] tokens = playerInput.split(" ");
        switch (tokens[0]) {
            case "attack", "a" -> {
                view.print("Good luck " + orange + p.getName() + reset + " may you be successful in your combat.");
                playerTurn = 0;
            }
            case "ignore", "i" -> {
                view.print(orange + monster.getName() + reset + " Attacked you! Can't leave them on read so easily.\nStarting combat!");
                playerTurn = 1;
            }
            case "run", "r" -> {
                p.exitRoom();
                view.print("Run to the previous room! Whew, that was close!");
            }
        }
        return playerTurn;
    }
}
