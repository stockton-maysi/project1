/**
 * One of a finite number of commands the Player can input during the main gameplay,
 * via the MCInput class.
 */
public class MainGameOption implements Named {
	private String name;
	
	/**
	 * Constructs a new MainGameOption
	 * @param s The name of the option; must be one word
	 */
	public MainGameOption(String s) {
		name = s;
	}
	
	/**
	 * Returns the MainGameOption's name
	 * @return The option's name
	 */
	public String getName() {
		return name;
	}
}