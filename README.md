# hoovering-robot-task


# PROBLEM STATEMENT


## Introduction
You will write a service that navigates an imaginary robotic hoover (much like a Roomba) through an equally imaginary room based on:

- room dimensions as X and Y coordinates, identifying the top right corner of the room rectangle. This room is divided up in a grid based on these dimensions; a room that has dimensions X: 5 and Y: 5 has 5 columns and 5 rows, so 25 possible hoover positions. The bottom left corner is the point of origin for our coordinate system, so as the room contains all coordinates its bottom left corner is defined by X: 0 and Y: 0.
- locations of patches of dirt, also defined by X and Y coordinates identifying the bottom left corner of those grid positions.
- an initial hoover position (X and Y coordinates like patches of dirt)
- driving instructions (as cardinal directions where e.g. N and E mean "go north" and "go east" respectively)

The room will be rectangular, has no obstacles (except the room walls), no doors and all locations in the room will be clean (hoovering has no effect) except for the locations of the patches of dirt presented in the program input.

Placing the hoover on a patch of dirt ("hoovering") removes the patch of dirt so that patch is then clean for the remainder of the program run. The hoover is always on - there is no need to enable it.

Driving into a wall has no effect (the robot skids in place).


## Goal
The goal of the service is to take the room dimensions, the locations of the dirt patches, the hoover location and the driving instructions as input and to then output the following:

- The final hoover position (X, Y)
- The number of patches of dirt the robot cleaned up

The service must persist every input and output to a database.


## Input
Program input will be received in a json payload with the format described here.

Example:

```javascript
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```


## Output
Service output should be returned as a json payload.

Example (matching the input above):

```javascript
{
  "coords" : [1, 3],
  "patches" : 1
}
```

Where `coords` are the final coordinates of the hoover and patches is the number of cleaned patches.


## Deliverable
The service:

- is a web service
- must run on Mac OS X or Linux (x86-64)
- must be written in any of the languages that we support with our SDKs (Java, C#, Python, Ruby, PHP, Node, Go)
- can make use of any existing open source libraries that don't directly address the problem statement (use your best judgement).

Send us:

- The full source code, including any code written which is not part of the normal program run (scripts, tests)
- Clear instructions on how to obtain and run the program
- Please provide any deliverables and instructions using a public Github (or similar) Repository as several people will need to inspect the solution


## Evaluation
The point of the exercise is for us to see some of the code you wrote (and should be proud of).

We will especially consider:

- Code organisation
- Quality
- Readability
- Actually solving the problem

This test is based on the following gist https://gist.github.com/alirussell/9a519e07128b7eafcb50


# SOLUTION NOTES


## Run Configuration
+ please run the com.example.Application class as the entry point of the app
+ run & build with Java 11


## Endpoint
+ exposed: [POST] http://localhost:8080/api/v1/instructions
+ sample input:
    ```
    {
      "roomSize" : [4, 4],
      "coords" : [0, 0],
      "patches" : [
        [0, 2],
        [1, 1],
        [1, 3],
        [2, 0],
        [2, 2],
        [3, 0],
        [3, 1]
      ],
      "instructions" : "NNNEEESSWWSENES"
    }
    ```

+ expected result:
    ```
    {
        "coords": [
            3,
            0
        ],
        "patches": 6
    }
    ```


## Assumptions:
+ only valid input is being stored in DB
+ have not attempted to link stored input with the given run's result (this could be done via common run-id or via foreign key constraint)


## Persistance
+ have exposed the h2 console on http://localhost:8080/h2
+ username and password: ```sa```
+ URL: ```jdbc:h2:mem:testdb```
+ sample queries:
    ```
    select * from INSTRUCTION_AUDIT;
    select * from ROBOT_CLEANING_RESULT;
    ```
+ dirt patch locations stored as CSV (in the format: x:y,x1:y1), e.g. ```1:2,5:0``` 
