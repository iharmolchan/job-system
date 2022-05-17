# job-system

App runs on port 8088 by default.

### The Task:
Create a basic job system that can schedule and execute jobs of different types.
The users of this system will be another developers and their main objective is to create
different job definitions which they can run asynchronously.

### Requirements:
#### Language: Java 8+
#### Jobs Definitions:
- The system should support custom job types. The different job types will be defined
by the developers who use this system.
- A job should have a life-cycle that can be tracked i.e. we need to track the job's
current state - running, failed etc.
#### Scheduling:
- Jobs can be scheduled for periodic execution (every 1, 2, 6, or 12 hours).
- Jobs can be executed immediately for a one time run.
#### Concurrency:
- Jobs need to run concurrently. A job should not wait for a previous job to finish
before it can run. You can assume that there are no dependencies between any two
jobs.
- There should be a limit on the amount of jobs that can run concurrently at any given
moment. If the limit is reached the pending job(s) should wait for an open spot.

## Valuable endpoints

http://localhost:8090/swagger - Open API description for the app. Some endpoints contain clarifications
of usage.

http://localhost:8090/h2-console - SQL console to check data in default db - H2
