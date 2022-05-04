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

