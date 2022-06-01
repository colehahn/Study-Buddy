
# Developer Guide (How to Contribute):

## How to obtain source code
* Clone the repo: `git clone git@github.com:colehahn/Study-Buddy.git`

## Layout of directory structure
````markdown
│   .gitignore
│   pom.xml                       #Maven configuration file to automatically import dependencies
│   README.md
│   UserManual.md
│   DeveloperGuide.md
│
├───.github                           
│   └───workflows                 #GitHub Actions config files to automatically run tests and build installer
├───.idea
├───releases                      #location of installer to install the app
├───reports
└───src
    ├───main
    │   ├───java
    │   │   └───org.studybuddy    #contains all the source code Java classes, for both the backend and frontend
    │   └───resources             #additional files required, including the fxml files that describe the UI layout
    └───test
        └───java                  #tests for Java classes
````

## How to build/test the software
* running `mvn compile` from the root directory will compile
* running `mvn test` from the root directory will compile and run tests
  * alternatively, in IntelliJ, you can navigate to the test folder and run tests individually


## How to Run the System
* `mvn javafx:run` will run the application, and it will open up in a window
* you can also run the main method in `src/main/java/org.studybuddy/App.java`


## How to add new tests
* The name of test files should be the name of the class that will be tested followed by the word ‘Test’.


## How to build a release of the software
* to build a new release, you should first increment the `fileVersion` and `productVersion` tags at the bottom of `pom.xml`
  * if you leave these numbers unchanged, the current release on github will be overwritten (currently there is only one version)
* Next, all you need to do is commit to the `main` branch. Whenever code is committed to `main`, Github Actions automatically does all the work to add a new downloadable file to the `releases` directory


## A note about JavaFX
When creating this app, none of the developers had used JavaFX before, and we found that the learning curve can be
somewhat steep. Here is some info about JavaFX as it applies to our project: \
JavaFX is a library for making GUIs in Java, similar to Swing, but more modern and potentially friendly if you know 
how to use it \
It allows you to add different *Nodes* to the *Stage* (screen). There is a very large variety of these Nodes 
predefined for you. In our project, some of the ones we used frequently were `Label`, `Button`, `TextField`, etc. \
These nodes then have particular attributes that can be set by calling methods on them (label has `setText()` and 
Button has `setOnAction()`, for example). \
To arrange these elements on the screen, there are things called layouts (which are also Nodes). Some useful layouts are 
`VBox` (arranges Nodes vertically), `HBox` (arranges Nodes horizontally), and `BorderPane` (splits the area into top, 
left, right, bottom, and middle, which can each contain different Nodes). Note that Since layouts are nodes, you can nest
layouts together (the timer page is a VBox which has many things, including an HBox with two of the buttons). \
This is the basics of JavaFX, but to make it easier to build layouts graphically, there is a tool called SceneBuilder,
which can be easily downloaded [here](https://gluonhq.com/products/scene-builder/). This provides a very easy-to-use
drag-and-drop interface for building potentially complex layouts. It allows you to directly edit the attributes of each 
Node you add It also allows you to add a *controller* file, which is a Java class whose methods can be referenced by Nodes 
in that scene. SceneBuilder takes your description of the layout (scene), and it creates a `.fxml` file. Then, to access
this Scene in your code, you simply use a tool called FXML loader, and then you never have to write the many many lines of
code that are needed to directly describe the layout.\
SceneBuilder was used for the assignments page layout, but not for the timer page, whose layout was described programmatically.

