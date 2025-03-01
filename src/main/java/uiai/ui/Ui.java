package uiai.ui;

import java.util.Scanner;

public class Ui {
    public static final String DIVIDER = "\t---------------------------------------------";
    public static final String LOGO = "\t /\\_/\\\n"
            + "\t( o.o )\n"
            + "\t > ^ <";

    public void showWelcomeMessage() {
        System.out.println("\n\t Meow! I'm uiai, your helpful cat\n" + LOGO);
        System.out.println("\tHow can I help you?\n" + DIVIDER);
    }

    public String readCommand(Scanner in) {
        return in.nextLine();
    }

    public void showError(String message) {
        System.out.println("\t" + message);
    }

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public void showMessage(String message) {
        System.out.println("\t" + message);
    }
}
