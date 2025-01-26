import java.util.ArrayList;
import java.util.Scanner;

public class Uiai {

    public static void main(String[] args) {
        String logo =
                "\t /\\_/\\\n"
                        + "\t( o.o )\n"
                        + "\t > ^ <";

        String divider =
                "\t♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡";

        System.out.println("\t Meow! I'm Uiai, your helpful cat\n" + logo);
        System.out.println("\tHow can I help you?\n" + divider);

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        boolean task = true;

        while (task) {
            String line = in.nextLine();
            String[] command = line.split(" ", 2);

            switch (command[0]) {
            case "bye":
                System.out.println(divider + "\n" + "\tBye! Hope you had a meow-tastic time ฅ^•⩊•^ฅ \n" + divider);
                task = false;
                break;

            case "list":
                System.out.println(divider + "\n\tᨐฅ Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("\t" + (i + 1) + ".[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description + "\n");
                }
                System.out.println(divider);
                break;

            case "mark":
                try {
                    int markIndex = Integer.parseInt(command[1]) - 1;
                    tasks.get(markIndex).markAsDone();
                    System.out.println(divider + "\n\t≽^-⩊-^≼ Marked as done:");
                    System.out.println("\t  [" + tasks.get(markIndex).getStatusIcon() + "] " + tasks.get(markIndex).description);
                    System.out.println(divider);
                } catch (Exception e) {
                    System.out.println("\tInvalid task number.");
                }
                break;

            case "unmark":
                try {
                    int unmarkIndex = Integer.parseInt(command[1]) - 1;
                    tasks.get(unmarkIndex).markAsNotDone();
                    System.out.println(divider + "\n\t≽^-⩊-^≼ Marked as not done yet:");
                    System.out.println("\t  [" + tasks.get(unmarkIndex).getStatusIcon() + "] " + tasks.get(unmarkIndex).description);
                    System.out.println(divider);
                } catch (Exception e) {
                    System.out.println("\tInvalid task number.");
                }
                break;

            default:
                System.out.println(divider + "\n\t" + "added: " + line + "\n" + divider);
                tasks.add(new Task(line));
                break;
            }
        }
    }
}