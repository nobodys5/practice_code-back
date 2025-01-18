package youngpil.backend.common.utill.object;

import java.util.Random;

public class AuthNumberCreator {
 
    public static String number() {

        String authNumber = "";

        Random random = new Random();
        for (int count = 0; count < 4; count++) {
            authNumber += random.nextInt(10);
        }

        return authNumber;
    }
}
