package org.studybuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {

    private AssignmentManager assignments;

    @FXML
    public BorderPane borderPane;

    @FXML
    public Pane leftPane;

    @FXML
    public VBox middlePane;

    @FXML
    public VBox rightPane;

    @FXML
    public Button timerButton;

    @FXML
    public Button assignmentsPageButton;

    @FXML
    public Button assignment1;

    @FXML
    public Button assignment2;

    @FXML
    public Button assignment3;

    @FXML
    public Button assignment4;

    @FXML
    public Button assignment5;

    @FXML
    public Button assignment6;

    @FXML
    public Button assignment7;

    @FXML
    public Button assignment8;

    @FXML
    public Button assignment9;

    @FXML
    public Button addAssignmentUIButton;

    @FXML
    public TextArea assignmentName;

    @FXML
    public TextArea dueDate;

    @FXML
    public TextArea predictedTime;

    @FXML
    public TextArea assignmentDescription;

    @FXML
    public Button addAssignmentButton;

    public void initialize() {
        this.assignments = new AssignmentManager();
    }

    public void goToTimer() {
         App.goToTimerScene();
    }

    public void addAssignment() throws Exception {
        //validate name, due, time, description
        //create new assignment
        //add to assignments
        //check assignment buttons and find next available slot
        //update slot with name of assignment

        String name = assignmentName.getText();
        String due = dueDate.getText();
        String estimateToFinish = predictedTime.getText();
        String description = assignmentDescription.getText();

        if (assignments.containsAssignment(name)) {
            System.out.println("Assignment with name " + name + " already exists");
            return;
        }
        assignments.addAssignment(name, description, estimateToFinish, due);
        if (assignment1.getText() == "") {
            assignment1.setText(name);
        } else if (assignment2.getText() == "") {
            assignment2.setText(name);
        } else if (assignment3.getText() == "") {
            assignment3.setText(name);
        } else if (assignment4.getText() == "") {
            assignment4.setText(name);
        } else if (assignment5.getText() == "") {
            assignment5.setText(name);
        } else if (assignment6.getText() == "") {
            assignment6.setText(name);
        } else if (assignment7.getText() == "") {
            assignment7.setText(name);
        } else if (assignment8.getText() == "") {
            assignment8.setText(name);
        } else if (assignment9.getText() == "") {
            assignment9.setText(name);
        }

    }
}
