package uiai.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, remove, and retrieve tasks from the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the specified tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index from the task list.
     *
     * @param index the index of the task to remove
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword the keyword to search for in the task descriptions
     * @return an ArrayList of tasks that contain the keyword in their description
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
