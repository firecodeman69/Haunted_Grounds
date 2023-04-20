/**************** ReAnn ********************/
package org.superherosquad;

public class Equipment extends Item{
    protected int statsGain;
    protected boolean isEquipped;

    public Equipment(int Id, String name, String description, int price, int statsGain, boolean isEquipped)
    {
        super(Id, name, description, price);
        this.statsGain = statsGain;
        this.isEquipped = isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }

    public int getStatsGain() {
        return statsGain;
    }

  /*  // TODO: Complete the method.
    public void equipItem()
    {

    }*/
}