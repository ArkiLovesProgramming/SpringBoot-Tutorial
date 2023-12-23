package arki.redis;

import redis.clients.jedis.Jedis;

public class TestCon {
    public static void main(String[] args) {
        //1. new jedis object
        Jedis jedis = new Jedis("34.152.25.47", 6379);
        System.out.println(jedis.ping());
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.hget("myhash", "name"));
    }
}
