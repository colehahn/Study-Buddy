package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TimerScene implements EventHandler<ActionEvent> {

    public static Scene getScene() {
        Label label1 = new Label("Study Timer");
        Button goToAssignments = new Button("Assignments");
        goToAssignments.setOnAction(App.goToAssignmentsScene());
        VBox timerLayout = new VBox(50);
        timerLayout.getChildren().addAll(label1, goToAssignments);
        // timerLayout.setStyle("-fx-background-color: BEIGE;"); an example of inline CSS. can also add a .css file
        return new Scene(timerLayout, 1000, 800);
    }

    @Override
    public void handle(ActionEvent event) {
        // can handle timer-specific events here, or just use lambdas
    }
}
