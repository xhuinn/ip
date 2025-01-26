import java.util.Scanner;

public class uiai {
    public static void main(String[] args) {
        String logo =
                "\t /\\_/\\\n"
                        + "\t( o.o )\n"
                        + "\t > ^ <";

        String divider =
                "\t♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡";

        System.out.println("\t Meow! I'm uiai, your helpful cat\n" + logo);
        System.out.println("\tHow can I help you?\n" + divider);

        // Level-1: echo
        boolean task = true;
        while (task) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println(divider + "\n" + "\tBye! Hope you had a meow-tastic time ฅ^•⩊•^ฅ \n" + divider);
                task = false;
            } else {
                System.out.println(divider + "\n\t" + line + "\n" + divider);
                line = null;
            }
        }
    }
}


