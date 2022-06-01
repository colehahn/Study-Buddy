package org.studybuddy;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Controller Class for the scene laid out by "Assignment Details UI.fxml",
 * which is a scene that allows the user to enter details about a new assignment,
 * or display details from some other assignment
 */
public class AssignmentDetailsController {

    // References to on-screen UI elements

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

    /**
     * This method is called whenever the scene is loaded. It leaves everything default, but forces
     * numeric fields to be integers only, since parsing poorly-formatted time will give errors at runtime
     */
    @FXML
    public void initialize () {
        // force the fields to be numeric only
        forceNumeric(hoursLeft);
        forceNumeric(minutesLeft);
        forceNumeric(hoursSpent);
        forceNumeric(minutesSpent);
    }

    /**
     * Forces the text in the given TextField to be numeric only.
     * This is used for the time fields where the user types time spent/remaining on an assignment
     */
    private void forceNumeric (TextField text) {
        // TODO: in the future, it would probably be smart to make it so that the minutes fields can only be < 60
        // or deal with converting this to reduced form in makeAssignmentFromDisplay
        text.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                text.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    // TODO: once the timer is capable of keeping track of what assignment is being worked on, we can add
    // a button that goes to the timer scene and sets the current assignment to this one
    public void workOnAssignment(AssignmentClass assignment) {
        AssignmentsPageController.goToTimer();
        //.setCurrentAssignment(assignment);
    }

    /**
     * Method that is called when the "Add assignment" button is pressed
     */
    public void addAssignment() {
        // get pass current assignment information to AssignmentsPageController::addAssignmentAndRefresh
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.addAssignmentAndRefresh(makeAssignmentFromDisplay());
    }

    /**
     * Returns an assignment created from the contents of the display at that time.
     * Works whether the user is inputting an assignment or just viewing a saved one
     */
    private AssignmentClass makeAssignmentFromDisplay() {
        String name = assignmentName.getText();
        String due = dueDate.isVisible() ? dueDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/uuuu"))
                                         : dueDateText.getText();
        String estimateToFinish = hoursLeft.getText() + ":" + minutesLeft.getText() + ":00";
        String timeSpent = hoursSpent.getText() + ":" + minutesSpent.getText() + ":00";
        String description = assignmentDescription.getText();

        return new AssignmentClass(name, description, estimateToFinish, timeSpent, due);
    }

    /**
     * Sets the scene to "display mode", meaning it is showing the details for the given assignment.
     * Sets the appropriate fields to the values stored in the assignment, makes them not editable,
     * makes an edit button, and sets the bottom button to "mark as done"
     */
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
            // TODO: style stuff should be moved to stylesheet.css
            // here maybe you can do something like editable.setStyleClass(uneditable)
            editable.setStyle("-fx-background-color: rgb(217,217,217)");
        }
        assignmentDescription.setEditable(false);
        assignmentDescription.setStyle("text-area-background: rgb(217,217,217)");

        // add edit button
        editButton.setVisible(true);
        editButton.setOnAction(e -> editAssignment());
    }

    /**
     * method called when the "mark as done" button is clicked.
     * Forwards to AssignmetnsPageController::removeAssignment to remove the assignment from the list
     */
    @FXML
    public void markDone() {
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.removeAssignment(assignmentName.getText());
    }

    /**
     * Method called when the "edit" button is pressed.
     * Makes the fields editable again, changes "mark as done" button to "save" button,
     * and changes "edit" button to "cancel" button
     */
    @FXML
    public void editAssignment() {
        // save previous assignment
        AssignmentClass oldAssignment = makeAssignmentFromDisplay();

        // make things editable again
        for (TextInputControl editable : new TextInputControl[]{assignmentName, hoursSpent, minutesSpent, hoursLeft, minutesLeft}) {
            editable.setEditable(true);
            // TODO: move style to stylesheet.css
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

    /**
     * Method called when the "save" button is clicked.
     * Forwards to AssignmentsPageController to remove the old assignment and add the new one
     */
    @FXML
    public void Save(AssignmentClass oldAssignment, AssignmentClass newAssignment) {
        // remove old assignment
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.removeAssignment(oldAssignment.getName());
        controller.addAssignmentAndRefresh(newAssignment);
        displayAssignment(newAssignment);
    }

}
