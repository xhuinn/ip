package uiai.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import uiai.task.Deadline;
import uiai.task.Event;
import uiai.task.Task;
import uiai.task.Todo;

public class TasksSaver {
    public static void saveTasks(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task : tasks) {
            String taskType = "";

            if (task instanceof Todo) {
                taskType = "T"; // Todo
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Todo) task).description;
                writer.write(taskData + System.lineSeparator());
            } else if (task instanceof Deadline) {
                taskType = "D"; //Deadline
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Deadline) task).description + " | " + ((Deadline) task).getByDate();
                writer.write(taskData + System.lineSeparator());
            } else if (task instanceof Event) {
                taskType = "E"; //Event
                String taskData = taskType + " | " + (task.isDone() ? "1" : "0") + " | " + ((Event) task).description + " | " + ((Event) task).getFromDate() + " | " + ((Event) task).getToDate();
                writer.write(taskData + System.lineSeparator());
            }
        }
        writer.close();
    }
}
