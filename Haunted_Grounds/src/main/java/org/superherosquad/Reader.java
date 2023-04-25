package org.superherosquad;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Reader {
    Scanner sc;

    /************************************Buffered room file reader (Cody)*****************************/
    
    public ArrayList<Room> newRoom() { //Create rooms for a new game
        Room room;
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("rooms.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("#"); //# delimited
                String[] associationTokens = tokens[3].split(","); //Split roomAssociations
                int[] associations = new int[associationTokens.length];
                for (int i = 0; i < associationTokens.length; i++) {
                    associations[i] = Integer.parseInt(associationTokens[i]); //Set int array of roomAssociations
                }

                room = new Room(Integer.parseInt(tokens[0]), //Id
                        tokens[1], //Name
                        tokens[2], //Description
                        associations[0], //northRoom
                        associations[1], //southRoom
                        associations[2], //eastRoom
                        associations[3], //westRoom
                        Boolean.parseBoolean(tokens[4]), //isVisited
                        Integer.parseInt(tokens[5]), //monsterId
                        Integer.parseInt(tokens[6]), //puzzleId
                        Integer.parseInt(tokens[7]), //itemId
                        Integer.parseInt(tokens[8]), //npcId
                		Boolean.parseBoolean(tokens[9])); //isDark
                rooms.add(room); //create new Room
            } //end while
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rooms;
    }


    /************************************Item file reader (ReAnn)*****************************/

    public ArrayList<Item> newItem() {
        Item item;
        ArrayList<Item> allItems = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("items.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("#"); //# delimited
                String[] associationTokens = tokens[7].split(","); //Split roomAssociations
                int[] roomAssociations = new int[associationTokens.length];
                for (int i = 0; i < associationTokens.length; i++) {
                    roomAssociations[i] = Integer.parseInt(associationTokens[i]); //Set int array of roomAssociations
                }

                item = new Item(Integer.parseInt(tokens[0]) // ID
                        , tokens[1] // Name
                        , tokens[2] // Description
                        , tokens[3] // Type
                        , tokens[4] // Command
                        , Integer.parseInt(tokens[5]) //Effect
                        , Integer.parseInt(tokens[6]) //Price
                        , roomAssociations); // Room Associations
                allItems.add(item);

            } //end while
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allItems;
    }

    /************************************Puzzle file reader (Cobi)*****************************/
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
				puzzle = new Puzzle(Integer.parseInt(puzzleTokens[0]), //Id
									puzzleTokens[1], //Name
									puzzleTokens[2], //Question
									puzzleTokens[3], //Solution
									puzzleTokens[4], //Hint
									Integer.parseInt(puzzleTokens[5]), //Reward
									puzzleTokens[6], //Description
									puzzleTokens[7], //Repsonse on correct answer
									puzzleTokens[8]); //Response on incorrect answer
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
                String[] itemAssociationTokens = monsterTokens[8].split(",");
                int[] itemAssociations = new int[itemAssociationTokens.length];
                for (int i = 0; i < itemAssociationTokens.length; i++) {
                    itemAssociations[i] = Integer.parseInt(itemAssociationTokens[i]);
                }
                    monster = new Monster(Integer.parseInt(monsterTokens[0]) //MonsterID
                            , monsterTokens[1] //Name
                            , Integer.parseInt(monsterTokens[2]) //HP
                            , Integer.parseInt(monsterTokens[3]) //Currency amount
                            , monsterTokens[4] //Description
                            , Integer.parseInt(monsterTokens[5]) //Speed
                    , Integer.parseInt(monsterTokens[6]) //Defense
                    , Integer.parseInt(monsterTokens[7]), //Attack
                            itemAssociations); //Item associations
                   // , roomAssociations); //room associations
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
    			npc = new NPC(Integer.parseInt(npcTokens[0]) //Id
    				  , npcTokens[1] //Name
    			      , npcTokens[2] //Description
    			      , npcPuzzle //Puzzle
    			      , npcTokens[4] //Greets (greeting)
    			      , Boolean.parseBoolean(npcTokens[5])); //Shop access
    			npcs.add(npc);
    		}
			sc.close();
    	}
    	catch (IOException ioe){
            System.out.println("IOException!" +
                    "No NPC file exists! " +
                    "Please make sure that the file exists and try again.");
    	}
    	return npcs;
    }


    /************************************Add things to rooms (Cody)*****************************/

    public void addMonstersToRoom(ArrayList<Room> gameRooms, ArrayList<Monster> gameMonsters) { //Cody - adds monsters to rooms
        for (Room r : gameRooms) {
            for (Monster m : gameMonsters) {
                if (r.getMonsterId() == m.getId()) {
                    r.setMonster(m);
                }
            }
        }
    }

    public void addPuzzleToRoom(ArrayList<Room> gameRooms, ArrayList<Puzzle> gamePuzzles) { //Cody - adds puzzles to rooms
        for (Room r : gameRooms) {
            for (Puzzle p : gamePuzzles) {
                if (r.getPuzzleId() == p.getId()) {
                    r.setPuzzle(p);
                }
            }
        }
    }

    public void addItemToRoom(ArrayList<Room> gameRooms, ArrayList<Item> gameItems) { //Cody - adds items to rooms
        for (Room r : gameRooms) {
            for (Item i: gameItems) {
                if (r.getItemId() == i.getId()) {
                    r.addItem(i);
                }
            }
        }
    }

    public void addItemToMonster(ArrayList<Monster> gameMonsters, ArrayList<Item> gameItems) { //Cody - adds items to rooms
        for (Monster m : gameMonsters) {
            for (Item item: gameItems) {
                for (int i = 0; i < m.getMonsterItemAssociations().length; i++) {
                    if (m.getMonsterItemAssociations()[i] == item.getId()) m.addItems(item);
                }
            }
        }
    }

    public void addNPCToRoom(ArrayList<Room> gameRooms, ArrayList<NPC> gameNPCs) { //Cody - adds puzzles to rooms
        for (Room r : gameRooms) {
            for (NPC npc : gameNPCs) {
                if (r.getNpcId() == npc.getId()) {
                    r.setNPC(npc);
                }
            }
        }
    }
    
	/************************************Scanner Room file reader (Cody)*****************************/
    /****This was giving errors with reading the file. Seems to be some special characters or encoding that Scanner doesn't like. This is only here just in case.****/
//    public ArrayList<Room> newRoom() {
//        Room room;
//        ArrayList<Room> rooms = new ArrayList<>();
//        try {
//            sc = new Scanner(new File("rooms.txt"));
//
//            while (sc.hasNextLine()) {
//                String roomInfo = sc.nextLine();
//                System.out.println("Current line is (you fool): " + roomInfo);
//                String[] roomTokens = roomInfo.split("#");
//                String[] roomAssociationTokens = roomTokens[3].split(",");
//                int[] roomAssociations = new int[roomAssociationTokens.length];
//                for (int i = 0; i < roomAssociations.length; i++) {
//                    roomAssociations[i] = Integer.parseInt(roomAssociationTokens[i]);
//                }
//                room = new Room(Integer.parseInt(roomTokens[0]) //RoomID
//                        , roomTokens[1] //Name
//                        , roomTokens[2] //Description
//                        , roomAssociations[0] //northRoom
//                        , roomAssociations[1] //southRoom
//                        , roomAssociations[2] //eastRoom
//                        , roomAssociations[3] //westRoom
//                        ,Boolean.parseBoolean(roomTokens[4])); //isVisited
//                rooms.add(room);
//            }//end while
//            sc.close();
//            //System.out.println(rooms);
//        }//end try
//        catch (IOException ioe) {
//            ioe.printStackTrace();
//            System.out.println("IOException!" +
//                    "No room file exists! " +
//                    "Please make sure that the file exists and try again.");
//        } catch (NoSuchElementException nse) {
//            nse.printStackTrace();
//        }
//        return rooms;
//    }
}
