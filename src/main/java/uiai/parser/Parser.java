package uiai.parser;

import uiai.command.*;
import uiai.exception.UiaiException;

public class Parser {
    public static Command parse(String fullCommand) throws UiaiException {
        String[] command = fullCommand.split(" ", 2);
        return switch (command[0]) {
            case "bye" -> new ByeCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(command);
            case "unmark" -> new UnmarkCommand(command);
            case "deadline" -> new DeadlineCommand(command);
            case "todo" -> new TodoCommand(command);
            case "event" -> new EventCommand(command);
            case "delete" -> new DeleteCommand(command);
            default -> throw UiaiException.incorrectFormat();
        };
    }
}
