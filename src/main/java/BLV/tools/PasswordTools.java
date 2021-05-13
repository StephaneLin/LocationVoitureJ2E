package BLV.tools;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordTools {
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;

    private static final int NUMBER_OF_ITERATIONS = 2;
    private static final int MEMORY = 65536;
    private static final int PARALLELISM = 1;

    private static Argon2 instanciateArgon2() {
        return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i, SALT_LENGTH, HASH_LENGTH);
    }

    public static String generatePassword(String password) {
        return instanciateArgon2().hash(NUMBER_OF_ITERATIONS, MEMORY, PARALLELISM, password);
    }

    public static boolean validatePassword(String password, String hashCorrect) {
        return instanciateArgon2().verify(hashCorrect, password);
    }

    public static void main(String[] args) {
        System.out.println(String.format("Le mot de passe chiffré est : %s", PasswordTools.generatePassword("testword")));
    }
}
