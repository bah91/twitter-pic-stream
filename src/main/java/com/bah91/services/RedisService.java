package com.bah91.services;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by bah91 on 15/08/17.
 */
@Service
public class RedisService {


    private JedisPool pool;


    public RedisService() {
        pool = new JedisPool(new JedisPoolConfig(), "localhost");
    }

    public void store(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(key, value);
        }
    }

    public String retrieve(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }


}
