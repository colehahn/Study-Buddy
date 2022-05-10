package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Stack;

public class AssignmentsScene implements EventHandler<ActionEvent> {

    public static Scene getScene() {

        // left column has navigation bar to go back to timer
        Label sceneLabel = new Label("Assignment Tracker");
        Button goToTimer = new Button("Study Timer");
        goToTimer.setOnAction(App.goToTimerScene());
        VBox leftColumn = new VBox(5);
        leftColumn.getChildren().addAll(sceneLabel, goToTimer);
        leftColumn.setMinWidth(200);

        // middle column has list of assignments from AssignmentManager
        Label assignmentsLabel = new Label("current assignments");
        VBox middleColumn = new VBox(5);
        middleColumn.getChildren().addAll(assignmentsLabel);
        middleColumn.setMinWidth(300);

        // right column has information about selected assignment
        Label selectedAssignmentLabel = new Label("here we will display info about the selected assignment");
        VBox rightColumn = new VBox(5);
        rightColumn.setMinHeight(300);
        rightColumn.getChildren().addAll(selectedAssignmentLabel);

        // add columns to
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);

        GridPane.setConstraints(leftColumn, 0, 0);
        GridPane.setConstraints(middleColumn, 1, 0);
        GridPane.setConstraints(rightColumn, 2, 0);
        gridPane.getChildren().addAll(leftColumn,middleColumn,rightColumn);

        return new Scene(gridPane, 1000, 800);
    }

    @Override
    public void handle(ActionEvent event) {
        // can handle timer-specific events here, or just use lambdas
    }
}
