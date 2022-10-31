/**
 * A slot in the inventory for holding Items. Can be a ConsumableSlot (for
 * Consumables) or an EquipmentSlot (for Equipment)
 */
public class ItemSlot implements Named {
	private String name;
	private Item currentItem;
	
	/**
	 * Constructs a new ItemSlot
	 * @param s The slot name, determining what items can go inside it (only
	 * customizable for EquipmentSlots; ConsumableSlots are "Consumable")
	 */
	public ItemSlot(String s) {
		name = s;
	}
	
	/**
	 * Returns the ItemSlot's name
	 * @return The slot name (i.e. what type of item it holds)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the ItemSlot's contents
	 * @return The Item currently inside the slot
	 */
	public Item getItem() {
		return currentItem;
	}
	
	/**
	 * Puts an Item in the ItemSlot
	 * @param item The Item to place in the slot
	 */
	public void setItem(Item item) {
		currentItem = item;
	}
	
	@Override
	public String toString() {
		return name + ": " + (currentItem != null ? currentItem.toString() : "empty");
	}
}