public class UiaiException extends Exception {
    public UiaiException(String message) {
        super(message);
    }

    public static UiaiException invalidTaskNumber(int maxTasks) {
        return new UiaiException("Invalid task number. Total tasks: " + maxTasks);
    }

    public static UiaiException noCommand() {
        return new UiaiException("Meow? I don't understand... Please enter a command.");
    }

}
