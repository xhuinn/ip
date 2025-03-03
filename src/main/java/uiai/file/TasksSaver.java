package uiai.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import uiai.task.Deadline;
import uiai.task.Event;
import uiai.task.Task;
import uiai.task.Todo;

/**
 * A utility class responsible for saving tasks to a file. This class writes the tasks
 * in a specific format to the specified file path, ensuring that the task data is preserved.
 */
public class TasksSaver {
    /**
     * Saves the list of tasks to a file at the specified file path.
     * Each task is written in a format where the task type, completion status, and relevant task data
     * are separated by " | " symbols. Tasks are saved one per line.
     *
     * <p>Task types are denoted as follows:
     * <ul>
     *     <li>"T" for Todo tasks</li>
     *     <li>"D" for Deadline tasks</li>
     *     <li>"E" for Event tasks</li>
     * </ul>
     * The task data includes the completion status (1 for done, 0 for not done),
     * task description, and relevant dates (for Deadline and Event tasks).
     * </p>
     *
     * @param filePath The path to the file where the tasks will be saved.
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public static void saveTasks(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks) {
            String taskType = "";

            if (task instanceof Todo) {
                taskType = "T"; // Todo
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Todo) task).description;
                writer.write(taskData + System.lineSeparator());
            } else if (task instanceof Deadline) {
                taskType = "D"; //Deadline
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Deadline) task).description + " | " + ((Deadline) task).getByDate();
                writer.write(taskData + System.lineSeparator());
            } else if (task instanceof Event) {
                taskType = "E"; //Event
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Event) task).description + " | " + ((Event) task).getFromDate() + " | " + ((Event) task).getToDate();
                writer.write(taskData + System.lineSeparator());
            }
        }
        writer.close();
    }
}
