package uiai.command;

import uiai.task.TaskList;
import uiai.file.Storage;
import uiai.ui.Ui;
import uiai.exception.UiaiException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to be deleted.
     */
    private final int deleteTaskIndex;

    /**
     * Constructs a DeleteCommand with the given command arguments.
     *
     * @param commandArgs The command arguments containing the task index to delete.
     * @throws UiaiException If the task index is missing or not a valid number.
     */
    public DeleteCommand(String[] commandArgs) throws UiaiException {
        super();
        if (commandArgs.length < 2) {
            throw UiaiException.incorrectDeleteFormat();
        }
        try {
            this.deleteTaskIndex = Integer.parseInt(commandArgs[1]) - 1;
        } catch (NumberFormatException e) {
            throw UiaiException.incorrectDeleteFormat();
        }
    }

    /**
     * Executes the delete command by removing the specified task from the task list
     * and updating the storage file.
     *
     * @param tasks   The task list from which the task will be removed.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving/loading tasks.
     * @throws UiaiException If the task index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        if (deleteTaskIndex < 0 || deleteTaskIndex >= tasks.getTasks().size()) {
            throw UiaiException.invalidTaskNumber(tasks.getTasks().size());
        }

        ui.showMessage("Meow! I've removed this task:");
        ui.showMessage("\t" + tasks.getTasks().get(deleteTaskIndex).toString());
        tasks.removeTask(deleteTaskIndex);

        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks in your list.");

        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            throw UiaiException.failSaveTasks();
        }
    }
}
