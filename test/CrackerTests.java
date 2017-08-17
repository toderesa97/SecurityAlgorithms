import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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

    @Test
    public void mcdTests() {
        assertEquals(1, Cracker.euclidean_gcd(45,7));
        assertEquals(5, Cracker.euclidean_gcd(45,5));
        assertEquals(10, Cracker.euclidean_gcd(50,10));
        assertEquals(1, Cracker.euclidean_gcd(157,211));
        assertEquals(20, Cracker.euclidean_gcd(180,80));
        assertEquals(9, Cracker.euclidean_gcd(180,81));
        assertEquals(6, Cracker.euclidean_gcd(174,180));
        assertEquals(12, Cracker.euclidean_gcd(48,60));
        assertEquals(1, Cracker.euclidean_gcd(18574,459));
        assertEquals(100, Cracker.euclidean_gcd(100,100));
        assertEquals(4, Cracker.euclidean_gcd(816,2260));
    }

    @Test
    public void inverseTests() {
        assertEquals(-1, Cracker.calculateInverse(12, 8));
        assertEquals(9, Cracker.calculateInverse(5, 11));
        assertEquals(2, Cracker.calculateInverse(127, 11));
    }
}