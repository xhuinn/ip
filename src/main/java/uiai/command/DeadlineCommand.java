package uiai.command;

import uiai.exception.UiaiException;
import uiai.task.Deadline;
import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public DeadlineCommand(String[] commandArgs) {
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

        try {
            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineTime, INPUT_FORMAT);

            Deadline deadlineTask = new Deadline(taskDescription, deadlineDateTime);
            tasks.addTask(deadlineTask);

            ui.showMessage("Added this deadline task!");
            ui.showMessage("\t" + deadlineTask);
            ui.showMessage("You now have " + tasks.size() + " tasks.");
        } catch (DateTimeParseException e) {
            throw new UiaiException("Invalid date format! Please use dd/MM/yyyy HHmm (e.g., 02/12/2019 1800).");
        }
    }
}
