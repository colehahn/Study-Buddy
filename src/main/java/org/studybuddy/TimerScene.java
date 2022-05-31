package org.studybuddy;

import Timer.Countdown;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

// TODO: implement break timer
// TODO: allow user to choose study and break times

public class TimerScene implements EventHandler<ActionEvent> {
    public static Label timerLabel;
    public static Timeline[] timeline = {null};
    public static boolean isPaused = false;
    public static Scene getScene() throws Exception {
        // Added this code here to test writing and reading to csv files.
        AssignmentManager manager = new AssignmentManager();
        manager.addAssignment("test", "test", "00:00:00", "04/22/22");
        manager.readAssignment(new AssignmentClass("test", "test", "00:00:00", "04/22/22"));

        Label title = new Label("Study Timer");
        title.setStyle("-fx-font-size: 2em;");

        // go to assignment button
        Button goToAssignments = new Button("Assignments");
        goToAssignments.setOnAction(App.goToAssignmentsScene());


        // timer variables
        timerLabel = new Label();
        //final Timeline[] timeline = {null};

        // start timer button
        Button startTimer = new Button("Start Timer");
        startTimer.setOnAction(event -> {
            if (Countdown.MINUTES <= 0) {
                throw new IllegalArgumentException("Study timer cannot be set to negative value");
            }
            // stop previous running timers
            if (timeline[0] != null) {
                timeline[0].stop();
            }

            isPaused = false;
            Countdown.setCountdownStarter(Countdown.MINUTES * Countdown.NUM_SECONDS);

            // update timerLabel
            timerLabel.setText(Countdown.MINUTES + ":00");
            timeline[0] = new Timeline();
            timeline[0].setCycleCount(Timeline.INDEFINITE);
            // KeyFrame event handler
            timeline[0].getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1), (event2) -> {
                                Countdown.countdownStarter = Countdown.MINUTES * Countdown.NUM_SECONDS;
                                Countdown.main(null);
                            }));
            timeline[0].playFromStart();
        });

        // Pause Resume Button
        Button pauseResumeButton = new Button("Pause / Resume Timer");
        pauseResumeButton.setOnAction(e -> isPaused = !isPaused);


        // timer label
        timerLabel.setText(Countdown.MINUTES + ":00");
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

        VBox timerLayout = new VBox(50);
        timerLayout.getChildren().addAll(title, timerLabel, startTimer, pauseResumeButton);
        timerLayout.setAlignment(Pos.CENTER);
        timerLayout.setLayoutY(30);  // Move the VBox down a bit

        HBox scene = new HBox(300);
        scene.getChildren().addAll(leftPane(), timerLayout);
        return new Scene(scene, 1000, 560);
    }

    private static Pane leftPane() {
        Pane pane = new Pane();
        Button goToTimer = new Button("Timer");
        Button assignment = new Button("Assignment");

        assignment.setOnAction(App.goToAssignmentsScene());
        pane.getChildren().addAll(goToTimer);
        pane.getChildren().addAll(assignment);
        pane.setPrefWidth(220);
        pane.setPrefHeight(560);

        goToTimer.setFont(new Font("Regular", 25));
        goToTimer.setStyle("-fx-background-color:  rgb(217,217,217)");
        goToTimer.setPrefWidth(200);
        goToTimer.setPrefHeight(75);
        goToTimer.setLayoutX(8);
        goToTimer.setLayoutY(40);

        assignment.setFont(new Font("Regular", 25));
        assignment.setStyle("-fx-background-color:   rgb(183,183,183)");
        assignment.setPrefWidth(200);
        assignment.setPrefHeight(75);
        assignment.setLayoutX(8);
        assignment.setLayoutY(136);

        return pane;
    }

    @Override
    public void handle(ActionEvent event) {
        // can handle timer-specific events here, or just use lambdas
    }
}

