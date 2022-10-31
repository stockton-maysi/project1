import java.util.Random;

/**
 * The main environment in which the game takes place. Each dungeon floor has a total
 * of 32 cells, one of which is the Player, one is the Exit, and the rest are
 * randomly populated with a variety of Enemies and Items, some of which are weighted
 * more heavily than others.
 */
public class Dungeon {
	private Cell head;
	private int length;
	
	private int floor;
	private boolean floorComplete;
	private Player player;
	
	private Random random = new Random();
	
	/**
	 * Constructs a new Dungeon floor at a certain difficulty level
	 * @param p The Player character who will be placed inside
	 * @param f The floor you'll be on, which determines the difficulty of the
	 * Enemies that spawn
	 */
	public Dungeon(Player p, int f) {
		length = 0;
		floor = f;
		floorComplete = false;
		player = p;
		
		add(new Cell(p));
		
		for (int i = 0; i < 15; i++) {
			add(new Cell(new Empty()));
		}
		
		add(new Cell(new Exit()));
		
		for (int i = 0; i < 15; i++) {
			add(new Cell(new Empty()));
		}
		
		PronounSet it = new PronounSet("it", "it", "its");
		
		Enemy goblin = new Enemy("Goblin", it, 30*floor, 10*floor, 50*floor, 100*floor, 10*floor);
		Enemy troll = new Enemy("Troll", it, 25*floor, 15*floor, 20*floor, 100*floor, 10*floor);
		Enemy dragon = new Enemy("Dragon", it, 50*floor, 0, 50*floor, 150*floor, 15*floor);
		Equipment sword = new Equipment("Sword", "Weapon", 30, 0, 0, 0);
		Equipment battleaxe = new Equipment("Battleaxe", "Weapon", 40, 0, -20, 0);
		Equipment ballSpikeThingy = new Equipment("Ball Spike Thingy", "Weapon", 40, 10, -40, 0);
		Equipment leatherArmor = new Equipment("Leather Armor", "Armor", 0, 10, -5, 0);
		Equipment chainmailArmor = new Equipment("Chainmail Armor", "Armor", 0, 25, -10, 0);
		Equipment cactusArmor = new Equipment("Cactus Armor", "Armor", 10, 10, 0, 0);
		Equipment runningShoes = new Equipment("Running Shoes", "Boots", 0, 0, 50, 0);
		Equipment spikyBoots = new Equipment("Spiky Boots", "Boots", 20, 0, -10, 0);
		Equipment ringOfFirepower = new Equipment("Ring of Firepower", "Ring", 20, 0, 0, 0);
		Equipment ringOfProtection = new Equipment("Ring of Protection", "Ring", 0, 10, 0, 0);
		Equipment ringOfSwiftness = new Equipment("Ring of Swiftness", "Ring", 0, 0, 50, 0);
		Equipment ringOfLife = new Equipment("Ring of Life", "Ring", 0, 0, 0, 200);
		Consumable smallPotion = new Consumable("Small Health Potion", 100);
		Consumable mediumPotion = new Consumable("Medium Health Potion", 200);
		Consumable largePotion = new Consumable("Large Health Potion", 400);
		
		//Many of these are listed multiple times to make them more common
		Copyable[] randomEncounters = {
			goblin, goblin, goblin, goblin, goblin, goblin, goblin, goblin, goblin,
			troll, troll, troll, troll, troll, troll, troll,
			dragon, dragon, dragon, dragon,
			
			sword, sword, battleaxe, ballSpikeThingy,
			leatherArmor, leatherArmor, chainmailArmor, cactusArmor,
			runningShoes, runningShoes, spikyBoots, spikyBoots,
			ringOfFirepower, ringOfProtection, ringOfSwiftness, ringOfLife,
			smallPotion, smallPotion, mediumPotion, largePotion
		};
		
		//Populate the floor with 10 random Encounters, making sure not to overwrite
		//Encounters that are already there
		for (int i = 0; i < 10; i++) {
			Encounter randomEncounter = (Encounter) randomEncounters[random.nextInt(randomEncounters.length)].copy();
			
			Cell randomCell;
			do {
				randomCell = get(random.nextInt(length));
			} while (!(randomCell.getData() instanceof Empty));
			
			randomCell.setData(randomEncounter);
		}
	}
	
	/**
	 * Gets a Cell at a given position in the Dungeon.
	 * <p>
	 * Doesn't really work for negative numbers; the % operator treats -a % b as
	 * being equal to -(a % b)
	 * 
	 * @param n The position in the Dungeon; 0 is the head, with + going to the right
	 * @return The Cell at the given position
	 */
	public Cell get(int n) {
		Cell currentCell = head;
		
		for (int i = 0; i < n % length; i++) {
			currentCell = currentCell.getNext();
		}
		
		return currentCell;
	}
	
	/**
	 * Adds a cell just to the left of the head
	 * @param cell The Cell to add
	 */
	public void add(Cell cell) {
		if (length == 0) {
			head = cell;
			cell.setPrevious(cell);
			cell.setNext(cell);
		} else {
			head.getPrevious().setNext(cell);
			cell.setPrevious(head.getPrevious());
			head.setPrevious(cell);
			cell.setNext(head);
		}
		
		length++;
	}
	
	/**
	 * Removes the first "null" Cell from the Dungeon, by updating its neighbors
	 * accordingly
	 */
	public void removeNull() {
		if (head.getData() == null) {
			head.getPrevious().setNext(head.getNext());
			head.getNext().setPrevious(head.getPrevious());
			
			if (length != 0) {
				head = head.getNext();
			} else {
				head = null;
			}
			
			length--;
		} else {
			int checkedSoFar = 1;
			Cell currentCell = head.getNext();
			
			boolean foundNull = false;
			while (!foundNull && checkedSoFar < length) {
				if (currentCell.getData() == null) {
					foundNull = true;
				} else {
					currentCell = currentCell.getNext();
					checkedSoFar++;
				}
			}
			
			if (foundNull) {
				currentCell.getPrevious().setNext(currentCell.getNext());
				currentCell.getNext().setPrevious(currentCell.getPrevious());
				
				length--;
			}
		}
	}
	
	/**
	 * Locates the Player within the Dungeon
	 * @return The Cell containing the Player
	 */
	public Cell findPlayer() {
		Cell currentCell = head;
		int checkedSoFar = 0;
		while (checkedSoFar < length) {
			if (currentCell.getData() == player) {
				return currentCell;
			} else {
				currentCell = currentCell.getNext();
				checkedSoFar++;
			}
		}
		
		return null;
	}
	
	/**
	 * Moves the Player to a (presumably adjacent) Cell.
	 * <p>
	 * If the Player moves on top of an Enemy, they will engage in a fight to the
	 * death. If it's an Item, the game will ask if the Player wants to pick it up.
	 * Otherwise, it will be put back in that Cell when the Player moves away. If
	 * it's an Exit, the Dungeon floor will be marked complete. 
	 * <p>
	 * Why do we need to know the previousCell, you ask? Because if the Player tries
	 * to run in the opposite direction from an Enemy that's next to them, they will
	 * get attacked exactly once
	 * 
	 * @param previousCell The Cell opposite to the nextCell with respect to
	 * currentCell
	 * @param currentCell The Cell the Player is presumed to currently be in
	 * @param nextCell The Cell to move the Player to
	 */
	public void movePlayer(Cell previousCell, Cell currentCell, Cell nextCell) {
		Encounter previousCellData = previousCell.getData();
		Encounter nextCellData = nextCell.getData();
		
		if (previousCellData instanceof Enemy) {
			((Enemy) previousCellData).attack(player, false);
		}
		
		if (nextCellData instanceof Enemy) {
			Enemy enemy = (Enemy) nextCellData;
			
			if (player.getSpeed() > enemy.getSpeed()) {
				player.attack(enemy, true);
			} else if (player.getSpeed() < enemy.getSpeed()) {
				enemy.attack(player, true);
			} else if (random.nextBoolean()) {
				player.attack(enemy, true);
			} else {
				enemy.attack(player, true);
			}
		} else if (nextCellData instanceof Item) {
			Item item = (Item) nextCellData;
			System.out.println(item.toString());
			
			MainGameOption yes = new MainGameOption("yes");
			MainGameOption no = new MainGameOption("no");
			MainGameOption[] yesOrNo = {yes, no};
			MCInput yesOrNoInput = new MCInput("Would you like to equip it?", yesOrNo, false);
			Named choice = yesOrNoInput.getInput();
			
			if (choice == yes) {
				player.equip(item);
			} else {
				player.setDroppedItem(item);
			}
			
			nextCell.setData(new Empty());
		} else if (nextCellData instanceof Exit) {
			floorComplete = true;
		}
		
		Item droppedItem = player.getDroppedItem();
		if (droppedItem == null) {
			if (previousCellData instanceof Enemy) {
				currentCell.setData(new Empty());
			} else {
				currentCell.setData(null);
				removeNull();
			}
		} else {
			currentCell.setData(droppedItem);
			player.setDroppedItem(null);
		}
		
		nextCell.setData(player);
	}
	
	/**
	 * Moves the Player to the Cell immediately to their left
	 */
	public void moveLeft() {
		Cell currentCell = findPlayer();
		Cell leftCell = currentCell.getPrevious();
		Cell rightCell = currentCell.getNext();
		
		movePlayer(rightCell, currentCell, leftCell);
	}
	
	/**
	 * Moves the Player to the Cell immediately to their right
	 */
	public void moveRight() {
		Cell currentCell = findPlayer();
		Cell leftCell = currentCell.getPrevious();
		Cell rightCell = currentCell.getNext();
		
		movePlayer(leftCell, currentCell, rightCell);
	}
	
	/**
	 * Plays the Dungeon floor, asking the Player to input commands until they either
	 * die or complete the floor; in the latter case, the next floor will then be
	 * generated and played
	 */
	public void play() {
		System.out.println("Welcome to floor " + floor + ".");
		
		MainGameOption left = new MainGameOption("left");
		MainGameOption right = new MainGameOption("right");
		MainGameOption use = new MainGameOption("use");
		MainGameOption drop = new MainGameOption("drop");
		MainGameOption stats = new MainGameOption("stats");
		MainGameOption[] options = {left, right, use, drop, stats};
		MCInput mainInput = new MCInput("What would you like to do next?", options, false);
		
		while (player.isAlive() && !floorComplete) {
			System.out.println(toString());
			Named choice = mainInput.getInput();
			
			if (choice == left) {
				moveLeft();
			} else if (choice == right) {
				moveRight();
			} else if (choice == use) {
				player.useConsumable();
			} else if (choice == drop) {
				MCInput itemInput = new MCInput("What would you like to drop?", player.getInventory(), false);
				player.drop((ItemSlot) itemInput.getInput());
			} else if (choice == stats) {
				System.out.println(player.toString());
			}
		}
		
		if (!player.isAlive()) {
			System.out.println("GAME OVER.");
			System.out.println(player.getName() + " made it to floor " + floor + " before dying.");
		}
		
		if (floorComplete) {
			System.out.println("Floor " + floor + " complete!");
			(new Dungeon(player, floor+1)).play();
		}
	}
	
	@Override
	public String toString() {
		String s = "...";
		
		Cell currentCell;
		Cell playerCell = findPlayer();
		if (playerCell != null) {
			currentCell = playerCell;
		} else {
			currentCell = head;
		}
		
		for (int i = 0; i < length/2 + 1; i++) {
			currentCell = currentCell.getNext();
		}
		
		for (int i = 0; i < length; i++) {
			s += currentCell.toString();
			currentCell = currentCell.getNext();
		}
		
		s += "...";
		return s;
	}
}