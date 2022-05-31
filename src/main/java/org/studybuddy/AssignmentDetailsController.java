package org.studybuddy;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.addAssignment(makeAssignmentFromDisplay());
    }

    private AssignmentClass makeAssignmentFromDisplay() {
        String name = assignmentName.getText();
        String due = dueDate.isVisible() ? dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/uuuu"))
                                         : dueDateText.getText();
        String estimateToFinish = hoursLeft.getText() + ":" + minutesLeft.getText() + ":00";
        String timeSpent = hoursSpent.getText() + ":" + minutesSpent.getText() + ":00";
        String description = assignmentDescription.getText();

        return new AssignmentClass(name, description, estimateToFinish, timeSpent, due);
    }

    @FXML
    public void displayAssignment(AssignmentClass assigment) {
        // display assignment information
        assignmentName.setText(assigment.getName());
        completionText.setText("estimated ??% completed");
        dueDate.setVisible(false);
        dueDateText.setVisible(true);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        dueDateText.setText(df.format(assigment.getDuedate()));
        String[] hoursMinutesSecondsLeft = assigment.getEstimateToFinish().split(":");
        String[] hoursMinutesSecondsSpent = assigment.getTimeSpent().split(":");
        hoursSpent.setText(hoursMinutesSecondsSpent[0]);
        minutesSpent.setText(hoursMinutesSecondsSpent[1]);
        hoursLeft.setText(hoursMinutesSecondsLeft[0]);
        minutesLeft.setText(hoursMinutesSecondsLeft[1]);
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

        // add edit button
        editButton.setVisible(true);
        editButton.setOnAction(e -> editAssignment());
    }

    @FXML
    public void markDone() {
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.removeAssignment(assignmentName.getText());
    }

    @FXML
    public void editAssignment() {
        // save previous assignment
        AssignmentClass oldAssignment = makeAssignmentFromDisplay();

        // make things editable again
        for (TextInputControl editable : new TextInputControl[]{assignmentName, hoursSpent, minutesSpent, hoursLeft, minutesLeft}) {
            editable.setEditable(true);
            editable.setStyle("-fx-background-color: white");
        }
        assignmentDescription.setEditable(true);
        assignmentDescription.setStyle("text-area-background: white");
        dueDateText.setVisible(false);
        dueDate.setVisible(true);
        dueDate.setValue(oldAssignment.getDuedate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        // change "mark as done" button to "Save"
        bottomButton.setText("Save");
        bottomButton.setOnAction(e -> Save(oldAssignment, makeAssignmentFromDisplay()));

        // change "edit" button to "cancel"
        editButton.setText("Cancel");
        editButton.setOnAction(e -> displayAssignment(oldAssignment));

    }

    @FXML
    public void Save(AssignmentClass oldAssignment, AssignmentClass newAssignment) {
        // remove old assignment
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.removeAssignment(oldAssignment.getName());
        controller.addAssignment(newAssignment);
        displayAssignment(newAssignment);
    }

    @FXML
    public void handleExit() {
        //when the user leaves the app, we want to save their assignments
    }
}
