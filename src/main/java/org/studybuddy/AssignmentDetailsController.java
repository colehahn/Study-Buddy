package org.studybuddy;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class AssignmentDetailsController {

    @FXML
    public DatePicker dueDate;

    @FXML
    public Text completionText, dueDateText;

    @FXML
    public TextField assignmentName, hoursLeft, minutesLeft, hoursSpent, minutesSpent;

    @FXML
    public TextArea assignmentDescription;

    @FXML
    public Button bottomButton, editButton;

    @FXML
    public void initialize () {
        // force the fields to be numeric only
        forceNumeric(hoursLeft);
        forceNumeric(minutesLeft);
        forceNumeric(hoursSpent);
        forceNumeric(minutesSpent);
    }

    private void forceNumeric (TextField text) {
        text.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                text.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void goToTimer() {
         App.goToTimerScene();
    }

    public void addAssignment() {
        //create new assignment
        //add to assignments
        //check assignment buttons and find next available slot
        //update slot with name of assignment

        String name = assignmentName.getText();
        String due = dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/uuuu"));

        String estimateToFinish = hoursLeft.getText() + ":" + minutesLeft.getText() + ":00";
        String timeSpent = hoursSpent.getText() + ":" + minutesSpent.getText() + ":00";
        String description = assignmentDescription.getText();

        //AssignmentsPageController.assignments.addAssignment(name, description, estimateToFinish, due);

        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        AssignmentClass newAssignment = new AssignmentClass(name, description, estimateToFinish, timeSpent, due);
        controller.addAssignment(newAssignment);
    }

    @FXML
    public void displayAssignment(AssignmentClass assigment) {
        // display assignment information
        assignmentName.setText(assigment.getName());
        completionText.setText("estimated ??% completed");
        dueDate.setVisible(false);
        dueDateText.setText(assigment.getDuedate().toString());
        String[] hoursMinutesSeconds = assigment.getEstimateToFinish().split(":");
        hoursSpent.setText("00");
        minutesSpent.setText("00");
        hoursLeft.setText(hoursMinutesSeconds[0]);
        minutesLeft.setText(hoursMinutesSeconds[1]);
        assignmentDescription.setText(assigment.getDescription());

        // change button to "mark as done"
        bottomButton.setText("Mark As Done");
        bottomButton.setOnAction(e -> markDone());

        // make things not editable / change color?
        for (TextInputControl editable : new TextInputControl[]{assignmentName, hoursSpent, minutesSpent, hoursLeft, minutesLeft}) {
            editable.setEditable(false);
            editable.setStyle("-fx-background-color: rgb(217,217,217)");
        }
        assignmentDescription.setEditable(false);
        assignmentDescription.setStyle("text-area-background: rgb(217,217,217)");
        /*
        assignmentName.setEditable(false);
        hoursSpent.setEditable(false);
        minutesSpent.setEditable(false);
        hoursLeft.setEditable(false);
        minutesLeft.setEditable(false);
        assignmentDescription.setEditable(false);
*/
        // add an edit button
        editButton.setVisible(true);
    }

    @FXML
    public void markDone() {
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.removeAssignment(assignmentName.getText());
    }

    @FXML
    public void editAssignment(ActionEvent e) {
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.removeAssignment(assignmentName.getText());
    }

    @FXML
    public void handleExit() {
        //when the user leaves the app, we want to save their assignments
    }
}
