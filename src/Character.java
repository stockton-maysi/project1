import java.lang.Math;
import java.util.Random;

/**
 * A type of Encounter which encompasses both Enemies and the Player. Characters have
 * attack, defense, speed, health, and maxHealth stats, but they also have
 * PronounSets for the purpose of correctly referring to them in the program output.
 */
public class Character extends Encounter {
	private Random random = new Random();
	
	private int attack;
	private int defense;
	private int speed;
	private int health;
	private int maxHealth;
	private boolean alive;
	private PronounSet pronouns;
	
	/**
	 * Constructs a new Character
	 * @param s The Character's name
	 * @param p The Character's pronouns
	 * @param atk The Character's attack stat
	 * @param def The Character's defense stat
	 * @param spd The Character's speed stat
	 * @param lif The Character's max health stat (starts out at 100% health)
	 */
	public Character(String s, PronounSet p, int atk, int def, int spd, int lif) {
		super(s);
		pronouns = p;
		
		attack = atk;
		defense = def;
		speed = spd;
		health = lif;
		maxHealth = lif;
		alive = true;
	}
	
	/**
	 * Returns the Character's pronouns
	 * @return The Character's PronounSet
	 */
	public PronounSet getPronouns() {
		return pronouns;
	}
	
	/**
	 * Returns the subject form of the Character's pronouns
	 * @return The subject pronoun
	 */
	public String getSubject() {
		return pronouns.getSubject();
	}
	
	/**
	 * Returns the object form of the Character's pronouns
	 * @return The object pronoun
	 */
	public String getObject() {
		return pronouns.getObject();
	}
	
	/**
	 * Returns the possessive form of the Character's pronouns
	 * @return The possessive pronoun
	 */
	public String getPossessive() {
		return pronouns.getPossessive();
	}
	
	/**
	 * Returns the Character's attack stat
	 * @return The attack stat
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * Sets the Character's attack stat
	 * @param n The number to set it to
	 */
	public void setAttack(int n) {
		attack = n;
	}
	
	/**
	 * Returns the Character's defense stat
	 * @return The defense stat
	 */
	public int getDefense() {
		return defense;
	}
	
	/**
	 * Sets the Character's defense stat
	 * @param n The number to set it to
	 */
	public void setDefense(int n) {
		defense = n;
	}
	
	/**
	 * Returns the Character's speed stat
	 * @return The speed stat
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Sets the Character's speed stat
	 * @param n The number to set it to
	 */
	public void setSpeed(int n) {
		speed = n;
	}
	
	/**
	 * Returns the Character's current health
	 * @return The current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the Character's current health
	 * @param n The number to set it to
	 */
	public void setHealth(int n) {
		health = Math.min(n, maxHealth);
		
		if (health <= 0) {
			System.out.println(getName() + " has died!");
			alive = false;
		}
	}
	
	/**
	 * Returns the Character's maximum health
	 * @return The maximum health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Sets the Character's maximum health
	 * @param n The number to set it to
	 */
	public void setMaxHealth(int n) {
		maxHealth = n;
	}
	
	/**
	 * Returns whether or not the Character is alive
	 * @return true if alive, false if dead
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Attacks another player or enemy. If attackBack is set to true, recursion will
	 * turn this into a fight to the death. The damage each party deals is normally
	 * equal to their own attack minus the target's defense, although attacks cannot
	 * deal negative damage.
	 * <p>
	 * Hits have a 10% chance of being critical, meaning they will have twice as much
	 * base damage and also ignore all of the target's defense.
	 * <p>
	 * Also note that if the player is one (non-critical) hit away from being killed,
	 * they will automatically try to use their consumable to get health back.
	 * 
	 * @param target The player/enemy to target
	 * @param attackBack Whether said player/enemy will try to attack back. Set to
	 * true for a full fight, false for a one-off hit
	 */
	public void attack(Character target, boolean attackBack) {
		if (alive) {
			int damageDealt;
			boolean critical;
			if (random.nextFloat() < 0.1) {
				damageDealt = Math.max(attack*2, 1);
				critical = true;
				System.out.print("CRITICAL HIT! ");
			} else {
				damageDealt = Math.max(attack - target.getDefense(), 1);
				critical = false;
			}
			
			System.out.println(getName() + " attacks " + target.getName() + " and deals " + StringFormatting.formatInt(damageDealt) + " damage.");
			
			target.setHealth(target.getHealth() - damageDealt);
			
			if (!critical && target.isAlive() && target instanceof Player && damageDealt >= target.getHealth()) {
				System.out.print("Close to death, frantically ");
				((Player) target).useConsumable();
			}
			
			if (attackBack) {
				target.attack(this, true);
			}
		//If the Player won the fight, give them some XP
		} else if (this instanceof Enemy && target instanceof Player) {
			int experienceGained = ((Enemy) this).getExperience();
			((Player) target).addXP(experienceGained);
		}
	}
}