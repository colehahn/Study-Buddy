package org.studybuddy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        // scene 1, timer page
        Label label1 = new Label("Study Timer");
        goToAssignments = new Button("Assignments");
        goToAssignments.setOnAction(this);

        VBox timerLayout = new VBox();
        timerLayout.getChildren().addAll(goToAssignments, label1);

        timer = new Scene(timerLayout, 640, 800);



        // scene 2, assignments
        Label label2 = new Label("Assignment Tracker");
        goToTimer = new Button("Study Timer");
        goToTimer.setOnAction(this);

        VBox assLayout = new VBox();
        assLayout.getChildren().addAll(goToTimer, label2);

        assignments = new Scene(assLayout, 640, 800);


        window.setScene(timer);
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