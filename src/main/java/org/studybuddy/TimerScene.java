package org.studybuddy;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class TimerScene implements EventHandler<ActionEvent> {
    public static Label timerLabel;
    public static Label titleLabel;
    public static Label cyclesCompletedLabel;
    public static Timeline[] timeline = {null};
    public static Button startTimer;
    public static boolean isPaused = false;
    public static boolean isStudyTime = true;
    public static Scene getScene(AssignmentManager assignments) {
        titleLabel = new Label("Study Timer");
        titleLabel.setStyle("-fx-font-size: 2em;");

        // go to assignment button
        Button goToAssignments = new Button("Assignments");
        goToAssignments.setOnAction(App.goToAssignmentsScene());

        // timer variables
        timerLabel = new Label();

        // start timer button
        startTimer = new Button("Start Studying");
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


        // Spinner to adjust study time
        Label adjust1 = new Label("Study Time: (minutes)");
        final Spinner<Integer> spinner1 = new Spinner<>();
        final int initialValue1 = 20;
            // Value factory.
        SpinnerValueFactory<Integer> valueFactory1 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 120, initialValue1);
        spinner1.setValueFactory(valueFactory1);
        // Event when spinner changes value
        spinner1.valueProperty().addListener((observable, oldValue, newValue) -> {
            Countdown.STUDY_MINUTES = newValue;
            Countdown.MINUTES = isStudyTime? Countdown.STUDY_MINUTES : Countdown.BREAK_MINUTES;
        });
        // location adjustments
        FlowPane input1 = new FlowPane();
        input1.setHgap(10);
        input1.getChildren().addAll(adjust1, spinner1);


        // Spinner to adjust break time
        Label adjust2 = new Label("Break Time: (minutes)");
        final Spinner<Integer> spinner2 = new Spinner<>();
        final int initialValue2 = 5;
        // Value factory.
        SpinnerValueFactory<Integer> valueFactory2 =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, initialValue2);
        spinner2.setValueFactory(valueFactory2);
        // Event when spinner changes value
        spinner2.valueProperty().addListener((observable, oldValue, newValue) -> {
            Countdown.BREAK_MINUTES = newValue;
            Countdown.MINUTES = isStudyTime? Countdown.STUDY_MINUTES : Countdown.BREAK_MINUTES;
        });
        // location adjustments
        FlowPane input2 = new FlowPane();
        input2.setHgap(10);
        input2.getChildren().addAll(adjust2, spinner2);


        // cycles completed label
        cyclesCompletedLabel = new Label("Study Cycles Completed: 0");

        // add all components together
        HBox timerButtons = new HBox(40);
        timerButtons.getChildren().addAll(startTimer, pauseResumeButton);
        timerButtons.setAlignment(Pos.CENTER);
        VBox timerLayout = new VBox(40);
        timerLayout.getChildren().addAll(titleLabel, timerLabel, timerButtons, input1, input2, cyclesCompletedLabel);
        timerLayout.setAlignment(Pos.CENTER);
        timerLayout.setLayoutY(30);  // Move the VBox down a bit

        HBox scene = new HBox(300);
        scene.getChildren().addAll(leftPane(), timerLayout);
        return new Scene(scene, 1000, 560);
    }

    // Buttons for assignment scene and timer scene
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

