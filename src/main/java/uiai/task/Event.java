package uiai.task;

/**
 * Represents an event task. An event task has a description and a start and end date/time range for the event.
 * It extends from the {@link Task} class and provides functionality to represent the task as a string and retrieve
 * the start and end dates of the event.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, String from, String to) {
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
        return "[E]" + "[" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the start date/time of the event.
     *
     * @return A string representing the start date and time of the event.
     */
    public String getFromDate() {
        return from;
    }

    /**
     * Returns the end date/time of the event.
     *
     * @return A string representing the end date and time of the event.
     */
    public String getToDate() {
        return to;
    }
}
