package uiai.command;

import uiai.task.TaskList;
import uiai.file.Storage;
import uiai.ui.Ui;
import uiai.exception.UiaiException;

public class DeleteCommand extends Command {
    private final int deleteTaskIndex;

    public DeleteCommand(String[] commandArgs) throws UiaiException {
        super();
        if (commandArgs.length < 2) {
            throw UiaiException.invalidTaskNumber(0);
        }

        try {
            this.deleteTaskIndex = Integer.parseInt(commandArgs[1]) - 1;
        } catch (NumberFormatException e) {
            throw UiaiException.invalidTaskNumber(0);
        }
    }

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
            ui.showError("Failed to save tasks after deletion.");
        }
    }
}
