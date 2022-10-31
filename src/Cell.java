/**
 * Cells are the individual tiles that contain in-game objects/characters, aka
 * Encounters. They can interact with adjacent Cells in multiple ways.
 * <p>
 * A single character is used to display the Cell's data. A space is used for Empty
 * cells, P for the Player, I for Items, N for Enemies, and X for the Exit. If the
 * Cell is actually empty (i.e. null, distinct from containing an Empty object), it'll
 * display as a question mark, although this shouldn't happen in normal gameplay.
 */
public class Cell {
	private Encounter data;
	private Cell previous;
	private Cell next;
	
	/**
	 * Constructs a new cell containing an Encounter
	 * @param encounter The Encounter that the Cell contains
	 */
	public Cell(Encounter encounter) {
		data = encounter;
	}
	
	/**
	 * Returns the data inside the Cell
	 * @return The Encounter that the Cell contains
	 */
	public Encounter getData() {
		return data;
	}
	
	/**
	 * Sets the Cell data
	 * @param encounter The Encounter to put inside the Cell
	 */
	public void setData(Encounter encounter) {
		data = encounter;
	}
	
	/**
	 * Gets the Cell's leftward neighboring Cell
	 * @return The Cell's leftward neighbor
	 */
	public Cell getPrevious() {
		return previous;
	}
	
	/**
	 * Sets the Cell's leftward neighbor to something else
	 * @param cell The Cell to set it to
	 */
	public void setPrevious(Cell cell) {
		previous = cell;
	}
	
	/**
	 * Gets the Cell's rightward neighboring Cell
	 * @return The Cell's rightward neighbor
	 */
	public Cell getNext() {
		return next;
	}
	
	/**
	 * Sets the Cell's rightward neighbor to something else
	 * @param cell The Cell to set it to
	 */
	public void setNext(Cell cell) {
		next = cell;
	}
	
	@Override
	public String toString() {
		char character;
		
		if (data instanceof Player) {
			character = 'P';
		} else if (data instanceof Enemy) {
			character = 'N';
		} else if (data instanceof Exit) {
			character = 'X';
		} else if (data instanceof Item) {
			character = 'I';
		} else if (data instanceof Empty) {
			character = ' ';
		} else {
			character = '?';
		}
		
		return "[" + character + "]";
	}
}