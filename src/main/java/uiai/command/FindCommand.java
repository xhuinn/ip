package uiai.command;

import uiai.task.Task;
import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

import java.util.ArrayList;

/**
 * The FindCommand class represents a command to search for tasks containing a specific keyword.
 * It finds all tasks that match the given keyword and displays them to the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object with the specified command arguments.
     * If the command does not contain a keyword, an exception is thrown.
     *
     * @param command the command arguments
     * @throws UiaiException if the command is in an incorrect format or missing a keyword
     */
    public FindCommand(String[] command) throws UiaiException {
        if (command.length < 2) {
            throw UiaiException.incorrectFindFormat();
        }
        this.keyword = command[1].trim().toLowerCase();
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     * If matching tasks are found, they are displayed to the user.
     * If no matching tasks are found, a message is displayed indicating no matches were found.
     *
     * @param tasks   the list of tasks to search through
     * @param ui      the user interface for displaying messages to the user
     * @param storage the storage for saving and loading tasks (not used in this command)
     * @throws UiaiException if an error occurs while processing the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
            return;
        }

        ui.showMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            ui.showMessage((i + 1) + "." + matchingTasks.get(i));
        }
    }
}
