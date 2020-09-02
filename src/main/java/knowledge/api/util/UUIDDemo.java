package knowledge.api.util;

import org.junit.Test;

import java.util.UUID;

/**
 * UUIDDemo
 *
 * @author ljh
 * created on 2020/9/2 17:43
 */
public class UUIDDemo {
    
    @Test
    public void test() {
        System.out.println(UUID.randomUUID().version());
        // creating UUID      
        System.out.println(UUID.randomUUID());
        UUID x = UUID.fromString("aa743d11-bbdb-4a16-94c9-10dbc9473d2b");

        // getting clock sequence value
        System.out.println("Clock sequence value: "+x.clockSequence());

    }
}
