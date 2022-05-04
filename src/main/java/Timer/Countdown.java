package Timer;

import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;
// The timer is part of the Model.
public class Countdown {

    public static int countdownStarter = 20;

    public static void setCountdownStarter(int n) {
        countdownStarter = n;
    }

    public static void main(String[] args) {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            // We can set this to the amount of time we want to set the timer
            // Maybe use scanner to get input from the console
            // or pass down value passed in from user (the controller) to the
            // model? (Which this is part of the model)
            // Try to understand this code and make comments for it.
            // https://www.delftstack.com/howto/java/countdown-timer-java/#:~:text=To%20create%20the%20countdown%20timer%2C%20we%20create%20a,
            // and%20the%20period%20in%20milliseconds%20between%20each%20execution.?
            // msclkid=02d52a70c9b711eca5ee03f124ccfddc.
            // Can we use code from other sites and just reference them.

            public void run() {

                System.out.println(countdownStarter);
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

