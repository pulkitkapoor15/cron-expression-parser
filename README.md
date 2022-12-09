### Technical Task - Cron Expression Parser

The project is a command line application or script which parses a cron string and expands each field
to show the times at which it will run.

### Stack used
This is a maven project.
The project can be built using standard maven command `mvn clean install`. The command generates a jar file `cron-parser-1.0-SNAPSHOT.jar` in the /target folder.
Maven compiler - `1.8`

### How to run the project 

To run the project, building the same is important using above. The jar generated is used to run the project using the following command template 
`$ java -jar cron-parser-1.0-SNAPSHOT.jar "<cron expression> <command to execute>"`

The cron should be passed in the following format: [minute] [hour] [day of month] [month] [day of week]

The command to execute should be separate string showing the path of executable. 

The following example can clear things a bit more - 
`java -jar target/cron-parser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"`

The output for the above is:
```
        minute       0 15 30 45  
          hour                0  
  day of month             1 15  
         month 1 2 3 4 5 6 7 8 9 10 11 12  
   day of week        1 2 3 4 5  
       command     /usr/bin/find
```

### What have we done 

We have a common parser that scans through the string and sends the control to parser manager. The parser manager follows a strategy pattern and calls different methods as per the token.
The different algorithms are for different tokens - 
- Bound parser, for bounded intervals, eg 1-5.
- Exact Time Token, for giving an exact time, eg 1, 12, means different times of 1 and 12 as per the position of the token.
- Interval Token, for having the command run at specific intervals as per the token, eg */12 means every 12 minutes if this happens as a first token.
- Number Token, for having one single time, eg 1.
- Star token, for having the command as every single interval of the token. eg * for month, will mean running it every month.

### Unit tests

- The unit tests are token based and check for the methods in the token to ensure there is correctness and ensure any changes in the regex matching go through the unit tests to ensure no quality checks fail.
- There is a main cron parser test which covers the data for the response and checks for the outputs to see if they match.

