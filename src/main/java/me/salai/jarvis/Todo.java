package me.salai.jarvis;

import java.io.*;
import java.util.*;

/**
 * Todo
 * Helps to Create, Delete, Mark, Archive tasks
 */
public class Todo {

    private File todoFile;
    private Map<Integer,Task> tasks;
    private Map<String, Integer> taskDescription;
    private Random random;

    /*
     * Check if a named TODO.txt exists
     * Location. User's home/documents.
     * Else, create one and keep the file instance.
     */
    public Todo(){
        String homeDirPath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "TODO.text";
        todoFile  = new File(homeDirPath);
        random    = new Random(17);
        tasks     = new HashMap<Integer, Task>();
        taskDescription = new HashMap<String, Integer>();

        if (!todoFile.exists()){
            try {
                todoFile.createNewFile();
                /*
                * Solving an Interesting Case.
                * When File is empty, Object InputStream instance creation throws an EOF Exception
                * In order to avoid it, Just create and ObjectOutputStream and flush it.
                */
                FileOutputStream fileOutputStream = new FileOutputStream(todoFile.getAbsolutePath());
                ObjectOutputStream outputPrint = new ObjectOutputStream(fileOutputStream);
                outputPrint.flush();

            } catch (IOException e) {
                throw new RuntimeException("TODO file cannot be created.");
            }
        }
        else {
            populateMaps();
        }
    }

    private void populateMaps() {
        Task newTask = null;
        try {

            FileInputStream inputStream = new FileInputStream(todoFile.getAbsolutePath());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            while(true){
                try {
                    newTask = (Task) objectInputStream.readObject();
                }catch (EOFException e){
                    break;
                }
                tasks.put(newTask.getId(),newTask);
                taskDescription.put(newTask.getTaskDescription(),newTask.getId());
            }
            objectInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // FixMe --> 1.Ignore Case, 2. Search for Substring. 3.Search using Regex Pattern Match. 4. Return a list
    public boolean isPresent(String taskDesc){
        return taskDescription.containsKey(taskDesc);
    }

    public void addTask(String description){
        // Generate a random number that is not present already
        int randId;
        do {
            randId  = random.nextInt();
        }while( tasks.get(randId) != null);
        tasks.put(randId,new Task(randId,description));
        taskDescription.put(description,randId);
    }

    public void updateStatus(int id, Status status){
        Task task = tasks.get(id);
        task.setStatus(status);
        tasks.put(id,task);
    }

    /*
     * get all the tasks from the hash table
     */
    public List<Task> getAllTasks(){
        ArrayList<Task> allTasks = new ArrayList<Task>();
        Set<Integer> keys = tasks.keySet();
        for (Integer key : keys){
            allTasks.add(tasks.get(key));
        }
        return allTasks;
    }

    /*
     * Get all Completed Tasks
     */
    public List<Task> completedTasks(){
        ArrayList<Task> completedTasks = new ArrayList<Task>();
        Set<Integer> keys = tasks.keySet();
        for (Integer key : keys){
            Task task =  tasks.get(key);
            if (task.getStatus() == Status.C)
                completedTasks.add(task);
        }
        return completedTasks;
    }

    /*
     * Get all Archived Tasks
     */
    public List<Task> archivedTasks(){
        ArrayList<Task> archivedTasks = new ArrayList<Task>();
        Set<Integer> keys = tasks.keySet();
        for (Integer key : keys){
            Task task =  tasks.get(key);
            if (task.getStatus() == Status.A)
                archivedTasks.add(task);
        }
        return archivedTasks;
    }

    /*
     * Get Next Target Tasks
     */
    public List<Task> nextTasks(){
        ArrayList<Task> nextTasks = new ArrayList<Task>();
        Set<Integer> keys = tasks.keySet();
        for (Integer key : keys){
            Task task =  tasks.get(key);
            if (task.getStatus() == Status.N)
                nextTasks.add(task);
        }
        return nextTasks;
    }

    /*
     * Get current working on tasks
     */
    public List<Task> workingTasks(){
        ArrayList<Task> workingTasks = new ArrayList<Task>();
        Set<Integer> keys = tasks.keySet();
        for (Integer key : keys){
            Task task =  tasks.get(key);
            if (task.getStatus() == Status.W)
                workingTasks.add(task);
        }
        return workingTasks;
    }

    /*
     * Saves the changes to the file
     */
    public void save(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(todoFile.getAbsolutePath());
            ObjectOutputStream outputPrint = new ObjectOutputStream(fileOutputStream);
            Set<Integer> keySet = tasks.keySet();
            for (Integer i : keySet){
                outputPrint.writeObject(tasks.get(i));
            }
            outputPrint.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
