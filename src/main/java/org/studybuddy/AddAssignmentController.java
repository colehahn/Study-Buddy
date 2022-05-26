package org.studybuddy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AddAssignmentController {

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

    public void goToTimer() {
         App.goToTimerScene();
    }

    public void addAssignment() throws Exception {
        // TODO: validate name, due, time, description
        //create new assignment
        //add to assignments
        //check assignment buttons and find next available slot
        //update slot with name of assignment

        String name = assignmentName.getText();
        String due = dueDate.getText();
        String estimateToFinish = predictedTime.getText();
        String description = assignmentDescription.getText();

        //AssignmentsPageController.assignments.addAssignment(name, description, estimateToFinish, due);

        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.addAssignment(name, description, estimateToFinish, due);
    }

    public void assignment1UI() {

    }

    public void addAssignmentUI() {

    }

    @FXML
    public void handleExit() {
        //when the user leaves the app, we want to save their assignments
    }
}
