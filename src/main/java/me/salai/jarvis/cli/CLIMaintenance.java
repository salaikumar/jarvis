package me.salai.jarvis.cli;/*
* CLI Maintenance
* Creates the command line options as needed.
* This class should be tested by using Command line.
*/

import me.salai.jarvis.Status;
import me.salai.jarvis.Task;
import me.salai.jarvis.Todo;
import org.apache.commons.cli.*;

import java.util.List;

public class CLIMaintenance{
        private Options options;
        private CommandLineParser parser;
        private CommandLine cmdLine;
        private HelpFormatter helpFormatter;
        Todo todo;

        // Constructor
        public CLIMaintenance(){
          options = new Options();
          parser =  new DefaultParser();
          helpFormatter = new HelpFormatter();
          todo = new Todo();
          generateOptions();
        }

        /*
        * Generate all Options
        */
        public void generateOptions(){
          /*
          * -s, --search <task desc>
          * return the list of tasks that matches the search task description
          */
          Option search = OptionBuilder.withArgName("task desc")
                                       .hasArg()
                                       .withDescription("Search for tasks using description")
                                       .withLongOpt("search")
                                       .create("s");
          options.addOption(search);
          /*
          * -a , --add <task desc>
          * adds a new task with the given task description
          */
          Option add = OptionBuilder.withArgName("task desc")
                                       .hasArg()
                                       .withDescription("Add the given task")
                                       .withLongOpt("add")
                                       .create("a");

          options.addOption(add);
          /*
          * -u , --update <task number>  <status>
          */
          Option update = OptionBuilder.withArgName(" task number> <status")
                                       .withValueSeparator(',')
                                       .hasArgs(2)
                                       .withLongOpt("update")
                                       .withDescription("Updates the given <task number> with given <status>")
                                       .create("u");
          options.addOption(update);
         /*
         * -all --all tasks
         * Display all tasks
         */
         Option all = OptionBuilder.withDescription("All Tasks")
                                   .withLongOpt("all tasks")
                                   .create("all");
         options.addOption(all);
         /*
         * -c --completed
         * Display all completed tasks
         */
         Option completed = OptionBuilder.withDescription("Completed Tasks")
                                   .withLongOpt("completed")
                                   .create("c");

         options.addOption(completed);
         /*
         * -ar --archived
         * Display all archived tasks
         */
         Option archive = OptionBuilder.withDescription("Archived Tasks")
                                   .withLongOpt("archived")
                                   .create("ar");

         options.addOption(archive);
         /*
         * -n --next
         * Display next list of tasks to be completed
         */
         Option next = OptionBuilder.withDescription("Next Tasks")
                                   .withLongOpt("next")
                                   .create("n");
         options.addOption(next);
         /*
         * -w --work
         * Display next list of tasks to be completed
         */
         Option work = OptionBuilder.withDescription("Currently Working tasks")
                                   .withLongOpt("work")
                                   .create("w");
         options.addOption(work);
        }

        /*
        * parse ()
        * parse the given input as per the options and print it
        */
        public void parseCommands(String[] args){
          try{
              cmdLine = parser.parse(options, args);
              if(cmdLine.hasOption('w')){
                  printList(todo.workingTasks());
              }
              else if (cmdLine.hasOption('n')){
                  printList(todo.nextTasks());
              }
              else if (cmdLine.hasOption("ar")){
                  printList(todo.archivedTasks());
              }
              else if (cmdLine.hasOption('c')){
                  printList(todo.completedTasks());
              }
              else if (cmdLine.hasOption("all")){
                  printList(todo.getAllTasks());
              }
              else if( cmdLine.hasOption('a')){
                  todo.addTask(cmdLine.getOptionValue('a'));
                  todo.save();
                  printList(todo.getAllTasks());
              }
              else if (cmdLine.hasOption('s')){
                  System.out.println(todo.isPresent(cmdLine.getOptionValue('s')));
              }
              else if (cmdLine.hasOption('u')){
                  String inputs[] = cmdLine.getOptionValues('u');
                  if (inputs.length < 2 || inputs.length > 2)
                      System.out.println("Required Arguments missing. Use -h to see it's usage");
                  else {
                      Status st = Status.valueOf(inputs[1].toUpperCase());
                      todo.updateStatus(Integer.parseInt(inputs[0]),st);
                      todo.save();
                  }
              }
          }catch(ParseException pe){
              helpFormatter.printHelp("jarvis",options);
          }

        }

        private void printList(List<Task> tasks) {
            if (tasks.isEmpty()){
                System.out.println("No Tasks");
                return;
            }
            System.out.println( "-----------------------------------------------------------------------------------------------");
            System.out.format( "%-15s %-50s %-5s","| Id","| Task","| Status" );
            System.out.print("\n-----------------------------------------------------------------------------------------------\n");
            for (Task task: tasks){
                System.out.format( "%-15s %-50s %-5s","| "+task.getId(),"| "+task.getTaskDescription(),"| "+task.getStatus());
                System.out.printf("%n");
                System.out.println("----------------------------------------------------------------------------------------------");
            }
        }

        public static void main(String[] args){
            CLIMaintenance cliMaintenance= new CLIMaintenance();
            cliMaintenance.parseCommands(args);
        }
}