package uiai.command;

import uiai.exception.UiaiException;
import uiai.task.Event;
import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;

public class EventCommand extends Command {

    public EventCommand(String[] commandArgs) throws UiaiException {
        super();
        this.commandArgs = commandArgs;
    }


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        if (commandArgs.length < 2 || commandArgs[1].isBlank()) {
            throw UiaiException.incorrectEventFormat();
        }

        String[] description = commandArgs[1].split("/from", 2);

        if (description.length != 2 || description[0].isBlank() || description[1].isBlank()) {
            throw UiaiException.incorrectEventFormat();
        }

        String[] time = description[1].split("/to", 2);

        if (time.length != 2 || time[0].isBlank() || time[1].isBlank()) {
            throw UiaiException.incorrectEventFormat();
        }

        Event event = new Event(description[0].trim(), time[0].trim(), time[1].trim());

        tasks.addTask(event);

        ui.showMessage("Added this event!");
        ui.showMessage("\t" + event.toString());

        ui.showMessage("You now have " + tasks.size() + " tasks.");
    }
}
