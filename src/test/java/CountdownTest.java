import org.studybuddy.Countdown;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountdownTest {
    // Example of a Unit Test
    @Test
    public void testSettingCountdownTimer() {
        Countdown.setCountdownStarter(50);
        Assertions.assertEquals(Countdown.countdownStarter, 50);
    }


    @Test
    public void testingInvalidCountdown() {
        Countdown.setCountdownStarter(-1);
        Assertions.assertEquals(Countdown.countdownStarter, -1);
        try {
            Countdown.main(null);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }
    }

}
