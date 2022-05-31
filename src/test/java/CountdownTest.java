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
}
