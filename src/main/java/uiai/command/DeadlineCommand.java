package uiai.command;

import uiai.exception.UiaiException;
import uiai.task.Deadline;
import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String[] commandArgs) {
        super();
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        if (commandArgs.length < 2 || commandArgs[1].isBlank()) {
            throw UiaiException.incorrectDeadlineFormat();
        }

        String[] description = commandArgs[1].split("/by", 2);

        if (description.length != 2 || description[0].isBlank() || description[1].isBlank()) {
            throw UiaiException.incorrectDeadlineFormat();
        }

        String taskDescription = description[0].trim();
        String deadlineTime = description[1].trim();

        Deadline deadlineTask = new Deadline(taskDescription, deadlineTime);

        tasks.addTask(deadlineTask);

        ui.showMessage("Added this deadline task!");
        ui.showMessage("\t" + deadlineTask.toString());

        ui.showMessage("You now have " + tasks.size() + " tasks.");
    }
}
