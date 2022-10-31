/**
 * A subclass of Item. Unlike Equipment, Consumables can only have their effects
 * (i.e. increasing the Player's current health) used once before disappearing from
 * the Player's inventory.
 */
public class Consumable extends Item implements Copyable {
	/**
	 * Constructs a new Consumable
	 * @param n The name of the Item
	 * @param lif The amount of health it gives when consumed
	 */
	public Consumable(String n, int lif) {
		super(n, "Consumable", 0, 0, 0, lif, 0);
	}
	
	/**
	 * Constructs and returns a new Consumable with the same name and health buff
	 */
	public Consumable copy() {
		return new Consumable(getName(), getHealth());
	}
	
	@Override
	public String toString() {
		return getName() + " (" + StringFormatting.formatInt(getHealth(), true) + " health when consumed)";
	}
}