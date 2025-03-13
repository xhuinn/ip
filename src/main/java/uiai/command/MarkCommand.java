package uiai.command;

import uiai.task.TaskList;
import uiai.file.Storage;
import uiai.ui.Ui;
import uiai.exception.UiaiException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int markIndex;


    /**
     * Constructs a MarkCommand with the specified command arguments.
     *
     * @param command The command arguments. The second argument should be the task index.
     * @throws UiaiException If the command arguments are invalid.
     */
    public MarkCommand(String[] command) throws UiaiException {
        super();

        if (command.length < 2) {
            throw UiaiException.invalidTaskNumber(0);
        }

        try {
            this.markIndex = Integer.parseInt(command[1]) - 1;
        } catch (NumberFormatException e) {
            throw UiaiException.invalidTaskNumber(0);
        }
    }

    /**
     * Executes the mark task command by marking the specified task as done.
     * Displays a message with the updated task.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws UiaiException If the task index is invalid or the task is already marked as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        if (markIndex < 0 || markIndex >= tasks.getTasks().size()) {
            throw UiaiException.invalidTaskNumber(tasks.getTasks().size());
        }

        if (tasks.getTasks().get(markIndex).isDone()) {
            throw UiaiException.markedTask();
        }

        tasks.getTasks().get(markIndex).markAsDone();
        ui.showMessage("Meow! I've marked this task as done:");
        ui.showMessage("\t" + tasks.getTasks().get(markIndex).toString());

        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            throw UiaiException.failSaveTasks();
        }
    }
}
