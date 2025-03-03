package uiai.command;

import uiai.task.TaskList;
import uiai.task.Todo;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

/**
 * Represents a command to add a Todo task to the task list.
 */
public class TodoCommand extends Command {

    /**
     * Constructs a TodoCommand with the specified command arguments.
     *
     * @param commandArgs The command arguments. The second argument should be the task description.
     * @throws UiaiException If the command arguments are invalid or incomplete.
     */
    public TodoCommand(String[] commandArgs) throws UiaiException {
        if (commandArgs.length < 2 || commandArgs[1].isBlank()) {
            throw UiaiException.incorrectTodoFormat();
        }
        this.commandArgs = commandArgs;
    }

    /**
     * Executes the add todo task command by creating a new Todo task and adding it to the task list.
     * Displays a message with the newly added task and updates the task count.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws UiaiException If the task description is invalid or incomplete.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UiaiException {
        if (this.commandArgs == null || this.commandArgs.length < 2) {
            throw UiaiException.incorrectTodoFormat();
        }

        String description = commandArgs[1];
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);

        ui.showMessage("Meow! I've added this task:");
        ui.showMessage("\t" + newTodo.toString());
        ui.showMessage("Now you have " + tasks.getTasks().size() + " tasks in your list.");

        try {
            storage.saveTasks(tasks.getTasks());
        } catch (Exception e) {
            ui.showError("Failed to save tasks after adding todo.");
        }
    }
}
