/**
 * An Encounter for open spaces in the Dungeon. If the Player steps on one of these,
 * it will simply be deleted in most circumstances, rather than moving to the Player's
 * old position. Thus, the Dungeon floor will shrink as the Player works their way
 * through it.
 */
public class Empty extends Encounter {
	/**
	 * Constructs a new Empty
	 */
	public Empty() {
		super(null);
	}
}