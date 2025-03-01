package uiai.command;

import uiai.task.TaskList;
import uiai.task.Todo;
import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.exception.UiaiException;

public class TodoCommand extends Command {

    public TodoCommand(String[] commandArgs) throws UiaiException {
        if (commandArgs.length < 2 || commandArgs[1].isBlank()) {
            throw UiaiException.incorrectTodoFormat();
        }
        this.commandArgs = commandArgs;
    }

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
