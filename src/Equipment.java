import java.util.ArrayList;

/**
 * A subclass of Item. Equipment grants passive buffs (and debuffs) to the Player, and
 * remains on them until they decide to pick up something else in the same slot.
 */
public class Equipment extends Item implements Copyable {
	/**
	 * Constructs a new piece of Equipment
	 * @param n The Equipment's name
	 * @param t The name of the slot it belongs to
	 * @param atk The attack boost granted
	 * @param def The defense boost granted
	 * @param spd The speed boost granted
	 * @param mxl The health boost granted
	 */
	public Equipment(String n, String t, int atk, int def, int spd, int mxl) {
		super(n, t, atk, def, spd, 0, mxl);
	}
	
	@Override
	public String toString() {
		String s = getName();
		
		//Formats a list of the non-zero attributes
		ArrayList<String> attributes = new ArrayList<>();
		
		if (getAttack() != 0) {
			attributes.add(StringFormatting.formatInt(getAttack(), true) + " ATK");
		}
		
		if (getDefense() != 0) {
			attributes.add(StringFormatting.formatInt(getDefense(), true) + " DEF");
		}
		
		if (getSpeed() != 0) {
			attributes.add(StringFormatting.formatInt(getSpeed(), true) + " SPD");
		}
		
		if (getMaxHealth() != 0) {
			attributes.add(StringFormatting.formatInt(getMaxHealth(), true) + " LIF");
		}
		
		if (attributes.size() > 0) {
			s += " (";
			
			for (int i = 0; i < attributes.size(); i++) {
				s += attributes.get(i);
				
				if (i != attributes.size() - 1) {
					s += ", ";
				}
			}
			
			s += ")";
		}
		
		return s;
	}
	
	/**
	 * Creates and returns a new piece of Equipment with the same name, type, and
	 * stats
	 */
	public Equipment copy() {
		return new Equipment(getName(), getType(), getAttack(), getDefense(), getSpeed(), getMaxHealth());
	}
}