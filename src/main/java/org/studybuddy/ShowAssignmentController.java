package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowAssignmentController {

    @FXML
    public BorderPane borderPane;

    @FXML
    public Pane leftPane;

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
    public VBox middlePane;

    @FXML
    public Text dueDate;

    @FXML
    public Text timeSpent;

    @FXML
    public Text timeRemaining;
   // @FXML
   /// public void initialize() {
   //     this.assignments = new AssignmentManager();
   // }

    public void goToTimer(ActionEvent actionEvent) {
        App.goToTimerScene();
    }

    public void assignment1UI(ActionEvent actionEvent) {
        dueDate.setText("12/12/12");
        timeSpent.setText("2:00");
        timeRemaining.setText("2:00");
    }

    public void addAssignmentUI(ActionEvent actionEvent) throws Exception {
        Scene scene = FXMLLoader.load(ClassLoader.getSystemClassLoader().getResource("Add Assignment UI.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
