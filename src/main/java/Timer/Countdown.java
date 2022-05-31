package Timer;

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

// The timer is part of the Model.
public class Countdown {
    public static int STUDY_MINUTES = 2;
    public static int BREAK_MINUTES = 1;
    public static int MINUTES = isStudyTime? STUDY_MINUTES : BREAK_MINUTES;
    public static int NUM_SECONDS = 60;
    public static int countdownStarter = MINUTES * NUM_SECONDS;

    // Update this so that it sets the MINUTES and NUM_SECONDS
    public static void setCountdownStarter(int n) {
        countdownStarter = n;
    }
    public static void main(String[] args) {
        // We can set this to the amount of time we want to set the timer
        // Maybe use scanner to get input from the console
        // or pass down value passed in from user (the controller) to the
        // model? (Which this is part of the model)
        // Try to understand this code and make comments for it.
        // https://www.delftstack.com/howto/java/countdown-timer-java/#:~:text=To%20create%20the%20countdown%20timer%2C%20we%20create%20a,
        // and%20the%20period%20in%20milliseconds%20between%20each%20execution.?
        // msclkid=02d52a70c9b711eca5ee03f124ccfddc.
        // Can we use code from other sites and just reference them.
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {

            public void run() {
                if (MINUTES < 1) {
                    throw new IllegalArgumentException("Minutes must be at least 1");
                }
                if (TimerScene.isPaused) {
                    TimerScene.timeline[0].pause();
                } else {
                    if (countdownStarter < NUM_SECONDS) { // Seconds
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

                    if (countdownStarter <= 0) {
                        Platform.runLater(() -> {
                            TimerScene.timerLabel.setText("Timer Over!");
                            // adjust title label
                            String s = isStudyTime ? "Study time!" : "Take a break!";
                            titleLabel.setText(s);
                            // adjust start button's text
                            s = isStudyTime ? "Start Studying" : "Begin Break";
                            startTimer.setText(s);

                            scheduler.shutdown();

                            // play sound
                            try {
                                playBeep();
                            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
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



