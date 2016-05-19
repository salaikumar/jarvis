# jarvis
Jarvis - Command line Todo tool
Inspired by the work of Hashnuke's J - https://github.com/HashNuke/j. Jarvis is a similar work.

## Usage
+ Clone the repo
+ ``` mvn clean package ```. Jarvis is ready to use.

## Features
```
java -jar target/jarvis-1.0-SNAPSHOT.jar --h
usage: jarvis
 -a,--add <task desc>                  Add the given task
 -all,--all tasks                      All Tasks
 -ar,--archived                        Archived Tasks
 -c,--completed                        Completed Tasks
 -n,--next                             Next Tasks
 -s,--search <task desc>               Search for tasks using description
 -u,--update < task number> <status>   Updates the given <task number>
                                       with given <status>
 -w,--work                             Currently Working tasks
```
### Add a Task
```
java -jar target/jarvis-1.0-SNAPSHOT.jar -a "Complete Jarvis TODO" 
-----------------------------------------------------------------------------------------------
| Id            | Task                                             | Status
-----------------------------------------------------------------------------------------------
| 1723477387    | Complete Jarvis TODO                             | N  
----------------------------------------------------------------------------------------------
| 356293784     | Get Started with your 90 days Code Sprint.       | W  
----------------------------------------------------------------------------------------------
| -1149713343   | Start Working on Dashboard Application           | N  
----------------------------------------------------------------------------------------------
| -876354855    | Learn React JS and Node JS, Java Clean Code      | N  
----------------------------------------------------------------------------------------------
| -1299783908   | 1.README update for Jarvis 2.Search Task as Regex  | N  
----------------------------------------------------------------------------------------------
```
### Update a Task 
To update the status of a given task, use any of the below with the Task Id
```
java -jar target/jarvis-1.0-SNAPSHOT.jar -u 1723477387 W
```
<B>Status</B>
+ W - Working on
+ C - Completed
+ A - Archive
+ N - Next to be worked on

### Search for a Task
Search is yet to be completed.You can search using Task description as of now
```
java -jar target/jarvis-1.0-SNAPSHOT.jar -s "Complete Jarvis TODO"
true
```
*Note: The description should be an exact match. Watch for an update soon on this*
### List Tasks
You can list tasks based on its status. 
```
java -jar target/jarvis-1.0-SNAPSHOT.jar -all
-----------------------------------------------------------------------------------------------
| Id            | Task                                             | Status
-----------------------------------------------------------------------------------------------
| 1723477387    | Complete Jarvis TODO                             | W  
----------------------------------------------------------------------------------------------
| 356293784     | Get Started with your 90 days Code Sprint.       | W  
----------------------------------------------------------------------------------------------
| -1149713343   | Start Working on Dashboard Application           | N  
----------------------------------------------------------------------------------------------
| -876354855    | Learn React JS and Node JS, Java Clean Code      | N  
----------------------------------------------------------------------------------------------
```
Similarly, you can use -W to list currently working tasks.

## TODO
+ Update Search Feature to keep it simple
+ Add Tags to Tasks
+ Support user defined Task Status

