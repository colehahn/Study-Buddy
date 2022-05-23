
# Developer Guide (How to Contribute):

## How to obtain source code
* Clone the repo.

## Layout of directory structure
* The overall file structure of our repository is explained in the [top-level README](README.md). 
* The source code for our project is all in `src/main`
    * `src/main/java` includes all of our backend java classes (`AssignmentClass`, `AssignmentManager`, `Timer`) as well as the classes that set up and display the application’s frontend (`App`, `AssignmentsScene`, `TimerScene`)
    * `src/main/resources` holds any additional files required, like the logo image or saved user data
      * `src/test/java` holds all of the tests for our java classes

## How to build the software.
*  To build the software, clone our repository from https://github.com/colehahn/Study-Buddy. Run `mvn compile`.

## How to test the software
* Go to where the maven tests are located, and then click on run all tests (`mvn test`)

## How to add new tests
* The name of test files should be the name of the class that will be tested followed by the word ‘Test’.


## How to build a release of the software.
* to build a release, you should first increment the `fileVersion` and `productVersion` tags at the bottom of `pom.xml` <work in progress: we will make these constants in the file so that they are easily accessible at the top of the file>
* Next, all you need to do is commit to the `main` branch. Whenever code is committed to `main`, Github Actions automatically does all the work to add a new downloadable file to the `releases` directory


