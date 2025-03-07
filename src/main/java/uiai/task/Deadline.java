package uiai.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. A deadline task has a description and a due date and time by which it must be completed.
 * It extends from the {@link Task} class and provides functionality to represent the task as a string and retrieve
 * the deadline date in a specific format.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the task.
     * @param by The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task, including its type, status, description, and the formatted
     * deadline date.
     *
     * @return A string representing the deadline task in the format:
     *         "[D][status] description (by: formatted_date)"
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the deadline date formatted as "dd/MM/yyyy HHmm".
     *
     * @return A string representing the deadline date in the format "dd/MM/yyyy HHmm".
     */
    public String getByDate() {
        return by.format(INPUT_FORMAT);
    }
}
