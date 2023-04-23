//Cobi
package org.superherosquad;

public class NPC 
{
	private int id;
	private String name;
	private String description;
	private Puzzle puzzle;
	private boolean shopAccess;
	private boolean active = false;
	
	public NPC(int i, String n, String d, Puzzle p, boolean a)
	{
		this.id = i;
		this.name = n;
		this.description = d;
		this.puzzle = p;
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
	
	public boolean hasShopAccess()
	{
		return this.shopAccess;
	}
	
	public void activate()
	{
		active = true;
	}
	
	public void talk()
	{
		
	}

	//todo: change this to be how you want - if you want it changed.
	@Override
	public String toString() {
		return "[Id: " + id + "] " +
				"[Name: " + name + "] " +
				"[Description: " + description + "] " +
				"[Puzzle: " + puzzle.getName() + "] " +
				"[ShopAccess: " + shopAccess + "] ";
	}
}
