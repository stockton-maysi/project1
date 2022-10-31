/**
 * An Encounter which can go inside the Player's inventory. Can be Equipment (stays
 * in the inventory until taken off) or Consumable (used once and restores some of the
 * player's health)
 */
public class Item extends Encounter {
	private String type;
	private int attack;
	private int defense;
	private int speed;
	private int health;
	private int maxHealth;
	
	/**
	 * Constructs a new Item
	 * @param n The Item's name
	 * @param t The name of the slot it goes in (only customizable for Equipment;
	 * Consumables go in "Consumable")
	 * @param atk The attack boost (Equipment only)
	 * @param def The defense boost (Equipment only)
	 * @param spd The speed boost (Equipment only)
	 * @param lif The health restored (Consumable only)
	 * @param mxl The maximum health boost (Equipment only)
	 */
	public Item(String n, String t, int atk, int def, int spd, int lif, int mxl) {
		super(n);
		
		type = t;
		attack = atk;
		defense = def;
		speed = spd;
		health = lif;
		maxHealth = mxl;
	}

	/**
	 * Returns the Item's intended slot
	 * @return The Item's slot, as a String
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Returns the Item's attack boost
	 * @return The attack stat
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * Returns the Item's defense boost
	 * @return The defense stat
	 */
	public int getDefense() {
		return defense;
	}
	
	/**
	 * Returns the Item's speed boost
	 * @return The speed stat
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Returns the Item's health restoration
	 * @return The current health stat
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns the Item's maximum health boost
	 * @return The maximum health boost
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
}