package uiai.file;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import uiai.command.Todo;
import uiai.command.Deadline;
import uiai.command.Event;
import uiai.task.Task;

public class TasksLoader {
    public static ArrayList<Task> loadTasks(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
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
                        task = new Deadline(taskData[2], taskData[3]);
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
