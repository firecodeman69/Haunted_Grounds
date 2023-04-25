/***********************Cody************************/
package org.superherosquad;

import java.util.ArrayList;
import java.util.Scanner;

public class Combat {
    private View view = new View();

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
                    view.combatWithMonster(); //print the prompt for when a user is in combat
                    String playerInput = input.nextLine().toLowerCase();
                    String[] tokens = playerInput.split(" ");
                    switch (tokens[0]) {
                        case "attack", "a" -> {
                            monster.loseHP(p.getAttack()); //if player attacks, deal damage to monster
                            view.playerAttacked(monster, p);
                            if (monster.getHP() <= 0) slaying = false;
                            playerTurn = false;
                        }
                        case "defend", "d" -> {
                            defending = true;
                            view.playerDefending(); //print user is defending
                            playerTurn = false;
                        }
                        case "use", "u" -> {
                            if (tokens.length == 2) {
                                if (p.getItem(tokens[1]) != null) {
                                    p.useConsumableItem(p.getItem(tokens[1])); //add item effect to player's health
                                }
                            } else if (tokens.length == 3){
                                if (p.getItem(tokens[1] + " " + tokens[2]) != null) {
                                    p.useConsumableItem(p.getItem(tokens[1] + " " + tokens[2])); //add item effect to player's health
                                }
                            } else if (tokens.length == 4){
                                if (p.getItem(tokens[1] + " " + tokens[2] + " " + tokens[3]) != null) {
                                    p.useConsumableItem(p.getItem(tokens[1] + " " + tokens[2] + " " + tokens[3])); //add item effect to player's health
                                }
                            }
                            playerTurn = false;
                        }
                        case "run", "r" -> {
                            p.setRunChance(monster);
                            view.printRunSuccess(p); //print the runSuccess prompt
                            if (p.runSuccess()) {
                                return prevMode;
                            }
                            else {
                                playerTurn = false;
                            }
                        }
                        case "item", "i" -> itemMenu(p.getPlayerInventory(), input);
                        default -> view.print("That command, or item cannot be used in combat. Please enter a valid command.");
                    }
                } else {
                    if (defending) { //if player defends, deal half of monster attack
                        p.loseHP(monster.getAttack() / 2);
                        view.monsterAttackedDefending(monster, p);
                        playerTurn = true;
                        defending = false;
                    } else { //monster attacks
                        p.loseHP(monster.getAttack());
                        view.monsterAttacked(monster, p);
                        playerTurn = true;
                    }
                }
            }
            if (!p.isAlive()) { //if player is dead
                view.playerDefeat();
                return p.playerDeath();
            } else { //when monster is defeated
                view.monsterDefeat(monster, p);
                p.addMonsterItemToInventory(monster.dropRandomItem());
                p.addCurrency(monster.getCurrency());
                p.getCurrentRoom().removeMonster();
                
                if(monster.getId() == 5) { //if monster was the boss monster, end the game.
                	view.endGame();
                	System.exit(0);
                }
                return prevMode;
            }
        } else {
            return prevMode;
        }
    }

    public void itemMenu(ArrayList<Item> playerInventory, Scanner input) { //handle the item menu logic
            boolean itemMenuOpen = true;
            view.combatInventoryMenuPrompt();
            while (itemMenuOpen) {
                view.combatInventory(playerInventory);
                String playerInput = input.nextLine().toLowerCase();
                String[] tokens = playerInput.split(" ");
                if (tokens[0].equalsIgnoreCase("exit")) {
                    itemMenuOpen = false;
                }
            }
    }

    public int initialCombat(Monster monster, Scanner input, Player p) { //Handle the initial combat logic
        int playerTurn = -1;
        view.encounterMonster(monster);
        String playerInput = input.nextLine().toLowerCase();
        String[] tokens = playerInput.split(" ");
        switch (tokens[0]) {
            case "attack", "a" -> {
                view.initAttackOption(p);
                playerTurn = 0;
            }
            case "ignore", "i" -> {
                view.initIgnoreOption(monster);
                playerTurn = 1;
            }
            case "run", "r" -> {
                p.exitRoom();
                view.initRunOption();
            }
        }
        return playerTurn;
    }
}
