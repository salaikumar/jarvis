package me.salaikumar.jarvis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Todo
 * Helps to Create, Delete, Mark, Archive tasks
 */
public class Todo {
    private File todoFile;
    private HashMap<Integer,Task> tasks;
    /*
     * Check if a named TODO.txt exists
     * Location. User's home/documents.
     * Else, create one and keep the file instance.
     */
    public Todo(){
        String homeDirPath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TODO.text";
        todoFile  = new File(homeDirPath);
        if (!todoFile.exists()){
            try {
                todoFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("TODO file cannot be created.");
            }
        }
        else {
            List<String> lines;
            // Read the data from the file and generate the tasks
            try {
                lines = Files.readAllLines(todoFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Unable to read file");
            }

            for (String lineItem : lines) {
                String[] items = lineItem.split("--");
                Status status = null;
                int id = Integer.parseInt(items[0]);
                if (items[2].equals("A")) {
                    status = Status.A;
                } else if (items[2].equals("C")) {
                    status = Status.C;

                } else if (items[2].equals("W")) {
                    status = Status.W;

                } else if (items[2].equals("N")) {
                    status = Status.N;
                }
                tasks.put(id,new Task(id,items[1],status));
            }
        }
    }

    public void addTask(String description){
        // Generate a random int for the task id.
        Random random = new Random(17);
        int randId = random.nextInt();
        tasks.put(randId,new Task(randId,description));
    }

    public void updateStatus(int id,Character status){
        Task task = tasks.get(id);
        Status s =null;
        switch (status){
            case 'A':
                s = Status.A;
                break;
            case 'C':
                s = Status.C;
                break;
            case 'W':
                s = Status.W;
                break;
            case 'N':
                s = Status.N;
                break;
        }
        task.setStatus(s);
        tasks.put(id,task);
    }

    /*
     * Open the file in write mode , update data and close it
     */
    private void persistTask(){
        FileWriter fileObj = null;

        try {
            fileObj = new FileWriter(todoFile.getName());
            fileObj.write("");
            List<Task> allTasks = (List<Task>) tasks.values();
            for (Task task : allTasks){
                fileObj.write(task.toString());
            }
            fileObj.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
