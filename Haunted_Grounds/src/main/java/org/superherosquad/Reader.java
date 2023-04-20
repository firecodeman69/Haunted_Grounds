package org.superherosquad;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Reader {
    Scanner sc;

//    public void newRoom() {
//        Room room;
//
//        ArrayList<Room> roomAL = new ArrayList<>();
//        try {
//            sc = new Scanner(new File("map.txt"));
//            while (sc.hasNextLine()) {
//                for (int i = 0; i < 7; i++) {
//                    room = new Room(sc.nextInt() //RoomID
//                            , sc.next() //Room Name/Desc
//                            , sc.nextBoolean() //Has been visited
//                            , sc.nextBoolean() //Can have Item
//                            , sc.nextBoolean() //Can have Puzzle
//                            , sc.nextInt() //North RoomId
//                            , sc.nextInt() //East RoomId
//                            , sc.nextInt() //South RoomId
//                            , sc.nextInt()); //West RoomId
//                    roomAL.add(room);
//                }
//            }//end while
//            sc.close();
//        }//end try
//        catch (IOException ioe) {
//            ioe.printStackTrace();
//            System.out.println("IOException!" +
//                    "No file exists! " +
//                    "Please make sure that the file exists and try again.");
//        } catch (NoSuchElementException ignored) {
//        }
//        this.gameRooms = roomAL;
//    }
//
//    /************************************Item file reader*****************************/
//
//    public void newItem() {
//        Item item;
//        ArrayList<Item> allItems = new ArrayList<>();
//        try {
//            sc = new Scanner(new File("items.txt"));
//            sc.nextLine(); // skip the first line
//            for (int i = 0; i < 4; i++) {
//                {
//                    item = new Item(sc.nextLine() //Item name
//                            , sc.nextLine()); //Item description
//
//                    allItems.add(item);
//                }
//            }//end while
//            sc.close();
//        }//end try
//        catch (IOException ioe) {
//            ioe.printStackTrace();
//            System.out.println("IOException!" +
//                    "No file exists! " +
//                    "Please make sure that the file exists and try again.");
//        } catch (NoSuchElementException ignored) {
//        }
//        this.allItems = allItems;
//    }
//
//    /************************************Puzzle file reader (Cobi)*****************************/
	public ArrayList<Puzzle> newPuzzle() {
		Puzzle puzzle;
		ArrayList<Puzzle> puzzleAL = new ArrayList<>();
		try {
			sc = new Scanner(new File("Puzzles.txt"));

			while (sc.hasNextLine()) {
				String puzzleInfo = sc.nextLine();
				// System.out.println("Current line is: " + puzzleInfo);
				String[] puzzleTokens = puzzleInfo.split("#");
				String[] puzzleLines = puzzleTokens[2].split(",");
				String text = puzzleLines[0];
				if (puzzleLines.length > 1) {
					for (int i = 1; i < puzzleLines.length; i++) {
						text.concat("\n" + puzzleLines[i]);
					}
				}
				puzzle = new Puzzle(Integer.parseInt(puzzleTokens[0]), puzzleTokens[1], puzzleTokens[2],
						puzzleTokens[3], puzzleTokens[4], puzzleTokens[5], puzzleTokens[6], puzzleTokens[7]);
				puzzleAL.add(puzzle);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println(
					"IOException!" + "No puzzle file exists! " + "Please make sure that the file exists and try again.");
		} catch (NoSuchElementException ignored) {
		}
		return puzzleAL;
	}

    /************************************Monster file reader (Cody)*****************************/
    public ArrayList<Monster> newMonster() {
        Monster monster;
        ArrayList<Monster> monsters = new ArrayList<>();
        try {
            sc = new Scanner(new File("monster.txt"));

            while (sc.hasNextLine()) {
                String monsterInfo = sc.nextLine();
                //System.out.println("Current line is (you fool): " + monsterInfo);
                String[] monsterTokens = monsterInfo.split("#");
                String[] roomAssociationTokens = monsterTokens[8].split(",");
                int[] roomAssociations = new int[roomAssociationTokens.length];
                for (int i = 0; i < roomAssociations.length; i++) {
                    roomAssociations[i] = Integer.parseInt(roomAssociationTokens[i]);
                }
                    monster = new Monster(Integer.parseInt(monsterTokens[0]) //MonsterID
                            , monsterTokens[1] //Name
                            , Integer.parseInt(monsterTokens[2]) //HP
                            , Integer.parseInt(monsterTokens[3]) //Currency amount
                            , monsterTokens[4] //Description
                            , Integer.parseInt(monsterTokens[5]) //Speed
                    , Integer.parseInt(monsterTokens[6]) //Defense
                    , Integer.parseInt(monsterTokens[7]) //Attack
                    , roomAssociations); //room associations
                monsters.add(monster);
            }//end while
            sc.close();
            //System.out.println(monsters);
        }//end try
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("IOException!" +
                    "No monster file exists! " +
                    "Please make sure that the file exists and try again.");
        } catch (NoSuchElementException nse) {
            nse.printStackTrace();
        }
        return monsters;
    }
    
    /************************************NPC file reader (Cobi)*****************************/
    public ArrayList<NPC> newNPC(ArrayList<Puzzle> puzzles) {
    	NPC npc;
    	ArrayList<NPC> npcs = new ArrayList<>();
    	try {
    		sc = new Scanner(new File("NPCs.txt"));
    		
    		while(sc.hasNextLine()) {
    			String npcInfo = sc.nextLine();
    			// System.out.println("Current line is: " + npcInfo);
    			String[] npcTokens = npcInfo.split("#");
    			Puzzle npcPuzzle = null;
    			for(Puzzle p: puzzles)
    			{
    				if(Integer.parseInt(npcTokens[3]) == p.getId())
    				{
    					npcPuzzle = p;
    				}
    			}
    			npc = new NPC(Integer.parseInt(npcTokens[0])
    				  , npcTokens[1]
    			      , npcTokens[2]
    			      , npcPuzzle
    			      , Boolean.parseBoolean(npcTokens[4]));
    			npcs.add(npc);
    		}
    	}
    	catch (IOException ioe){
            System.out.println("IOException!" +
                    "No NPC file exists! " +
                    "Please make sure that the file exists and try again.");
    	}
    	return npcs;
    }
}
