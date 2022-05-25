package org.studybuddy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.Objects;
import java.util.Timer;

/**
 * JavaFX App
 **/
public class App extends Application {
    static Stage window;
    static Scene timer, assignments;

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        // scene 1, timer page
        timer = TimerScene.getScene();

        // scene 2, assignments
        try {
            assignments = FXMLLoader.load(ClassLoader.getSystemClassLoader().getResource("Add Assignments UI.fxml"));
        } catch(Exception e) {
            e.printStackTrace();
        }

        window.setScene(timer);
        window.setTitle("Study Buddy");
        window.getIcons().add(new Image("SB logo.png"));
        window.show();
    }


    public static void main(String[] args) {
        launch();
    }

    public static EventHandler<ActionEvent> goToAssignmentsScene() {
        return ((e -> window.setScene(assignments)));
    }

    public static void goToTimerScene() {
        window.setScene(timer);
    }

}