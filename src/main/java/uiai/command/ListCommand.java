package uiai.command;

import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the list command by displaying all tasks in the task list.
     * If the task list is empty, a message is displayed indicating that there are no tasks.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     * @throws UiaiException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        if (tasks.getTasks().isEmpty()) {
            ui.showMessage("Meow! Your task list is empty!");
            return;
        }

        ui.showMessage("Meow! Here are the tasks in your list:");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            ui.showMessage("\t" + (i + 1) + ". " + tasks.getTasks().get(i).toString());
        }
        ui.showMessage("You have a total of " + tasks.getTasks().size() + " task(s) in your list.");
    }
}

