/**
 * A set of pronouns to be used by Characters. Features subject, object, and
 * possessive forms.
 */
public class PronounSet implements Named {
	private String subject;
	private String object;
	private String possessive;
	
	/**
	 * Constructs a new PronounSet
	 * @param subj The subject pronoun
	 * @param obj The object pronoun
	 * @param poss The possessive pronoun
	 */
	public PronounSet(String subj, String obj, String poss) {
		subject = subj;
		object = obj;
		possessive = poss;
	}
	
	/**
	 * Returns the "name" of the pronoun, for the purposes of MCInput
	 * @return The subject pronoun
	 */
	public String getName() {
		return subject;
	}
	
	/**
	 * Returns the subject pronoun
	 * @return The subject pronoun
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Returns the object pronoun
	 * @return The object pronoun
	 */
	public String getObject() {
		return object;
	}
	
	/**
	 * Returns the possessive pronoun
	 * @return The possessive pronoun
	 */
	public String getPossessive() {
		return possessive;
	}
}