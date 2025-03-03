package uiai.task;

/**
 * Represents a Todo task.
 * A Todo task is a simple task without a specific deadline or time range.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the specified description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * The format is: [T][status] description.
     *
     * @return the string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }
}
