import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.xml.bind.DatatypeConverter;

public class Cracker {

    public static String crackSha256(String h, String chars) {
        // if the string is lower than 10 chars, it generates 9!=362880 possibilities at most.
        ArrayList<String> variations = permute(chars);
        System.out.println("Number of permuted elements="+variations.size());
        for (String var : variations) {
            if (Objects.equals(generateSHA256(var), h)) return var;
        }
        return null;
    }

    public static int calculateInverse(int number, int zp) {
        if (euclidean_gcd(number, zp) != 1) {
            return -1;
        }
        ArrayList<Integer> g_int = new ArrayList<>();
        ArrayList<Integer> u_int = new ArrayList<>();
        ArrayList<Integer> v_int = new ArrayList<>();
        g_int.add(zp);
        g_int.add(number);
        u_int.add(1);
        u_int.add(0);
        v_int.add(0);
        v_int.add(1);
        int i = 1;
        for(; g_int.get(i) != 0; i++) {
            int yi = (g_int.get(i-1)/g_int.get(i));
            g_int.add(g_int.get(i-1)-yi*g_int.get(i));
            u_int.add(u_int.get(i-1)-yi*u_int.get(i));
            v_int.add(v_int.get(i-1)-yi*v_int.get(i));
        }
        int aux = 0;
        if ((aux = v_int.get(i-1)) < 0) {
            v_int.add(i-1, aux+zp);
        }
        return v_int.get(i-1);
    }


    public static int euclidean_gcd(int a, int b) {
        // implementing Euclidean algorithm
        int greater, lower;
        if (b > a) {
            greater = b;
            lower = a;
        } else {
            greater = a;
            lower = b;
        }
        int remainder = -1;
        while (remainder != 0) {
            greater = lower*(greater/lower) + (remainder=greater-(lower*(greater/lower)));
            greater = lower;
            lower = remainder;
        }
        return greater;
    }

    private static ArrayList<String> permute(String chars) {
        ArrayList<String> variations = new ArrayList<>();
        if (chars.length() == 1) {
            variations.add(chars);
            return variations;
        }
        for (int i = 0; i < chars.length(); i++) {
            String c = "";
            for (int j = 0; j < chars.length(); j++) {
                if (i == j) continue;
                c += chars.charAt(j);
            }
            ArrayList<String> pet = permute(c);
            for (String s : pet) {
                variations.add(chars.charAt(i)+s);
            }
        }
        return variations;
    }

    private static String generateSHA256 (String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            String theHash = DatatypeConverter.printHexBinary(hash);
            return theHash.toLowerCase();
        }catch(Exception e){
            return null;
        }
    }
}