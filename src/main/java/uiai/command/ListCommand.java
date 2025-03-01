package uiai.command;

import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

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

