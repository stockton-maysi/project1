/**
 * One of the two classes of Character, the other being Player. Enemies drop
 * experience when killed.
 */
public class Enemy extends Character implements Copyable {
	private int experience;
	
	/**
	 * Constructs a new Enemy
	 * @param s The Enemy's name
	 * @param pronouns The Enemy's pronouns (usually "it")
	 * @param atk The Enemy's attack stat
	 * @param def The Enemy's defense stat
	 * @param spd The Enemy's speed stat
	 * @param lif The Enemy's max health stat (health starts at 100%)
	 * @param xp The amount of experience dropped when killed
	 */
	public Enemy(String s, PronounSet pronouns, int atk, int def, int spd, int lif, int xp) {
		super(s, pronouns, atk, def, spd, lif);
		
		experience = xp;
	}
	
	/**
	 * Returns the experience value
	 * @return The amount of experience dropped when the Enemy is killed
	 */
	public int getExperience() {
		return experience;
	}
	
	/**
	 * Creates and returns a new Enemy with identical name, pronouns, stats, and
	 * experience
	 */
	public Enemy copy() {
		return new Enemy(getName(), getPronouns(), getAttack(), getDefense(), getSpeed(), getMaxHealth(), experience);
	}
}