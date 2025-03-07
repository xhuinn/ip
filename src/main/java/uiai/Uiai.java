package uiai;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import uiai.command.Command;
import uiai.parser.Parser;

import uiai.exception.UiaiException;

import uiai.ui.Ui;
import uiai.file.Storage;
import uiai.task.TaskList;

/**
 * The main class for the Uiai application.
 * The application provides a task manager where users can add, remove, mark, and unmark tasks.
 * The tasks are saved to and loaded from a file.
 */
public class Uiai {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private static final String FILE_PATH = String.valueOf(Paths.get(System.getProperty("user.dir"), "uiai.txt"));

    /**
     * Constructs a Uiai object and initializes the UI, Storage, and TaskList.
     * If an error occurs while loading tasks from the file, an empty task list is used.
     */
    public Uiai() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main loop of the Uiai application.
     * Reads commands from the user, parses them, and executes the corresponding actions.
     * The loop continues until an exit command is given.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        Scanner in = new Scanner(System.in);

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(in);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (UiaiException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * The main method that runs the Uiai application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Uiai().run();
    }
}