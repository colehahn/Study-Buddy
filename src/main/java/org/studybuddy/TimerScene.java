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

public class TimerScene implements EventHandler<ActionEvent> {

    public static Scene getScene() {
        Label title = new Label("Study Timer");

        // go to assignment button
        Button goToAssignments = new Button("Assignments");
        goToAssignments.setOnAction(App.goToAssignmentsScene());


        // timer variables
        Label timerLabel = new Label();
        final Integer[] timeSeconds = {120};
        final Timeline[] timeline = {null};

        // start timer button
        Button startTimer = new Button("Start Timer");
        startTimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (timeSeconds[0] < 0) {
                    throw new IllegalArgumentException("Study timer cannot be set to negative value");
                }
                if (timeline[0] != null) {
                    timeline[0].stop();
                }



                // update timerLabel
                timerLabel.setText(timeSeconds[0].toString());
                timeline[0] = new Timeline();
                timeline[0].setCycleCount(Timeline.INDEFINITE);
                timeline[0].getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1),
                                new EventHandler() {
                                    // KeyFrame event handler
                                    @Override
                                    public void handle(Event event) {
                                        timeSeconds[0]--;
                                        // update timerLabel
                                        timerLabel.setText(timeSeconds[0].toString());
                                        if (timeSeconds[0] <= 0) {
                                            timeline[0].stop();
                                        }
                                    }
                                }));
                timeline[0].playFromStart();
            }
        });

        // timer label
        timerLabel.setText(timeSeconds[0].toString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 4em;");

        VBox timerLayout = new VBox(50);
        timerLayout.getChildren().addAll(title, timerLabel, startTimer, goToAssignments);
        timerLayout.setAlignment(Pos.CENTER);
        timerLayout.setLayoutY(30);  // Move the VBox down a bit
        // timerLayout.setStyle("-fx-background-color: BEIGE;"); an example of inline CSS. can also add a .css file
        return new Scene(timerLayout, 1000, 800);
    }

    @Override
    public void handle(ActionEvent event) {
        // can handle timer-specific events here, or just use lambdas
    }
}
