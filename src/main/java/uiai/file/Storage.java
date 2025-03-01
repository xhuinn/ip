package uiai.file;

import java.io.IOException;
import java.util.ArrayList;

import uiai.task.Task;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        TasksSaver.saveTasks(filePath, tasks);
    }
}
