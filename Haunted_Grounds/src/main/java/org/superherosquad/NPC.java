//Cobi
package org.superherosquad;

import java.io.Serializable;

public class NPC implements Serializable
{
	private static final long serialVersionUID = 2424396446293707167L;

	private View view = new View();
	
	private int id;
	private String name;
	private String description;
	private Puzzle puzzle;
	private String options;
	private boolean shopAccess;
	
	public NPC(int i, String n, String d, Puzzle p, String o, boolean a)
	{
		this.id = i;
		this.name = n;
		this.description = d;
		this.puzzle = p;
		this.options = o;
		this.shopAccess = a;
	}

	public int getId() {
		return id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public Puzzle getPuzzle()
	{
		return this.puzzle;
	}
	
	public String getOptions()
	{
		return this.options;
	}
	
	public boolean hasShopAccess()
	{
		return this.shopAccess;
	}
	
	public int activatePuzzle(int currentMode)
	{
		if(puzzle.getId() == -1) {
			view.print("You've already answered my riddle.");
		}
		else {
			view.print("The NPC has given you the " + puzzle + ". Here is the prompt:");
			puzzle.activate();
			currentMode = 2;
		}
		return currentMode;
	}
	
	public void removePuzzle() {
		puzzle.setId(-1);
	}
	
	public int enterShop(Shop shop)
	{
		if(shopAccess) {
			view.print("Welcome to the shop! What would you like to buy?");
			shop.list();
			return 4;
		}
		else {
			view.print("This NPC does not have access to the shop.");
			return 3;
		}
	}
	
	public int leave()
	{
		view.print("Thanks for chatting!");
		return 0;
	}

	@Override
	public String toString() {
		return name;
	}
}
