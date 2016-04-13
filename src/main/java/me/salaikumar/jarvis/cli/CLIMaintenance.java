/*
* CLI Maintenance
* Creates the command line options as needed.
* This class should be tested by using Command line.
*/

// Should be optimized soon
import org.apache.commons.cli.*;

public class CLIMaintenance{
    private Options options;
    private CommandLineParser parser;
    private CommandLine cmdLine;

    // Constructor
    public CLIMaintenance(){
      options = new Options();
      parser =  new CommandLineParser();
      cmdLine = new CommandLine();
      generateOptions();
    }

    /*
    * Generate all Options
    */
    public generateOptions(){

      /*
      * -h --help
      * Display all possible Options
      */
      Option help = OptionBuilder.withDescription("Jarvis: I understand the below commands master")
                                .withLongOpt("help")
                                .create("h");
      /*
      * -s, --search <task desc>
      * return the list of tasks that matches the search task description
      */
      Option search = OptionBuilder.withArgName("task desc")
                                   .hasArg()
                                   .withDescription("Search for tasks using description")
                                   .withLongOpt("search")
                                   .create("s");

      /*
      * -a , --add <task desc>
      * adds a new task with the given task description
      */
      Option add = OptionBuilder.withArgName("task desc")
                                   .hasArg()
                                   .withDescription("Add the given task")
                                   .withLongOpt("add")
                                   .create("a");


      /*
      * -u , --update <task number>  <status>
      */
      Option update = OptionBuilder.withArgName(" task number> <status")
                                   .withValueSeperator(' ')
                                   .hasArgs(2)
                                   .withLongOpt("update")
                                   .withDescription("Updates the given <task number> with given <status>")
                                   .create("u");

     /*
     * -all --all tasks
     * Display all tasks
     */
     Option all = OptionBuilder.withDescription("All Tasks")
                               .withLongOpt("all tasks")
                               .create("all");

     /*
     * -c --completed
     * Display all completed tasks
     */
     Option all = OptionBuilder.withDescription("Completed Tasks")
                               .withLongOpt("completed")
                               .create("c");


     /*
     * -a --archived
     * Display all archived tasks
     */
     Option all = OptionBuilder.withDescription("Completed Tasks")
                               .withLongOpt("completed")
                               .create("c");

    }
}
