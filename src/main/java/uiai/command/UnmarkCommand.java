package uiai.command;

import uiai.task.TaskList;
import uiai.file.Storage;
import uiai.ui.Ui;
import uiai.exception.UiaiException;

public class UnmarkCommand extends Command {
    private final int unmarkIndex;

    public UnmarkCommand(String[] command) throws UiaiException {
        super();

        if (command.length < 2) {
            throw UiaiException.invalidTaskNumber(0);
        }

        try {
            this.unmarkIndex = Integer.parseInt(command[1]) - 1;
        } catch (NumberFormatException e) {
            throw UiaiException.invalidTaskNumber(0);
        }
    }

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
            ui.showError("Failed to save tasks after unmarking.");
        }
    }
}
