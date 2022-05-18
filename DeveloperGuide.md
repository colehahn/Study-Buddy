
# Developer Guide (How to Contribute):

## How to obtain source code
* Clone the repo.

## Layout of directory structure
 	* The overall file structure of our repository is explained in the [top-level README] (README.md). 
* The source code for our project is all in `src/main`
    * `src/main/java` includes all of our backend java classes (`AssignmentClass`, `AssignmentManager`, `Timer`) as well as the classes that set up and display the application’s frontend (`App`, `AssignmentsScene`, `TimerScene`)
    * `src/main/resources` holds any additional files required, like the logo image or saved user data
      * `src/test/java` holds all of the tests for our java classes

## How to build the software.
*  To build the software, clone our repository from https://github.com/colehahn/Study-Buddy. Run ‘maven compile’.

## How to test the software
* Go to where the maven tests are located, and then click on run all tests (`mvn test`)

## How to add new tests
* The name of test files should be the name of the class that will be tested followed by the word ‘Test’.


## How to build a release of the software.
* to build a release, you should first increment the `fileVersion` and `productVersion` tags at the bottom of `pom.xml` <work in progress: we will make these constants in the file so that they are easily accessible at the top of the file>
* After updating the version number, run `mvn package`. This will create a Windows executable file, along with a few other files, in `Study-Buddy\target\Study-Buddy\` <only on auto_deploy branch for now, you will need to be on a windows machine>
* <work in progress: to then indicate that this is a published release, you simply need to copy the contents of this directory into the `download` directory, which is where users will get it from>


