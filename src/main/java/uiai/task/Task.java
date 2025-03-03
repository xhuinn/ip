package uiai.task;

/**
 * Represents a general task with a description and a completion status.
 * The task can be marked as done or not done, and its status can be checked.
 * Subclasses of Task can extend this class to add more specific task types, such as Todo, Deadline, and Event.
 */
public class Task {
    public String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description. By default, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the status icon of the task. If the task is done, it returns "X", otherwise returns a space.
     *
     * @return A string representing the status of the task ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its type, status, and description.
     *
     * @return A string representing the task in the format:
     *         "[ ] [status] description"
     */
    @Override
    public String toString() {
        return "[ ]" + "[" + getStatusIcon() + "] " + description;
    }
}