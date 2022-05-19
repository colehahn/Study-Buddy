package org.studybuddy;

import Timer.Countdown;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import Timer.Countdown.*;

public class TimerScene implements EventHandler<ActionEvent> {
    public static Label timerLabel;
    public static Integer[] timeSeconds = {1};
    public static Timeline[] timeline = {null};
    public static boolean isPaused = false;
    public static Scene getScene() {
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
            // TODO: have timer reset when button is pressed
            if (timeSeconds[0] < 0) {
                throw new IllegalArgumentException("Study timer cannot be set to negative value");
            }
            // stop previous running timers
            if (timeline[0] != null) {
                timeline[0].stop();
            }

            isPaused = false;

            // update timerLabel
            timerLabel.setText(timeSeconds[0].toString());
            timeline[0] = new Timeline();
            timeline[0].setCycleCount(Timeline.INDEFINITE);
            // KeyFrame event handler
            timeline[0].getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1), (event2) -> {
//                                        timeSeconds[0]--;
//                                        // update timerLabel
//                                        timerLabel.setText(timeSeconds[0].toString());
//                                        if (timeSeconds[0] <= 0) {
//                                            timeline[0].stop();
//
//                                            // alert or notification signalling the timer is done
                                Countdown.countdownStarter = Countdown.MINUTES * Countdown.NUM_SECONDS;
                                Countdown.main(null);
//                                        }
                            }));
            timeline[0].playFromStart();
        });

        // TODO: Pause Resume Button
        Button pauseResumeButton = new Button("Pause / Resume Timer");
        pauseResumeButton.setOnAction(e -> isPaused = !isPaused);


        // timer label
        timerLabel.setText(timeSeconds[0].toString());  // TODO: connect with backend time value for initial label
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

        VBox timerLayout = new VBox(50);
        timerLayout.getChildren().addAll(title, timerLabel, startTimer, pauseResumeButton, goToAssignments);
        timerLayout.setAlignment(Pos.CENTER);
        timerLayout.setLayoutY(30);  // Move the VBox down a bit
        timerLayout.setStyle("-fx-background-color: BEIGE;"); // an example of inline CSS. can also add a .css file
        return new Scene(timerLayout, 1000, 800);
    }

    @Override
    public void handle(ActionEvent event) {
        // can handle timer-specific events here, or just use lambdas
    }
}

