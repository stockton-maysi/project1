/**
 * A class (in the game sense, not the Java sense) the user can choose for their
 * Player character.
 */
public class PlayerClass implements Named {
	private String name;
	private int baseAttack;
	private int baseDefense;
	private int baseSpeed;
	private int baseHealth;
	
	/**
	 * Constructs a new PlayerClass
	 * @param s The class name
	 * @param atk The class's base attack stat
	 * @param def The class's base defense stat
	 * @param spd The class's base speed stat
	 * @param lif The class's base health stat
	 */
	public PlayerClass(String s, int atk, int def, int spd, int lif) {
		name = s;
		baseAttack = atk;
		baseDefense = def;
		baseSpeed = spd;
		baseHealth = lif;
	}
	
	/**
	 * Returns the class's name
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the class's base attack stat
	 * @return The base attack
	 */
	public int getAttack() {
		return baseAttack;
	}
	
	/**
	 * Returns the class's base defense stat
	 * @return The base defense
	 */
	public int getDefense() {
		return baseDefense;
	}
	
	/**
	 * Returns the class's base speed stat
	 * @return The base speed
	 */
	public int getSpeed() {
		return baseSpeed;
	}
	
	/**
	 * Returns the class's base health stat
	 * @return The base health
	 */
	public int getHealth() {
		return baseHealth;
	}
}