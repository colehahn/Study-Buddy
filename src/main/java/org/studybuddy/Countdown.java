package org.studybuddy;

import javax.sound.sampled.*;

import java.net.URL;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.studybuddy.TimerScene.*;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import org.studybuddy.TimerScene;
import java.io.*;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

/**
 * Class that runs the timer,
 * and updates it appropriately on the UI
 */
public class Countdown {
    public static int STUDY_MINUTES = 2;
    public static int BREAK_MINUTES = 1;
    public static int MINUTES = isStudyTime? STUDY_MINUTES : BREAK_MINUTES;
    public static int NUM_SECONDS = 60;
    public static int countdownStarter = MINUTES * NUM_SECONDS;
    public static int cyclesCompleted = 0;


    /**
     * Sets the timer to n minutes.
     * @param n specifies the amount of minutes to set the timer.
     */
    public static void setCountdownStarter(int n) {
        countdownStarter = n;
    }

    // Where the code came from:
    // https://www.delftstack.com/howto/java/countdown-timer-java/#:~:
    // text=To%20create%20the%20countdown%20timer%2C%20we%20create%20a,
    // and%20the%20period%20in%20milliseconds%20between%20each%20execution.?
    /**
     * Runs the timer for a given amount of minutes and seconds
     * @param args
     */
    public static void main(String[] args) {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            // Updates the time in timer and show it on the timer label
            public void run() {
                if (MINUTES < 1) {
                    throw new IllegalArgumentException("Minutes must be at least 1");
                }
                if (TimerScene.isPaused) {
                    TimerScene.timeline[0].pause();
                } else {
                    if (countdownStarter < NUM_SECONDS) { // Less than a minute remaining
                        if (countdownStarter <= 10) {
                            if (TimerScene.timeline[0] != null) {
                                TimerScene.timeline[0].stop();
                            }
                            Platform.runLater(() -> {
                                TimerScene.timerLabel.setText("0:" + "0" + countdownStarter);
                            });
                        } else {
                            if (TimerScene.timeline[0] != null) {
                                TimerScene.timeline[0].stop();
                            }
                            Platform.runLater(() -> {
                                TimerScene.timerLabel.setText("0:" + countdownStarter);
                            });
                        }
                    } else { // Minutes
                        String minutes = "" + countdownStarter / 60;
                        String seconds;
                        if ((countdownStarter % 60) < 10) {
                            seconds = "0" + countdownStarter % 60;
                        } else {
                            seconds = "" + countdownStarter % 60;
                        }
                        Platform.runLater(() -> {
                            // code that updates UI
                            if (TimerScene.timeline[0] != null) {
                                TimerScene.timeline[0].stop();
                            }
                            TimerScene.timerLabel.setText(minutes + ":" + seconds);
                        });
                    }

                    countdownStarter--;

                    // When timer is done
                    if (countdownStarter <= 0) {
                        Platform.runLater(() -> {
                            TimerScene.timerLabel.setText("Timer Over!");
                            // adjust title label
                            String s = isStudyTime ? "Study time!" : "Take a break!";
                            titleLabel.setText(s);
                            // adjust start button's text
                            s = isStudyTime ? "Start Studying" : "Begin Break";
                            startTimer.setText(s);

                            // update cycles completed label
                            cyclesCompletedLabel.setText("Study Cycles Completed: " + cyclesCompleted);

                            scheduler.shutdown();

                            // Play sound
                            try {
                                playBeep();
                            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        // increment cycles completed
                        if (isStudyTime) {
                            cyclesCompleted++;
                        }

                        // Implement break timer, able to switch
                        isStudyTime = !isStudyTime;
                        MINUTES = isStudyTime? STUDY_MINUTES : BREAK_MINUTES;
                        countdownStarter = MINUTES * NUM_SECONDS;

                        // change color
                        Color timerColor = isStudyTime ? Color.RED : Color.BLUE;
                        timerLabel.setTextFill(timerColor);
                    }
                }

            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

    // Where I got the sound: https://free-loops.com/3328-alarmclock-sound.html (wav file).
    // Referenced code: https://www.codejava.net/coding/how-to-play-back-audio-in-java-with-examples.
    // Where I got the sound: https://free-loops.com/3328-alarmclock-sound.html (wav file).
    /**
     * Plays the notification sound when the timer is done.
     * @throws UnsupportedAudioFileException is thrown when the audio file is not supported.
     * @throws IOException is thrown when something wring happens when opening the file.
     * @throws LineUnavailableException
     */
    public static void playBeep() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL url = ClassLoader.getSystemResource("ringing.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);
        audioClip.open(audioStream);
        audioClip.start();
    }
}



