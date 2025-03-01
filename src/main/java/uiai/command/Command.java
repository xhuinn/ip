package uiai.command;

import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

public abstract class Command {
    protected String[] commandArgs;

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException;

    public boolean isExit() {
        return false;
    }
}
