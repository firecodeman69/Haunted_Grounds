//Cobi
package org.superherosquad;

public class NPC 
{
	private int id;
	private String name;
	private String description;
	private Puzzle puzzle;
	private boolean shopAccess;
	
	public NPC(int i, String n, String d, Puzzle p, boolean a)
	{
		this.id = i;
		this.name = n;
		this.description = d;
		this.puzzle = p;
		this.shopAccess = a;
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
	
	public void talk()
	{
		
	}
}
