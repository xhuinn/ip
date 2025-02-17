package uiai.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import uiai.task.Task;

public class TasksSaver {
    public static void saveTasks(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks) {
            String taskType = "";

            if (task instanceof uiai.command.Todo) {
                taskType = "T"; // Todo
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((uiai.command.Todo) task).description;
                writer.write(taskData + System.lineSeparator());
            } else if (task instanceof uiai.command.Deadline) {
                taskType = "D"; //Deadline
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((uiai.command.Deadline) task).description + " | " + ((uiai.command.Deadline) task).getByDate();
                writer.write(taskData + System.lineSeparator());
            } else if (task instanceof uiai.command.Event) {
                taskType = "E"; //Event
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((uiai.command.Event) task).description + " | " + ((uiai.command.Event) task).getFromDate() + " | " + ((uiai.command.Event) task).getToDate();
                writer.write(taskData + System.lineSeparator());
            }
        }

        writer.close();
    }
}
