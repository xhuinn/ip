package uiai.command;

import uiai.task.TaskList;
import uiai.file.Storage;
import uiai.ui.Ui;
import uiai.exception.UiaiException;

/**
 * Represents a command to unmark a task as incomplete in the task list.
 * This command is used to mark a task as not done, reversing the mark as done status.
 */
public class UnmarkCommand extends Command {
    private final int unmarkIndex;

    /**
     * Constructs an UnmarkCommand with the specified command arguments.
     *
     * @param command The command arguments. The second argument should be the task index.
     * @throws UiaiException If the command arguments are invalid or incomplete.
     */
    public UnmarkCommand(String[] command) throws UiaiException {
        super();

        if (command.length < 2) {
            throw UiaiException.incorrectUnmarkFormat();
        }

        try {
            this.unmarkIndex = Integer.parseInt(command[1]) - 1;
        } catch (NumberFormatException e) {
            throw UiaiException.incorrectUnmarkFormat();
        }
    }

    /**
     * Executes the unmark task command by marking the specified task as not done.
     * If the task is already incomplete, it throws an exception.
     * It updates the task list and saves the changes.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws UiaiException If the task index is invalid, the task is already not done, or the task cannot be unmarked.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        if (unmarkIndex < 0 || unmarkIndex >= tasks.getTasks().size()) {
            throw UiaiException.invalidTaskNumber(tasks.getTasks().size());
        }

        if (!tasks.getTasks().get(unmarkIndex).isDone()) {
            throw UiaiException.unmarkedTask();
        }

        tasks.getTasks().get(unmarkIndex).markAsNotDone();
        ui.showMessage("Meow! I've marked this task as not done yet:");
        ui.showMessage("\t" + tasks.getTasks().get(unmarkIndex).toString());

        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            throw UiaiException.failSaveTasks();
        }
    }
}
