/**
 * An object which can appear inside the Dungeon. Contains many subclasses: Character
 * (which in turn has the subclasses Enemy and Player), Empty, Exit, and Item (which
 * in turn has the subclasses Consumable and Equipment)
 */
public class Encounter implements Named {
	private String name;
	
	/**
	 * Constructs a new Encounter with a given name
	 * @param s The Encounter's name
	 */
	public Encounter(String s) {
		name = s;
	}
	
	/**
	 * Returns the Encounter's name
	 * @return The name of the object
	 */
	public String getName() {
		return name;
	}
}