package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Stack;

public class AssignmentsScene implements EventHandler<ActionEvent> {

    public static Scene getScene() {

//        // left column has navigation bar to go back to timer
//        Label sceneLabel = new Label("Assignment Tracker");
//        Button goToTimer = new Button("Study Timer");
//        goToTimer.setOnAction(App.goToTimerScene());
//        VBox leftColumn = new VBox(5);
//        leftColumn.getChildren().addAll(sceneLabel, goToTimer);
//        leftColumn.setMinWidth(200);
//
//        // middle column has list of assignments from AssignmentManager
//        Label assignmentsLabel = new Label("current assignments");
//        VBox middleColumn = new VBox(5);
//        middleColumn.getChildren().addAll(assignmentsLabel);
//        middleColumn.setMinWidth(300);
//
//        // right column has information about selected assignment
//        Label selectedAssignmentLabel = new Label("here we will display info about the selected assignment");
//        VBox rightColumn = new VBox(5);
//        rightColumn.setMinHeight(300);
//        rightColumn.getChildren().addAll(selectedAssignmentLabel);
//
//        // add columns to
//        GridPane gridPane = new GridPane();
//        gridPane.setPadding(new Insets(10,10,10,10));
//        gridPane.setVgap(10);
//        gridPane.setHgap(10);
//        gridPane.setGridLinesVisible(false);
//
//        GridPane.setConstraints(leftColumn, 0, 0);
//        GridPane.setConstraints(middleColumn, 1, 0);
//        GridPane.setConstraints(rightColumn, 2, 0);
//        gridPane.getChildren().addAll(leftColumn,middleColumn,rightColumn);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(leftPane());
        borderPane.setCenter(centerPane());
        borderPane.setRight(rightPane());
        return new Scene(borderPane, 1000, 560);
    }

    @Override
    public void handle(ActionEvent event) {
        // can handle timer-specific events here, or just use lambdas
    }

    private static Pane leftPane() {
        Pane pane = new Pane();
        Button goToTimer = new Button("Timer");
        Button assignment = new Button("Assignment");

        goToTimer.setOnAction(App.goToTimerScene());
        pane.getChildren().addAll(goToTimer);
        pane.getChildren().addAll(assignment);
        pane.setPrefWidth(220);
        pane.setPrefHeight(560);

        goToTimer.setFont(new Font("Regular", 25));
        goToTimer.setStyle("-fx-background-color:  rgb(183,183,183)");
        goToTimer.setPrefWidth(200);
        goToTimer.setPrefHeight(75);
        goToTimer.setLayoutX(8);
        goToTimer.setLayoutY(40);

        assignment.setFont(new Font("Regular", 25));
        assignment.setStyle("-fx-background-color:   rgb(217,217,217)");
        assignment.setPrefWidth(200);
        assignment.setPrefHeight(75);
        assignment.setLayoutX(8);
        assignment.setLayoutY(136);

        return pane;
    }

    private static Pane centerPane() {
        Pane pane = new Pane();
        Label label = new Label("Upcoming Assignments:");
        Button projectProposal = new Button("403 project proposal");
        Button lab = new Button("371 lab");
        Button assignment = new Button("writing assignment");
        Button addAssignment = new Button("Add Assignment");

        pane.getChildren().addAll(label, projectProposal, lab, assignment, addAssignment);
        pane.setPrefWidth(320);
        pane.setPrefHeight(560);

        label.setFont(new Font("Regular", 25));

        projectProposal.setFont(new Font("Regular", 25));
        projectProposal.setStyle("-fx-background-color:    rgb(123, 167, 216)");
        projectProposal.setPrefWidth(280);
        projectProposal.setPrefHeight(60);
        projectProposal.setLayoutX(9);
        projectProposal.setLayoutY(133);

        lab.setFont(new Font("Regular", 25));
        lab.setStyle("-fx-background-color:    rgb(166, 196, 230)");
        lab.setPrefWidth(280);
        lab.setPrefHeight(60);
        lab.setLayoutX(9);
        lab.setLayoutY(202);

        assignment.setFont(new Font("Regular", 25));
        assignment.setStyle("-fx-background-color:    rgb(166, 196, 230)");
        assignment.setPrefWidth(280);
        assignment.setPrefHeight(60);
        assignment.setLayoutX(9);
        assignment.setLayoutY(271);

        addAssignment.setFont(new Font("Regular", 25));
        addAssignment.setLayoutX(36);
        addAssignment.setLayoutY(452);

        return pane;
    }

    private static Pane rightPane() {
        Pane pane = new Pane();
        Label label = new Label("403 project proposal");
        Text estimate = new Text("Estimated 75% complete");
        Text due = new Text("Due:");
        Text timeSpent= new Text("Time spent so far:");
        Text timeRemaining = new Text("Estimated time remaining:");
        Text description = new Text("Make a proposal for a project for CSE 403, " +
                                    "including a written pdf and a video presentation.");
        Button projectProposal = new Button("Mark As Done");
        Button dueLabel = new Button();
        Button timeSpentLabel = new Button();
        Button timeRemainingLabel = new Button();
        Text dueValue = new Text("4/4");
        Text timeSpentValue = new Text("2:30");
        Text timeRemainingValue = new Text("1:00");

        pane.getChildren().addAll(label, estimate, due, dueLabel, dueValue, timeSpent, timeSpentLabel, timeSpentValue,
                                  timeRemaining, timeRemainingLabel, timeRemainingValue, description, projectProposal);
        pane.setPrefWidth(483);
        pane.setPrefHeight(560);

        label.setFont(new Font("Regular", 25));
        label.setLayoutX(50);
        label.setLayoutY(66);

        estimate.setFont(new Font("Regular", 17));
        estimate.setLayoutX(76);
        estimate.setLayoutY(116);

        due.setFont(new Font("Regular", 17));
        due.setLayoutX(76);
        due.setLayoutY(152);

        timeSpent.setFont(new Font("Regular", 17));
        timeSpent.setLayoutX(76);
        timeSpent.setLayoutY(188);

        timeRemaining.setFont(new Font("Regular", 17));
        timeRemaining.setLayoutX(76);
        timeRemaining.setLayoutY(224);

        description.setFont(new Font("Regular", 25));
        description.setLayoutX(40);
        description.setLayoutY(307);
        description.setWrappingWidth(427.6966552734375);

        projectProposal.setFont(new Font("Regular", 25));
        projectProposal.setLayoutX(130);
        projectProposal.setLayoutY(452);

        dueLabel.setPrefWidth(50);
        dueLabel.setPrefHeight(25);
        dueLabel.setLayoutX(340);
        dueLabel.setLayoutY(134);

        timeSpentLabel.setPrefWidth(50);
        timeSpentLabel.setPrefHeight(25);
        timeSpentLabel.setLayoutX(340);
        timeSpentLabel.setLayoutY(173);

        timeRemainingLabel.setPrefWidth(50);
        timeRemainingLabel.setPrefHeight(25);
        timeRemainingLabel.setLayoutX(340);
        timeRemainingLabel.setLayoutY(213);

        dueValue.setFont(new Font("Regular", 17));
        dueValue.setLayoutX(350);
        dueValue.setLayoutY(152);

        timeSpentValue.setFont(new Font("Regular", 17));
        timeSpentValue.setLayoutX(350);
        timeSpentValue.setLayoutY(192);

        timeRemainingValue.setFont(new Font("Regular", 17));
        timeRemainingValue.setLayoutX(350);
        timeRemainingValue.setLayoutY(232);

        return pane;
    }
}
