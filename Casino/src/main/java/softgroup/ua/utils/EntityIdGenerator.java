package softgroup.ua.utils;


import java.util.UUID;

public class EntityIdGenerator {

    public static Long random() {
        return UUID.randomUUID().getLeastSignificantBits();
    }
}
