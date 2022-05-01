package org.studybuddy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;

/**
 * JavaFX App
 **/
public class App extends Application implements EventHandler<ActionEvent> {
    Stage window;
    Scene timer, assignments;
    Button goToAssignments;
    Button goToTimer;
    @Override
    public void start(Stage stage) {
        window = stage;
//        org.studybuddy.EventHandler handler = new org.studybuddy.EventHandler(
//                window, goToAssignments, goToTimer, timer, assignments);


        // scene 1, timer page
        timer = TimerScene.timerScene(new ActionEvent(), goToAssignments);

        // scene 2, assignments
        Label label2 = new Label("Assignment Tracker");
        goToTimer = new Button("Study Timer");
        goToTimer.setOnAction(this);
        VBox assLayout = new VBox(50);
        assLayout.getChildren().addAll(label2, goToTimer);
        assignments = new Scene(assLayout, 640, 800);

        window.setScene(timer);
        window.setTitle("Study Buddy!");
        window.show();
    }
    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == goToAssignments) {
            window.setScene(assignments);
        }
        if (event.getSource() == goToTimer) {
            window.setScene(timer);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}