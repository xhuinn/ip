package uiai.parser;

import uiai.command.FindCommand;
import uiai.command.*;
import uiai.exception.UiaiException;

/**
 * A utility class responsible for parsing user input into appropriate command objects.
 * The parser identifies the command type based on the user's input and returns the corresponding
 * Command object for execution.
 */
public class Parser {
    /**
     * Parses the given input string to determine the appropriate command.
     *
     * <p>The method splits the input string to extract the command and its arguments. Based on the command
     * keyword, a corresponding Command object is instantiated and returned. If the command is invalid or unrecognized,
     * a UiaiException is thrown.</p>
     *
     * <p>Supported commands include:</p>
     * <ul>
     *     <li>"bye" -> ByeCommand</li>
     *     <li>"list" -> ListCommand</li>
     *     <li>"mark" -> MarkCommand</li>
     *     <li>"unmark" -> UnmarkCommand</li>
     *     <li>"deadline" -> DeadlineCommand</li>
     *     <li>"todo" -> TodoCommand</li>
     *     <li>"event" -> EventCommand</li>
     *     <li>"delete" -> DeleteCommand</li>
     *     <li>"find" -> FindCommand</li>
     * </ul>
     *
     * @param fullCommand The full command entered by the user, typically consisting of a command keyword
     *                    followed by its arguments.
     * @return The corresponding Command object based on the input.
     * @throws UiaiException If the command format is incorrect or the command is unrecognized.
     */
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
            case "find" -> new FindCommand(command);
            default -> throw UiaiException.incorrectFormat();
        };
    }
}
