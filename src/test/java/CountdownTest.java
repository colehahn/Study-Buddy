import Timer.Countdown;
import org.junit.Assert;
import org.junit.Test;

class CountdownTest {
    // Example of a Unit Test
    @Test
    public void testSettingCountdownTimer() {
        Countdown.setCountdownStarter(50);
        Assert.assertEquals(Countdown.countdownStarter, 50);
    }
}
