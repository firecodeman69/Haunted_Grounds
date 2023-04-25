/**************** Cody ********************/
package org.superherosquad;

import java.io.Serializable;
import java.util.ArrayList;

public class Monster extends Character implements Serializable {
    private int[] monsterItemAssociations;
    private ArrayList<Item> monsterInventory = new ArrayList<>();

    public Monster(int Id, String name, int hp, int currency, String description,
                   int speed, int defense, int attack,
                   //ArrayList<Item> monsterInventory,
                   int[] monsterItemAssociations) {
        super(Id, name, hp, currency, description, speed, defense, attack);
        this.monsterItemAssociations = monsterItemAssociations;
        //this.monsterInventory = new ArrayList<>();
        //this.monsterLocations = monsterLocations;
    }


//    public int[] getMonsterLocation() {
//        return monsterLocations;
//    }

    //TODO: determine what should happen upon monster defeat
//    public void defeatMonster() {
//        dropItems();
//        dropCurrency();
//    }

    public ArrayList<Item> dropItems() {
        return monsterInventory;
    }

    public void addItems(Item item) {
        monsterInventory.add(item);
    }

    public int dropCurrency() {
        return currency;
    }

    public int[] getMonsterItemAssociations() {
        return monsterItemAssociations;
    }

    public ArrayList<Item> getMonsterInventory() {
        return monsterInventory;
    }

    public String toString() {
        return super.toString();
        // " [Monster Locations: " + monsterLocations.toString() + "]";
    }
}
