// Cobi
package org.superherosquad;

import java.util.ArrayList;

public class Shop 
{
	View view = new View();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Shop() {}
	
	public Shop(ArrayList<Item> i)
	{
		items = i;
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
	public void purchase(Player p, String desire, int quantity)
	{
		int q = quantity;
		boolean found = false;
		boolean broke = false;
		String name = null;
		if(quantity > 0) {
			for(Item i: items) {
				if(i.getName().equalsIgnoreCase(desire)); {
					found = true;
					if(quantity * i.getPrice() > p.getCurrency()) {
						view.print("You do not have enough money for this transaction.");
						broke = true;
					} else {
						name = i.getName();
						while(quantity > 0) {
							p.silentAdder(i);
							p.spendCurrency(i.getPrice());
							quantity -= 1;
						}
					}
				}
				break;
			}
			if(found && !broke) {
				if(q > 1) {
					view.print("Added " + q + " " + name + "s to your inventory.");
				} else {
					view.print("Added " + q + " " + name + " to your inventory.");
				}
			} else {
				view.print("The item you have attempted to purchase is not in the shop.");
			}
		} else {
			view.print("You cannot purchase a number of items less than 1. That would be silly.");
		}
	}
	
	public void list()
	{
		for(Item i: items) {
			view.shopPrint(i);
		}
	}
	
	public int leave()
	{
		view.print("Thanks for shopping!");
		return 3;
	}
}
