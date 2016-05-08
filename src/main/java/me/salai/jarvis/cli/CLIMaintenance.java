package me.salai.jarvis.cli;/*
* CLI Maintenance
* Creates the command line options as needed.
* This class should be tested by using Command line.
*/

// Should be optimized soon
import me.salai.jarvis.Todo;
import org.apache.commons.cli.*;

public class CLIMaintenance{
    private Options options;
    private CommandLineParser parser;
    private CommandLine cmdLine;
    private HelpFormatter helpFormatter;
    Todo todo;

    // Constructor
    public CLIMaintenance(){
      options = new Options();
      parser =  new DefaultParser();             //CommandLineParser(); - this is an abstract class.
      helpFormatter = new HelpFormatter();
      todo = new Todo();
      generateOptions();
    }

    /*
    * Generate all Options
    */
    public void generateOptions(){
      /*
      * -h --help
      * Display all possible Options
      * Might be replaced with help formatter.-- Need to check
      */
      Option help = OptionBuilder.withDescription("Jarvis: I understand the below commands master")
                                .withLongOpt("help")
                                .create("h");
      options.addOption(help);
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
                                   .withValueSeparator(' ')
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
      }catch(ParseException pe){
         pe.printStackTrace();
      }
      //FixME --> Is this the only way I can Query for Options? Check API
      //FixME --> Check how it prints a list by default. else, you need write code for it
      // FixME --> Check for Exceptions at points -- 1. Arguments passed when not required
//                                                -- 2. Arguments not passed when required
      if(cmdLine.hasOption('w')){
          System.out.println(todo.workingTasks());
      }

      if (cmdLine.hasOption('n')){
          System.out.println(todo.nextTasks());
      }

      if (cmdLine.hasOption("ar")){
          System.out.println(todo.archivedTasks());
      }

      if (cmdLine.hasOption('c')){
          System.out.println(todo.completedTasks());
      }

      if (cmdLine.hasOption("all")){
          System.out.println(todo.getAllTasks());
      }

      if( cmdLine.hasOption('a')){

        todo.addTask(cmdLine.getOptionValue('a'));
        // If needed print all Tasks
      }

      if (cmdLine.hasOption('s')){
          System.out.println(todo.isPresent(cmdLine.getOptionValue('s')));
      }
    }
    // Let this class be the face to the app.
    // No need of one more driver class
    public static void main(String[] args){
        // Add the options
        CLIMaintenance cliMaintenance= new CLIMaintenance();
        cliMaintenance.parseCommands(args);
    }
}