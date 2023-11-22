package managment.productservice.utils;

import java.util.UUID;

public class RandomNumberGenerator {
    public static Long numberGenerator(){
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
