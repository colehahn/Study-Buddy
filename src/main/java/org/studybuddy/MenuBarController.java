package org.studybuddy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller class for the menu bar, which consists of 2 buttons, one for each scene
 */

public class MenuBarController {

    // references to the on-screen buttons
    @FXML
    public Button timerButton, assignmentsPageButton;

    // methods that are called when the buttons are clicked
    // this should use App's goToTimerScene()
    public void goToTimer() {
        AssignmentsPageController.goToTimer();
    }

    // TODO: note that there should also be a goToAssignments method, but because the...
    // timer page does not use fxml, it is not set up to use this controller

}
