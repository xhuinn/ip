package uiai.command;

import uiai.exception.UiaiException;
import uiai.task.Deadline;
import uiai.task.TaskList;
import uiai.ui.Ui;
import uiai.file.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to create a new deadline task.
 * The deadline task consists of a description and a due date/time.
 */
public class DeadlineCommand extends Command {
    /**
     * The expected date-time format for input parsing (dd/MM/yyyy HHmm).
     */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Constructs a DeadlineCommand with the given arguments.
     *
     * @param commandArgs The command arguments containing the task description and deadline.
     */
    public DeadlineCommand(String[] commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Executes the deadline command by parsing the input, creating a Deadline task,
     * and adding it to the task list.
     *
     * @param tasks   The task list where the new deadline task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving/loading tasks.
     * @throws UiaiException If the command format is incorrect or the date-time format is invalid.
     */
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

            ui.showMessage("Meow! I've added this deadline task!");
            ui.showMessage("\t" + deadlineTask);
            ui.showMessage("You now have " + tasks.size() + " tasks.");
            storage.saveTasks(tasks.getTasks());

        } catch (DateTimeParseException e) {
            throw UiaiException.invalidDateFormat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
