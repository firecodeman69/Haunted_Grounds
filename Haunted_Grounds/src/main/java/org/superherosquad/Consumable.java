/**************** ReAnn ********************/
package org.superherosquad;

public class Consumable extends Item {
    protected int healthGain;

    public Consumable(int Id, String name, String description, int price, int healthGain)
    {
        super(Id, name, description, price);
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
