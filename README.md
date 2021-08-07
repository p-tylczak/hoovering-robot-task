# hoovering-robot-task


# Run Configuration
+ please run the com.example.Application class as the entry point of the app
+ run & build with Java 11


# Endpoint
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


# Assumptions:
+ only valid input is being stored in DB
+ have not attempted to link stored input with the given run's result (this could be done via common run-id or via foreign key constraint)


# Persistance
+ have exposed the h2 console on http://localhost:8080/h2
+ username and password: ```sa```
+ URL: ```jdbc:h2:mem:testdb```
+ sample queries:
    ```
    select * from INSTRUCTION_AUDIT;
    select * from ROBOT_CLEANING_RESULT;
    ```
+ dirt patch locations stored as CSV (in the format: x:y,x1:y1), e.g. ```1:2,5:0``` 
