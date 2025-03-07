package uiai.exception;

/**
 * Custom exception class for handling errors in the task management system.
 * This class extends the standard {@link Exception} class and provides specific
 * error messages related to incorrect task inputs and operations.
 */
public class UiaiException extends Exception {
    /**
     * Constructs a new UiaiException with the specified detail message.
     *
     * @param message The detail message for this exception.
     */
    public UiaiException(String message) {
        super(message);
    }

    /**
     * Creates an exception indicating that the task number provided is invalid.
     *
     * @param maxTasks The total number of tasks in the list.
     * @return A new {@link UiaiException} with an error message indicating the invalid task number.
     */
    public static UiaiException invalidTaskNumber(int maxTasks) {
        return new UiaiException("Invalid task number. Total tasks: " + maxTasks);
    }

    /**
     * Creates an exception indicating that the user's input format is incorrect.
     *
     * @return A new {@link UiaiException} with an error message indicating incorrect format.
     */
    public static UiaiException incorrectFormat() {
        return new UiaiException("Meow? I don't understand... Please enter in correct format.");
    }

    /**
     * Creates an exception indicating that a task is already marked as done.
     *
     * @return A new {@link UiaiException} with an error message indicating the task is already marked.
     */
    public static UiaiException markedTask() {
        return new UiaiException("Another task! It's already marked...");
    }

    /**
     * Creates an exception indicating that a task is already unmarked.
     *
     * @return A new {@link UiaiException} with an error message indicating the task is already unmarked.
     */
    public static UiaiException unmarkedTask() {
        return new UiaiException("Another task! It's already unmarked...");
    }

    /**
     * Creates an exception indicating that the deadline format is incorrect.
     *
     * @return A new {@link UiaiException} with an error message indicating incorrect deadline format.
     */
    public static UiaiException incorrectDeadlineFormat() {
        return new UiaiException("Meow? Please input in this format: deadline [task] /by [date,time]");
    }

    /**
     * Creates an exception indicating that the todo format is incorrect.
     *
     * @return A new {@link UiaiException} with an error message indicating incorrect todo format.
     */
    public static UiaiException incorrectTodoFormat() {
        return new UiaiException("Meow? Please input in this format: todo [task]");
    }

    /**
     * Creates an exception indicating that the find format is incorrect.
     *
     * @return A new {@link UiaiException} with an error message indicating incorrect find format.
     */
    public static UiaiException incorrectFindFormat() {
        return new UiaiException("Meow? Please input in this format: find [task]");
    }

    /**
     * Creates an exception indicating that the event format is incorrect.
     *
     * @return A new {@link UiaiException} with an error message indicating incorrect event format.
     */
    public static UiaiException incorrectEventFormat() {
        return new UiaiException("Meow? Please input in this format: event [task] /from [date,time] /to [date,time]");
    }
}
