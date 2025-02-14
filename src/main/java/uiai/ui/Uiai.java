package uiai.ui;

import java.util.Objects;
import java.util.Scanner;

import uiai.command.Deadline;
import uiai.command.Event;
import uiai.command.Todo;
import uiai.task.Task;
import uiai.exception.UiaiException;

public class Uiai {
    public static final String DIVIDER = "\t---------------------------------------------";
    public static final String LOGO = "\t /\\_/\\\n"
            + "\t( o.o )\n"
            + "\t > ^ <";

    public static void main(String[] args) throws UiaiException {
        System.out.println("\t Meow! I'm uiai, your helpful cat\n" + LOGO);
        System.out.println("\tHow can I help you?\n" + DIVIDER);

        Task[] tasks = new Task[100];
        int tasksIndex = 0;

        Scanner in = new Scanner(System.in);
        boolean task = true;

        while (task) {
            try {
                String line = in.nextLine();
                String[] command = line.split(" ", 2);

                if (command.length != 2 && !Objects.equals(command[0], "list") && !Objects.equals(command[0], "bye")) {
                    throw UiaiException.incorrectFormat();
                }

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
                        commandMark(command, tasks, tasksIndex);
                    } catch (UiaiException e) {
                        System.out.println("\t" + e.getMessage());
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
                    } catch (UiaiException e) {
                        System.out.println("\t" + e.getMessage());
                    }
                    break;

                case "todo":
                    try {
                        tasksIndex = commandTodo(command, tasks, tasksIndex);
                    } catch (UiaiException e) {
                        System.out.println("\t" + e.getMessage());
                    }
                    break;

                case "event":
                    try {
                        tasksIndex = commandEvent(command, tasks, tasksIndex);
                    } catch (UiaiException e) {
                        System.out.println("\t" + e.getMessage());
                    }
                    break;

                default:
                    try {
                        throw UiaiException.incorrectFormat();
                    } catch (UiaiException e) {
                        System.out.println("\t" + e.getMessage());
                    }

                }
            } catch (UiaiException e) {
                System.out.println("\t" + e.getMessage());
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

    private static void commandMark(String[] command, Task[] tasks, int tasksIndex) throws UiaiException {
        int markIndex = Integer.parseInt(command[1]) - 1;

        // if index is out of bounds
        if (markIndex < 0 || markIndex >= tasksIndex || tasks[markIndex] == null) {
            throw UiaiException.invalidTaskNumber(tasksIndex);
        }

        // if task is marked already
        if (tasks[markIndex].isDone()) {
            throw UiaiException.markedTask();
        }

        tasks[markIndex].markAsDone();
        System.out.println(DIVIDER + "\n\tMarked as done:");
        System.out.println("\t" + tasks[markIndex].toString());
        System.out.println(DIVIDER);
    }


    private static void commandUnmark(String[] command, Task[] tasks, int tasksIndex) throws UiaiException {
        int unmarkIndex = Integer.parseInt(command[1]) - 1;

        // if index is out of bounds
        if (unmarkIndex < 0 || unmarkIndex >= tasksIndex || tasks[unmarkIndex] == null) {
            throw UiaiException.invalidTaskNumber(tasksIndex);
        }

        // if task is unmarked already
        if (!tasks[unmarkIndex].isDone()) {
            throw UiaiException.unmarkedTask();
        }

        tasks[unmarkIndex].markAsNotDone();
        System.out.println(DIVIDER + "\n\tMarked as not done yet:");
        System.out.println("\t" + tasks[unmarkIndex].toString());
        System.out.println(DIVIDER);
    }


    private static int commandDeadline(String[] command, Task[] tasks, int tasksIndex) throws UiaiException {
        String[] description = command[1].split("/by ", 2);

        if (description.length != 2) {
            throw UiaiException.incorrectDeadlineFormat();
        }

        tasks[tasksIndex] = new Deadline(description[0], description[1]);
        System.out.println("\tAdded this task!");
        System.out.println("\t" + tasks[tasksIndex].toString());
        System.out.println("\tCurrently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
        tasksIndex++;
        return tasksIndex;
    }

    private static int commandTodo(String[] command, Task[] tasks, int tasksIndex) throws UiaiException {

        String description = command[1];
        tasks[tasksIndex] = new Todo(description);
        System.out.println("\tAdded this task!");
        System.out.println("\t" + tasks[tasksIndex].toString());
        System.out.println("\tCurrently there's " + (tasksIndex + 1) + " tasks in your list!\n" + DIVIDER);
        tasksIndex++;
        return tasksIndex;
    }

    private static int commandEvent(String[] command, Task[] tasks, int tasksIndex) throws UiaiException {
        if (command[1] == null || command[1].isBlank()) {
            throw UiaiException.incorrectEventFormat();
        }

        String[] description = command[1].split("/from", 2);

        // check if /from part is present
        if (description.length != 2 || description[0].isBlank() || description[1].isBlank()) {
            throw UiaiException.incorrectEventFormat();
        }

        String[] time = description[1].split("/to", 2);

        // check if /to part is present
        if (time.length != 2 || time[0].isBlank() || time[1].isBlank()) {
            throw UiaiException.incorrectEventFormat();
        }

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