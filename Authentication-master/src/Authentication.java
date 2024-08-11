import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.Arrays;
import java.util.Base64;

public class Authentication {
    public static String HashPassword(String password) {
        return Base64.getEncoder().encodeToString(BCrypt.withDefaults().hash(6, password.getBytes()));
    }

    public static boolean VerifyPassword(String password, String hash) {
        return BCrypt.verifyer().verify(password.getBytes(), Base64.getDecoder().decode(hash)).verified;
    }

}

