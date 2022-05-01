package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TimerScene {

    public static Scene timerScene(ActionEvent event, Button goToAssignments) {
        Label label1 = new Label("Study Timer");
        goToAssignments = new Button("Assignments");
        goToAssignments.setOnAction((EventHandler<ActionEvent>) event);
        VBox timerLayout = new VBox(50);
        timerLayout.getChildren().addAll(label1, goToAssignments);
        return new Scene(timerLayout, 640, 800);
    }
}
