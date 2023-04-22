/**************** Cody ********************/
package org.superherosquad;

import java.util.ArrayList;

public class Monster extends Character {
    //private int[] monsterLocations;
    private ArrayList<Item> monsterInventory;

    public Monster(int Id, String name, int hp, int currency, String description,
                   int speed, int defense, int attack
                   //ArrayList<Item> monsterInventory,
                   //int[] monsterLocations
    ) {
        super(Id, name, hp, currency, description, speed, defense, attack);
        //this.monsterInventory = monsterInventory;
        //this.monsterLocations = monsterLocations;
    }


//    public int[] getMonsterLocation() {
//        return monsterLocations;
//    }

    //todo: determine what should happen upon monster defeat
    public void defeatMonster() {

    }

    public ArrayList<Item> dropItems() {
        return monsterInventory;
    }

    public int dropCurrency() {
        return currency;
    }

    public String toString() {
        return super.toString();
        // " [Monster Locations: " + monsterLocations.toString() + "]";
    }
}
