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

        String[] items = new String[100];
        int index = 0;
        Scanner in = new Scanner(System.in);

        boolean task = true;
        while (task) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println(divider + "\n" + "\tBye! Hope you had a meow-tastic time ฅ^•⩊•^ฅ \n" + divider);
                task = false;
            } else if (line.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + (i + 1) + ": " + items[i]);
                }
            } else {
                System.out.println(divider + "\n\t" + "added: " + line + "\n" + divider);
                items[index] = line;
                index++;
            }
        }
    }
}