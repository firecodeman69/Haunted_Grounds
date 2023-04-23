package org.superherosquad;

import java.util.Scanner;

public class Combat {
    View view = new View();

    public int combatLoop(Player p, Scanner input, int prevMode) {
        boolean playerTurn = true;
        boolean defending = false;
        Monster monster = p.getCurrentRoom().getRoomMonster();
        int decision = p.initialCombat(monster, input); // if 0, player turn first. if 1, monster turn first, if -1, escape combat, change mode to prevMode

        if (decision == 0 || decision == 1) { //didn't run from monster
            if (decision == 1) playerTurn = false; //ignored the monster
            while (monster.isAlive() && p.isAlive()) {
                if (playerTurn) {
                    view.print("What would you like to do?\n(A)ttack, (D)efend, Use {item name}, (R)un");
                    String playerInput = input.nextLine().toLowerCase();
                    String[] tokens = playerInput.split(" ");
                    switch (tokens[0]) {
                        case "attack", "a" -> {
                            monster.loseHP(p.getAttack()); //if player attacks, deal damage to monster
                            view.print("You hit the monster for " + p.getAttack() + "! " +
                                    "Monster has " + monster.getHP() + "hp left.");
                            playerTurn = false;
                        }
                        case "defend", "d" -> {
                            defending = true;
                            view.print("You are defending.");
                            playerTurn = false;
                        }
                        case "use" -> {
                            if (tokens.length < 2) {
                                if (p.hasItem(tokens[1])) {
                                    p.useConsumableItem(tokens[1]); //add item effect to player's health
                                }
                            } else {
                                if (p.hasItem(tokens[1] + " " + tokens[2])) {
                                    p.useConsumableItem(tokens[1]); //add item effect to player's health
                                }
                            }
                            playerTurn = false;
                        }
                        case "run", "r" -> {
                            p.setRunChance(monster);
                            view.print("Player run percentage is " + p.getRunChance() + "\nRun away successfully? " + p.runSuccess(monster));
                            playerTurn = false;
                        }
                    }
                } else {
                    if (defending) {
                        p.loseHP(monster.getAttack() / 2); //if player defends, deal half of monster attack
                        view.print("Monster attacked and hit you for " + (monster.getAttack() / 2) + ". " +
                                "Remaining HP: " + p.getHP());
                        playerTurn = true;
                    } else {
                        p.loseHP(monster.getAttack());
                        view.print("Monster attacked and hit you for " + (monster.getAttack()) + ". " +
                                "Remaining HP: " + p.getHP());
                        playerTurn = true;
                    }
                }
            }
            if (!p.isAlive()) {
                view.print("You have been defeated in battle. Regroup and try again!");
                return prevMode;
            } else {
                view.print("You successfully defeated " + monster.getName() + "! " +
                        "You have earned " + monster.getCurrency() + " and gained items: " +
                        monster.getMonsterInventory());
                p.addItemsToInventory(monster.getMonsterInventory());
                p.addCurrency(monster.getCurrency());
                p.currentRoom.removeMonster();
                return prevMode;
            }
        } else {
            return prevMode;
        }
    }
}
