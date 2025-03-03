package uiai.command;

import uiai.task.Task;
import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String[] command) throws UiaiException {
        if (command.length < 2) {
            throw UiaiException.incorrectFindFormat();
        }
        this.keyword = command[1].trim().toLowerCase();
    }

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
