import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.*;

public class CrackerTests {

    @Test
    public void firstSteps () {
        assertEquals("cab", Cracker.crackSha256("6548d955790a22925c1e23508ec4e2bffb8e45d80261b4b2c1f9d8c9b0d152b6", "abc"));
    }
    @Test
    public void testRandomExamples() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();
        int alphabetLength = alphabet.length();
        for (int i=8; i<76; i+=4) {
            char[] randomCharsArray = new char[i/8];
            for (int j=0; j<randomCharsArray.length; j++)
                randomCharsArray[j] = alphabet.charAt(r.nextInt(alphabetLength));
            String randomString = new String(randomCharsArray);
            System.out.println(randomString);
            Arrays.sort(randomCharsArray);
            assertEquals(randomString, Cracker.crackSha256(sha256(randomString), new String(randomCharsArray)));
        }
    }

    private static String sha256(String text) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(text.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new RuntimeException();
        }
    }
}