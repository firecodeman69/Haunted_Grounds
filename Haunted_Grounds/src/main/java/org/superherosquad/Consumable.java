/**************** ReAnn ********************/
package org.superherosquad;

public class Consumable extends Item {
    protected int healthGain;

    public Consumable(int Id, String name, String description, String type, String command, int effect, int price, int[] roomAssociations, int healthGain)
    {
        super(Id, name, description, type, command, effect, price, roomAssociations);
        this.healthGain = healthGain;
    }

    public int getHealthGain() {
        return healthGain;
    }

  /*  // TODO: Complete the method.
    public void useConsumable()
    {

    }*/
    
}