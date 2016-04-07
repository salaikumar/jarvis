package me.salaikumar.jarvis;

public class Task {
    private String taskDescription;
    private Status status;
    private int id;

    public Task(int id, String taskDescription){
        this.taskDescription = taskDescription;
        this.status = Status.C;
        this.id = id;
    }

    public Task(int id, String taskDescription, Status status) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (!taskDescription.equals(task.taskDescription)) return false;
        return status == task.status;

    }

    @Override
    public int hashCode() {
        int result = taskDescription.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        String statusText = null;
        switch (status){
            case A:
                statusText = "Archived";
                break;
            case C:
                statusText = "Completed";
                break;
            case W:
                statusText = "Working on";
                break;
            case N:
                statusText = "Next Target";
                break;
            default:
                statusText = "Some Stage";
        }
        return  id + "--" + taskDescription  + "--" + status ;
    }
}
