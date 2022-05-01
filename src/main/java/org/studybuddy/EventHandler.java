package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EventHandler implements javafx.event.EventHandler<ActionEvent> {
    Stage stage;
    Button goToAssignments, goToTimer;
    Scene timer, assignments;

    public EventHandler(Stage stage, Button goToAssignments, Button goToTimer, Scene timer, Scene assignments) {
        this.stage = stage;
        this.goToAssignments = goToAssignments;
        this.goToTimer = goToTimer;
        this.timer = timer;
        this.assignments = assignments;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == goToAssignments) {
            stage.setScene(assignments);
        }
        if (event.getSource() == goToTimer) {
            stage.setScene(timer);
        }
    }
}
