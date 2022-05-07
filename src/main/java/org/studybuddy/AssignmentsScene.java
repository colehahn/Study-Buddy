package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AssignmentsScene implements EventHandler<ActionEvent> {

    public static Scene getScene() {
        Label label2 = new Label("Assignment Tracker");
        Button goToTimer = new Button("Study Timer");
        goToTimer.setOnAction(App.goToTimerScene());
        VBox assLayout = new VBox(50);
        assLayout.getChildren().addAll(label2, goToTimer);
        return new Scene(assLayout, 640, 800);
    }

    @Override
    public void handle(ActionEvent event) {
        // can handle timer-specific events here, or just use lambdas
    }
}
