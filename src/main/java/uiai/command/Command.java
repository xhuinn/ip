package uiai.command;

import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

/**
 * Represents an abstract command that can be executed in the application.
 * All specific command types should extend this class and implement the execute method.
 */
public abstract class Command {
    /**
     * Stores the arguments associated with the command.
     */
    protected String[] commandArgs;

    /**
     * Constructs a Command instance.
     * This constructor is primarily used by subclasses.
     */
    public Command() {
    }

    /**
     * Executes the command, performing the appropriate actions.
     *
     * @param tasks   The task list that the command may modify.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving/loading tasks.
     * @throws UiaiException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException;

    /**
     * Determines whether this command should terminate the application.
     * By default, commands do not cause the program to exit.
     * Subclasses can override this method if they should exit the program.
     *
     * @return {@code false} by default; overridden to {@code true} for exit commands.
     */
    public boolean isExit() {
        return false;
    }
}
