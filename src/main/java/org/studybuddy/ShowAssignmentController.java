package org.studybuddy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowAssignmentController {

    @FXML
    public Label assignmentTitle;

    @FXML
    public Text completionText;

    @FXML
    public Text dueDate;

    @FXML
    public Text timeSpent;

    @FXML
    public Text timeRemaining;

    @FXML
    public Text description;

    @FXML
    public void displayAssignment(String name, String desc, String time, String due) {
        assignmentTitle.setText(name);
        completionText.setText("estimated ??% completed");
        dueDate.setText(due);
        timeSpent.setText("0:00");
        timeRemaining.setText(time);
        description.setText(desc);
    }

    @FXML
    public void markDone() {
        AssignmentsPageController controller = App.assignmentsSceneLoader.getController();
        controller.removeAssignment(assignmentTitle.getText());
    }


}
