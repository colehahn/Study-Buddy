package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuBarController {

    @FXML
    public Button timerButton;

    @FXML
    public Button assignmentsPageButton;


    public void goToTimer() {
        AssignmentsPageController.goToTimer();
    }


    @FXML
    public void handleExit() {
        //when the user leaves the app, we want to save their assignments
    }
}
