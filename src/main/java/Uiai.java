import java.util.Scanner;

public class Uiai {
    public static final String DIVIDER = "\t---------------------------------------------";
    public static final String LOGO = "\t /\\_/\\\n"
            + "\t( o.o )\n"
            + "\t > ^ <";

    public static void main(String[] args) throws UiaiException {
        System.out.println("\t Meow! I'm Uiai, your helpful cat\n" + LOGO);
        System.out.println("\tHow can I help you?\n" + DIVIDER);

        Task[] tasks = new Task[100];
        int tasksIndex = 0;

        Scanner in = new Scanner(System.in);
        boolean task = true;

        while (task) {
            String line = in.nextLine();
            String[] command = line.split(" ", 2);

            switch (command[0]) {
            case "bye":
                commandBye();
                task = false;
                break;

            case "list":
                commandList(tasksIndex, tasks);
                break;

            case "mark":
                try {
                    commandMark(command, tasks);
                } catch (Exception e) {
                    System.out.println("\tInvalid task number.");
                }
                break;

            case "unmark":
                try {
                    commandUnmark(command, tasks, tasksIndex);
                } catch (UiaiException e) {
                    System.out.println("\t" + e.getMessage());
                }
                break;

            case "deadline":
                try {
                    tasksIndex = commandDeadline(command, tasks, tasksIndex);
                } catch (Exception e) {
                    System.out.println("\tNo Deadline found.");
                }
                break;

            case "todo":
                try {
                    tasksIndex = commandTodo(command, tasks, tasksIndex);
                } catch (Exception e) {
                    System.out.println("\tNo Todo found");
                }
                break;

            case "event":
                try {
                    tasksIndex = commandEvent(command, tasks, tasksIndex);
                } catch (Exception e) {
                    System.out.println("\tNo Event found.");
                }
                break;

            default:
                try {
                    throw UiaiException.noCommand();
                } catch (UiaiException e) {
                    System.out.println("\t" + e.getMessage());
                }

            }
        }
    }

    private static void commandBye() {
        System.out.println(DIVIDER + "\n" + "\tBye! Hope you had a meow-tastic time\n" + DIVIDER);
    }

    private static void commandList(int tasksIndex, Task[] tasks) {
        System.out.println(DIVIDER + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < tasksIndex; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i].toString());
        }
        System.out.println("\tCurrently there's " + tasksIndex + " tasks in your list!\n" + DIVIDER);
    }

    private static void commandMark(String[] command, Task[] tasks) throws UiaiException {
        try {
            int markIndex = Integer.parseInt(command[1]) - 1;
            tasks[markIndex].markAsDone();
            System.out.println(DIVIDER + "\n\tMarked as done:");
            System.out.println("\t" + tasks[markIndex].toString());
            System.out.println(DIVIDER);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new UiaiException("Invalid task number");
        }
    }

    private static void commandUnmark(String[] command, Task[] tasks, int tasksIndex) throws UiaiException {
        int unmarkIndex = Integer.parseInt(command[1]) - 1;

        // if index is out of bounds
        if (unmarkIndex < 0 || unmarkIndex >= tasksIndex || tasks[unmarkIndex] == null) {
            throw UiaiException.invalidTaskNumber(tasksIndex);
        }

        if (tasks[unmarkIndex].isDone) {
            tasks[unmarkIndex].markAsNotDone();
            System.out.println(DIVIDER + "\n\tMarked as not done yet:");
            System.out.println("\t" + tasks[unmarkIndex].toString());
            System.out.println(DIVIDER);
        } else {
            System.out.println(DIVIDER + "\n\tAlready marked as not done yet");
            System.out.println(DIVIDER);
        }
    }

    private static int commandDeadline(String[] command, Task[] tasks, int tasksIndex) {
        String[] description = command[1].split("/by ", 2);
        tasks[tasksIndex] = new Deadline(description[0], description[1]);
        System.out.println("\tAdded this task!");
        System.out.println("\t" + tasks[tasksIndex].toString());
        System.out.println("\tCurrently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
        tasksIndex++;
        return tasksIndex;
    }

    private static int commandTodo(String[] command, Task[] tasks, int tasksIndex) {
        String description = command[1];
        tasks[tasksIndex] = new Todo(description);
        System.out.println("\tAdded this task!");
        System.out.println("\t" + tasks[tasksIndex].toString());
        System.out.println("\tCurrently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
        tasksIndex++;
        return tasksIndex;
    }

    private static int commandEvent(String[] command, Task[] tasks, int tasksIndex) {
        String[] description = command[1].split("/from", 2);
        String[] time = description[1].split("/to", 2);
        tasks[tasksIndex] = new Event(description[0], time[0], time[1]);
        System.out.println("\tAdded this task!");
        System.out.println("\t" + tasks[tasksIndex].toString());
        System.out.println("\tCurrently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
        tasksIndex++;
        return tasksIndex;
    }

    private static int commandDefault(String line, int tasksIndex, Task[] tasks) {
        System.out.println(DIVIDER + "\n\t" + "Added this task!\n\t" + line + "\n\t" + "Currently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
        tasks[tasksIndex] = new Task(line);
        tasksIndex++;
        return tasksIndex;
    }
}