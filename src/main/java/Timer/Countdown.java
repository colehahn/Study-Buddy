package Timer;

import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
// The timer is part of the Model.
public class Countdown {

    public static int MINUTES = 10;
    public static int NUM_SECONDS = 60;
    public static int countdownStarter = MINUTES * NUM_SECONDS;
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
                if (countdownStarter < NUM_SECONDS) { // Seconds
                    if (countdownStarter < 10) {
                        System.out.println("0:" + "0" + countdownStarter);
                    } else {
                        System.out.println("0:" + countdownStarter);
                    }
                } else { // Minutes
                    String minutes;
                    if (countdownStarter/ NUM_SECONDS < 10) {
                        minutes =  "0" + countdownStarter /60;
                    } else {
                        minutes = "" + countdownStarter / 60;
                    }
                    String seconds;
                    if ((countdownStarter % 60) < 10) {
                        seconds = "0" + countdownStarter % 60;
                    } else {
                        seconds = "" + countdownStarter % 60;
                    }
                    System.out.println(minutes + ":" + seconds);
                }

                countdownStarter--;

                if (countdownStarter < 0) {
                    System.out.println("Timer Over!");
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}


