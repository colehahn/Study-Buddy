package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        assignments = new AssignmentManager();
        try {
            borderPane.setLeft(FXMLLoader.load(getClass().getClassLoader().getResource("Menu Bar.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAssignment(String name, String desc, String time, String due) throws Exception {
        assignments.addAssignment(name, desc, time, due);
        Button newButton = new Button(name);
        newButton.setOnAction(actionEvent -> {
            try {
                borderPane.setRight(FXMLLoader.load(getClass().getClassLoader().getResource("Show Assignment UI.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        assignmentsList.getChildren().add(newButton);
    }

    @FXML
    public void addAssignmentUI(ActionEvent actionEvent) throws Exception {
        borderPane.setRight(FXMLLoader.load(getClass().getClassLoader().getResource("Add Assignment UI.fxml")));
    }


    @FXML
    public void handleExit() {
        //when the user leaves the app, we want to save their assignments
    }
}
