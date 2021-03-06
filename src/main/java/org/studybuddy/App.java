package org.studybuddy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.Objects;
import java.util.Timer;

/**
 * The main JavaFX App entry point
 * sets up the window and provides references to each Scene (Timer and Assignments)
 * to allow for swapping and communicating between them
 **/
public class App extends Application {
    static Stage window;
    static Scene timer, assignments;
    static FXMLLoader assignmentsSceneLoader;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        // scene 1, timer page
        timer = TimerScene.getScene(new AssignmentManager());

        // scene 2, assignments
        try {
            assignmentsSceneLoader = new FXMLLoader(getClass().getClassLoader().getResource("Assignments UI.fxml"));
            assignments = new Scene(assignmentsSceneLoader.load());
        } catch(Exception e) {
            e.printStackTrace();
        }

        window.setScene(timer);
        window.setTitle("Study Buddy");
        window.getIcons().add(new Image("SB logo.png"));
        window.show();
    }


    public static void main(String[] args) {
        launch();
    }

    /**
     * Methods for swapping between scenes:
     */

    public static EventHandler<ActionEvent> goToAssignmentsScene() {
        return ((e -> window.setScene(assignments)));
    }

    public static void goToTimerScene(AssignmentManager assignments) {
        timer = TimerScene.getScene(assignments);
        window.setUserData(assignments);
        window.setScene(timer);
    }

}