package todoapplication;

public class Task {
    private String title;
    private boolean completed;
    private long createdTime;

    public Task(String title) {
        this.title = title;
        this.completed = false;
        this.createdTime = System.currentTimeMillis();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    @Override
    public String toString() {
        return (completed ? "[DONE] " : "[TODO] ") + title;
    }
}