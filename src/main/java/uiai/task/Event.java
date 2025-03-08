package uiai.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task. An event task has a description and a start and end date/time range for the event.
 * It extends from the {@link Task} class and provides functionality to represent the task as a string and retrieve
 * the start and end dates of the event.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * Constructs an Event task with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task, including its type, status, description, and the start and
     * end dates.
     *
     * @return A string representing the event task in the format:
     *         "[E][status] description (from: start_date to: end_date)"
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the start date/time of the event.
     *
     * @return A string representing the start date and time of the event.
     */
    public String getFromDate() {
        return from.format(INPUT_FORMAT);
    }

    /**
     * Returns the end date/time of the event.
     *
     * @return A string representing the end date and time of the event.
     */
    public String getToDate() {
        return to.format(INPUT_FORMAT);
    }
}
