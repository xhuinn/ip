package uiai.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import uiai.command.Todo;
import uiai.command.Deadline;
import uiai.command.Event;
import uiai.task.Task;

public class TaskLoader {
    public static ArrayList<Task> loadTasks(String filePath) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return an empty list if the file doesn't exist
        }

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split(" \\| ");
            Task task = null;

            switch (taskData[0]) {
            case "T":
                task = new Todo(taskData[2]);
                break;
            case "D":
                task = new Deadline(taskData[2], taskData[3]);
                break;
            case "E":
                task = new Event(taskData[2], taskData[3], taskData[4]);
                break;
            }

            if (task != null && taskData[1].equals("1")) {
                task.markAsDone();
            }

            if (task != null) {
                tasks.add(task); // Add the task to the ArrayList
            }
        }

        scanner.close();

        return tasks;
    }
}