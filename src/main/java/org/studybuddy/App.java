package org.studybuddy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;

/**
 * JavaFX App
 **/
public class App extends Application {
    static Stage window;
    static Scene timer, assignments;

    @Override
    public void start(Stage stage) {
        window = stage;


        // scene 1, timer page
        timer = TimerScene.getScene();

        // scene 2, assignments
        assignments = AssignmentsScene.getScene();

        window.setScene(timer);
        window.setTitle("Study Buddy");
        window.getIcons().add(new Image("SB logo.png"));
        window.show();
    }


    public static void main(String[] args) {
        launch();
    }

    public static EventHandler<ActionEvent> goToAssignmentsScene() {
        return ((e -> window.setScene(assignments)));
    }

    public static EventHandler<ActionEvent> goToTimerScene() {
        return ((e -> window.setScene(timer)));
    }

}