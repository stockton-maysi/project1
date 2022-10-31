/**
 * The Player themselves. Has a PlayerClass, as well as a level-up mechanic and an
 * inventory consisting of ItemSlots.
 */
public class Player extends Character {
	private PlayerClass playerClass;
	private int level;
	private int experience;
	
	private ItemSlot[] inventory;
	private Item droppedItem;
	
	/**
	 * Constructs a new Player character
	 * @param s The Player's name
	 * @param pronouns The Player's pronouns
	 * @param pClass The Player's class
	 */
	public Player(String s, PronounSet pronouns, PlayerClass pClass) {
		super(s, pronouns, pClass.getAttack(), pClass.getDefense(), pClass.getSpeed(), pClass.getHealth());
		
		playerClass = pClass;
		level = 0;
		experience = 0;
		
		ItemSlot[] slots = {
			new EquipmentSlot("Weapon"),
			new EquipmentSlot("Armor"),
			new EquipmentSlot("Boots"),
			new EquipmentSlot("Ring"),
			new ConsumableSlot()
		};
		
		inventory = slots;
		droppedItem = null;
	}
	
	/**
	 * Returns the class
	 * @return The PlayerClass
	 */
	public PlayerClass getPlayerClass() {
		return playerClass;
	}
	
	/**
	 * Returns the Player's overall attack stat
	 * @return The attack stat, including any buffs granted by Equipment
	 */
	public int getAttack() {
		int n = super.getAttack();
		System.out.println(n);
		
		for (int i = 0; i < inventory.length; i++) {
			Item item = inventory[i].getItem();
			
			if (item != null) {
				
				n += item.getAttack();
				System.out.println(n);
			}
		}
		
		return n;
	}
	
	/**
	 * Returns the Player's overall defense stat
	 * @return The defense stat, including any buffs granted by Equipment
	 */
	public int getDefense() {
		int n = super.getDefense();
		
		for (int i = 0; i < inventory.length; i++) {
			Item item = inventory[i].getItem();
			
			if (item != null) {
				n += item.getDefense();
			}
		}
		
		return n;
	}
	
	/**
	 * Returns the Player's overall speed stat
	 * @return The speed stat, including any buffs granted by Equipment
	 */
	public int getSpeed() {
		int n = super.getSpeed();
		
		for (int i = 0; i < inventory.length; i++) {
			Item item = inventory[i].getItem();
			
			if (item != null) {
				n += item.getSpeed();
			}
		}
		
		return n;
	}
	
	/**
	 * Returns the Player's overall maximum health stat
	 * @return The maximum health stat, including any buffs granted by Equipment
	 */
	public int getMaxHealth() {
		int n = super.getMaxHealth();
		
		for (int i = 0; i < inventory.length; i++) {
			Item item = inventory[i].getItem();
			
			if (item != null) {
				n += item.getMaxHealth();
			}
		}
		
		return n;
	}
	
	/**
	 * Returns the Player's level
	 * @return The Player's current level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Returns the Player's experience within a single level
	 * @return The Player's current experience
	 */
	public int getExperience() {
		return experience;
	}
	
	/**
	 * Returns the amount of experience needed to reach the next level
	 * @return The amount of experience
	 */
	public int getMaxXP() {
		return (level+1)*(level+2) * 10;
	}
	
	/**
	 * Gives the Player experience, and levels them up as many times as needed. When
	 * the Player levels up, they will also see their base stats increase.
	 * @param amount The amount of experience to give
	 */
	public void addXP(int amount) {
		experience += amount;
		System.out.println(getName() + " has earned " + StringFormatting.formatInt(amount) + " XP.");
		
		while (experience >= getMaxXP()) {
			experience -= getMaxXP();
			level++;
			System.out.println(getName() + " has reached level " + StringFormatting.formatInt(getLevel()) + "!");
			
			setAttack(playerClass.getAttack() * (5+level) / 5);
			setDefense(playerClass.getDefense() * (5+level) / 5);
			setSpeed(playerClass.getSpeed() * (5+level) / 5);
			setMaxHealth(playerClass.getHealth() * (5+level) / 5);
			setHealth(playerClass.getHealth() * (5+level) / 5);
		}
	}
	
	/**
	 * Returns the Player's inventory
	 * @return The Player's inventory
	 */
	public ItemSlot[] getInventory() {
		return inventory;
	}
	
	/**
	 * Puts an Item in the Player's inventory; if the slot is taken up, the old Item
	 * will be dropped to make room for the new one
	 * @param item The Item to equip
	 */
	public void equip(Item item) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].getName().equals(item.getType())) {
				if (inventory[i].getItem() != null) {
					drop(inventory[i]);
				}
				
				System.out.println(getName() + " puts " + item.getName() + " in " + getPossessive() + " " + inventory[i].getName() + " slot.");
				inventory[i].setItem(item);
			}
		}
	}
	
	/**
	 * Uses the Consumable, if there is one, located in the Player's ConsumableSlot
	 */
	public void useConsumable() {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] instanceof ConsumableSlot) {
				if (inventory[i].getItem() != null) {
					int oldHealth = getHealth();
					setHealth(getHealth() + inventory[i].getItem().getHealth());
					int newHealth = getHealth();
					System.out.println(getName() + " consumes " + inventory[i].getItem() + " and gains " + StringFormatting.formatInt(newHealth - oldHealth) + " health.");
					
					inventory[i].setItem(null);
				} else {
					System.out.println(getName() + " looks in " + getPossessive() + " inventory for something " + getSubject() + " can consume, but there's nothing there.");
				}
			}
		}
	}
	
	/**
	 * Returns the Item to be dropped when the Player makes their next move
	 * @return The Item that will be dropped
	 */
	public Item getDroppedItem() {
		return droppedItem;
	}
	
	/**
	 * Sets an Item to be dropped when the Player makes their next move
	 * @param item The Item that will be dropped
	 */
	public void setDroppedItem(Item item) {
		droppedItem = item;
	}
	
	/**
	 * Drops an item currently located within one of the Player's ItemSlots
	 * @param slot The ItemSlot to take an Item from
	 */
	public void drop(ItemSlot slot) {
		Item slotItem = slot.getItem();
		
		if (slotItem != null) {
			slot.setItem(null);
			setDroppedItem(slotItem);
			System.out.println(getName() + " drops " + slotItem.getName() + ".");
		} else {
			System.out.println("There's nothing in the " + slot.getName() + " slot to drop.");
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		
		s += "Name: " + getName() + "\n";
		s += "Level " + StringFormatting.formatInt(level) + " " + playerClass.getName() + " (" + StringFormatting.formatInt(experience) + "/" + StringFormatting.formatInt(getMaxXP()) + " XP)\n";
		s += "Attack: " + StringFormatting.formatInt(getAttack()) + "\n";
		s += "Defense: " + StringFormatting.formatInt(getDefense()) + "\n";
		s += "Speed: " + StringFormatting.formatInt(getSpeed()) + "\n";
		s += "Health: " + StringFormatting.formatInt(getHealth()) + "/" + StringFormatting.formatInt(getMaxHealth()) + "\n";
		s += "\nInventory:\n";
		
		for (int i = 0; i < inventory.length; i++) {
			s += inventory[i].toString() + "\n";
		}
		
		return s;
	}
}