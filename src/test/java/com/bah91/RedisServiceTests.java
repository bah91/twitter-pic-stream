package com.bah91;

import com.bah91.services.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by bah91 on 15/08/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTests {

    @Autowired
    RedisService redisService;

    @Test
    public void test_Can_Set_And_Retrieve() {
        String uuid = UUID.randomUUID().toString();
        redisService.store(uuid, "HI!");
        assertEquals("HI!",
                redisService.retrieve(uuid));
    }

    @Test
    public void test_Retrieve_Non_Existent_Value() {
        assertEquals(null,
                redisService.retrieve("dfsdfs"));
    }

}
