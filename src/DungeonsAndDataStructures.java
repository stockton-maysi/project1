import java.util.Scanner;

/**
 * Dungeons and Data Structures
 * <p>
 * A vaguely Dungeons-and-Dragons-inspired text RPG (I've never played D&D) which
 * takes place on a circular linked list. Features multiple different enemy types,
 * numerous different items to equip/consume, a level-up system, a critical hit system,
 * and character customization.
 * 
 * @author Ian Mays
 * @version 1.0
 */
public class DungeonsAndDataStructures {
	/**
	 * Has the player customize their character (name, pronouns, class) and starts a
	 * new game
	 * @param args Not applicable; all arguments are provided via scanner
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		PronounSet he = new PronounSet("he", "him", "his");
		PronounSet she = new PronounSet("she", "her", "her");
		PronounSet they = new PronounSet("they", "them", "their");
		PronounSet it = new PronounSet("it", "it", "its");
		PronounSet[] standardPronouns = {he, she, they, it};
		
		PlayerClass[] classes = {
			new PlayerClass("Normal", 50, 10, 50, 500),
			new PlayerClass("Tank", 40, 15, 25, 600),
			new PlayerClass("Warrior", 60, 5, 40, 500),
			new PlayerClass("Ninja", 40, 5, 100, 400)
		};
		
		System.out.print("Enter player name: ");
		String playerName = scanner.next();
		System.out.println("Player name is " + playerName + ".");
		
		PronounSet playerPronouns;
		try {
			playerPronouns = (PronounSet) (new MCInput("Enter player pronouns", standardPronouns, true)).getInput();
		} catch (MCInputOtherException exception) {
			System.out.println("You chose 'other', so let's make a neopronoun. Fill in the blanks.");
			System.out.print("Enter subject pronoun (\"___ is the player.\"): ");
			String subject = scanner.next().toLowerCase();
			System.out.print("Enter object pronoun (\"" + StringFormatting.capitalizeFirst(subject) + " was surprised when an enemy attacked ___.\"): ");
			String object = scanner.next().toLowerCase();
			System.out.print("Enter possessive pronoun (\"Luckily, ___ armor protected " + object + ", so " + subject + " only took 1 damage.\"): ");
			String possessive = scanner.next().toLowerCase();
			
			playerPronouns = new PronounSet(subject, object, possessive);
		}
		
		System.out.println("Player pronouns are " + playerPronouns.getSubject() + "/" + playerPronouns.getObject() + "/" + playerPronouns.getPossessive() + ".");
		
		PlayerClass playerClass;
		playerClass = (PlayerClass) (new MCInput("Enter player class", classes, false)).getInput();
		System.out.println("Player class is " + playerClass.getName() + ": " + playerClass.getAttack() + " ATK, " + playerClass.getSpeed() + " SPD, " + playerClass.getDefense() + " DEF, " + playerClass.getHealth() + " LIF.");
		
		Player player = new Player(playerName, playerPronouns, playerClass);
		
		//Start game on floor 1
		(new Dungeon(player, 1)).play();
	}
}