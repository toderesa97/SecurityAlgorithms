import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class CrackerTests {

    @Test
    public void firstSteps () {
        assertEquals("cab", Cracker.crackSha256("6548d955790a22925c1e23508ec4e2bffb8e45d80261b4b2c1f9d8c9b0d152b6", "abc"));
    }
}