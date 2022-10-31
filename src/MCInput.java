import java.util.Scanner;

/**
 * Allows the user to input one of a finite number of options corresponding to a list
 * of Named objects. May or may not have "other" as an option; if the user chooses
 * "other", an MCInputOtherException will be thrown.
 * <p>
 * Note that each option must have a one-word name; Scanner.next() only collects one
 * word at a time, so multi-word inputs cannot be obtained that way.
 */
public class MCInput {
	private Scanner scanner = new Scanner(System.in);
	
	private String prompt;
	private Named[] options;
	private boolean other;
	
	/**
	 * Constructs a new MCInput prompter
	 * @param s The initial prompt (excluding the options list)
	 * @param ops The array of Named objects, whose names will be used as potential
	 * user inputs
	 * @param oth Whether "other" is an option
	 */
	public MCInput(String s, Named[] ops, boolean oth) {
		prompt = s;
		options = ops;
		other = oth;
	}
	
	/**
	 * Asks the player for an input; if the input doesn't match any of the options,
	 * the prompt will be given again until a valid input is given
	 * @return The Named object which corresponds to the user input
	 */
	public Named getInput() {
		String fullPrompt = prompt + " (";
		int totalOptions = options.length + (other ? 1 : 0);
		
		for (int i = 0; i < totalOptions; i++) {
			if (i < options.length) {
				fullPrompt += options[i].getName();
			} else {
				fullPrompt += "other";
			}
			
			if (i != totalOptions - 1) {
				if (totalOptions == 2) {
					fullPrompt += " or ";
				} else {
					if (i != totalOptions - 2) {
						fullPrompt += ", ";
					} else {
						fullPrompt += ", or ";
					}
				}
			}
		}
		
		fullPrompt += "): ";
		
		while (true) {
			System.out.print(fullPrompt);
			String input = scanner.next().toLowerCase();
			
			if (input.equals("other")) {
				throw new MCInputOtherException();
			}
			
			for (int i = 0; i < options.length; i++) {
				if (options[i].getName().toLowerCase().equals(input)) {
					return options[i];
				}
			}
			
			System.out.println("Invalid input; please try again.");
		}
	}
}