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

    }

    public void goToTimer() {
         App.goToTimerScene();
    }

    public void addAssignmentUI() {

    }

    public void addAssignment() {

    }
}
