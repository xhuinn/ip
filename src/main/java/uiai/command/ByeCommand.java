package uiai.command;

import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

/**
 * Represents a command to exit the application.
 * When executed, it displays a farewell message to the user.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand instance.
     */
    public ByeCommand(String[] command) throws UiaiException {
        super();

        if (command.length > 1) {
            throw UiaiException.incorrectByeFormat();
        }
    }

    /**
     * Executes the ByeCommand by displaying a farewell message to the user.
     *
     * @param tasks   The current task list (not used in this command).
     * @param ui      The user interface to display messages.
     * @param storage The storage handler (not used in this command).
     * @throws UiaiException If an error occurs during execution (unlikely for this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        ui.showMessage("Meow! Bye! Hope you had a meow-tastic time!");
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return {@code true}, as executing this command should terminate the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
