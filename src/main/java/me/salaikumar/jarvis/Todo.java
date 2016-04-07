package me.salaikumar.jarvis;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Todo
 * Helps to Create, Delete, Mark, Archive tasks
 */
public class Todo {
    private File todoFile;
    private List<Task> tasks;
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
        else{
            // Read the data from the file and generate the tasks

        }
    }

    public void addTask(String description){
        // Generate a random int for the task id.
    }

    public void updateStatus(int id,Character status){

    }

    private void updateFile(){

    }
}
