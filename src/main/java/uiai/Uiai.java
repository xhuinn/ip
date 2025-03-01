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

public class Uiai {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private static final String FILE_PATH = String.valueOf(Paths.get(System.getProperty("user.dir"), "uiai.txt"));

    public Uiai() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

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

    public static void main(String[] args) {
        new Uiai().run();
    }
}