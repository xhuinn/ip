package uiai.file;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import uiai.task.Todo;
import uiai.task.Deadline;
import uiai.task.Event;
import uiai.task.Task;

public class TasksLoader {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public static ArrayList<Task> loadTasks(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

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
                case "T": // Todo
                    if (taskData.length == 3) {
                        task = new Todo(taskData[2]);
                    }
                    break;

                case "D": // Deadline
                    if (taskData.length == 4) {
                        try {
                            LocalDateTime deadlineDate = LocalDateTime.parse(taskData[3], DATE_FORMAT);
                            task = new Deadline(taskData[2], deadlineDate);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid deadline date format: " + taskData[3]);
                        }
                    }
                    break;

                case "E": // Event
                    if (taskData.length == 5) {
                        task = new Event(taskData[2], taskData[3], taskData[4]);
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
