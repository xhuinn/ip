package uiai.exception;

public class UiaiException extends Exception {
    public UiaiException(String message) {
        super(message);
    }

    public static UiaiException invalidTaskNumber(int maxTasks) {
        return new UiaiException("Invalid task number. Total tasks: " + maxTasks);
    }

    public static UiaiException incorrectFormat() {
        return new UiaiException("Meow? I don't understand... Please enter in correct format.");
    }

    public static UiaiException markedTask() {
        return new UiaiException("Another task! It's already marked...");
    }

    public static UiaiException unmarkedTask() {
        return new UiaiException("Another task! It's already unmarked...");
    }

    public static UiaiException incorrectDeadlineFormat() {
        return new UiaiException("Meow? Please input in this format: deadline [task] /by [date,time]");
    }

    public static UiaiException incorrectTodoFormat() {
        return new UiaiException("Meow? Please input in this format: todo [task]");
    }

    public static UiaiException incorrectFindFormat() {
        return new UiaiException("Meow? Please input in this format: find [task]");
    }

    public static UiaiException incorrectEventFormat() {
        return new UiaiException("Meow? Please input in this format: event [task] /from [date,time] /to [date,time]");
    }
}
