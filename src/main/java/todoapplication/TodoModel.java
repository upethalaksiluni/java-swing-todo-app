package todoapplication;

import java.util.ArrayList;
import java.util.List;

public class TodoModel {
    private List<Task> tasks;

    public TodoModel() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        if (task != null && !task.getTitle().trim().isEmpty()) {
            tasks.add(task);
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    public void updateTask(int index, String newTitle) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setTitle(newTitle);
        }
    }

    public void toggleTaskCompletion(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setCompleted(!task.isCompleted());
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public int getTaskCount() {
        return tasks.size();
    }
}