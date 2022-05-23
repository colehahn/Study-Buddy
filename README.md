# Study-Buddy
Hi, welcome to Study Buddy! \
Study Buddy is a Windows application that helps users keep track of their time to work on homework assignments and tasks. The two core features are a study timer and an assignment tracker. The study timer, inspired by the pomodoro method of studying, will allow the user to input how long they want their focus and break times to be, and will continue alternating between those two times. The assignment tracker will contain a list of all assignments that the student needs to complete, and the student can keep track of their due dates and each task’s progress and estimated time to complete.

[Google Doc (living document)](https://docs.google.com/document/d/1R582gmYngv6BbP5c-UfUz10P5_Q-NITTTkpt5H63j0k/edit?usp=sharing) \
[Developer Guide](DeveloperGuide.md) \
[User Manual](UserManual.md) 

## Overview of Repository
  * `src/` contains all of the project source code. Split up into main and test
  * `releases/` contains our downloadable releases of the app
  * `pom.xml` contains the configuration for our maven project, which helps with automatically downloading dependancies and running tests
  * `reports/` contains our weekly status reports
  * `.github/workflows/` sets up infrastructure needed for mvn package to be run whenever code is pushed to the main branch
  * `.gitignore` currently we ignore any files outputted by maven (the target directory)

## How to Build/Test/Run the System
  * running `mvn test` will build and run our tests
    * alternatively you can run `mvn compile` to build without running tests
  * to run the application, you must run the main method found in `src/main/java/org.studybuddy/App.java`

## Current Version
Beta release: our application currently has the functionality required to view assignments and start the study timer.\
Future releases will extend the functionality of the assignment tracker (add, edit, and mark assignments as completed) as well as the timer (study periods/break periods, edit duration, and play a sound when complete).


