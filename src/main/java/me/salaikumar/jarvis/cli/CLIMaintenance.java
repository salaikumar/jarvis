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
    private HelpFormatter helpFormatter;

    // Constructor
    public CLIMaintenance(){
      options = new Options();
      parser =  new CommandLineParser();
      cmdLine = new CommandLine();
      helpFormatter = new HelpFormatter();
      generateOptions();
    }

    /*
    * Generate all Options
    */
    public generateOptions(){
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
                                   .withValueSeperator(' ')
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

    


}
