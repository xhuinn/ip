package uiai.file;

import java.io.IOException;
import java.util.ArrayList;

import uiai.task.Task;

/**
 * Handles the loading and saving of tasks to and from a file.
 * This class interacts with the file system to persist task data
 * and retrieve it upon program startup.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath The file path where tasks will be stored and retrieved from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file located at the specified file path.
     * If the file cannot be loaded, an empty task list is returned,
     * and an error message is printed.
     *
     * @return An {@link ArrayList} of {@link Task} objects loaded from the file.
     * @throws IOException If an error occurs during the file reading process.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            tasks = TasksLoader.loadTasks(filePath);
            System.out.println("\tLoaded tasks:");
            for (Task task : tasks) {
                System.out.println("\t" + task);
            }
        } catch (IOException e) {
            System.out.println("\tError loading tasks. Starting with an empty task list.");
        }
        return tasks;
    }

    /**
     * Saves the specified list of tasks to the file located at the specified file path.
     *
     * @param tasks An {@link ArrayList} of {@link Task} objects to be saved to the file.
     * @throws IOException If an error occurs during the file writing process.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        TasksSaver.saveTasks(filePath, tasks);
    }
}
