import java.util.ArrayList;
import java.util.Scanner;

public class Uiai {

    public static final String DIVIDER = "\t♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡♡";
    public static final String LOGO = "\t /\\_/\\\n"
            + "\t( o.o )\n"
            + "\t > ^ <";

    public static void main(String[] args) {
        System.out.println("\t Meow! I'm Uiai, your helpful cat\n" + LOGO);
        System.out.println("\tHow can I help you?\n" + DIVIDER);

        //ArrayList<Task> tasks = new ArrayList<>();
        Task[] tasks = new Task[100];
        int tasksIndex = 0;

        Scanner in = new Scanner(System.in);
        boolean task = true;

        while (task) {
            String line = in.nextLine();
            String[] command = line.split(" ", 2);

            switch (command[0]) {
            case "bye":
                System.out.println(DIVIDER + "\n" + "\tBye! Hope you had a meow-tastic time ฅ^•⩊•^ฅ \n" + DIVIDER);
                task = false;
                break;

            case "list":
                System.out.println(DIVIDER + "\n\tᨐฅ Here are the tasks in your list:");
                for (int i = 0; i < tasksIndex; i++) {
                    System.out.println("\t" + (i + 1) + "." + tasks[i].toString());
                }
                System.out.println("\tcurrently there's " + tasksIndex + " tasks in your list!\n" + DIVIDER);
                break;

            case "mark":
                try {
                    int markIndex = Integer.parseInt(command[1]) - 1;
                    tasks[markIndex].markAsDone();
                    System.out.println(DIVIDER + "\n\t≽^-⩊-^≼ Marked as done:");
                    System.out.println("\t"  + tasks[markIndex].toString());
                    System.out.println(DIVIDER);
                } catch (Exception e) {
                    System.out.println("\tInvalid task number.");
                }
                break;

            case "unmark":
                try {
                    int unmarkIndex = Integer.parseInt(command[1]) - 1;
                    tasks[unmarkIndex].markAsNotDone();
                    System.out.println(DIVIDER + "\n\t≽^-⩊-^≼ Marked as not done yet:");
                    System.out.println("\t"  + tasks[unmarkIndex].toString());
                    System.out.println(DIVIDER);
                } catch (Exception e) {
                    System.out.println("\tInvalid task number.");
                }
                break;

            case "deadline":
                try {
                    String[] description = command[1].split("/by ", 2);
                    tasks[tasksIndex] = new Deadline(description[0], description[1] );
                    System.out.println("\tᓚ₍ ^. .^₎ added this task!");
                    System.out.println("\t" + tasks[tasksIndex].toString());
                    System.out.println("\tcurrently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
                    tasksIndex++;
                } catch (Exception e) {
                    System.out.println("\tNo Deadline found.");
                }
                break;

            case "todo":
                try {
                    String description = command[1];
                    tasks[tasksIndex] = new Todo(description);
                    System.out.println("\tᓚ₍ ^. .^₎ added this task!");
                    System.out.println("\t" + tasks[tasksIndex].toString());
                    System.out.println("\tcurrently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
                    tasksIndex++;
                } catch (Exception e) {
                    System.out.println("\tNo Todo found");
                }
                break;

            case "event":
                try {
                    String[] description = command[1].split("/from", 2);
                    String[] time = description[1].split("/to", 2);
                    tasks[tasksIndex] = new Event(description[0], time[0], time[1]);
                    System.out.println("\tᓚ₍ ^. .^₎ added this task!");
                    System.out.println("\t" + tasks[tasksIndex].toString());
                    System.out.println("\tcurrently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
                    tasksIndex++;
                } catch (Exception e) {
                    System.out.println("\tNo Event found.");
                }
                break;

            default:
                System.out.println(DIVIDER + "\n\t" + "ᓚ₍ ^. .^₎ added this task!\n\t " + line + "\n\t" + "currently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
                tasks[tasksIndex] = new Task(line);
                tasksIndex++;
                break;
            }
        }
    }
}