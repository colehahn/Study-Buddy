package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;

/**
 * Controller for the top-level assignments page, which consists of a borderPane whose
 * middle Node is the list of assignments
 */

public class AssignmentsPageController {

    // this is the assignmentManager that holds a list of the user's assignments
    public static AssignmentManager assignments;

    // references to elements laid out by the fxml

    @FXML
    public BorderPane borderPane;

    @FXML
    public Button addAssignmentUIButton;

    @FXML
    public VBox assignmentsList;

    @FXML
    public void initialize() {
        // create AssignmentManager object, which is/should be auto-populated
        // with assignments that have been saved to the save file
        assignments = new AssignmentManager();
        try {
            // Loop through the assignments and add a button for each one.
            // Because AssignmentManager's iterator returns assignments in order,
            // the buttons will be added in that order
            for (AssignmentClass assignment : assignments) {
                addAssignmentButton(assignment);
            }
            // always set the left side of the screen to be the menu bar
            borderPane.setLeft(FXMLLoader.load(getClass().getClassLoader().getResource("Menu Bar.fxml")));
            // set the right side of the screen to be a blank Pane until something is clicked,
            // this helps with alignment
            Pane right = new Pane();
            right.setPrefHeight(560);
            right.setPrefWidth(480);
            borderPane.setRight(right);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds the given assignment to the AssignmentManager, and refreshes the list of buttons
     * so that the added assigment is displayed in its proper order
     */
    public void addAssignmentAndRefresh(AssignmentClass toAdd) {
        assignments.addAssignment(toAdd);
        assignmentsList.getChildren().clear();
        for (AssignmentClass assignment : assignments) {
            addAssignmentButton(assignment);
        }
    }

    /**
     * Adds a button for the given assignment to the list of assignments.
     * When clicked the button will open up an AssignmentDetailsUI in the right of the borderPane
     * that shows that assignments details/information
     */
    public void addAssignmentButton(AssignmentClass assignment) {
        // TODO: the assignment should not be added to the AssignmentManager in this method.
        // probably this was why we were having duplicates in the save file
        assignments.addAssignment(assignment);

        // create a new button
        Button newButton = new Button(assignment.getName());
        // TODO: style things should be moved into stylesheet.css
        // here, you can just call newButton.setStyleClass(...)
        newButton.setFont(new Font("Regular", 25));
        newButton.setStyle("-fx-background-color:    rgb(166, 196, 230)");
        newButton.setPrefWidth(280);
        newButton.setPrefHeight(60);
        newButton.setLayoutX(9);
        newButton.setLayoutY(202);
        newButton.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Assignment Details UI.fxml"));
                borderPane.setRight(loader.load());
                // need to call displayAssignment so that the AssignmentDetailsUI isn't blank and editable
                AssignmentDetailsController controller = loader.getController();
                controller.displayAssignment(assignment);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        assignmentsList.getChildren().add(newButton);
        borderPane.getRight().setVisible(false);
    }

    /**
     * Remove the assignment button whose name is given,
     * and also remove this assignment from the AssignmentManager
     */
    @FXML
    public void removeAssignment(String assignmentTitle) {
        assignments.deleteAssignment(assignmentTitle);
        borderPane.getRight().setVisible(false);
        for (Node n : assignmentsList.getChildren()) {
            if (((Button) n).getText().equals(assignmentTitle)) {
                assignmentsList.getChildren().remove(n);
                return;
            }
        }
    }

    /**
     * method that is called when the "add assignment" button is clicked.
     * The right side of the border pane is set to the AssignmentDetailsUI, which by default
     * is ready to take in user input about a new assignment
     */
    @FXML
    public void addAssignmentUI(ActionEvent actionEvent) throws Exception {
        borderPane.setRight(FXMLLoader.load(getClass().getClassLoader().getResource("Assignment Details UI.fxml")));
    }

    // this method should not be here because the scene swapping logic should be in MenuBarController.java>App.java
    public static void goToTimer() {
        App.goToTimerScene(assignments);
    }

}
