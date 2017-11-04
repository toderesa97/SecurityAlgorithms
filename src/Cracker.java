import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.DatatypeConverter;

public class Cracker {

    private String letters;

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
            int factor = greater/lower;
            int dummy = lower*factor + (remainder=greater-(lower*factor));
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
            StringBuilder c = new StringBuilder();
            for (int j = 0; j < chars.length(); j++) {
                if (i == j) continue;
                c.append(chars.charAt(j));
            }
            ArrayList<String> pet = permute(c.toString());
            for (String s : pet) {
                variations.add(chars.charAt(i)+s);
            }
        }
        return variations;
    }

    public static String generateSHA256 (String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            String theHash = DatatypeConverter.printHexBinary(hash);
            return theHash.toLowerCase();
        }catch(Exception e){
            return null;
        }
    }

    public static String encrypt_shift_cipher (String x, int key) {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder y = new StringBuilder();
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == ' ') {
                y.append(" ");
                continue;
            }
            y.append(letters.charAt(Math.floorMod(letters.indexOf(x.charAt(i)) + key, 26)));
        }
        return y.toString();
    }

    public static String encrypt_affine_cipher (String x, int a, int b) {
        // to obtain the digest apply y=(a*x+b)mod(26)
        // a must be relatively prime to 26, ie: gcd(a, 26)=1
        if (euclidean_gcd(26, a) != 1) {
            return null;
        }
        StringBuilder y = new StringBuilder();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == ' ') {
                y.append(" ");
                continue;
            }
            y.append(letters.charAt(Math.floorMod(letters.indexOf(x.charAt(i)) * a + b, 26)));
        }
        return y.toString();
    }

    public static List<String> decrypt_affine_cipher (String y) {
        // reverting the modular equation y=(a*x+b)mod(26)
        // (y-b)mod(26)=a*x*mod(26) -> ((y-b)*inv(a))mod(26)=x
        int[] keys_a = {1,3,5,7,9,11,15,17,19,21,23,25};
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String[] common_words = {"the", "of", "to", "is", "are", "and", "on", "from"};
        StringBuilder x;
        ArrayList<String> posible = new ArrayList<>();
        for (int a = 0; a < keys_a.length; a++) {
            for (int b = 0; b < 26; b++) {
                x = new StringBuilder();
                for (int i = 0; i < y.length(); i++) {
                    if (y.charAt(i) == ' ') {
                        x.append(" ");
                        continue;
                    }
                    x.append(letters.charAt(Math.floorMod(calculateInverse(keys_a[a],26)*(letters.indexOf(y.charAt(i))-b),26)));
                }
                for (String w : common_words) {
                    if (x.toString().matches(".*\\b"+w+"\\b.*")) {
                        posible.add(x.toString());
                    }
                }
            }
        }
        return posible;
    }

    public static List<String> decrypt_shift_cipher (String y) {
        // based on shift encryption
        String[] common_words = {"the", "are","of", "to","on" ,"is", "and", "from"};
        String letters = "abcdefghijklmnopqrstuvwxyz";
        // x=d(y)=(y-k)mod(26)
        StringBuilder x;
        ArrayList<String> possible = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            x = new StringBuilder();
            for (int j = 0; j < y.length(); j++) {
                if (y.charAt(j) == ' ') {
                    x.append(" ");
                    continue;
                }
                x.append(letters.charAt(Math.floorMod(letters.indexOf(y.charAt(j)) - i, 26)));
            }
            for (String w : common_words) {
                if (! x.toString().contains(" ")) {
                    if (x.toString().contains(w)) possible.add(x.toString());
                    continue;
                }
                if (x.toString().matches(".*\\b"+w+"\\b.*")) {
                    possible.add(x.toString());
                }
            }
        }
        return possible;
    }

}