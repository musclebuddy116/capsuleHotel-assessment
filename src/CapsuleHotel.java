import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

/** CapsuleHotel Project - HTD Java Accelerator Module 1 Assessment
 * "A capsule hotel is a compact hotel with bed-sized capsules instead of rooms.
 * Capsules are stacked. They're outfitted with a single bed that fills the entire capsule,
 * a television, and a control panel for the television, lighting, and temperature control.
 * Guests use shared restrooms and showers. No visitors are allowed."
 *
 * Capabilities:
 * On start up, the application prompts the administrator for the hotel's capacity. The capacity determines how many capsules are available.
 * The administrator may book a guest in an unoccupied numbered capsule.
 * The administrator may check out a guest from an occupied capsule.
 * The administrator may view guests and their capsule numbers in groups of 10.
 */

public class CapsuleHotel {
    public static int capsulesAvailable;
    public static String[] capsules;

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        // Display welcome message
        System.out.println("Hello, administrator.");
        System.out.println("=====================");

        // Prompt user to enter the number of capsules available
        capsulesAvailable = readInt(console, "Please enter the number of capsules available for today: ");

        // Create string array for capsules with the specified capacity
        capsules = new String[capsulesAvailable];

        System.out.println("There are " + capsules.length + " unoccupied capsules ready to be booked.");

        // Display menu
        menu(console);



    }

    /**
     * Reads a string input by the user.
     * @param console the Scanner object to use for input
     * @param prompt the message to display
     * @return a non-null, non-empty, non-whitespace string
     */

    public static String readString(Scanner console, String prompt) {
        String input;
        boolean isValid = false;
        do {
            System.out.print(prompt);
            input = console.nextLine();

            if (input == null) {
                isValid = false;
            } else {
                for (int i = 0; i < input.length(); i++) {
                    if (!Character.isWhitespace(input.charAt(i))) {
                        isValid = true;
                        break;
                    }
                }
            }
        } while(!isValid);

        return input;
    }

    /**
     * Reads an integer input by the user.
     * Uses readString() and validates for integer input
     * @param console the Scanner object to use for input
     * @param prompt the message to display
     * @return a non-negative integer
     */

    public static int readInt(Scanner console, String prompt) {
        String inputStr;
        boolean isValid = false;

        do {
            inputStr = readString(console, prompt);

            boolean isInt = true;
            for (int i = 0; i < inputStr.length(); i++) {
                if(!Character.isDigit(inputStr.charAt(i))) {
                    isInt = false;
                    break;
                }
            }
            isValid = isInt;

        } while (!isValid);

        return Integer.parseInt(inputStr);

    }

    public static void menu(Scanner console) {
        boolean exit = false;

        do {
            String option = getMenuOption(console);

            switch (option) {
                case "1":
                    System.out.println("\nGuest Check In");
                    System.out.println("==============");
                    String guestName = readString(console,"Guest Name: ");
                    boolean success = handleCheckIn(console, guestName);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println("I don't recognize that option. Please try again.");
            }

        } while (!exit);

    }

    /**
     * Method Name: getMenuOption
     * The getMenuOption method is designed to display a simple menu for a guest system and prompt the user to choose an option.
     * It takes a Scanner object as a parameter to read user input and returns the selected option as a String.
     * @param console the Scanner object to use for input
     * @return String the selected menu option
     */

    public static String getMenuOption(Scanner console) {

        System.out.println("\nGuest menu");
        System.out.println("==========");
        System.out.println("1. Check in");
        System.out.println("2. Check out");
        System.out.println("3. View guests");
        System.out.println("4. Exit");

        String option = readString(console, "Choose an option [1-4]: ");

        return option;
    }

    /**
     * Method Name: handleCheckIn
     * The handleCheckIn method is designed to facilitate the check-in process for guests into capsules.
     * It takes a Scanner object for user input and a String representing the guest's name.
     * The method returns a boolean value indicating the success or failure of the check-in process.
     * @param console the Scanner object to use for input
     * @param guestName the name of the guest being checked in
     * @return boolean the status (success/fail) of the check in
     * If the capsule number does not exist the user should see the following error message:
     * Error :(
     * Capsule #9 does not exist. (9 is an example, should be replaced by the capsule number the user tries to input)
     * If the Guest is successfully booked the user should see the following success message:
     * Success :)
     * John is booked in capsule #3 (John and 3 are examples, they should be replaced by the user inputs)
     * If the capsule is occupied the user should see the following error message:
     * Error :(
     * Capsule #5 is occupied. (5 is an example, should be replaced by the capsule number the user tries to input)
     */

    public static boolean handleCheckIn(Scanner console, String guestName) {
        int capsuleNum = readInt(console, "Capsule #[1-" + capsulesAvailable + "]: ");
        int capsuleI = capsuleNum - 1;

        if (capsuleI < 0 || capsuleI >= capsulesAvailable) {
            System.out.println("Error :(");
            System.out.println("Capsule #" + capsuleNum + " does not exist");
            return false;
        }
        if (capsules[capsuleI] != null) {
            System.out.println("Error :(");
            System.out.println("Capsule #" + capsuleNum + " is occupied.");
            return false;
        }
        // Shouldn't reach this code unless capsule is a valid option
        capsules[capsuleI] = guestName;
        System.out.println("\nSuccess :)");
        System.out.printf("%s is booked in capsule #%d\n", guestName, capsuleNum);
        return true;
    }
}
