/**************** Cody ********************/
package org.superherosquad;

public class Monster extends Character {
    private int monsterLocation;
    //private ArrayList<Item> monsterInventory;
    public Monster(int Id, String name, int hp, int currency, String description,
                   int speed, int defense, int attack,
                   //ArrayList<Item> monsterInventory,
                   int monsterLocation) {
        super(Id, name, hp, currency, description, speed, defense, attack);
        //this.monsterInventory = monsterInventory;
        this.monsterLocation = monsterLocation;
    }

    public int getMonsterLocation() {
        return monsterLocation;
    }

    //todo: implement this attack method.
    //todo: determine what needs to happen when monster attacks
    public void monsterAttack() {

    }

    //todo: determine what should happen upon monster defeat
    public void defeatMonster() {

    }

//    public ArrayList<Item> dropItems() {
//        return monsterInventory;
//    }

    public int dropCurrency() {
        return this.currency;
    }
}
