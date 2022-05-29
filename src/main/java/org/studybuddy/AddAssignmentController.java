package org.studybuddy;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class AddAssignmentController {

    @FXML
    public DatePicker dueDate;

    @FXML
    public TextField assignmentName, hours, minutes;

    @FXML
    public TextArea assignmentDescription;

    @FXML
    public Button addAssignmentButton;

    @FXML
    public void initialize () {
        // force the field to be numeric only
        hours.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    hours.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        minutes.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    minutes.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

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
        String due = dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/uuuu"));

        String estimateToFinish = hours.getText() + ":" + minutes.getText() + ":00";
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
