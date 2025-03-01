package uiai.command;

import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        ui.showMessage("Meow! Bye! Hope you had a meow-tastic time!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
