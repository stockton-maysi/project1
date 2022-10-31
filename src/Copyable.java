/**
 * An interface for objects which are to be copied, producing a new object with the
 * same attributes. Useful for populating the Dungeon with copies of the same few
 * Encounter templates 
 */
public interface Copyable {
	Copyable copy();
}