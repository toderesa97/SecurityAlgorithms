public class Main {
    public static void main(String[] args) {
        // returns a possible set of understandable sentences
        System.out.println(Cracker.decrypt_affine_cipher(Cracker.encrypt_affine_cipher("hi how are u",7,2)));
        String encrypted_mesg = Cracker.encrypt_affine_cipher("life is too hard",21,124);
        System.out.println(encrypted_mesg);
        System.out.println(Cracker.decrypt_affine_cipher(encrypted_mesg));

        encrypted_mesg = Cracker.encrypt_shift_cipher("this is an encrypted message using caesar cipher",21);
        System.out.println(encrypted_mesg);
        System.out.println(Cracker.decrypt_shift_cipher(encrypted_mesg));

        // who wrote the message ?
        encrypted_mesg = "xultpaajcxitltlxaarpjhtiwtgxktghidhipxciwtvgtpilpit" +
                "ghlxiwiwtxgqadds";
        System.out.println(Cracker.decrypt_shift_cipher(encrypted_mesg));
        System.out.println(Cracker.
                encrypt_shift_cipher("if we all unite we will cause the rivers " +
                        "to stain the great waters with their blood",15).replaceAll(" ", ""));
        // Tecumseh
    }
}
