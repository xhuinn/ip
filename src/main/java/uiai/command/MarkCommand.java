package uiai.command;

import uiai.task.TaskList;
import uiai.file.Storage;
import uiai.ui.Ui;
import uiai.exception.UiaiException;

public class MarkCommand extends Command {
    private final int markIndex;

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
            ui.showError("Failed to save tasks after marking.");
        }
    }
}
