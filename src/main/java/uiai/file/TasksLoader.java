package uiai.file;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import uiai.exception.UiaiException;
import uiai.task.Todo;
import uiai.task.Deadline;
import uiai.task.Event;
import uiai.task.Task;

/**
 * A utility class that handles loading tasks from a file. This class reads task data
 * from a specified file and parses the information into appropriate Task objects.
 */
public class TasksLoader {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Loads tasks from the file located at the specified file path.
     * The tasks are parsed from each line in the file, with each task being identified
     * by its type and attributes. If the task data is malformed or the format is incorrect,
     * an error message will be printed, and the invalid task will be skipped.
     *
     * @param filePath The path to the file from which tasks will be loaded.
     * @return An {@link ArrayList} of {@link Task} objects parsed from the file.
     * @throws IOException If an error occurs during reading the file.
     */
    public static ArrayList<Task> loadTasks(String filePath) throws IOException {
        File file = new File(filePath);

        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskData = line.split(" \\| ");

                if (taskData.length < 3) {
                    System.out.println("Invalid task format: " + line);
                    continue;
                }

                Task task = null;

                switch (taskData[0]) {

                // Todo
                case "T":
                    if (taskData.length == 3) {
                        task = new Todo(taskData[2]);
                    }
                    break;

                // Deadline
                case "D":
                    if (taskData.length == 4) {
                        try {
                            LocalDateTime deadlineDate = LocalDateTime.parse(taskData[3], DATE_FORMAT);
                            task = new Deadline(taskData[2], deadlineDate);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid deadline date format: " + taskData[3]);
                        }
                    }
                    break;

                // Event
                case "E":
                    if (taskData.length == 5) {
                        try {

                            LocalDateTime deadlineFromDate = LocalDateTime.parse(taskData[3], DATE_FORMAT);
                            LocalDateTime deadlineToDate = LocalDateTime.parse(taskData[4], DATE_FORMAT);
                            task = new Event(taskData[2], deadlineFromDate, deadlineToDate);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid deadline date format");
                        }
                    }
                    break;

                default:
                    System.out.println("Unknown task type: " + taskData[0]);
                    break;
                }

                if (task != null) {
                    if ("1".equals(taskData[1])) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } else {
                    System.out.println("Invalid task data: " + line);
                }
            }
        }
        return tasks;
    }
}
