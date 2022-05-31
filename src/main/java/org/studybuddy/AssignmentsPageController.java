package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AssignmentsPageController {

    public static AssignmentManager assignments;

    @FXML
    public BorderPane borderPane;

    @FXML
    public Button addAssignmentUIButton;

    @FXML
    public VBox assignmentsList;

    @FXML
    public void initialize() {
        assignments = new AssignmentManager();  // TODO: load saved ones from disk
        try {
            for (AssignmentClass assignment : assignments) {
                addAssignment(assignment);
            }
            borderPane.setLeft(FXMLLoader.load(getClass().getClassLoader().getResource("Menu Bar.fxml")));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addAssignment(AssignmentClass assignment) {
        assignments.addAssignment(assignment);
        Button newButton = new Button(assignment.getName());

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
                AssignmentDetailsController controller = loader.getController();
                controller.displayAssignment(assignment);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        assignmentsList.getChildren().add(newButton);
        borderPane.getRight().setVisible(false);
    }
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

    @FXML
    public void addAssignmentUI(ActionEvent actionEvent) throws Exception {
        borderPane.setRight(FXMLLoader.load(getClass().getClassLoader().getResource("Assignment Details UI.fxml")));
    }


    @FXML
    public void handleExit() {
        //when the user leaves the app, we want to save their assignments
    }

    public static void goToTimer() {
        App.goToTimerScene(assignments);
    }

}
